package lib;

import config.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map implements  Iterable<Cell> {
    private GridPane gridPane;
    private boolean fogOfWar;

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
        if(isFogOfWar()){
            GridPane.setConstraints(cell.getSprite2(), cell.getPosition().getKey(), cell.getPosition().getValue());
            this.getGridPane().getChildren().add(cell.getSprite2());
        }

        GridPane.setConstraints(cell.getSprite(), cell.getPosition().getKey(), cell.getPosition().getValue());
        this.getGridPane().getChildren().add(cell.getSprite());

    }

    @Override
    public Iterator<Cell> iterator() {
        return this.cells.iterator();
    }

    public boolean isFogOfWar() {
        return fogOfWar;
    }

    public void setFogOfWar(boolean fogOfWar) {
        this.fogOfWar = fogOfWar;
    }

    public void updateFogOfWar(){
        for(Cell cell : this){
            if(cellPositionAt(cell, 2)){
                cell.getSprite().setVisible(true);
            }
            else{
                cell.getSprite().setVisible(false);
            }
        }
    }

    private boolean cellPositionAt(Cell cell, int nb){
        Player player = Player.getINSTANCE();
        return Math.abs(cell.getPosition().getKey() - player.getPosition().getKey()) <= nb &&
            Math.abs(cell.getPosition().getValue() - player.getPosition().getValue()) <= nb;
    }
}
