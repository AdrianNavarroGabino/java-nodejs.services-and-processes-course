// Adrián Navarro Gabino

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main
{
    public static CompletableFuture<Integer> wordInText(String wordToSearch, String file) {
        return CompletableFuture.supplyAsync(() -> {
            try (Stream<String> lines = Files.lines(Paths.get(file))) {
                return lines.mapToInt(line -> {
                            int count = 0;
                            int i = -1;
                            while((i = line.indexOf(wordToSearch, i + 1)) != -1)
                            {
                                count++;
                            }
                            return count;
                        })
                        .sum();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    public static void main(String[] args) {
        String search = "alcohol";

        CompletableFuture<Object> anyTask = CompletableFuture.anyOf(
                wordInText(search, "text1.txt"),
                wordInText(search, "text2.txt"),
                wordInText(search, "text3.txt")
        );

        anyTask.thenAccept((times) -> System.out.println(search + " appears " + times + " times"));

        while (!anyTask.isDone()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {}
        }
    }
}