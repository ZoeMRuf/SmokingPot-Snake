package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoop {

    public Timeline timeLine;
    private final double tickTime = 1500;


    public GameLoop() {
        this.timeLine = new Timeline(new KeyFrame(Duration.millis(tickTime),event -> {
            //what needs to be repeated for the game to work

            getInput();
            App.snake.moveSnake();



        }));

        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();

    }

    private void getInput() {


        App.root.setOnKeyPressed(event -> {
            switch(event.getCode()) {
                case RIGHT -> {

                    if (App.snake.getDirection() != 'L') App.snake.setDirection('R');
                }
                case LEFT -> {
                    if (App.snake.getDirection() != 'R') App.snake.setDirection('L');
                }
                case UP -> {
                    if (App.snake.getDirection() != 'D') App.snake.setDirection('U');
                }
                case DOWN -> {
                    if (App.snake.getDirection() != 'U') App.snake.setDirection('D');
                }
            }
        });

    }


}
