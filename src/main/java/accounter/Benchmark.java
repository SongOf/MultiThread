package accounter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author SongOf
 * @ClassName BenchMark
 * @Description cas、synchronized、LongAdder三种方法 评测
 * @Date 2021/5/29 11:22
 * @Version 1.0
 */
public class Benchmark {
    private static Long n = 0L;
    public static void main(String[] args) {
        testCore(1, 10000000);
        testCore(10, 10000000);
        testCore(20, 10000000);
        testCore(40, 10000000);
        testCore(80, 10000000);
    }

    private static void testCore(int threadSize, int size) {
        try {
            long start = System.currentTimeMillis();
            testAtomicLong(threadSize, size);
            System.out.println("AtomicLong elapse: " + (System.currentTimeMillis() - start) + "ms");

            start = System.currentTimeMillis();
            testLongAdder(threadSize, size);
            System.out.println("LongAdder elapse: " + (System.currentTimeMillis() - start));

            start = System.currentTimeMillis();
            testSync(threadSize, size);
            System.out.println("Synchronized elapse: " + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void testAtomicLong(int threadSize, int size) throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadSize; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < size; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }
        for(Thread thread : list) {
            thread.start();
        }
        for(Thread thread : list) {
            thread.join();
        }
    }

    private static void testLongAdder(int threadSize, int size) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadSize; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < size; j++) {
                    longAdder.increment();
                }
            }));
        }
        for(Thread thread : list) {
            thread.start();
        }
        for(Thread thread : list) {
            thread.join();
        }
    }

    private static void testSync(int threadSize, int size) throws InterruptedException {

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadSize; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < size; j++) {
                    synchronized (Object.class) {
                        n++;
                    }
                }
            }));
        }
        for(Thread thread : list) {
            thread.start();
        }
        for(Thread thread : list) {
            thread.join();
        }
    }
}
