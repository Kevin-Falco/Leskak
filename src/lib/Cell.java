package lib;

import config.Sprite;
import config.SpriteSet;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class Cell {
    private ImageView sprite;
    private ImageView sprite2;
    private SpriteSet playerSpriteSet;
    private Pair<Integer, Integer> position;
    private boolean inFogOfWar = false;

    public Cell(Sprite sprite, Pair<Integer, Integer> position) {
        this.sprite = new ImageView( sprite.getSpritePath());
        this.sprite.setPreserveRatio(true);
        this.sprite.setFitWidth(50);//(37);
        this.sprite2 = new ImageView(Sprite.FOG.getSpritePath());
        this.sprite2.setPreserveRatio(true);
        this.sprite2.setFitWidth(50);//(37);
        this.position = position;
        this.playerSpriteSet = setupSpriteSet(sprite);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public ImageView getSprite2() {
        return sprite2;
    }

    public SpriteSet getPlayerSprite() {
        return playerSpriteSet;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
        this.sprite.setPreserveRatio(true);
        this.sprite.setFitWidth(50);
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public boolean isInFogOfWar() {
        return inFogOfWar;
    }

    public void setInFogOfWar(boolean inFogOfWar) {
        this.inFogOfWar = inFogOfWar;
    }

    private SpriteSet setupSpriteSet(Sprite sprite){
        switch(sprite){
            case UP_P1:
            case DOWN_P1:
            case RIGHT_P1:
            case LEFT_P1:
            case GRASS:
                return SpriteSet.SET_P1;
            case UP_BRIDGE:
            case DOWN_BRIDGE:
            case RIGHT_BRIDGE:
            case LEFT_BRIDGE:
            case BRIDGE:
                return SpriteSet.SET_BRIDGE;
            default:
                return SpriteSet.SET_P1;
        }
    }
}
