package at.ac.fhcampuswien;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameOver {

    //TODO if Gameover gets Menu Scene (from App) & delete from App button "Test GameOver scene"


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
//Idee für nicht aus dem Fenster fallen
        //(App.snake.getDirection() != 'D' || App.snake.getDirection() != 'U' || App.snake.getDirection() != 'L' || App.snake.getDirection() != 'R')
        // && (App.snake.getDirection() != 'L' || App.snake.getDirection() != 'R')
        // + App.snake.gridSize nach outOfGame
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
