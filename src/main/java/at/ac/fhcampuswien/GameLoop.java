package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoop {

    public Timeline timeLine;
    private final double tickTime = 1500;
    private boolean spawnFood = false;
    Food f = new Food();


    public GameLoop() {
        this.timeLine = new Timeline(new KeyFrame(Duration.millis(tickTime),event -> {
            //what needs to be repeated for the game to work

            getInput();
            App.snake.moveSnake();
            App.snake.addBodyPart(App.root);

            if (!spawnFood){
                f.setRandomFood(App.root);
                spawnFood = true;
            }

            f.deleteFood();

        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    private void getInput() {

        App.scene.setOnKeyReleased(event -> {
            switch(event.getCode()) {
                case RIGHT:
                    if (App.snake.getDirection() != 'L'){
                        App.snake.setDirection('R');
                    }
                    break;
                case LEFT:
                    if (App.snake.getDirection() != 'R'){
                        App.snake.setDirection('L');
                    }
                    break;
                case UP:
                    if (App.snake.getDirection() != 'D'){
                        App.snake.setDirection('U');
                    }
                    break;
                case DOWN:
                    if (App.snake.getDirection() != 'U'){
                        App.snake.setDirection('D');
                    }
                    break;
            }
        });

    }


}
