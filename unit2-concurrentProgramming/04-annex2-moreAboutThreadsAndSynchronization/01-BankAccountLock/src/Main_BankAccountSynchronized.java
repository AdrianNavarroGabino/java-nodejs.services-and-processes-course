

public class Main_BankAccountSynchronized
{
    public static void main(String[] args) 
    {
        // Create the bank account
        BankAccount ba = new BankAccount(500);
        
        // Create the threads
        Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length / 2; i++)
            threads[i] = new BankThreadSave(ba);
        for (int i = threads.length / 2; i < threads.length; i++)
            threads[i] = new BankThreadSpend(ba);
        
        // Start the threads
        for (int i = 0; i < threads.length; i++)
            threads[i].start();
        
    }
    
}
