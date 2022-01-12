package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.geometry.Pos;
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
    public static Pane root, paneGameOver;
    public static Scene sceneGame, sceneMenu, sceneGameOver;
    public static GameLoop gameLoop;
    public static Text scoreText, gameOverText, gameOverscoreText;
    public static Button playAgain, backToMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Game Scene:
        //Objekts that need to be created
        VBox layoutGame = new VBox();
        root = new Pane();

        scoreText = new Text();
        getNewScoreOnScreen();

        //Begin GameOver Scene inside of GameScene:
        paneGameOver = new Pane();

        playAgain = new Button("Play again");
        playAgain.setOnAction(e -> {
            gameLoop.timeLine.play();
            primaryStage.setScene(sceneGame);
        });
        backToMenu = new Button("Back to Menu");
        backToMenu.setOnAction(e -> primaryStage.setScene(sceneMenu));

        gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font ("Verdana", 70));
        gameOverText.setFill(Color.rgb(252,3,3));
        gameOverscoreText = new Text("Score: " + score);
        gameOverscoreText.setFill(Color.rgb(255, 255, 255));
        gameOverscoreText.setFont(Font.font("Courier New", Snake.scale));
        // End of Game Over inside of GameScene

        layoutGame.getChildren().addAll(root, paneGameOver);
        layoutGame.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        sceneGame = new Scene(layoutGame, GameSize, GameSize);

        snake = new Snake();
        gameLoop = new GameLoop();
        gameLoop.timeLine.stop();

        //Menu scene:
        Label label = new Label("Welcome to our Version of Snake");
        Button startGame = new Button("Start the Game");
        Button showHighScore = new Button("Show High-score");

         startGame.setOnAction(event -> {
            gameLoop.timeLine.play();
            primaryStage.setScene(sceneGame);
        });
        VBox layoutMenu = new VBox();
        layoutMenu.getChildren().addAll(label, startGame,showHighScore);
        sceneMenu = new Scene(layoutMenu, GameSize, GameSize);

        //to display our game
        primaryStage.setTitle("S N A K E");
        primaryStage.getIcons().add(new Image(getClass().getResource("/snakyTest.png").toExternalForm()));
        primaryStage.setScene(sceneMenu);
        primaryStage.show();

        App.root.getChildren().addAll(App.snake.getSnakeLengthArr());
    }


    public static void getNewScoreOnScreen() {

        root.getChildren().remove(scoreText);
        scoreText.setFill(Color.rgb(255, 255, 255));
        scoreText.setFont(Font.font("Courier New", Snake.scale));
        scoreText.setTextOrigin(VPos.BOTTOM);
        scoreText.setTranslateX(0);
        scoreText.setText("Score: " + score);
        scoreText.setLayoutX(Snake.scale);
        scoreText.setTranslateY(GameSize);
        root.getChildren().addAll(scoreText);
    }
}

