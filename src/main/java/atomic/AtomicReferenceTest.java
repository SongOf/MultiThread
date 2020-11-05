package atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    static AtomicReference<Integer> money=new AtomicReference<Integer>();

    public static void main(String[] args) {
        money.set(19);
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                while (true){
                    Integer m=money.get();
                    if(m<20){
                        if(money.compareAndSet(m,m+20)){
                            System.out.println("余额小于20元，充值成功，余额："+money.get()+"元");
                        }
                    }else {
//                        System.out.println("无需充值");
                    }
                }
            }).start();
        }
        new Thread(){
            @Override
            public void run() {
                while (true){
                    Integer m=money.get();
                    if(m>10){
                        System.out.println("大于10元");
                        if(money.compareAndSet(m,m-10)){
                            System.out.println("成功消费10元 余额："+money.get());
                        }
                    }else {
                        System.out.println("没有足够的金额");
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
