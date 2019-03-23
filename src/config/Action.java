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
    GIVE_MONEY_PNJ6,
    ERROR_PNJ6,
    RETURN_OBJECT6,
    RETURN_OBJECT6_2,
    CHEST_HIDDEN,
    CHEST_CLOSED,
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
        CHEST_HIDDEN.eventHandler = createInteractionAndSpriteChange(Interaction.CHEST_HIDDEN, DialogConfig.CHEST_CLOSED, 1,
                Interaction.CHEST_HIDDEN, Interaction.CHEST_CLOSED, Sprite.CHEST_CLOSED);
        CHEST_CLOSED.eventHandler = createInteractionAndSpriteChangeAndGiveMoney(Interaction.CHEST_CLOSED, DialogConfig.CHEST_OPENED, 1,
                Interaction.CHEST_CLOSED, Interaction.CHEST_OPENED, Sprite.CHEST_OPENED, 2000);
        GIVE_MONEY_PNJ6.eventHandler = createGiveMoneyAction(Interaction.PNJ6, 500,DialogConfig.PNJ6_SUCCESS);
        GIVE_OBJECT1.eventHandler = createGiveObjectAction(Interaction.PNJ4, Object.OBJ1, DialogConfig.PNJ4_AFTER);
        GIVE_OBJECT2.eventHandler = createGiveObjectAndMoneyAction(Interaction.SPACESHIP, Object.OBJ2, DialogConfig.SPACESHIP_AFTER ,250);
        //GIVE_OBJECT3.eventHandler = createGiveObjectAction(Interaction., Object.OBJ3, DialogConfig.);
        //GIVE_OBJECT4.eventHandler = createGiveObjectAction(Interaction., Object.OBJ4, DialogConfig.);
        //GIVE_OBJECT5.eventHandler = createGiveObjectAction(Interaction., Object.OBJ5, DialogConfig.);


        ERROR_FOX.eventHandler = createRiddleErrorAction(DialogConfig.FOX_ERROR);
        ERROR_PNJ6.eventHandler = createRiddleErrorAction(DialogConfig.PNJ6_ERROR);
        RETURN_OBJECT6.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(!MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6))){
                MapConfig.getINSTANCE().movePlayer(new Pair<>(21, 6));
                MapConfig.getINSTANCE().swapCells(0, new Pair<>(22, 5), new Pair<>(22, 6));
            }
            DialogLayout.getINSTANCE().addMoney(250);
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

    public static EventHandler createGiveObjectAndMoneyAction(Interaction interaction, Object object, DialogConfig dialogConfig, int money){
        return ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().add(object);
            DialogLayout.getINSTANCE().addMoney(money);
            interaction.setInteractionDone(true);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(dialogConfig.getText());
            Movement.setMoved(false);
        });
    }

    public static EventHandler createRiddleErrorAction(DialogConfig dialogError){
        return ((EventHandler<ActionEvent>) (action) -> {
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(dialogError.getText());
            Node node = (Node) action.getSource();
            node.setFocusTraversable(false);
            Movement.setMoved(true);
            Movement.resumeMovement();
        });
    }

    public  static  EventHandler createInteractionAndSpriteChange(Interaction interaction,DialogConfig dialogConfig,
                                                                  int nbMap, Interaction toRemove, Interaction toAdd, Sprite sprite){
        return ((EventHandler<ActionEvent>) (action) -> {
            interaction.setInteractionDone(true);
            for(Cell cell : MapConfig.getINSTANCE().getMaps().get(nbMap).getCells()){
                if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null && ((BlockingCell) cell).getInteraction().equals(toRemove)){
                    ((BlockingCell) cell).setInteraction(toAdd);
                    cell.setSprite(sprite);
                }
            }
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(dialogConfig.getText());
            Movement.setMoved(true);
        });
    }

    public  static  EventHandler createInteractionAndSpriteChangeAndGiveMoney(Interaction interaction,DialogConfig dialogConfig,
                                                                  int nbMap, Interaction toRemove, Interaction toAdd, Sprite sprite, int money){
        return ((EventHandler<ActionEvent>) (action) -> {
            interaction.setInteractionDone(true);
            for(Cell cell : MapConfig.getINSTANCE().getMaps().get(nbMap).getCells()){
                if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null && ((BlockingCell) cell).getInteraction().equals(toRemove)){
                    ((BlockingCell) cell).setInteraction(toAdd);
                    cell.setSprite(sprite);
                }
            }
            DialogLayout.getINSTANCE().addMoney(money);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(dialogConfig.getText());
            Movement.setMoved(true);
        });
    }
}
