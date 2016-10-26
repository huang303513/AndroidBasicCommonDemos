package www.ctrip.com.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/10/26 下午8:53
 * 修改备注：
 */
public class ProgressBarTest extends Activity {
    private ProgressBar mProgressBar;
    private  MyAsyncTask2 mTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        mProgressBar = (ProgressBar) findViewById(R.id.pg);

        mTask = new MyAsyncTask2();
        mTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            mTask.cancel(true);//cancel方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行。

        }
    }

    /**
     * asyntask是顺序执行的，当前任务会阻塞后面的任务。
     */
    class MyAsyncTask2 extends AsyncTask<Void,Integer,Void>{
        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 0; i < 100; i++){
                if (isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            mProgressBar.setProgress(values[0]);
        }
    }
}
