package model;

import game.Grille;
import exceptions.*;
import java.util.Random;

/**
 * Classe représentant un gardien qui peut se téléporter
 */

public class GardienTeleporte extends Gardien implements Teleportable {
    private final Grille grille;

    public GardienTeleporte(String nom, Grille g) {
        super(nom);
        this.grille = g;
    }

    @Override
    public String toString() {
        return super.toString() + " teleportable ";
    }

    @Override
    public void seTeleporter() {
        int aleax = (int)(Math.random() * grille.nbLignes);
        int aleay = (int)(Math.random() * grille.nbColonnes);
        
        System.out.println("Le gardien id:" + this.ident + 
                         " avant la teleportation en position :(" + 
                         this.getX() + ", " + this.getY() + ")");
        
        if (!grille.sontValides(aleax, aleay)) {
            System.out.println("Deplacement impossible ligne " + aleax + 
                             "n'existe pas, Deplacement impossible colonne " + 
                             aleay + " n'existe pas");
            return;
        }
        
        try {
            if (grille.getCase(aleax, aleay) instanceof Contenu) {
                System.out.println("Case occupée");
                return;
            }
        } catch (CoordonneesIncorrectesException | CaseNonPleineException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        try {
            System.out.println("Le gardien id:" + this.ident + 
                             "se teleporte dans (" + aleax + ", " + aleay + ")");
            this.setPosition(aleax, aleay);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}