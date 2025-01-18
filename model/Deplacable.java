package model;

import exceptions.*;
import game.Grille;

/**
 * Interface pour les objets qui peuvent se déplacer sur la grille
 */
public interface Deplacable {
    /**
     * Déplace l'entité vers une nouvelle position
     * @param x Nouvelle coordonnée x
     * @param y Nouvelle coordonnée y
     * @param grille La grille sur laquelle se déplacer
     */
    void seDeplacer(int x, int y, Grille grille) throws DeplacementIncorrecteException, 
                                                       CoordonneesIncorrectesException, 
                                                       CaseNonPleineException;
}
