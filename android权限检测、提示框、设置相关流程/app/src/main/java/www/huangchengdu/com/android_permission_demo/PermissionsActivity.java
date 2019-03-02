package www.huangchengdu.com.android_permission_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PermissionsActivity extends AppCompatActivity {

    //授权
    public static final int PERMISSIONS_GRANTED = 0;
    //拒绝
    public static final int PERMISSIONS_DENIED = 1;

    //系统权限管理页面参数
    private static final int PERMISSION_REQUEST_CODE = 0;
    //权限参数
    private static final String EXTRA_PERMISSIONS = "www.huangchengdu.com.android_permission_extra";
    //方案
    private static final String PACKAGE_URL_SCHEME = "package:";

    //权限检测器
    private PermissionsChecker mChecker;
    //是否需要系统权限检测，防止
    private boolean isRequireCheck;


    public static void startActivityForResult(Activity activity, int requestCode, String... permissions){
        Intent intent = new Intent(activity, PermissionsActivity.class);
        intent.putExtra(EXTRA_PERMISSIONS, permissions);
        ActivityCompat.startActivityForResult(activity, intent, requestCode,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        if (getIntent() == null || !getIntent().hasExtra(EXTRA_PERMISSIONS)){
            throw new RuntimeException("PermissionsActivity需要startActivityForResult启动");
        }

        mChecker = new PermissionsChecker(this);
        isRequireCheck = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRequireCheck){
            String[] permissions = getPermissions();
            if (mChecker.lacksPermissions(permissions)){
                requestPermissions(permissions);
            }else{
                allPermissionsGranted();
            }
        }else{
            isRequireCheck = true;
        }
    }

    private String[] getPermissions(){
        return getIntent().getStringArrayExtra(EXTRA_PERMISSIONS);
    }

    //请求权限兼容低版本
    private void requestPermissions(String... permissions){
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    //全部权限均已经获取
    private  void allPermissionsGranted(){
        setResult(PERMISSIONS_GRANTED);
        finish();
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)){
            isRequireCheck = true;
            allPermissionsGranted();
        }else{
            isRequireCheck = false;
            showMissingPermissionDialog();
        }
    }

    //全部权限的结果
    private boolean hasAllPermissionsGranted(int[] grantResults){
        for (int grantResult: grantResults){
            if (grantResult == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    //显示缺失权限提示
    private void showMissingPermissionDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PermissionsActivity.this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                setResult(PERMISSIONS_DENIED);
                finish();
            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

    //启动引用的设置
    private void startAppSettings(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }
}
