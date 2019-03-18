package lib;

import config.Direction;
import config.Key;
import config.KeyboardConfig;
import config.Sprite;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.util.Pair;



public class Movement {
    private static Map map = new Map();
    private static ImageView lastSprite;
    private static boolean moved = false;
    private static EventHandler setupEventHandler;
    private static EventHandler automaticEventHandler;
    private static EventHandler backToGame;
    private static Key lastKeyTyped;
    private static Key automaticLastKey;
    private static boolean stoped = true;
    private static boolean directionChanged = false;
    private static boolean lastKeyReleased = true;
    private static int delay = 200;

    public static void configPlayerEventHandler(Scene scene) {
        KeyboardConfig k = KeyboardConfig.ENTER;
        KeyboardConfig k1 = KeyboardConfig.ESCAPE;
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
                directionChanged = true;
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                pt.play();
                return;
            }

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
            transitionCell = (TransitionCell) getCell(player.getPosition().getKey(), player.getPosition().getValue());
        }
        if (lastSprite != null) {
            lastSprite.setVisible(true);
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
            player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getUP());
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
            player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getDOWN());
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
            player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getRIGHT());
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
            player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getLEFT());
            player.setDirection(Direction.LEFT);
            Movement.moved = true;
        }
        if (key.getCode() != Key.ENTER.getKeyCode()) {
            DialogLayout.getINSTANCE().removeContent();
        }

        if(Movement.isMovementKey(key)){
            Movement.automaticLastKey = Key.getKeyofKeyCode(key.getCode());
        }

        if (isTransitionCell && transitionCell.getDirection().equals(player.getDirection())) {
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, transitionCell.getEventHandler());
        } else {
            lastSprite = Movement.getSprite(player.getPosition().getKey(), player.getPosition().getValue());


            ImageView imageView = (ImageView) Movement.map.getGridPane().getChildren().get(Movement.map.getGridPane().getChildren().size() - 1);
            imageView.setImage(new Image(player.getSprite().getSpritePath()));
            tt.setNode(imageView);
            tt.setByX(x);
            tt.setByY(y);
            tt.setInterpolator(Interpolator.LINEAR);

            tt.setOnFinished(event -> {
                if (lastSprite != null) {
                    //lastSprite.setVisible(false);
                }
            });

            tt.play();
            //GridPane.setConstraints(imageView, player.getPosition().getKey(), player.getPosition().getValue());
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
                return cell.getSprite();
            }
        }
        return null;
    }

    private static Cell getCell(Integer col, Integer row){
        for (Cell cell : Movement.map
        ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                return cell;
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

    public static ImageView getLastSprite() {
        return lastSprite;
    }

    public static void setLastSprite(ImageView lastSprite) {
        Movement.lastSprite = lastSprite;
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

}
