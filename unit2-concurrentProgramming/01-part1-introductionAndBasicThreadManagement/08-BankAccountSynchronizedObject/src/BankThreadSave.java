// Adrián Navarro Gabino

public class BankThreadSave extends Thread
{
    private BankAccount account;

    public BankThreadSave(BankAccount account)
    {
        this.account = account;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < 5; i++)
        {
            synchronized (account)
            {
                account.addMoney(100);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
        }
    }
}
