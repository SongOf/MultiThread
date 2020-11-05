package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    private AtomicInteger count=new AtomicInteger();
    public void increment(){
        count.incrementAndGet();
    }
    public int getCount(){
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        atomic.AtomicIntegerTest atomicIntegerTest=new atomic.AtomicIntegerTest();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                atomicIntegerTest.increment();
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(atomicIntegerTest.getCount());
    }
}
