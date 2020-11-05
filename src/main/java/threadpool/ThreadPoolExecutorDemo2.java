package threadpool;

import java.time.Instant;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo2 {
    private static final int CORE_POOL_SIZE=5;
    private static final int MAX_POOL_SIZE=10;
    private static final int QUEUE_CAPACITY=100;
    private static final Long KEEP_ALIVE_TIME=1L;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 20; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CurrentThread Name："+Thread.currentThread().getName()+"date:"+ Instant.now());
            });
        }
        executor.shutdown();
        executor.awaitTermination(10,TimeUnit.SECONDS);
        System.out.println("Finished all threads");
    }
}
