package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class GameLoop {

    public Timeline timeLine;
    private final double tickTime = 150;
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
            App.snake.moveSnake();
            App.snake.doesSnakeEat();

            GameOver.snakeHitItSelf();
            GameOver.snakeLeavesGameBoard();


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

                App.root.getChildren().remove(App.scoreText);
                App.paneGameOver.getChildren().addAll(App.gameOverText, App.gameOverscoreText, App.playAgain, App.backToMenu);
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
