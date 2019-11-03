// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        ThreadCount threadA = new ThreadCount("A", Thread.MAX_PRIORITY);
        ThreadCount threadB = new ThreadCount("B", Thread.NORM_PRIORITY);
        ThreadCount threadC = new ThreadCount("C", Thread.MIN_PRIORITY);

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
