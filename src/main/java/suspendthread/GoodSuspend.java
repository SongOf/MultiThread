package suspendthread;

public class GoodSuspend {
    public static Object u=new Object();

    public static class ChangeObjectThread extends Thread{
        public volatile boolean suspendTask=false;
        public void suspendTask(){
            suspendTask=true;
        }
        public void resumeTask(){
            suspendTask=false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while (suspendTask){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (u){
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1=new ChangeObjectThread();
        ReadObjectThread t2=new ReadObjectThread();
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.suspendTask();
        System.out.println("suspend t1 2 Sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeTask();
    }
}
