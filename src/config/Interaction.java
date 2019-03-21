package config;


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lib.*;

public enum Interaction {
    PNJ1,
    PNJ2,
    PNJ3,
    PNJ4,
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
                DialogConfig.PNJ3_AFTER, DialogConfig.PNJ3_BUTTON, Action.RETURN_OBJECT1, true,
                Object.OBJ1);
        PNJ4.eventHandler = createSimpleButtonInteractionObject(Interaction.PNJ4, DialogConfig.PNJ4_BEFORE,
                DialogConfig.PNJ4_AFTER, DialogConfig.PNJ4_BUTTON, Action.GIVE_OBJECT1_2, false,
                Object.OBJ1_2);

        BUSH1.eventHandler = createSimpleButtonInteractionObject(Interaction.BUSH1, DialogConfig.BUSH1_BEFORE,
                DialogConfig.BUSH1_AFTER, DialogConfig.BUSH1_BUTTON, Action.GIVE_OBJECT1, false,
                Object.OBJ1);
        CAT2.eventHandler = createSimpleButtonInteraction(Interaction.CAT2, DialogConfig.CAT2_BEFORE,
                DialogConfig.CAT2_AFTER, DialogConfig.CAT2_BUTTON, Action.GIVE_MONEY_CAT);

        FOX.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                if(!FOX.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText(DialogConfig.FOX_BEFORE.getText());
                    DialogLayout.getINSTANCE().addButton(DialogConfig.FOX_BUTTON1.getText(), Action.ERROR_FOX.getEventHandler());
                    DialogLayout.getINSTANCE().addButton(DialogConfig.FOX_BUTTON2.getText(), Action.GIVE_MONEY_FOX.getEventHandler());
                    DialogLayout.getINSTANCE().addButton(DialogConfig.FOX_BUTTON3.getText(), Action.ERROR_FOX.getEventHandler());
                    DialogLayout.getINSTANCE().addReturnButton();
                }
                else{
                    DialogLayout.getINSTANCE().setText(DialogConfig.FOX_AFTER.getText());
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.FOX.getEventHandler());
        });

        ROCKET.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("I'M A ROCKET");
                if(Inventory.getINSTANCE().contains(Object.OBJ1_2)){
                    DialogLayout.getINSTANCE().addButton("GO NEW MAP", Action.TELEPORT_MAP4.getEventHandler());
                    DialogLayout.getINSTANCE().addReturnButton();
                }
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
}
