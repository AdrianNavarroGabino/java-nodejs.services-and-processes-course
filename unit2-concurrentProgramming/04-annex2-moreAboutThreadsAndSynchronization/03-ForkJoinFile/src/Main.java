// Adrián Navarro Gabino

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> lines = new ArrayList<>();
        String[] lineArray;
        BufferedReader br = null;
        PrintWriter pw = null;

        try
        {
            br = new BufferedReader(new FileReader("textFile.txt"));
            String line;
            while ((line = br.readLine()) != null)
            {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { br.close(); } catch (Exception e) {}
        }

        lineArray = new String[lines.size()];
        lineArray = lines.toArray(lineArray);

        ChangeJavaWord cjw = new ChangeJavaWord(lineArray, 0, lineArray.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(cjw);

        do
        {
            try { Thread.sleep(100); } catch (Exception e) { }
        } while (!cjw.isDone());

        try
        {
            pw = new PrintWriter("textFile2.txt");
            for (int i = 0; i < lineArray.length; i++)
            {
                pw.println(lineArray[i]);
            }

            System.out.println("Text changed");
        } catch (Exception e) {
        } finally {
            pw.close();
        }

        pool.shutdown();

    }
}
