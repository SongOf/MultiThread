package threadpool;

import worker.MyCallable;
import worker.MyRunnable;
import worker.MyThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    private static final int CORE_POOL_SIZE=2;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 3; i++) {
            Runnable work=new MyRunnable("线程"+i);
            executor.execute(work);
        }
        for (int i = 0; i < 3; i++) {
            Thread thread=new MyThread("线程"+i);
            executor.execute(thread);
        }
        List<Future<Long>> list=new ArrayList<Future<Long>>();
        for (int i = 0; i < 10; i++) {
            Callable<Long> callable=new MyCallable("线程"+i);
            Future<Long> submit= executor.submit(callable);
            list.add(submit);
        }
        int sum=0;
        for (Future<Long> future:list){
            try {
                sum+=future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);

        // executor不接受新的threads
        executor.shutdown();
        // 等待所有threads结束
        try {
            executor.awaitTermination(5000,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }
}
