package config;

public enum DialogConfig {
    PNJ1("Coucou ça va ? je sais plus ce que je dois dire en vrai lol."),
    PNJ2("Tu chechers un plaque de tôle ? Va voir le trouduc a droite j'crois qu'il sais où tu peux en avoir."),
    PNJ3_BEFORE("Wep je sais où trouver une plaque de tole. Mais si tu veux que je te le dises, tu vas devoir me su... euh me trouver" +
            "un bidule que j'ai pommé dans un buisson là haut là bas. Allez tchuss. "),
    PNJ3_AFTER("Ok Ci-mer bro va voir l'autre teubé la bas crois il a ce que tu veux mdr. "),
    PNJ3_BUTTON("Donner son bidule"),
    PNJ4_BEFORE("Vlà ta plaque de tôle pti fragile. Maintenant laisses moi tranquille j'ai autre chose à foutre."),
    PNJ4_AFTER("Jte l'ai filé ta putain de plaque. Casse toi de là."),
    PNJ4_BUTTON("Prendre la plaque de tôle"),
    BUSH1_BEFORE("Vous trouvez un bidule."),
    BUSH1_AFTER("Vous avez déjà pris le bidule."),
    BUSH1_BUTTON("Prendre le bidule"),
    ;

    private String text;

    DialogConfig(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
