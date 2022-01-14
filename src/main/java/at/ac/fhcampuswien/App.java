package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


import java.io.FileNotFoundException;

import static javafx.scene.paint.Color.*;


public class App extends Application {

    //Alle Variablen die wir später brauchen
    public static Snake snake;
    public static GameOver gameOver;
    public static int GameSize = 480;
    public static int score = 0;

    //GUI Variablen
    public static Pane root;
    public static GridPane paneGameOver, paneWin;
    public static Scene sceneGame, sceneMenu, sceneGameOver;
    public static GameLoop gameLoop;
    public static Text scoreText, gameOverText, gameOverscoreText, gameWinText;
    public static Button playAgain, backToMenu;
    public static boolean pressed = false;
    public static HBox heightBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        //Game Scene:
        //Objekts that need to be created
        VBox layoutGame = new VBox();
        Label press = new Label("Press an arrow key\n   to continue");
        root = new Pane();

        scoreText = new Text();
        getNewScoreOnScreen();


        //Begin GameOver Scene inside of GameScene:
        paneGameOver = new GridPane();
        paneGameOver.setAlignment(Pos.CENTER);
        paneGameOver.setHgap(20);
        paneGameOver.setVgap(50);
        paneGameOver.setPadding(new Insets(10, 10, 10, 10));


        playAgain = new Button("Play again");
        Font playAgainFont = Font.font("Courier New", FontWeight.BOLD, 20);
        playAgain.setFont(playAgainFont);
        //Pane root = new Pane();
        playAgain.setLayoutX(10);
        playAgain.setLayoutY(10);
        playAgain.setOnAction(e -> {
            root.getChildren().removeAll(snake.getSnakeLengthArr());
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());

            paneGameOver.getChildren().removeAll(playAgain, backToMenu, gameOverscoreText, gameOverText);
            root.getChildren().add(scoreText);

            score = 0;
            getNewScoreOnScreen();


            GameOver.gameOverPlayAgain = true;
        });

        backToMenu = new Button("Back to Menu");
        Font btmFont = Font.font("Courier New", FontWeight.BOLD, 20);
        backToMenu.setFont(btmFont);
        backToMenu.setLayoutX(10);
        backToMenu.setLayoutY(10);
        backToMenu.setOnAction(event -> {
            root.getChildren().removeAll(snake.getSnakeLengthArr());
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());

            paneGameOver.getChildren().removeAll(playAgain, backToMenu, gameOverscoreText, gameOverText);
            root.getChildren().add(scoreText);

            score = 0;
            getNewScoreOnScreen();


            GameOver.gameOverPlayAgain = true;
            primaryStage.setScene(sceneMenu);
        });

        gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Courier New", FontWeight.BOLD, 60));
        gameOverText.setFill(rgb(255, 3, 3));
        gameOverText.setLayoutX(10);
        gameOverText.setLayoutY(10);


        // End of Game Over inside of GameScene

        //Begin Game Win Scene inside of GameScene:
        paneWin = new GridPane();
        paneWin.setAlignment(Pos.CENTER);
        paneWin.setHgap(20);
        paneWin.setVgap(50);
        paneWin.setPadding(new Insets(10, 10, 10, 10));

        gameWinText = new Text("You Won!");
        gameWinText.setFont(Font.font("Courier New", FontWeight.BOLD, 60));
        gameWinText.setFill(rgb(3, 150, 3));
        gameWinText.setLayoutX(10);
        gameWinText.setLayoutY(10);
        gameOverscoreText = new Text("Score: " + score);
        gameOverscoreText.setFill(rgb(255, 3, 3));
        gameOverscoreText.setFont(Font.font("Courier New", Snake.scale));
        gameOverscoreText.setLayoutX(10);
        gameOverscoreText.setLayoutY(10);

        // End of Game Win inside of GameScene

        layoutGame.getChildren().addAll(root, paneGameOver, paneWin);
        layoutGame.setBackground(new Background(new BackgroundFill(BLACK, null, null)));

        sceneGame = new Scene(layoutGame, GameSize, GameSize);

        snake = new Snake();
        gameLoop = new GameLoop();
        gameLoop.timeLine.stop();

        //Menu scene:

        Image snakyTest = new Image("/SnakyGreen.png");
        ImageView imgView = new ImageView();
        imgView.setImage(snakyTest);
        Rectangle2D viewportRect = new Rectangle2D(0, 0, 420, 305);
        imgView.setViewport(viewportRect);


        Label label = new Label("Welcome to our Version of Snake");
        Font labelfont = Font.font("Courier New", FontWeight.BOLD, 23);
        label.setFont(labelfont);
        Button startGame = new Button("Start the Game");
        Font startG = Font.font("Courier New", FontWeight.BOLD, 36);
        startGame.setFont(startG);
        Button showHighScore = new Button("Show High-score");
        Font showHS = Font.font("Courier New", FontWeight.BOLD, 16);
        showHighScore.setFont(showHS);

        //Test for Win condition
/*
        Button testWin = new Button("Test Win");
        Font showTestWin = Font.font("Courier New", FontWeight.BOLD, 16);
        testWin.setFont(showTestWin);
        testWin.setOnKeyReleased(event -> score = 323);

        testWin.setOnAction(event -> primaryStage.setScene(sceneGame));
        App.sceneGame.setOnKeyReleased(event -> gameLoop.timeLine.play());
        VBox layoutMenuTest = new VBox();
        layoutMenuTest.getChildren().addAll(imgView, label, startGame, showHighScore, testWin);
        sceneMenu = new Scene(layoutMenuTest, GameSize, GameSize);
        layoutMenuTest.setBackground(new Background(new BackgroundFill(DARKGREEN,null,null)));
        layoutMenuTest.setAlignment(Pos.CENTER);
        layoutMenuTest.setSpacing(10);
        score = 323;
*/
        //Game Start
        startGame.setOnAction(event -> primaryStage.setScene(sceneGame));
        App.sceneGame.setOnKeyReleased(event -> gameLoop.timeLine.play());
        VBox layoutMenu = new VBox();
        layoutMenu.getChildren().addAll(imgView, label, startGame, showHighScore);
        sceneMenu = new Scene(layoutMenu, GameSize, GameSize);
        layoutMenu.setBackground(new Background(new BackgroundFill(DARKGREEN,null,null)));
        layoutMenu.setAlignment(Pos.CENTER);
        layoutMenu.setSpacing(10);

        //Pause Menu
        Pane pauseMenu = new Pane();
        Button cont = new Button("Continue");
        Font contFont = Font.font("Courier New", FontWeight.BOLD, 20);
        cont.setFont(btmFont);
        cont.setLayoutX(150);
        cont.setLayoutY(150);
        Button home = new Button("Home");
        Font homeFont = Font.font("Courier New", FontWeight.BOLD, 20);
        home.setFont(btmFont);
        home.setLayoutX(185);
        home.setLayoutY(250);
        Scene pauseScene = new Scene(pauseMenu, GameSize, GameSize);
        pauseMenu.setBackground(new Background(new BackgroundFill(LIMEGREEN, null, null)));
        pauseMenu.getChildren().addAll(cont, home);
        //home.setLayoutY(GameSize/17);
        press.setLayoutX(GameSize / 10);
        press.setLayoutY(GameSize / 3);
        press.setFont(startG);

        cont.setOnAction(event -> {
            GameLoop.paused = true;
            primaryStage.setScene(sceneGame);
            if (!pressed) {
                root.getChildren().addAll(press);
                pressed = true;
            }
        });

        home.setOnAction(event -> {
            gameLoop.timeLine.stop();
            root.getChildren().removeAll(snake.getSnakeLengthArr());
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());
            score = 0;
            getNewScoreOnScreen();
            primaryStage.setScene(sceneMenu);
        });

        sceneGame.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                primaryStage.setScene(pauseScene);
                if (!GameLoop.paused) {
                    gameLoop.timeLine.pause();
                }
            }

            switch (key.getCode()) {
                case DOWN:
                case LEFT:
                case RIGHT:
                case UP:
                    if (GameLoop.paused) {
                        gameLoop.timeLine.play();
                        GameLoop.paused = false;
                        pressed = false;
                        root.getChildren().removeAll(press);
                    }
                    if (GameOver.gameOverPlayAgain) {
                        gameLoop.timeLine.play();
                        GameOver.gameOverPlayAgain = false;
                    }
            }
        });

        pauseScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                GameLoop.paused = true;
                primaryStage.setScene(sceneGame);
                if (!pressed) {
                    root.getChildren().addAll(press);
                }
                pressed = true;
            }
        });


        //to display our game
        primaryStage.setTitle("S N A K E");
        primaryStage.getIcons().add(new Image(getClass().getResource("/SnakyWhite.png").toExternalForm()));
        primaryStage.setScene(sceneMenu);
        primaryStage.show();

        App.root.getChildren().addAll(App.snake.getSnakeLengthArr());
    }


    public static void getNewScoreOnScreen() {

        root.getChildren().remove(scoreText);
        scoreText.setFill(rgb(255, 255, 255));
        scoreText.setFont(Font.font("Courier New", Snake.scale));
        scoreText.setTextOrigin(VPos.BOTTOM);
        scoreText.setTranslateX(0);
        scoreText.setText("Score: " + score);
        scoreText.setLayoutX(Snake.scale);
        scoreText.setTranslateY(GameSize);
        root.getChildren().addAll(scoreText);
    }
}

