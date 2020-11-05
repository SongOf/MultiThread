package stopthread;

public class WriteObjectThread implements Runnable {
    private User user;
    private volatile boolean stopTask=false;
    public WriteObjectThread(User u){
        user=u;
    }
    public void stopTask(){
        stopTask=true;
    }
    @Override
    public void run() {
        while (true){
            if(stopTask){
                System.out.println("exit by stop me");
                break;
            }
            synchronized (user){
                int v= (int) (System.currentTimeMillis()/1000);
                user.setId(v);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                user.setName(String.valueOf(v));
            }
            Thread.yield();
        }
    }
}
