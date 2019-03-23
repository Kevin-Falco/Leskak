package config;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import lib.*;

public enum Action {
    TELEPORT_PLANET1,
    TELEPORT_PLANET2,
    GIVE_OBJECT1,
    GIVE_OBJECT2,
    GIVE_OBJECT3,
    GIVE_OBJECT4,
    GIVE_OBJECT5,
    GIVE_OBJECT6,
    GIVE_OBJECT6_2,
    GIVE_MONEY_CAT,
    GIVE_MONEY_FOX,
    ERROR_FOX,
    RETURN_OBJECT6,
    RETURN_OBJECT6_2,
    TEST1,
    RETURN,
    ;

    static{
        TELEPORT_PLANET1.eventHandler = createTeleportAction(Planet.PLANET1.getMaps().get(0));
        TELEPORT_PLANET2.eventHandler = createTeleportAction(Planet.PLANET2.getMaps().get(0));
        GIVE_OBJECT6.eventHandler = createGiveObjectAction(Interaction.BUSH1, Object.OBJ6, DialogConfig.BUSH1_AFTER);
        GIVE_OBJECT6_2.eventHandler = createGiveObjectAction(Interaction.PNJ3_2, Object.OBJ6, DialogConfig.PNJ3_2_AFTER);
        GIVE_MONEY_CAT.eventHandler = createGiveMoneyAction(Interaction.CAT2, 500, DialogConfig.CAT2_AFTER);
        GIVE_MONEY_FOX.eventHandler = createGiveMoneyAction(Interaction.FOX, 500,DialogConfig.FOX_SUCCESS);
        GIVE_OBJECT1.eventHandler = createGiveObjectAction(Interaction.PNJ4, Object.OBJ1, DialogConfig.PNJ4_AFTER);
        //GIVE_OBJECT2.eventHandler = createGiveObjectAction(Interaction., Object.OBJ2, DialogConfig.);
        //GIVE_OBJECT3.eventHandler = createGiveObjectAction(Interaction., Object.OBJ3, DialogConfig.);
        //GIVE_OBJECT4.eventHandler = createGiveObjectAction(Interaction., Object.OBJ4, DialogConfig.);
        //GIVE_OBJECT5.eventHandler = createGiveObjectAction(Interaction., Object.OBJ5, DialogConfig.);


        ERROR_FOX.eventHandler = TEST1.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.FOX_ERROR.getText());
            Node node = (Node) action.getSource();
            node.setFocusTraversable(false);
            Movement.setMoved(false);
            Movement.resumeMovement();
        });
        RETURN_OBJECT6.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(!MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6))){
                MapConfig.getINSTANCE().movePlayer(new Pair<>(21, 6));
                MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6));
            }
            Interaction.PNJ3.setInteractionDone(true);
            Inventory.getINSTANCE().remove(Object.OBJ6);
            DialogLayout.getINSTANCE().removeContent();
            Movement.setMoved(true);
            Movement.resumeMovement();
        });
        RETURN_OBJECT6_2.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Interaction.PNJ5.setInteractionDone(true);
            Inventory.getINSTANCE().remove(Object.OBJ6);
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ5_AFTER.getText());
            Movement.setMoved(true);
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

    public static EventHandler createTeleportAction(Map map){
        return ((EventHandler<ActionEvent>) (action) ->{
            Movement.getMap().getGridPane().getChildren().remove(Player.getINSTANCE().getImage());
            MapConfig.getINSTANCE().configMap(map);
            DialogLayout.getINSTANCE().removeContent();
            Node node = (Node) action.getSource();
            node.setFocusTraversable(false);
            Movement.resumeMovement();
        });
    }

    public static EventHandler createGiveObjectAction(Interaction interaction, Object object, DialogConfig dialogConfig){
        return ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(object);
            interaction.setInteractionDone(true);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(dialogConfig.getText());
            Movement.setMoved(false);
        });
    }

    public static EventHandler createGiveMoneyAction(Interaction interaction, int money, DialogConfig dialogConfig){
        return ((EventHandler<ActionEvent>) (action) -> {
            DialogLayout.getINSTANCE().addMoney(money);
            interaction.setInteractionDone(true);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(dialogConfig.getText());
            Movement.setMoved(false);
        });
    }
}
