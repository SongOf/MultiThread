package stopthread;

public class ReadObjectThread implements Runnable {
    private User user;
    public ReadObjectThread(User u){
        user=u;
    }

    @Override
    public void run() {
        while (true){
            synchronized (user){
                if(user.getId()!=Integer.parseInt(user.getName())){
                    System.out.println(user.toString());
                }
            }
            Thread.yield();
        }
    }
}
