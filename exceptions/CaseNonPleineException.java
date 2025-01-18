package exceptions;

/**
 * Exception levée lorsqu'une case est vide alors qu'elle devrait contenir quelque chose
 */

public class CaseNonPleineException extends JeuException {
    public CaseNonPleineException(int x, int y) {
        super(String.format("La case (%d,%d) est vide", x, y));
    }
}