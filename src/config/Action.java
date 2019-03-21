package config;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import lib.*;

public enum Action {
    TELEPORT_MAP1,
    TELEPORT_MAP2,
    TELEPORT_MAP3,
    TELEPORT_MAP4,
    GIVE_OBJECT1,
    GIVE_OBJECT1_2,
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
        TELEPORT_MAP1.eventHandler = createTeleportAction(0);
        TELEPORT_MAP2.eventHandler = createTeleportAction(1);
        TELEPORT_MAP3.eventHandler = createTeleportAction(2);
        TELEPORT_MAP4.eventHandler = createTeleportAction(3);
        GIVE_OBJECT1.eventHandler = createGiveObjectAction(Interaction.BUSH1, Object.OBJ1);
        GIVE_OBJECT1_2.eventHandler = createGiveObjectAction(Interaction.PNJ4, Object.OBJ1_2);
        //GIVE_OBJECT2.eventHandler = createGiveObjectAction(Interaction., Object.OBJ2);
        //GIVE_OBJECT3.eventHandler = createGiveObjectAction(Interaction., Object.OBJ3);
        //GIVE_OBJECT4.eventHandler = createGiveObjectAction(Interaction., Object.OBJ4);
        //GIVE_OBJECT5.eventHandler = createGiveObjectAction(Interaction., Object.OBJ5);
        //GIVE_OBJECT6.eventHandler = createGiveObjectAction(Interaction., Object.OBJ6);

        RETURN_OBJECT1.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(!MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6))){
                MapConfig.getINSTANCE().movePlayer(new Pair<>(21, 6));
                MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6));
            }
            Interaction.PNJ3.setInteractionDone(true);
            Inventory.getINSTANCE().remove(Object.OBJ1);
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

    public static EventHandler createTeleportAction(int nbMap){
        return ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Player.getINSTANCE().getImage());
            MapConfig.getINSTANCE().configMap(nbMap);
            DialogLayout.getINSTANCE().removeContent();
            Node node = (Node) action.getSource();
            node.setFocusTraversable(false);
            Movement.resumeMovement();
        });
    }

    public static EventHandler createGiveObjectAction(Interaction interaction, Object object){
        return ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(object);
            interaction.setInteractionDone(true);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            Movement.setMoved(true);
        });
    }
}
