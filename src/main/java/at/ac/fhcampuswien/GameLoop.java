package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoop {

    public Timeline timeLine;
    private final double tickTime = 150;
    private boolean spawnFood = false;
    public static Food food = new Food();


    /*
        fruitNew.setFruit(x, y);
        mainPane.getChildren().remove(fruit.getFruit());
        mainPane.getChildren().addAll(fruitNew.getFruit());
        fruit = fruitNew;

     */



    public GameLoop() {
        this.timeLine = new Timeline(new KeyFrame(Duration.millis(tickTime),event -> {
            //what needs to be repeated for the game to work

            //set first food
            if (!spawnFood){
                food.setRandomFood(App.root);
                spawnFood = true;
            }

            getInput();
            App.snake.moveSnake();
            App.snake.doesSnakeEat();

            if (App.snake.snakeEats) {
                //new food
                food.deleteFood(App.root);

                //add body part
                App.snake.addBodyPart(App.root);

                App.score++;
            }
        }));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    private void getInput() {
        App.sceneGame.setOnKeyReleased(event -> {
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
