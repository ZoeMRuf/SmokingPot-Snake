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
    public static Scene sceneGame, sceneMenu, sceneGameOver;
    private static GameLoop gameLoop;
    public Text scoreText;
    private static Group group;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        //Game Scene:
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

        //Menu scene:
        Label label = new Label("Welcome to our Version of Snake");
        Button startGame = new Button("Start the Game");
        Button showHighScore = new Button("Show High-score");

        startGame.setOnAction(e -> primaryStage.setScene(sceneGame));
        Button gameOver = new Button("Test GameOver");
        gameOver.setOnAction(e -> primaryStage.setScene(sceneGameOver));

        VBox layoutMenu = new VBox();
        layoutMenu.getChildren().addAll(label, startGame,showHighScore, gameOver);
        sceneMenu = new Scene(layoutMenu, GameSize, GameSize);

        //GameOver Scene
        Button playAgain = new Button("Play again");
        playAgain.setOnAction(e -> primaryStage.setScene(sceneGame));
        Button backToMenu = new Button("Back to Menu");
        backToMenu.setOnAction(e -> primaryStage.setScene(sceneMenu));

        VBox layoutGameOver = new VBox();
        layoutGameOver.getChildren().addAll(playAgain, backToMenu);
        sceneGameOver = new Scene(layoutGameOver, GameSize, GameSize);


        //to display our game
        primaryStage.setTitle("S N A K E");
        //TODO @Andela hier passt sicher eines von deinen gezeichnten Bildern gut als Icon :)
        primaryStage.getIcons().add(new Image(getClass().getResource("/OnlyTestApple.jpg").toExternalForm()));
        primaryStage.setScene(sceneMenu);
        primaryStage.show();

        App.root.getChildren().addAll(App.snake.getSnakeLengthArr());
    }
}
