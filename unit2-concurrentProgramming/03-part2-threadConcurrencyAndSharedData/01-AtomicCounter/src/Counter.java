// Adrián Navarro Gabino

import java.util.concurrent.atomic.AtomicInteger;

public class Counter
{
    AtomicInteger value;

    public Counter(int value)
    {
        this.value = new AtomicInteger(value);
    }

    public void increment()
    {
        value.incrementAndGet();
    }

    public void decrement()
    {
        value.decrementAndGet();
    }

    public int getValue()
    {
        return value.get();
    }
}
