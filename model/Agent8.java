package model;

import exceptions.*;
import game.Grille;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe représentant l'agent principal du jeu.
 * Cette classe implémente le pattern Singleton pour assurer une instance unique de l'agent.
 * L'agent peut se déplacer sur une grille, collecter des joyaux et combattre des gardiens.
 */

public class Agent8 implements Deplacable, Positionnable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private int posX;
    private int posY;
    private ArrayList<Joyaux> sacJoyaux;
    
    private static volatile Agent8 instance;
    private static final Object mutex = new Object();

    private Agent8() {
        initialisePosition();
        this.sacJoyaux = new ArrayList<Joyaux>();
    }

    public static Agent8 getInstance() {
        Agent8 result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new Agent8();
                }
            }
        }
        return result;
    }

    @Override
    public int getX() {
        return this.posX;
    }

    @Override
    public int getY() {
        return this.posY;
    }

    @Override
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public void initialisePosition() {
        this.posX = 0;
        this.posY = 0;
    }
    
    public ArrayList<Joyaux> getSac() {
        return this.sacJoyaux;
    }
    
    public void seDeplacer(int xnew,int ynew,Grille g)throws DeplacementIncorrecteException,CoordonneesIncorrectesException {
        if(!g.sontValides(ynew,xnew))throw new DeplacementIncorrecteException("Deplacement impossible ligne "+ynew+ "n'existe pas,Deplacement impossible colonne "+xnew+" n'existe pas");
        
        try {
            // Si la case n'est pas vide, on vérifie le contenu
            if (!g.caseEstVide(ynew,xnew)) {
                try {
                    Contenu a = g.getCase(ynew,xnew);
                    if(a instanceof Joyaux){
                        sacJoyaux.add((Joyaux)a);
                        g.videCase(ynew,xnew);
                        System.out.println("l'Agent tombe sur un joyaux");
                    }
                    else if(a instanceof Gardien){
                        sacJoyaux.clear();
                        System.out.println("l'Agent tombe sur un gardien et perds les joyaux");
                    }
                } catch(CaseNonPleineException e) {

                }
            }
            
            this.posX=xnew;
            this.posY=ynew;
        } catch(CoordonneesIncorrectesException e) {
            throw e;
        }
    }
    
    public void seDeplacer(int xnew , int ynew , int f, Grille g)throws DeplacementIncorrecteException,CoordonneesIncorrectesException {
        if(!g.sontValides(ynew,xnew))throw new DeplacementIncorrecteException("Deplacement impossible ligne "+ynew+ "n'existe pas,Deplacement impossible colonne "+xnew+" n'existe pas");
        
        try {
            // Si la case n'est pas vide, on vérifie le contenu
            if (!g.caseEstVide(ynew,xnew)) {
                try {
                    Contenu a = g.getCase(ynew,xnew);
                    if(a instanceof Joyaux){
                        sacJoyaux.add((Joyaux)a);
                        g.videCase(ynew,xnew);
                        System.out.println("l'Agent tombe sur un joyaux");
                    }
                    else if(a instanceof Gardien){
                        if(a.getQuantite()<=f){
                            g.videCase(ynew,xnew);
                            System.out.println("L'agent de force: "+f +" se bat contre le gardient ayant "+a.getQuantite()+" point de vie");
                            System.out.println("l'Agent tue le gardien");
                        }
                        else if(a.getQuantite()>f){
                            sacJoyaux.clear();
                            System.out.println("le gardien avant le combat a "+a.getQuantite());
                            System.out.println("l'Agent tombe sur un gardien se bat et perds ses joyaux");
                            a.setQuantite(a.getQuantite()-f);
                            System.out.println("le gardien apres le combat a "+a.getQuantite());
                        }
                    }
                } catch(CaseNonPleineException e) {

                }
            }
            
            this.posX=xnew;
            this.posY=ynew;
        } catch(CoordonneesIncorrectesException e) {
            throw e;
        }
    }
    public int fortune(){
        int sum=0;
        for(Joyaux j :sacJoyaux){
            sum+=j.getQuantite();
        }
        return sum;
    }
    public String contenuSac() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nContenu du sac:\n");
        
        if (sacJoyaux.isEmpty()) {
            sb.append("Le sac est vide\n");
        } else {
            for (Joyaux j : sacJoyaux) {
                sb.append("(*) ").append(j.getNom()).append(" - ").append(j.getValeur()).append(" pièces\n");
            }
        }
        
        return sb.toString();
    }
    public String toString(){
        return "L'agent posx= "+this.posX+"; posY= "+this.posY+" fortune="+this.fortune();
    }
}