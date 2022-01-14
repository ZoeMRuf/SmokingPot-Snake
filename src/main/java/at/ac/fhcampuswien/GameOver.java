package at.ac.fhcampuswien;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameOver {

    //TODO snakeHitsitSelf Head is stuck in the snake


    public static boolean isGameOver = false;


    public static boolean snakeHitItSelf() {

        Rectangle bodyPart;

        for (int i = 1; i < App.snake.getSnakeLengthArr().length; i++) {

            bodyPart = App.snake.getSnakeLengthArr()[i];

            if (App.snake.getHead().getX() == bodyPart.getX() && App.snake.getHead().getY() == bodyPart.getY()) {

                isGameOver = true;

            }

        }

        return isGameOver;
    }

    public static boolean snakeLeavesGameBoard() {

        int outOfGame = App.GameSize;

        if (App.snake.getHead().getX() == (outOfGame - App.snake.gridSize) && App.snake.getDirection() == 'R') {

            isGameOver = true;
        } else if (App.snake.getHead().getY() == (outOfGame - App.snake.gridSize) && App.snake.getDirection() == 'D') {

            isGameOver = true;

        } else if (App.snake.getHead().getX() == 0 && App.snake.getDirection() == 'L' ) {
            isGameOver = true;

        }else if(App.snake.getHead().getY() == 0 && App.snake.getDirection() == 'U'){
            isGameOver = true;
        }
        return isGameOver;
    }

}
