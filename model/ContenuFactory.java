package model;

import game.Grille;
import java.util.Random;

/**
 * Factory pour la création des différents types de contenus du jeu.
 * Implémente le pattern Factory Method.
 */

public class ContenuFactory {
    
    public enum TypeContenu {
        GARDIEN,
        GARDIEN_TELEPORTE,
        RUBIS,
        OPALE,
        DIAMANT
    }

    public static Contenu createContenu(TypeContenu type, Grille grille) {
        return switch(type) {
            case GARDIEN -> new Gardien("Gardien");
            case GARDIEN_TELEPORTE -> new GardienTeleporte("Gardien Teleportable", grille);
            case RUBIS -> new Joyaux("RUBIS");
            case OPALE -> new Joyaux("OPALE");
            case DIAMANT -> new Joyaux("DIAMANT");
        };
    }

    public static Contenu createContenuAleatoire(Grille grille) {
        TypeContenu[] types = TypeContenu.values();
        int index = (int)(Math.random() * types.length);
        return createContenu(types[index], grille);
    }
}
