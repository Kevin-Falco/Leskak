package config;


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lib.*;

public enum Interaction {
    PNJ,
    PNJ1,
    PNJ2,
    PNJ3,
    MASTER,
    ROCKET;

    static {
        PNJ.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMooved()){
                Movement.setMooved(false);
                DialogLayout.getINSTANCE().setText("I'M A PNJ");
                System.out.println("I'M A PNJ");
                InventoryConfig inventoryConfig = InventoryConfig.OBJ1;
                Inventory.getINSTANCE().add(inventoryConfig);
            }
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ.getEventHandler());
        });
        PNJ1.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMooved()){
                Movement.setMooved(false);
                DialogLayout.getINSTANCE().setText("Tu ne pourras voir notre maître que si tu possèdes les 3 statues sacrées.");
                if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ4.getImageView()))
                    DialogLayout.getINSTANCE().addButton("Prendre la statue", Action.GIVE_OBJECT4.getEventHandler());
            }
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ1.getEventHandler());
        });
        PNJ2.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMooved()){
                Movement.setMooved(false);
                DialogLayout.getINSTANCE().setText("Tu ne pourras voir notre maître que si tu possèdes les 3 statues sacrées.");
                if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ5.getImageView()))
                    DialogLayout.getINSTANCE().addButton("Prendre la statue", Action.GIVE_OBJECT5.getEventHandler());
            }
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ2.getEventHandler());
        });
        PNJ3.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMooved()){
                Movement.setMooved(false);
                DialogLayout.getINSTANCE().setText("Tu ne pourras voir notre maître que si tu possèdes les 3 statues sacrées.");
                if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ6.getImageView()))
                    DialogLayout.getINSTANCE().addButton("Prendre la statue", Action.GIVE_OBJECT6.getEventHandler());
            }
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ3.getEventHandler());
        });
        MASTER.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMooved()){
                Movement.setMooved(false);
                DialogLayout.getINSTANCE().setText("Coucou je suis le maitre. Et t'es bloqué ici. Lol.");
            }
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.MASTER.getEventHandler());
        });
        ROCKET.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMooved()){
                Movement.setMooved(false);
                DialogLayout.getINSTANCE().setText("I'M A ROCKET");
                DialogLayout.getINSTANCE().addButton("TEST MAP1", Action.TELEPORT_MAP1.getEventHandler());
                DialogLayout.getINSTANCE().addButton("TEST MAP2", Action.TELEPORT_MAP2.getEventHandler());
                DialogLayout.getINSTANCE().addButton("TEST MAP3", Action.TELEPORT_MAP3.getEventHandler());
                if(Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ4.getImageView()) &&
                        Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ5.getImageView()) &&
                                Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ6.getImageView())){
                    DialogLayout.getINSTANCE().addButton("Aller voir le maitre", Action.TELEPORT_MAP4.getEventHandler());
                }
                System.out.println("I'M A ROCKET");
            }
            GameLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.ROCKET.getEventHandler());
        });
    }

    private EventHandler eventHandler;

    Interaction() {}

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
