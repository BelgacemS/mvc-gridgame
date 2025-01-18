package exceptions;

/**
 * Exception levée lorsque les coordonnées sont incorrectes
 */

public class CoordonneesIncorrectesException extends JeuException {
    
    public CoordonneesIncorrectesException(String message) {
        super(message);
    }
    
    public CoordonneesIncorrectesException(int x, int y, int maxX, int maxY) {
        super("Coordonnées (" + x + "," + y + ") incorrectes ! Les coordonnées doivent être entre (0,0) et (" + maxX + "," + maxY + ")");
    }
}