package linktracker;

import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import linktracker.model.WebPage;
import linktracker.utils.FileUtils;
import linktracker.utils.LinkReader;
import linktracker.utils.MessageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FXMLMainViewController {
    @FXML
    private Label totalPagesLbl;

    @FXML
    private Label processedLbl;

    @FXML
    private Label totalLinkLbl;

    List<WebPage> webPages;
    List<String> links;
    List<Future> futures;
    ThreadPoolExecutor executor;
    Future<WebPage> future;
    ScheduledService<Boolean> scheduledService;
    AtomicInteger processedPages;
    AtomicInteger totalLinks;

    @FXML
    void initialize()
    {
        processedPages = new AtomicInteger(0);
        totalLinks = new AtomicInteger(0);
        webPages = new ArrayList<>();
        links = new ArrayList<>();
        futures = new ArrayList<>();
    }

    public Callable<WebPage> getLinks(WebPage webPage) {
        return () -> {
            links.addAll(LinkReader.getLinks(webPage.getUrl()));
            processedPages.incrementAndGet();
            totalLinks.incrementAndGet();
            return webPage;
        };
    }

    @FXML
    void loadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            webPages = FileUtils.loadPages(Path.of(file.getPath()));
            MessageUtils.showMessage(
                    "File loaded",
                    webPages.size() + " pages found");
            totalPagesLbl.setText(String.valueOf(webPages.size()));
        }
    }

    @FXML
    void start(ActionEvent event) {
        if(webPages.size() == 0)
        {
            MessageUtils.showError(
                    "Process error",
                    "No URL list loaded");
        }
        else
        {
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors());

            for(WebPage wp: webPages) {
                future = executor.submit(getLinks(wp));
                futures.add(future);
            }

            executor.shutdown();

            scheduledService = new ScheduledService<Boolean>() {
                @Override
                protected Task<Boolean> createTask() {
                    return new Task<Boolean>() {
                        @Override
                        protected Boolean call() throws Exception {
                            return executor.isTerminated();
                        }
                    };
                }
            };

            scheduledService.setPeriod(Duration.millis(100));
            scheduledService.setOnSucceeded(eh -> {
                processedLbl.setText(
                        String.valueOf(processedPages.get()));
                totalLinkLbl.setText(
                        String.valueOf(totalLinks.get()));
            });
            scheduledService.start();
        }
    }

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }
}