package game;

import exceptions.*;
import model.Contenu;
import java.util.ArrayList;

/**
 * Classe représentant la grille de jeu.
 * Elle contient les éléments du jeu et gère leur disposition et interactions.
 */

public final class Grille {
   public static final int NBLIGNESMAX = 20;
   public static final int NBCOLONNESMAX = 20;
   public final int nbLignes;
   public final int nbColonnes;
   private Contenu[][] grille;

   public Grille() {
      this(20, 20);
   }

   public Grille(int var1, int var2) {
      if (var1 > 20) {
         this.nbLignes = 20;
      } else if (var1 <= 0) {
         this.nbLignes = 1;
      } else {
         this.nbLignes = var1;
      }

      if (var2 > 20) {
         this.nbColonnes = 20;
      } else if (var2 <= 0) {
         this.nbColonnes = 1;
      } else {
         this.nbColonnes = var2;
      } 

      this.grille = new Contenu[this.nbLignes][this.nbColonnes];
   }

   public void setCase(int i, int j, Contenu c) throws CoordonneesIncorrectesException {
      verifierCoordonnees(i, j);
      if (this.grille[i][j] != null) {
         this.grille[i][j].initialisePosition();
      }
      this.grille[i][j] = c;
      if (c != null) {
         c.setPosition(i, j);
      }
   }

   public Contenu getCase(int i, int j) throws CoordonneesIncorrectesException, CaseNonPleineException {
      verifierCoordonnees(i, j);
      if (this.grille[i][j] == null) {
         throw new CaseNonPleineException(i, j);
      }
      return this.grille[i][j];
   }

   public void videCase(int i, int j) throws CoordonneesIncorrectesException {
      verifierCoordonnees(i, j);
      if (this.grille[i][j] != null) {
         this.grille[i][j].initialisePosition();
         this.grille[i][j] = null;
      }
   }

   public boolean caseEstVide(int var1, int var2) throws CoordonneesIncorrectesException {
      verifierCoordonnees(var1, var2);
      return this.grille[var1][var2] == null;
   }

   public ArrayList<Contenu> lesContenus() {
      ArrayList<Contenu> contenus = new ArrayList<>();

      for(int i = 0; i < this.nbLignes; ++i) {
         for(int j = 0; j < this.nbColonnes; ++j) {
            if (this.grille[i][j] != null) {
               contenus.add(this.grille[i][j]);
            }
         }
      }

      return contenus;
   }

   public boolean sontValides(int var1, int var2) {
      return var1 >= 0 && var1 < this.nbLignes && var2 >= 0 && var2 < this.nbColonnes;
   }

   public void affiche(int var1) {
      String var2 = "";
      String var3 = ":";
      String var4 = "";
      int var5 = Math.max(var1, 1);

      int var6;
      for(var6 = 0; var6 < var5; ++var6) {
         var4 = var4 + "-";
      }

      for(var6 = 0; var6 < this.nbColonnes; ++var6) {
         var3 = var3 + var4 + ":";
      }

      var3 = var3 + "\n";
      var2 = var3;

      for(var6 = 0; var6 < this.nbLignes; ++var6) {
         for(int var7 = 0; var7 < this.nbColonnes; ++var7) {
            if (this.grille[var6][var7] == null) {
               var2 = var2 + "|" + String.format("%-" + var5 + "s", " ");
            } else {
               var2 = var2 + "|" + this.premiersCar(this.grille[var6][var7].type, var5);
            }
         }

         var2 = var2 + "|\n" + var3;
      }

      System.out.println(var2);
   }

   public String toString() {
      int var1 = 0;

      for(int var2 = 0; var2 < this.nbLignes; ++var2) {
         for(int var3 = 0; var3 < this.nbColonnes; ++var3) {
            if (this.grille[var2][var3] != null) {
               ++var1;
            }
         }
      }

      String var4 = "Grille de " + this.nbLignes + "x" + this.nbColonnes + " cases: ";
      if (var1 == 0) {
         var4 = var4 + "toutes les cases sont libres.";
      } else if (var1 == 1) {
         var4 = var4 + "il y a une case occupée.";
      } else {
         var4 = var4 + "il y a " + var1 + " cases occupées.";
      }

      return var4;
   }

   public int getNbLignes() {
      return this.nbLignes;
   }

   public int getNbColonnes() {
      return this.nbColonnes;
   }

   private String premiersCar(String var1, int var2) {
      String var3 = String.format("%-" + var2 + "s", var1);
      return var3.substring(0, var2);
   }

   private void verifierCoordonnees(int x, int y) throws CoordonneesIncorrectesException {
      if (!sontValides(x, y)) {
         throw new CoordonneesIncorrectesException(x, y, nbLignes - 1, nbColonnes - 1);
      }
   }
}
