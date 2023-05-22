package sync;

/* 两个线程交替打印 1-100
* */
public class MultiThreadPrint {
    private static int i;

    public static class PrintRun implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (this) {
                        this.notify();
                        if (i < 100) {
                            i++;
                            System.out.println("Thread: " + Thread.currentThread().getName() + ", i = " + i);
                        } else {
                            break;
                        }
                        this.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
