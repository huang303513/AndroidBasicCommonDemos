package com.ctrip.www.Utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 项目名称：WebService网络请求的实现
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/30 下午3:06
 * 修改人：huangchengdu
 * 修改时间：16/8/30 下午3:06
 * 修改备注：
 */
public class ProgressDialogUtils {
    private static ProgressDialog progressDialog;
    public static void  showProgressDialog(Context context,CharSequence message){
        if (progressDialog == null){
            progressDialog = ProgressDialog.show(context,"",message);
        }else {
            progressDialog.show();
        }
    }


    public static void dismissProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
