package sample;

import config.Sprite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lib.LauncherLayout;


public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Leskak");
        Scene scene = LauncherLayout.getSCENE();
        scene.setRoot(LauncherLayout.getINSTANCE().getvBox());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Sprite.PLAYER_DOWN_STOP.getSpritePath()));
        primaryStage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
