package accounter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author SongOf
 * @ClassName AccountAtomic
 * @Description
 * @Date 2021/5/29 11:41
 * @Version 1.0
 */
public class AccountAtomic implements Runnable {

    private static AtomicLong i = new AtomicLong();

    public void increment() {
        i.addAndGet(1L);
    }

    public Long get() {
        return i.get();
    }

    @Override
    public void run() {
        increment();
    }
}
