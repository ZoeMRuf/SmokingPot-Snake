package at.ac.fhcampuswien;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameOver {

    //TODO if Gameover get Menu Scene (from App) & delete from App button "Test GameOver scene"


    public static boolean isGameOver = false;


    public static boolean snakeHitItSelf(){

        Rectangle bodyPart;

        for (int i = 1; i < App.snake.getSnakeLengthArr().length; i++) {

            bodyPart = App.snake.getSnakeLengthArr()[i];

            if (App.snake.getHead().getX() == bodyPart.getX() && App.snake.getHead().getY() == bodyPart.getY()){

                isGameOver = true;

            }

        }

        return isGameOver;
    }

    public static boolean snakeLeavesGameBoard(){

        int outOfGame = App.GameSize; //+grid???

        if (App.snake.getHead().getX() >= outOfGame || App.snake.getHead().getY() >= outOfGame){

            isGameOver = true;

        }
        else if (App.snake.getHead().getX() < 0 || App.snake.getHead().getY() < 0){

            isGameOver = true;

        }

        return isGameOver;
    }



}
