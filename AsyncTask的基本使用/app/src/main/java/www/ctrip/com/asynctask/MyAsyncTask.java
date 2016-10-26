package www.ctrip.com.asynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/10/26 下午7:58
 * 修改备注：
 */
public class MyAsyncTask extends AsyncTask<Void,Integer,Void> {
    public MyAsyncTask() {
        super();
        Log.d("xyz","MyAsyncTask");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("xyz","onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("xyz","onPostExecute");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("xyz","onProgressUpdate" + " " + values.toString());
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        Log.d("xyz","onCancelled");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d("xyz","onCancelled");
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("xyz","doInBackground");

        publishProgress(0);
        return null;
    }
}
