package lib;

import config.*;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class TransitionCell extends Cell {
    private Direction direction;
    private EventHandler eventHandler;

    public TransitionCell(ImageView sprite, Pair<Integer, Integer> position, Direction direction) {
        super(sprite, position);
        this.direction = direction;
        this.eventHandler = (EventHandler<KeyEvent>) event -> {
            System.out.println("HEY");
            if(event.getCode() == this.direction.getKey().getKeyCode()){
                System.out.println("LISTEN");
                int i = 0;
                MapConfig nextMap = null;
                Pair<Integer, Integer> nextPosition = null;
                for(MapConfig m : MapConfig.values()){
                    if(m.getMap().equals(Movement.getMap())){
                        switch(this.direction){
                            case UP :
                                nextMap = MapConfig.values()[MapArrangement.values()[i].getUp() - 1];
                                nextPosition = new Pair<>(Player.getINSTANCE().getPosition().getKey(),
                                        GameLayout.getINSTANCE().getNbRows() - 1);
                                break;
                            case DOWN :
                                nextMap = MapConfig.values()[MapArrangement.values()[i].getDown() - 1];
                                nextPosition = new Pair<>(Player.getINSTANCE().getPosition().getKey(), 0);
                                break;
                            case RIGHT :
                                nextMap = MapConfig.values()[MapArrangement.values()[i].getRight() - 1];
                                nextPosition = new Pair<>(0, Player.getINSTANCE().getPosition().getValue());
                                break;
                            case LEFT :
                                nextMap = MapConfig.values()[MapArrangement.values()[i].getLeft() - 1];
                                nextPosition = new Pair<>(GameLayout.getINSTANCE().getNbColumns() - 1,
                                        Player.getINSTANCE().getPosition().getValue());
                                break;
                        }
                        nextMap.setupMap(new Pair<>(nextPosition.getKey(),nextPosition.getValue()),Player.getINSTANCE().getSprite());
                        break;
                    }
                    ++i;
                }
            }
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, this.getEventHandler());
        };
    }

    public Direction getDirection() {
        return direction;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
