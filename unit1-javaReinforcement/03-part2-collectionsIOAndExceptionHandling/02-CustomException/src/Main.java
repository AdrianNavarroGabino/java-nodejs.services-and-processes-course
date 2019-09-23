// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            positiveSubtract(5, 6);
        }
        catch(NegativeSubtractException e)
        {
            System.err.println(e.getMessage());
        }
    }

    static int positiveSubtract(int n1, int n2) throws NegativeSubtractException
    {
        if(n1 - n2 < 0)
        {
            throw new NegativeSubtractException(n1, n2);
        }
        else
        {
            return n1 - n2;
        }
    }
}
