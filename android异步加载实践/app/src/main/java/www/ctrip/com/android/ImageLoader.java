package www.ctrip.com.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/10/27 下午8:04
 * 修改备注：
 */
public class ImageLoader {
    private ImageView mImageView;
    private String mUrl;
    /**
     * handler实现子线程与主线程的通讯
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mImageView.getTag().equals(mUrl)){//只有正确的URl和imageview对应的时候，才设置图片
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    public void showImageByThread(ImageView imageView, final String url){
        mImageView = imageView;
        mUrl = url;
        new Thread(){
            @Override
            /**
             * 默认开启一个子线程来做操作
             */
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromURL(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    /**
     * 使用多线程实现加载图片
     * @param urlString
     * @return
     */
    public Bitmap getBitmapFromURL(String urlString){
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            //Thread.sleep(1000);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 使用Async实现图片的异步加载
     * @param imageView
     * @param url
     */
    public void showImageByAsyncTask(ImageView imageView, String url){
        new NewsAsyncTask(imageView,url).execute(url);
    }

    private class NewsAsyncTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView mImageView;
        private String mUrl;
        public NewsAsyncTask(ImageView imageView,String url){
            mImageView = imageView;
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return getBitmapFromURL(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (mImageView.getTag().equals(mUrl)){
                mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
