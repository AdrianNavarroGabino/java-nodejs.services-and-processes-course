package linktracker.utils;

import linktracker.model.WebPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils
{
    public static List<WebPage> loadPages(Path file)
    {
        List<WebPage> pages = new ArrayList<>();

        try(Stream<String> lines = Files.lines(file))
        {
            lines.forEach(l -> pages.add(
                    new WebPage(l.split(";")[0], l.split(";")[1])));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return pages;
    }
}
