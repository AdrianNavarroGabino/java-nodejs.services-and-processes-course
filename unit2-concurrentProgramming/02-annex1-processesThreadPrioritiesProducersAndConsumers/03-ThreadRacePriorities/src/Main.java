// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        ThreadCount threadA = new ThreadCount("A");
        ThreadCount threadB = new ThreadCount("B");
        ThreadCount threadC = new ThreadCount("C");

        threadA.setPriority(Thread.MAX_PRIORITY);
        threadB.setPriority(Thread.NORM_PRIORITY);
        threadC.setPriority(Thread.MIN_PRIORITY);

        threadA.start();
        threadB.start();
        threadC.start();

        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(threadA.isAlive() || threadB.isAlive() || threadC.isAlive());
    }
}
