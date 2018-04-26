/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.awt.Point;

/**
 * Modele est la classe qui gere les calculs sur le jeu
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>Solitude est le nombre de cellules voisine en dessous du quel la cellule deperit</li>
 * <li>asphyxie est le nombre de cellules voisine au dessus du quel la celule deperit</li>
 * <li>minVie est le nombre de cellule voisine necessaire à l'apparition d'une nouvelle celule</li>
 * <li>maxVie est le nombre de cellule voisine necessaire à l'apparition d'une nouvelle celule</li>
 * </ul>
 * Cette classe calcul les changement du tableau dans le jeu de la vie
 * @author jérémy rousseau
 * @version 1.0
 */
public class Modele {
    private int solitude;
    private int asphyxie;
    private int minVie;
    private int maxVie;

    private int largeur;
    private int hauteur;
    private boolean tab[][];
    /**Definit les 8 directions possibles pour une cellule */
    private static final int direction[][] = {{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0}};
    public Modele(){
        
    }    
     /**
     * <b>Fonction jeu </b>
     * Calcul la possition du plateau à l'intant t+1
     * /!\ ne modifie pas le tableau du modele
     * 
     * @return Renvoie le tableau dans l'etat suivant à l'etat actuel
     */
    public boolean[][] nextMove(){
        boolean local_tab[][] = new boolean[this.tab.length][this.tab[0].length];
        
        for(int i = 0; i< this.largeur; i++){
            for(int j = 0; j < this.hauteur; j++){
                int nbVoisins = 0;
                
                for(int n = 0; n<8;n++){
                    int x = i + direction[n][0];
                    int y = j + direction[n][1];
                   
                    if(is_inPanel(new Point(x, y))) 
                        nbVoisins += tab[x][y]?1:0;
                }
                
                if(nbVoisins <=solitude || nbVoisins >= asphyxie){
                    local_tab[i][j] = false;
                } else {
                    if(nbVoisins >= this.minVie && nbVoisins <= this.maxVie){
                        local_tab[i][j] = true;
                    } else {
                        local_tab[i][j] = this.tab[i][j];
                    }
                }
            }
        }
        
        return local_tab;
    }
    
    private boolean is_inPanel(Point p){
        return ! (p.x<0 || p.x >= this.largeur || p.y < 0 || p.y >= this.hauteur);
    }
    
    public boolean[][] getTab() {
        return tab;
    }
    
       //Fonction qui redefini la taille du tableau sans perte de donnée
    //Les vérifications seront effectuer dans le controleur
    public boolean[][] resize(int larg, int haut){
        int largeur_local = larg<this.largeur?larg:this.largeur;
        int hauteur_local= haut<this.hauteur?haut:this.hauteur;
        boolean tab_local[][] = new boolean[larg][haut];
        
        for(int i = 0;i <larg;i++){
            for(int j =0; j< haut; j++){
                //Si l'on ai dans la limite du plus petit tableau, on copie, sinon on initialise a false
                if(i< largeur_local && j< hauteur_local){
                  
                    tab_local[i][j] = this.tab[i][j]; //On copie le contenu de l'ancien tableau dans le nouveau
                }else{
                    tab_local[i][j] = false;
                }
                
            }
        }
        this.setTab(tab_local);
        return this.tab;
    }

    public void setTab(boolean[][] tab) {
        this.tab = tab;
        this.largeur = tab.length;
        this.hauteur = tab[0].length;
    }
    
    public int getSolitude() {
        return solitude;
    }

    public void setSolitude(int solitude) {
        this.solitude = solitude;
    }

    public int getAsphyxie() {
        return asphyxie;
    }

    public void setAsphyxie(int asphyxie) {
        this.asphyxie = asphyxie;
    }

    public int getMinVie() {
        return minVie;
    }

    public void setMinVie(int minVie) {
        this.minVie = minVie;
    }

    public int getMaxVie() {
        return maxVie;
    }

    public void setMaxVie(int maxVie) {
        this.maxVie = maxVie;
    }
    
 
}
