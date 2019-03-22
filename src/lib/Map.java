package lib;

import config.Sprite;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

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
        GridPane.setConstraints(cell.getImage(), cell.getPosition().getKey(), cell.getPosition().getValue());
        this.getGridPane().getChildren().add(cell.getImage());

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
        if(this.fogOfWar){
            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j){
                add(new FogCell(new Pair<>(i, j)));
            }
        }
        else{
            for(Cell cell : this){
                if(cell instanceof FogCell){
                    this.cells.remove(cell);
                }
            }
        }
    }

    public void removeFogCell(int col, int row){
        Cell cell1 = null;
        for (Cell cell : this
                ) {
            if( cell instanceof FogCell && cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                this.getGridPane().getChildren().remove(cell.getImage());
                cell1 = cell;
            }
        }
        if(cell1 != null)
            this.getCells().remove(cell1);
    }

    public void updateFogOfWar(){
        for(Cell cell : this){
            if(cell instanceof FogCell){
                if(cellPositionAt((FogCell)cell, 2)){
                    //cell.getImage().setVisible(false);
                    this.getGridPane().getChildren().remove(cell.getImage());
                }
                else{
                    //cell.getImage().setVisible(true);
                    if(!this.getGridPane().getChildren().contains(cell.getImage()))
                        this.getGridPane().getChildren().add(cell.getImage());
                }
            }
        }
    }

    private boolean cellPositionAt(FogCell cell, int nb){
        Player player = Player.getINSTANCE();
        return Math.abs(cell.getPosition().getKey() - player.getPosition().getKey()) <= nb &&
            Math.abs(cell.getPosition().getValue() - player.getPosition().getValue()) <= nb
                // suite à enlever pour Fog of War carré
                && !(Math.abs(cell.getPosition().getKey() - player.getPosition().getKey()) == nb &&
                        Math.abs(cell.getPosition().getValue() - player.getPosition().getValue()) == nb );
    }
}
