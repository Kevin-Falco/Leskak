package config;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
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
    PNJ5_2,
    PNJ6,
    PNJ7,
    PNJ8,
    PNJ9,
    PNJ10,
    PNJ11,
    PNJ12,
    PNJ12_2,
    PNJ13,
    PNJ14,
    PNJ14_2,
    PNJ15,
    PNJ16,
    PNJ17,
    PNJ18,
    PNJ19,
    PNJ20,
    CHEST_BEFORE_HIDDEN,
    CHEST_HIDDEN,
    CHEST_CLOSED,
    CHEST_OPENED,
    SPACESHIP,
    PACMAN_IN,
    PACMAN_OUT,
    ROCKET,
    RETURN_GAME,
    BUSH1,
    BUSH2,
    CAT1,
    CAT2,
    FOX,
    SNAKE,
    CHICKEN,
    CHICKEN1,
    CHICKEN2,
    CHICKEN3,
    FOX1,
    STATUE,
    ROCK,
    PACMANSKIP,
    MOVEMENT;

    static {
        PNJ1.eventHandler = createSimpleDialog(Interaction.PNJ1, DialogConfig.PNJ1);
        PNJ2.eventHandler = createSimpleDialog(Interaction.PNJ2, DialogConfig.PNJ2);
        CAT1.eventHandler = createSimpleDialog(Interaction.CAT1, DialogConfig.CAT1);
        PNJ8.eventHandler = createSimpleDialog(Interaction.PNJ8, DialogConfig.PNJ8);
        PNJ9.eventHandler = createSimpleDialog(Interaction.PNJ9, DialogConfig.PNJ9);
        PNJ12.eventHandler = createSimpleDialog(Interaction.PNJ12, DialogConfig.PNJ12);
        PNJ12_2.eventHandler = createSimpleDialog(Interaction.PNJ12_2, DialogConfig.PNJ12_2);
        PNJ13.eventHandler = createSimpleDialog(Interaction.PNJ13, DialogConfig.PNJ13);
        PNJ16.eventHandler = createSimpleDialog(Interaction.PNJ16, DialogConfig.PNJ16);
        PNJ18.eventHandler = createSimpleDialog(Interaction.PNJ18, DialogConfig.PNJ18);
        SNAKE.eventHandler = createSimpleDialog(Interaction.SNAKE, DialogConfig.SNAKE);
        CHICKEN.eventHandler = createSimpleDialog(Interaction.CHICKEN, DialogConfig.CHICKEN);
        CHICKEN1.eventHandler = chickenInteraction(Interaction.CHICKEN1);
        CHICKEN2.eventHandler = chickenInteraction(Interaction.CHICKEN2);
        CHICKEN3.eventHandler = chickenInteraction(Interaction.CHICKEN3);
        FOX1.eventHandler = createSimpleDialog(Interaction.FOX1, DialogConfig.FOX);
        SNAKE.eventHandler = createSimpleDialog(Interaction.SNAKE, DialogConfig.SNAKE);
        PNJ3.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ3, DialogConfig.PNJ3_BEFORE,
                DialogConfig.PNJ3_AFTER, DialogConfig.PNJ3_BUTTON, Action.RETURN_OBJECT6, true,
                Object.OBJ6);
        PNJ11.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ11, DialogConfig.PNJ11_BEFORE,
                DialogConfig.PNJ11_AFTER, DialogConfig.PNJ11_BUTTON, Action.RETURN_OBJECT4_2, true,
                Object.OBJ4_2);
        PNJ3_2.eventHandler = createSimpleButtonInteraction(Interaction.PNJ3_2, DialogConfig.PNJ3_2_BEFORE,
                DialogConfig.PNJ3_2_AFTER, DialogConfig.PNJ3_2_BUTTON, Action.GIVE_OBJECT6_2);
        PNJ4.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ4, DialogConfig.PNJ4_BEFORE,
                DialogConfig.PNJ4_AFTER, DialogConfig.PNJ4_BUTTON, Action.GIVE_OBJECT1, false,
                Object.OBJ1);
        PNJ5.eventHandler = createSimpleButtonInteractionObjectWithInteractionChange(Interaction.PNJ5, DialogConfig.PNJ5_BEFORE,
                DialogConfig.PNJ5_AFTER, DialogConfig.PNJ5_BUTTON, Action.RETURN_OBJECT6_2, true,
                Object.OBJ6, 0, Interaction.PNJ3, Interaction.PNJ3_2);
        PNJ5_2.eventHandler = createSimpleButtonInteraction(Interaction.PNJ5_2, DialogConfig.PNJ5_2_BEFORE,
                DialogConfig.PNJ5_2_AFTER, DialogConfig.PNJ5_2_BUTTON, Action.GIVE_OBJECT6_3);
        BUSH1.eventHandler = createSimpleButtonInteractionObject(Interaction.BUSH1, DialogConfig.BUSH1_BEFORE,
                DialogConfig.BUSH1_AFTER, DialogConfig.BUSH1_BUTTON, Action.GIVE_OBJECT6, false,
                Object.OBJ6);
        BUSH2.eventHandler = createSimpleButtonInteractionObject(Interaction.BUSH2, DialogConfig.BUSH2_BEFORE,
                DialogConfig.BUSH2_AFTER, DialogConfig.BUSH2_BUTTON, Action.GIVE_OBJECT4_2, false,
                Object.OBJ4_2);
        CAT2.eventHandler = createSimpleButtonInteraction(Interaction.CAT2, DialogConfig.CAT2_BEFORE,
                DialogConfig.CAT2_AFTER, DialogConfig.CAT2_BUTTON, Action.GIVE_MONEY_CAT);

        FOX.eventHandler = createRiddleInteraction(Interaction.FOX, DialogConfig.FOX_BEFORE, DialogConfig.FOX_AFTER,
                DialogConfig.FOX_BUTTON1, DialogConfig.FOX_BUTTON2, DialogConfig.FOX_BUTTON3,
                Action.ERROR_FOX, Action.GIVE_MONEY_FOX);
        PNJ19.eventHandler = createRiddleInteraction(Interaction.PNJ19, DialogConfig.PNJ19_BEFORE, DialogConfig.PNJ19_AFTER,
                DialogConfig.PNJ19_BUTTON1, DialogConfig.PNJ19_BUTTON2, DialogConfig.PNJ19_BUTTON3,
                Action.ERROR_PNJ19, Action.GIVE_SKIN2);
        PNJ6.eventHandler = createRiddleInteraction(Interaction.PNJ6, DialogConfig.PNJ6_BEFORE, DialogConfig.PNJ6_AFTER,
                DialogConfig.PNJ6_BUTTON1, DialogConfig.PNJ6_BUTTON2, DialogConfig.PNJ6_BUTTON3,
                Action.ERROR_PNJ6, Action.GIVE_MONEY_PNJ6);
        STATUE.eventHandler = createRiddleInteraction(Interaction.STATUE, DialogConfig.STATUE_BEFORE, DialogConfig.STATUE_AFTER,
                DialogConfig.STATUE_BUTTON1, DialogConfig.STATUE_BUTTON2, DialogConfig.STATUE_BUTTON3,
                Action.ERROR_STATUE, Action.GIVE_MONEY_STATUE);
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

        PNJ20.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ20, DialogConfig.PNJ20_BEFORE,
                DialogConfig.PNJ20_AFTER, DialogConfig.PNJ20_BUTTON, Action.GIVE_OBJECT5, false,
                Object.OBJ5);

        ROCK.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(Inventory.getINSTANCE().contains(Object.OBJ4)){
                    DialogLayout.getINSTANCE().addButton(DialogConfig.ROCK_BUTTON.getText(), Action.USE_DYNAMITE.getEventHandler());
                    DialogLayout.getINSTANCE().addReturnButton();
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, ROCK.getEventHandler());
        });

        PACMANSKIP.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
            if(event.getCode().equals(KeyCode.P) && Movement.isStoped()){
                Interaction.PACMAN_IN.setInteractionDone(true);
                DialogLayout.getINSTANCE().removeContent();
                DialogLayout.getINSTANCE().setText(DialogConfig.PACMAN_WON.getText());
                Inventory.getINSTANCE().add(Object.OBJ3);
                Player.getINSTANCE().getSkinAvailables().add(3);
                Movement.setMoved(true);
                Movement.resumeMovement();
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, PACMANSKIP.getEventHandler());
            }
        });

        PNJ14.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!PNJ17.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ14_BEFORE.getText());
                }
                else{
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ14_AFTER.getText());
                    Inventory.getINSTANCE().remove(Object.OBJ6);
                    Interaction.PNJ14.setInteractionDone(true);
                    for(Cell cell : MapConfig.getINSTANCE().getMaps().get(8).getCells()){
                        if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null &&
                                ((BlockingCell) cell).getInteraction().equals(Interaction.PNJ14)){
                            ((BlockingCell) cell).setInteraction(Interaction.PNJ14_2);
                        }
                    }
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, PNJ14.getEventHandler());
        });

        PNJ17.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(Inventory.getINSTANCE().contains(Object.OBJ6) && Interaction.PNJ5_2.isInteractionDone() ){
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ17_AFTER.getText());
                    PNJ17.setInteractionDone(true);
                }
                else{
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ17_BEFORE.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, PNJ17.getEventHandler());
        });

        PNJ15.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(Interaction.PNJ15.isInteractionDone() )
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ15_AFTER.getText());
                else if(!Inventory.getINSTANCE().contains(Object.OBJ6) && Interaction.PNJ5.isInteractionDone() ){
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ15_AFTER.getText());
                    PNJ15.setInteractionDone(true);
                    for(Cell cell : MapConfig.getINSTANCE().getMaps().get(3).getCells()){
                        if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null && ((BlockingCell) cell).getInteraction().equals(Interaction.PNJ5))
                            ((BlockingCell) cell).setInteraction(Interaction.PNJ5_2);
                    }
                }
                else{
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ15_BEFORE.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, PNJ15.getEventHandler());
        });

        PNJ14_2.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ14_2, DialogConfig.PNJ14_2_BEFORE,
                DialogConfig.PNJ14_2_AFTER, DialogConfig.PNJ14_2_BUTTON, Action.GIVE_SKIN, true,
                Object.OBJ7_3);

        PACMAN_IN.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                if(PACMAN_IN.isInteractionDone()) return;
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_RELEASED, Movement.getStopEventHandler());
                Player.getINSTANCE().setCurrentSkin(3);
                Movement.setAnimationSet(AnimationSet.getSpriteSet(Player.getINSTANCE().getCurrentSkin()*4).getStopSpriteSet());
                Player.getINSTANCE().setSprite(Movement.getAnimationSet().getSpriteDirection(Player.getINSTANCE().getDirection()));

                PacMan.setRemainingDots(PacMan.getMaxDots());
                MapConfig.getINSTANCE().getMaps().get(10).getGridPane().getChildren().remove(0, MapConfig.getINSTANCE().getMaps().get(10).getGridPane().getChildren().size() - 1 );
                MapConfig.getINSTANCE().getMaps().get(10).getCells().clear();
                MapConfig.getINSTANCE().setupMap(10);
                PacMan.setPacmanMovement(true);
                MapConfig.getINSTANCE().configMap(Planet.PLANET3.getMaps().get(2), new Pair<>(15, 7));
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PACMAN_IN.getEventHandler());
            }
        });
        PACMAN_OUT.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyCode.P && Movement.isMoved()){
                MapConfig.getINSTANCE().configMap(Planet.PLANET3.getMaps().get(1), new Pair<>(20, 5));
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_RELEASED, Movement.getStopEventHandler());
                Player.getINSTANCE().setCurrentSkin(0);
                Movement.setAnimationSet(AnimationSet.getSpriteSet(Player.getINSTANCE().getCurrentSkin()*4).getStopSpriteSet());
                Player.getINSTANCE().setSprite(Movement.getAnimationSet().getSpriteDirection(Player.getINSTANCE().getDirection()));
                Movement.setLastKeyReleased(true);
                PacMan.setPacmanMovement(false);
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PACMAN_OUT.getEventHandler());
            }
        });
        PNJ10.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(Inventory.getINSTANCE().contains(Object.OBJ6_2)){
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_PACKAGE_BEFORE.getText());
                    DialogLayout.getINSTANCE().addButton(DialogConfig.PNJ10_PACKAGE_BUTTON.getText(), Action.PACKAGE_REPAIR.getEventHandler());
                    DialogLayout.getINSTANCE().addReturnButton();
                }
                else {
                    DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10.getText());
                    if(Inventory.getINSTANCE().contains(Object.OBJ2)){
                        DialogLayout.getINSTANCE().addButton(DialogConfig.PNJ10_BUTTON1.getText(), Action.RETURN_OBJECT2.getEventHandler());
                    }
                    if(!Player.getINSTANCE().getSkinAvailables().contains(2)){
                        DialogLayout.getINSTANCE().addButton(DialogConfig.PNJ10_BUTTON2.getText(), Action.BUY_SKIN.getEventHandler());
                    }
                    if(Interaction.PNJ18.isInteractionDone() && !Interaction.PNJ10.isInteractionDone())
                        DialogLayout.getINSTANCE().addButton(DialogConfig.PNJ10_BUTTON4.getText(), Action.QUEST_DYNAMITE.getEventHandler());
                    else if(!Inventory.getINSTANCE().contains(Object.OBJ4) && !Interaction.ROCK.isInteractionDone()){
                        DialogLayout.getINSTANCE().addButton(DialogConfig.PNJ10_BUTTON3.getText(), Action.BUY_DYNAMITE.getEventHandler());
                    }
                    if(Inventory.getINSTANCE().contains(Object.OBJ1) && Inventory.getINSTANCE().contains(Object.OBJ2_2) &&
                            Inventory.getINSTANCE().contains(Object.OBJ3) && Inventory.getINSTANCE().contains(Object.OBJ5)){
                        DialogLayout.getINSTANCE().addButton(DialogConfig.DEATH_STAR.getText(), Action.DEATH_STAR.getEventHandler());
                    }
                    DialogLayout.getINSTANCE().addReturnButton();
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ10.getEventHandler());
        });
        ROCKET.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!Planet.PLANET1.getMaps().contains(Movement.getMap())){
                    DialogLayout.getINSTANCE().addButton(Planet.PLANET1.getName(), Action.TELEPORT_PLANET1.getEventHandler());
                }
                if(!Planet.PLANET2.getMaps().contains(Movement.getMap()) && Inventory.getINSTANCE().contains(Object.OBJ1)){
                    DialogLayout.getINSTANCE().addButton(Planet.PLANET2.getName(), Action.TELEPORT_PLANET2.getEventHandler());
                }
                if(!Planet.COMMERCIAL_CENTER.getMaps().contains(Movement.getMap()) && (Inventory.getINSTANCE().contains(Object.OBJ2) || Inventory.getINSTANCE().contains(Object.OBJ2_2))){
                    DialogLayout.getINSTANCE().addButton(Planet.COMMERCIAL_CENTER.getName(), Action.TELEPORT_COMMERCIAL_CENTER.getEventHandler());
                }
                if(!Planet.PLANET3.getMaps().contains(Movement.getMap()) && PNJ12_2.isInteractionDone()){
                    DialogLayout.getINSTANCE().addButton(Planet.PLANET3.getName(), Action.TELEPORT_PLANET3.getEventHandler());
                }
                if(!Planet.PLANET4.getMaps().contains(Movement.getMap()) && Inventory.getINSTANCE().contains(Object.OBJ3)){
                    DialogLayout.getINSTANCE().addButton(Planet.PLANET4.getName(), Action.TELEPORT_PLANET4.getEventHandler());
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
                interaction.setInteractionDone(true);
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

    public  static EventHandler chickenInteraction(Interaction interaction){
        return ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!interaction.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN.getText());
                    if(PNJ14.isInteractionDone()){
                        if(Inventory.getINSTANCE().contains(Object.OBJ7_2)){
                            DialogLayout.getINSTANCE().removeContent();
                            DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN_BEFORE.getText());
                            DialogLayout.getINSTANCE().addButton(DialogConfig.CHICKEN_BUTTON.getText(), Action.CHICKEN3.getEventHandler());
                            DialogLayout.getINSTANCE().addReturnButton();
                        }
                        else if(Inventory.getINSTANCE().contains(Object.OBJ7_1)){
                            DialogLayout.getINSTANCE().removeContent();
                            DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN_BEFORE.getText());
                            DialogLayout.getINSTANCE().addButton(DialogConfig.CHICKEN_BUTTON.getText(), Action.CHICKEN2.getEventHandler());
                            DialogLayout.getINSTANCE().addReturnButton();
                        }
                        else if(!Inventory.getINSTANCE().contains(Object.OBJ6)){
                            DialogLayout.getINSTANCE().removeContent();
                            DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN_BEFORE.getText());
                            DialogLayout.getINSTANCE().addButton(DialogConfig.CHICKEN_BUTTON.getText(), Action.CHICKEN1.getEventHandler());
                            DialogLayout.getINSTANCE().addReturnButton();
                        }
                    }
                }
                else{
                    DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN_AFTER.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, interaction.getEventHandler());
        });
    }
}
