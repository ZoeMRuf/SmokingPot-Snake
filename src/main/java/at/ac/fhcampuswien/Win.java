package at.ac.fhcampuswien;

public class Win {
    public static boolean isGameWin = false;

    //Checks if the Game is Won
    public static boolean GameWin() {
        if (App.score == 324-1) {
            isGameWin = true;
        }
        return isGameWin;
    }
}
