package threadpool;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo3 {
    public static class Task implements Runnable{
        public String name;
        public Task(String name){
            this.name=name;
        }
        @Override
        public void run() {
            System.out.println("正在执行"+":Thread ID:"+Thread.currentThread().getId()+",Task Name="+name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec=new ThreadPoolExecutor(5,5,0L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行"+((Task)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成"+((Task)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for (int i = 0; i < 5; i++) {
            Task myTask=new Task("TASK-GEYM-"+i);
            exec.execute(myTask);
            Thread.sleep(1000);
        }
        exec.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
