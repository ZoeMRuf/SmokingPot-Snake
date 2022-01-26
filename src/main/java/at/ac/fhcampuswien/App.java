package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static javafx.scene.paint.Color.*;

public class App extends Application {

    //Variables for later use
    protected static Snake snake;
    private static GameLoop gameLoop;
    protected final static int GAME_SIZE = 480;
    protected static int score = 0;

    //GUI Variables
    protected static VBox layoutHighScore;
    protected static Pane root;
    protected static GridPane paneGameOver, paneWin;
    protected static Scene sceneGame, sceneMenu, sceneHighScore;
    protected static Text scoreText, gameOverText, gameOverScoreText, gameWinText, textHighscore;
    protected static Button playAgain, backToMenu, playAgainWin, backToMenuWin, backToMenu2;
    private static boolean pressed = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Objects needed to play the Game
        snake = new Snake();
        gameLoop = new GameLoop();
        gameLoop.timeLine.stop();

        //Game Scene:
        VBox layoutGame = new VBox();
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
        Font playAgainFont = Font.font("Courier New", FontWeight.BOLD, 15);
        playAgain.setFont(playAgainFont);
        //Pane root = new Pane();
        playAgain.setLayoutX(10);
        playAgain.setLayoutY(10);
        playAgain.setOnAction(event -> {
            root.getChildren().removeAll(snake.getSnakeLengthArr());
            root.getChildren().remove(Food.food);
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());
            GameOver.isGameOver = false;
            paneGameOver.getChildren().removeAll(playAgain, backToMenu, gameOverScoreText, gameOverText);
            root.getChildren().add(scoreText);

            score = 0;
            getNewScoreOnScreen();

            GameOver.gameOverPlayAgain = true;
        });

        backToMenu = new Button("Back to Menu");
        Font btmFont = Font.font("Courier New", FontWeight.BOLD, 15);
        backToMenu.setFont(btmFont);
        backToMenu.setLayoutX(10);
        backToMenu.setLayoutY(10);
        backToMenu.setOnAction(event -> {
            root.getChildren().removeAll(snake.getSnakeLengthArr());
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());
            GameOver.isGameOver = false;
            paneGameOver.getChildren().removeAll(playAgain, backToMenu, gameOverScoreText, gameOverText);
            root.getChildren().add(scoreText);
            root.getChildren().removeAll(Food.food);
            Food.spawnedFood = true;

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
        gameOverScoreText = new Text("Score: " + score);
        gameOverScoreText.setFill(rgb(255, 3, 3));
        gameOverScoreText.setFont(Font.font("Courier New", Snake.scale));
        gameOverScoreText.setLayoutX(10);
        gameOverScoreText.setLayoutY(10);


        playAgainWin = new Button("Play again");
        playAgainWin.setFont(playAgainFont);
        playAgainWin.setLayoutX(10);
        playAgainWin.setLayoutY(10);
        playAgainWin.setOnAction(event -> {
            root.getChildren().removeAll(snake.getSnakeLengthArr());
            root.getChildren().remove(Food.food);
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());
            GameOver.isGameWon = false;
            paneWin.getChildren().removeAll(playAgainWin, backToMenuWin, gameOverScoreText, gameWinText);
            root.getChildren().add(scoreText);

            score = 0;
            getNewScoreOnScreen();

            GameOver.gameOverPlayAgain = true;
        });

        backToMenuWin = new Button("Back to Menu");
        backToMenuWin.setFont(btmFont);
        backToMenuWin.setLayoutX(10);
        backToMenuWin.setLayoutY(10);
        backToMenuWin.setOnAction(event -> {
            root.getChildren().removeAll(snake.getSnakeLengthArr());
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());
            GameOver.isGameWon = false;
            paneWin.getChildren().removeAll(playAgainWin, backToMenuWin, gameOverScoreText, gameWinText);
            root.getChildren().add(scoreText);
            root.getChildren().removeAll(Food.food);
            Food.spawnedFood = true;

            score = 0;
            getNewScoreOnScreen();

            GameOver.gameOverPlayAgain = true;
            primaryStage.setScene(sceneMenu);
        });


        // End of Game Win inside of GameScene

        //Show GameWon and Game Over
        layoutGame.getChildren().addAll(root, paneGameOver, paneWin);
        layoutGame.setBackground(new Background(new BackgroundFill(BLACK, null, null)));

        sceneGame = new Scene(layoutGame, GAME_SIZE, GAME_SIZE);


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
        showHighScore.setOnAction(event -> primaryStage.setScene(sceneHighScore));


        // HighscoreScene
        layoutHighScore = new VBox();
        textHighscore = new Text("Highscore: " + gameLoop.getHighScore());
        Font fontHighscore = Font.font("Courier New", FontWeight.BOLD, 40);
        textHighscore.setFont(fontHighscore);
        textHighscore.setFill(rgb(0, 100, 0));
        textHighscore.setStrokeWidth(0.5);
        textHighscore.setStroke(Color.rgb(75, 251, 75));
        textHighscore.setLayoutX(GAME_SIZE / 10);
        textHighscore.setLayoutY(GAME_SIZE / 3);
        backToMenu2 = new Button("Back to Menu");
        Font btm2Font = Font.font("Courier New", FontWeight.BOLD, 15);
        backToMenu2.setFont(btm2Font);
        backToMenu2.setAlignment(Pos.BOTTOM_CENTER);
        backToMenu2.setOnAction(event -> primaryStage.setScene(sceneMenu));
        layoutHighScore.getChildren().addAll(textHighscore, backToMenu2);
        layoutHighScore.setBackground(new Background(new BackgroundFill(BLACK, null, null)));
        layoutHighScore.setSpacing(10);
        layoutHighScore.setAlignment(Pos.CENTER);
        sceneHighScore = new Scene(layoutHighScore, GAME_SIZE, GAME_SIZE);


        //Game Start
        startGame.setOnAction(event -> primaryStage.setScene(sceneGame));

        sceneGame.setOnKeyReleased(event -> {
            if (!GameOver.isGameOver && !GameOver.isGameWon) {
                gameLoop.timeLine.play();
            }
        });

        VBox layoutMenu = new VBox();
        layoutMenu.getChildren().addAll(imgView, label, startGame, showHighScore);
        sceneMenu = new Scene(layoutMenu, GAME_SIZE, GAME_SIZE);
        layoutMenu.setBackground(new Background(new BackgroundFill(DARKGREEN, null, null)));
        layoutMenu.setAlignment(Pos.CENTER);
        layoutMenu.setSpacing(10);

        //Pause Menu
        Label press = new Label("Press an arrow key\n   to continue");
        Pane pauseMenu = new Pane();
        Button cont = new Button("Continue");
        cont.setFont(btmFont);
        cont.setLayoutX(190);
        cont.setLayoutY(150);
        Button home = new Button("Home");
        home.setFont(btmFont);
        home.setLayoutX(210);
        home.setLayoutY(250);

        Scene pauseScene = new Scene(pauseMenu, GAME_SIZE, GAME_SIZE);
        pauseMenu.setBackground(new Background(new BackgroundFill(BLACK, null, null)));
        pauseMenu.getChildren().addAll(cont, home);
        press.setLayoutX(GAME_SIZE / 10);
        press.setLayoutY(GAME_SIZE / 3);
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
            root.getChildren().removeAll(press);
            snake = new Snake();
            root.getChildren().addAll(snake.getSnakeLengthArr());
            score = 0;
            getNewScoreOnScreen();

            root.getChildren().removeAll(Food.food); //test
            GameOver.gameOverPlayAgain = true;

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
                case S:
                case LEFT:
                case A:
                case RIGHT:
                case D:
                case UP:
                case W:
                    if (GameLoop.paused) {
                        gameLoop.timeLine.play();
                        GameLoop.paused = false;
                        pressed = false;
                        root.getChildren().removeAll(press);
                    }
                    if (GameOver.gameOverPlayAgain) {
                        gameLoop.timeLine.play();
                        root.getChildren().addAll(Food.food);
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
                    pressed = true;
                }
            }
        });


        //to display our game
        primaryStage.setTitle("S N A K E");
        primaryStage.getIcons().add(new Image(getClass().getResource("/SnakyWhite.png").toExternalForm()));
        primaryStage.setScene(sceneMenu);
        primaryStage.setResizable(false);
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
        scoreText.setTranslateY(GAME_SIZE);
        root.getChildren().addAll(scoreText);
    }
}

