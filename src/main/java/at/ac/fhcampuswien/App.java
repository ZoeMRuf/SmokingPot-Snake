package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.awt.*;
import java.util.Collection;

public class App extends Application {

    //Alle Variablen die wir später brauchen
    public static Snake snake;
    public static GameOver gameOver;
    public static int GameSize = 480;
    public static int score = 0;

    //GUI Variablen
    public static Pane root;
    public static Scene scene;
    private static GameOver gameOverScene; // To show "GameOver"
    private static GameLoop gameLoop;
    private static Text scoreText;
    private static Group group;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Objekts that need to be created
        root = new Pane();

        Text text = new Text();
        text.setText("Score: " + score);
        text.setLayoutX(20);
        text.setLayoutY(GameSize - 20);

        /*
        scoreText = new Text();
        scoreText.setX(20);
        scoreText.setY(GameSize - 20);
        scoreText.setFont(Font.font ("Verdana", snake.gridSize));
        scoreText.setFill(Color.rgb(255,255,255));
         */

        root.getChildren().add(text);

        scene = new Scene(root, GameSize, GameSize);

        scene.setFill(Color.rgb(0, 0, 0));



        snake = new Snake();
        gameLoop = new GameLoop();

        //to display our game
        primaryStage.setTitle("S N A K E");
        primaryStage.setScene(scene);
        primaryStage.show();

        App.root.getChildren().addAll(App.snake.getSnakeLengthArr());
    }
}
