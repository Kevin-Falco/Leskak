package config;

import javafx.scene.image.ImageView;
import javafx.util.Pair;


public enum Object {
    OBJ1("Plaque de tôle", new ImageView("sprite/object/sheet_metal_plate.png"), new Pair<>(0,0)),
    OBJ2("Réacteurs endommagés", new ImageView("sprite/object/reactors.png"), new Pair<>(1,0)),
    OBJ2_2("Réacteurs", new ImageView("sprite/object/reactors.png"), new Pair<>(1,0)),
    OBJ3("Panneau de commande", new ImageView("sprite/object/command_panel.png"), new Pair<>(2,0)),
    OBJ4("Dynamite", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(0,1)),
    OBJ4_2("Bague de Rohkan", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(0,1)),
    OBJ5("Rayon laser", new ImageView("sprite/player/player_down_stop.png"), new Pair<>(1,1)),
    OBJ6("Colis", new ImageView("sprite/object/package.png"), new Pair<>(2,1)),
    OBJ6_2("Colis dangeureux", new ImageView("sprite/object/package.png"), new Pair<>(2,1)),
    OBJ7_1("Plume", new ImageView("sprite/animal/fox_up.png"), new Pair<>(2,1)),
    OBJ7_2("Plumes", new ImageView("sprite/animal/fox_down.png"), new Pair<>(2,1)),
    OBJ7_3("Plumes", new ImageView("sprite/animal/fox_left.png"), new Pair<>(2,1));

    private String name;
    private ImageView imageView;
    private Pair<Integer, Integer> inventoryPosition;

    Object(String name, ImageView imageView, Pair<Integer, Integer> inventoryPosition) {
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
