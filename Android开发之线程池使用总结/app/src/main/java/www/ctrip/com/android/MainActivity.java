package www.ctrip.com.android;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * ThreadPoolExecutor的使用
     * ThreadPoolExecutor遵循如下规则：
     * 1.execute一个线程之后，如果线程池中的线程数未达到核心线程数，则会立马启用一个核心线程去执行
     * <p/>
     * 2.execute一个线程之后，如果线程池中的线程数已经达到核心线程数，且workQueue未满，则将新线程放入workQueue中等待执行
     * <p/>
     * 3.execute一个线程之后，如果线程池中的线程数已经达到核心线程数但未超过非核心线程数，且workQueue已满，则开启一个非核心线程来执行任务
     * <p/>
     * 4.execute一个线程之后，如果线程池中的线程数已经超过非核心线程数，则拒绝执行该任务
     *
     * @param view
     */
    public void threadPoolExecutor(View view) {
        /**
         * 核心线程数为3,最大线程数30,非核心线程数保持时间1秒,大小为6的缓存池。
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 30, 1, TimeUnit.SECONDS, new
                LinkedBlockingDeque<Runnable>(6));
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Log.d("google_lenve_fb", "run: " + Thread.currentThread().getName() + "-----" +
                            finalI);
                }
            };
            poolExecutor.execute(runnable);
            //poolExecutor.submit(runnable);
        }
    }

    /**
     * FixedThreadPool是一个核心线程数量固定的线程池,FixedThreadPool中没有非核心线程，所有的线程都是核心线程，且线程的超时时间为0，
     * 说明核心线程即使在没有任务可执行的时候也不会被销毁（这样可让FixedThreadPool更快速的响应请求），
     * 最后的线程队列是一个LinkedBlockingQueue，但是LinkedBlockingQueue却没有参数，这说明线程队列的大小为Integer
     * .MAX_VALUE（2的31次方减1）
     *
     * @param view
     */
    public void fixedThreadPool(View view) {
        /**
         * 核心线程数为3
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                    Log.d("google_lenve_fb", "run: " + Thread.currentThread().getName() + "-----"
                            + finalI);
                }
            };
            fixedThreadPool.execute(runnable);
        }
    }

    /**
     * singleThreadExecutor和FixedThreadPool很像，不同的就是SingleThreadExecutor的核心线程数只有1
     */
    public void singleThreadPool(View view) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d("google_lenve_fb", "run: " + Thread.currentThread().getName() + "-----"
                            + finalI);
                    SystemClock.sleep(1000);
                }
            };
            singleThreadExecutor.execute(runnable);
        }
    }

    /**
     * CachedTreadPool一个最大的优势是它可以根据程序的运行情况自动来调整线程池中的线程数量.
     * CachedThreadPool中是没有核心线程的，但是它的最大线程数却为Integer.MAX_VALUE，
     * 另外，它是有线程超时机制的，超时时间为60秒.这里它使用了SynchronousQueue作为线程队列.
     * 由于最大线程数为无限大，所以每当我们添加一个新任务进来的时候，如果线程池中有空闲的线程，则由该空闲的线程执行新任务，如果没有空闲线程，则创建新线程来执行任务。
     *
     * @param view
     */
    public void cachedThreadPool(View view) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //线程执行过程中暂停两秒
                    SystemClock.sleep(2000);
                    Log.d("google_lenve_fb", "run: " + Thread.currentThread().getName() + "----"
                            + finalI);
                }
            };
            cachedThreadPool.execute(runnable);
            //每隔一秒添加新任务
            SystemClock.sleep(2000);
        }
    }

    /**
     * ScheduledThreadPool是一个具有定时定期执行任务功能的线程池
     * 它的核心线程数量是固定的（我们在构造的时候传入的），但是非核心线程是无穷大，当非核心线程闲置时，则会被立即回收。
     *
     * @param view
     */
    public void scheduledThreadPool(View view) {
        //核心线程数为3
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("google_lenve_fb", "run: ----");
            }
        };
        /**
         * 第一次延迟1秒之后，以后每次也延迟1秒执行。
         */
        scheduledExecutorService.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS);
        /**
         * 第一次延迟一秒执行,然后间隔一秒执行任务
         */
        //scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
        /**
         * 一秒以后启动任务
         */
        //scheduledExecutorService.schedule(runnable, 1, TimeUnit.SECONDS);
    }

    /**
     * 一般情况下我们使用execute来提交任务，但是有时候可能也会用到submit，使用submit的好处是submit有返回值.
     * 使用submit时我们可以通过实现Callable接口来实现异步任务。在call方法中执行异步任务，返回值即为该任务的返回值。
     * Future是返回结果，返回它的isDone属性表示异步任务执行成功！
     *
     * @param view
     */
    public void submit(View view) {
        List<Future<String>> futures = new ArrayList<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 10; i++) {
            Future<String> taskFuture = threadPoolExecutor.submit(new MyTask(i));
            //将每一个任务的执行结果保存起来
            futures.add(taskFuture);
        }
        try {
            //遍历所有任务的执行结果
            for (Future<String> future : futures) {
                Log.d("google_lenve_fb", "submit: " + future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现callable接口,则从他的call方法里异步返回执行结果
     */
    class MyTask implements Callable<String> {

        private int taskId;

        public MyTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public String call() throws Exception {
            SystemClock.sleep(1000);
            //返回每一个任务的执行结果
            return "call()方法被调用----" + Thread.currentThread().getName() + "-------" + taskId;
        }
    }

    /**
     * 自定义线程池
     * @param view
     */
    public void customThreadPool(View view) {
        /**
         * 初始化自定义线程池
         */
        final MyThreadPool myThreadPool = new MyThreadPool(3, 5, 1, TimeUnit.MINUTES, new
                LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(100);
                    Log.d("google_lenve_fb", "run: " + finalI);
                }
            };
            myThreadPool.execute(runnable);
        }
    }

    /**
     * 自定义线程池
     */
    class MyThreadPool extends ThreadPoolExecutor {

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit
                unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            Log.d("google_lenve_fb", "beforeExecute: 开始执行任务！");
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            Log.d("google_lenve_fb", "beforeExecute: 任务执行结束！");
        }

        @Override
        protected void terminated() {
            super.terminated();
            //当调用shutDown()或者shutDownNow()时会触发该方法
            Log.d("google_lenve_fb", "terminated: 线程池关闭！");
        }
    }

}
