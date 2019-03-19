package lib;

import config.Key;
import config.MapConfig;
import config.Sprite;
import javafx.animation.PauseTransition;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LauncherLayout {
    private static VBox vBox;
    private static VBox options;
    private static Service<Void> testService;

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
        LauncherLayout.setupLauncher();
    }

    public static void setupLauncher(){
        Button game = new Button( GameLayout.getINSTANCE().HasGameBegun() ? "Reprendre la partie" : "Jouer");
        Stage stage = new Stage();
        LauncherLayout.testService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return MapConfig.getTask();
            }
        };
        ProgressIndicator progressIndicator = new ProgressIndicator();
        ProgressIndicator progressIndicator2 = new ProgressIndicator();
        progressIndicator.progressProperty().bind(testService.progressProperty());

        ProgressBar progressBar = new ProgressBar();
        ProgressBar progressBar2 = new ProgressBar();
        progressBar2.progressProperty().unbind();
        progressBar.progressProperty().bind(testService.progressProperty());
        Text text = new Text();
        text.textProperty().bind(testService.messageProperty());

        Pane pane = new BorderPane();
        Pane pane2 = new HBox();
        ((HBox) pane2).setSpacing(20);
        ((HBox) pane2).setAlignment(Pos.CENTER);
        pane.setBackground(new Background( new BackgroundImage(new Image(Sprite.LOAD.getSpritePath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        pane2.setBackground(new Background(new BackgroundFill(Color.color(0.6,0.6,0.6, 0.7), new CornerRadii(30), new Insets(-10, 200, -10, 200))));
        stage.setScene(new Scene(pane, 900, 600));
        pane2.getChildren().addAll(progressBar, progressBar2, progressIndicator, progressIndicator2, text );

        BorderPane.setAlignment(text, Pos.BOTTOM_CENTER);
        //BorderPane.setAlignment(pane2, Pos.TOP_LEFT);
        //pane.getChildren().add(pane2);
        ((BorderPane) pane).setBottom(pane2);
        //((BorderPane) pane).setCenter(progressIndicator);
        //((BorderPane) pane).setBottom(text);


        testService.setOnFailed(event -> testService.getException().printStackTrace());
        testService.setOnSucceeded(event -> {
            stage.close();
            MainLayout.getSTAGE().show();
        });

        game.setOnAction((EventHandler) (event) -> {
            Button button = (Button) event.getSource();
            if(button.getText() == "Reprendre la partie"){
                MainLayout.getSTAGE().show();
            }
            else{
                stage.show();
                MainLayout.getSCENE().setRoot(MainLayout.getINSTANCE().getGridPane());
                testService.start();
            }
        });
        Button options = new Button("Options");
        options.setOnAction((EventHandler) (event) -> LauncherLayout.SCENE.setRoot(LauncherLayout.getOptions()));
        Button credits = new Button("Crédits");
        credits.setOnAction((EventHandler) (event) -> LauncherLayout.SCENE.setRoot(LauncherLayout.getCredits()));
        Button quit = new Button("Quitter");
        quit.setOnAction((EventHandler) (event) -> {
            Stage s = (Stage) LauncherLayout.SCENE.getWindow();
            s.close();
        });
        LauncherLayout.vBox.getChildren().removeAll(LauncherLayout.vBox.getChildren());
        LauncherLayout.vBox.getChildren().addAll(game, options, credits, quit);
    }



    private static VBox getCredits(){
        VBox vBox = new VBox();
        Text text = new Text("Ici c'est les crédits Lol");
        vBox.getChildren().add(text);
        vBox.getChildren().add(LauncherLayout.getReturnButon());
        vBox.getChildren().forEach((child) -> child.setTranslateY(100));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        vBox.setBackground(new Background(new BackgroundImage(new Image("sprite/background_credits.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        return vBox;
    }

    private static VBox getOptions(){
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(1));
        LauncherLayout.options = new VBox();
        Text text = new Text("Ici c'est les options Lol");
        LauncherLayout.options.getChildren().add(text);
        for(int i = 0; i < Key.values().length; ++i){
            HBox hBox = new HBox();
            Key currentKey = Key.values()[i] ;
            Text name = new Text(currentKey.name() + " : " + currentKey.getKeyCode().getName());
            Button button = new Button("Change Key for " + currentKey.name());
           EventHandler<KeyEvent> keyEventEventHandler = new EventHandler<KeyEvent>() {
               @Override
               public void handle(KeyEvent key) {
                   if(Key.isKeyCodeAlreadyUsed(key.getCode())){
                       Key.getKeyofKeyCode(key.getCode()).setKeyCode(KeyCode.UNDEFINED);
                       refreshOptions();
                   }
                   currentKey.setKeyCode(key.getCode());
                   name.setText(currentKey.name() + " : " + currentKey.getKeyCode().getName());
                   hBox.getChildren().get(1).requestFocus();
                   text.setText("Ici c'est les options Lol");
                   LauncherLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, this);
               }
           };
            button.setOnAction((EventHandler) (event) -> {
                pt.play();
                pt.setOnFinished(event1 -> {
                    text.setText("Appuyez sur une touche pour : " + currentKey.name());
                    LauncherLayout.getSCENE().getRoot().requestFocus();
                    LauncherLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, keyEventEventHandler);
                });
            });
            hBox.getChildren().addAll(name, button);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);

            LauncherLayout.options.getChildren().add(hBox);
        }
        LauncherLayout.options.getChildren().add(LauncherLayout.getReturnButon());
        LauncherLayout.options.setAlignment(Pos.CENTER);
        LauncherLayout.options.setSpacing(10);
        LauncherLayout.options.getChildren().forEach((child) -> child.setTranslateY(-100));
        LauncherLayout.options.setBackground(new Background(new BackgroundImage(new Image("sprite/background_options.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        return LauncherLayout.options;
    }

    private static void refreshOptions(){
        for(int i = 0; i < Key.values().length; ++i){
            HBox hBox = (HBox) LauncherLayout.options.getChildren().get(i + 1);
            Key currentKey = Key.values()[i] ;
            Text name = (Text) hBox.getChildren().get(0);
            name.setText(currentKey.name() + " : " + currentKey.getKeyCode().getName());
        }
    }

    private static Button getReturnButon(){
        Button button = new Button("Retour");
        button.setOnAction((EventHandler) (event) -> LauncherLayout.SCENE.setRoot(LauncherLayout.vBox));
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.setCancelButton(true);
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

    public static Service<Void> getTestService() {
        return testService;
    }
}
