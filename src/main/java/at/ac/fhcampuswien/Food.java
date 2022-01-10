package at.ac.fhcampuswien;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Food {

    private Rectangle food;

    Random r = new Random();

    public Food() {
        food.setFill(Color.rgb(255,0,150));
        food.setHeight(App.snake.gridSize-5);
        food.setWidth(App.snake.gridSize-5);

    }

    public void setRandomFood(){
        food.setX(r.nextInt(App.snake.scale)*App.snake.gridSize+App.snake.gridSize);
        food.setY(r.nextInt(App.snake.scale)*App.snake.gridSize+App.snake.gridSize);
        App.root.getChildren().addAll(food);
    }
}
