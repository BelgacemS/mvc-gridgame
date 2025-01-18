package model;

/**
 * Classe représentant un gardien dans le jeu
 */

public class Gardien extends Contenu {
    public Gardien(String nom) {
        super(nom, (int)(Math.random() * 201));
    }

    @Override
    public String toString() {
        return super.toString() + " point de vie= " + super.getQuantite();
    }

    @Override
    public void materiel() {
        System.out.println("Je suis un Gardien");
    }
}