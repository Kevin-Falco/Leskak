package config;

/**
 * Enumération contenant l'ensemble des sprites du jeu, ainsi que les images du lanceur du jeu, des cinématiques et du
 * temps de chargement.
 */
public enum Sprite {

    // Liste des sprites (animés) du joueur
    PLAYER_UP_STOP("sprite/player/player_up_stop.png"),
    PLAYER_UP_MOVE_RIGHT("sprite/player/player_up_move_right.png"),
    PLAYER_UP_MOVE_LEFT("sprite/player/player_up_move_left.png"),
    PLAYER_DOWN_STOP("sprite/player/player_down_stop.png"),
    PLAYER_DOWN_MOVE_RIGHT("sprite/player/player_down_move_right.png"),
    PLAYER_DOWN_MOVE_LEFT("sprite/player/player_down_move_left.png"),
    PLAYER_RIGHT_STOP("sprite/player/player_right_stop.png"),
    PLAYER_RIGHT_MOVE_RIGHT("sprite/player/player_right_move_right.png"),
    PLAYER_RIGHT_MOVE_LEFT("sprite/player/player_right_move_left.png"),
    PLAYER_LEFT_STOP("sprite/player/player_left_stop.png"),
    PLAYER_LEFT_MOVE_RIGHT("sprite/player/player_left_move_right.png"),
    PLAYER_LEFT_MOVE_LEFT("sprite/player/player_left_move_left.png"),
    PLAYER2_UP_STOP("sprite/player/player2_up_stop.png"),
    PLAYER2_UP_MOVE_RIGHT("sprite/player/player2_up_move_right.png"),
    PLAYER2_UP_MOVE_LEFT("sprite/player/player2_up_move_left.png"),
    PLAYER2_DOWN_STOP("sprite/player/player2_down_stop.png"),
    PLAYER2_DOWN_MOVE_RIGHT("sprite/player/player2_down_move_right.png"),
    PLAYER2_DOWN_MOVE_LEFT("sprite/player/player2_down_move_left.png"),
    PLAYER2_RIGHT_STOP("sprite/player/player2_right_stop.png"),
    PLAYER2_RIGHT_MOVE_RIGHT("sprite/player/player2_right_move_right.png"),
    PLAYER2_RIGHT_MOVE_LEFT("sprite/player/player2_right_move_left.png"),
    PLAYER2_LEFT_STOP("sprite/player/player2_left_stop.png"),
    PLAYER2_LEFT_MOVE_RIGHT("sprite/player/player2_left_move_right.png"),
    PLAYER2_LEFT_MOVE_LEFT("sprite/player/player2_left_move_left.png"),
    PLAYER3_UP_STOP("sprite/player/player3_up_stop.png"),
    PLAYER3_UP_MOVE_RIGHT("sprite/player/player3_up_move_right.png"),
    PLAYER3_UP_MOVE_LEFT("sprite/player/player3_up_move_left.png"),
    PLAYER3_DOWN_STOP("sprite/player/player3_down_stop.png"),
    PLAYER3_DOWN_MOVE_RIGHT("sprite/player/player3_down_move_right.png"),
    PLAYER3_DOWN_MOVE_LEFT("sprite/player/player3_down_move_left.png"),
    PLAYER3_RIGHT_STOP("sprite/player/player3_right_stop.png"),
    PLAYER3_RIGHT_MOVE_RIGHT("sprite/player/player3_right_move_right.png"),
    PLAYER3_RIGHT_MOVE_LEFT("sprite/player/player3_right_move_left.png"),
    PLAYER3_LEFT_STOP("sprite/player/player3_left_stop.png"),
    PLAYER3_LEFT_MOVE_RIGHT("sprite/player/player3_left_move_right.png"),
    PLAYER3_LEFT_MOVE_LEFT("sprite/player/player3_left_move_left.png"),

    // Liste des sprites dans les quatre directions de tous les personnages non jouables
    PNJ1_UP("sprite/pnj/pnj1_up.png"),
    PNJ1_DOWN("sprite/pnj/pnj1_down.png"),
    PNJ1_RIGHT("sprite/pnj/pnj1_right.png"),
    PNJ1_LEFT("sprite/pnj/pnj1_left.png"),
    PNJ2_UP("sprite/pnj/pnj2_up.png"),
    PNJ2_DOWN("sprite/pnj/pnj2_down.png"),
    PNJ2_RIGHT("sprite/pnj/pnj2_right.png"),
    PNJ2_LEFT("sprite/pnj/pnj2_left.png"),
    PNJ3_UP("sprite/pnj/pnj3_up.png"),
    PNJ3_DOWN("sprite/pnj/pnj3_down.png"),
    PNJ3_RIGHT("sprite/pnj/pnj3_right.png"),
    PNJ3_LEFT("sprite/pnj/pnj3_left.png"),
    PNJ4_UP("sprite/pnj/pnj4_up.png"),
    PNJ4_DOWN("sprite/pnj/pnj4_down.png"),
    PNJ4_RIGHT("sprite/pnj/pnj4_right.png"),
    PNJ4_LEFT("sprite/pnj/pnj4_left.png"),
    PNJ5_UP("sprite/pnj/pnj5_up.png"),
    PNJ5_DOWN("sprite/pnj/pnj5_down.png"),
    PNJ5_RIGHT("sprite/pnj/pnj5_right.png"),
    PNJ5_LEFT("sprite/pnj/pnj5_left.png"),
    PNJ6_UP("sprite/pnj/pnj6_up.png"),
    PNJ6_DOWN("sprite/pnj/pnj6_down.png"),
    PNJ6_RIGHT("sprite/pnj/pnj6_right.png"),
    PNJ6_LEFT("sprite/pnj/pnj6_left.png"),
    PNJ7_UP("sprite/pnj/pnj7_up.png"),
    PNJ7_DOWN("sprite/pnj/pnj7_down.png"),
    PNJ7_RIGHT("sprite/pnj/pnj7_right.png"),
    PNJ7_LEFT("sprite/pnj/pnj7_left.png"),
    PNJ8_UP("sprite/pnj/pnj8_up.png"),
    PNJ8_DOWN("sprite/pnj/pnj8_down.png"),
    PNJ8_RIGHT("sprite/pnj/pnj8_right.png"),
    PNJ8_LEFT("sprite/pnj/pnj8_left.png"),
    PNJ9_UP("sprite/pnj/pnj9_up.png"),
    PNJ9_DOWN("sprite/pnj/pnj9_down.png"),
    PNJ9_RIGHT("sprite/pnj/pnj9_right.png"),
    PNJ9_LEFT("sprite/pnj/pnj9_left.png"),
    PNJ10_UP("sprite/pnj/pnj10_up.png"),
    PNJ10_DOWN("sprite/pnj/pnj10_down.png"),
    PNJ10_RIGHT("sprite/pnj/pnj10_right.png"),
    PNJ10_LEFT("sprite/pnj/pnj10_left.png"),
    PNJ11_UP("sprite/pnj/pnj11_up.png"),
    PNJ11_DOWN("sprite/pnj/pnj11_down.png"),
    PNJ11_RIGHT("sprite/pnj/pnj11_right.png"),
    PNJ11_LEFT("sprite/pnj/pnj11_left.png"),
    PNJ12_UP("sprite/pnj/pnj12_up.png"),
    PNJ12_DOWN("sprite/pnj/pnj12_down.png"),
    PNJ12_RIGHT("sprite/pnj/pnj12_right.png"),
    PNJ12_LEFT("sprite/pnj/pnj12_left.png"),
    PNJ13_UP("sprite/pnj/pnj13_up.png"),
    PNJ13_DOWN("sprite/pnj/pnj13_down.png"),
    PNJ13_RIGHT("sprite/pnj/pnj13_right.png"),
    PNJ13_LEFT("sprite/pnj/pnj13_left.png"),
    PNJ14_UP("sprite/pnj/pnj14_up.png"),
    PNJ14_DOWN("sprite/pnj/pnj14_down.png"),
    PNJ14_RIGHT("sprite/pnj/pnj14_right.png"),
    PNJ14_LEFT("sprite/pnj/pnj14_left.png"),
    PNJ15_UP("sprite/pnj/pnj15_up.png"),
    PNJ15_DOWN("sprite/pnj/pnj15_down.png"),
    PNJ15_RIGHT("sprite/pnj/pnj15_right.png"),
    PNJ15_LEFT("sprite/pnj/pnj15_left.png"),
    PNJ16_UP("sprite/pnj/pnj16_up.png"),
    PNJ16_DOWN("sprite/pnj/pnj16_down.png"),
    PNJ16_RIGHT("sprite/pnj/pnj16_right.png"),
    PNJ16_LEFT("sprite/pnj/pnj16_left.png"),
    PNJ17_UP("sprite/pnj/pnj17_up.png"),
    PNJ17_DOWN("sprite/pnj/pnj17_down.png"),
    PNJ17_RIGHT("sprite/pnj/pnj17_right.png"),
    PNJ17_LEFT("sprite/pnj/pnj17_left.png"),
    PNJ18_UP("sprite/pnj/pnj18_up.png"),
    PNJ18_DOWN("sprite/pnj/pnj18_down.png"),
    PNJ18_RIGHT("sprite/pnj/pnj18_right.png"),
    PNJ18_LEFT("sprite/pnj/pnj18_left.png"),
    PNJ19_UP("sprite/pnj/pnj19_up.png"),
    PNJ19_DOWN("sprite/pnj/pnj19_down.png"),
    PNJ19_RIGHT("sprite/pnj/pnj19_right.png"),
    PNJ19_LEFT("sprite/pnj/pnj19_left.png"),
    PNJ20_UP("sprite/pnj/pnj20_up.png"),
    PNJ20_DOWN("sprite/pnj/pnj20_down.png"),
    PNJ20_RIGHT("sprite/pnj/pnj20_right.png"),
    PNJ20_LEFT("sprite/pnj/pnj20_left.png"),

    // Liste de tous les sprites des animaux dans les quatre directions ainsi que du panda, qui est incarnable par le joueur
    WHITE_CAT_UP("sprite/animal/white_cat_up.png"),
    WHITE_CAT_DOWN("sprite/animal/white_cat_down.png"),
    WHITE_CAT_RIGHT("sprite/animal/white_cat_right.png"),
    WHITE_CAT_LEFT("sprite/animal/white_cat_left.png"),
    GREY_CAT_UP("sprite/animal/grey_cat_up.png"),
    GREY_CAT_DOWN("sprite/animal/grey_cat_down.png"),
    GREY_CAT_RIGHT("sprite/animal/grey_cat_right.png"),
    GREY_CAT_LEFT("sprite/animal/grey_cat_left.png"),
    BLACK_CAT_UP("sprite/animal/black_cat_up.png"),
    BLACK_CAT_DOWN("sprite/animal/black_cat_down.png"),
    BLACK_CAT_RIGHT("sprite/animal/black_cat_right.png"),
    BLACK_CAT_LEFT("sprite/animal/black_cat_left.png"),
    SNAKE_UP("sprite/animal/snake_up.png"),
    SNAKE_DOWN("sprite/animal/snake_down.png"),
    SNAKE_RIGHT("sprite/animal/snake_right.png"),
    SNAKE_LEFT("sprite/animal/snake_left.png"),
    FOX_UP("sprite/animal/fox_up.png"),
    FOX_DOWN("sprite/animal/fox_down.png"),
    FOX_RIGHT("sprite/animal/fox_right.png"),
    FOX_LEFT("sprite/animal/fox_left.png"),
    CHICKEN_UP("sprite/animal/chicken_up.png"),
    CHICKEN_DOWN("sprite/animal/chicken_down.png"),
    CHICKEN_RIGHT("sprite/animal/chicken_right.png"),
    CHICKEN_LEFT("sprite/animal/chicken_left.png"),

    PANDA_UP_STOP("sprite/animal/panda_up_stop.png"),
    PANDA_UP_MOVE_RIGHT("sprite/animal/panda_up_move_right.png"),
    PANDA_UP_MOVE_LEFT("sprite/animal/panda_up_move_left.png"),
    PANDA_DOWN_STOP("sprite/animal/panda_down_stop.png"),
    PANDA_DOWN_MOVE_RIGHT("sprite/animal/panda_down_move_right.png"),
    PANDA_DOWN_MOVE_LEFT("sprite/animal/panda_down_move_left.png"),
    PANDA_RIGHT_STOP("sprite/animal/panda_right_stop.png"),
    PANDA_RIGHT_MOVE_RIGHT("sprite/animal/panda_right_move_right.png"),
    PANDA_RIGHT_MOVE_LEFT("sprite/animal/panda_right_move_left.png"),
    PANDA_LEFT_STOP("sprite/animal/panda_left_stop.png"),
    PANDA_LEFT_MOVE_RIGHT("sprite/animal/panda_left_move_right.png"),
    PANDA_LEFT_MOVE_LEFT("sprite/animal/panda_left_move_left.png"),

    // Liste des sprites des vaisseaux spatiaux du jeu
    ROCKET_UP("sprite/rocket/rocket_up.png"),
    ROCKET_DOWN("sprite/rocket/rocket_down.png"),
    SPACESHIP_0("sprite/rocket/spaceship_0.png"),
    SPACESHIP_1("sprite/rocket/spaceship_1.png"),
    SPACESHIP_2("sprite/rocket/spaceship_2.png"),
    SPACESHIP_3("sprite/rocket/spaceship_3.png"),
    SPACESHIP_4("sprite/rocket/spaceship_4.png"),
    SPACESHIP_5("sprite/rocket/spaceship_5.png"),
    SPACESHIP_6("sprite/rocket/spaceship_6.png"),
    SPACESHIP_7("sprite/rocket/spaceship_7.png"),
    SPACESHIP_8("sprite/rocket/spaceship_8.png"),
    SPACESHIP_9("sprite/rocket/spaceship_9.png"),

    // Liste des sprites de l'unique coffre du jeu
    CHEST_HIDDEN("sprite/chest/chest_hidden.png"),
    CHEST_CLOSED("sprite/chest/chest_closed.png"),
    CHEST_OPENED("sprite/chest/chest_opened.png"),

    // Liste des sprites liés à l'environnement du jeu
    GRASS("sprite/env/grass.png"),
    BUSH("sprite/env/bush.png"),
    BUSH2("sprite/env/bush2.png"),
    BUSH3("sprite/env/bush3.png"),
    HERB("sprite/env/herb.png"),
    ROCK("sprite/env/rock.png"),
    ROCK2("sprite/env/rock2.png"),
    BRIDGE("sprite/env/bridge.png"),
    FOG("sprite/env/fog.png"),
    SAND("sprite/env/sand.png"),
    ROAD("sprite/env/road.png"),
    ROAD_UP_DOWN("sprite/env/road_up_down.png"),
    ROAD_UP_RIGHT("sprite/env/road_up_right.png"),
    ROAD_LEFT_RIGHT("sprite/env/road_left_right.png"),
    FLOOR("sprite/env/floor.png"),
    FLOOR_UP("sprite/env/floor_up.png"),
    FLOOR_UP_LEFT("sprite/env/floor_up_left.png"),
    FLOOR_UP_RIGHT("sprite/env/floor_up_right.png"),
    FLOOR_DOWN("sprite/env/floor_down.png"),
    FLOOR_DOWN_LEFT("sprite/env/floor_down_left.png"),
    FLOOR_DOWN_RIGHT("sprite/env/floor_down_right.png"),
    FLOOR_LEFT("sprite/env/floor_left.png"),
    FLOOR_RIGHT("sprite/env/floor_right.png"),
    SPACE("sprite/env/space.png"),
    STATUE_UP("sprite/env/statue_up.png"),
    STATUE_DOWN("sprite/env/statue_down.png"),
    VORTEX_0("sprite/env/vortex_0.png"),
    VORTEX_1("sprite/env/vortex_1.png"),
    VORTEX_2("sprite/env/vortex_2.png"),
    VORTEX_3("sprite/env/vortex_3.png"),
    SNOW("sprite/env/snow.png"),
    CAVE_ENTRANCE("sprite/env/cave_entrance.png"),
    CAVE_FLOOR("sprite/env/cave_floor.png"),

    // Liste des sprites permettant de générer la grotte de la planète 4, étant adaptables
    CAVE("sprite/cave/cave.png"),
    CAVE_UP("sprite/cave/cave_up.png"),
    CAVE_UP_LEFT("sprite/cave/cave_up_left.png"),
    CAVE_UP_LEFT_RIGHT("sprite/cave/cave_up_left_right.png"),
    CAVE_UP_RIGHT("sprite/cave/cave_up_right.png"),
    CAVE_UP_DOWN("sprite/cave/cave_up_down.png"),
    CAVE_DOWN("sprite/cave/cave_down.png"),
    CAVE_DOWN_LEFT("sprite/cave/cave_down_left.png"),
    CAVE_DOWN_LEFT_RIGHT("sprite/cave/cave_down_left_right.png"),
    CAVE_DOWN_RIGHT("sprite/cave/cave_down_right.png"),
    CAVE_LEFT("sprite/cave/cave_left.png"),
    CAVE_LEFT_RIGHT("sprite/cave/cave_left_right.png"),
    CAVE_LEFT_UP_DOWN("sprite/cave/cave_left_up_down.png"),
    CAVE_RIGHT("sprite/cave/cave_right.png"),
    CAVE_RIGHT_UP_DOWN("sprite/cave/cave_right_up_down.png"),

    // Liste des quatre types d'arbres des quatre planètes (représentant les quatre saisons), étant adaptables
    TREE("sprite/tree/tree.png"),
    TREE_UP("sprite/tree/tree_up.png"),
    TREE_UP_LEFT("sprite/tree/tree_up_left.png"),
    TREE_UP_LEFT_RIGHT("sprite/tree/tree_up_left_right.png"),
    TREE_UP_RIGHT("sprite/tree/tree_up_right.png"),
    TREE_UP_DOWN("sprite/tree/tree_up_down.png"),
    TREE_DOWN("sprite/tree/tree_down.png"),
    TREE_DOWN_LEFT("sprite/tree/tree_down_left.png"),
    TREE_DOWN_LEFT_RIGHT("sprite/tree/tree_down_left_right.png"),
    TREE_DOWN_RIGHT("sprite/tree/tree_down_right.png"),
    TREE_LEFT("sprite/tree/tree_left.png"),
    TREE_LEFT_RIGHT("sprite/tree/tree_left_right.png"),
    TREE_LEFT_UP_DOWN("sprite/tree/tree_left_up_down.png"),
    TREE_RIGHT("sprite/tree/tree_right.png"),
    TREE_RIGHT_UP_DOWN("sprite/tree/tree_right_up_down.png"),
    TREE2("sprite/tree/tree2.png"),
    TREE2_UP("sprite/tree/tree2_up.png"),
    TREE2_UP_LEFT("sprite/tree/tree2_up_left.png"),
    TREE2_UP_LEFT_RIGHT("sprite/tree/tree2_up_left_right.png"),
    TREE2_UP_RIGHT("sprite/tree/tree2_up_right.png"),
    TREE2_UP_DOWN("sprite/tree/tree2_up_down.png"),
    TREE2_DOWN("sprite/tree/tree2_down.png"),
    TREE2_DOWN_LEFT("sprite/tree/tree2_down_left.png"),
    TREE2_DOWN_LEFT_RIGHT("sprite/tree/tree2_down_left_right.png"),
    TREE2_DOWN_RIGHT("sprite/tree/tree2_down_right.png"),
    TREE2_LEFT("sprite/tree/tree2_left.png"),
    TREE2_LEFT_RIGHT("sprite/tree/tree2_left_right.png"),
    TREE2_LEFT_UP_DOWN("sprite/tree/tree2_left_up_down.png"),
    TREE2_RIGHT("sprite/tree/tree2_right.png"),
    TREE2_RIGHT_UP_DOWN("sprite/tree/tree2_right_up_down.png"),
    TREE3("sprite/tree/tree3.png"),
    TREE3_UP("sprite/tree/tree3_up.png"),
    TREE3_UP_LEFT("sprite/tree/tree3_up_left.png"),
    TREE3_UP_LEFT_RIGHT("sprite/tree/tree3_up_left_right.png"),
    TREE3_UP_RIGHT("sprite/tree/tree3_up_right.png"),
    TREE3_UP_DOWN("sprite/tree/tree3_up_down.png"),
    TREE3_DOWN("sprite/tree/tree3_down.png"),
    TREE3_DOWN_LEFT("sprite/tree/tree3_down_left.png"),
    TREE3_DOWN_LEFT_RIGHT("sprite/tree/tree3_down_left_right.png"),
    TREE3_DOWN_RIGHT("sprite/tree/tree3_down_right.png"),
    TREE3_LEFT("sprite/tree/tree3_left.png"),
    TREE3_LEFT_RIGHT("sprite/tree/tree3_left_right.png"),
    TREE3_LEFT_UP_DOWN("sprite/tree/tree3_left_up_down.png"),
    TREE3_RIGHT("sprite/tree/tree3_right.png"),
    TREE3_RIGHT_UP_DOWN("sprite/tree/tree3_right_up_down.png"),
    TREE4("sprite/tree/tree4.png"),
    TREE4_UP("sprite/tree/tree4_up.png"),
    TREE4_UP_LEFT("sprite/tree/tree4_up_left.png"),
    TREE4_UP_LEFT_RIGHT("sprite/tree/tree4_up_left_right.png"),
    TREE4_UP_RIGHT("sprite/tree/tree4_up_right.png"),
    TREE4_UP_DOWN("sprite/tree/tree4_up_down.png"),
    TREE4_DOWN("sprite/tree/tree4_down.png"),
    TREE4_DOWN_LEFT("sprite/tree/tree4_down_left.png"),
    TREE4_DOWN_LEFT_RIGHT("sprite/tree/tree4_down_left_right.png"),
    TREE4_DOWN_RIGHT("sprite/tree/tree4_down_right.png"),
    TREE4_LEFT("sprite/tree/tree4_left.png"),
    TREE4_LEFT_RIGHT("sprite/tree/tree4_left_right.png"),
    TREE4_LEFT_UP_DOWN("sprite/tree/tree4_left_up_down.png"),
    TREE4_RIGHT("sprite/tree/tree4_right.png"),
    TREE4_RIGHT_UP_DOWN("sprite/tree/tree4_right_up_down.png"),

    // Liste des sprites de l'eau (gelée ou non) étant adaptables
    WATER("sprite/water/water.png"),
    WATER_UP("sprite/water/water_up.png"),
    WATER_UP_LEFT("sprite/water/water_up_left.png"),
    WATER_UP_LEFT_RIGHT("sprite/water/water_up_left_right.png"),
    WATER_UP_RIGHT("sprite/water/water_up_right.png"),
    WATER_UP_DOWN("sprite/water/water_up_down.png"),
    WATER_DOWN("sprite/water/water_down.png"),
    WATER_DOWN_LEFT("sprite/water/water_down_left.png"),
    WATER_DOWN_LEFT_RIGHT("sprite/water/water_down_left_right.png"),
    WATER_DOWN_RIGHT("sprite/water/water_down_right.png"),
    WATER_LEFT("sprite/water/water_left.png"),
    WATER_LEFT_RIGHT("sprite/water/water_left_right.png"),
    WATER_LEFT_UP_DOWN("sprite/water/water_left_up_down.png"),
    WATER_RIGHT("sprite/water/water_right.png"),
    WATER_RIGHT_UP_DOWN("sprite/water/water_right_up_down.png"),
    WATER2("sprite/water/water2.png"),
    WATER2_UP("sprite/water/water2_up.png"),
    WATER2_UP_LEFT("sprite/water/water2_up_left.png"),
    WATER2_UP_LEFT_RIGHT("sprite/water/water2_up_left_right.png"),
    WATER2_UP_RIGHT("sprite/water/water2_up_right.png"),
    WATER2_UP_DOWN("sprite/water/water2_up_down.png"),
    WATER2_DOWN("sprite/water/water2_down.png"),
    WATER2_DOWN_LEFT("sprite/water/water2_down_left.png"),
    WATER2_DOWN_LEFT_RIGHT("sprite/water/water2_down_left_right.png"),
    WATER2_DOWN_RIGHT("sprite/water/water2_down_right.png"),
    WATER2_LEFT("sprite/water/water2_left.png"),
    WATER2_LEFT_RIGHT("sprite/water/water2_left_right.png"),
    WATER2_LEFT_UP_DOWN("sprite/water/water2_left_up_down.png"),
    WATER2_RIGHT("sprite/water/water2_right.png"),
    WATER2_RIGHT_UP_DOWN("sprite/water/water2_right_up_down.png"),

    // Liste des maisons disponibles dans le jeu (quatre types pour les quatre saisons)
    HOUSE1_0("sprite/house/house1_0.png"),
    HOUSE1_1("sprite/house/house1_1.png"),
    HOUSE1_2("sprite/house/house1_2.png"),
    HOUSE1_3("sprite/house/house1_3.png"),
    HOUSE2_0("sprite/house/house2_0.png"),
    HOUSE2_1("sprite/house/house2_1.png"),
    HOUSE2_2("sprite/house/house2_2.png"),
    HOUSE2_3("sprite/house/house2_3.png"),
    HOUSE3_0("sprite/house/house3_0.png"),
    HOUSE3_1("sprite/house/house3_1.png"),
    HOUSE3_2("sprite/house/house3_2.png"),
    HOUSE3_3("sprite/house/house3_3.png"),
    HOUSE4_0("sprite/house/house4_0.png"),
    HOUSE4_1("sprite/house/house4_1.png"),
    HOUSE4_2("sprite/house/house4_2.png"),
    HOUSE4_3("sprite/house/house4_3.png"),

    // Liste de tous les sprites des buildings du centre commercial
    BUILDING_0("sprite/shopping_center/building_0.png"),
    BUILDING_1("sprite/shopping_center/building_1.png"),
    BUILDING_2("sprite/shopping_center/building_2.png"),
    BUILDING_3("sprite/shopping_center/building_3.png"),
    BUILDING_4("sprite/shopping_center/building_4.png"),
    BUILDING_5("sprite/shopping_center/building_5.png"),
    BUILDING_6("sprite/shopping_center/building_6.png"),
    BUILDING_7("sprite/shopping_center/building_7.png"),
    BUILDING_8("sprite/shopping_center/building_8.png"),
    BUILDING_9("sprite/shopping_center/building_9.png"),
    BUILDING_10("sprite/shopping_center/building_10.png"),
    BUILDING_11("sprite/shopping_center/building_11.png"),
    BUILDING_12("sprite/shopping_center/building_12.png"),
    BUILDING_13("sprite/shopping_center/building_13.png"),
    BUILDING_14("sprite/shopping_center/building_14.png"),
    BUILDING_15("sprite/shopping_center/building_15.png"),
    BUILDING_16("sprite/shopping_center/building_16.png"),
    BUILDING_17("sprite/shopping_center/building_17.png"),
    BUILDING_18("sprite/shopping_center/building_18.png"),
    BUILDING_19("sprite/shopping_center/building_19.png"),
    BUILDING_20("sprite/shopping_center/building_20.png"),
    BUILDING_21("sprite/shopping_center/building_21.png"),
    BUILDING_22("sprite/shopping_center/building_22.png"),
    BUILDING_23("sprite/shopping_center/building_23.png"),
    BUILDING2_0("sprite/shopping_center/building2_0.png"),
    BUILDING2_1("sprite/shopping_center/building2_1.png"),
    BUILDING2_2("sprite/shopping_center/building2_2.png"),
    BUILDING2_3("sprite/shopping_center/building2_3.png"),
    BUILDING2_4("sprite/shopping_center/building2_4.png"),
    BUILDING2_5("sprite/shopping_center/building2_5.png"),
    BUILDING2_6("sprite/shopping_center/building2_6.png"),
    BUILDING2_7("sprite/shopping_center/building2_7.png"),
    BUILDING2_8("sprite/shopping_center/building2_8.png"),
    BUILDING2_9("sprite/shopping_center/building2_9.png"),
    BUILDING2_10("sprite/shopping_center/building2_10.png"),
    BUILDING2_11("sprite/shopping_center/building2_11.png"),
    BUILDING2_12("sprite/shopping_center/building2_12.png"),
    BUILDING2_13("sprite/shopping_center/building2_13.png"),
    BUILDING2_14("sprite/shopping_center/building2_14.png"),
    BUILDING2_15("sprite/shopping_center/building2_15.png"),
    FOUNTAIN_0("sprite/shopping_center/fountain_0.png"),
    FOUNTAIN_1("sprite/shopping_center/fountain_1.png"),
    FOUNTAIN_2("sprite/shopping_center/fountain_2.png"),
    FOUNTAIN_3("sprite/shopping_center/fountain_3.png"),
    FOUNTAIN_4("sprite/shopping_center/fountain_4.png"),
    FOUNTAIN_5("sprite/shopping_center/fountain_5.png"),
    FOUNTAIN_6("sprite/shopping_center/fountain_6.png"),
    FOUNTAIN_7("sprite/shopping_center/fountain_7.png"),
    FOUNTAIN_8("sprite/shopping_center/fountain_8.png"),
    STORE_0("sprite/shopping_center/store_0.png"),
    STORE_1("sprite/shopping_center/store_1.png"),
    STORE_2("sprite/shopping_center/store_2.png"),
    STORE_3("sprite/shopping_center/store_3.png"),
    STORE_4("sprite/shopping_center/store_4.png"),
    STORE_5("sprite/shopping_center/store_5.png"),
    STORE_6("sprite/shopping_center/store_6.png"),
    STORE_7("sprite/shopping_center/store_7.png"),
    STORE_8("sprite/shopping_center/store_8.png"),
    STORE_9("sprite/shopping_center/store_9.png"),
    STORE_10("sprite/shopping_center/store_10.png"),
    STORE_11("sprite/shopping_center/store_11.png"),
    STORE_12("sprite/shopping_center/store_12.png"),
    STORE_13("sprite/shopping_center/store_13.png"),
    STORE_14("sprite/shopping_center/store_14.png"),
    STORE_15("sprite/shopping_center/store_15.png"),
    BURGER_0("sprite/shopping_center/burger_0.png"),
    BURGER_1("sprite/shopping_center/burger_1.png"),
    BURGER_2("sprite/shopping_center/burger_2.png"),
    BURGER_3("sprite/shopping_center/burger_3.png"),
    BURGER_4("sprite/shopping_center/burger_4.png"),
    BURGER_5("sprite/shopping_center/burger_5.png"),
    BURGER_6("sprite/shopping_center/burger_6.png"),
    BURGER_7("sprite/shopping_center/burger_7.png"),
    BURGER_8("sprite/shopping_center/burger_8.png"),
    BURGER_9("sprite/shopping_center/burger_9.png"),
    BURGER_10("sprite/shopping_center/burger_10.png"),
    BURGER_11("sprite/shopping_center/burger_11.png"),
    BURGER_12("sprite/shopping_center/burger_12.png"),
    BURGER_13("sprite/shopping_center/burger_13.png"),
    BURGER_14("sprite/shopping_center/burger_14.png"),
    BURGER_15("sprite/shopping_center/burger_15.png"),
    CAR_0("sprite/shopping_center/car_0.png"),
    CAR_1("sprite/shopping_center/car_1.png"),
    CAR_2("sprite/shopping_center/car_2.png"),
    CAR_3("sprite/shopping_center/car_3.png"),
    CAR_4("sprite/shopping_center/car_4.png"),
    CAR_5("sprite/shopping_center/car_5.png"),
    CAR_6("sprite/shopping_center/car_6.png"),
    CAR_7("sprite/shopping_center/car_7.png"),

    // Liste des sprites utilisables par le pacman
    PACMAN_UP_STOP("sprite/pacman/pacman_up_stop.png"),
    PACMAN_UP_MOVE_RIGHT("sprite/pacman/pacman_up_move_right.png"),
    PACMAN_UP_MOVE_LEFT("sprite/pacman/pacman_up_move_left.png"),
    PACMAN_DOWN_STOP("sprite/pacman/pacman_down_stop.png"),
    PACMAN_DOWN_MOVE_RIGHT("sprite/pacman/pacman_down_move_right.png"),
    PACMAN_DOWN_MOVE_LEFT("sprite/pacman/pacman_down_move_left.png"),
    PACMAN_RIGHT_STOP("sprite/pacman/pacman_right_stop.png"),
    PACMAN_RIGHT_MOVE_RIGHT("sprite/pacman/pacman_right_move_right.png"),
    PACMAN_RIGHT_MOVE_LEFT("sprite/pacman/pacman_right_move_left.png"),
    PACMAN_LEFT_STOP("sprite/pacman/pacman_left_stop.png"),
    PACMAN_LEFT_MOVE_RIGHT("sprite/pacman/pacman_left_move_right.png"),
    PACMAN_LEFT_MOVE_LEFT("sprite/pacman/pacman_left_move_left.png"),
    BLUE_GHOST_UP("sprite/pacman/blue_ghost_up.png"),
    BLUE_GHOST_DOWN("sprite/pacman/blue_ghost_down.png"),
    BLUE_GHOST_LEFT("sprite/pacman/blue_ghost_left.png"),
    BLUE_GHOST_RIGHT("sprite/pacman/blue_ghost_right.png"),
    ORANGE_GHOST_UP("sprite/pacman/orange_ghost_up.png"),
    ORANGE_GHOST_DOWN("sprite/pacman/orange_ghost_down.png"),
    ORANGE_GHOST_LEFT("sprite/pacman/orange_ghost_left.png"),
    ORANGE_GHOST_RIGHT("sprite/pacman/orange_ghost_right.png"),
    PINK_GHOST_UP("sprite/pacman/pink_ghost_up.png"),
    PINK_GHOST_DOWN("sprite/pacman/pink_ghost_down.png"),
    PINK_GHOST_LEFT("sprite/pacman/pink_ghost_left.png"),
    PINK_GHOST_RIGHT("sprite/pacman/pink_ghost_right.png"),
    RED_GHOST_UP("sprite/pacman/red_ghost_up.png"),
    RED_GHOST_DOWN("sprite/pacman/red_ghost_down.png"),
    RED_GHOST_LEFT("sprite/pacman/red_ghost_left.png"),
    RED_GHOST_RIGHT("sprite/pacman/red_ghost_right.png"),
    DEAD_GHOST("sprite/pacman/dead_ghost.png"),
    FUGITIVE_GHOST("sprite/pacman/fugitive_ghost.png"),
    PACGUM("sprite/pacman/pacgum.png"),
    SUPER_PACGUM("sprite/pacman/super_pacgum.png"),
    PACMAN_BG("sprite/pacman/pacman_bg.png"),
    PACMAN_WALL("sprite/pacman/pacman_wall.png"),
    PACMAN_SEMI_WALL("sprite/pacman/pacman_semi_wall.png"),
    LETTER_0("sprite/pacman/letter_0.png"),
    LETTER_1("sprite/pacman/letter_1.png"),
    LETTER_2("sprite/pacman/letter_2.png"),
    LETTER_3("sprite/pacman/letter_3.png"),
    LETTER_4("sprite/pacman/letter_4.png"),
    LETTER_5("sprite/pacman/letter_5.png"),
    LETTER_6("sprite/pacman/letter_6.png"),
    LETTER_7("sprite/pacman/letter_7.png"),

    // Liste des cinématiques disponibles
    CINEMATIC1("sprite/cinematic/cinematic1.png"),
    CINEMATIC2("sprite/cinematic/cinematic2.png"),
    CINEMATIC3("sprite/cinematic/cinematic3.png"),
    CINEMATIC4("sprite/cinematic/cinematic4.png"),
    CINEMATIC5("sprite/cinematic/cinematic5.png"),
    CINEMATIC6("sprite/cinematic/cinematic6.png"),
    CINEMATIC7("sprite/cinematic/cinematic7.png"),
    CINEMATIC8("sprite/cinematic/cinematic8.png"),
    CINEMATIC9("sprite/cinematic/cinematic9.png"),
    CINEMATIC10("sprite/cinematic/cinematic10.png"),
    CINEMATIC11("sprite/cinematic/cinematic11.png"),
    CINEMATIC12("sprite/cinematic/cinematic12.png"),

    // Images de fond du chargement et du lanceur
    LOAD("sprite/app/load.jpg"),
    BACKGROUND_LAUNCHER("sprite/app/background_launcher.jpg"),
    BACKGROUND_OPTIONS("sprite/app/background_options.jpg"),
    BACKGROUND_CREDITS("sprite/app/background_credits.jpg"),
    ;

    /**
     * Chemin menant vers le sprite de chaque élément.
     */
    private String spritePath;

    /**
     * Constructeur du sprite grâce au chemin de l'image le représentant.
     * @param spritePath chemin vers le fichier de l'image .png
     */
    Sprite(String spritePath) {
        this.spritePath = spritePath;
    }

    /**
     * Getter du chemin de l'image le représentant.
     * @return String
     */
    public String getSpritePath() {
        return this.spritePath;
    }
}
