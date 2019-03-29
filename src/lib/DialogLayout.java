package lib;

import config.Action;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * La classe DialogLayout sert à mettre en place la boîte de dialogue, les boutons contenus dans celle-ci et l'argent que
 * possède Leskak. Cette classe est un singleton.
 */
public class DialogLayout {

    /**
     * Texte à afficher dans la boite de dialogue.
     */
    private Text text;

    /**
     * GridPane formant le DialogLayout, permettant de savoir où placer chaque élément.
     */
    private GridPane gridPane;

    /**
     * Vertical Box contenant les boutons (pour répondre aux énigmes, récupérer ou donner des objets, ou se téléporter sur d'autres planètes).
     */
    private VBox buttons;

    /**
     * Représente l'argent que possède Leskak.
     */
    private int money;

    /**
     * Instance de DialogLayout, permet à la classe d'être un singleton.
     */
    private static final DialogLayout INSTANCE = new DialogLayout();

    /**
     * Constructeur du Dialoglayout initialisant sa mise en forme, ses contraintes...
     */
    private DialogLayout() {
        this.text = new Text();
        this.text.setWrappingWidth((float) (MainLayout.getWIDTH()*2/3)*2/3);
        this.text.setTextAlignment(TextAlignment.JUSTIFY);
        this.text.setFont(Font.font("Lucida Console"));
        this.text.setFill(Color.WHITE);
        VBox pane = new VBox();
        pane.getChildren().add(this.text);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50));
        GridPane.setConstraints(pane, 0, 0);

        this.money = 0;
        VBox pane1 = new VBox();
        pane1.setAlignment(Pos.CENTER);
        Text text = new Text();
        text.setFill(Color.WHITE);

        pane1.getChildren().add(text);
        GridPane.setConstraints(pane1, 1, 1 );

        this.buttons = new VBox();
        this.buttons.setSpacing(1);
        this.buttons.setAlignment(Pos.CENTER);
        GridPane.setConstraints(this.buttons, 1, 0 );

        this.gridPane = new GridPane();
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        this.gridPane.getChildren().add(pane);
        this.gridPane.getChildren().add(pane1);
        this.updateMoney();
        this.gridPane.getChildren().add(this.buttons);
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)(MainLayout.getWIDTH()*2/3)*2/3));
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)(MainLayout.getWIDTH()*2/3)/3));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)(MainLayout.getHEIGHT()/3)*2/3));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)(MainLayout.getHEIGHT()/3)/3));
    }

    /**
     * Getter de la grille du DialogLayout.
     * @return GridPane
     */
    public GridPane getGridPane() {
        return this.gridPane;
    }

    /**
     * Renvoie l'instance de DialogLayout.
     * @return DialogLayout
     */
    public static DialogLayout getINSTANCE() {
        return DialogLayout.INSTANCE;
    }

    /**
     * Setter du texte de la boîte de dialogue.
     * @param string texte de la boîte de dialogue
     */
    public void setText(String string){
        this.text.setText(string);
    }

    /**
     * Réinitialise le contenu de la boîte de dialogue.
     */
    public void removeContent(){
        this.text.setText("");
        this.buttons.getChildren().remove(0, this.buttons.getChildren().size());
    }

    /**
     * Ajoute un nouveau bouton à la VBox.
     * @param name texte du bouton
     * @param eventHandler événement associé au bouton
     */
    public void addButton(String name, EventHandler<ActionEvent> eventHandler){
        Button button = new Button(name);
        button.setOnAction(eventHandler);
        button.setScaleY(0.8);
        button.setScaleX(0.8);
        button.getStyleClass().add("interact_button");
        button.focusedProperty().addListener((observable, oldValue, newValue) -> button.setStyle(observable.getValue() ? "-fx-background-color: lightgrey;" : ""));

        this.buttons.getChildren().add(button);

        Movement.removeMovement();
        if(this.buttons.getChildren().size() == 1){
            button.requestFocus();
        }
    }

    /**
     * Ajoute un bouton de retour à la VBox.
     */
    public void addReturnButton(){
        if(this.buttons.getChildren().isEmpty())
            return;
        Button button = new Button("Retour");
        button.setScaleY(0.8);
        button.setScaleX(0.8);
        button.setOnAction(Action.RETURN.getEventHandler());
        button.setCancelButton(true);
        button.getStyleClass().add("interact_button");
        button.focusedProperty().addListener((observable, oldValue, newValue) -> button.setStyle(observable.getValue() ? "-fx-background-color: lightgrey" : ""));

        this.buttons.getChildren().addAll(button);

        Movement.removeMovement();
    }

    /**
     * Getter de l'argent que possède Leskak.
     * @return int
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Ajoute de l'argent à la somme que possède déjà Leskak.
     * @param money somme à ajouter
     */
    public void addMoney(int money) {
        this.money += money;
        this.updateMoney();
    }

    /**
     * Enlève de l'argent à la somme que possède Leskak.
     * @param money somme à retirer
     */
    public void removeMoney(int money) {
        this.money -= money;
        if(this.money < 0)
            this.money = 0;
        this.updateMoney();
    }

    /**
     * Permet de mettre à jour l'affichage de la somme que possède Leskak dans la boîte de dialogue.
     */
    private void updateMoney(){
        VBox pane = (VBox) this.getGridPane().getChildren().get(1);
        Text text = (Text) pane.getChildren().get(0);
        text.setText("Argent : " + this.money + "€");
    }
}
