// Adrián Navarro Gabino

public class NegativeSubtractException extends Exception
{
    public NegativeSubtractException(int n1, int n2)
    {
        super("NegativeSubstractException: '" + n1 + " - " + n2 +
                "' result is negative");
    }
}
