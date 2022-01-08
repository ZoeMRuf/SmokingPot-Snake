package at.ac.fhcampuswien;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

public class Snake extends Node {
    // Variables
    private static int scale = 20;
    private static int startSize = 2;
    private final int gridSize = App.GameSize / scale;
    public static int nowSnakeSize = startSize;
    private Rectangle[] snakeLengthArr = new Rectangle[nowSnakeSize];

    //GUI
    private static Color headColor = Color.rgb(3, 160, 98);
    private static Color bodyColor = Color.rgb(209, 250, 113);

    //Conctuctor for Snake Object
    public Snake() {
        for (int i = 0; i < nowSnakeSize; i++) {
            //Rectangle erzeugen und befüllen

            Rectangle bodyPart = new Rectangle();

            //Farbe der Snake festlegen
            if (i == 0) {
                bodyPart.setFill(headColor);
            } else {
                bodyPart.setFill(bodyColor);
            }

            //Höhe und Breite der Rectangels
            bodyPart.setHeight(gridSize - 1);
            bodyPart.setWidth(gridSize - 1);

            bodyPart.setX(gridSize * (nowSnakeSize - 1) - i * gridSize + gridSize);

            snakeLengthArr[i] = bodyPart;
        }
    }

    public Rectangle[] getSnakeLengthArr() {
        return snakeLengthArr;
    }

    //add a new BodyPart to the snake Array
    public void addBodyPart(Pane pane) {
        Rectangle[] snakeNextSizeArray = new Rectangle[snakeLengthArr.length + 1];

        System.arraycopy(snakeLengthArr, 0, snakeNextSizeArray, 0, snakeLengthArr.length);

        /* Long Version from Arraycopy
        for (int i = 0; i < snakeLengthArr.length; i++) {
            snakeNextSizeArray[i] = snakeLengthArr[i];
        }
         */

        Rectangle bodyPart = new Rectangle();
        bodyPart.setFill(bodyColor);
        bodyPart.setWidth(gridSize - 1);
        bodyPart.setHeight(gridSize - 1);

        //so this rectangle is out of the GameBoard, when it's created
        bodyPart.setX(- gridSize);
        bodyPart.setY(- gridSize);

        snakeNextSizeArray[snakeLengthArr.length] = bodyPart;

        //overwrite whole variable
        snakeLengthArr = snakeNextSizeArray;

        pane.getChildren().addAll(bodyPart);
    }
}