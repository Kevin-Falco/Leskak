package config;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lib.*;

public enum KeyboardConfig {

    ENTER(Key.ENTER, (EventHandler<KeyEvent>) event -> {
        if(event.getCode() == Key.ENTER.getKeyCode()){
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
                    isAPNJ = true;
                    blockingCellInteract = blockingCell;
                    break;
                }
            }
            if(isAPNJ){
                GameLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, blockingCellInteract.getInteraction().getEventHandler());
            }
            else{
                DialogLayout.getINSTANCE().setText("YOUPIIWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWI");
                System.out.println("YOUPI");
            }
        }
    });

    private Key key;
    private EventHandler eventHandler;

    KeyboardConfig(Key key, EventHandler eventHandler) {
        this.key = key;
        this.eventHandler = eventHandler;
        GameLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, this.eventHandler);
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
        this.eventHandler = (EventHandler<KeyEvent>) event -> {
            if(event.getCode() == key.getKeyCode()){
                System.out.println("YOUPI");
            }
        };
        GameLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, this.eventHandler);
    }
}
