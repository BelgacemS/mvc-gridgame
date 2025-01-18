package model;

/**
 * Énumération des différents types de joyaux et leur valeur
 */

public enum TypeJoyau {
    RUBIS(100),
    DIAMANT(200),
    OPALE(150);
    
    private final int valeur;
    
    TypeJoyau(int valeur) {
        this.valeur = valeur;
    }
    
    public int getValeur() {
        return this.valeur;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
}
