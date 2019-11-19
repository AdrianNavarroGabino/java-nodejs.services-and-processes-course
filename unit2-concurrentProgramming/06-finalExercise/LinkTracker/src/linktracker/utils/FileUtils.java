// Adrián Navarro Gabino

package linktracker.utils;

import linktracker.model.WebPage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <h1>File Utils</h1>
 * Tools to use in the application.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class FileUtils
{
    /**
     * Loads every web page from a txt file.
     * @param file File path
     * @return Web page list
     */
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
