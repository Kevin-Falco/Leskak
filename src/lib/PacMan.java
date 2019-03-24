package lib;


import config.Action;
import config.Interaction;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class PacMan {
    private static boolean pacmanMovement = false;
    private static int remainingDots = 182;


    public static boolean isPacmanMovement() {
        return pacmanMovement;
    }

    public static void setPacmanMovement(boolean pacmanMovement) {
        PacMan.pacmanMovement = pacmanMovement;
    }

    public static int getRemainingDots() {
        return remainingDots;
    }

    public static void setRemainingDots(int pacmanRemainingDots) {
        PacMan.remainingDots = pacmanRemainingDots;
        if (PacMan.remainingDots == 0){
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Interaction.PACMAN_OUT.getEventHandler());
            KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", KeyCode.P, false, false, false, false) );
        }
    }
}
