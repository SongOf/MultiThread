package accounter;
//volatile不能保证线程安全的测试 添加synchronized可以保证线程安全
public class AccountSync implements Runnable {

//    private volatile static int i = 0;
    private static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        increase();
    }
}
