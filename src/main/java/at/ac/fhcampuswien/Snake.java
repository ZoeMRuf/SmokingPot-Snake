package at.ac.fhcampuswien;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Snake extends Node {
    public static int scale = 20;
    private final static int START_SIZE = 2;
    public final int GRID_SIZE = App.GAME_SIZE / scale;
    private static int nowSnakeSize = START_SIZE;
    private Rectangle[] snakeLengthArr = new Rectangle[nowSnakeSize];
    public boolean snakeEats;

    private char direction = 'D';

    //Constructor for Snake Object
    public Snake() {
        for (int i = 0; i < nowSnakeSize; i++) {
            //Create and fill Rectangle
            Rectangle bodyPart = new Rectangle();
            //Snake-Style
            if (i == 0) {
                Image snakeHead = new Image("/SnakeHead.png");
                ImagePattern imagePattern = new ImagePattern(snakeHead);
                bodyPart.setFill(imagePattern);
            } else {
                Image snakeBody = new Image("/Snakebody.png");
                ImagePattern imagePattern = new ImagePattern(snakeBody);
                bodyPart.setFill(imagePattern);
            }

            //Height and Width of Rectangles
            bodyPart.setHeight(GRID_SIZE - 1);
            bodyPart.setWidth(GRID_SIZE - 1);

            bodyPart.setX(GRID_SIZE * (scale / 2));
            bodyPart.setY(GRID_SIZE * scale / 2 * (nowSnakeSize - 1) - i * GRID_SIZE);
            snakeLengthArr[i] = bodyPart;
        }
    }

    //Getter and Setter for snake

    public Rectangle[] getSnakeLengthArr() {
        return snakeLengthArr;
    }

    public Rectangle getHead() {
        return this.getSnakeLengthArr()[0];
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    //add a new BodyPart to the snake Array
    public void addBodyPart(Pane pane) {
        Rectangle[] snakeNextSizeArray = new Rectangle[snakeLengthArr.length + 1];
        System.arraycopy(snakeLengthArr, 0, snakeNextSizeArray, 0, snakeLengthArr.length);

        /*Long Version from Arraycopy
        for (int i = 0; i < snakeLengthArr.length; i++) {
            snakeNextSizeArray[i] = snakeLengthArr[i];
        }
         */

        Rectangle bodyPart = new Rectangle();
        Image snakeBody = new Image("/Snakebody.png");
        ImagePattern imagePattern = new ImagePattern(snakeBody);
        bodyPart.setFill(imagePattern);
        bodyPart.setWidth(GRID_SIZE - 1);
        bodyPart.setHeight(GRID_SIZE - 1);

        //so this rectangle is out of the GameBoard, when it's created
        bodyPart.setX(-GRID_SIZE);
        bodyPart.setY(-GRID_SIZE);

        snakeNextSizeArray[snakeLengthArr.length] = bodyPart;

        //overwrite whole variable
        snakeLengthArr = snakeNextSizeArray;

        pane.getChildren().addAll(bodyPart);
    }

    public void moveSnake() {
        if (!GameLoop.paused) {
            //Body
            for (int i = this.getSnakeLengthArr().length - 1; i > 0; i--) {
                this.getSnakeLengthArr()[i].setX(getSnakeLengthArr()[i - 1].getX());
                this.getSnakeLengthArr()[i].setY(getSnakeLengthArr()[i - 1].getY());
            }
        }

        if (!GameLoop.paused) {
            //Head
            switch (direction) {
                case 'R':
                    this.getHead().setX(this.getHead().getX() + GRID_SIZE);
                    this.getHead().setRotate(-90);
                    break;
                case 'L':
                    this.getHead().setX(this.getHead().getX() - GRID_SIZE);
                    this.getHead().setRotate(90);
                    break;
                case 'U':
                    this.getHead().setY(this.getHead().getY() - GRID_SIZE);
                    this.getHead().setRotate(180);
                    break;
                case 'D':
                    this.getHead().setY(this.getHead().getY() + GRID_SIZE);
                    this.getHead().setRotate(0);
                    break;
            }
        }
    }

    public void doesSnakeEat() {
        snakeEats = ((this.getHead().getX() == (GameLoop.food.getFoodX())) && (this.getHead().getY() ==
                (GameLoop.food.getFoodY())));
    }
}
