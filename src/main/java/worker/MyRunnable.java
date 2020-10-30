package worker;

import java.util.Date;

public class MyRunnable implements Runnable{
    private final String name;
    public MyRunnable(String name){
        this.name=name;
    }

    @Override
    public void run() {
        System.out.println("实现Runnable的任务"+name+Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println("实现Runnable的任务"+name+Thread.currentThread().getName() + " End. Time = " + new Date());
    }
    private void processCommand(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MyRunnable{" +
                "name='" + name + '\'' +
                '}';
    }
}
