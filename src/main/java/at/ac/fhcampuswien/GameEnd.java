package at.ac.fhcampuswien;

// Erklärung: Ich würde bei jeder Bewegung des Heads der Schlange testen ob sich die Schlange auf ein safes Feld bewegt
// Das setted dann eine Flag safe auf 0 oder 1, dann prüft GameEnd den Zustand dieser Flag und endet oder continued eben
// Kompiliert noch nicht, weil es erst ein Objekt Snake mit der safe Flag braucht (ohne Objekt und nur mit Klasse
// verlangt es dann dass safe static gemacht wird, aber wollen ja dann vom konkreten Objekt prüfen)
public class GameEnd(Snake.safe) {
    if (Snake.safe == 0) {
        System.out.println("Test");

    }




    /*
    1) GameWon
    2) GameOver
     */





}
