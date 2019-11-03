// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        BankAccount account = new BankAccount(100);

        Thread[] operations = new Thread[50];

        for(int i = 0; i < operations.length / 2; i++)
        {
            operations[i] = new BankThreadSave(account);
        }
        for(int i = operations.length / 2; i < operations.length; i++)
        {
            operations[i] = new BankThreadSpend(account);
        }

        for(int i = 0; i < operations.length; i++)
        {
            operations[i].start();
        }
    }
}
