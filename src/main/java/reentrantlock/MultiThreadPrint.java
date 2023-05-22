package reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadPrint {
    private static int i;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static class PrintRun implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    condition.signal();
                    if (i < 100) {
                        i++;
                        System.out.println("Thread: " + Thread.currentThread().getName() + ", i = " + i);
                    } else {
                        break;
                    }
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintRun r = new PrintRun();
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
