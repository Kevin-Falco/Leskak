package config;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lib.*;
import sample.Main;

/**
 * Enumération des différentes cinématiques, c'est à dire celles au début du jeu présentant les régles ainsi que l'histoire
 * de départ, puis celles de fin de jeu avec les deux fins alternatives.
 */
public enum CinematicConfig {

    // Configuration des cinématiques de départ
    CINEMATIC1,
    CINEMATIC2,
    CINEMATIC3,

    // Configuration des cinématiques de fin
    DEATH_STAR1,
    DEATH_STAR2,

    // Configuration de la sortie du jeu
    END_GAME,
    ;

    static {

        // Configuration des cinématiques de départ
        CINEMATIC3.eventHandler = createCinematicEventHandler(CINEMATIC3, Sprite.CINEMATIC3, Interaction.RETURN_GAME.getEventHandler());
        CINEMATIC2.eventHandler = createCinematicEventHandler(CINEMATIC2, Sprite.CINEMATIC2, CINEMATIC3.getEventHandler());
        CINEMATIC1.eventHandler = createCinematicEventHandler(CINEMATIC1, Sprite.CINEMATIC, CINEMATIC2.getEventHandler());

        // Configuration de la sortie du jeu
        END_GAME.eventHandler = (event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                MainLayout.getSTAGE().close();
                Main.getStage().close();
            }
        });

        // Configuration des cinématiques de fin
        DEATH_STAR1.eventHandler = createCinematicEventHandler(DEATH_STAR1, Sprite.CINEMATIC4, END_GAME.getEventHandler());
        DEATH_STAR2.eventHandler = createCinematicEventHandler(DEATH_STAR2, Sprite.CINEMATIC5, END_GAME.getEventHandler());
    }

    /**
     * Evénement rattaché à chacune des cinématiques.
     */
    private EventHandler<KeyEvent> eventHandler;

    /**
     * Permet de récupérer l'événément rattaché à chacune des actions.
     * @return EventHandler
     */
    public EventHandler<KeyEvent> getEventHandler() {
        return this.eventHandler;
    }

    /**
     * Met en place le jeu et active la première cinématique du jeu pour permettre au joueur de commencer l'aventure de Leskak.
     */
    public static void setupGame(){
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CinematicConfig.CINEMATIC1.getEventHandler());
        KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Key.SPACE.getKeyCode(), false, false, false, false) );
    }

    /**
     * Permet de mettre en place une cinématique et de déterminer quelle sera le prochain événement (nouvelle cinématique ou retour au jeu).
     * @param cinematicConfig cinématique à enlever
     * @param sprite cinématique à ajouter
     * @param eventHandler prochaine action après avoir cliquer sur la touche pour passer la cinémtique
     * @return EventHandler
     */
    private static EventHandler<KeyEvent> createCinematicEventHandler(CinematicConfig cinematicConfig, Sprite sprite, EventHandler<KeyEvent> eventHandler){
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(1));
        return (event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                Pane pane = new Pane();
                ImageView imageView = new ImageView(sprite.getSpritePath());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(MainLayout.getWIDTH());
                pane.getChildren().add(imageView);
                MainLayout.getSCENE().setRoot(pane);
                pt.play();
                pt.setOnFinished(event1 -> {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, cinematicConfig.getEventHandler());
                });
            }
        });
    }
}
