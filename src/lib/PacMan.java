package lib;


import config.*;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


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
        if(pacmanMovement){

            map.add(ghost1.cellInMap);
            map.add(ghost2.cellInMap);
            map.add(ghost3.cellInMap);
            map.add(ghost4.cellInMap);
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

    public static void moveGhosts(){
        ghost1.move();
        ghost2.move();
        ghost3.move();
        ghost4.move();
    }

    private static class Ghost {
        private AnimationSet animationSet;
        private Cell cellInMap;

        public Ghost(Pair<Integer, Integer> position, AnimationSet animationSet) {
            this.animationSet = animationSet;
            this.cellInMap = new Cell( this.animationSet.getDown(), position);
        }

        public void move(){
            Pair<Integer, Integer> position = this.cellInMap.getPosition();
            Cell upCell = MapConfig.getLastCell(10, position.getKey(), position.getValue() - 1);
            Cell downCell = MapConfig.getLastCell(10, position.getKey() ,  position.getValue() + 1);
            Cell rightCell = MapConfig.getLastCell(10, position.getKey() + 1, position.getValue());
            Cell leftCell = MapConfig.getLastCell(10, position.getKey() - 1, position.getValue());

            boolean canMoveUp = upCell != null && isGoodSprite(upCell.getSprite());
            boolean canMoveDown = downCell != null && isGoodSprite(downCell.getSprite());
            boolean canMoveRight = rightCell != null && isGoodSprite(rightCell.getSprite());
            boolean canMoveLeft = leftCell != null && isGoodSprite(leftCell.getSprite());

            if(!canMoveUp && !canMoveDown && !canMoveRight && !canMoveLeft)
                return;

            Sprite sprite = null;
            TranslateTransition tt = new TranslateTransition(Duration.millis(Movement.getDelay()));

            tt.setInterpolator(Interpolator.LINEAR);
            double x = 0;
            double y = 0;

            switch (randomMove(canMoveUp, canMoveDown, canMoveRight, canMoveLeft)){
                case UP:
                    y = -50;
                    sprite = animationSet.getUp();
                    position = upCell.getPosition();
                    cellInMap.setPosition(position);
                    break;
                case DOWN:
                    y = 50;
                    sprite = animationSet.getDown();
                    position = downCell.getPosition();
                    cellInMap.setPosition(position);
                    break;
                case RIGHT:
                    x = 50;
                    sprite = animationSet.getRight();
                    position = rightCell.getPosition();
                    cellInMap.setPosition(position);
                    break;
                case LEFT:
                    x = -50;
                    sprite = animationSet.getLeft();
                    position =leftCell.getPosition();
                    cellInMap.setPosition(position);
            }
            this.cellInMap.setSprite(sprite);
            tt.setNode(this.cellInMap.getImage());
            tt.setByX(x);
            tt.setByY(y);
            tt.play();
        }

        private Direction randomMove(boolean canMoveUp, boolean canMoveDown, boolean canMoveRight, boolean canMoveLeft){
            ArrayList<Boolean> arrayList = new ArrayList(Arrays.asList(canMoveUp, canMoveDown, canMoveRight, canMoveLeft));
            ArrayList<Direction> arrayList2 = new ArrayList(Arrays.asList(Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT));
            ArrayList<Integer> arrayList3 = new ArrayList();
            for(int i = 0; i < arrayList.size(); ++i){
                if(arrayList.get(i)){
                    arrayList3.add(i);
                }
            }
            Random random = new Random();
            return arrayList2.get( arrayList3.get(random.nextInt(arrayList3.size())));
        }

        private boolean isGoodSprite(Sprite sprite){
            return sprite.equals(Sprite.HERB) || sprite.equals(Sprite.SAND) || sprite.equals(Player.getINSTANCE().getSprite());
        }

    }
}
