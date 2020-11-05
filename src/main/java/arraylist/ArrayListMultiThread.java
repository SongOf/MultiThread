package arraylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayListMultiThread {
    private static List<Integer> a1=new ArrayList<>();
    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                synchronized (a1){
                    a1.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new AddThread());
        Thread t2=new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a1.size());
    }
}
