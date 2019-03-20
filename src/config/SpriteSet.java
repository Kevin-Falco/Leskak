package config;

import java.util.ArrayList;
import java.util.List;

public enum SpriteSet {
    TREE_SET(new ArrayList<>(13));

    static {
        TREE_SET.sprites.add(Sprite.TREE);
        TREE_SET.sprites.add(Sprite.TREE_UP);
        TREE_SET.sprites.add(Sprite.TREE_DOWN);
        TREE_SET.sprites.add(Sprite.TREE_RIGHT);
        TREE_SET.sprites.add(Sprite.TREE_LEFT);
        TREE_SET.sprites.add(Sprite.TREE_UP_RIGHT);
        TREE_SET.sprites.add(Sprite.TREE_UP_LEFT);
        TREE_SET.sprites.add(Sprite.TREE_UP_LEFT_RIGHT);
        TREE_SET.sprites.add(Sprite.TREE_DOWN_RIGHT);
        TREE_SET.sprites.add(Sprite.TREE_DOWN_LEFT);
        TREE_SET.sprites.add(Sprite.TREE_DOWN_LEFT_RIGHT);
        TREE_SET.sprites.add(Sprite.TREE_RIGHT_UP_DOWN);
        TREE_SET.sprites.add(Sprite.TREE_LEFT_UP_DOWN);
    }

    private List<Sprite> sprites;

    SpriteSet(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public boolean contains(Sprite sprite){
        if(sprite == null) return false;
        return sprites.contains(sprite);
    }

}
