package sample;

import config.Sprite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lib.LauncherLayout;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("LESKAK");
        Scene scene = LauncherLayout.getSCENE();
        scene.setRoot(LauncherLayout.getINSTANCE().getvBox());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Sprite.RIGHT_P1.getSpritePath()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
