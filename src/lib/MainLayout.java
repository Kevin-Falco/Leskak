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


public class MainLayout {
    private GridPane gridPane;


    private static final Integer WIDTH= 1600;//1200;
    private static final Integer HEIGHT = 900;//675;
    private static final Scene SCENE= new Scene(new Parent(){}, MainLayout.WIDTH, MainLayout.HEIGHT);
    private static final Stage STAGE = new Stage();
    private static final MainLayout INSTANCE = new MainLayout();

    private MainLayout() {
        MainLayout.STAGE.setScene(MainLayout.SCENE);
        MainLayout.getSCENE().getStylesheets().add("config/style.css");
        MainLayout.STAGE.getIcons().add(new Image(Sprite.PLAYER_DOWN_STOP.getSpritePath()));
        this.gridPane = mainLayout();
    }

    public GridPane getGridPane() {
        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.RETURN_GAME.getEventHandler());
        return gridPane;
    }

    public static MainLayout getINSTANCE() {
        return INSTANCE;
    }



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

    public static GridPane inventoryLayout(){
        return Inventory.getINSTANCE().getGridPane();
    }

    public static GridPane dialogLayout(){
        return DialogLayout.getINSTANCE().getGridPane();
    }

    public static Integer getWIDTH() {
        return MainLayout.WIDTH;
    }

    public static Integer getHEIGHT() {
        return MainLayout.HEIGHT;
    }

    public static Scene getSCENE() {
        return MainLayout.SCENE;
    }

    public static Stage getSTAGE() {
        return STAGE;
    }
}
