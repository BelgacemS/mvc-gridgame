package model;

import java.io.Serializable;

/**
 * Classe abstraite représentant un contenu dans une case de la grille.
 * Cette classe sert de base pour tous les éléments comme les gardiens et les joyaux.
 */

public abstract class Contenu implements Positionnable, Serializable {
    private static final long serialVersionUID = 1L;
    private static int nbContenusCrees = 0;
    
    public final int ident;
    public final String type;
    private int quantite;
    private int x;
    private int y;

    public Contenu(String type, int quantite) {
        this.type = type;
        this.quantite = quantite;
        this.ident = nbContenusCrees++;
        initialisePosition();
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void initialisePosition() {
        this.x = -1;
        this.y = -1;
    }

    @Override
    public String toString() {
        if (this instanceof Joyaux) {
            return "(*) " + this.type + " - Valeur: " + this.quantite + " pièces";
        }
        return this.type + " - Force: " + this.quantite;
    }

    public abstract void materiel();
}