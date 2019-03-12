package sample;

import config.CinematicConfig;
import config.MapConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        CinematicConfig.setupGame();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
