package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.*;
import static javafx.scene.paint.Color.rgb;

public class GameLoop {

    public Timeline timeLine;
    private final double TICK_TIME = 150;
    private boolean spawnFood = false;
    public static Food food = new Food();
    private static boolean snakeMoved = true;
    public static boolean paused = false;
    private File file;
    private int highScore;


    public GameLoop() {
        this.timeLine = new Timeline(new KeyFrame(Duration.millis(TICK_TIME), event -> {
            //what needs to be repeated for the game to work

            //set first food
            if (!spawnFood){
                food.setRandomFood(App.root);
                spawnFood = true;
            }

            //move the snake
            getInput();
            snakeMoved = true;

            //check if GameOver or GameWon
            GameOver.snakeLeavesGameBoard();
            GameOver.gameWon();

            if(!GameOver.isGameOver) {
                App.snake.moveSnake();
                App.snake.doesSnakeEat();
                GameOver.snakeHitItSelf();
            }

            //add bodyPart if snake eats
            if (App.snake.snakeEats) {
                //new food
                food.deleteFood(App.root);

                //add body part
                App.snake.addBodyPart(App.root);
                App.score++;
                App.getNewScoreOnScreen();

            }

            //Show Game Over Scene if GameOver
            if (GameOver.isGameOver) {
                this.timeLine.stop();
                App.paneGameOver.add(App.gameOverText, 0, 0, 3,2);
                App.paneGameOver.setAlignment(Pos.CENTER);
                GridPane.setHalignment(App.gameOverText, HPos.CENTER);
                App.paneGameOver.add(App.playAgain, 0, 2, 1, 1);
                App.paneGameOver.add(App.backToMenu, 2, 2, 1, 1);
                //to get current Score in GameOverscoreText:
                forHighscore();
                getGameOverscoreText();
                App.gameOverscoreText.setFill(rgb(255, 3, 3));
                App.paneGameOver.add(App.gameOverscoreText, 1, 3, 1, 1);
                App.root.getChildren().remove(App.scoreText);
            }

            //Show Game Won Scene if GameWon
            if (GameOver.isGameWon) {
                System.out.println("You Won!");
                this.timeLine.stop();

                App.paneWin.add(App.gameWinText, 0, 0, 3,2);
                App.paneGameOver.setAlignment(Pos.CENTER);

                App.paneWin.setHalignment(App.gameWinText, HPos.CENTER);
                App.paneWin.add(App.playAgainWin, 0, 2, 1, 1);
                App.paneWin.add(App.backToMenuWin, 2, 2, 1, 1);
                forHighscore();
                getGameOverscoreText();
                App.gameOverscoreText.setFill(rgb(30, 160, 98));
                App.paneWin.add(App.gameOverscoreText, 1, 3, 1, 1);
                App.root.getChildren().remove(App.scoreText);
            }

        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    public int getHighScore() {
        forHighscore();
        readFile();
        return highScore;
    }

    //gets the input from user to move the snake
    private void getInput() {
        if(snakeMoved){
            App.sceneGame.setOnKeyPressed(event -> {
                switch(event.getCode()) {
                    case RIGHT:
                    case D:
                        if (App.snake.getDirection() != 'L' && snakeMoved){
                            App.snake.setDirection('R');
                            snakeMoved = false;
                        }
                        break;
                    case LEFT:
                    case A:
                        if (App.snake.getDirection() != 'R' && snakeMoved){
                            App.snake.setDirection('L');
                            snakeMoved = false;
                        }
                        break;
                    case UP:
                    case W:
                        if (App.snake.getDirection() != 'D' && snakeMoved){
                            App.snake.setDirection('U');
                            snakeMoved = false;
                        }
                        break;
                    case DOWN:
                    case S:
                        if (App.snake.getDirection() != 'U' && snakeMoved){
                            App.snake.setDirection('D');
                            snakeMoved = false;
                        }
                        break;
                }
            });
        }
    }

    private void getGameOverscoreText() {
        App.gameOverscoreText = new Text("Your score: " + App.score + "\nHigh score: " + highScore);
        App.gameOverscoreText.setFont(Font.font("Courier New", Snake.scale));
        App.gameOverscoreText.setLayoutX(10);
        App.gameOverscoreText.setLayoutY(10);
    }

    //method for everything about highscore - reference: https://www.w3schools.com/java/java_files_create.asp
    private void forHighscore() {

        //create file highscore
        try {
            file = new File("highscore.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        readFile();


            //write into file
        try {
            PrintWriter write = new PrintWriter(file);
            write.println(highScore);
            write.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public void readFile () {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                try {
                    int isHighScoreOrNot = Integer.parseInt(line.trim());
                    if ((isHighScoreOrNot < App.score) && (App.score != 0)) {
                        highScore = App.score;
                    } else {
                        highScore = isHighScoreOrNot;
                    }

                    line = reader.readLine();
                } catch (NumberFormatException e1) {}

            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
    }
}

