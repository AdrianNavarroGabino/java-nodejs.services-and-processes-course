// Adrián Navarro Gabino

package sample;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private Set<KeyCode> activeKeys;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);

        Canvas canvas = new Canvas(400, 200);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Sprite img;
        Image lambda;
        try {
            img = new Sprite(200, 100, 50, 50, new Image(Files.newInputStream(Paths.get("image.png"))));
            lambda = new Image(Files.newInputStream(Paths.get("lambda.png")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        activeKeys = new HashSet<>();
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));

        scene.setOnMouseClicked(e ->
        {
            img.setX((int)e.getX() - 25);
            img.setY((int)e.getY() - 25);
        });

        Random rand = new Random(System.nanoTime());
        List<Sprite> lambdas = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            lambdas.add(new Sprite(rand.nextInt(350), rand.nextInt(150), 50, 50, lambda));
        }

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if(activeKeys.contains(KeyCode.RIGHT))
                    img.setX(img.getX()+1);
                if(activeKeys.contains(KeyCode.LEFT))
                    img.setX(img.getX()-1);
                if(activeKeys.contains(KeyCode.UP))
                    img.setY(img.getY()-1);
                if(activeKeys.contains(KeyCode.DOWN))
                    img.setY(img.getY()+1);

                // collision detection
                Iterator<Sprite> lambdasIter = lambdas.iterator();
                while (lambdasIter.hasNext())
                {
                    Sprite lambda = lambdasIter.next();
                    if (lambda.intersects(img))
                    {
                        lambdasIter.remove();
                    }
                }

                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 400, 200);
                for(Sprite lambda: lambdas)
                    lambda.draw(gc);
                img.draw(gc);

                if (lambdas.isEmpty())
                    this.stop();
            }
        }.start();

        primaryStage.setTitle("2D Game Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
