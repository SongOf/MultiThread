package accounter;
//volatile不能保证线程安全的测试 添加synchronized可以保证线程安全
public class AccountingVol implements Runnable {
    private static AccountingVol instance=new AccountingVol();
    private static volatile int i=0;
    public synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
