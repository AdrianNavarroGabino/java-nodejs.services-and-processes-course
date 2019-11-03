// Adrián Navarro Gabino

public class BankThreadSpend extends Thread
{
    private BankAccount account;

    public BankThreadSpend(BankAccount account)
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
                account.takeOutMoney(100);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
        }
    }
}
