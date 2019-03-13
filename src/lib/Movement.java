package lib;

import config.Direction;
import config.Key;
import config.KeyboardConfig;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.util.Pair;
import sample.Main;


public class Movement {
    private static Map map = new Map();
    private static ImageView lastSprite;
    private static boolean moved = false;
    private static EventHandler setupEventHandler;
    private static EventHandler automaticEventHandler;
    private static EventHandler backToGame;
    private static Key lastKeyTyped;
    private static boolean newMovement = false;
    private static int delay = 150;

    public static void configPlayerEventHandler(Scene scene) {
        KeyboardConfig k = KeyboardConfig.ENTER;
        KeyboardConfig k1 = KeyboardConfig.ESCAPE;
        Movement.setupEventHandler = Movement.setupMovementEvent();
        Movement.automaticEventHandler = Movement.automaticMovementEvent();
        scene.addEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        Movement.backToGame = ((EventHandler<KeyEvent>) (key) -> {
            if (key.getCode() == Key.BACK_SPACE.getKeyCode()){
                scene.addEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
                scene.removeEventHandler(KeyEvent.KEY_PRESSED, Movement.backToGame);
            }
        } );
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode().equals(lastKeyTyped.getKeyCode())){
                lastKeyTyped = Key.SPACE;
            }
        });
    }

    public static EventHandler<KeyEvent> automaticMovementEvent(){
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(Movement.delay));
        pt.setOnFinished(event -> {
            if(Movement.isNewMovement()){
                System.out.println("NEW BLOCKED");
                return;
            }
            System.out.println( "AUTO : " + Movement.lastKeyTyped.name());
            //MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);

            if(lastKeyTyped.equals(Key.SPACE))
                return;
            else{
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.lastKeyTyped.getKeyCode(), false, false, false, false) );

            }
        });
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                Player player = Player.getINSTANCE();
                Movement.newMovement = false;

                boolean isTransitionCell = false;
                TransitionCell transitionCell = null;
                if (isTransitionCell(player.getPosition().getKey(), player.getPosition().getValue())) {
                    isTransitionCell = true;
                    transitionCell = (TransitionCell) getCell(player.getPosition().getKey(), player.getPosition().getValue());
                }
                if (lastSprite != null) {
                    lastSprite.setVisible(true);
                }
                if (key.getCode() == Key.UP.getKeyCode()) {
                    player.setPosition(new Pair<>(
                            player.getPosition().getKey(),
                            ((player.getPosition().getValue() == 0
                                    || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() - 1)) ?
                                    player.getPosition().getValue() : player.getPosition().getValue() - 1)));
                    player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getUP());
                    player.setDirection(Direction.UP);
                    Movement.moved = true;
                    Movement.lastKeyTyped = Key.UP;
                }
                if (key.getCode() == Key.DOWN.getKeyCode()) {
                    player.setPosition(new Pair<>(
                            player.getPosition().getKey(),
                            player.getPosition().getValue() == GameLayout.getINSTANCE().getNbRows() - 1
                                    || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() + 1) ?
                                    player.getPosition().getValue() : player.getPosition().getValue() + 1));
                    player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getDOWN());
                    player.setDirection(Direction.DOWN);
                    Movement.moved = true;
                    Movement.lastKeyTyped = Key.DOWN;
                }
                if (key.getCode() == Key.RIGHT.getKeyCode()) {
                    player.setPosition(new Pair<>(
                            player.getPosition().getKey() == GameLayout.getINSTANCE().getNbColumns() - 1
                                    || !isAccessibleCell(player.getPosition().getKey() + 1, player.getPosition().getValue()) ?
                                    player.getPosition().getKey() : player.getPosition().getKey() + 1,
                            player.getPosition().getValue()));
                    player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getRIGHT());
                    player.setDirection(Direction.RIGHT);
                    Movement.moved = true;
                    Movement.lastKeyTyped = Key.RIGHT;
                }
                if (key.getCode() == Key.LEFT.getKeyCode()) {
                    player.setPosition(new Pair<>(
                            player.getPosition().getKey() == 0
                                    || !isAccessibleCell(player.getPosition().getKey() - 1, player.getPosition().getValue()) ?
                                    player.getPosition().getKey() : player.getPosition().getKey() - 1,
                            player.getPosition().getValue()));
                    player.setSprite(getCell(player.getPosition().getKey(), player.getPosition().getValue()).getPlayerSprite().getLEFT());
                    player.setDirection(Direction.LEFT);
                    Movement.moved = true;
                    Movement.lastKeyTyped = Key.LEFT;
                }
                if (key.getCode() != Key.ENTER.getKeyCode()) {
                    DialogLayout.getINSTANCE().removeContent();
                }

                if (isTransitionCell && transitionCell.getDirection().equals(player.getDirection())) {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, transitionCell.getEventHandler());
                } else {
                    lastSprite = Movement.getSprite(player.getPosition().getKey(), player.getPosition().getValue());

                    if (lastSprite != null) {
                        lastSprite.setVisible(false);
                    }

                    ImageView imageView = (ImageView) Movement.map.getGridPane().getChildren().get(Movement.map.getGridPane().getChildren().size() - 1);
                    imageView.setImage(new Image(player.getSprite().getSpritePath()));
                    GridPane.setConstraints(imageView, player.getPosition().getKey(), player.getPosition().getValue());
                }

                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, this);

                pt.play();
            }
        };
    }

    public static EventHandler<KeyEvent> setupMovementEvent(){
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(Movement.delay));
        pt.setOnFinished(event -> {
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        });
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if(lastKeyTyped != null){
                    if(key.getCode().equals(lastKeyTyped.getKeyCode())){
                        return;
                    }
                    else{
                        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
                        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                        lastKeyTyped = Key.getKeyofKeyCode(key.getCode());
                        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
                    }
                }
                Movement.newMovement = true;
                System.out.println("NEW");

                if (key.getCode() == Key.UP.getKeyCode()) {
                    Movement.lastKeyTyped = Key.UP;
                }
                if (key.getCode() == Key.DOWN.getKeyCode()) {
                    Movement.lastKeyTyped = Key.DOWN;
                }
                if (key.getCode() == Key.RIGHT.getKeyCode()) {
                    Movement.lastKeyTyped = Key.RIGHT;
                }
                if (key.getCode() == Key.LEFT.getKeyCode()) {
                    Movement.lastKeyTyped = Key.LEFT;
                }

                if(lastKeyTyped == null) return;
                System.out.println("SETUP : " + Movement.lastKeyTyped.name());
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticMovementEvent());
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.lastKeyTyped.getKeyCode(), false, false, false, false) );

                if(lastKeyTyped.equals(Key.SPACE)){
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);

                }
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
                pt.play();
            }
        };
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
        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.backToGame);
    }

    public static void resumeMovement(){
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

    public static boolean isNewMovement() {
        return newMovement;
    }
}
