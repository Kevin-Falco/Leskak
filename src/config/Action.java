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
    TELEPORT_PLANET3,
    TELEPORT_COMMERCIAL_CENTER,
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
    RETURN_OBJECT2,
    RETURN_OBJECT6,
    RETURN_OBJECT4_2,
    RETURN_OBJECT6_2,
    CHEST_HIDDEN,
    CHEST_CLOSED,
    BUY_SKIN,
    BUY_DYNAMITE,
    TEST1,
    RETURN,
    ;

    static{
        TELEPORT_PLANET1.eventHandler = createTeleportAction(Planet.PLANET1.getMaps().get(0));
        TELEPORT_PLANET2.eventHandler = createTeleportAction(Planet.PLANET2.getMaps().get(0));
        TELEPORT_PLANET3.eventHandler = createTeleportAction(Planet.PLANET3.getMaps().get(0));
        TELEPORT_COMMERCIAL_CENTER.eventHandler = createTeleportAction(Planet.COMMERCIAL_CENTER.getMaps().get(0));
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
        BUY_DYNAMITE.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(DialogLayout.getINSTANCE().getMoney() < 15000){
                DialogLayout.getINSTANCE().removeContent();
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_NOT_ENOUGH_MONEY.getText());
                Movement.setMoved(true);
                Movement.resumeMovement();
                return;
            }
        });
        BUY_SKIN.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(DialogLayout.getINSTANCE().getMoney() < 10000){
                DialogLayout.getINSTANCE().removeContent();
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_NOT_ENOUGH_MONEY.getText());
                Movement.setMoved(true);
                Movement.resumeMovement();
                return;
            }
            DialogLayout.getINSTANCE().removeMoney(10000);
            Player.getINSTANCE().getSkinAvailables().add(1);
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_SKIN_BUYED.getText());
            Movement.setMoved(true);
            Movement.resumeMovement();
        });
        RETURN_OBJECT2.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(DialogLayout.getINSTANCE().getMoney() < 500){
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_NOT_ENOUGH_MONEY.getText());
                Movement.setMoved(true);
                Movement.resumeMovement();
                return;
            }
            DialogLayout.getINSTANCE().removeMoney(500);
            Inventory.getINSTANCE().remove(Object.OBJ2);
            Inventory.getINSTANCE().add(Object.OBJ2_2);
            for(Cell cell : MapConfig.getINSTANCE().getMaps().get(7).getCells()){
                if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null &&
                        ((BlockingCell) cell).getInteraction().equals(Interaction.PNJ12)){
                    ((BlockingCell) cell).setInteraction(Interaction.PNJ12_2);
                }
            }
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_REACTORS_REPARED.getText());
            Movement.setMoved(true);
            Movement.resumeMovement();
        });
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
        RETURN_OBJECT4_2.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Interaction.PNJ11.setInteractionDone(true);
            Inventory.getINSTANCE().remove(Object.OBJ4_2);
            DialogLayout.getINSTANCE().addMoney(500);
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ11_AFTER.getText());
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
            Movement.setMoved(true);
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
            Movement.setMoved(true);
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
