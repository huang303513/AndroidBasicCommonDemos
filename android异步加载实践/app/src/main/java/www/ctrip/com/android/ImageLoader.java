package www.ctrip.com.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/10/27 下午8:04
 * 修改备注：
 */
public class ImageLoader {
    private ImageView mImageView;
    private String mUrl;
    //创建Cache
    private LruCache<String,Bitmap> mCaches;
    private ListView mListView;
    private Set<NewsAsyncTask> mTask;

    public ImageLoader(ListView listView){
        mListView = listView;
        mTask = new HashSet<>();

        //获取当前可以利用最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/4;
        //初始化
        mCaches = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用，告诉每次缓存图片的大小
                return value.getByteCount();
            }
        };
    }

    /**
     * 存入图片进入缓存
     * @param url
     * @param bitmap
     */
    public void addBitmapToCache(String url,Bitmap bitmap){
        if (getBitmapFromCache(url) == null){
            mCaches.put(url,bitmap);
        }
    }

    /**
     * 从缓存获取图片
     * @param url
     * @return
     */
    public Bitmap getBitmapFromCache(String url){
        return mCaches.get(url);
    }

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
     * 使用Async实现图片的异步加载
     * @param imageView
     * @param url
     */
    public void showImageByAsyncTask(ImageView imageView, String url){
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null){
            //网络请求
            //new NewsAsyncTask(imageView,url).execute(url);
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else {
            //使用缓存
            imageView.setImageBitmap(bitmap);
        }
    }
    //取消任务
    public void cancelAllTasks(){
        if (mTask != null){
            for (NewsAsyncTask task:mTask){
                task.cancel(false);
            }
        }
    }
    //开始任务
    public void loadImages(int start,int end){
        for (int i = start;i < end; i++){
            String url = NewsAdapter.URLS[i];
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null){
                //网络请求
                //new NewsAsyncTask(url).execute(url);
                NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                mTask.add(task);
            }else {
                //使用缓存
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private class NewsAsyncTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView mImageView;
        private String mUrl;
//        public NewsAsyncTask(ImageView imageView,String url){
//            mImageView = imageView;
//            mUrl = url;
//        }

        public NewsAsyncTask(String url){
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            //从网络请求获取图片
            Bitmap bitmap = getBitmapFromURL(url);
            if (bitmap != null){
                //把下载图片存入缓存
                addBitmapToCache(url,bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
//            if (mImageView.getTag().equals(mUrl)){
//                mImageView.setImageBitmap(bitmap);
//            }
        }
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
}
