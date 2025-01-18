package exceptions;

/**
 * Classe de base pour toutes les exceptions du jeu
 */

public class JeuException extends Exception {
    public JeuException(String message) {
        super(message);
    }

    public JeuException(String message, Throwable cause) {
        super(message, cause);
    }
}
