package config;


public enum DialogConfig {
    PNJ1("Salut étranger ! Ici nous sommes sur la planète Jansen. Cette planète est couverte d'arbres et de plaines verdoyantes !"),
    PNJ2("Tu t'appelles Leskak ? Et tu cherches une plaque de tôle ? Il y a un ermite à l'Est qui a refait sa maison il y a peu. Il doit posséder ce que tu cherches."),
    PNJ3_BEFORE("Etranger ! Etranger ! Je dois me rendre sur Hibliss dans moins d'une heure et je ne trouve plus le colis que je devais livrer ! J'ai du le perdre en m'enfuyant face aux serpents sauvages tout à l'heure. S'il te plait, AIDE-MOI A LE RETROUVER !"),
    PNJ3_AFTER("Merci énormément étranger ! Je te donne un peu d'argent pour m'avoir aider ! Je prépare mon téléporteur et je vais livrer mon colis de ce pas !"),
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
    PNJ5_2_BEFORE("A CHANGER : Problème avec colis défectueux"),
    PNJ5_2_AFTER("A CHANGER : Après l'avoir amené réparé go planète 4"),
    PNJ5_2_BUTTON("Reprendre le colis"),
    BUSH1_BEFORE("Il y a un colis dans ces hautes herbes. Souhaitez-vous le prendre ?"),
    BUSH1_AFTER(""),
    BUSH1_BUTTON("Prendre le colis"),
    CAT1("Miaou !"),
    CAT2_BEFORE("Ce chat a une bourse dans la gueule. Voulez-vous la prendre ?"),
    CAT2_AFTER("MIAOOOOU ! Il n'est pas content de ne plus avoir sa bourse ! Le paaauvre."),
    CAT2_BUTTON("Lui prendre sa bourse"),
    CHICKEN("A CHANGER : Côôôt côt côt"),
    FOX("A CHANGER :  What does the fox say ?"),
    FOX_BEFORE("Je vais vous raconter une petite histoire ...\n       Maître Corbeau, sur un arbre perché,\n" +
            "           Tenait en son bec un fromage.\n" +
            "       Maître Renard, par l'odeur ... "),
    FOX_AFTER("Tu peux partir maintenant. Allez !"),
    FOX_ERROR("Mauvaise réponse. IGNORANT !"),
    FOX_SUCCESS("Félicitations. Je t'offre 500€ afin que tu puisses accomplir ta quête."),
    FOX_BUTTON1("Subjuguée"),
    FOX_BUTTON2("Alléchée"),
    FOX_BUTTON3("Tout emoustillée"),
    PNJ6_BEFORE("Salut étranger ! Connais-tu la devise la Miage ? \"La Miage c'est le ...\""),
    PNJ6_AFTER("Je n'ai plus rien à te dire maintenant. Pars étranger !"),
    PNJ6_ERROR("A toi, on voit que tu n'es pas un bon miagiste !"),
    PNJ6_SUCCESS("C'est ça ! La Miage c'est le Partage !"),
    PNJ6_BUTTON1("MAAAAAL !"),
    PNJ6_BUTTON2("partage !"),
    PNJ6_BUTTON3("BIIIIEN !"),
    STATUE_BEFORE("A CHANGER : Enigme STATUE"),
    STATUE_AFTER("A CHANGER : t'as win dégage"),
    STATUE_ERROR("A CHANGER : Et c'est perdu"),
    STATUE_SUCCESS("A CHANGER : Et c'est gagné"),
    STATUE_BUTTON1("A CHANGER : mauvaise réponse"),
    STATUE_BUTTON2("A CHANGER : bonne réponse"),
    STATUE_BUTTON3("A CHANGER : mauvaise réponse"),
    SNAKE("Ssssssssssss !"),
    PNJ7("Je me suis perdue dans cet immense labyrinthe et je crois que je ne pourrais jamais trouver la sortie...\n" +
            "Si jamais tu y arrives étranger, saches que j'ai enterré un coffre au trésor derrière la maison d'un ermite sur Jansen.\n" +
            "Peut être trouveras-tu ton bonheur à l'intérieur"),
    CHEST_BEFORE_HIDDEN("La terre semble molle à cet endroit. Il est préférrable de ne pas marcher ici."),
    CHEST_HIDDEN("Avec plus d'attention, il semble bien que la terre a été retournée à cet endroit..."),
    CHEST_HIDDEN_BUTTON("Creuser"),
    CHEST_CLOSED("Il y avait bien un coffre là dessous !"),
    CHEST_CLOSED_BUTTON("Ouvrir le coffre"),
    CHEST_OPENED("Vous trouvez 2000 pièces."),
    CHEST_AFTER_OPENED("Le coffre est désormais aussi vide que votre avenir."),
    SPACESHIP_BEFORE("C'est un vaisseau écrasé ! Il y a même une bourse pleine à l'intérieur ! Mais ces réacteurs sont en piteux état... "),
    SPACESHIP_BUTTON("Prendre les réacteurs endommagés"),
    SPACESHIP_AFTER("Peut être qu'en allant au centre commercial, je trouverais un moyen de réparer ces réacteurs..."),
    PNJ8("Ma baguette magique a été faite avec du CRAIN DE LICORNE, Monsieur !"),
    PNJ9("Ma baguette magique a été faite avec un VENTRICULE DE DRAGON, Monsieur !"),
    PNJ10("Bienvenue au point de vente intergalactique ! Nous réparons et vendons de nombreux articles. Faites-vous plaisir !"),
    PNJ10_PACKAGE_BEFORE("A CHANGER : oh un colis endommagé je dois vite le réparer"),
    PNJ10_PACKAGE_AFTER("A CHANGER : colis réparé! ++A"),
    PNJ10_PACKAGE_BUTTON("Faire réparer le colis"),
    PNJ10_BUTTON1("Réparer les réacteurs (500€)"),
    PNJ10_BUTTON2("Tenue blanche (5.000€)"),
    PNJ10_BUTTON3("Dynamite (10.000€)"),
    PNJ10_NOT_ENOUGH_MONEY("Il semblerait que vous n'ayez pas assez d'argent pour cet article."),
    PNJ10_REACTORS_REPARED("Et voilà, terminé en deux coups de cuiller à pot ! Vos réacteurs sont réparés!\n" +
            "Quoi ?! Tu cherches à aller sur Longstar ? Vas voir M. Stark près des bureaux, il possède un carnet avec les coordonnées de nombreuses planètes."),
    PNJ10_SKIN_BUYED("Voilà une nouvelle tenue pour ta collection ! Appuie sur " + Key.CHANGE_SKIN.getKeyCode().getName() + " pour en changer !"),
    PNJ11_BEFORE("Salut toi !  Je m'appelle Rokhan et je viens du planète lointaine du nom de Kavi. Mais je ne peux désormais plus y retourner à cause de mes problèmes de dos...\n" +
            "Pourrais-tu m'aider à retrouver une bague que j'ai perdu là bas ? Elle est très préciseuse et vaut très cher. Je te donnerais un petit quelque chose en échange.\n" +
            "MAIS NE VAS SURTOUT PAS LA VENDRE AU MARCHAND ! COMPRIS ?"),
    PNJ11_AFTER("Ohhh ! Tu as retrouvé ma bague ! Merci beaucoup ! Comme convenu, je te donne un peu d'argent pour t'aider dans ta quête !"),
    PNJ11_BUTTON("Donner la bague"),
    PNJ12("Les coordonnées de la planète Longstar ?! Répare déjà tes réacteurs avant que je t'en dise plus."),
    PNJ12_2("Ooooh des réacteurs neufs ! Tu pourrais aller sur Longstar avec ça ! Elle se situe en Latitude:43.51715 et Longitude: 5.456508 dans le secteur NeufTroisQuart !"),
    PNJ13("A CHANGER : HULK SMASH!"),
    PNJ14_BEFORE("A CHANGER : PD"),
    PNJ14_AFTER("A CHANGER : *Ouvre le colis* Quete des poulets"),
    PNJ14_2_BEFORE("A CHANGER : Va chercher des poulets la"),
    PNJ14_2_BUTTON("A CHANGER : Rendre les 3 plumes"),
    PNJ14_2_AFTER("A CHANGER : Merci mais en fait c'est ton colis lol c'est une nouvelle tenur appuie sur " + Key.CHANGE_SKIN.getKeyCode().getName() + " pour en changer"),
    PNJ15_BEFORE("A CHANGER : Coucou jsuis IronMan lol"),
    PNJ15_AFTER("A CHANGER : Mon colis a explosé va le rechercher planete 2 "),
    PNJ16("A CHANGER : I AM THOR"),
    PNJ17_BEFORE("A CHANGER : Il fait beau aujourd'hui. Ah non  c'est une planète de glace lol. Puis la map elle est moche en plus ptdr Keke t'es nul. Et c'est pas Yaël qui le dit c'est moi, la map est symetrique de ouf et l'eau on dirait juste des rochers lol. Va apprendre à faire des sprites gros naaaaze."),
    PNJ17_AFTER("A CHANGER : J'en veux pas de ton colis pourri"),
    CHICKEN_BEFORE("A CHANGER : vous voulez lui prendre une plume ?"),
    CHICKEN_AFTER("A CHANGER : ça a eu l'air douloureux. LE POOOOOOOOOOOOOOOVRE"),
    CHICKEN_BUTTON("Arracher une plume"),
    PNJ18("A CHANGER : Il faut de la dynamite"),
    PNJ19("A CHANGER : Je suis le panda"),
    PNJ20("A CHANGER : Tiens le rayon laser"),
    PACMAN_WON("A CHANGER : Gagné, nouveau skin et panneau de controle"),
    PACMAN_LOSE("A CHANGER : Perdu"),
    ;

    private String text;

    DialogConfig(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
