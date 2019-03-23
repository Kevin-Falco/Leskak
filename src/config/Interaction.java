package config;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lib.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public enum Interaction {
    PNJ1,
    PNJ2,
    PNJ3,
    PNJ3_2,
    PNJ4,
    PNJ5,
    PNJ6,
    PNJ7,
    CHEST_BEFORE_HIDDEN,
    CHEST_HIDDEN,
    CHEST_CLOSED,
    CHEST_OPENED,
    SPACESHIP,
    ROCKET,
    RETURN_GAME,
    BUSH1,
    CAT1,
    CAT2,
    FOX,
    SNAKE,
    MOVEMENT;

    static {
        PNJ1.eventHandler = createSimpleDialog(Interaction.PNJ1, DialogConfig.PNJ1);
        PNJ2.eventHandler = createSimpleDialog(Interaction.PNJ2, DialogConfig.PNJ2);
        CAT1.eventHandler = createSimpleDialog(Interaction.CAT1, DialogConfig.CAT1);
        SNAKE.eventHandler = createSimpleDialog(Interaction.SNAKE, DialogConfig.SNAKE);
        PNJ3.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ3, DialogConfig.PNJ3_BEFORE,
                DialogConfig.PNJ3_AFTER, DialogConfig.PNJ3_BUTTON, Action.RETURN_OBJECT6, true,
                Object.OBJ6);
        PNJ3_2.eventHandler = createSimpleButtonInteraction(Interaction.PNJ3_2, DialogConfig.PNJ3_2_BEFORE,
                DialogConfig.PNJ3_2_AFTER, DialogConfig.PNJ3_2_BUTTON, Action.GIVE_OBJECT6_2);
        PNJ4.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ4, DialogConfig.PNJ4_BEFORE,
                DialogConfig.PNJ4_AFTER, DialogConfig.PNJ4_BUTTON, Action.GIVE_OBJECT1, false,
                Object.OBJ1);
        PNJ5.eventHandler = createSimpleButtonInteractionObjectWithInteractionChange(Interaction.PNJ5, DialogConfig.PNJ5_BEFORE,
                DialogConfig.PNJ5_AFTER, DialogConfig.PNJ5_BUTTON, Action.RETURN_OBJECT6_2, true,
                Object.OBJ6, 0, Interaction.PNJ3, Interaction.PNJ3_2);

        BUSH1.eventHandler = createSimpleButtonInteractionObject(Interaction.BUSH1, DialogConfig.BUSH1_BEFORE,
                DialogConfig.BUSH1_AFTER, DialogConfig.BUSH1_BUTTON, Action.GIVE_OBJECT6, false,
                Object.OBJ6);
        CAT2.eventHandler = createSimpleButtonInteraction(Interaction.CAT2, DialogConfig.CAT2_BEFORE,
                DialogConfig.CAT2_AFTER, DialogConfig.CAT2_BUTTON, Action.GIVE_MONEY_CAT);

        FOX.eventHandler = createRiddleInteraction(Interaction.FOX, DialogConfig.FOX_BEFORE, DialogConfig.FOX_AFTER,
                DialogConfig.FOX_BUTTON1, DialogConfig.FOX_BUTTON2, DialogConfig.FOX_BUTTON3,
                Action.ERROR_FOX, Action.GIVE_MONEY_FOX);
        PNJ6.eventHandler = createRiddleInteraction(Interaction.PNJ6, DialogConfig.PNJ6_BEFORE, DialogConfig.PNJ6_AFTER,
                DialogConfig.PNJ6_BUTTON1, DialogConfig.PNJ6_BUTTON2, DialogConfig.PNJ6_BUTTON3,
                Action.ERROR_PNJ6, Action.GIVE_MONEY_PNJ6);
        PNJ7.eventHandler = createSimpleDialogWithInteractionAndSpriteChange(Interaction.PNJ7, DialogConfig.PNJ7, 1,
                Interaction.CHEST_BEFORE_HIDDEN, Interaction.CHEST_HIDDEN, Sprite.CHEST_HIDDEN);
        CHEST_BEFORE_HIDDEN.eventHandler = createSimpleDialog(Interaction.CHEST_BEFORE_HIDDEN, DialogConfig.CHEST_BEFORE_HIDDEN);
        CHEST_HIDDEN.eventHandler = createSimpleButtonInteraction(Interaction.CHEST_HIDDEN, DialogConfig.CHEST_HIDDEN,
                null, DialogConfig.CHEST_HIDDEN_BUTTON, Action.CHEST_HIDDEN);
        CHEST_CLOSED.eventHandler = createSimpleButtonInteraction(Interaction.CHEST_CLOSED, DialogConfig.CHEST_CLOSED,
                null, DialogConfig.CHEST_CLOSED_BUTTON, Action.CHEST_CLOSED);
        CHEST_OPENED.eventHandler = createSimpleDialog(Interaction.CHEST_OPENED, DialogConfig.CHEST_AFTER_OPENED);
        SPACESHIP.eventHandler = createSimpleButtonInteraction(Interaction.SPACESHIP, DialogConfig.SPACESHIP_BEFORE,
                DialogConfig.SPACESHIP_AFTER, DialogConfig.SPACESHIP_BUTTON, Action.GIVE_OBJECT2);

        ROCKET.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!Planet.PLANET1.getMaps().contains(Movement.getMap())){
                    DialogLayout.getINSTANCE().addButton(Planet.PLANET1.getName(), Action.TELEPORT_PLANET1.getEventHandler());
                }
                if(!Planet.PLANET2.getMaps().contains(Movement.getMap()) && Inventory.getINSTANCE().contains(Object.OBJ1)){
                    DialogLayout.getINSTANCE().addButton(Planet.PLANET2.getName(), Action.TELEPORT_PLANET2.getEventHandler());
                }
                DialogLayout.getINSTANCE().addReturnButton();
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
    private boolean interactionDone = false;

    Interaction() {}

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public boolean isInteractionDone() {
        return interactionDone;
    }

    public void setInteractionDone(boolean interactionDone) {
        this.interactionDone = interactionDone;
    }

    public  static EventHandler createSimpleDialog(Interaction interaction, DialogConfig dialogConfig){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText(dialogConfig.getText());

            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public  static EventHandler createSimpleDialogWithInteractionChange(Interaction interaction, DialogConfig dialogConfig,
                                                                        int nbMap, Interaction toRemove, Interaction toAdd){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText(dialogConfig.getText());
                for(Cell cell : MapConfig.getINSTANCE().getMaps().get(nbMap).getCells()){
                    if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null && ((BlockingCell) cell).getInteraction().equals(toRemove))
                        ((BlockingCell) cell).setInteraction(toAdd);
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public  static EventHandler createSimpleDialogWithInteractionAndSpriteChange(Interaction interaction, DialogConfig dialogConfig,
                                                                        int nbMap, Interaction toRemove, Interaction toAdd, Sprite sprite){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText(dialogConfig.getText());
                for(Cell cell : MapConfig.getINSTANCE().getMaps().get(nbMap).getCells()){
                    if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null && ((BlockingCell) cell).getInteraction().equals(toRemove)){
                        ((BlockingCell) cell).setInteraction(toAdd);
                        cell.setSprite(sprite);
                    }
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public  static EventHandler createSimpleButtonInteractionObject(Interaction interaction, DialogConfig dialogConfigBefore,
                                                              DialogConfig dialogConfigAfter, DialogConfig dialogConfigButton,
                                                              Action action, boolean contains, Object object){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!interaction.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(dialogConfigBefore.getText());
                    if(contains == Inventory.getINSTANCE().contains(object)){
                        DialogLayout.getINSTANCE().addButton(dialogConfigButton.getText(), action.getEventHandler());
                        DialogLayout.getINSTANCE().addReturnButton();
                    }
                }
                else{
                    DialogLayout.getINSTANCE().setText(dialogConfigAfter.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public  static EventHandler createSimpleButtonInteractionObjectWithInteractionChange(Interaction interaction, DialogConfig dialogConfigBefore,
                                                                                         DialogConfig dialogConfigAfter, DialogConfig dialogConfigButton,
                                                                                         Action action, boolean contains, Object object,
                                                                                         int nbMap, Interaction toRemove, Interaction toAdd){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!interaction.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(dialogConfigBefore.getText());
                    if(contains == Inventory.getINSTANCE().contains(object)){
                        DialogLayout.getINSTANCE().addButton(dialogConfigButton.getText(), action.getEventHandler());
                        DialogLayout.getINSTANCE().addReturnButton();
                    }
                }
                else{
                    DialogLayout.getINSTANCE().setText(dialogConfigAfter.getText());
                }
                for(Cell cell : MapConfig.getINSTANCE().getMaps().get(nbMap).getCells()){
                    if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null && ((BlockingCell) cell).getInteraction().equals(toRemove))
                        ((BlockingCell) cell).setInteraction(toAdd);
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public static EventHandler createSimpleButtonInteractionWithInteractionAndSpriteChange(Interaction interaction, DialogConfig dialogConfigBefore,
                                                                                         DialogConfig dialogConfigAfter, DialogConfig dialogConfigButton,
                                                                                         Action action,
                                                                                         int nbMap, Interaction toRemove, Interaction toAdd, Sprite sprite){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!interaction.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(dialogConfigBefore.getText());
                    DialogLayout.getINSTANCE().addButton(dialogConfigButton.getText(), action.getEventHandler());
                    DialogLayout.getINSTANCE().addReturnButton();
                }
                else{
                    DialogLayout.getINSTANCE().setText(dialogConfigAfter.getText());
                }
                for(Cell cell : MapConfig.getINSTANCE().getMaps().get(nbMap).getCells()){
                    if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null && ((BlockingCell) cell).getInteraction().equals(toRemove)){
                        ((BlockingCell) cell).setInteraction(toAdd);
                        cell.setSprite(sprite);
                    }
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public  static EventHandler createSimpleButtonInteraction(Interaction interaction, DialogConfig dialogConfigBefore,
                                                              DialogConfig dialogConfigAfter, DialogConfig dialogConfigButton,
                                                              Action action){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!interaction.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(dialogConfigBefore.getText());
                    DialogLayout.getINSTANCE().addButton(dialogConfigButton.getText(), action.getEventHandler());
                    DialogLayout.getINSTANCE().addReturnButton();
                }
                else{
                    DialogLayout.getINSTANCE().setText(dialogConfigAfter.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public  static EventHandler createSimpleButtonInteractionWithInteractionChange(Interaction interaction, DialogConfig dialogConfigBefore,
                                                              DialogConfig dialogConfigAfter, DialogConfig dialogConfigButton,
                                                              Action action, int nbMap, Interaction toRemove, Interaction toAdd){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!interaction.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(dialogConfigBefore.getText());
                    DialogLayout.getINSTANCE().addButton(dialogConfigButton.getText(), action.getEventHandler());
                    DialogLayout.getINSTANCE().addReturnButton();
                }
                else{
                    DialogLayout.getINSTANCE().setText(dialogConfigAfter.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }

    public static EventHandler createRiddleInteraction(Interaction interaction, DialogConfig dialogBefore, DialogConfig dialogAfter,
                                                       DialogConfig dialogButton1, DialogConfig dialogButton2, DialogConfig dialogButton3,
                                                       Action actionError, Action actionSuccess){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                if(!interaction.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(dialogBefore.getText());
                    DialogLayout.getINSTANCE().addButton(dialogButton1.getText(), actionError.getEventHandler());
                    DialogLayout.getINSTANCE().addButton(dialogButton2.getText(), actionSuccess.getEventHandler());
                    DialogLayout.getINSTANCE().addButton(dialogButton3.getText(), actionError.getEventHandler());
                    VBox vbox = (VBox) DialogLayout.getINSTANCE().getGridPane().getChildren().get(2);
                    List list = new ArrayList(vbox.getChildren());
                    Collections.shuffle(list);
                    vbox.getChildren().removeAll(list);
                    vbox.getChildren().addAll(list);
                    DialogLayout.getINSTANCE().addReturnButton();
                }
                else{
                    DialogLayout.getINSTANCE().setText(dialogAfter.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }
}
