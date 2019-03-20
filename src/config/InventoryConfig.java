package config;

import javafx.scene.image.ImageView;
import javafx.util.Pair;


public enum InventoryConfig {
    OBJ1("Bidule", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(0,0)),
    OBJ1_2("Plaque de tôle", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(0,0)),
    OBJ2("Panneau de commande", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(1,0)),
    OBJ3("Dynamite", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(2,0)),
    OBJ4("Réacteurs", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(0,1)),
    OBJ5("Rayon laser", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(1,1)),
    OBJ6("Bague de Rokanh", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(2,1));

    private String name;
    private ImageView imageView;
    private Pair<Integer, Integer> inventoryPosition;

    InventoryConfig(String name, ImageView imageView, Pair<Integer, Integer> inventoryPosition) {
        this.name = name;
        this.imageView = imageView;
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        this.inventoryPosition = inventoryPosition;
    }

    public String getName() {
        return name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Pair<Integer, Integer> getInventoryPosition() {
        return inventoryPosition;
    }
}
