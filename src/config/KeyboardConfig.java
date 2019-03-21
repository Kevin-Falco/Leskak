package config;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import lib.*;


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
        if(event.getCode() == Key.TELEPORT.getKeyCode() && GameLayout.getINSTANCE().getPane().isFocused()){
            MapConfig.getINSTANCE().configMap(0);
        }
    }),
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
