package at.ac.fhcampuswien;
import javafx.scene.shape.Rectangle;

public class GameOver {
    public static boolean isGameOver = false;
    public static boolean isGameWon = false;
    public static boolean gameOverPlayAgain = false;

    /*
    All static Methods to check if the Game is Over or Won no constructor necessary
     */

    public static boolean snakeHitItSelf() {
        Rectangle bodyPart;
        for (int i = 1; i < App.snake.getSnakeLengthArr().length; i++) {
            bodyPart = App.snake.getSnakeLengthArr()[i];
            if (App.snake.getHead().getX() == bodyPart.getX() && App.snake.getHead().getY() == bodyPart.getY()) {
                isGameOver = true;
            }
        }

        if (isGameOver){
            for (int i = 0; i <= App.snake.getSnakeLengthArr().length - 1 ; i++) {
                if(i != App.snake.getSnakeLengthArr().length - 1) {
                    App.snake.getSnakeLengthArr()[i].setX(App.snake.getSnakeLengthArr()[i + 1].getX());
                    App.snake.getSnakeLengthArr()[i].setY(App.snake.getSnakeLengthArr()[i + 1].getY());
                }else{
                    App.root.getChildren().remove(App.snake.getSnakeLengthArr()[i]);
                }
            }
        }
        return isGameOver;
    }

    public static boolean snakeLeavesGameBoard() {

        int outOfGame = App.GAME_SIZE;

        if (App.snake.getHead().getX() == (outOfGame - App.snake.GRID_SIZE) && App.snake.getDirection() == 'R') {

            App.snake.getHead().setRotate(-90);
            isGameOver = true;
        } else if (App.snake.getHead().getY() == (outOfGame - App.snake.GRID_SIZE) && App.snake.getDirection() == 'D') {

            App.snake.getHead().setRotate(0);
            isGameOver = true;
        } else if (App.snake.getHead().getX() == 0 && App.snake.getDirection() == 'L' ) {

            App.snake.getHead().setRotate(90);
            isGameOver = true;
        }else if(App.snake.getHead().getY() == 0 && App.snake.getDirection() == 'U'){

            App.snake.getHead().setRotate(180);
            isGameOver = true;
        }

        return isGameOver;
    }

    public static boolean gameWon() {
        if (App.score == Snake.scale * Snake.scale - 2) {
            isGameWon = true;
        }
        return isGameWon;
    }

}
