package lib;

import config.CinematicConfig;
import config.MapConfig;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LauncherLayout {
    private static VBox vBox;

    private static final Integer WIDTH= 300;//1200;
    private static final Integer HEIGHT = 600;//675;
    private static final Scene SCENE= new Scene(new Parent(){}, LauncherLayout.WIDTH, LauncherLayout.HEIGHT);
    private static LauncherLayout INSTANCE = new LauncherLayout();

    private LauncherLayout() {
        LauncherLayout.vBox = new VBox();
        LauncherLayout.vBox.setAlignment(Pos.CENTER);
        LauncherLayout.vBox.setSpacing(10);
        LauncherLayout.vBox.setBackground(new Background(new BackgroundImage(new Image("sprite/background_launcher.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        Button game = new Button("Jouer");
        game.setOnAction((EventHandler) (event) -> {
            MainLayout.getSCENE().setRoot(MainLayout.getINSTANCE().getGridPane());
            Movement.configPlayerEventHandler(MainLayout.getSCENE());
            MapConfig.getINSTANCE();
            CinematicConfig.setupGame();
            MainLayout.getSTAGE().show();
        });
        Button options = new Button("Options");
        options.setOnAction((EventHandler) (event) -> LauncherLayout.SCENE.setRoot(LauncherLayout.getOptions()));
        Button credits = new Button("Crédits");
        credits.setOnAction((EventHandler) (event) -> LauncherLayout.SCENE.setRoot(LauncherLayout.getCredits()));
        Button quit = new Button("Quitter");
        quit.setOnAction((EventHandler) (event) -> {
            Stage stage = (Stage) LauncherLayout.SCENE.getWindow();
            stage.close();
        });
        LauncherLayout.vBox.getChildren().addAll(game, options, credits, quit);
    }

    private static VBox getCredits(){
        VBox vBox = new VBox();
        vBox.getChildren().add(new Text("Ici c'est les crédits Lol"));
        vBox.getChildren().add(LauncherLayout.getReturnButon());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        return vBox;
    }

    private static VBox getOptions(){
        VBox vBox = new VBox();
        vBox.getChildren().add(new Text("Ici c'est les options Lol"));
        vBox.getChildren().add(LauncherLayout.getReturnButon());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        return vBox;
    }

    private static Button getReturnButon(){
        Button button = new Button("Retour");
        button.setOnAction((EventHandler) (event) -> LauncherLayout.SCENE.setRoot(LauncherLayout.vBox));
        button.setAlignment(Pos.BOTTOM_CENTER);
        return button;
    }

    public VBox getvBox() {
        return vBox;
    }

    public static Scene getSCENE() {
        return SCENE;
    }

    public static LauncherLayout getINSTANCE() {
        return INSTANCE;
    }
}
