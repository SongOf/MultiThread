package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//读取多个文件或者调用多个api可以用CountDownLatch来等待所以线程都结束 也可以进一步使用future甚至更好的CompletableFuture
public class CountDownLatchDemo {
    private static final int threadCount=6;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch=new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            exec.execute(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        exec.shutdown();
        System.out.println("finish");
    }
}
