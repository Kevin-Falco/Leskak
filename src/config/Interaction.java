package config;


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lib.DialogLayout;
import lib.GameLayout;
import lib.Inventory;

public enum Interaction {
    PNJ((EventHandler<KeyEvent>) event -> {
        if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode()){
            DialogLayout.getINSTANCE().setText("I'M A PNJ");
            System.out.println("I'M A PNJ");
            InventoryConfig inventoryConfig = InventoryConfig.OBJ1;
            Inventory.getINSTANCE().add(inventoryConfig);
        }
        Interaction i = Interaction.PNJ;
        GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, i.getEventHandler());
    }),
    PNJ2((EventHandler<KeyEvent>) event -> {
        if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode()){
            DialogLayout.getINSTANCE().setText("I'M A PNJ");
            System.out.println("I'M A PNJ");
            InventoryConfig inventoryConfig = InventoryConfig.OBJ3;
            Inventory.getINSTANCE().add(inventoryConfig);
        }
        Interaction i = Interaction.PNJ2;
        GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, i.getEventHandler());
    });

    private EventHandler eventHandler;

    Interaction(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
