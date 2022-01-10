package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoop {

    public Timeline timeLine;
    private final double tickTime = 150;


    public GameLoop() {
        this.timeLine = new Timeline(new KeyFrame(Duration.millis(tickTime),event -> {
            //what needs to be repeated for the game to work

            App.snake.moveSnake();


        }));

        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();

    }





}
