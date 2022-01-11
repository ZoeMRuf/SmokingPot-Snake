package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;


public class App extends Application {

    //Alle Variablen die wir später brauchen
    public static Snake snake;
    public static GameOver gameOver;
    public static int GameSize = 480;
    public static int score = 0;

    //GUI Variablen
    public static Pane root;
    public static Scene sceneGame, sceneMenu;
    private static GameOver gameOverScene; // To show "GameOver"
    private static GameLoop gameLoop;
    public static Text scoreText;
    private static Group group;

    public static void main(String[] args) {
        launch(args);
    }


    //we need throws Exception - otherwise setOnAction wouldn't work
    @Override
    public void start(Stage primaryStage) throws Exception{

        //all for our Game Scene
        //Objekts that need to be created
        VBox layoutGame = new VBox();
        root = new Pane();

        Text scoreText = new Text();
        scoreText.setFill(Color.rgb(255, 255, 255));
        scoreText.setFont(Font.font("Courier New", Snake.scale));
        scoreText.setTextOrigin(VPos.BOTTOM);
        scoreText.setTranslateX(0);
        scoreText.setText("Score: " + score);
        scoreText.setLayoutX(Snake.scale);
        scoreText.setTranslateY(GameSize);

        root.getChildren().add(scoreText);

        layoutGame.getChildren().addAll(root);
        layoutGame.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        sceneGame = new Scene(layoutGame, GameSize, GameSize);

        snake = new Snake();
        gameLoop = new GameLoop();

        //all for our Menu
        Label label = new Label("Welcome to our Version of Snake");
        Button startGame = new Button("Start the Game");
        Button showHighScore = new Button("Show High-score");

        startGame.setOnAction(e -> primaryStage.setScene(sceneGame));

        VBox layoutMenu = new VBox();
        layoutMenu.getChildren().addAll(label, startGame,showHighScore);
        sceneMenu = new Scene(layoutMenu, GameSize - Snake.scale * 2, GameSize - Snake.scale * 2);

        //to display our game
        primaryStage.setTitle("S N A K E");
        primaryStage.getIcons().add(new Image(getClass().getResource("/OnlyTestApple.jpg").toExternalForm()));
        primaryStage.setScene(sceneMenu);
        primaryStage.show();

        App.root.getChildren().addAll(App.snake.getSnakeLengthArr());
    }
}
