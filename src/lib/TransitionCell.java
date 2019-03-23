package lib;

import config.*;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class TransitionCell extends Cell {
    private Direction direction;
    private EventHandler eventHandler;

    public TransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction) {
        super(sprite, position);
        this.direction = direction;
        this.eventHandler = (EventHandler<KeyEvent>) event -> {
            if(event.getCode() == this.direction.getKey().getKeyCode()){
                int i = 0;
                Map nextMap = null;
                Pair<Integer, Integer> nextPosition = null;
                for(Map m : MapConfig.getINSTANCE().getMaps()){
                    if(m.equals(Movement.getMap())){
                        switch(this.direction){
                            case UP :
                                //nextMap = MapConfig.getINSTANCE().getMaps().get(MapArrangement.values()[i].getUp() - 1);
                                nextMap = MapArrangement.values()[i].getUp();
                                nextPosition = new Pair<>(Player.getINSTANCE().getPosition().getKey(),
                                        GameLayout.getINSTANCE().getNbRows() - 1);
                                break;
                            case DOWN :
                                nextMap = MapArrangement.values()[i].getDown();
                                nextPosition = new Pair<>(Player.getINSTANCE().getPosition().getKey(), 0);
                                break;
                            case RIGHT :
                                nextMap = MapArrangement.values()[i].getRight();
                                nextPosition = new Pair<>(0, Player.getINSTANCE().getPosition().getValue());
                                break;
                            case LEFT :
                                nextMap = MapArrangement.values()[i].getLeft();
                                nextPosition = new Pair<>(GameLayout.getINSTANCE().getNbColumns() - 1,
                                        Player.getINSTANCE().getPosition().getValue());
                                break;
                        }
                        Player player = Player.getINSTANCE();

                        //Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
                        Movement.getMap().getGridPane().getChildren().remove(player.getImage());
                        MapConfig.getINSTANCE().configMap(nextMap, new Pair<>(nextPosition.getKey(),nextPosition.getValue()), Player.getINSTANCE().getSprite());

                        //MapConfig.getINSTANCE().movePlayer(nextPosition);

                        //ImageView imageView = (ImageView) Movement.getMap().getGridPane().getChildren().get(Movement.getMap().getGridPane().getChildren().size() -1);
                        //ImageView imageView = player.getImage();
                        //imageView.setImage(new Image(player.getImage().getSpritePath()));
                        //GridPane.setConstraints(player.getImage(), player.getPosition().getKey(), player.getPosition().getValue());


                        break;
                    }
                    ++i;
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, this.getEventHandler());
        };
    }

    public Direction getDirection() {
        return direction;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
