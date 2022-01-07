package at.ac.fhcampuswien;

import java.awt.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;


public class Snake {
    // Variables
    private static int scale = 20;
    private static int startSize = 2;
    private final int gridSize = App.GameSize / scale;
    public static int nowSnakeSize = startSize;
    private Rectangle[] snakeLength = new Rectangle[nowSnakeSize];


    //GUI
    private static Color headColor = Color.rgb(3, 160, 98);
    private static Color bodyColor = Color.rgb(209, 250, 113);

    //Concturtor for Snake Object
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

            //Höhe und breite der Rectangels
            bodyPart.setHeight(gridSize - 1);
            bodyPart.setWidth(gridSize - 1);

            bodyPart.setX(gridSize * (nowSnakeSize - 1) - i * gridSize);

            snakeLength[i] = bodyPart;
        }
    }

    public Rectangle[] getSnakeLength() {
        return snakeLength;
    }

    //Versuch die Snake dynamisch vergrößern zu können
    public void plusSnakeSize(){
        for (int i = 0; i < nowSnakeSize; i++) {
            //Rectangle erzeugen und befüllen

            Rectangle bodyPart = new Rectangle();

            //Farbe der Snake festlegen
            if (i == 0) {
                bodyPart.setFill(headColor);
            } else {
                bodyPart.setFill(bodyColor);
            }

            //Höhe und breite der Rectangels
            bodyPart.setHeight(gridSize - 1);
            bodyPart.setWidth(gridSize - 1);

            bodyPart.setX(gridSize * (nowSnakeSize - 1) - i * gridSize);

            snakeLength[i] = bodyPart;
        }
    }

}
