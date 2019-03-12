package config;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lib.MainLayout;
import lib.Movement;

public enum CinematicConfig {
    TEST1,
    TEST2,
    TEST3;

    static {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(1));
        TEST1.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
            event.consume();
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
        TEST2.eventHandler = ((EventHandler<KeyEvent>) (event) -> {
            event.consume();
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
    }

    private EventHandler eventHandler;

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public static void setupGame(){
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, CinematicConfig.TEST3.getEventHandler());
        KeyEvent.fireEvent(MainLayout.getSCENE(),new KeyEvent(KeyEvent.KEY_PRESSED, " ", " ", Key.ENTER.getKeyCode(), false, false, false, false) );
    }
}
