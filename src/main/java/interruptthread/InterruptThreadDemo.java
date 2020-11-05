package interruptthread;

public class InterruptThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(new InterruptTask());
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
