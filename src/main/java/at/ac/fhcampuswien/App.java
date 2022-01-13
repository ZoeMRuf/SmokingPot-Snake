package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;


import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


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
    public void start(Stage primaryStage) throws FileNotFoundException {

        //Game Scene:
        //Objekts that need to be created
        VBox layoutGame = new VBox();
        root = new Pane();

        scoreText = new Text();
        getNewScoreOnScreen();

        //Begin GameOver Scene inside of GameScene:
        paneGameOver = new Pane();

        playAgain = new Button("Play again");
        Font playAgainFont = Font.font("Courier New",FontWeight.BOLD,40);
        playAgain.setFont(playAgainFont);
        //Pane root = new Pane();
        playAgain.setLayoutX(10);
        playAgain.setLayoutY(10);
        playAgain.setOnAction(e -> {
            gameLoop.timeLine.play();
            primaryStage.setScene(sceneGame);
        });
        backToMenu = new Button("Back to Menu");
        Font btmFont = Font.font("Courier New",FontWeight.BOLD,30);
        backToMenu.setFont(btmFont);
        backToMenu.setLayoutX(50);
        backToMenu.setLayoutY(30);
        backToMenu.setOnAction(e -> primaryStage.setScene(sceneMenu));

        gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font ("Courier New", FontWeight.BOLD,60));
        gameOverText.setFill(Color.rgb(255,3,3));
        gameOverText.setLayoutX(10);
        gameOverText.setLayoutY(10);
        gameOverscoreText = new Text("Score: " + score);
        gameOverscoreText.setFill(Color.rgb(255, 3, 3));
        gameOverscoreText.setFont(Font.font("Courier New", Snake.scale));
        gameOverscoreText.setLayoutX(10);
        gameOverscoreText.setLayoutY(10);

        // End of Game Over inside of GameScene //from Andela: why? why no new Scene, dont know how to fix it

        layoutGame.getChildren().addAll(root, paneGameOver);
        layoutGame.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        sceneGame = new Scene(layoutGame, GameSize, GameSize);

        snake = new Snake();
        gameLoop = new GameLoop();
        gameLoop.timeLine.stop();

        //Menu scene:
        Image snakyTest = new Image("/snakyTest.png");
        ImageView imgView = new ImageView();
        imgView.setImage(snakyTest);
        Rectangle2D viewportRect = new Rectangle2D(0,0,150,150);
        imgView.setViewport(viewportRect);

        Label label = new Label("Welcome to our Version of Snake");
        Font labelfont = Font.font("Courier New",FontWeight.BOLD,20);
        label.setFont(labelfont);
        Button startGame = new Button("Start the Game");
        Font startG = Font.font("Courier New",FontWeight.BOLD,36);
        startGame.setFont(startG);
        Button showHighScore = new Button("Show High-score");
        Font showHS = Font.font("Courier New",FontWeight.BOLD,16);
        showHighScore.setFont(showHS);


         startGame.setOnAction(event -> {
            gameLoop.timeLine.play();
            primaryStage.setScene(sceneGame);
        });
        VBox layoutMenu = new VBox();
        layoutMenu.getChildren().addAll(imgView, label, startGame,showHighScore);
        sceneMenu = new Scene(layoutMenu, GameSize, GameSize);
        layoutMenu.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN,null,null)));
        layoutMenu.setAlignment(Pos.CENTER);
        layoutMenu.setSpacing(30);

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

