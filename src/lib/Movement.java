package lib;

import config.*;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.util.Pair;



public class Movement {
    private static Map map = new Map();
    private static boolean moved = false;
    private static EventHandler setupEventHandler;
    private static EventHandler automaticEventHandler;
    private static EventHandler backToGame;
    private static Key lastKeyTyped;
    private static Key automaticLastKey;
    private static boolean stoped = true;
    private static boolean directionChanged = false;
    private static boolean lastKeyReleased = true;
    private static AnimationSet animationSet = AnimationSet.PLAYER_STOP;
    private static int delay = 250;

    public static void configPlayerEventHandler(Scene scene) {
        KeyboardConfig k = KeyboardConfig.ENTER;
        KeyboardConfig k1 = KeyboardConfig.ESCAPE;
        KeyboardConfig k2 = KeyboardConfig.TELEPORT;
        Movement.setupEventHandler = Movement.setupMovementEvent();
        Movement.automaticEventHandler = Movement.automaticMovementEvent();

        scene.removeEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        scene.removeEventHandler(KeyEvent.KEY_PRESSED ,Movement.automaticEventHandler);
        scene.addEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        Movement.backToGame = ((EventHandler<KeyEvent>) (key) -> {
            if (key.getCode() == Key.BACK_SPACE.getKeyCode()){
                scene.addEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
                scene.removeEventHandler(KeyEvent.KEY_PRESSED, Movement.backToGame);
            }
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(lastKeyTyped != null && event.getCode().equals(lastKeyTyped.getKeyCode())){
                lastKeyReleased = true;
            }
        });
    }

    public static EventHandler<KeyEvent> automaticMovementEvent(){

        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(Movement.delay));
        pt.setOnFinished(event -> {

            if(directionChanged){
                Movement.automaticLastKey = Movement.lastKeyTyped;
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.automaticLastKey.getKeyCode(), false, false, false, false) );
                directionChanged = false;
                return;
            }

            if(lastKeyReleased){
                animationSet = animationSet.getStopSpriteSet();
                stoped = true;
                return;
            }
            else {
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.automaticLastKey.getKeyCode(), false, false, false, false) );
            }
        });
        return key -> {

            if(!stoped && !Movement.automaticLastKey.equals(Movement.lastKeyTyped)){
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                stoped = false;
                animationSet = AnimationSet.getSpriteSet(AnimationSet.getNbAnim(Math.floorDiv(
                        AnimationSet.getAnimationSetThatHave( Player.getINSTANCE().getSprite()).ordinal(), 4)));
                directionChanged = true;
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                pt.play();
                return;
            }

            animationSet = AnimationSet.getSpriteSet(AnimationSet.getNbAnim(Math.floorDiv(
                    AnimationSet.getAnimationSetThatHave( Player.getINSTANCE().getSprite()).ordinal(), 4)));
            movePlayer(key);
            stoped = false;

            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
            pt.play();
        };
    }

    public static EventHandler<KeyEvent> setupMovementEvent(){
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(10));
        pt.setOnFinished(event -> {
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        });
        return key -> {
            if(!Movement.isMovementKey(key) ) return;
            lastKeyTyped = Key.getKeyofKeyCode(key.getCode());
            if(Movement.automaticLastKey != null && Movement.automaticLastKey.equals(Movement.lastKeyTyped) && !stoped) return;

            if(lastKeyReleased)
                lastKeyReleased = false;

            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
            KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.lastKeyTyped.getKeyCode(), false, false, false, false) );
            pt.play();
        };
    }

    private static void movePlayer(KeyEvent key){
        Player player = Player.getINSTANCE();
        TranslateTransition tt = new TranslateTransition(Duration.millis(Movement.delay));

        boolean isTransitionCell = false;
        TransitionCell transitionCell = null;
        if (isTransitionCell(player.getPosition().getKey(), player.getPosition().getValue())) {
            isTransitionCell = true;
            transitionCell = getTransitionCell(player.getPosition().getKey(), player.getPosition().getValue());
        }
        double x = 0;
        double y = 0;
        if (key.getCode() == Key.UP.getKeyCode()) {
            y = ((player.getPosition().getValue() == 0
                    || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() - 1)) ? 0 : -50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey(),
                    ((player.getPosition().getValue() == 0
                            || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() - 1)) ?
                            player.getPosition().getValue() : player.getPosition().getValue() - 1)));
            player.setDirection(Direction.UP);
            Movement.moved = true;
        }
        if (key.getCode() == Key.DOWN.getKeyCode()) {
            y = (player.getPosition().getValue() == GameLayout.getINSTANCE().getNbRows() - 1
                    || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() + 1) ? 0 : 50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey(),
                    player.getPosition().getValue() == GameLayout.getINSTANCE().getNbRows() - 1
                            || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() + 1) ?
                            player.getPosition().getValue() : player.getPosition().getValue() + 1));
            player.setDirection(Direction.DOWN);
            Movement.moved = true;
        }
        if (key.getCode() == Key.RIGHT.getKeyCode()) {
            x = (player.getPosition().getKey() == GameLayout.getINSTANCE().getNbColumns() - 1
                    || !isAccessibleCell(player.getPosition().getKey() + 1, player.getPosition().getValue()) ? 0 : 50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey() == GameLayout.getINSTANCE().getNbColumns() - 1
                            || !isAccessibleCell(player.getPosition().getKey() + 1, player.getPosition().getValue()) ?
                            player.getPosition().getKey() : player.getPosition().getKey() + 1,
                    player.getPosition().getValue()));
            player.setDirection(Direction.RIGHT);
            Movement.moved = true;
        }
        if (key.getCode() == Key.LEFT.getKeyCode()) {
            x = (player.getPosition().getKey() == 0
                    || !isAccessibleCell(player.getPosition().getKey() - 1, player.getPosition().getValue()) ? 0 : -50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey() == 0
                            || !isAccessibleCell(player.getPosition().getKey() - 1, player.getPosition().getValue()) ?
                            player.getPosition().getKey() : player.getPosition().getKey() - 1,
                    player.getPosition().getValue()));
            player.setDirection(Direction.LEFT);
            Movement.moved = true;
        }
        if (key.getCode() != Key.ENTER.getKeyCode()) {
            DialogLayout.getINSTANCE().removeContent();
        }
        refreshPlayerSprite();
        if(Movement.isMovementKey(key)){
            Movement.automaticLastKey = Key.getKeyofKeyCode(key.getCode());
        }

        if (isTransitionCell && transitionCell.getDirection().equals(player.getDirection())) {
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, transitionCell.getEventHandler());
        } else {

            ImageView imageView = player.getImage();
            tt.setNode(imageView);
            tt.setByX(x);
            tt.setByY(y);
            tt.setInterpolator(Interpolator.LINEAR);

            tt.setOnFinished(event -> {
                if(map.isFogOfWar())
                    map.updateFogOfWar();
            });
            tt.play();
        }
    }

    private static void refreshPlayerSprite(){
        Player player = Player.getINSTANCE();
        switch (player.getDirection()){
            case UP:
                player.setSprite(animationSet.getUp());
                break;
            case DOWN:
                player.setSprite(animationSet.getDown());
                break;
            case RIGHT:
                player.setSprite(animationSet.getRight());
                break;
            case LEFT:
                player.setSprite(animationSet.getLeft());
                break;
        }
    }

    private static boolean isMovementKey(KeyEvent key){
        return key.getCode() == Key.UP.getKeyCode() || key.getCode() == Key.DOWN.getKeyCode() ||
                key.getCode() == Key.RIGHT.getKeyCode() || key.getCode() == Key.LEFT.getKeyCode();
    }

    private static boolean isAccessibleCell(Integer col, Integer row){
        while(Movement.map.getBlockingCellIterator().hasNext()){
            BlockingCell blockingCell = Movement.map.getBlockingCellIterator().next();
            if(blockingCell.getPosition().getKey().equals(col) && blockingCell.getPosition().getValue().equals(row)){
                Movement.map.getBlockingCellIterator().reset();
                return false;
            }
        }
        return true;
    }

    private static boolean isTransitionCell(Integer col, Integer row){
        while(Movement.map.getTransitionCellIterator().hasNext()){
            TransitionCell transitionCell = Movement.map.getTransitionCellIterator().next();
            if(transitionCell.getPosition().getKey().equals(col) && transitionCell.getPosition().getValue().equals(row)){
                Movement.map.getTransitionCellIterator().reset();
                return true;
            }
        }
        return false;
    }

    public static ImageView getSprite(Integer col, Integer row){
        for (Cell cell : Movement.map
             ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                return cell.getImage();
            }
        }
        return null;
    }

    private static TransitionCell getTransitionCell(Integer col, Integer row){
        for (Cell cell : Movement.map
        ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                if(cell instanceof TransitionCell)
                    return (TransitionCell) cell;
            }
        }
        return null;
    }

    public static void removeMovement(){
        GameLayout.getINSTANCE().getPane().setFocusTraversable(false);
        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.backToGame);
    }

    public static void resumeMovement(){
        GameLayout.getINSTANCE().getPane().setFocusTraversable(true);
        GameLayout.getINSTANCE().getPane().requestFocus();
        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.backToGame);
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        Movement.map = map;
    }

    public static boolean isMoved() {
        return moved;
    }

    public static void setMoved(boolean moved) {
        Movement.moved = moved;
    }

    public static AnimationSet getAnimationSet() {
        return animationSet;
    }

    public static void setAnimationSet(AnimationSet animationSet) {
        Movement.animationSet = animationSet;
    }

    public static boolean isStoped() {
        return stoped;
    }
}
