package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.Time;
import java.util.Random;

public class Food {
    public static Rectangle food;
    public double foodScale = Snake.scale / 4;
    public int foodX;
    public int foodY;
    public boolean foodNotInSnake = true;
    Random foodGenerator = new Random();
    Random r = new Random();
    String[] foodPaths;

    public int getFoodX() {
        return foodX = (int) (food.getX() - foodScale / 2);
    }

    public int getFoodY() {
        return foodY = (int) (food.getY() - foodScale / 2);
    }

    public void setRandomFood(Pane pane) {

        double randomX;
        double randomY;

        do {
            randomX = r.nextInt(Snake.scale) * App.snake.gridSize + foodScale / 2;
            randomY = r.nextInt(Snake.scale) * App.snake.gridSize + foodScale / 2;
        }
        while (randomX < 0 || randomX >= App.GameSize || randomY < 0 || randomY >= App.GameSize);

        food = new Rectangle(App.snake.gridSize - 2, App.snake.gridSize - 2);
        foodPaths = new String[]{"/Burger.png", "/Fish.png", "/Pizza.png", "/apple.png", "/banana.png", "/mouse.png", "/shroom.png"};
        int randomIndex = foodGenerator.nextInt(foodPaths.length);
        Image foodImg = new Image(foodPaths[randomIndex]);
        ImagePattern imagePattern = new ImagePattern(foodImg);
        food.setFill(imagePattern);
        ImageView imageView = new ImageView(foodImg);
        food.setX(randomX);
        food.setY(randomY);
        pane.getChildren().addAll(food);
    }

    public void deleteFood(Pane pane) {
        pane.getChildren().remove(food);
        this.setRandomFood(App.root);

        //Makes sure that no food spawns inside the snake
        do {
            for (int i = 0; i < App.snake.getSnakeLengthArr().length; i++) {
                if (App.snake.getSnakeLengthArr()[i].getX() == this.getFoodX() &&
                        App.snake.getSnakeLengthArr()[i].getY() == this.getFoodY()) {
                    //System.out.println("YES");
                    pane.getChildren().remove(food);
                    this.setRandomFood(App.root);
                    if (!foodNotInSnake)
                        foodNotInSnake = true;
                    break;
                }
                foodNotInSnake = false;
            }
        } while (foodNotInSnake);
        foodNotInSnake = true;
    }
}





