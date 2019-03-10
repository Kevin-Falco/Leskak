package config;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import lib.DialogLayout;
import lib.Inventory;
import lib.Movement;

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
    ;

    static{
        TELEPORT_MAP1.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(0);
            DialogLayout.getINSTANCE().removeContent();
            Node node = (Node) action.getSource();
            node.setFocusTraversable(false);
        });
        TELEPORT_MAP2.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(1);
            DialogLayout.getINSTANCE().removeContent();
        });
        TELEPORT_MAP3.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(2);
            DialogLayout.getINSTANCE().removeContent();
        });
        TELEPORT_MAP4.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Movement.getMap().getGridPane().getChildren().size() - 1);
            MapConfig.getINSTANCE().configMap(3);
            DialogLayout.getINSTANCE().removeContent();
        });
        GIVE_OBJECT1.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ1);
            Node node = (Node) action.getSource();
            System.out.println(node);
            node.setFocusTraversable(false);
        });
        GIVE_OBJECT2.eventHandler = ((EventHandler<ActionEvent>) (action) -> Inventory.getINSTANCE().add(InventoryConfig.OBJ2));
        GIVE_OBJECT3.eventHandler = ((EventHandler<ActionEvent>) (action) -> Inventory.getINSTANCE().add(InventoryConfig.OBJ3));
        GIVE_OBJECT4.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ4);
            ((Node) action.getSource()).setVisible(false);
        });
        GIVE_OBJECT5.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ5);
            ((Node) action.getSource()).setVisible(false);
        });
        GIVE_OBJECT6.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(InventoryConfig.OBJ6);
            ((Node) action.getSource()).setVisible(false);
        });
    }

    private EventHandler eventHandler;

    Action() { }

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
