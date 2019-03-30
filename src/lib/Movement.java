package lib;

import config.*;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * Classe servant à gérer les mouvements du joueur, ses déplacements sur la grille de jeu.
 */
public class Movement {

    /**
     * La carte où se déplace le joueur.
     */
    private static Map map = new Map();

    /**
     * Si true, le joueur peut effectuer une intéraction, sinon false.
     */
    private static boolean interactionAllowed = false;

    /**
     * Définit le premier déplacement du joueur et quand on change de direction.
     */
    private static EventHandler<KeyEvent> setupEventHandler;

    /**
     * Tant que la touche n'est pas relâchée, on applique cet événement pour les déplacements, afin d'éviter de cliquer plusieurs fois dans la même direction.
     */
    private static EventHandler<KeyEvent> automaticEventHandler;

    /**
     * Evénement qui définit la fin du déplacement.
     */
    private static EventHandler<KeyEvent> stopEventHandler;

    /**
     * Dernière touche sur laquelle l'utilisateur a appuyé.
     */
    private static Key lastKeyTyped;

    /**
     * Dernière touche qui a été retenue par l'automaticEventHandler.
     */
    private static Key automaticLastKey;

    /**
     * Vaut true si le Leskak ne bouge pas, false sinon.
     */
    private static boolean stopped = true;

    /**
     * Vaut true quand Leskak change de direction, false sinon.
     */
    private static boolean directionChanged = false;

    /**
     * Vaut true si on a relâchée la dernière touche appuyée, false sinon.
     */
    private static boolean isLastKeyTypedReleased = true;

    /**
     * AnimationSet de Leskak.
     */
    private static AnimationSet animationSet = AnimationSet.PLAYER_STOP;

    /**
     * Délai de déplacement entre deux mouvements.
     */
    private static int delay = 250;

    /**
     * Configure, en ajoutant les événements qu'il faut, les déplacements du joueur.
     * @param scene scene sur laquelle se trouve Leskak
     */
    public static void configPlayerEventHandler(Scene scene) {
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, KeyboardConfig.ENTER.getEventHandler());
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, KeyboardConfig.ESCAPE.getEventHandler());
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, KeyboardConfig.TELEPORT.getEventHandler());
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, KeyboardConfig.CHANGE_SKIN.getEventHandler());
        Movement.setupEventHandler = Movement.setupMovementEvent();
        Movement.automaticEventHandler = Movement.automaticMovementEvent();
        Movement.stopEventHandler = (event -> {
            if(lastKeyTyped != null && event.getCode().equals(lastKeyTyped.getKeyCode())){
                isLastKeyTypedReleased = true;
            }
        });

        scene.removeEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        scene.removeEventHandler(KeyEvent.KEY_PRESSED ,Movement.automaticEventHandler);
        scene.addEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, stopEventHandler);
    }

    /**
     * Définit l'événement lorsqu'on maintient une touche appuyée.
     * @return EventHandler
     */
    private static EventHandler<KeyEvent> automaticMovementEvent(){

        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(Movement.delay));
        pt.setOnFinished(event -> {

            if(directionChanged){
                Movement.automaticLastKey = Movement.lastKeyTyped;
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.automaticLastKey.getKeyCode(), false, false, false, false) );
                directionChanged = false;
                return;
            }

            if(isLastKeyTypedReleased){
                animationSet = animationSet.getStopAnimationSet();
                Player.getINSTANCE().setSprite(animationSet.getSpriteDirection(Player.getINSTANCE().getDirection()));
                stopped = true;
                return;
            }
            else {
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.automaticLastKey.getKeyCode(), false, false, false, false) );
            }
        });
        return key -> {

            if(!stopped && !Movement.automaticLastKey.equals(Movement.lastKeyTyped)){
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                stopped = false;
                animationSet = AnimationSet.getAnimationSet(AnimationSet.getNbAnim(Math.floorDiv(
                        AnimationSet.getAnimationSetThatHave( Player.getINSTANCE().getSprite()).ordinal(), 4)));
                directionChanged = true;
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
                pt.play();
                return;
            }

            animationSet = AnimationSet.getAnimationSet(AnimationSet.getNbAnim(Math.floorDiv(
                    AnimationSet.getAnimationSetThatHave( Player.getINSTANCE().getSprite()).ordinal(), 4)));
            movePlayer(key);
            stopped = false;

            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
            pt.play();
        };
    }

    /**
     * Définit l'événement lorsqu'on commence à appuyer sur une touche.
     * @return EventHandler
     */
    private static EventHandler<KeyEvent> setupMovementEvent(){
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(10));
        pt.setOnFinished(event -> {
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED ,Movement.setupEventHandler);
        });
        return key -> {
            if(!Movement.isMovementKey(key) ) return;
            lastKeyTyped = Key.getKeyofKeyCode(key.getCode());
            if(Movement.automaticLastKey != null && Movement.automaticLastKey.equals(Movement.lastKeyTyped) && !stopped) return;

            if(isLastKeyTypedReleased)
                isLastKeyTypedReleased = false;

            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.automaticEventHandler);
            KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Movement.lastKeyTyped.getKeyCode(), false, false, false, false) );
            pt.play();
        };
    }

    /**
     * Permet le déplacement du joueur selon la touche qui a été mise en paramètre.
     * @param key touche sur laquelle l'utilisateur a appuyé
     */
    private static void movePlayer(KeyEvent key){
        Player player = Player.getINSTANCE();
        TranslateTransition tt = new TranslateTransition(Duration.millis(Movement.delay));

        boolean isTransitionCell = false;
        TransitionCell transitionCell = null;
        if (isTransitionCell(player.getPosition().getKey(), player.getPosition().getValue())) {
            isTransitionCell = true;
            transitionCell = getTransitionCell(player.getPosition().getKey(), player.getPosition().getValue());
        }
        double x = 0;
        double y = 0;
        if (key.getCode() == Key.UP.getKeyCode()) {
            y = ((player.getPosition().getValue() == 0
                    || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() - 1)) ? 0 : -50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey(),
                    ((player.getPosition().getValue() == 0
                            || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() - 1)) ?
                            player.getPosition().getValue() : player.getPosition().getValue() - 1)));
            player.setDirection(Direction.UP);
            Movement.interactionAllowed = true;
        }
        if (key.getCode() == Key.DOWN.getKeyCode()) {
            y = (player.getPosition().getValue() == GameLayout.getINSTANCE().getNbRows() - 1
                    || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() + 1) ? 0 : 50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey(),
                    player.getPosition().getValue() == GameLayout.getINSTANCE().getNbRows() - 1
                            || !isAccessibleCell(player.getPosition().getKey(), player.getPosition().getValue() + 1) ?
                            player.getPosition().getValue() : player.getPosition().getValue() + 1));
            player.setDirection(Direction.DOWN);
            Movement.interactionAllowed = true;
        }
        if (key.getCode() == Key.RIGHT.getKeyCode()) {
            x = (player.getPosition().getKey() == GameLayout.getINSTANCE().getNbColumns() - 1
                    || !isAccessibleCell(player.getPosition().getKey() + 1, player.getPosition().getValue()) ? 0 : 50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey() == GameLayout.getINSTANCE().getNbColumns() - 1
                            || !isAccessibleCell(player.getPosition().getKey() + 1, player.getPosition().getValue()) ?
                            player.getPosition().getKey() : player.getPosition().getKey() + 1,
                    player.getPosition().getValue()));
            player.setDirection(Direction.RIGHT);
            Movement.interactionAllowed = true;
        }
        if (key.getCode() == Key.LEFT.getKeyCode()) {
            x = (player.getPosition().getKey() == 0
                    || !isAccessibleCell(player.getPosition().getKey() - 1, player.getPosition().getValue()) ? 0 : -50 );
            player.setPosition(new Pair<>(
                    player.getPosition().getKey() == 0
                            || !isAccessibleCell(player.getPosition().getKey() - 1, player.getPosition().getValue()) ?
                            player.getPosition().getKey() : player.getPosition().getKey() - 1,
                    player.getPosition().getValue()));
            player.setDirection(Direction.LEFT);
            Movement.interactionAllowed = true;
        }
        if (key.getCode() != Key.ENTER.getKeyCode()) {
            DialogLayout.getINSTANCE().removeContent();
        }
        refreshPlayerSprite();
        Cell cell = MapConfig.getSecondCell(MapConfig.getINSTANCE().getMaps().indexOf(map), player.getPosition().getKey(), player.getPosition().getValue());

        if(Movement.isMovementKey(key)){
            Movement.automaticLastKey = Key.getKeyofKeyCode(key.getCode());
        }

        if (isTransitionCell && transitionCell.getDirection().equals(player.getDirection())) {
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, transitionCell.getEventHandler());
        } else {

            ImageView imageView = player.getImage();
            tt.setNode(imageView);
            tt.setByX(x);
            tt.setByY(y);
            tt.setInterpolator(Interpolator.LINEAR);

            tt.setOnFinished(event -> {
                if(map.isFogOfWar())
                    map.updateFogOfWar();
                if(PacMan.isPacmanMovement() && cell != null && cell.getSprite().equals(Sprite.PACGUM)){
                    map.getGridPane().getChildren().remove(cell.getImage());
                    map.getCells().remove(cell);
                    PacMan.setRemainingDots(PacMan.getRemainingDots() - 1);
                }
                if(PacMan.isPacmanMovement())
                    PacMan.moveGhosts();
            });
            tt.play();
        }
    }

    /**
     * Permet de changer la direction du sprite du joueur lorsque l'utilisateur fait changer de direction à Leskak.
     */
    private static void refreshPlayerSprite(){
        Player player = Player.getINSTANCE();
        switch (player.getDirection()){
            case UP:
                player.setSprite(animationSet.getUp());
                break;
            case DOWN:
                player.setSprite(animationSet.getDown());
                break;
            case RIGHT:
                player.setSprite(animationSet.getRight());
                break;
            case LEFT:
                player.setSprite(animationSet.getLeft());
                break;
        }
    }

    /**
     * Renvoie true si la touche entrée par l'utilisateur est une touche de déplacement, false sinon.
     * @param key touche sur laquelle l'utilisateur a appuyé
     * @return boolean
     */
    private static boolean isMovementKey(KeyEvent key){
        return key.getCode() == Key.UP.getKeyCode() || key.getCode() == Key.DOWN.getKeyCode() ||
                key.getCode() == Key.RIGHT.getKeyCode() || key.getCode() == Key.LEFT.getKeyCode();
    }

    /**
     * Renvoie true si la case sur laquelle veut aller Leskak est accessible, false sinon.
     * @param col indice de la colonne de la cellule
     * @param row indice de la ligne de la cellule
     * @return boolean
     */
    private static boolean isAccessibleCell(Integer col, Integer row){
        while(Movement.map.getBlockingCellIterator().hasNext()){
            BlockingCell blockingCell = Movement.map.getBlockingCellIterator().next();
            if(blockingCell.getPosition().getKey().equals(col) && blockingCell.getPosition().getValue().equals(row)){
                Movement.map.getBlockingCellIterator().reset();
                return false;
            }
        }
        return true;
    }

    /**
     * Renvoie true si la cellule sur laquelle veut aller Leskak est une cellule de transition false sinon.
     * @param col indice de la colonne de la cellule
     * @param row indice de la ligne de la cellule
     * @return boolean
     */
    private static boolean isTransitionCell(Integer col, Integer row){
        while(Movement.map.getTransitionCellIterator().hasNext()){
            TransitionCell transitionCell = Movement.map.getTransitionCellIterator().next();
            if(transitionCell.getPosition().getKey().equals(col) && transitionCell.getPosition().getValue().equals(row)){
                Movement.map.getTransitionCellIterator().reset();
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoie la cellule de transition à une position donnée en paramètre.
     * @param col indice de la colonne de la cellule de transition
     * @param row indice de la ligne de la cellule de transition
     * @return TransitionCell
     */
    private static TransitionCell getTransitionCell(Integer col, Integer row){
        for (Cell cell : Movement.map
        ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                if(cell instanceof TransitionCell)
                    return (TransitionCell) cell;
            }
        }
        return null;
    }

    /**
     * Empêche le joueur d'effectuer un mouvement (par exemple lorsque le joueur doit répondre à un pnj).
     */
    public static void removeMovement(){
        GameLayout.getINSTANCE().getPane().setFocusTraversable(false);
        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
    }

    /**
     * Autorise de nouveau à Leskak d'effectuer des mouvements.
     */
    public static void resumeMovement(){
        GameLayout.getINSTANCE().getPane().setFocusTraversable(true);
        GameLayout.getINSTANCE().getPane().requestFocus();
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Movement.setupEventHandler);
    }

    /**
     * Getter de la carte sur laquelle se déplace Leskak.
     * @return Map
     */
    public static Map getMap() {
        return Movement.map;
    }

    /**
     * Setter de la carte sur laquelle va se déplacer Leskak.
     * @param map carte sur va se déplacer Leskak
     */
    public static void setMap(Map map) {
        Movement.map = map;
    }

    /**
     * Getter du booléen permettant de savoir si Leskak peut intégir ou non.
     * @return boolean
     */
    public static boolean isInteractionAllowed() {
        return Movement.interactionAllowed;
    }

    /**
     * Setter du booléen permettant de savoir si Leskak peut intégir ou non.
     * @param interactionAllowed true si Leskak peut intéragir, false sinon
     */
    public static void setInteractionAllowed(boolean interactionAllowed) {
        Movement.interactionAllowed = interactionAllowed;
    }

    /**
     * Getter de l'AnimationSet de Leskak.
     * @return AnimationSet
     */
    public static AnimationSet getAnimationSet() {
        return Movement.animationSet;
    }

    /**
     * Setter de l'AnimationSet de Leskak.
     * @param animationSet nouvel AnimationSet de Leskak
     */
    public static void setAnimationSet(AnimationSet animationSet) {
        Movement.animationSet = animationSet;
    }

    /**
     * Getter du booléen permettant de savoir si Leskak est en mouvement ou non.
     * @return boolean
     */
    public static boolean isStopped() {
        return Movement.stopped;
    }

    /**
     * Getter de l'événement qui définit la fin du déplacement.
     * @return EventHandler
     */
    public static EventHandler<KeyEvent> getStopEventHandler() {
        return Movement.stopEventHandler;
    }

    /**
     * Setter permettant de savoir si la dernière touche a été relâchée.
     * @param isLastKeyTypedReleased true si elle a été relâchée, false sinon
     */
    public static void setIsLastKeyTypedReleased(boolean isLastKeyTypedReleased) {
        Movement.isLastKeyTypedReleased = isLastKeyTypedReleased;
    }

    /**
     * Getter du délai de déplacement entre deux mouvements.
     * @return int
     */
    public static int getDelay() {
        return Movement.delay;
    }
}
