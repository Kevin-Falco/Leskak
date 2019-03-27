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
    PNJ5_2_BEFORE("Quoi ?! Tu dis que le colis que j'ai reçu fait sans doute parti d'une série endommagée ? Bon tant pis.\n" +
            "Je te laisse l'emmener au centre commercial afin que tu puisses le faire réparer, il est sous garantie.\n" +
            "Par la suite, cela serait gentil si tu pouvais l'amener à mon fils, sur la planète Kavi, il devrait en avoir plus besoin que moi au final."),
    PNJ5_2_AFTER("Une fois réparé, amène le colis à mon fils sur Kavi s'il te plait."),
    PNJ5_2_BUTTON("Reprendre le colis"),
    BUSH1_BEFORE("Il y a un colis dans ces hautes herbes. Souhaitez-vous le prendre ?"),
    BUSH1_AFTER(""),
    BUSH1_BUTTON("Prendre le colis"),
    CAT1("Miaou !"),
    CAT2_BEFORE("Ce chat a une bourse dans la gueule. Voulez-vous la prendre ?"),
    CAT2_AFTER("MIAOOOOU ! Il n'est pas content de ne plus avoir sa bourse ! Le paaauvre."),
    CAT2_BUTTON("Lui prendre sa bourse"),
    CHICKEN("Côôôt côt côt"),
    FOX("What does the fox say ? \"Wa-pa-pa-pa-pa-pa-pow!\""),
    FOX_BEFORE("Je vais vous raconter une petite histoire ...\n" +
            "       Maître Corbeau, sur un arbre perché,\n" +
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
    STATUE_BEFORE("Qui est le créateur du Java ? Petit indice : il s'agit du plus beau des Gosling !"),
    STATUE_AFTER("Pourquoi parles-tu à une statue ? T'es bête ou quoi ?"),
    STATUE_ERROR("Je t'ai dit le plus beau des Gosling ! Tu le fais exprès ou quoi ?"),
    STATUE_SUCCESS("Eh oui, oui, oui, oui ! C'est gagnéééé !"),
    STATUE_BUTTON1("Ryan Gosling"),
    STATUE_BUTTON2("James Gosling"),
    STATUE_BUTTON3("Dennis Ritchie"),
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
    PNJ10_PACKAGE_BEFORE("Oh merci ! Tu nous ramènes un colis parmi ceux qui étaient endommagés"),
    PNJ10_PACKAGE_AFTER("Ca y est ton colis est tout neuf !  Tu peux le remettre à son propriétaire !"),
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
    PNJ13("Hulk pas content !  Hulk vouloir tout casser !"),
    PNJ14_BEFORE("Salut c'est Captain ! Ici nous sommes sur Longstar, où il régne un toujours un temps très automnal, à l'inverse de la planète Kavi où habite mon frère ! Il fait un froid de canard là bas."),
    PNJ14_AFTER("*Ouvre le colis* Beurk ! J'aime pas du tout la couleur de ce costume ! Tu pourrais aller me chercher quelques plumes de poulets ? Histoire que j'améliore tout ça."),
    PNJ14_2_BEFORE("Ramène moi quelques plumes de poulet, s'il te plait ! Je n'aime vraiment pas la couleur du costume !"),
    PNJ14_2_BUTTON("Rendre les 3 plumes"),
    PNJ14_2_AFTER("Ah parfait ! Je vais pouvoir changer la couleur de ce costume, attends juste deux petites secondes ...\n\n" +
            "*fait deux, trois arrangements*\n\n" +
            "Voilà c'est terminé ! Par contre j'ai remarqué un petit détail... Il y a écrit Leskak sur l'étiquette. Je crois qu'il s'agit de ton colis depuis le début mon cher... du coup je te la donne !" +
            " Appuie sur " + Key.CHANGE_SKIN.getKeyCode().getName() + " pour changer de tenue."),
    PNJ15_BEFORE("J'ai hâte de recevoir mon colis plein de produits super high-tech !"),
    PNJ15_AFTER("Moi qui était si content d'avoir enfin reçu... et il m'a explosé dans les mains ! Nom d'un Stark ! J'espère que ça ne va arriver à personne d'autres..."),
    PNJ16("BRING ME THANOOOOOOOOOOOS !"),
    PNJ17_BEFORE("ICIIII, C'EST KAVIIIII ! Ah bah non, on a encore perdu en Ligue des Champions..."),
    PNJ17_AFTER("C'est quoi ce colis ? C'est mon père qui t'envoie ? Mais j'en veux pas de ton truc là ! Amène le à mon frangin plutôt, il habite sur Longstar."),
    CHICKEN_BEFORE("Voulez-vous arracher une plume à ce pauvre poulet ?"),
    CHICKEN_AFTER("Ca a eu l'air plutôt douloureux. LE PAAAUVRE !"),
    CHICKEN_BUTTON("Arracher une plume"),
    PNJ18("Un avalanche a dépose ce caillou devant l'entrée de la grotte, bloquant ici son entrée... Peut-être que de la dynamite pourrait arranger ça ?"),
    PNJ19("A CHANGER : Tiens le rayon laser"),
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
