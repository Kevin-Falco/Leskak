package config;


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lib.DialogLayout;
import lib.GameLayout;
import lib.Inventory;

public enum Interaction {
    PNJ,
    PNJ2;

    static {
        PNJ.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode()){
                DialogLayout.getINSTANCE().setText("I'M A PNJ");
                System.out.println("I'M A PNJ");
                InventoryConfig inventoryConfig = InventoryConfig.OBJ1;
                Inventory.getINSTANCE().add(inventoryConfig);
            }
            Interaction i = Interaction.PNJ;
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, i.getEventHandler());
        });
        PNJ2.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode()){
                DialogLayout.getINSTANCE().setText("I'M A PNJ");
                System.out.println("I'M A PNJ");
                InventoryConfig inventoryConfig = InventoryConfig.OBJ3;
                Inventory.getINSTANCE().add(inventoryConfig);
            }
            Interaction i = Interaction.PNJ2;
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, i.getEventHandler());
        });
    }

    private EventHandler eventHandler;

    Interaction() {}

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
