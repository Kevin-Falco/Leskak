package sample;

import config.Sprite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lib.LauncherLayout;

/**
 * Classe principale du jeu, permettant le lancement de l'application JavaFX.
 */
public class Main extends Application {

    /**
     * Principale Stage du jeu.
     */
    private static Stage stage;

    /**
     * Configure tout ce qu'il faut pour que le jeu apparaisse à l'écran avec une fenêtre de jeu personnalisée.
     * @param primaryStage Stage principale du jeu
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Leskak");
        Scene scene = LauncherLayout.getSCENE();
        scene.setRoot(LauncherLayout.getINSTANCE().getvBox());
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Sprite.PLAYER_DOWN_STOP.getSpritePath()));
        primaryStage.show();
    }

    /**
     * Getter du Stage principal du jeu.
     * @return Stage
     */
    public static Stage getStage() {
        return Main.stage;
    }

    /**
     * Permet le lancement du jeu, fonction main.
     * @param args arguments du main
     */
    public static void main(String[] args) {
        launch(args);
    }
}
