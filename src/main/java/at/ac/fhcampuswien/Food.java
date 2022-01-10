package at.ac.fhcampuswien;

import javafx.scene.image.Image;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

    public class Food {
        public static Rectangle food;
        public double foodScale = Snake.scale/4;
        public int foodX;
        public int foodY;
        Random r = new Random();

        public Food() {
            food = new Rectangle();
            food.setFill(Color.rgb(35,100,150));
            food.setHeight(App.snake.gridSize-foodScale);
            food.setWidth(App.snake.gridSize-foodScale);
        }

        public int getFoodX() {
            return foodX = (int) (food.getX() + foodScale - foodScale - foodScale / 2);
        }

        public int getFoodY() {
            return foodY = (int) (food.getY() + foodScale - foodScale - foodScale / 2);
        }

        public void setRandomFood(Pane pane){

            double randomX;
            double randomY;

            do {
                randomX = r.nextInt(Snake.scale) * App.snake.gridSize + App.snake.gridSize + foodScale/2;
                randomY = r.nextInt(Snake.scale) * App.snake.gridSize + App.snake.gridSize + foodScale/2;
            }
            while (randomX < 0 || randomX > App.GameSize || randomY < 0 || randomY > App.GameSize );

            food.setX(randomX);
            System.out.println(randomX);
            food.setY(randomY);
            System.out.println(randomY);
            pane.getChildren().addAll(food);
        }

        public void deleteFood(Pane pane){
            pane.getChildren().remove(food);
            this.setRandomFood(App.root);
        }

    }





