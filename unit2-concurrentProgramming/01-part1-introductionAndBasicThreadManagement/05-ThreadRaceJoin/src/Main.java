public class Main
{
    public static void main(String[] args)
    {
        Thread threadA = new ThreadCount("A");
        Thread threadB = new ThreadCount("B", threadA);
        Thread threadC = new ThreadCount("C", threadB);

        threadA.start();
        threadB.start();
        threadC.start();

        do {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(threadA.isAlive() || threadB.isAlive() || threadC.isAlive());
    }
}
