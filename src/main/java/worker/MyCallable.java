package worker;

import java.util.Date;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Long> {
    private final String name;
    public MyCallable(String name){
        this.name=name;
    }
    @Override
    public Long call() throws Exception {
        int sum=1;
        System.out.println("实现Callable的任务"+name+Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println("实现Callable的任务"+name+Thread.currentThread().getName() + " End. Time = " + new Date());
        return (long)sum;
    }
    private void processCommand(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
