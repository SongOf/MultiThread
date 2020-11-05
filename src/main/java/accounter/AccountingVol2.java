package accounter;

public class AccountingVol2 implements Runnable {
    private static volatile int i=0;
    public static synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new AccountingVol2());
        Thread t2=new Thread(new AccountingVol2());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
