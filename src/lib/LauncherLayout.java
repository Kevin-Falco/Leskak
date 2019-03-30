package lib;

import config.*;
import javafx.animation.PauseTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe représentant le lanceur du jeu "L'aventure de Leskak", permettant l'accès au jeu, aux options et aux crédits.
 * Cette classe est un singleton.
 */
public class LauncherLayout {

    /**
     * VBox contenant les boutons du lanceur du jeu.
     */
    private static VBox vBox;

    /**
     * Vbox contenant les boutons des options, permettant de changer les touches du jeu.
     */
    private static VBox options;

    /**
     * Tâche de service du lanceur.
     */
    private static Service<Void> testService;

    /**
     * Fenêtre de chargement des cartes du jeu.
     */
    private static Stage loadingStage;

    /**
     * Renvoie true si un bouton est changé, false sinon.
     */
    private static boolean isButtonChanging = false;

    /**
     * Largeur de la fenêtre du lanceur.
     */
    private static final Integer WIDTH = 300;

    /**
     * Hauteur de la fenêtre du lanceur.
     */
    private static final Integer HEIGHT = 600;

    /**
     * Scène contenant le lanceur du jeu.
     */
    private static final Scene SCENE = new Scene(new Parent(){}, LauncherLayout.WIDTH, LauncherLayout.HEIGHT);

    /**
     * Instance de LauncherLayout, permet à la classe d'être un singleton.
     */
    private static LauncherLayout INSTANCE = new LauncherLayout();

    /**
     * Constructeur privé du LauncherLayout initialisant sa mise en forme, ses contraintes...
     */
    private LauncherLayout() {
        LauncherLayout.vBox = new VBox();
        LauncherLayout.vBox.setAlignment(Pos.CENTER);
        LauncherLayout.vBox.setSpacing(10);
        LauncherLayout.vBox.setBackground(new Background(new BackgroundImage(new Image(Sprite.BACKGROUND_LAUNCHER.getSpritePath()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        LauncherLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER && LauncherLayout.getSCENE().getFocusOwner() instanceof Button){
                ((Button) LauncherLayout.getSCENE().getFocusOwner()).fire();
            }
            if(event.getCode() == KeyCode.ESCAPE && !isButtonChanging){
                LauncherLayout.SCENE.setRoot(LauncherLayout.vBox);
            }
        });
        LauncherLayout.setupLauncher();
    }

    /**
     * Met en place l'ensemble des éléments présents sur le lanceur du jeu
     */
    public static void setupLauncher(){
        Button game = new Button( GameLayout.getINSTANCE().HasGameBegun() ? "Reprendre la partie" : "Jouer");

        setupLoadingLayout();

        game.setOnAction(event -> {
            Button button = (Button) event.getSource();
            if(button.getText().equals("Reprendre la partie")){
                MainLayout.getSTAGE().show();
            }
            else{
                loadingStage.show();
                MainLayout.getSCENE().setRoot(MainLayout.getINSTANCE().getGridPane());
                if(testService.stateProperty().equals(Worker.State.SUCCEEDED)){
                    System.out.println("OK");
                    testService.restart();
                }
                else{
                    testService.restart() ;
                }

            }
        });
        Button options = new Button("Options");
        options.setOnAction(event -> LauncherLayout.SCENE.setRoot(LauncherLayout.getOptions()));
        Button credits = new Button("Crédits");
        credits.setOnAction(event -> LauncherLayout.SCENE.setRoot(LauncherLayout.getCredits()));
        Button quit = new Button("Quitter");
        quit.setOnAction(event -> {
            Stage s = (Stage) LauncherLayout.SCENE.getWindow();
            s.close();
        });
        LauncherLayout.vBox.getChildren().removeAll(LauncherLayout.vBox.getChildren());
        LauncherLayout.vBox.getChildren().addAll(game, options, credits, quit);
    }

    /**
     * Met en place la fenêtre de chargement du jeu permettant de générer l'intégralité des cartes du jeu.
     */
    private static void setupLoadingLayout(){
        loadingStage = new Stage();
        loadingStage.getIcons().add(new Image(Sprite.PLAYER_DOWN_STOP.getSpritePath()));
        LauncherLayout.testService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return MapConfig.getTask();
            }
        };

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(testService.progressProperty());

        Text text = new Text();
        Text percent = new Text();
        text.textProperty().bind(testService.messageProperty());
        percent.textProperty().bind(StringProperty.stringExpression(IntegerProperty.integerExpression(testService.progressProperty().multiply(100))).concat("%"));

        BorderPane pane = new BorderPane();
        VBox pane2 = new VBox();
        pane2.setSpacing(5);
        pane2.setAlignment(Pos.CENTER);
        pane.setBackground(new Background( new BackgroundImage(new Image(Sprite.LOAD.getSpritePath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        pane2.setBackground(new Background(new BackgroundFill(Color.color(0.6,0.6,0.6, 0.7), new CornerRadii(30), new Insets(-10, 350, -10, 350))));
        loadingStage.setScene(new Scene(pane, 900, 600));
        pane2.getChildren().addAll(progressBar, percent, text );

        BorderPane.setAlignment(text, Pos.BOTTOM_CENTER);
        pane.setBottom(pane2);

        testService.setOnFailed(event -> testService.getException().printStackTrace());
        testService.setOnSucceeded(event -> {
            CinematicConfig.setupGame();
            MainLayout.getSTAGE().setTitle("La légende de Leskak");
            MainLayout.getSTAGE().setOnCloseRequest(event1 -> LauncherLayout.setupLauncher());
            loadingStage.close();
            MainLayout.getSTAGE().show();
        });
    }

    /**
     * Met en place la VBox contenant l'intégralite des crédits
     * @return VBox
     */
    private static VBox getCredits(){
        VBox vBox = new VBox();
        Text text = new Text("L'equipe projet est composée de :\n\n Kévin Falco\nAntoine Hourdeau\n Benjamin Guerin\nHugo Desuert\n\n De plus un grand merci à Yaël Hoaurau pour l'aide apportée lors du développement du jeu et à Ophélie Trawka pour la création des sprites.");
        text.setWrappingWidth(300);
        vBox.getChildren().add(text);
        vBox.getChildren().add(LauncherLayout.getReturnButon());
        vBox.getChildren().forEach((child) -> child.setTranslateY(100));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        vBox.setBackground(new Background(new BackgroundImage(new Image(Sprite.BACKGROUND_CREDITS.getSpritePath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        return vBox;
    }

    /**
     * Met en place la VBox contenant l'intégralité des options (changements de touches) du jeu.
     * @return VBox
     */
    private static VBox getOptions(){
        PauseTransition pt = new PauseTransition();
        PauseTransition pt1 = new PauseTransition();
        pt.setDuration(Duration.millis(1));
        pt1.setDuration(Duration.millis(100));
        LauncherLayout.options = new VBox();
        Text text = new Text("Chagement de touches");
        LauncherLayout.options.getChildren().add(text);
        for(int i = 0; i < Key.values().length; ++i){
            HBox hBox = new HBox();
            Key currentKey = Key.values()[i] ;
            Text name = new Text(currentKey.name() + " : " + currentKey.getKeyCode().getName());
            Button button = new Button("Changer" );
            button.setScaleY(0.8);
            button.setScaleX(0.8);
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
                   text.setText("Changement de touches");
                   isButtonChanging = false;
                   LauncherLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, this);
               }
           };
            button.setOnAction(event -> {
                pt.play();
                pt.setOnFinished(event1 -> {
                    text.setText("Appuyez sur une touche pour : " + currentKey.name());
                    isButtonChanging = true;
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
        LauncherLayout.options.setSpacing(1);
        LauncherLayout.options.getChildren().forEach((child) -> child.setTranslateY(-100));
        LauncherLayout.options.setBackground(new Background(new BackgroundImage(new Image(Sprite.BACKGROUND_OPTIONS.getSpritePath()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        return LauncherLayout.options;
    }

    /**
     * Permet de mettre à jour les nouvelles touches (options) du jeu.
     */
    private static void refreshOptions(){
        for(int i = 0; i < Key.values().length; ++i){
            HBox hBox = (HBox) LauncherLayout.options.getChildren().get(i + 1);
            Key currentKey = Key.values()[i] ;
            Text name = (Text) hBox.getChildren().get(0);
            name.setText(currentKey.name() + " : " + currentKey.getKeyCode().getName());
        }
    }

    /**
     * Met en place le bouton de retour
     * @return Button
     */
    private static Button getReturnButon(){
        Button button = new Button("Retour");
        button.setOnAction(event -> LauncherLayout.SCENE.setRoot(LauncherLayout.vBox));
        button.setAlignment(Pos.BOTTOM_CENTER);
        return button;
    }

    /**
     * Getter de la VBox contenant tous les boutons du lanceur du jeu.
     * @return VBox
     */
    public VBox getvBox() {
        return LauncherLayout.vBox;
    }

    /**
     * Getter de la scène du lanceur du jeu.
     * @return Scene
     */
    public static Scene getSCENE() {
        return LauncherLayout.SCENE;
    }

    /**
     * Getter de l'instance du lanceur du jeu.
     * @return LauncherLayout
     */
    public static LauncherLayout getINSTANCE() {
        return LauncherLayout.INSTANCE;
    }
}
