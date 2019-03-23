package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SpriteSet {
    TREE_SET(new ArrayList<>(Arrays.asList(Sprite.TREE, Sprite.TREE_UP, Sprite.TREE_DOWN, Sprite.TREE_RIGHT, Sprite.TREE_LEFT,
            Sprite.TREE_UP_RIGHT, Sprite.TREE_UP_LEFT, Sprite.TREE_UP_LEFT_RIGHT, Sprite.TREE_DOWN_RIGHT, Sprite.TREE_DOWN_LEFT,
            Sprite.TREE_DOWN_LEFT_RIGHT, Sprite.TREE_RIGHT_UP_DOWN, Sprite.TREE_LEFT_UP_DOWN, Sprite.TREE_UP_DOWN, Sprite.TREE_LEFT_RIGHT ))),
    WATER_SET(new ArrayList<>(Arrays.asList(Sprite.WATER, Sprite.WATER_UP, Sprite.WATER_DOWN, Sprite.WATER_RIGHT, Sprite.WATER_LEFT,
             Sprite.WATER_UP_RIGHT, Sprite.WATER_UP_LEFT, Sprite.WATER_UP_LEFT_RIGHT, Sprite.WATER_DOWN_RIGHT, Sprite.WATER_DOWN_LEFT,
             Sprite.WATER_DOWN_LEFT_RIGHT, Sprite.WATER_RIGHT_UP_DOWN, Sprite.WATER_LEFT_UP_DOWN, Sprite.WATER_UP_DOWN, Sprite.WATER_LEFT_RIGHT )));

    private List<Sprite> sprites;

    SpriteSet(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    public boolean contains(Sprite sprite){
        if(sprite == null) return false;
        return sprites.contains(sprite);
    }

    public Sprite getCenter(){ return sprites.get(0); }
    public Sprite getUp(){ return sprites.get(1); }
    public Sprite getDown(){ return sprites.get(2); }
    public Sprite getRight(){ return sprites.get(3); }
    public Sprite getLeft(){ return sprites.get(4); }
    public Sprite getUpRight(){ return sprites.get(5); }
    public Sprite getUpLeft(){ return sprites.get(6); }
    public Sprite getUpLeftRight(){ return sprites.get(7); }
    public Sprite getDownRight(){ return sprites.get(8); }
    public Sprite getDownLeft(){ return sprites.get(9); }
    public Sprite getDownLeftRight(){ return sprites.get(10); }
    public Sprite getRightUpDown(){ return sprites.get(11); }
    public Sprite getLeftUpDown(){ return sprites.get(12); }
    public Sprite getUpDown(){ return sprites.get(13); }
    public Sprite getLeftRight(){ return sprites.get(14); }

}
