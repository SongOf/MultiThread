package stopthread;

public class StopThreadOnSafe {
    private User user;
    StopThreadOnSafe(){
        user=new User();
    }

    public User getUser() {
        return user;
    }

    public static void main(String[] args) throws InterruptedException {
        StopThreadOnSafe stopThreadOnSafe=new StopThreadOnSafe();
        new Thread(new ReadObjectThread(stopThreadOnSafe.getUser())).start();
        while (true){
            WriteObjectThread writeTask=new WriteObjectThread(stopThreadOnSafe.getUser());
            Thread t=new Thread(writeTask);
            t.start();
            Thread.sleep(150);
            writeTask.stopTask();
        }
    }
}
