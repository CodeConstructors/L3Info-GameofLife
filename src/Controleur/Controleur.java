/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;

import Modele.Modele;
import Vue.Frame;
import Vue.Panel;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerem
 */
public class Controleur {
    
    private Panel p;
    private Frame f;
    private Modele m;
    private auto aut;
    private boolean tab [][];
    private int largeur = 70;
    private int hauteur= 70;
    
    public Controleur(){
        this.m = new Modele();
        this.aut= new auto(this);
        
        System.out.println(this.aut.getState());
        
        tab = new boolean[largeur][hauteur];
        for(int i =0; i< largeur ; i++){
            for(int j = 0; j <hauteur; j++){
                tab[i][j] = false;
            }
        }
        
        this.m.setTab(this.tab);
        this.m.setAsphyxie(4);
        this.m.setMaxVie(3);
        this.m.setMinVie(3);
        this.m.setSolitude(1);
        
        this.p = new Panel(this.tab);
        this.f = new Frame();
        
        //Etablit les liens
        this.f.setLienControlleur(this);
        p.setLienControleur(this);
        
        f.add(p,BorderLayout.CENTER);
        f.Start();
        
        f.pack();
        f.setSize(500, 500);
        f.setVisible(true);
    }
    
    public void catchClick(Point p){
       if(p.x < this.largeur && p.y < this.hauteur){
            this.tab[p.x][p.y] = !this.tab[p.x][p.y];
            this.p.setTab(this.tab);
       }
    }
    
    public void nextMove(){
        this.tab = m.nextMove();
        p.setTab(this.tab);
        
    }
    
    public void clear(){
        for(int i =0; i< largeur ; i++){
            for(int j = 0; j <hauteur; j++){
                tab[i][j] = false;
            }
        }
        p.setTab(this.tab);
    }
    
    public void playpause(){
        if(this.aut.getState() != Thread.State.TERMINATED){
            this.aut.arret();
        } else {
            this.aut = new auto(this);
            this.aut.start();
        }
    }
}

class auto extends Thread {
    private Controleur controleur;
    private boolean go = true;
    public void run(){
        go = true;
        while(go){
            try {
                this.controleur.nextMove();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    public auto(Controleur c){
         this.controleur = c;
         this.setDaemon(true);
    }
    
    public void arret(){
        go = false;
    }
}
