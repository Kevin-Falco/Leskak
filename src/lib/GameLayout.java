package lib;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameLayout {

    private int nbColumns;
    private int nbRows;
    private GridPane gridPane;
    private Pane pane;
    private boolean gameHasBegun = false;


    private static final GameLayout INSTANCE = new GameLayout();

    private GameLayout(){
        this.nbColumns = 32;
        this.nbRows = 12;
        this.gridPane = new GridPane();
        this.pane = new Pane();
        GridPane.setColumnSpan(this.pane, 2);
        GridPane.setConstraints(this.pane, 0, 0);
        this.pane.getChildren().add(this.gridPane);
        this.pane.setFocusTraversable(true);
        this.pane.requestFocus();
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

    public boolean HasGameBegun() {
        return gameHasBegun;
    }

    public void setGameHasBegun(boolean gameHasBegun) {
        this.gameHasBegun = gameHasBegun;
    }

    public Pane getPane() {
        return pane;
    }

    public void setNbColumns(int nbColumns) {
        this.nbColumns = nbColumns;
    }

    public void setNbRows(int nbRows) {
        this.nbRows = nbRows;
    }

    public void setGridPane(GridPane gridPane) {
        this.pane.getChildren().remove(this.gridPane);
        this.gridPane = gridPane;
        this.pane.getChildren().add(this.gridPane);
    }

    public void setPane(Pane pane) {
        this.pane = pane;
        GridPane.setColumnSpan(this.pane, 2);
        GridPane.setConstraints(this.pane, 0, 0);
    }
}
