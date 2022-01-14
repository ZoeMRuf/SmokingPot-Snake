package at.ac.fhcampuswien;

public class Win {

    int outOfGame = App.GameSize;

    public static boolean isGameWin = false;

    public static boolean GameWin() {

        if (App.score == 324-1) {
            isGameWin = true;
        }
        return isGameWin;
    }
}
