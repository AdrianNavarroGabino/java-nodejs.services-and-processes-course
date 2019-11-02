// Adrián Navarro Gabino

public class BankAccount
{
    private int balance;

    public BankAccount(int balance)
    {
        this.balance = balance;
    }

    public void addMoney(int money)
    {
        balance += money;
        System.out.println("Balance: " + balance);
    }

    public void takeOutMoney(int money)
    {
        balance -= money;
        System.out.println("Balance: " + balance);
    }

    public int getBalance() { return balance; }
}
