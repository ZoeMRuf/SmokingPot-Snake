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
    private static Pane pane;
    private static Scene scene;
    private static GameOver gameOverScene; // To show "GameOver"


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Pane = Leinwand = Hintergrundfenster
        pane = new Pane();
        scene = new Scene(pane,GameSize,GameSize);
        scene.setFill(Color.rgb(0,0,0));

        snake = new Snake();
        pane.getChildren().addAll(Snake.getSnakeLength());



        // Damit es auch etwas anzeigt
        primaryStage.setTitle("S N A K E");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Hallo wie geht es euch

    }
}
