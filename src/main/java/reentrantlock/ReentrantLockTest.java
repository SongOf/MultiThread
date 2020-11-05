package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//可重入锁
public class ReentrantLockTest implements Runnable {
    private static final Lock lock=new ReentrantLock();
    private static int i=0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获取到了锁");
            try{
                i++;
            } finally {
                System.out.println(Thread.currentThread().getName()+"释放了锁");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest task=new ReentrantLockTest();
        Thread t1=new Thread(task,"t1线程");
        Thread t2=new Thread(task,"t2线程");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
//        for (int i = 0; i < 20; i++) {
//            new Thread(task,"线程"+i).start();
//        }
    }
}
