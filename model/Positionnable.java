package model;

/**
 * Interface pour les objets qui peuvent être positionnés sur la grille
 */

public interface Positionnable {
    int getX();
    int getY();
    void setPosition(int x, int y);
    void initialisePosition();
}
