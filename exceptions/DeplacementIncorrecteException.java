package exceptions;

/**
 * Exception levée lors d'un déplacement incorrect
 */

public class DeplacementIncorrecteException extends JeuException {
    public DeplacementIncorrecteException(int x, int y, String raison) {
        super(String.format("Déplacement impossible vers (%d,%d) : %s", x, y, raison));
    }

    public DeplacementIncorrecteException(String message) {
        super(message);
    }
}