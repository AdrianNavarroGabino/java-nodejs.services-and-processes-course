// Adrián Navarro Gabino

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class ChangeJavaWord extends RecursiveAction
{
    static final int MAX_LINES = 10;

    String[] lines;
    int firstLine;
    int lastLine;

    public ChangeJavaWord(String[] lines, int firstLine, int lastLine)
    {
        this.lines = lines;
        this.firstLine = firstLine;
        this.lastLine = lastLine;
    }

    @Override
    protected void compute()
    {
        if (lastLine - firstLine <= MAX_LINES)
        {
            replace();
        } else {
            int total = (lastLine - 1) / MAX_LINES + 1;
            ArrayList<ChangeJavaWord> subtasks = new ArrayList<>();
            for (int i = 0; i < total; i++)
            {
                ChangeJavaWord cjw = new ChangeJavaWord(
                        lines, i * 10, i * 10 + 10);
                subtasks.add(cjw);
            }
            invokeAll(subtasks);
        }
    }

    private void replace()
    {
        for (int i = firstLine; i < lastLine; i++)
        {
            if (i < lines.length)
                lines[i] = lines[i].replaceAll("java", "Java");
            else
                break;
        }
    }
}