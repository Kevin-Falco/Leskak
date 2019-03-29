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
    CINEMATIC4,
    CINEMATIC5,
    CINEMATIC6,

    // Configuration des cinématiques de fin
    CINEMATIC7,
    CINEMATIC8,
    CINEMATIC9,

    CINEMATIC10,
    CINEMATIC11,
    CINEMATIC12,

    // Configuration de la sortie du jeu
    END_GAME,
    ;

    static {
        // Configuration de la sortie du jeu
        END_GAME.eventHandler = (event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                MainLayout.getSTAGE().close();
                Main.getStage().close();
            }
        });

        // Configuration des cinématiques de départ
        CINEMATIC6.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC6, Sprite.CINEMATIC6, Interaction.RETURN_GAME.getEventHandler());
        CINEMATIC5.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC5, Sprite.CINEMATIC5, CinematicConfig.CINEMATIC6.getEventHandler());
        CINEMATIC4.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC4, Sprite.CINEMATIC4, CinematicConfig.CINEMATIC5.getEventHandler());
        CINEMATIC3.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC3, Sprite.CINEMATIC3, CinematicConfig.CINEMATIC4.getEventHandler());
        CINEMATIC2.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC2, Sprite.CINEMATIC2, CinematicConfig.CINEMATIC3.getEventHandler());
        CINEMATIC1.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC1, Sprite.CINEMATIC1, CinematicConfig.CINEMATIC2.getEventHandler());

        // Configuration des cinématiques de fin
        CINEMATIC9.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC9, Sprite.CINEMATIC9, CinematicConfig.END_GAME.getEventHandler());
        CINEMATIC8.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC8, Sprite.CINEMATIC8, CinematicConfig.CINEMATIC9.getEventHandler());
        CINEMATIC7.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC7, Sprite.CINEMATIC7, CinematicConfig.CINEMATIC8.getEventHandler());

        CINEMATIC12.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC12, Sprite.CINEMATIC12, CinematicConfig.END_GAME.getEventHandler());
        CINEMATIC11.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC11, Sprite.CINEMATIC11, CinematicConfig.CINEMATIC12.getEventHandler());
        CINEMATIC10.eventHandler = createCinematicEventHandler(CinematicConfig.CINEMATIC10, Sprite.CINEMATIC10, CinematicConfig.CINEMATIC11.getEventHandler());
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
