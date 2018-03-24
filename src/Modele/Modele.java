/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.awt.Point;

/**
 *
 * @author jerem
 */
public class Modele {
    private int solitude;
    private int asphyxie;
    private int minVie;
    private int maxVie;

    private int largeur;
    private int hauteur;
    private boolean tab[][];
    
    private static final int direction[][] = {{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0}};
    public Modele(){
        
    }    
    
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
        this.tab = local_tab;
        return this.tab;
    }
    
    private boolean is_inPanel(Point p){
        return ! (p.x<0 || p.x >= this.largeur || p.y < 0 || p.y >= this.hauteur);
    }
    
    public boolean[][] getTab() {
        return tab;
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
