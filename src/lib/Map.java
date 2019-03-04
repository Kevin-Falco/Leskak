package lib;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map implements  Iterable<Cell> {
    private GridPane gridPane;

    private List<Cell> cells = new ArrayList<>();
    private ParticularIterator<BlockingCell> blockingCellIterator = new ParticularIterator<>(BlockingCell.class, this.cells);
    private ParticularIterator<TransitionCell> transitionCellIterator = new ParticularIterator<>(TransitionCell.class, this.cells);

    public Map() {
        this.gridPane = new GridPane();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public ParticularIterator<BlockingCell> getBlockingCellIterator() {
        return blockingCellIterator;
    }
    public ParticularIterator<TransitionCell> getTransitionCellIterator() {
        return transitionCellIterator;
    }

    public void add(Cell cell){
        this.cells.add(cell);
        this.getGridPane().getChildren().add(cell.getSprite());
    }

    @Override
    public Iterator<Cell> iterator() {
        return this.cells.iterator();
    }
}
