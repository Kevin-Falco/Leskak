package config;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import lib.*;

/**
 * Enumération de toutes les actions disponibles dans le jeu, que ce soit pour recevoir de l'argent ou en donner, récupérer
 * un objet ou encore se téléporter au spawn de la planète.
 */
public enum Action {

    // Téléportation au point de spawn de chaque planète
    TELEPORT_PLANET1,
    TELEPORT_PLANET2,
    TELEPORT_PLANET3,
    TELEPORT_PLANET4,
    TELEPORT_COMMERCIAL_CENTER,

    // Récupération des différents objets sur chaque planète
    GIVE_OBJECT1,
    GIVE_OBJECT2,
    GIVE_OBJECT4_2,
    GIVE_OBJECT5,
    GIVE_OBJECT6,
    GIVE_OBJECT6_2,
    GIVE_OBJECT6_3,

    // Récupération des skins pour changer d'apparence à la suite d'objectifs secondaires
    GIVE_SKIN,
    GIVE_SKIN2,

    // Récupération d'argent suite à des objectifs secondaires et erreurs correspondantes
    GIVE_MONEY_CAT,
    GIVE_MONEY_FOX,
    GIVE_MONEY_STATUE,
    GIVE_MONEY_PNJ6,

    ERROR_FOX,
    ERROR_STATUE,
    ERROR_PNJ6,
    ERROR_PNJ19,

    // Récupération des objets par leurs propriétaires initiaux
    RETURN_OBJECT2,
    RETURN_OBJECT6,
    RETURN_OBJECT4_2,
    RETURN_OBJECT6_2,

    // Actions de la quête du coffre caché
    CHEST_HIDDEN,
    CHEST_CLOSED,

    // Achats au marchand
    BUY_SKIN,
    BUY_DYNAMITE,
    PACKAGE_REPAIR,
    QUEST_DYNAMITE,
    DEATH_STAR,

    // Récupération des plumes de poulet sur la planète 3
    CHICKEN1,
    CHICKEN2,
    CHICKEN3,

    // Pemet de passer le pacman sur la planète 3
    PACMANSKIP,

    // Utilisation de la dynamite pour débloquer le reste de la planète 4
    USE_DYNAMITE,

    // Action du bouton Retour
    RETURN,
    ;

    static {

        // Téléportation au point de spawn de chaque planète
        TELEPORT_PLANET1.eventHandler = createTeleportAction(Planet.PLANET1.getMaps().get(0));
        TELEPORT_PLANET2.eventHandler = createTeleportAction(Planet.PLANET2.getMaps().get(0));
        TELEPORT_PLANET3.eventHandler = createTeleportAction(Planet.PLANET3.getMaps().get(0));
        TELEPORT_PLANET4.eventHandler = createTeleportAction(Planet.PLANET4.getMaps().get(0));
        TELEPORT_COMMERCIAL_CENTER.eventHandler = createTeleportAction(Planet.COMMERCIAL_CENTER.getMaps().get(0));

        // Récupération des différents objets sur chaque planète
        GIVE_OBJECT1.eventHandler = createGiveObjectAction(Interaction.PNJ4, Object.OBJ1, DialogConfig.PNJ4_AFTER);
        GIVE_OBJECT2.eventHandler = createGiveObjectAndMoneyAction(Interaction.SPACESHIP, Object.OBJ2, DialogConfig.SPACESHIP_AFTER ,250);
        GIVE_OBJECT4_2.eventHandler = createGiveObjectAction(Interaction.BUSH2, Object.OBJ4_2, DialogConfig.BUSH2_AFTER);
        GIVE_OBJECT5.eventHandler = createGiveObjectAction(Interaction.PNJ20, Object.OBJ5, DialogConfig.PNJ20_AFTER);
        GIVE_OBJECT6.eventHandler = createGiveObjectAction(Interaction.BUSH1, Object.OBJ6, DialogConfig.BUSH1_AFTER);
        GIVE_OBJECT6_2.eventHandler = createGiveObjectAction(Interaction.PNJ3_2, Object.OBJ6, DialogConfig.PNJ3_2_AFTER);
        GIVE_OBJECT6_3.eventHandler = createGiveObjectAction(Interaction.PNJ5_2, Object.OBJ6_2, DialogConfig.PNJ5_2_AFTER);

        // Récupération des skins pour changer d'apparence à la suite d'objectifs secondaires
        GIVE_SKIN.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Player.getINSTANCE().getSkinAvailables().add(1);
            Interaction.PNJ14_2.setInteractionDone(true);
            Inventory.getINSTANCE().remove(Object.OBJ7_3);
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ14_2_AFTER.getText());
            Movement.resumeMovement();
        });
        GIVE_SKIN2.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Player.getINSTANCE().getSkinAvailables().add(4);
            Interaction.PNJ14_2.setInteractionDone(true);
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ19_SUCCESS.getText());
            Movement.resumeMovement();
        });

        // Récupération d'argent suite à des objectifs secondaires et erreurs correspondantes
        GIVE_MONEY_CAT.eventHandler = createGiveMoneyAction(Interaction.CAT2, 500, DialogConfig.CAT2_AFTER);
        GIVE_MONEY_FOX.eventHandler = createGiveMoneyAction(Interaction.FOX, 500,DialogConfig.FOX_SUCCESS);
        GIVE_MONEY_STATUE.eventHandler = createGiveMoneyAction(Interaction.STATUE, 500,DialogConfig.STATUE_SUCCESS);
        GIVE_MONEY_PNJ6.eventHandler = createGiveMoneyAction(Interaction.PNJ6, 500,DialogConfig.PNJ6_SUCCESS);

        ERROR_FOX.eventHandler = createRiddleErrorAction(DialogConfig.FOX_ERROR);
        ERROR_STATUE.eventHandler = createRiddleErrorAction(DialogConfig.STATUE_ERROR);
        ERROR_PNJ6.eventHandler = createRiddleErrorAction(DialogConfig.PNJ6_ERROR);
        ERROR_PNJ19.eventHandler = createRiddleErrorAction(DialogConfig.PNJ19_ERROR);

        // Récupération des objets par leurs propriétaires initiaux
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
            DialogLayout.getINSTANCE().addMoney(1000);
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

        // Actions de la quête du coffre caché
        CHEST_HIDDEN.eventHandler = createInteractionAndSpriteChange(Interaction.CHEST_HIDDEN, DialogConfig.CHEST_CLOSED, 1,
                Interaction.CHEST_HIDDEN, Interaction.CHEST_CLOSED, Sprite.CHEST_CLOSED);
        CHEST_CLOSED.eventHandler = createInteractionAndSpriteChangeAndGiveMoney(Interaction.CHEST_CLOSED, DialogConfig.CHEST_OPENED, 1,
                Interaction.CHEST_CLOSED, Interaction.CHEST_OPENED, Sprite.CHEST_OPENED, 2000);

        // Achats au marchand
        BUY_SKIN.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(DialogLayout.getINSTANCE().getMoney() < 5000){
                DialogLayout.getINSTANCE().removeContent();
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_NOT_ENOUGH_MONEY.getText());
                Movement.setMoved(true);
                Movement.resumeMovement();
                return;
            }
            DialogLayout.getINSTANCE().removeMoney(5000);
            Player.getINSTANCE().getSkinAvailables().add(2);
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_SKIN_BUYED.getText());
            Movement.setMoved(true);
            Movement.resumeMovement();
        });
        BUY_DYNAMITE.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            if(DialogLayout.getINSTANCE().getMoney() < 10000){
                DialogLayout.getINSTANCE().removeContent();
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_NOT_ENOUGH_MONEY.getText());
                Movement.setMoved(true);
                Movement.resumeMovement();
            }
        });
        PACKAGE_REPAIR.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().remove(Object.OBJ6_2);
            Inventory.getINSTANCE().add(Object.OBJ6);
            Movement.resumeMovement();
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_PACKAGE_AFTER.getText());
            Movement.setMoved(true);
        });
        QUEST_DYNAMITE.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            DialogLayout.getINSTANCE().removeContent();
            if(Player.getINSTANCE().getCurrentSkin() == 4){
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_QUEST_AFTER.getText());
                Inventory.getINSTANCE().add(Object.OBJ4);
                Interaction.PNJ10.setInteractionDone(true);
            }
            else if(Player.getINSTANCE().getSkinAvailables().contains(4)){
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_QUEST_BETWEEN.getText());
            }
            else{
                DialogLayout.getINSTANCE().setText(DialogConfig.PNJ10_QUEST_BEFORE.getText());
            }
            Movement.resumeMovement();
            Movement.setMoved(true);
        });

        DEATH_STAR.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CinematicConfig.DEATH_STAR.getEventHandler());
            KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Key.SPACE.getKeyCode(), false, false, false, false) );
        });


        // Récupération des plumes de poulet sur la planète 3
        CHICKEN1.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Inventory.getINSTANCE().add(Object.OBJ7_1);
            Pair<Integer, Integer> p = Player.getINSTANCE().getPosition();
            switch (Player.getINSTANCE().getDirection()){
                case UP:
                    p = new Pair<>( p.getKey(), p.getValue() - 1);
                    break;
                case DOWN:
                    p = new Pair<>( p.getKey(), p.getValue() + 1);

                    break;
                case RIGHT:
                    p = new Pair<>( p.getKey() + 1, p.getValue());
                    break;
                case LEFT:

                    p = new Pair<>( p.getKey() - 1, p.getValue());
                    break;
            }
            Cell cell = MapConfig.getLastCell( 9, p.getKey(), p.getValue());
            BlockingCell blockingCell;
            if(cell instanceof BlockingCell){
                blockingCell = (BlockingCell) cell;
                blockingCell.getInteraction().setInteractionDone(true);
            }

            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN_AFTER.getText());
            Movement.resumeMovement();
        });
        CHICKEN2.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Inventory.getINSTANCE().remove(Object.OBJ7_1);
            Inventory.getINSTANCE().add(Object.OBJ7_2);
            Pair<Integer, Integer> p = Player.getINSTANCE().getPosition();
            switch (Player.getINSTANCE().getDirection()){
                case UP:
                    p = new Pair<>( p.getKey(), p.getValue() - 1);
                    break;
                case DOWN:
                    p = new Pair<>( p.getKey(), p.getValue() + 1);

                    break;
                case RIGHT:
                    p = new Pair<>( p.getKey() + 1, p.getValue());
                    break;
                case LEFT:

                    p = new Pair<>( p.getKey() - 1, p.getValue());
                    break;
            }
            Cell cell = MapConfig.getLastCell( 9, p.getKey(), p.getValue());
            BlockingCell blockingCell;
            if(cell instanceof BlockingCell){
                blockingCell = (BlockingCell) cell;
                blockingCell.getInteraction().setInteractionDone(true);
            }

            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN_AFTER.getText());
            Movement.resumeMovement();
        });
        CHICKEN3.eventHandler = ((EventHandler<ActionEvent>) (action) ->{
            Inventory.getINSTANCE().remove(Object.OBJ7_2);
            Inventory.getINSTANCE().add(Object.OBJ7_3);
            Pair<Integer, Integer> p = Player.getINSTANCE().getPosition();
            switch (Player.getINSTANCE().getDirection()){
                case UP:
                    p = new Pair<>( p.getKey(), p.getValue() - 1);
                    break;
                case DOWN:
                    p = new Pair<>( p.getKey(), p.getValue() + 1);

                    break;
                case RIGHT:
                    p = new Pair<>( p.getKey() + 1, p.getValue());
                    break;
                case LEFT:

                    p = new Pair<>( p.getKey() - 1, p.getValue());
                    break;
            }
            Cell cell = MapConfig.getLastCell( 9, p.getKey(), p.getValue());
            BlockingCell blockingCell;
            if(cell instanceof BlockingCell){
                blockingCell = (BlockingCell) cell;
                blockingCell.getInteraction().setInteractionDone(true);
            }

            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.CHICKEN_AFTER.getText());
            Movement.resumeMovement();
        });

        // Pemet de passer le pacman sur la planète 3
        PACMANSKIP.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Interaction.PACMAN_IN.setInteractionDone(true);
            DialogLayout.getINSTANCE().removeContent();
            DialogLayout.getINSTANCE().setText(DialogConfig.PACMAN_WON.getText());
            Inventory.getINSTANCE().add(Object.OBJ3);
            Player.getINSTANCE().getSkinAvailables().add(3);
            Movement.setMoved(true);
            Movement.resumeMovement();
        });

        // Utilisation de la dynamite pour débloquer le reste de la planète 4
        USE_DYNAMITE.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            Inventory.getINSTANCE().remove(Object.OBJ4);
            Movement.resumeMovement();
            Cell toRemove = null;
            for(Cell cell : Movement.getMap().getCells()){
                if(cell instanceof BlockingCell && ((BlockingCell) cell).getInteraction() != null &&
                        ((BlockingCell) cell).getInteraction().equals(Interaction.ROCK)){
                    toRemove = cell;
                }
            }
            Movement.getMap().remove(toRemove);
            Interaction.ROCK.setInteractionDone(true);
            DialogLayout.getINSTANCE().removeContent();
            Movement.setMoved(true);
        });

        // Action du bouton Retour
        RETURN.eventHandler = ((EventHandler<ActionEvent>) (action) -> {
            DialogLayout.getINSTANCE().removeContent();
            Movement.resumeMovement();
            Movement.setMoved(true);
        });
    }

    /**
     * Evénement rattaché à chacune des actions.
     */
    private EventHandler eventHandler;

    /**
     * Constructeur vide d'une action.
     */
    Action() {}

    /**
     * Permet de récupérer l'événément rattaché à chacune des actions.
     * @return EventHandler
     */
    public EventHandler getEventHandler() {
        return eventHandler;
    }

    /**
     * Permet de créer un événement de téléportation pour faire revenir Leskak au spawn de la planète où il est.
     * @param map Carte de départ de la planète
     * @return EventHandler
     */
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

    /**
     * Permet de créer un événement pour que Leskak puisse récupérer un objet.
     * @param interaction Définit avec qui Leskak doit intégir pour récupérer l'objet
     * @param object L'objet que va récupérer Leskak
     * @param dialogConfig Le dialogue qui va s'afficher avec cette action
     * @return EventHandler
     */
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

    /**
     * Permet de créer un événement pour que Leskak puisse récupérer de l'argent.
     * @param interaction Définit avec qui Leskak doit intégir pour récupérer l'objet
     * @param money L'argent que va récupérer Leskak
     * @param dialogConfig Le dialogue qui va s'afficher avec cette action
     * @return EventHandler
     */
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

    /**
     * Permet de créer un événement pour que Leskak puisse récupérer de l'argent ainsi qu'un objet.
     * @param interaction Définit avec qui Leskak doit intégir pour récupérer l'objet et l'argent
     * @param object L'objet que va récupérer Leskak
     * @param dialogConfig Le dialogue qui va s'afficher avec cette action
     * @param money L'argent que va récupérer Leskak
     * @return EventHandler
     */
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

    /**
     * Permet de créer un événement permettant de définir le message d'erreur suite à une mauvaise réponse sur l'énigme.
     * @param dialogError Le dialogue qui doit apparaître en cas d'erreur
     * @return EventHandler
     */
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

    /**
     * Permet de créer un événénement permettant d'intéragir avec un objet et de faire changer son sprite.
     * @param interaction Définit avec qui Leskak doit intégir pour récupérer l'objet
     * @param dialogConfig Dialogue à afficher lors de l'intéraction
     * @param nbMap Carte sur laquelle se déroule l'intéraction
     * @param toRemove Intéraction à enlever à la suite de cette intéraction
     * @param toAdd Intéraction à mettre en place à la suite de cette intéraction
     * @param sprite Sprite de l'objet une fois que l'intéraction est faites
     * @return EventHandler
     */
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

    /**
     * Permet de créer un événénement permettant d'intéragir avec un objet et de faire changer son sprite, ainsi que de donner de l'argent à Leskak.
     * @param interaction Définit avec qui Leskak doit intégir pour récupérer l'objet
     * @param dialogConfig Dialogue à afficher lors de l'intéraction
     * @param nbMap Carte sur laquelle se déroule l'intéraction
     * @param toRemove Intéraction à enlever à la suite de cette intéraction
     * @param toAdd Intéraction à mettre en place à la suite de cette intéraction
     * @param sprite Sprite de l'objet une fois que l'intéraction est faites
     * @param money Somme à ajouter à la bourse de Leskak
     * @return EventHandler
     */
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
