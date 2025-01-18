package controller;

import game.Simulation;
import model.Agent8;
import view.GameView;
import exceptions.*;
import java.util.Scanner;

/**
 * Contrôleur principal du jeu, gérant les interactions utilisateur
 **/

public class GameController {
    private final Simulation simulation;
    private final GameView view;
    private final Scanner scanner;
    private boolean running;

    public GameController(int taille) {
        this.simulation = new Simulation(taille);
        this.view = new GameView();
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void start() {
        view.afficherMessage("Bienvenue dans le jeu de l'Agent 8!");
        while (running) {
            view.afficherEtatJeu(simulation.getTerrain(), Agent8.getInstance());
            handleUserInput();
        }
        scanner.close();
    }

    private void handleUserInput() {
        view.afficherMessage("\nActions disponibles:");
        view.afficherMessage("1. Déplacer l'agent :");
        view.afficherMessage("   - Z : Haut");
        view.afficherMessage("   - S : Bas");
        view.afficherMessage("   - Q : Gauche");
        view.afficherMessage("   - D : Droite");
        view.afficherMessage("2. Passer un tour");
        view.afficherMessage("3. Quitter");
        view.afficherMessage("\nVotre choix: ");

        String input = scanner.nextLine().toUpperCase();
        try {
            switch (input) {
                case "Z" -> deplacerAgent("HAUT");
                case "S" -> deplacerAgent("BAS");
                case "Q" -> deplacerAgent("GAUCHE");
                case "D" -> deplacerAgent("DROITE");
                case "2" -> simulation.tourSuivant();
                case "3" -> {
                    running = false;
                    view.afficherMessage("Merci d'avoir joué!");
                }
                default -> view.afficherErreur("Commande non reconnue");
            }
        } catch (Exception e) {
            view.afficherErreur(e.getMessage());
        }
    }

    private void deplacerAgent(String direction) {
        Agent8 agent = Agent8.getInstance();
        int newX = agent.getX();
        int newY = agent.getY();

        switch (direction) {
            case "HAUT" -> newY--;
            case "BAS" -> newY++;
            case "GAUCHE" -> newX--;
            case "DROITE" -> newX++;
        }

        try {
            agent.seDeplacer(newX, newY, simulation.getTerrain());
            simulation.tourSuivant();
        } catch (DeplacementIncorrecteException e) {
            view.afficherErreur(e.getMessage());
        } catch (CoordonneesIncorrectesException e) {
            view.afficherErreur(e.getMessage());
        }
    }
}
