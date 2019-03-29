package lib;

import config.Interaction;
import config.Sprite;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Classe représentant l'ensemble de la fenêtre de jeu (avec la zone de jeu, la boîte de dialogue et l'inventaire).
 * Cette classe est un singleton.
 */
public class MainLayout {

    /**
     * Grille contenant les trois éléments nécessaires au jeu.
     */
    private GridPane gridPane;

    /**
     * Largeur de la fenêtre de jeu.
     */
    private static final Integer WIDTH = 1600;

    /**
     * Hauteur de la fenêtre de jeu.
     */
    private static final Integer HEIGHT = 900;

    /**
     * Scène contenant les trois éléments nécessaires au jeu.
     */
    private static final Scene SCENE= new Scene(new Parent(){}, MainLayout.WIDTH, MainLayout.HEIGHT);

    /**
     * Stage du MainLayout.
     */
    private static final Stage STAGE = new Stage();

    /**
     * Instance de MainLayout, permet à la classe d'être un singleton.
     */
    private static final MainLayout INSTANCE = new MainLayout();

    /**
     * Constructeur du MainLayout mettant en place le Stage, la Scène et la grille.
     */
    private MainLayout() {
        MainLayout.STAGE.setScene(MainLayout.SCENE);
        MainLayout.getSCENE().getStylesheets().add("config/style.css");
        MainLayout.STAGE.getIcons().add(new Image(Sprite.PLAYER_DOWN_STOP.getSpritePath()));
        this.gridPane = mainLayout();
    }

    /**
     * Renvoie la grille du main.
     * @return GridPane
     */
    public GridPane getGridPane() {
        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.RETURN_GAME.getEventHandler());
        return this.gridPane;
    }

    /**
     * Renvoie l'instance du MainLayout pour permettre que cette classe soit un singleton.
     * @return MainLayout
     */
    public static MainLayout getINSTANCE() {
        return MainLayout.INSTANCE;
    }

    /**
     * Met en place de la grille du Main.
     * @return GridPane
     */
    private static GridPane mainLayout(){
        GridPane gridPane = new GridPane();
        GridPane inventoryLayout = MainLayout.inventoryLayout();
        GridPane dialogLayout = MainLayout.dialogLayout();

        gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()*2/3));
        gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()/3));
        gridPane.getRowConstraints().add(new RowConstraints(  (float)MainLayout.getHEIGHT()*2/3));
        gridPane.getRowConstraints().add(new RowConstraints(  (float)MainLayout.getHEIGHT()/3));
        GridPane.setConstraints(inventoryLayout, 1, 1);
        GridPane.setConstraints(dialogLayout, 0, 1);
        gridPane.getChildren().addAll(GameLayout.getINSTANCE().getPane(), inventoryLayout, dialogLayout);

        return gridPane;
    }

    /**
     * Renvoie la disposition (grille) de l'inventaire.
     * @return GridPane
     */
    private static GridPane inventoryLayout(){
        return Inventory.getINSTANCE().getGridPane();
    }

    /**
     * Renvoie la disposition (grille) de la boîte de dialogue.
     * @return GridPane
     */
    private static GridPane dialogLayout(){
        return DialogLayout.getINSTANCE().getGridPane();
    }

    /**
     * Renvoie la largeur de la fenêtre du Main.
     * @return int
     */
    public static Integer getWIDTH() {
        return MainLayout.WIDTH;
    }

    /**
     * Renvoie la hauteur de la fenêtre du Main.
     * @return int
     */
    public static Integer getHEIGHT() {
        return MainLayout.HEIGHT;
    }

    /**
     * Renvoie la scène du Main.
     * @return Scene
     */
    public static Scene getSCENE() {
        return MainLayout.SCENE;
    }

    /**
     * Renvoie le Stage du Main.
     * @return Stage
     */
    public static Stage getSTAGE() {
        return STAGE;
    }
}
