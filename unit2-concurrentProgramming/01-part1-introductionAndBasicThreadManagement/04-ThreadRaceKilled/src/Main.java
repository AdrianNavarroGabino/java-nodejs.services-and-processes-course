public class Main
{
    public static void main(String[] args)
    {
        ThreadCount threadA = new ThreadCount("A");
        ThreadCount threadB = new ThreadCount("B");
        ThreadCount threadC = new ThreadCount("C");

        threadA.start();
        threadB.start();
        threadC.start();

        do {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (threadA.getNumber() >= 700) {
                threadA.setFinish(true);
                threadB.setFinish(true);
                threadC.setFinish(true);
            }
        } while(threadA.isAlive() || threadB.isAlive() || threadC.isAlive());
    }
}
