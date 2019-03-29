package lib;

import config.*;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Représente une cellule de transition, qui permet au joueur de changer de carte vers une nouvelle carte, de la même planète,
 * dont la position a été définie dans MapArrangement.
 */
public class TransitionCell extends Cell {

    /**
     * Direction dans laquelle doit aller Leskak pour activer la cellule de transition.
     */
    private Direction direction;

    /**
     * Evénement rattaché au fait que Leskak choisisse de changer de carte sur la même planète.
     */
    private EventHandler<KeyEvent> eventHandler;

    /**
     * Constructeur de la cellule de transition avec en paramètres, l'apparence de la cellule, la direction pour l'activer
     * et sa position sur la carte.
     * @param sprite apparence de la cellule
     * @param position position sur la carte de la cellule
     * @param direction direction dans laquelle Leskak doit aller pour l'activer
     */
    public TransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction) {
        super(sprite, position);
        this.direction = direction;
        this.eventHandler = event -> {
            if(event.getCode() == this.direction.getKey().getKeyCode()){
                int i = 0;
                Map nextMap = null;
                Pair<Integer, Integer> nextPosition = null;
                for(Map m : MapConfig.getINSTANCE().getMaps()){
                    if(m.equals(Movement.getMap())){
                        switch(this.direction){
                            case UP :
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

                        Movement.getMap().getGridPane().getChildren().remove(player.getImage());
                        MapConfig.getINSTANCE().configMap(nextMap, new Pair<>(nextPosition.getKey(),nextPosition.getValue()));

                        break;
                    }
                    ++i;
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, this.getEventHandler());
        };
    }

    /**
     * Getter de la direction de la cellule de transition qui doit être effectuée par Leskak.
     * @return Direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Getter de l'événement attaché à la cellule de transition.
     * @return EventHandler
     */
    public EventHandler<KeyEvent> getEventHandler() {
        return this.eventHandler;
    }
}
