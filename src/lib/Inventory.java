package lib;

import config.Object;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe représentant l'inventaire de Leskak avec ses six emplacements. Cette classe est un singleton.
 */
public class Inventory {

    /**
     * Représente la grille de six objets de l'inventaire.
     */
    private GridPane gridPane;

    /**
     * Instance de Inventory, permet à la classe d'être un singleton.
     */
    private static final Inventory INSTANCE = new Inventory();

    /**
     * Constructeur de l'inventaire initialisant sa mise en forme, ses contraintes...
     */
    private Inventory(){
        this.gridPane = new GridPane();
        this.gridPane.setGridLinesVisible(true);
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()/9));
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()/9));
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)MainLayout.getWIDTH()/9));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)MainLayout.getHEIGHT()/6));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)MainLayout.getHEIGHT()/6));
    }

    /**
     * Renvoie l'instance de l'inventaire, permet à la classe d'être un singleton.
     * @return Inventory
     */
    public static Inventory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Renvoie la grille de l'inventaire
     * @return GridPane
     */
    public GridPane getGridPane() {
        return this.gridPane;
    }

    /**
     * Ajoute un objet dans l'inventaire.
     * @param object objet à ajouter dans l'inventaire
     */
    public void add(Object object){
        if(this.contains(object))
            return;
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(100), Insets.EMPTY)));
        Text text = new Text( object.getName());
        text.setStyle("-fx-font: 12 arial;");
        vBox.getChildren().addAll(object.getImageView(), text );
        GridPane.setConstraints(vBox,
                object.getInventoryPosition().getKey(),
                object.getInventoryPosition().getValue());
        this.getGridPane().getChildren().add(vBox);
    }

    /**
     * Renvoie true si l'objet est dans l'inventaire, false sinon.
     * @param object objet à chercher
     * @return boolean
     */
    public boolean contains(Object object){
        for (Node node : this.getGridPane().getChildren()){
            if(! (node instanceof VBox))
                continue;
            VBox vBox = (VBox) node;
            if(vBox.getChildren().contains(object.getImageView())) return true;
        }
        return false;
    }

    /**
     * Enlève un objet de l'inventaire de Leskak.
     * @param object objet à enlever de l'inventaire
     */
    public void remove(Object object){
        if(!this.contains(object))
            return;
        this.getGridPane().getChildren().remove(getVboxThatContains(object));
    }

    /**
     * Renvoie la VBox qui contient l'objet recherché.
     * @param object objet à chercher
     * @return VBox
     *
     * @see lib.Inventory#remove(Object)
     */
    private VBox getVboxThatContains(Object object){
        for (Node node : this.getGridPane().getChildren()){
            if(! (node instanceof VBox))
                continue;
            VBox vBox = (VBox) node;
            if(vBox.getChildren().contains(object.getImageView())) return vBox;
        }
        return null;
    }
}
