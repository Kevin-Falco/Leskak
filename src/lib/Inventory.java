package lib;

import config.InventoryConfig;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Inventory {
    private GridPane gridPane;


    private static final Inventory INSTANCE = new Inventory();

    private Inventory(){
        this.gridPane = new GridPane();
        this.gridPane.setGridLinesVisible(true);

        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()/9));
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()/9));
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()/9));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)MainLayout.getHEIGHT()/6));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)MainLayout.getHEIGHT()/6));
    }

    public GridPane getGridPane() {

        return this.gridPane;
    }

    public static Inventory getINSTANCE() {
        return INSTANCE;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void add(InventoryConfig inventoryConfig){
        if(this.contains(inventoryConfig))
            return;
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(inventoryConfig.getImageView(), new Text( inventoryConfig.getName()));
        GridPane.setConstraints(vBox,
                inventoryConfig.getInventoryPosition().getKey(),
                inventoryConfig.getInventoryPosition().getValue());
        this.getGridPane().getChildren().add(vBox);
    }

    public boolean contains(InventoryConfig inventoryConfig){
        for (Node node : this.getGridPane().getChildren()){
            if(! (node instanceof VBox))
                continue;
            VBox vBox = (VBox) node;
            if(vBox.getChildren().contains(inventoryConfig.getImageView())) return true;
        }
        return false;
    }

    public void remove(InventoryConfig inventoryConfig){
        if(!this.contains(inventoryConfig))
            return;
        this.getGridPane().getChildren().remove(getVboxThatContains(inventoryConfig));
    }
    private VBox getVboxThatContains(InventoryConfig inventoryConfig){
        for (Node node : this.getGridPane().getChildren()){
            if(! (node instanceof VBox))
                continue;
            VBox vBox = (VBox) node;
            if(vBox.getChildren().contains(inventoryConfig.getImageView())) return vBox;
        }
        return null;
    }

}
