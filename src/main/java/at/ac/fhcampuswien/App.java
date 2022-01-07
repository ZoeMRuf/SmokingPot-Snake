package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class App extends Application {

    //Alle Variablen die wir später brauchen
    public static Snake snake;
    public static Food food;
    public static GameOver gameOver;
    public static int GameSize = 480;


    //GUI Variablen
    private static Pane root;
    private static Scene scene;
    private static GameOver gameOverScene; // To show "GameOver"


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Pane = Leinwand = Hintergrundfenster
        root = new Pane();
        scene = new Scene(root, GameSize, GameSize);
        scene.setFill(Color.rgb(0, 0, 0));

        snake = new Snake();
        root.getChildren().addAll(snake.getSnakeLength());
        /*
        Snake.nowSnakeSize++;
        snake.plusSnakeSize();
        root.getChildren().addAll(snake.getSnakeLength());

         */


        // Damit es auch etwas anzeigt
        primaryStage.setTitle("S N A K E");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
