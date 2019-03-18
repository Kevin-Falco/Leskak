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
        //cell.setInFogOfWar(true);
        //this.getGridPane().getChildren().add(this.cells.get(this.cells.size() - 1).getSprite());
        GridPane.setConstraints(this.cells.get(this.cells.size() - 1).getSprite(), cell.getPosition().getKey(), cell.getPosition().getValue());
        cell.setInFogOfWar(false);
        this.getGridPane().getChildren().add(this.cells.get(this.cells.size() - 1).getSprite());
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
        System.out.println("PLAYER : " + Player.getINSTANCE().getPosition());
        for(Cell cell : this){
            if(cellPositionAt(cell, 2)){
                this.getGridPane().getChildren().remove(cell.getSprite());
                cell.setInFogOfWar(false);
                this.getGridPane().getChildren().add(cell.getSprite());
                cell.getSprite().setVisible(true);
                System.out.println("YES : " + cell.getPosition());
            }
            else{
                this.getGridPane().getChildren().remove(cell.getSprite());
                cell.setInFogOfWar(true);
                this.getGridPane().getChildren().add(cell.getSprite());
                cell.getSprite().setVisible(false);
                System.out.println("NO : " + cell.getPosition());
            }
        }
    }

    private boolean cellPositionAt(Cell cell, int nb){
        Player player = Player.getINSTANCE();
        return Math.abs(cell.getPosition().getKey() - player.getPosition().getKey()) <= nb &&
            Math.abs(cell.getPosition().getValue() - player.getPosition().getValue()) <= nb;
    }
}
