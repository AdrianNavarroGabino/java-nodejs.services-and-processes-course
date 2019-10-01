// Adrián Navarro Gabino

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        if (!(new File("comments.txt")).exists()) {
            System.out.println("comments.txt not found");
            return;
        }

        List<Comment> comments = new ArrayList<>();

        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(new File("comments.txt")));
            String line = file.readLine();

            while(line != null)
            {
                String[] auxLine = line.split(";");

                Comment comment = new Comment(auxLine[0], auxLine[1],
                        auxLine[2], auxLine[3], auxLine[4]);

                comments.add(comment);

                line = file.readLine();
            }

            file.close();
        } catch (Exception e) {
            System.out.println("There were some problems: " +
                    e.getMessage());
        }

        for(Comment c: comments)
        {
            c.setEuropeMadrid();
        }

        comments.add(new Comment("paul", "Comment from Main", "08/09/2001",
                "23:23:21", "Europe/London"));
        comments.add(new Comment("jenn", "Hi everyone", "01/09/2001",
                "11:13:28", "Europe/Madrid"));
        comments.add(new Comment("harold", "Last comment", "10/10/2012",
                "14:12:11", "Europe/London"));

        comments.sort((a,b) -> (int)b.getDate().until(a.getDate(), ChronoUnit.SECONDS));

        comments.stream().forEach(System.out::println);

        PrintWriter pw = null;

        try
        {
            pw = new PrintWriter("ordered_comments.txt");

            for(Comment c: comments)
            {
                pw.println(c.getUsername() + ";" +
                        c.getComment() + ";" +
                        DateTimeFormatter.ofPattern("dd/MM/yyy;HH:mm:ss;VV")
                                .format(c.getDate()));
            }

            System.out.println("File created");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
    }
}
