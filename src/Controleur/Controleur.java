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

/**
 *
 * @author jerem
 */
public class Controleur {
    
    private final Panel p;
    private final Frame f;
    private final Modele m;
    private final auto aut;
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
        //Retire toute les cellules du tableau
        for(int i =0; i< largeur ; i++){
            for(int j = 0; j <hauteur; j++){
                tab[i][j] = false;
            }
        }
        p.setTab(this.tab);
    }
    
    public synchronized void  playpause(){
        //Si le thread n'a pas ete lance, le demare
        if(this.aut.getState() == Thread.State.NEW){
            this.aut.start();
        }else{
            //Met le thread en pause, si il y est deja, le relance
            if(this.aut.getState() != Thread.State.WAITING){
                this.aut.arret();
                System.out.println("pause");
            } else {
                this.aut.play();
                
                System.out.println("replay");
            }
        }
        
    }
}

