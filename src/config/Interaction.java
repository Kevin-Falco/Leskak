package config;


import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lib.*;

public enum Interaction {
    PNJ,
    PNJ1,
    PNJ2,
    PNJ3,
    MASTER,
    ROCKET,
    RETURN_GAME,
    TRANSITION,
    MOVEMENT;

    static {
        PNJ.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("V'la des sous. Enjoy");
                DialogLayout.getINSTANCE().addMoney(50);
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ.getEventHandler());
        });
        PNJ1.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("Tu ne pourras voir notre maître que si tu possèdes les 3 statues sacrées.");
                if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ4.getImageView()))
                    DialogLayout.getINSTANCE().addButton("Prendre la statue", Action.GIVE_OBJECT4.getEventHandler());
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ1.getEventHandler());
        });
        PNJ2.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("Tu ne pourras voir notre maître que si tu possèdes les 3 statues sacrées.");
                if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ5.getImageView()))
                    DialogLayout.getINSTANCE().addButton("Prendre la statue", Action.GIVE_OBJECT5.getEventHandler());
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ2.getEventHandler());
        });
        PNJ3.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("Tu ne pourras voir notre maître que si tu possèdes les 3 statues sacrées.");
                if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ6.getImageView()))
                    DialogLayout.getINSTANCE().addButton("Prendre la statue", Action.GIVE_OBJECT6.getEventHandler());
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ3.getEventHandler());
        });
        MASTER.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("Coucou je suis le maitre. Et t'es bloqué ici. Lol.");
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CinematicConfig.TEST3.getEventHandler());
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.MASTER.getEventHandler());
        });
        ROCKET.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("I'M A ROCKET");
                DialogLayout.getINSTANCE().addButton("TEST MAP1", Action.TELEPORT_MAP1.getEventHandler());
                DialogLayout.getINSTANCE().addButton("TEST MAP2", Action.TELEPORT_MAP2.getEventHandler());
                DialogLayout.getINSTANCE().addButton("TEST MAP3", Action.TELEPORT_MAP3.getEventHandler());
                if(Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ4.getImageView()) &&
                        Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ5.getImageView()) &&
                                Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ6.getImageView())){
                    DialogLayout.getINSTANCE().addButton("Aller voir le maitre", Action.TELEPORT_MAP4.getEventHandler());
                }
                DialogLayout.getINSTANCE().addButton("Aller voir le maitre", Action.TELEPORT_MAP4.getEventHandler());
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.ROCKET.getEventHandler());
        });
        RETURN_GAME.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()){
                MainLayout.getSCENE().setRoot(MainLayout.getINSTANCE().getGridPane());
                Movement.resumeMovement();
            }
        });

        MOVEMENT.eventHandler = Movement.setupMovementEvent();
    }

    private EventHandler eventHandler;

    Interaction() {}

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
