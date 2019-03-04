package sample;

import config.MapConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.GameLayout;
import lib.Movement;
import lib.MainLayout;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("LESKAK");
        Scene scene = GameLayout.getSCENE();
        scene.setRoot(MainLayout.getINSTANCE().getGridPane());
        Movement.configPlayerEventHandler(scene);
        MapConfig.MAP1.setupMap();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
