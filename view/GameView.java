package view;

import game.Grille;
import model.Agent8;
import model.Contenu;
import model.Gardien;
import model.Joyaux;

/**
 * Classe gérant l'affichage du jeu
 */

public class GameView {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    
    public void afficherGrille(Grille grille) {
        System.out.println("\n=== État de la grille ===");
        Agent8 agent = Agent8.getInstance();
        for (int y = 0; y < grille.getNbLignes(); y++) {
            System.out.print("|");
            for (int x = 0; x < grille.getNbColonnes(); x++) {
                if (x == agent.getX() && y == agent.getY()) {
                    System.out.print(ANSI_BLUE + " A " + ANSI_RESET);
                } else {
                    try {
                        Contenu contenu = grille.getCase(y, x);
                        if (contenu instanceof Gardien) {
                            System.out.print(ANSI_RED + " G " + ANSI_RESET);
                        } else if (contenu instanceof Joyaux) {
                            System.out.print(ANSI_YELLOW + " J " + ANSI_RESET);
                        } else {
                            System.out.print(" ? ");
                        }
                    } catch (Exception e) {
                        System.out.print(" · ");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-".repeat((grille.getNbColonnes() * 4) + 1));
        }
    }

    public void afficherAgent(Agent8 agent) {
        System.out.println("\n=== État de l'Agent ===");
        System.out.println(ANSI_BLUE + "Position: (" + agent.getX() + "," + agent.getY() + ")" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Fortune: " + agent.fortune() + ANSI_RESET);
        
        System.out.println("\nContenu du sac:");
        if (agent.getSac().isEmpty()) {
            System.out.println("Le sac est vide");
        } else {
            for (Joyaux j : agent.getSac()) {
                System.out.println(ANSI_YELLOW + "(*) " + j.type + " - " + j.getQuantite() + " pièces" + ANSI_RESET);
            }
        }
        System.out.println();
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public void afficherErreur(String message) {
        System.out.println(ANSI_RED + "\nErreur: " + message + ANSI_RESET);
    }

    public void afficherEtatJeu(Grille grille, Agent8 agent) {
        afficherGrille(grille);
        afficherAgent(agent);
    }
}
