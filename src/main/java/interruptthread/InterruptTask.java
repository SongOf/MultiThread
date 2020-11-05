package interruptthread;
/*
* Thread.sleep()方法由于中断而抛出异常，此时，它会清除中断标记，如果不加处理，下次循环就无法捕获这个中断，故在异常处理中，再次设置中断标志位
*
* */
public class InterruptTask implements Runnable {
    @Override
    public void run() {
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted");
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted When Sleep");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}
