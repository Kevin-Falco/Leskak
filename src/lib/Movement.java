package lib;

import config.Direction;
import config.Key;
import config.KeyboardConfig;
import config.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class Movement {
    private static Map map = new Map();
    private static ImageView lastSprite;
    private static boolean mooved = false;

    public static void configPlayerEventHandler(Scene scene) {
        KeyboardConfig k = KeyboardConfig.ENTER;
        Player player = Player.getINSTANCE();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            boolean isTransitionCell = false;
            TransitionCell transitionCell = null;
            if(isTransitionCell(player.getPosition().getKey(), player.getPosition().getValue())){
                isTransitionCell = true;
                transitionCell = (TransitionCell) getCell(player.getPosition().getKey(), player.getPosition().getValue());
            }
            if (lastSprite != null){
                lastSprite.setVisible(true);
            }
            if (key.getCode() == Key.UP.getKeyCode()) {
                player.setPosition(new Pair<>(
                        player.getPosition().getKey(),
                        ((player.getPosition().getValue() == 0
                                || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() -1)) ?
                                player.getPosition().getValue() :  player.getPosition().getValue() - 1)));
                player.setSprite(Sprite.UP);
                player.setDirection(Direction.UP);
                Movement.mooved = true;
            }
            if (key.getCode() == Key.DOWN.getKeyCode()) {
                player.setPosition(new Pair<>(
                        player.getPosition().getKey(),
                        player.getPosition().getValue() == GameLayout.getINSTANCE().getNbRows() - 1
                                || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() +1) ?
                                player.getPosition().getValue() :  player.getPosition().getValue() + 1));
                player.setSprite(Sprite.DOWN);
                player.setDirection(Direction.DOWN);
                Movement.mooved = true;
            }
            if (key.getCode() == Key.RIGHT.getKeyCode()) {
                player.setPosition(new Pair<>(
                        player.getPosition().getKey() == GameLayout.getINSTANCE().getNbColumns() - 1
                                || !isAccessibleCell(player.getPosition().getKey() +1, player.getPosition().getValue()) ?
                                player.getPosition().getKey() :  player.getPosition().getKey() + 1,
                        player.getPosition().getValue()));
                player.setSprite(Sprite.RIGHT);
                player.setDirection(Direction.RIGHT);
                Movement.mooved = true;
            }
            if (key.getCode() == Key.LEFT.getKeyCode()) {
                player.setPosition(new Pair<>(
                        player.getPosition().getKey() == 0
                                || !isAccessibleCell(player.getPosition().getKey() -1, player.getPosition().getValue()) ?
                                player.getPosition().getKey() :  player.getPosition().getKey() - 1,
                        player.getPosition().getValue()));
              player.setSprite(Sprite.LEFT);
                player.setDirection(Direction.LEFT);
                Movement.mooved = true;
            }
            if(key.getCode() != Key.ENTER.getKeyCode()){
                DialogLayout.getINSTANCE().removeContent();
            }

            if(isTransitionCell && transitionCell.getDirection().equals(player.getDirection())){
                GameLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, transitionCell.getEventHandler());
            }
            else{
                lastSprite = Movement.getSprite(player.getPosition().getKey(), player.getPosition().getValue());

                if (lastSprite != null){
                    lastSprite.setVisible(false);
                }

                ImageView imageView = (ImageView) Movement.map.getGridPane().getChildren().get(Movement.map.getGridPane().getChildren().size() -1);
                imageView.setImage(new Image(player.getSprite().getSpritePath()));
                GridPane.setConstraints(imageView, player.getPosition().getKey(), player.getPosition().getValue());
            }
        });
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

    public static boolean isMooved() {
        return mooved;
    }

    public static void setMooved(boolean mooved) {
        Movement.mooved = mooved;
    }
}
