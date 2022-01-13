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
        public double foodScale = Snake.scale/4;
        public int foodX;
        public int foodY;
        public boolean foodNotInSnake = true;
        Random r = new Random();

        public Food() { // hier fehlt mir noch die wiederholung der Random food methode
            //momentan spawned es nur neues Food pro new Game - wer ne Idee?
            //random Food choice
                String[] foodPaths = {"/apple.png", "/mouse.png", "/shroom.png"};
                    Random foodGenerator = new Random();
                    int randomIndex = foodGenerator.nextInt(foodPaths.length);
                    Image foodImg = new Image(foodPaths[randomIndex]);
                    food = new Rectangle(App.snake.gridSize - 2, App.snake.gridSize - 2);
                    ImagePattern imagePattern = new ImagePattern(foodImg);
                    food.setFill(imagePattern);
                    ImageView imageView = new ImageView(foodImg);
            //Code for normal rectangles in case the images don't work
            //food = new Rectangle();
            //food.setFill(Color.rgb(35,100,150));
            //food.setHeight(App.snake.gridSize-foodScale);
            //food.setWidth(App.snake.gridSize-foodScale);
        }

        public int getFoodX() {
            return foodX = (int) (food.getX() - foodScale / 2);
        }

        public int getFoodY() {
            return foodY = (int) (food.getY() - foodScale / 2);
        }

        public void setRandomFood(Pane pane){

            double randomX;
            double randomY;

            do {
                randomX = r.nextInt(Snake.scale) * App.snake.gridSize + foodScale/2;
                randomY = r.nextInt(Snake.scale) * App.snake.gridSize + foodScale/2;
            }
            while (randomX < 0 || randomX >= App.GameSize || randomY < 0 || randomY >= App.GameSize );

            food.setX(randomX);
            food.setY(randomY);
            pane.getChildren().addAll(food);
        }

        public void deleteFood(Pane pane) {
            pane.getChildren().remove(food);
            this.setRandomFood(App.root);

            //Makes sure that no food spawns inside the snake
            do{
                for (int i = 0; i < App.snake.getSnakeLengthArr().length; i++) {
                    if (App.snake.getSnakeLengthArr()[i].getX() == this.getFoodX() &&
                            App.snake.getSnakeLengthArr()[i].getY() == this.getFoodY()) {
                        //System.out.println("YES");
                        pane.getChildren().remove(food);
                        this.setRandomFood(App.root);
                        if(!foodNotInSnake)
                            foodNotInSnake = true;
                        break;
                    }
                    foodNotInSnake = false;
                }
            }while(foodNotInSnake);
            foodNotInSnake = true;
        }
    }





