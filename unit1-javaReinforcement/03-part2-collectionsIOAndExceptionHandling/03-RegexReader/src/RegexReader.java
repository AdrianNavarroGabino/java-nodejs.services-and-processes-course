// Adrián Navarro Gabino

import java.io.*;

public class RegexReader extends BufferedReader
{
    private String regex;

    public RegexReader(Reader reader, String regex)
    {
        super(reader);
        this.regex = regex;
    }

    @Override
    public String readLine() throws IOException
    {
        String result;

        do
        {
            result = super.readLine();
        }while(result != null && !result.matches(regex));

        return result;
    }
}
