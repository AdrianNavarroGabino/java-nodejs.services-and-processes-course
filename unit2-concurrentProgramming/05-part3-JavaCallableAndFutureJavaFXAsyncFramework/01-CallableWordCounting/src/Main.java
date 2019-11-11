// Adrián Navarro Gabino

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Main
{
    public static Callable<Integer> wordInText(String wordToSearch, String file) {
        return () -> {
            try (Stream<String> lines = Files.lines(Paths.get(file))) {
                return lines
                        .mapToInt(line -> {
                            int count = 0;
                            int index = -1;
                            while ((index = line.indexOf(wordToSearch, index + 1)) != -1) {
                                count++;
                            }
                            return count;
                        })
                        .sum();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        };
    }

    public static void main(String[] args)
    {
        String search = "alcohol";

        ExecutorService executor = Executors.newWorkStealingPool();
        List<Future<Integer>> futures;
        try {
            futures = executor.invokeAll(Arrays.asList(
                    wordInText(search, "text1.txt"),
                    wordInText(search, "text2.txt"),
                    wordInText(search, "text3.txt")
            ));
            executor.shutdown();
            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new IllegalStateException(e);
                }
            });
        } catch (InterruptedException ex) {
        }
    }
}
