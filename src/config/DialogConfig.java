package config;

public enum DialogConfig {
    PNJ1("Salut étranger ! Ici nous sommes sur la planète Jansen. Cette planète est couverte d'arbres et de plaines verdoyantes !"),
    PNJ2("Tu t'appelles Leskak ? Et tu cherches une plaque de tôle ? Il y a un ermite à l'Est qui a refait sa maison il y a peu. Il doit posséder ce que tu cherches."),
    PNJ3_BEFORE("Etranger ! Etranger ! Je dois me rendre sur Hibliss dans moins d'une heure et je ne trouve plus le colis que je devais livrer ! J'ai du le perdre en m'enfuyant face aux serpents sauvages tout à l'heure. S'il te plait, AIDE-MOI A LE RETROUVER !"),
    PNJ3_AFTER("Merci énormément étranger ! Je prépare mon téléporteur et je vais livrer mon colis de ce pas !"),
    PNJ3_BUTTON("Donner le colis"),
    PNJ3_2_BEFORE("Oh ! Vous avez rencontré le libraire d'Hibliss ? Et il veut son colis au plus vite ? Très bien je vous le donne, allez lui remettre de ce pas !"),
    PNJ3_2_AFTER("Allez livrer ce colis au plus vite !"),
    PNJ3_2_BUTTON("Reprendre le colis"),
    PNJ4_BEFORE("Tu cherches une plaque de tôle ? En effet, j'en possède une qui traîne dans mon grenier depuis les travaux. Si tu veux je te l'offre !"),
    PNJ4_AFTER("Rends toi sur la planète Hibliss si tu souhaites continuer ta quête."),
    PNJ4_BUTTON("Prendre la plaque de tôle"),
    PNJ5_BEFORE("Bienvenue étranger ! En effet, vous êtes bien sur la planète Hibliss, là où les pilotes peu aguerris perdent leurs vaisseaux !\n" +
            "Si tu cherches des réacteurs en bon état, n'hésite pas à t'aventurer sur cette planète qui est un vrai labyrinthe !\n" +
            "Par ailleurs mon ami, aurais-tu vu un livreur sur la planète Jansen ? Il a mon colis et j'aimerais le récupérer au plus vite..."),
    PNJ5_AFTER("Oh ! Merci beaucoup ! Bonne chance encore pour ta quête !"),
    PNJ5_BUTTON("Rendre le colis"),
    BUSH1_BEFORE("Il y a un colis dans ces hautes herbes. Souhaitez-vous le prendre ?"),
    BUSH1_AFTER(""),
    BUSH1_BUTTON("Prendre le colis"),
    CAT1("Miaou !"),
    CAT2_BEFORE("Ce chat a une bourse dans la gueule. Voulez-vous la prendre ?"),
    CAT2_AFTER("MIAOOOOU ! Il n'est pas content de ne plus avoir sa bourse ! Le paaauvre."),
    CAT2_BUTTON("Lui prendre sa bourse"),
    FOX_BEFORE("Je vais vous raconter une petite histoire ...\n       Maître Corbeau, sur un arbre perché,\n" +
            "           Tenait en son bec un fromage.\n" +
            "       Maître Renard, par l'odeur ... "),
    FOX_AFTER("Tu peux partir maintenant. Allez !"),
    FOX_ERROR("Mauvaise réponse. IGNORANT !"),
    FOX_SUCCESS("Félicitations. Je t'offre 500€ afin que tu puisses accomplir ta quête."),

    FOX_BUTTON1("Subjuguée"),
    FOX_BUTTON2("Alléchée"),
    FOX_BUTTON3("Tout emoustillée"),
    SNAKE("Ssssssssssss !"),
    ;

    private String text;

    DialogConfig(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
