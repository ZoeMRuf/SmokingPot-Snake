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
    private static int scale = 10;
    private static int startSize = 2;
    private static final int gridSize = App.GameSize/scale;
    private static Rectangle[] snakeLength = new Rectangle[scale*scale];
    private static int nowSnakeSize = startSize;


    //GUI
    private static Color headColor = Color.rgb(3,160,98);
    private static Color bodyColor = Color.rgb(209,250,113);

    //Concturtor for Snake Object
    public Snake(){
        //Rectangle erzeugen und befüllen
        for (int i = 0; i < nowSnakeSize; i++) {
            Rectangle bodyPart = new Rectangle();

            //Farbe der Snake festlegen
            if (i == 0){
                bodyPart.setFill(headColor);
            }
            else{
                bodyPart.setFill(bodyColor);
            }

            //Höhe und breite der Rectangels
            bodyPart.setHeight(gridSize);
            bodyPart.setWidth(gridSize);

            bodyPart.setX(gridSize * (nowSnakeSize - 1 ) - i * gridSize+gridSize);

            snakeLength[i] = bodyPart;
        }

    }

    public static Rectangle[] getSnakeLength() {
        return snakeLength;
    }
}
