package accounter;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author SongOf
 * @ClassName AccountAdder
 * @Description
 * @Date 2021/5/29 11:46
 * @Version 1.0
 */
public class AccountAdder implements Runnable {
    private static LongAdder i = new LongAdder();

    public void increment() {
        i.add(1L);
    }
    @Override
    public void run() {
        increment();
    }
}
