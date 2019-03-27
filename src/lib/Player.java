package lib;

import config.Direction;
import config.MapConfig;
import config.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Player {

    private Pair<Integer, Integer> position;
    private Direction direction = Direction.DOWN;
    private Sprite sprite;
    private ArrayList<Integer> skinAvailables = new ArrayList<>(Arrays.asList(0));
    private Integer currentSkin = 0;
    private ImageView image = new ImageView();

    private static final Player INSTANCE = new Player();
    private static final String NAME = "Leskak";

    private Player(){}

    public Pair<Integer, Integer> getPosition(){
        return this.position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public static Player getINSTANCE() {
        return Player.INSTANCE;
    }

    public static String getNAME() {
        return Player.NAME;
    }

    public void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSprite(final Sprite sprite) {
        this.sprite = sprite;
        this.image.setImage(new Image(this.sprite.getSpritePath()));
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(50);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setPlayerOnTop(int nbMap){
        MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().remove(this.image);
        MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().add(this.image);
    }

    public ArrayList<Integer> getSkinAvailables() {
        return skinAvailables;
    }

    public void setSkinAvailables(ArrayList<Integer> skinAvailables) {
        this.skinAvailables = skinAvailables;
    }

    public Integer getNextSkinAvailable() {
        return currentSkin.equals(this.skinAvailables.get(this.skinAvailables.size() -1))
                ? this.skinAvailables.get(0) : this.skinAvailables.get(this.skinAvailables.indexOf(this.currentSkin)+1);
    }

    public Integer getCurrentSkin() {
        return currentSkin;
    }

    public void setCurrentSkin(Integer currentSkin) {
        this.currentSkin = currentSkin;
    }
}
