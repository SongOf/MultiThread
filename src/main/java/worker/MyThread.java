package worker;

import java.util.Date;

public class MyThread extends Thread {
    private final String name;
    public MyThread(String name){
        this.name=name;
    }

    @Override
    public void run() {
        System.out.println("继承Thread的任务"+name+Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println("继承Thread的任务"+name+Thread.currentThread().getName() + " Start. Time = " + new Date());
    }
    private void processCommand(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
