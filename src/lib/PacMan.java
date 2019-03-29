package lib;

import config.*;
import config.Object;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Classe représentant et permettant de définir l'intégralité de la structure du Pacman, les conditions de victoire et de
 * défaite, les fantômes et leurs comportements.
 */
public class PacMan {

    /**
     * Vaut false si pacman ne bouge pas, true sinon.
     */
    private static boolean pacmanMovement = false;

    /**
     * Nombre maximal de pacgums pouvant être mangés avant de gagner.
     */
    private static final int MAX_DOTS = 176;

    /**
     * Nombre restant de pacgums avant la victoire.
     */
    private static int remainingDots = 176;

    /**
     * Déclaration du fantôme bleu du pacman.
     */
    private static Ghost ghost1 = new Ghost(new Pair<>(14, 5), AnimationSet.BLUE_GHOST);

    /**
     * Déclaration du fantôme orange du pacman.
     */
    private static Ghost ghost2 = new Ghost(new Pair<>(15, 5), AnimationSet.ORANGE_GHOST);

    /**
     * Déclaration du fantôme rose du pacman.
     */
    private static Ghost ghost3 = new Ghost(new Pair<>(16, 5), AnimationSet.PINK_GHOST);

    /**
     * Déclaration du fantôme rouge du pacman.
     */
    private static Ghost ghost4 = new Ghost(new Pair<>(17, 5), AnimationSet.RED_GHOST);

    /**
     * Carte sur laquelle a lieu le pacman, soit la carte n°10, présente sur le pacman.
     */
    private static Map map = MapConfig.getINSTANCE().getMaps().get(10);

    /**
     * Renvoie true si le pacman est en mouvement, false sinon.
     * @return boolean
     */
    public static boolean isPacmanMovement() {
        return PacMan.pacmanMovement;
    }

    /**
     * Setter du mouvement du pacman. Si pacman est en mouvement alors remise en place des fantômes dans le jeu.
     * @param pacmanMovement booléen définissant si pacman est en mouvement
     */
    public static void setPacmanMovement(boolean pacmanMovement) {
        if(pacmanMovement){
            map.remove(ghost1.cellInMap);
            map.remove(ghost2.cellInMap);
            map.remove(ghost3.cellInMap);
            map.remove(ghost4.cellInMap);
            ghost1.cellInMap.setPosition(new Pair<>(14, 5));
            ghost2.cellInMap.setPosition(new Pair<>(15, 5));
            ghost3.cellInMap.setPosition(new Pair<>(16, 5));
            ghost4.cellInMap.setPosition(new Pair<>(17, 5));
            ghost1.cellInMap.getImage().setTranslateX(0);
            ghost1.cellInMap.getImage().setTranslateY(0);
            ghost2.cellInMap.getImage().setTranslateX(0);
            ghost2.cellInMap.getImage().setTranslateY(0);
            ghost3.cellInMap.getImage().setTranslateX(0);
            ghost3.cellInMap.getImage().setTranslateY(0);
            ghost4.cellInMap.getImage().setTranslateX(0);
            ghost4.cellInMap.getImage().setTranslateY(0);
            map.add(ghost1.cellInMap);
            map.add(ghost2.cellInMap);
            map.add(ghost3.cellInMap);
            map.add(ghost4.cellInMap);

        }
        PacMan.pacmanMovement = pacmanMovement;
    }

    /**
     * Getter du nombre de pacgums restantes dans le jeu.
     * @return int
     */
    public static int getRemainingDots() {
        return PacMan.remainingDots;
    }

    /**
     * Getter du nombre maximal de pacgums dans le jeu.
     * @return int
     */
    public static int getMaxDots() {
        return PacMan.MAX_DOTS;
    }

    /**
     * Setter du nombre restant de pacgums sur le jeu.
     * @param pacmanRemainingDots nouveau nombre de pacgums restantes
     */
    public static void setRemainingDots(int pacmanRemainingDots) {
        PacMan.remainingDots = pacmanRemainingDots;
        if (PacMan.remainingDots == 0) gameOver();
    }

    /**
     * Met en place la fin du jeu selon si le joueur a gagné ou non le pacman. S'il a gagné, alors le joueur gagne un objet
     * ainsi qu'une tenue. Sinon il peut rejouer au pacman ou le passer et avoir directement les récompenses comme s'il avait
     * gagné.
     */
    private static void gameOver(){
        if(PacMan.remainingDots == 0){
            Interaction.PACMAN_IN.setInteractionDone(true);
            DialogLayout.getINSTANCE().setText(DialogConfig.PACMAN_WON.getText());
            Inventory.getINSTANCE().add(Object.OBJ3);
            Player.getINSTANCE().getSkinAvailables().add(3);
            MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PACMAN_SKIP.getEventHandler());
        }
        else{
            DialogLayout.getINSTANCE().setText(DialogConfig.PACMAN_LOSE.getText());
            MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_PRESSED, Interaction.PACMAN_SKIP.getEventHandler());
        }
        MapConfig.getINSTANCE().configMap(Planet.PLANET3.getMaps().get(1), new Pair<>(20, 5));
        MainLayout.getSCENE().addEventHandler(KeyEvent.KEY_RELEASED, Movement.getStopEventHandler());
        Player.getINSTANCE().setCurrentSkin(0);
        Movement.setAnimationSet(AnimationSet.getAnimationSet(Player.getINSTANCE().getCurrentSkin()*4).getStopAnimationSet());
        Player.getINSTANCE().setSprite(Movement.getAnimationSet().getSpriteDirection(Player.getINSTANCE().getDirection()));
        Movement.setLastKeyReleased(true);
        PacMan.setPacmanMovement(false);
        MainLayout.getSCENE().removeEventHandler(KeyEvent.KEY_PRESSED, Interaction.PACMAN_IN.getEventHandler());
    }

    /**
     * Active le déplacement de l'ensemble des fantômes.
     */
    public static void moveGhosts(){
        ghost1.move();
        ghost2.move();
        ghost3.move();
        ghost4.move();
    }

    /**
     * Classe interne représentant les fantômes présents dans le pacman.
     */
    private static class Ghost {

        /**
         * Animations de chacun des fantômes du pacman, dans leur quatre directions.
         */
        private AnimationSet animationSet;

        /**
         * Cellule sur laquelle se situe actuellement le fantôme dans le pacman.
         */
        private Cell cellInMap;

        /**
         * Sprite du fantôme (bleu, orange, rose ou rouge).
         */
        private Sprite sprite;

        /**
         * Constructeur du fantôme du pacman avec en paramètre son point de départ et ses animations.
         * @param position position initiale du fantôme dans le pacman
         * @param animationSet animations dans les quatres directions des fantômes
         */
        public Ghost(Pair<Integer, Integer> position, AnimationSet animationSet) {
            this.animationSet = animationSet;
            this.sprite = this.animationSet.getDown();
            this.cellInMap = new Cell( this.sprite, position);

        }

        /**
         * Permet le déplacement de chacun des fantômes ayant tous le même comportement. Leurs déplacements sont aléatoires
         * et sont modifiés à chaque intersection dans le pacman.
         */
        public void move(){
            Pair<Integer, Integer> position = this.cellInMap.getPosition();
            if(playerSameCase()){
                gameOver();
                return;
            }
            Cell upCell = MapConfig.getLastCell(10, position.getKey(), position.getValue() - 1);
            Cell downCell = MapConfig.getLastCell(10, position.getKey() ,  position.getValue() + 1);
            Cell rightCell = MapConfig.getLastCell(10, position.getKey() + 1, position.getValue());
            Cell leftCell = MapConfig.getLastCell(10, position.getKey() - 1, position.getValue());

            boolean canMoveUp = upCell != null && isGoodSprite(upCell.getSprite());
            boolean canMoveDown = downCell != null && isGoodSprite(downCell.getSprite());
            boolean canMoveRight = rightCell != null && isGoodSprite(rightCell.getSprite());
            boolean canMoveLeft = leftCell != null && isGoodSprite(leftCell.getSprite());

            TranslateTransition tt = new TranslateTransition(Duration.millis(Movement.getDelay()));

            tt.setInterpolator(Interpolator.LINEAR);
            double x = 0;
            double y = 0;

            Direction direction = randomMove(canMoveUp, canMoveDown, canMoveRight, canMoveLeft);

            if(direction != null){
                switch (direction){
                    case UP:
                        y = -50;
                        this.sprite = animationSet.getUp();
                        position = upCell.getPosition();
                        cellInMap.setPosition(position);
                        break;
                    case DOWN:
                        y = 50;
                        this.sprite = animationSet.getDown();
                        position = downCell.getPosition();
                        cellInMap.setPosition(position);
                        break;
                    case RIGHT:
                        x = 50;
                        this.sprite = animationSet.getRight();
                        position = rightCell.getPosition();
                        cellInMap.setPosition(position);
                        break;
                    case LEFT:
                        x = -50;
                        this.sprite = animationSet.getLeft();
                        position =leftCell.getPosition();
                        cellInMap.setPosition(position);
                        break;
                    default:
                        break;

                }
                this.cellInMap.setSprite(sprite);
                tt.setNode(this.cellInMap.getImage());
                tt.setByX(x);
                tt.setByY(y);
                tt.play();
            }
        }

        /**
         * Permet de renvoyer une direction aléatoire pour le fantôme dès qu'il arrive à une intersection.
         * @param canMoveUp booléen définissant s'il peut aller en haut
         * @param canMoveDown booléen définissant s'il peut aller en bas
         * @param canMoveRight booléen définissant s'il peut aller à droite
         * @param canMoveLeft booléen définissant s'il peut aller à gauche
         * @return Direction
         */
        private Direction randomMove(boolean canMoveUp, boolean canMoveDown, boolean canMoveRight, boolean canMoveLeft){
            ArrayList<Boolean> arrayList = new ArrayList<>(Arrays.asList(canMoveUp, canMoveDown, canMoveRight, canMoveLeft));
            ArrayList<Direction> arrayList2 = new ArrayList<>(Arrays.asList(Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT));
            ArrayList<Integer> arrayList3 = new ArrayList<>();
            for(int i = 0; i < arrayList.size(); ++i){
                if(arrayList.get(i)){
                    arrayList3.add(i);
                }
            }
            Random random = new Random();
            if(arrayList3.size() == 0)
                return null;
            return arrayList2.get( arrayList3.get(random.nextInt(arrayList3.size())));
        }

        /**
         * Renvoie true s'il s'agit d'un sprite sur lequel le fantôme peut aller, false sinon.
         * @param sprite sprite à tester
         * @return boolean
         */
        private boolean isGoodSprite(Sprite sprite){
            return sprite.equals(Sprite.PACMAN_BG) || sprite.equals(Sprite.PACGUM) || sprite.equals(Sprite.PACMAN_SEMI_WALL) || sprite.equals(Player.getINSTANCE().getSprite());
        }

        /**
         * Renvoie true si le joueur croise un des fantômes. Permet de savoir à chaque instant si le joueur a perdu ou non.
         * @return boolean
         */
        private boolean playerSameCase(){
            Player player = Player.getINSTANCE();
            Direction direction = animationSet.getDirection(this.sprite);
            Pair<Integer, Integer> pair = null;
            switch (direction){
                case UP:
                    pair = new Pair<>(this.cellInMap.getPosition().getKey(), this.cellInMap.getPosition().getValue() - 1 );
                    break;
                case DOWN:
                    pair = new Pair<>(this.cellInMap.getPosition().getKey(), this.cellInMap.getPosition().getValue() + 1 );
                    break;
                case RIGHT:
                    pair = new Pair<>(this.cellInMap.getPosition().getKey() - 1, this.cellInMap.getPosition().getValue() );
                    break;
                case LEFT:
                    pair = new Pair<>(this.cellInMap.getPosition().getKey() + 1, this.cellInMap.getPosition().getValue() );
                    break;
                default:
                    break;
            }

            boolean oppositeDirection = false;
            if(player.getPosition().equals(pair)){
                if (Math.abs(player.getDirection().ordinal() - animationSet.getDirection(cellInMap.getSprite()).ordinal()) == 1 &&
                        (Math.floorDiv( player.getDirection().ordinal(),2) == Math.floorDiv(animationSet.getDirection(cellInMap.getSprite()).ordinal(),2)))
                    oppositeDirection = true;
            }
            return (this.cellInMap.getPosition().equals(Player.getINSTANCE().getPosition()) ||  oppositeDirection );
        }
    }
}
