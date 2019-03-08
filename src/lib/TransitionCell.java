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
            if(event.getCode() == this.direction.getKey().getKeyCode()){
                int i = 0;
                int nextMap = 0;
                Pair<Integer, Integer> nextPosition = null;
                for(Map m : MapConfig.getINSTANCE().getMaps()){
                    if(m.equals(Movement.getMap())){
                        switch(this.direction){
                            case UP :
                                //nextMap = MapConfig.getINSTANCE().getMaps().get(MapArrangement.values()[i].getUp() - 1);
                                nextMap = MapArrangement.values()[i].getUp() - 1;
                                nextPosition = new Pair<>(Player.getINSTANCE().getPosition().getKey(),
                                        GameLayout.getINSTANCE().getNbRows() - 1);
                                break;
                            case DOWN :
                                nextMap = MapArrangement.values()[i].getDown() - 1;
                                nextPosition = new Pair<>(Player.getINSTANCE().getPosition().getKey(), 0);
                                break;
                            case RIGHT :
                                nextMap = MapArrangement.values()[i].getRight() - 1;
                                nextPosition = new Pair<>(0, Player.getINSTANCE().getPosition().getValue());
                                break;
                            case LEFT :
                                nextMap = MapArrangement.values()[i].getLeft() - 1;
                                nextPosition = new Pair<>(GameLayout.getINSTANCE().getNbColumns() - 1,
                                        Player.getINSTANCE().getPosition().getValue());
                                break;
                        }
                        MapConfig.getINSTANCE().configMap(nextMap, new Pair<>(nextPosition.getKey(),nextPosition.getValue()), Player.getINSTANCE().getSprite());
                        //nextMap.setupMap(new Pair<>(nextPosition.getKey(),nextPosition.getValue()),Player.getINSTANCE().getSprite());
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
