package app;

import controller.GameController;

/**
 * Point d'entrée principal du jeu
 **/

public class Main {
    public static void main(String[] args) {
        int tailleGrille = 5; // Taille par défaut
        
        // On vérifier si une taille est spécifiée en argument
        if (args.length > 0) {
            try {
                tailleGrille = Integer.parseInt(args[0]);
                if (tailleGrille < 3 || tailleGrille > 10) {
                    System.out.println("La taille doit être entre 3 et 10. Utilisation de la taille par défaut (5)");
                    tailleGrille = 5;
                }
            } catch (NumberFormatException e) {
                System.out.println("Argument invalide. Utilisation de la taille par défaut (5)");
            }
        }
        
        GameController game = new GameController(tailleGrille);
        game.start();
    }
}
