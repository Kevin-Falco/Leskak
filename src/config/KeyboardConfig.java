package config;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lib.*;
import sample.Main;

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
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, blockingCellInteract.getInteraction().getEventHandler());
            }
            else{
                DialogLayout.getINSTANCE().setText("YOUPI");
            }
        }
    }),
    ESCAPE(Key.ESCAPE, (EventHandler<KeyEvent>) event -> {
        if(event.getCode() == Key.ESCAPE.getKeyCode() && GameLayout.getINSTANCE().getPane().isFocused()){
            MainLayout.getSTAGE().close();
            LauncherLayout.setupLauncher();
        }
    })
    ;

    private Key key;

    KeyboardConfig(Key key, EventHandler eventHandler) {
        this.key = key;
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
    }

    public Key getKey() {
        return key;
    }
}
