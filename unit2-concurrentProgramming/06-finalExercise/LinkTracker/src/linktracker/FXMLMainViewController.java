// Adrián Navarro Gabino

package linktracker;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import linktracker.model.WebPage;
import linktracker.utils.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>Main View Controller</h1>
 * Manages the main view.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class FXMLMainViewController {
    @FXML
    private Label totalPagesLbl;

    @FXML
    private Label processedLbl;

    @FXML
    private Label totalLinkLbl;

    @FXML
    private ListView<WebPage> webPagesList;

    @FXML
    private ListView<String> linksList;

    private List<WebPage> webPages;
    private List<String> links;
    private List<Future> futures;
    private ThreadPoolExecutor executor;
    private Future<WebPage> future;
    private ScheduledService<Boolean> scheduledService;
    private AtomicInteger processedPages;
    private AtomicInteger totalLinks;

    @FXML
    private void initialize()
    {
        webPages = new ArrayList<>();
        links = new ArrayList<>();
        futures = new ArrayList<>();
    }

    @FXML
    private void loadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                webPages = FileUtils.loadPages(Path.of(file.getPath()));
                MessageUtils.showMessage(
                        "File loaded",
                        webPages.size() + " pages found");
                totalPagesLbl.setText(String.valueOf(webPages.size()));
            }
            catch(Exception e)
            {
                MessageUtils.showError("Read error",
                        "A read error has occurred");
            }
        }
    }

    @FXML
    private void start(ActionEvent event) {
        clearAux();

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
                if(scheduledService.getValue())
                {
                    FillWebPagesList();
                    scheduledService.cancel();
                }
            });
            scheduledService.start();
        }
    }

    @FXML
    private void ShowLinks(MouseEvent event) {
        WebPage wp = webPagesList.getSelectionModel().getSelectedItem();
        ObservableList<String> links = FXCollections.observableArrayList (
                wp.getLinks());
        linksList.setItems(links);
    }

    @FXML
    private void clear(ActionEvent event) {
        totalLinkLbl.setText("0");
        totalPagesLbl.setText("0");
        processedLbl.setText("0");
        webPages = new ArrayList<>();
        clearAux();
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Receives the WebPage object to process and returns it again when the
     * urls have been loaded.
     * @param webPage Web page object
     * @return Web page object
     */
    public Callable<WebPage> getLinks(WebPage webPage) {
        return () -> {
            List<String> linksAux = LinkReader.getLinks(webPage.getUrl());
            links.addAll(linksAux);
            webPage.setLinks(linksAux);
            processedPages.incrementAndGet();
            totalLinks.set(links.size());
            return webPage;
        };
    }

    private void FillWebPagesList()
    {
        ObservableList<WebPage> pages = FXCollections.observableArrayList (
                webPages);
        webPagesList.setItems(pages);
    }

    private void clearAux()
    {
        processedPages = new AtomicInteger(0);
        totalLinks = new AtomicInteger(0);
        links = new ArrayList<>();
        webPagesList.setItems(null);
        linksList.setItems(null);
    }
}