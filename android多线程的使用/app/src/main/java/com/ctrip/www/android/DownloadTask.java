package com.ctrip.www.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * 项目名称：android多线程的使用
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/27 下午9:51
 * 修改人：huangchengdu
 * 修改时间：16/8/27 下午9:51
 * 修改备注：
 */
public class DownloadTask extends AsyncTask<Void, Integer,Boolean> {
    private ProgressDialog progressDialog;
    @Override
    /***
     * 在后台任务开始执行前调用
     */
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();//显示进度条
    }

    @Override
    /***
     * 所有需要在子线程中执行的任务都在这个方法中调用
     */
    protected Boolean doInBackground(Void... voids) {
        return false;
        try {
            while (true){
                int downloadPercent = 1;  //doDownload();//这是一个虚构的方法
                publishProgress(downloadPercent);//这个方法会导致onprogressUpdate方法的调用
                if (downloadPercent >= 100){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    /***
     * 更新UI的操作
     */
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //更新下载进度
        progressDialog.setMessage("Downloaded" + values[0] + "%");
    }

    @Override
    /***
     * 后台任务执行完毕以后,会调用这个方法,background中返回的数据可以进行一些更新UI操作
     */
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
        progressDialog.dismiss();
        //在这里显示现在结果
        if (b){
            Toast.makeText(null,"Download succeeded",Toast.LENGTH_SHORT).show();
        }else {
            //Toast.makeText(null,"Download failed",Toast.LENGTH_SHORT).show();
        }
    }
    protected  int doDownload(){
        return  1;
    }

}
