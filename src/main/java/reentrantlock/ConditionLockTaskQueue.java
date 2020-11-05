package reentrantlock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLockTaskQueue {
    private Queue<String> queue=new LinkedList<>();
    private final int MAX=6;
    private Lock lock=new ReentrantLock();
    private Condition producer=lock.newCondition();
    private Condition consumer=lock.newCondition();

    public boolean addTask(String taskName){
        try {
            lock.lock();
            while (queue.size()>=MAX){
                System.out.println("队列已满");
                producer.await();
            }
            System.out.println("Producer"+Thread.currentThread().getName()+"task"+taskName);
            queue.add(taskName);
            consumer.signalAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return true;
    }

    public String getTask(){
        String s="";
        try {
            lock.lock();
            while (queue.isEmpty()){
                System.out.println("队列为空");
                consumer.await();
            }
            s=queue.remove();
            System.out.println("Consumer"+Thread.currentThread().getName()+"task"+s);
            producer.signalAll();
            return s;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return s;
    }

    public static void main(String[] args) {
        ConditionLockTaskQueue taskQueue=new ConditionLockTaskQueue();
        new Thread(()->{
            int i=0;
            while (true){
                taskQueue.addTask(""+i);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            int i=0;
            while (true){
                taskQueue.addTask(""+i);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while (true){
                taskQueue.getTask();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
