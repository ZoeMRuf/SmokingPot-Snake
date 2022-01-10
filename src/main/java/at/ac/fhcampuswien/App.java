package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.util.Collection;

public class App extends Application {

    //Alle Variablen die wir später brauchen
    public static Snake snake;
    public static Food food;
    public static GameOver gameOver;
    public static int GameSize = 480;

    //GUI Variablen
    public static Pane root;
    private static Scene scene;
    private static GameOver gameOverScene; // To show "GameOver"
    private static GameLoop gameLoop;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Objekts that need to be created
        root = new Pane();
        scene = new Scene(root, GameSize, GameSize);
        scene.setFill(Color.rgb(0, 0, 0));

        gameLoop = new GameLoop();
        snake = new Snake();

        //to display our game
        primaryStage.setTitle("S N A K E");
        primaryStage.setScene(scene);
        primaryStage.show();

        App.root.getChildren().addAll(App.snake.getSnakeLengthArr());

    }
}
