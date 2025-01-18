package game;

import model.*;
import exceptions.*;

/**
 * Classe principale gérant la simulation du jeu.
 * Cette classe coordonne les interactions entre l'agent, la grille et les différents contenus (gardiens et joyaux).
 * Elle gère également la logique de déplacement et les événements du jeu.
 **/

public class Simulation {
    // Constantes pour la configuration du jeu
    private static final int TAILLE_GRILLE = 5;
    private static final double PROBA_DEPLACEMENT = 0.3;
    private static final int FORCE_MIN = 10;
    private static final int FORCE_MAX = 100;
    private static final int CHANCE_GARDIEN = 1;
    private static final int CHANCE_RUBIS = 2;
    private static final int CHANCE_OPALE = 3;
    private static final int CHANCE_GARDIEN_TELEPORTE = 4;
    private static final int MAX_ALEATOIRE = 6;

    private final Agent8 agent;
    private final Grille terrain;
    private final Contenu[] contenu;

    public Simulation(int m) {
        this.agent = Agent8.getInstance();
        this.terrain = new Grille(TAILLE_GRILLE, TAILLE_GRILLE);
        // Place l'agent dans un coin de la grille au début (en haut à gauche)
        this.agent.setPosition(0, 0);
        this.contenu = new Contenu[m];
        initialiserContenu();
        placerContenuAleatoirement();
    }

    private void initialiserContenu() {
        for (int i = 0; i < contenu.length; i++) {
            contenu[i] = ContenuFactory.createContenuAleatoire(terrain);
        }
    }

    private void placerContenuAleatoirement() {
        for (Contenu c : contenu) {
            boolean place = false;
            while (!place) {
                try {
                    int aleax = (int)(Math.random() * this.terrain.getNbLignes());
                    int aleay = (int)(Math.random() * this.terrain.getNbColonnes());
                    terrain.setCase(aleax, aleay, c);
                    place = true;
                } catch(CoordonneesIncorrectesException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String toString(){
        String res="Information sur la grille:\t"+ terrain.toString()+"\t";
        for (Contenu c: contenu){
            res+=c.toString()+"\n";
        }
        return res+"\n";
    }

    public void lance(int nbetape) {
        for (int i = 0; i < nbetape; i++) {
            try {
                int aleax = (int)(Math.random() * this.terrain.getNbLignes());
                int aleay = (int)(Math.random() * this.terrain.getNbColonnes());
                if (Math.random() < PROBA_DEPLACEMENT) {
                    terrain.affiche(1);
                    System.out.println(this.toString());
                    System.out.println(agent.toString());
                    agent.seDeplacer(aleax, aleay, (int)(Math.random() * (FORCE_MAX - FORCE_MIN) + FORCE_MIN), terrain);
                    terrain.affiche(1);
                    System.out.println(this.toString());
                    System.out.println(agent.toString());
                } else {
                    terrain.affiche(1);
                    System.out.println(this.toString());
                    System.out.println(agent.toString());
                    agent.seDeplacer(aleax, aleay, terrain);
                    terrain.affiche(1);
                    System.out.println(this.toString());
                    System.out.println(agent.toString());
                }
            } catch(CoordonneesIncorrectesException | DeplacementIncorrecteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void tourSuivant() {
        for (Contenu c : contenu) {
            if (c instanceof GardienTeleporte) {
                ((GardienTeleporte) c).seTeleporter();
            }
        }
    }

    public Grille getTerrain() {
        return this.terrain;
    }
}
