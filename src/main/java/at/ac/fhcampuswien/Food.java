package at.ac.fhcampuswien;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Food {
    protected static Rectangle food;
    private final double FOOD_SCALE = Snake.scale / 4;
    protected int foodX;
    protected int foodY;
    private boolean foodNotInSnake = true;
    protected static boolean spawnedFood = false;
    Random r = new Random();
    String[] foodPaths;

    public int getFoodX() {
        return foodX = (int) (food.getX() - FOOD_SCALE / 2);
    }

    public int getFoodY() {
        return foodY = (int) (food.getY() - FOOD_SCALE / 2);
    }

    public void setRandomFood(Pane pane) {

        double randomX;
        double randomY;

        //so food does not spawn outside of GameBoard
        do {
            randomX = r.nextInt(Snake.scale) * App.snake.GRID_SIZE + FOOD_SCALE / 2;
            randomY = r.nextInt(Snake.scale) * App.snake.GRID_SIZE + FOOD_SCALE / 2;
        }
        while (randomX < 0 || randomX >= App.GAME_SIZE || randomY < 0 || randomY >= App.GAME_SIZE);

        //Image for Food
        food = new Rectangle(App.snake.GRID_SIZE - 2, App.snake.GRID_SIZE - 2);
        foodPaths = new String[]{"/Burger.png", "/Fish.png", "/Pizza.png", "/apple.png", "/banana.png", "/mouse.png", "/shroom.png"};
        int randomIndex = r.nextInt(foodPaths.length);
        Image foodImg = new Image(foodPaths[randomIndex]);
        ImagePattern imagePattern = new ImagePattern(foodImg);
        food.setFill(imagePattern);
        food.setX(randomX);
        food.setY(randomY);
        pane.getChildren().addAll(food);
    }

    public void deleteFood(Pane pane) {
        pane.getChildren().remove(food);

        //set new food after old one is deleted
        if (App.score != Snake.scale * Snake.scale - 2) {
            this.setRandomFood(App.root);

            //Makes sure that no food spawns inside the snake
            do {
                for (int i = 0; i < App.snake.getSnakeLengthArr().length; i++) {
                    if (App.snake.getSnakeLengthArr()[i].getX() == this.getFoodX() &&
                            App.snake.getSnakeLengthArr()[i].getY() == this.getFoodY()) {
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
}





