package config;

import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;
import lib.*;

import java.util.ArrayList;

public class MapConfig {
    private static final MapConfig INSTANCE = new MapConfig();

    private ArrayList<Map> maps;

    private MapConfig() {
        this.maps = new ArrayList<>();
        for(int nbMap = 0; nbMap < 4; ++nbMap){
            Map newMap = new Map();
            for(int i = 0; i < GameLayout.getINSTANCE().getNbColumns(); ++i){
                newMap.getGridPane().getColumnConstraints()
                        .add(new ColumnConstraints((float)GameLayout.getWIDTH()/GameLayout.getINSTANCE().getNbColumns()));
            }
            for(int i = 0 ; i < GameLayout.getINSTANCE().getNbRows(); ++i){
                newMap.getGridPane().getRowConstraints()
                        .add(new RowConstraints(  (float)GameLayout.getHEIGHT()*2/3/GameLayout.getINSTANCE().getNbRows()));
            }
            this.maps.add(newMap);
            setupMap(nbMap);
        }
        this.configMap(0);
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public static MapConfig getINSTANCE() {
        return INSTANCE;
    }

    private BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position){
        BlockingCell pnj = new BlockingCell(new ImageView( sprite.getSpritePath()), position);
        GridPane.setConstraints(pnj.getSprite(), position.getKey(),position.getValue());
        return pnj;
    }

    private BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction interaction){
        BlockingCell pnj = new BlockingCell(new ImageView( sprite.getSpritePath()), position, interaction);
        GridPane.setConstraints(pnj.getSprite(), position.getKey(),position.getValue());
        return pnj;
    }

    private TransitionCell addTransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction){
        TransitionCell pnj = new TransitionCell(new ImageView( sprite.getSpritePath()), position, direction);
        GridPane.setConstraints(pnj.getSprite(), position.getKey(), position.getValue());
        return pnj;
    }

    public void configMap(int nbMap){
        if(nbMap == 0){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
        if(nbMap == 1){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
        if(nbMap == 2){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
        if(nbMap == 3){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
    }

    public void configMap(int nbMap,Pair<Integer, Integer> position, Sprite sprite){
        Movement.setMap(this.maps.get(nbMap));
        Player player = Player.getINSTANCE();
        player.setPosition(position);
        player.setSprite(sprite);

        ImageView imageView = new ImageView(player.getSprite().getSpritePath());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(37);//(50);
        this.maps.get(nbMap).getGridPane().setGridLinesVisible(true);

        GridPane.setConstraints(imageView, position.getKey(),position.getValue());

        this.maps.get(nbMap).getGridPane().getChildren().add(imageView);

        GameLayout.getINSTANCE().setGridPane(this.maps.get(nbMap).getGridPane());
    }

    public void setupMap(int nbMap){
        if(nbMap == 0){
            Map m = this.maps.get(nbMap);
            m.add(this.addBlockingCell(Sprite.));
        }
        else if(nbMap == 1){
            this.maps.get(nbMap).add(this.addBlockingCell(Sprite.PNJ3, new Pair<>(29, 6), Interaction.PNJ2));
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    this.maps.get(nbMap).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>(31 - i, i)));
                else
                    this.maps.get(nbMap).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>( 31 - i, i)));
            }
            this.maps.get(nbMap).add(this.addTransitionCell(Sprite.LEFT_ARROW, new Pair<>(0, 2), Direction.LEFT));
        }
        else if(nbMap == 2){
            this.maps.get(nbMap).add(this.addBlockingCell(Sprite.PNJ4, new Pair<>(30, 3), Interaction.PNJ3));
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    this.maps.get(nbMap).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>(i+5, 0)));
                else
                    this.maps.get(nbMap).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>( i+5, 1)));
            }
            this.maps.get(nbMap).add(this.addTransitionCell(Sprite.DOWN_ARROW, new Pair<>(4, 11), Direction.DOWN));
        }
        else if(nbMap == 3){
            this.maps.get(nbMap).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>(16, 0), Interaction.MASTER));
        }
    }
}
