package at.ac.fhcampuswien;

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

            bodyPart.setX(gridSize * (nowSnakeSize - 1) - i * gridSize);

            snakeLengthArr[i] = bodyPart;
        }
    }

    public Rectangle[] getSnakeLengthArr() {
        return snakeLengthArr;
    }

    public void addBodyPart() {

        this.snakeLengthArr = new Rectangle[snakeLengthArr.length + 1];

        Rectangle bodyPart = new Rectangle();
        bodyPart.setFill(bodyColor);
        bodyPart.setHeight(gridSize - 1);
        bodyPart.setWidth(gridSize - 1);

        for (int i = 0; i < snakeLengthArr.length-1; i++) {
            this.snakeLengthArr[i] = snakeLengthArr[i];
        }

        bodyPart.setX(gridSize * (nowSnakeSize - 1) - snakeLengthArr.length * gridSize);

        this.snakeLengthArr[snakeLengthArr.length-1] = bodyPart;
    }

    public static void main(String[] args) {
        Snake s = new Snake();
        System.out.println(s.getSnakeLengthArr().length);
        s.addBodyPart();
        System.out.println(s.getSnakeLengthArr().length);
    }

}
