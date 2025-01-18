package model;

/**
 * Classe représentant un joyau dans le jeu
 */

public class Joyaux extends Contenu {
    private final TypeJoyau typeJoyau;

    public Joyaux(String type) {
        super(type, TypeJoyau.valueOf(type.toUpperCase()).getValeur());
        this.typeJoyau = TypeJoyau.valueOf(type.toUpperCase());
    }

    public Joyaux clone() {
        return new Joyaux(super.type);
    }

    public String getNom() {
        return typeJoyau.name();
    }

    public int getValeur() {
        return getQuantite();
    }

    @Override
    public String toString() {
        return "(*) " + type + " - " + getQuantite() + " pièces";
    }

    @Override
    public void materiel() {
        System.out.println("Je suis un " + typeJoyau + " de valeur " + typeJoyau.getValeur());
    }
}
