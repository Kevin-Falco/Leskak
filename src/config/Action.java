package config;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import lib.*;

public enum Action {
    TELEPORT_MAP1,
    TELEPORT_MAP2,
    TELEPORT_MAP3,
    TELEPORT_MAP4,
    GIVE_OBJECT1,
    GIVE_OBJECT2,
    GIVE_OBJECT3,
    GIVE_OBJECT4,
    GIVE_OBJECT5,
    GIVE_OBJECT6,
    RETURN_OBJECT1,
    TEST1,
    RETURN,
    ;

    static{
        TELEPORT_MAP1.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(0);
            DialogLayout.getINSTANCE().removeContent();
            Node node = (Node) action.getSource();
            node.setFocusTraversable(false);
            Movement.resumeMovement();
        });
        TELEPORT_MAP2.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(1);
            DialogLayout.getINSTANCE().removeContent();
            Movement.resumeMovement();
        });
        TELEPORT_MAP3.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(2);
            DialogLayout.getINSTANCE().removeContent();
            Movement.resumeMovement();
        });
        TELEPORT_MAP4.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(3);
            DialogLayout.getINSTANCE().removeContent();
            Movement.resumeMovement();
        });
        GIVE_OBJECT1.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ1);
            Interaction.BUSH1.setInteractionDone(true);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            Movement.setMoved(true);
        });
        GIVE_OBJECT2.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ2);
            Interaction.PNJ4.setInteractionDone(true);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            Movement.setMoved(true);
        });
        GIVE_OBJECT3.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ3);
            ((Node) action.getSource()).setVisible(false);
            Movement.resumeMovement();
        });
        GIVE_OBJECT4.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ4);
            ((Node) action.getSource()).setVisible(false);
            Movement.resumeMovement();
        });
        GIVE_OBJECT5.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ5);
            ((Node) action.getSource()).setVisible(false);
            Movement.resumeMovement();
        });
        GIVE_OBJECT6.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ6);
            ((Node) action.getSource()).setVisible(false);
            Movement.resumeMovement();
        });
        RETURN_OBJECT1.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            //MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CinematicConfig.TEST1.getEventHandler());
            //KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Key.SPACE.getKeyCode(), false, false, false, false) );
            if(!MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6))){
                MapConfig.getINSTANCE().movePlayer(new Pair<>(21, 6));
                MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6));
            }
            Interaction.PNJ3.setInteractionDone(true);
            Inventory.getINSTANCE().remove(InventoryConfig.OBJ1);
            DialogLayout.getINSTANCE().removeContent();
            Movement.resumeMovement();
        });
        TEST1.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CinematicConfig.TEST1.getEventHandler());
            KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Key.SPACE.getKeyCode(), false, false, false, false) );

        });

        RETURN.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            DialogLayout.getINSTANCE().removeContent();
            Movement.resumeMovement();
            Movement.setMoved(true);
        });
    }

    private EventHandler eventHandler;

    Action() {}

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
