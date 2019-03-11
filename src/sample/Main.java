package sample;

import config.MapConfig;
import config.Sprite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import lib.GameLayout;
import lib.Movement;
import lib.MainLayout;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("LESKAK");
        Scene scene = MainLayout.getSCENE();
        scene.setRoot(MainLayout.getINSTANCE().getGridPane());
        Movement.configPlayerEventHandler(scene);
        MapConfig.getINSTANCE();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
