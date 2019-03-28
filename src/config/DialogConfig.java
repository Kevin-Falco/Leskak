package config;


public enum DialogConfig {
    PNJ1("Felicity\n\n    Salut étranger ! Ici nous sommes sur la planète Jansen. Cette planète est couverte d'arbres et de plaines verdoyantes ! Le temps est toujours printanier ici."),
    PNJ2("Oliver (Objectif principal)\n\n    Tu t'appelles Leskak ? Et tu cherches une plaque de tôle ? Il y a un ermite à l'Est qui a refait sa maison il y a peu. Il doit posséder ce que tu cherches."),
    PNJ3_BEFORE("Facteur (Quête du colis)\n\n    Etranger ! Etranger ! Je dois me rendre sur Hibliss dans moins d'une heure et je ne trouve plus le colis que je devais livrer là bas ! J'ai du le perdre dans les hautes herbes au Nord en m'enfuyant face aux serpents sauvages tout à l'heure. S'il te plait, aide moi à le retrouver !"),
    PNJ3_AFTER("Facteur (Quête du colis)\n\n    Merci énormément étranger ! Je te donne un peu d'argent pour m'avoir aider ! Je prépare mon téléporteur et je vais livrer mon colis de ce pas !"),
    PNJ3_BUTTON("Donner le colis"),
    PNJ3_2_BEFORE("Facteur (Quête du colis)\n\n    Oh ! Vous avez rencontré le libraire d'Hibliss ? Et il veut son colis au plus vite ? Très bien je vous le donne, allez lui remettre de ce pas !"),
    PNJ3_2_AFTER("Facteur (Quête du colis)\n\n    Allez livrer ce colis au plus vite !"),
    PNJ3_2_BUTTON("Reprendre le colis"),
    PNJ4_BEFORE("Ermite (Objectif principal)\n\n    Tu cherches une plaque de tôle ? En effet, j'en possède une qui traîne dans mon grenier depuis les travaux. Si tu veux je te l'offre !"),
    PNJ4_AFTER("Ermite (Objectif principal)\n\n    Rends toi sur la planète Hibliss si tu souhaites continuer ta quête."),
    PNJ4_BUTTON("Prendre la plaque de tôle"),
    BUSH1_BEFORE("(Quête du colis)\n\n    Il y a un colis dans ces hautes herbes. Souhaitez-vous le prendre ?"),
    BUSH1_AFTER(""),
    BUSH1_BUTTON("Prendre le colis"),
    CAT1("Chat\n\n    Miaaaou !"),
    CAT2_BEFORE("Chat\n\n    Ce chat a une bourse dans la gueule. Voulez-vous la prendre ?"),
    CAT2_AFTER("Chat\n\n    MIAOOOOU ! Il n'est pas content de ne plus avoir sa bourse ! Le paaauvre."),
    CAT2_BUTTON("Lui prendre sa bourse"),
    SNAKE("Ssssssssssss !"),
    FOX("Renard\n\n    What does the fox say ? \"Wa-pa-pa-pa-pa-pa-pow!\""),
    FOX_BEFORE("Maître Renard (Objectif secondaire)\n\n    Je vais vous raconter une petite histoire ...\n" +
            "       Maître Corbeau, sur un arbre perché,\n" +
            "       Tenait en son bec un fromage.\n" +
            "       Maître Renard, par l'odeur ... "),
    FOX_AFTER("Maître Renard (Objectif secondaire)\n\n    Tu peux partir maintenant. Allez !"),
    FOX_ERROR("Maître Renard (Objectif secondaire)\n\n    Mauvaise réponse. IGNORANT !"),
    FOX_SUCCESS("Maître Renard (Objectif secondaire)\n\n    Félicitations. Je t'offre 500€ afin que tu puisses accomplir ta quête."),
    FOX_BUTTON1("Subjuguée"),
    FOX_BUTTON2("Alléchée"),
    FOX_BUTTON3("Emoustillée"),
    PNJ5_BEFORE("William (Quête du colis)\n\n    Bienvenue étranger ! En effet, vous êtes bien sur la planète Hibliss, là où les pilotes peu aguerris perdent leurs vaisseaux !\n" +
            "    Si tu cherches des réacteurs en bon état, n'hésite pas à t'aventurer sur cette planète qui est un vrai labyrinthe !\n" +
            "    Par ailleurs mon ami, aurais-tu vu un livreur sur la planète Jansen ? Il a mon colis et j'aimerais le récupérer au plus vite..."),
    PNJ5_AFTER("William (Quête du colis)\n\n    Oh ! Merci beaucoup ! Bonne chance encore pour ta quête !"),
    PNJ5_BUTTON("Rendre le colis"),
    PNJ5_2_BEFORE("William (Quête du colis)\n\n    Quoi ?! Tu dis que le colis que j'ai reçu fait sans doute parti d'une série endommagée ? Ce n'est pas très grave.\n" +
            "Je te laisse l'emmener au centre commercial afin que tu puisses le faire réparer, il est sous garantie.\n" +
            "Par la suite, cela serait gentil si tu pouvais l'amener à mon fils, sur la planète Kavi, il devrait en avoir plus besoin que moi au final."),
    PNJ5_2_AFTER("William (Quête du colis)\n\n    Une fois réparé, amène le colis à mon fils sur Kavi s'il te plait."),
    PNJ5_2_BUTTON("Reprendre le colis"),
    PNJ6_BEFORE("Ricardo (Objectif secondaire)\n\n    Salut étranger ! Connais-tu la devise la Miage ? \"La Miage c'est le ...\""),
    PNJ6_AFTER("Ricardo (Objectif secondaire)\n\n    Je n'ai plus rien à te dire maintenant. Pars étranger !"),
    PNJ6_ERROR("Ricardo (Objectif secondaire)\n\n    Ah toi, on voit que tu n'es pas un bon miagiste !"),
    PNJ6_SUCCESS("Ricardo (Objectif secondaire)\n\n    C'est ça ! La Miage c'est le Partage !"),
    PNJ6_BUTTON1("MAAAAAL !"),
    PNJ6_BUTTON2("partage !"),
    PNJ6_BUTTON3("BIIIIEN !"),
    PNJ7("Mia (Quête du coffre)\n\n    Je me suis perdue dans cet immense labyrinthe et je crois que je ne pourrais jamais trouver la sortie...\n" +
            "    Si jamais tu y arrives étranger, saches que j'ai enterré un coffre au trésor derrière la maison d'un ermite sur Jansen.\n" +
            "    Peut être trouveras-tu ton bonheur à l'intérieur"),
    CHEST_BEFORE_HIDDEN("(Quête du coffre)\n\n    La terre semble molle à cet endroit. Il est préférable de ne pas marcher ici."),
    CHEST_HIDDEN("(Quête du coffre)\n\n    Avec plus d'attention, il semble bien que la terre a été retournée à cet endroit..."),
    CHEST_HIDDEN_BUTTON("Creuser"),
    CHEST_CLOSED("(Quête du coffre)\n\n    Il y avait bien un coffre là dessous !"),
    CHEST_CLOSED_BUTTON("Ouvrir le coffre"),
    CHEST_OPENED("(Quête du coffre)\n\n    Vous trouvez 2000 pièces."),
    CHEST_AFTER_OPENED("(Quête du coffre)\n\n    Le coffre est désormais aussi vide que votre avenir."),
    SPACESHIP_BEFORE("(Objectif principal)\n\n    C'est un vaisseau écrasé ! Il y a même une bourse pleine à l'intérieur ! Mais ces réacteurs sont en piteux état... "),
    SPACESHIP_BUTTON("Prendre les réacteurs endommagés"),
    SPACESHIP_AFTER("Leskak (Objectif principal)\n\n    Peut être qu'en allant au centre commercial, je trouverais un moyen de réparer ces réacteurs..."),
    PNJ8("Albus Dumbledore\n\n    Ma baguette magique a été faite avec du CRAIN DE LICORNE, Monsieur!"),
    PNJ9("Tom Jedusor\n\n    Ma baguette magique a été faite avec un VENTRICULE DE DRAGON, Monsieur !"),
    PNJ10("Vendeur\n\n    Bienvenue au point de vente intergalactique ! Nous réparons et vendons de nombreux articles. Faites-vous plaisir !"),
    PNJ10_PACKAGE_BEFORE("Vendeur (Quête du colis)\n\n    Oh merci ! Tu nous ramènes un colis parmi ceux qui étaient endommagés"),
    PNJ10_PACKAGE_AFTER("Vendeur (Quête du colis)\n\n    Ca y est ton colis est tout neuf !  Tu peux le remettre à son propriétaire, sur la planète Kavi si je ne dis pas de bêtise !"),
    PNJ10_PACKAGE_BUTTON("Faire réparer le colis"),
    PNJ10_BUTTON1("Réparer les réacteurs (500€)"),
    PNJ10_BUTTON2("Tenue blanche (5.000€)"),
    PNJ10_BUTTON3("Dynamite (10.000€)"),
    PNJ10_BUTTON4("Dynamite"),
    PNJ10_NOT_ENOUGH_MONEY("Vendeur\n\n    Il semblerait que vous n'ayez pas assez d'argent pour cet article."),
    PNJ10_REACTORS_REPARED("Vendeur (Objectif principal)\n\n    Et voilà, terminé en deux coups de cuiller à pot ! Vos réacteurs sont réparés!\n" +
            "    Quoi ?! Tu cherches à aller sur Longstar ? Vas voir M. Stark près des bureaux, il possède un carnet avec les coordonnées de nombreuses planètes."),
    PNJ10_SKIN_BUYED("Vendeur (Objectif secondaire)\n\n    Voilà une nouvelle tenue pour ta collection ! Appuie sur " + Key.CHANGE_SKIN.getKeyCode().getName() + " pour en changer !"),
    PNJ10_QUEST_BEFORE("Vendeur (Objectif principal)\n\n    J'aimerais bien voir un panda... Il y en a un qui vit sur Kavi à ce qu'il parait."),
    PNJ10_QUEST_BETWEEN("Vendeur (Objectif principal)\n\n    Oh ! Tu peux te transofrmer en panda ?! Change d'apparence avec la touche " + Key.CHANGE_SKIN.getKeyCode().getName() + " et reviens me parler s'il te plait !" ),
    PNJ10_QUEST_AFTER("Vendeur (Objectif principal)\n\n    Un panda ?! Comme dans mes rêves les plus fous ! Ah oui, tu voulais de la dynamite ! Tiens, prends la !"),
    PNJ11_BEFORE("Rokhan (Objectif secondaire)\n\n    Salut toi !  Je viens du planète lointaine du nom de Kavi. Mais je ne peux désormais plus y retourner à cause de mes problèmes de dos...\n" +
            "    Pourrais-tu m'aider à retrouver une bague que j'ai perdu là bas ? Elle est très préciseuse et vaut très cher. Je te donnerais un petit quelque chose en échange."),
    PNJ11_AFTER("Rokhan (Objectif secondaire)\n\n    Ohhh ! Tu as retrouvé ma bague ! Merci beaucoup ! Comme convenu, je te donne un peu d'argent pour t'aider dans ta quête !"),
    PNJ11_BUTTON("Donner la bague"),
    PNJ12("Tony Stark (Objectif principal)\n\n    Les coordonnées de la planète Longstar ?! Répare déjà tes réacteurs avant que je t'en dise plus."),
    PNJ12_2("Tony Stark (Objectif principal)\n\n    Ooooh des réacteurs neufs ! Et de chez Stark Industries en plus ! Tu pourrais aller sur Longstar avec ça ! Elle se situe en Latitude:43.51715 et Longitude: 5.456508 dans le secteur NeufTroisQuart !"),
    STATUE_BEFORE("Statue (Objectif secondaire)\n\n    Qui est le créateur du Java ? Petit indice : il s'agit du plus beau des Gosling !"),
    STATUE_AFTER("Statue (Objectif secondaire)\n\n    Pourquoi parles-tu à une statue ? T'es bête ou quoi ?"),
    STATUE_ERROR("Statue (Objectif secondaire)\n\n    Je t'ai dit le plus beau des Gosling ! Tu le fais exprès ou quoi ?!"),
    STATUE_SUCCESS("Statue (Objectif secondaire)\n\n    Eh oui, oui, oui, oui ! C'est gagnéééé !"),
    STATUE_BUTTON1("Ryan Gosling"),
    STATUE_BUTTON2("James Gosling"),
    STATUE_BUTTON3("Dennis Ritchie"),
    PNJ13("Hulk\n\n    Hulk pas content !  Hulk vouloir tout casser !"),
    PNJ14_BEFORE("Steve Rogers\n\n    Salut c'est Captain ! Ici nous sommes sur Longstar, où il régne toujours un temps très automnal, à l'inverse de la planète Kavi où habite mon frère ! Il fait un froid de canard là bas."),
    PNJ14_AFTER("Steve Rogers (Quête du colis)\n\n    *Ouvre le colis* Beurk ! J'aime pas du tout la couleur de ce costume ! Tu pourrais aller me chercher quelques plumes de poulets ? Histoire que j'améliore tout ça."),
    PNJ14_2_BEFORE("Steve Rogers (Quête du colis)\n\n    Ramène moi quelques plumes de poulet, s'il te plait ! Je n'aime vraiment pas la couleur du costume !"),
    PNJ14_2_BUTTON("Rendre les 3 plumes"),
    PNJ14_2_AFTER("Steve Rogers (Quête du colis)\n\n    Ah parfait ! Je vais pouvoir changer la couleur de ce costume, attends juste deux petites secondes ...\n\n" +
            "*fait deux, trois arrangements*\n\n" +
            "    Voilà c'est terminé ! Par contre j'ai remarqué un petit détail... Il y a écrit Leskak sur l'étiquette. Je crois qu'il s'agit de ton colis depuis le début mon cher... du coup je te la donne ! " +
            "Appuie sur " + Key.CHANGE_SKIN.getKeyCode().getName() + " pour changer de tenue."),
    PNJ15_BEFORE("Tony Stark\n\n    J'ai hâte de recevoir mon colis plein de produits super high-tech !"),
    PNJ15_AFTER("Tony Stark (Quête du colis)\n\n    Moi qui étais si content d'avoir enfin reçu... et il m'a explosé dans les mains ! Nom d'un Stark ! J'espère que ça ne va pas arriver à quelqu'un d'autres..."),
    PNJ16("Thor\n\n    BRING ME THANOOOOOOOOOOOS !"),
    PNJ17_BEFORE("Ethan, fils de William\n\n    ICIIII, C'EST KAVIIIII ! Ah bah non, on a encore perdu en Ligue des Champions..."),
    PNJ17_AFTER("Ethan, fils de William (Quête du colis)\n\n    C'est quoi ce colis ? C'est mon père qui t'envoie ? Mais j'en veux pas de ton truc là ! Amène le à mon frangin plutôt, il habite sur Longstar."),
    CHICKEN("Poulet\n\n    Côôôt ! Côt ! Côt !"),
    CHICKEN_BEFORE("Poulet (Quête du colis)\n\n    Voulez-vous arracher une plume à ce pauvre poulet ?"),
    CHICKEN_AFTER("Leskak\n\n    Ca a eu l'air plutôt douloureux. LE PAAAUVRE !"),
    CHICKEN_BUTTON("Arracher une plume"),
    PNJ18("Yaël (Objectif principal)\n\n Un avalanche a déposé ces cailloux sur ce pont, bloquant ainsi le passage menant à la grotte... Peut-être que de la dynamite, venant du centre commercial, pourrait arranger ça ?"),
    PNJ19_BEFORE("Kévin (Objectif principal)\n\n    Tu connais le jeu World of Warcraft ? C'est gééénial ! Mais quelle est la meilleure zone de ce jeu ?\n" +
            "    Petit incide : je suis un panda !"),
    PNJ19_AFTER("Kévin\n\n    En tant que panda, je trouve que la Pandarie est une zone ma-gni-fique !"),
    PNJ19_ERROR("Kévin (Objectif principal)\n\n    Hummm ! C'est pas totalement faux, mais je pense qu'il y en a une encore mieux !"),
    PNJ19_SUCCESS("Kévin (Objectif principal)\n\n    Féliciations ! On voit que tu es un ami des pandas toi ! Voilà une nouvelle tenue pour ta collection ! Appuie sur " + Key.CHANGE_SKIN.getKeyCode().getName() + " pour en changer !"),
    PNJ19_BUTTON1("Outreterre"),
    PNJ19_BUTTON2("Pandarie"),
    PNJ19_BUTTON3("Norfendre"),
    PNJ20_BEFORE("Polia (Objectif principal)\n\n    Qui va là ?! Oh mon dieu, mais qui es-tu ? Comment m'as-tu trouvé ? Ne révèle à personne ma position s'il te plait ! Je t'offre mon rayon laser en échange de ton silence !"),
    PNJ20_AFTER("Polia\n\n    Pars loin je t'ai dit, je ne veux plus jamais te voir !"),
    PNJ20_BUTTON("Prendre le rayon laser"),
    PACMAN_WON("Leskak (Objectif principal)\n\n    Holàlà c'était quoi cet endroit ! J'était devenu une boule toute jaune ! Bon , je m'en suis sorti, c'est le principal ! Et j'ai même réussi à arracher le panneau de commande que je recherchais de ce foutu jeu !\n" +
            "    En le bidouillant un peu, je pourrais avoir accès aux coordonnées du rayon laser le plus proche... Et il semblerait que ce soit sur la planète Kavi !\n" +
            "    Il semblerait que j'ai gagné une nouvelle tenue aussi... Appuie sur " + Key.CHANGE_SKIN.getKeyCode().getName() + " pour changer de tenue."),
    PACMAN_LOSE("Leskak (Objectif principal)\n\n    C'est plus compliqué que prévu ! Peut être que je pourrais passer ça..."),
    PACMAN_BUTTON("Passer le Pacman ?"),
    ROCK_BUTTON("Faire tout péter !"),
    DEATH_STAR("Assembler les 4 éléments"),
    BUSH2_BEFORE("(Objectif secondaire)\n\n    Il y a une bague dans ces hautes herbes. Souhaitez-vous la prendre ?"),
    BUSH2_AFTER(""),
    BUSH2_BUTTON("Prendre la bague"),
    ;

    private String text;

    DialogConfig(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
