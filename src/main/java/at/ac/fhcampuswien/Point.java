package at.ac.fhcampuswien;

import java.util.Random;

public class Point {
    /*
    Kontruktor -> auf punkt des Spielfeldes Zugreeifen

    randomPoint is empty constructor
     */

    private int xAchse;
    private int yAchse;

    public Point(int xAchse, int yAchse) {
        this.xAchse = xAchse;
        this.yAchse = yAchse;
    }

    public  Point() {
        Random r = new Random();

        this.xAchse = r.nextInt(481); // between 0 and 480
        this.yAchse = r.nextInt(721); // between 0 and 720
    }

    public int getX() {
        return xAchse;
    }

    public int getY() {
        return yAchse;
    }

    public static void main(String[] args) {

        Point r = new Point();

        System.out.println(r.getX());
        System.out.println(r.getY());

    }

}
