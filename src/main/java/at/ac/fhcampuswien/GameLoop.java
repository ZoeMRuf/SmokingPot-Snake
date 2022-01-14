package at.ac.fhcampuswien;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameLoop {

    public Timeline timeLine;
    public double tickTime = 150; // * App.score * 20 time dynamisch verändern?
    private boolean spawnFood = false;
    public static Food food = new Food();
    public static boolean snakeMoved = true;
    public static boolean paused = false;

    public GameLoop() {
        this.timeLine = new Timeline(new KeyFrame(Duration.millis(tickTime),event -> {
            //what needs to be repeated for the game to work

            //set first food
            if (!spawnFood){
                food.setRandomFood(App.root);
                spawnFood = true;
            }

            getInput();
            snakeMoved = true;
            GameOver.snakeLeavesGameBoard();
            if(!GameOver.isGameOver) {
                App.snake.moveSnake();
                App.snake.doesSnakeEat();
                GameOver.snakeHitItSelf();
            }


            if (App.snake.snakeEats) {
                //new food
                food.deleteFood(App.root);

                //add body part
                App.snake.addBodyPart(App.root);

                App.score++;

                App.getNewScoreOnScreen();

            }

            if (GameOver.isGameOver) {

                GameOver.isGameOver = false;
                System.out.println("Game Over");
                timeLine.stop();

                App.paneGameOver.add(App.gameOverText, 0, 0, 3,2);
                App.paneGameOver.setAlignment(Pos.CENTER);
                // Wie schaff ich es, dass das gesamte Teil nicht immer wieder nach unten rutscht beim wiederholten
                // ausführen???

                App.paneGameOver.setHalignment(App.gameOverText, HPos.CENTER);
                App.paneGameOver.add(App.playAgain, 0, 2, 1, 1);
                App.paneGameOver.add(App.backToMenu, 2, 2, 1, 1);
                App.paneGameOver.add(App.gameOverscoreText, 1, 3, 1, 1);
                App.root.getChildren().remove(App.scoreText);
            }
        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    private void getInput() {
        if(snakeMoved){
            App.sceneGame.setOnKeyReleased(event -> {
                switch(event.getCode()) {
                    case RIGHT:
                        if (App.snake.getDirection() != 'L' && snakeMoved){
                            App.snake.setDirection('R');
                            snakeMoved = false;
                        }
                        break;
                    case LEFT:
                        if (App.snake.getDirection() != 'R' && snakeMoved){
                            App.snake.setDirection('L');
                            snakeMoved = false;
                        }
                        break;
                    case UP:
                        if (App.snake.getDirection() != 'D' && snakeMoved){
                            App.snake.setDirection('U');
                            snakeMoved = false;
                        }
                        break;
                    case DOWN:
                        if (App.snake.getDirection() != 'U' && snakeMoved){
                            App.snake.setDirection('D');
                            snakeMoved = false;
                        }
                        break;
                }
            });
        }
    }
}
