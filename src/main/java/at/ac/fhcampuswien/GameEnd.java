package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Erklärung: Ich würde bei jeder Bewegung des Heads der Schlange testen ob sich die Schlange auf ein safes Feld bewegt
// Das setted dann eine Flag safe auf 0 oder 1, dann prüft GameEnd den Zustand dieser Flag und endet oder continued eben
// Kompiliert noch nicht, weil es erst ein Objekt Snake mit der safe Flag braucht (ohne Objekt und nur mit Klasse
// verlangt es dann dass safe static gemacht wird, aber wollen ja dann vom konkreten Objekt prüfen)

/* public static class GameEnd() {



    @Override
    public void End(Snake.safe){


     if (Snake.safe == 0) {
         EndWindow(Stage stage);
     } else {
         EndWindow();
     }
    }

    // Kopiert aus App Java als Beispiel für neues Window, Spiel soll quasi dieses Window öffnen statt dem Spiel
    // oder über dem Spiel, Nachricht soll sich je nach Sieg oder Niederlage unterscheiden.
    // EndWindow soll später in diese 2 Cases aufgeteilt werden, wenn der Code weiter ist
    @Override
    public void EndWindow(Stage primaryStage) {
        primaryStage.setTitle("Another round?");
        Button button_end = new Button();
        button_end.setText("Click for retry!");
        button_end.setOnAction( (event) -> Platform.exit() );
        Pane root = new StackPane();
        root.getChildren().add(button_end);
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();
    }





    1) GameWon
    2) GameOver






}
*/
