// Adrián Navarro Gabino

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyData
{
    int value;
    ReentrantReadWriteLock lock;

    public MyData(int value)
    {
        this.value = value;
        lock = new ReentrantReadWriteLock();
    }
    public int getValue()
    {
        lock.readLock().lock();
        try { Thread.sleep(2000); } catch (Exception e) {}
        System.out.println("Thread #" + Thread.currentThread().getId() +
                " reads value " + value);
        int v = value;
        lock.readLock().unlock();
        return v;
    }
    public void setValue(int value)
    {
        lock.writeLock().lock();
        try { Thread.sleep(2000); } catch (Exception e) {}
        System.out.println("Thread #" + Thread.currentThread().getId() +
                " sets value to " + (this.value + value));
        this.value += value;
        lock.writeLock().unlock();
    }
}
