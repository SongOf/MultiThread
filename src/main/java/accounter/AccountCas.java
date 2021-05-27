package accounter;

import java.util.concurrent.CountDownLatch;

/**
 * @author SongOf
 * @ClassName AccountCas
 * @Description
 * @Date 2021/5/27 23:41
 * @Version 1.0
 */
public class AccountCas {
    //关键点一 volatile
    private volatile static int count = 0;

    public static int getCount() {
        return count;
    }

    public static void request() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int expectCount;
        while (!compareAndSwap(expectCount = getCount(), expectCount + 1)){};
    }
    //关键点二 synchronized
    public static synchronized boolean compareAndSwap(int expectCount, int newCount) {
        if(getCount() == expectCount) {
            count = newCount;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        request();
                    }
                    countDownLatch.countDown();
                }
            });
            t.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("The result is " + count);
        System.out.println("The cost is " + (endTime - startTime));
    }
}
