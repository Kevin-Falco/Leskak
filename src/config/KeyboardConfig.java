package config;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import lib.*;

/**
 * Enumération des événements attachés aux touches présentes dans Key à part celle de mouvements et celle du pacman qui
 * est un cas particulier.
 */
public enum KeyboardConfig {

    ENTER(Key.ENTER, (EventHandler<KeyEvent>) event -> {
        if(event.getCode() == Key.ENTER.getKeyCode()){
            if(GameLayout.getINSTANCE().getPane().isFocused()){
                boolean isAPNJ = false;
                BlockingCell blockingCellInteract = null;
                Player player = Player.getINSTANCE();
                int col = player.getDirection() == Direction.RIGHT ? player.getPosition().getKey() + 1 :
                        (player.getDirection() == Direction.LEFT ? player.getPosition().getKey() - 1 : player.getPosition().getKey());
                int row = player.getDirection() == Direction.DOWN ? player.getPosition().getValue() + 1 :
                        (player.getDirection() == Direction.UP ? player.getPosition().getValue() - 1 : player.getPosition().getValue());
                while(Movement.getMap().getBlockingCellIterator().hasNext()){
                    BlockingCell blockingCell = Movement.getMap().getBlockingCellIterator().next();
                    if(blockingCell.getPosition().getKey().equals(col) && blockingCell.getPosition().getValue().equals(row)
                            && blockingCell.getInteraction() != null ){
                        Movement.getMap().getBlockingCellIterator().reset();
                        blockingCell.setDirection(player.getDirection().ordinal() %2 == 0 ?
                                Direction.values()[player.getDirection().ordinal() +1] : Direction.values()[player.getDirection().ordinal() - 1] );
                        isAPNJ = true;
                        blockingCellInteract = blockingCell;
                        break;
                    }
                }
                if(isAPNJ){
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, blockingCellInteract.getInteraction().getEventHandler());
                }
            }
            else if(MainLayout.getSCENE().getFocusOwner() instanceof Button){
                ((Button) MainLayout.getSCENE().getFocusOwner()).fire();
            }
        }
    }),
    ESCAPE(Key.ESCAPE, (EventHandler<KeyEvent>) event -> {
        if(event.getCode() == Key.ESCAPE.getKeyCode() && GameLayout.getINSTANCE().getPane().isFocused()){
            MainLayout.getSTAGE().close();
            LauncherLayout.setupLauncher();
        }
    }),
    TELEPORT(Key.TELEPORT, (EventHandler<KeyEvent>) event -> {
        if(event.getCode() == Key.TELEPORT.getKeyCode() && GameLayout.getINSTANCE().getPane().isFocused() && Movement.isStopped()){
            MapConfig.getINSTANCE().configMap(Planet.getPlanetOfMap(Movement.getMap()).getMaps().get(0));
        }
    }),
    CHANGE_SKIN(Key.CHANGE_SKIN, (EventHandler<KeyEvent>) event -> {
        if(event.getCode() == Key.CHANGE_SKIN.getKeyCode() && GameLayout.getINSTANCE().getPane().isFocused() && Movement.isStopped()){
            Player player = Player.getINSTANCE();
            player.setCurrentSkin(Player.getINSTANCE().getNextSkinAvailable());
            Movement.setAnimationSet(AnimationSet.getAnimationSet(player.getCurrentSkin()*4).getStopAnimationSet());
            player.setSprite(Movement.getAnimationSet().getSpriteDirection(player.getDirection()));
        }
    }),
    ;

    /**
     * Touche attachée à chaque événement.
     */
    private Key key;

    /**
     * Evénement attaché à chaque touche.
     */
    private EventHandler<KeyEvent> eventHandler;

    /**
     * Constructeur de KeyboardConfig avec en paramètre la touche et l'événement rattaché.
     * @param key touche où il y aura un événement
     * @param eventHandler événement attaché à la touche
     */
    KeyboardConfig(Key key, EventHandler<KeyEvent> eventHandler) {
        this.key = key;
        this.eventHandler = eventHandler;
    }

    /**
     * Getter de la touche de l'événement.
     * @return Key
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * Getter de l'événement lié à la touche.
     * @return EventHandler
     */
    public EventHandler<KeyEvent> getEventHandler() {
        return this.eventHandler;
    }
}
