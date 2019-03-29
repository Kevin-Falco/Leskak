package config;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

/**
 * Enumération des objets disponibles dans l'inventaire, de la position qu'ils auront dans celui-ci et de leurs sprites.
 */
public enum Object {
    OBJ1("Plaque de tôle", new ImageView("sprite/object/sheet_metal_plate.png"), new Pair<>(0,0)),
    OBJ2("Réacteurs endommagés", new ImageView("sprite/object/reactors.png"), new Pair<>(1,0)),
    OBJ2_2("Réacteurs", new ImageView("sprite/object/reactors.png"), new Pair<>(1,0)),
    OBJ3("Panneau de commande", new ImageView("sprite/object/command_panel.png"), new Pair<>(0,1)),
    OBJ4("Dynamite", new ImageView("sprite/object/dynamite.png"), new Pair<>(2,0)),
    OBJ4_2("Bague de Rohkan", new ImageView("sprite/object/ring.png"), new Pair<>(2,0)),
    OBJ5("Rayon laser", new ImageView("sprite/object/laser.png"), new Pair<>(1,1)),
    OBJ6("Colis", new ImageView("sprite/object/package.png"), new Pair<>(2,1)),
    OBJ6_2("Colis dangeureux", new ImageView("sprite/object/package.png"), new Pair<>(2,1)),
    OBJ7_1("1 Plume", new ImageView("sprite/object/feather.png"), new Pair<>(2,1)),
    OBJ7_2("2 Plumes", new ImageView("sprite/object/feather2.png"), new Pair<>(2,1)),
    OBJ7_3("3 Plumes", new ImageView("sprite/object/feather3.png"), new Pair<>(2,1));

    /**
     * Nom de l'objet.
     */
    private String name;

    /**
     * ImageView formée grâce au chemin .png de l'image du sprite de l'objet.
     */
    private ImageView imageView;

    /**
     * Position de l'objet dans l'inventaire.
     */
    private Pair<Integer, Integer> inventoryPosition;

    /**
     * Constructeur de l'objet avec en paramètre son nom, sa position dans l'inventaire et son sprite.
     * @param name nom de l'objet
     * @param imageView ImageView de l'objet (son sprite)
     * @param inventoryPosition position dans l'inventaire
     */
    Object(String name, ImageView imageView, Pair<Integer, Integer> inventoryPosition) {
        this.name = name;
        this.imageView = imageView;
        this.imageView.setPreserveRatio(true);
        this.imageView.setFitWidth(100);
        this.inventoryPosition = inventoryPosition;
    }

    /**
     * Getter du nom de l'objet.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter de l'ImageView de l'objet formée grâce au sprite de celui-ci.
     * @return ImageView
     */
    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * Getter de la position de l'ojbjet dans l'inventaire.
     * @return Pair
     */
    public Pair<Integer, Integer> getInventoryPosition() {
        return inventoryPosition;
    }
}
