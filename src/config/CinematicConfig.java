package config;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lib.*;
import sample.Main;

public enum CinematicConfig {
    TEST1,
    TEST2,
    TEST3,
    DEATH_STAR,
    END_GAME,
    ;

    static {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(1));
        //TEST1.eventHandler = createCinematicEvent(false, Sprite.CINEMATIC, Interaction.RETURN_GAME.getEventHandler(), TEST1.getEventHandler());
        TEST1.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                Pane pane = new Pane();
                ImageView imageView = new ImageView(Sprite.CINEMATIC.getSpritePath());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(MainLayout.getWIDTH());
                pane.getChildren().add(imageView);
                MainLayout.getSCENE().setRoot(pane);
                pt.play();
                pt.setOnFinished(event1 -> {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Interaction.RETURN_GAME.getEventHandler());
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, TEST1.getEventHandler());
                });

            }
        });
        //TEST2.eventHandler = createCinematicEvent(false, Sprite.CINEMATIC3, TEST1.getEventHandler(), TEST2.getEventHandler());
        TEST2.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                Pane pane = new Pane();
                ImageView imageView = new ImageView(Sprite.CINEMATIC3.getSpritePath());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(MainLayout.getWIDTH());
                pane.getChildren().add(imageView);
                MainLayout.getSCENE().setRoot(pane);
                pt.play();
                pt.setOnFinished(event1 -> {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, TEST1.getEventHandler());
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, TEST2.getEventHandler());
                });
            }
        });
        //TEST3.eventHandler = createCinematicEvent(true, Sprite.CINEMATIC2, TEST2.getEventHandler(), TEST3.getEventHandler());
        TEST3.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
            Movement.removeMovement();
            Pane pane = new Pane();
            ImageView imageView = new ImageView(Sprite.CINEMATIC2.getSpritePath());
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(MainLayout.getWIDTH());
            pane.getChildren().add(imageView);
            MainLayout.getSCENE().setRoot(pane);
            pt.play();
            pt.setOnFinished(event1 -> {
                MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, TEST2.getEventHandler());
                MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, TEST3.getEventHandler());
            });
        });

        DEATH_STAR.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
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

        END_GAME.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                MainLayout.getSTAGE().close();
                Main.getStage().close();
            }
        });
    }

    private EventHandler eventHandler;

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public static void setupGame(){
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CinematicConfig.TEST3.getEventHandler());
        KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Key.ENTER.getKeyCode(), false, false, false, false) );
    }

    private static EventHandler createCinematicEvent(boolean removeMovement, Sprite spriteCinematic, EventHandler toAdd, EventHandler toRemove){
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(1));
        return ((EventHandler<KeyEvent>) (event) -> {
            if(event.getCode() == Key.SPACE.getKeyCode()) {
                if(removeMovement)
                    Movement.removeMovement();
                Pane pane = new Pane();
                ImageView imageView = new ImageView(spriteCinematic.getSpritePath());
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(MainLayout.getWIDTH());
                pane.getChildren().add(imageView);
                MainLayout.getSCENE().setRoot(pane);
                pt.play();
                pt.setOnFinished(event1 -> {
                    MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, toAdd);
                    MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, toRemove);
                });
            }
        });
    }
}
