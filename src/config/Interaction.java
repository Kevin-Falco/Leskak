package config;


import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lib.*;

public enum Interaction {
    PNJ1,
    PNJ2,
    PNJ3,
    PNJ4,
    MASTER,
    ROCKET,
    RETURN_GAME,
    BUSH1,
    MOVEMENT;

    static {
        PNJ1.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("Coucou ça va ? je sais plus ce que je dois dire en vrai lol.");
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ1.getEventHandler());
        });
        PNJ2.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("Tu chechers un plaque de tôle ? Va voir le trouduc a droite j'crois qu'il sais où tu peux en avoir.");

            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ2.getEventHandler());
        });
        PNJ3.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!PNJ3.isInteractionDone()) {
                    DialogLayout.getINSTANCE().setText("Wep je sais où trouver une plaque de tole. Mais si tu veux que je te le dises, tu vas devoir me su... euh me trouver" +
                            "un bidule que j'ai pommé dans un buisson là haut là bas. Allez tchuss. ");
                    if (Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ1.getImageView())) {
                        DialogLayout.getINSTANCE().addButton("Donner son bidule", Action.RETURN_OBJECT1.getEventHandler());
                        DialogLayout.getINSTANCE().addReturnButton();
                    }
                }
                else
                {
                    DialogLayout.getINSTANCE().setText("Ok Ci-mer bro va voir l'autre teubé la bas crois il a ce que tu veux mdr. ");
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ3.getEventHandler());
        });
        PNJ4.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!PNJ4.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText("Vlà ta plaque de tôle pti fragile. Maintenant laisses moi tranquille j'ai autre chose à foutre.");
                    if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ2.getImageView())){
                        DialogLayout.getINSTANCE().addButton("Prendre la plaque de tôle", Action.GIVE_OBJECT2.getEventHandler());
                        DialogLayout.getINSTANCE().addReturnButton();
                    }
                }
                else{
                    DialogLayout.getINSTANCE().setText("Jte l'ai filé ta putain de plaque. Casse toi de là.");
                }
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PNJ4.getEventHandler());
        });
        MASTER.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("Coucou je suis le maitre. Et t'es bloqué ici. Lol.");
                DialogLayout.getINSTANCE().addButton("DONNER L'OBJET", Action.TEST1.getEventHandler());
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.MASTER.getEventHandler());
        });
        ROCKET.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                DialogLayout.getINSTANCE().setText("I'M A ROCKET");
                //if(Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ2.getImageView()))
                    DialogLayout.getINSTANCE().addButton("GO NEW MAP", Action.TELEPORT_MAP4.getEventHandler());
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.ROCKET.getEventHandler());
        });
        BUSH1.eventHandler = ((EventHandler<KeyEvent>) event -> {
            if(event.getCode() == KeyboardConfig.ENTER.getKey().getKeyCode() && Movement.isMoved()){
                Movement.setMoved(false);
                if(!BUSH1.isInteractionDone()){
                    DialogLayout.getINSTANCE().setText("Vous trouvez un bidule.");
                    if(!Inventory.getINSTANCE().getGridPane().getChildren().contains(InventoryConfig.OBJ1.getImageView())){
                        DialogLayout.getINSTANCE().addButton("Prendre le bidule", Action.GIVE_OBJECT1.getEventHandler());
                        DialogLayout.getINSTANCE().addReturnButton();
                    }
                }
                else
                    DialogLayout.getINSTANCE().setText("Vous avez déjà pris le bidule.");
            }
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.BUSH1.getEventHandler());
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
}
