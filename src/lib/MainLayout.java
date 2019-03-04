package lib;


import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class MainLayout {
    private GridPane gridPane;

    private static final MainLayout INSTANCE = new MainLayout();

    private MainLayout() {
        this.gridPane = mainLayout();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public static MainLayout getINSTANCE() {
        return INSTANCE;
    }


    private static GridPane mainLayout(){
        GridPane gridPane = new GridPane();
        //GameLayout.getINSTANCE().setGridPane(MapConfig.MAP2.getMap().getGridPane());
        GridPane inventoryLayout = MainLayout.inventoryLayout();
        GridPane dialogLayout = MainLayout.dialogLayout();

        gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)GameLayout.getWIDTH()*2/3));
        gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)GameLayout.getWIDTH()/3));
        gridPane.getRowConstraints().add(new RowConstraints(  (float)GameLayout.getHEIGHT()*2/3));
        gridPane.getRowConstraints().add(new RowConstraints(  (float)GameLayout.getHEIGHT()/3));
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
}
