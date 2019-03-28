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
    DEATH_STAR,

    // Configuration de la sortie du jeu
    END_GAME,
    ;

    static {

        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(1));

        // Configuration des cinématiques de départ
        CINEMATIC1.eventHandler = (event -> {
            Movement.removeMovement();
            Pane pane = new Pane();
            ImageView imageView = new ImageView(Sprite.CINEMATIC.getSpritePath());
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(MainLayout.getWIDTH());
            pane.getChildren().add(imageView);
            MainLayout.getSCENE().setRoot(pane);
            pt.play();
            pt.setOnFinished(event1 -> {
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CINEMATIC2.getEventHandler());
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, CINEMATIC1.getEventHandler());
            });
        });

        CINEMATIC2.eventHandler = (event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                Pane pane = new Pane();
                ImageView imageView = new ImageView(Sprite.CINEMATIC2.getSpritePath());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(MainLayout.getWIDTH());
                pane.getChildren().add(imageView);
                MainLayout.getSCENE().setRoot(pane);
                pt.play();
                pt.setOnFinished(event1 -> {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CINEMATIC3.getEventHandler());
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, CINEMATIC2.getEventHandler());
                });
            }
        });

        CINEMATIC3.eventHandler = (event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                Pane pane = new Pane();
                ImageView imageView = new ImageView(Sprite.CINEMATIC3.getSpritePath());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(MainLayout.getWIDTH());
                pane.getChildren().add(imageView);
                MainLayout.getSCENE().setRoot(pane);
                pt.play();
                pt.setOnFinished(event1 -> {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Interaction.RETURN_GAME.getEventHandler());
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, CINEMATIC3.getEventHandler());
                });

            }
        });

        // Configuration des cinématiques de fin
        DEATH_STAR.eventHandler = (event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                Pane pane = new Pane();
                ImageView imageView;
                if(Player.getINSTANCE().getSkinAvailables().size() == 5)
                    imageView = new ImageView(Sprite.CINEMATIC2.getSpritePath());
                else
                    imageView = new ImageView(Sprite.CINEMATIC.getSpritePath());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(MainLayout.getWIDTH());
                pane.getChildren().add(imageView);
                MainLayout.getSCENE().setRoot(pane);
                pt.play();
                pt.setOnFinished(event1 -> {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, END_GAME.getEventHandler());
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, DEATH_STAR.getEventHandler());
                });
            }
        });

        // Configuration de la sortie du jeu
        END_GAME.eventHandler = (event -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                MainLayout.getSTAGE().close();
                Main.getStage().close();
            }
        });
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
        KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Key.ENTER.getKeyCode(), false, false, false, false) );
    }
}
