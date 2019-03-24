package lib;


import config.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;


public class PacMan {
    private static boolean pacmanMovement = false;
    private static int remainingDots = 182;
    private static Ghost ghost1 = new Ghost(new Pair<>(14, 5), AnimationSet.BLACK_CAT);
    private static Ghost ghost2 = new Ghost(new Pair<>(15, 5), AnimationSet.GREY_CAT);
    private static Ghost ghost3 = new Ghost(new Pair<>(16, 5), AnimationSet.WHITE_CAT);
    private static Ghost ghost4 = new Ghost(new Pair<>(17, 5), AnimationSet.FOX);
    private static Map map = MapConfig.getINSTANCE().getMaps().get(10);

    public static boolean isPacmanMovement() {
        return pacmanMovement;
    }

    public static void setPacmanMovement(boolean pacmanMovement) {
        if(pacmanMovement == true){

        }
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

    private static class Ghost {
        private Pair<Integer, Integer> position;
        private AnimationSet animationSet;
        private Direction direction;
        private ImageView image = new ImageView();

        public Ghost(Pair<Integer, Integer> position, AnimationSet animationSet) {
            this.position = position;
            this.animationSet = animationSet;
            this.direction = Direction.DOWN;
            image.setImage(new Image(animationSet.getSpriteDirection(this.direction).getSpritePath()));
        }
    }
}
