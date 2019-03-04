package lib;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameLayout {

    private int nbColumns;
    private int nbRows;
    private GridPane gridPane;
    private Pane pane;

    private static final Integer WIDTH= 1600;
    private static final Integer HEIGHT = 900;
    private static final Scene SCENE= new Scene(new Parent(){}, GameLayout.WIDTH, GameLayout.HEIGHT);
    private static final GameLayout INSTANCE = new GameLayout();

    private GameLayout(){
        this.nbColumns = 32;
        this.nbRows = 12;
        this.gridPane = new GridPane();
        this.pane = new Pane();
        GridPane.setColumnSpan(this.pane, 2);
        GridPane.setConstraints(this.pane, 0, 0);
        this.pane.getChildren().add(this.gridPane);
    }

    public static GameLayout getINSTANCE() {
        return GameLayout.INSTANCE;
    }

    public int getNbColumns() {
        return this.nbColumns;
    }

    public int getNbRows() {
        return this.nbRows;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Pane getPane() {
        return pane;
    }

    public static Integer getWIDTH() {
        return GameLayout.WIDTH;
    }

    public static Integer getHEIGHT() {
        return GameLayout.HEIGHT;
    }

    public static Scene getSCENE() {
        return GameLayout.SCENE;
    }

    public void setNbColumns(int nbColumns) {
        this.nbColumns = nbColumns;
    }

    public void setNbRows(int nbRows) {
        this.nbRows = nbRows;
    }

    public void setGridPane(GridPane gridPane) {
        this.pane.getChildren().remove(this.gridPane);
        if(this.gridPane.getChildren().size() > 0)
            this.gridPane.getChildren().remove(1, this.gridPane.getChildren().size());
        System.out.println( this.gridPane.getChildren().size());
        this.gridPane = gridPane;
        System.out.println( this.gridPane.getChildren().size());
        this.pane.getChildren().add(this.gridPane);
    }

    public void setPane(Pane pane) {
        this.pane = pane;
        GridPane.setColumnSpan(this.pane, 2);
        GridPane.setConstraints(this.pane, 0, 0);
    }
}
