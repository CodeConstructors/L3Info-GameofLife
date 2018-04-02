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
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author jeremy
 */
public class Controleur {
    
    private final Panel panel_principal;
    private final Panel panel_tampon;
    private final Frame f;
    private final Modele m;
    private final auto aut;
    private boolean tab [][];
    private boolean tab_mini [][];
    private int largeur = 20;
    private int hauteur= 20;
    private boolean actif;
    
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
        
        tab_mini = new boolean[10][10];
        for(int i =0; i< 10 ; i++){
            for(int j = 0; j <10; j++){
                tab_mini[i][j] = false;
            }
        }
        
        this.m.setTab(this.tab);
        this.m.setAsphyxie(4);
        this.m.setMaxVie(3);
        this.m.setMinVie(3);
        this.m.setSolitude(1);
        
        this.panel_principal = new Panel(this.tab);
        this.panel_tampon = new Panel(this.tab_mini);
        this.f = new Frame();
        
        //Etablit les liens
        this.f.setLienControlleur(this);
        this.panel_principal.setLienControleur(this);
        this.panel_tampon.setLienControleur(this);
        this.panel_tampon.setPreferredSize(new Dimension(100,100));
        f.setPanelPrincipal(panel_principal);
        f.setPanelSecondaire(panel_tampon);
        f.Start();
        
        //f.pack();
        f.setSize(900, 700);
        
        f.setVisible(true);
    }
    
    public void catchClick(Point p, Panel panel){
        
        
           
        
            if(panel == this.panel_principal){
                //Pas encore implementer
                if(! this.actif){
                    if(p.x < this.largeur && p.y < this.hauteur){
                   this.tab[p.x][p.y] = !this.tab[p.x][p.y];
                   this.panel_principal.setTab(this.tab);
                   }
                }
            }else if(panel == this.panel_tampon) {
                   if(p.x < 10 && p.y < 10){
                   this.tab_mini[p.x][p.y] = !this.tab_mini[p.x][p.y];
                   this.panel_tampon.setTab(this.tab_mini);
                   }
            }
            
        }
            
       
    
    
    public void catchClickDroit(Point p, Panel panel, int mod){
        int onmask = MouseEvent.SHIFT_DOWN_MASK | MouseEvent.BUTTON3_DOWN_MASK;
        int x = p.x;
        int y = p.y;
        if( (mod & onmask) == onmask){//Clic droit + shift
            System.out.println("Shift");
             for(int i = 0; i<10; i++){
                  for(int j = 0; j<10; j++){
                      if(p.x+i < this.largeur && p.y+j < this.hauteur){
                          this.tab[i+x][j+y] = this.tab_mini[i][j];
                      }                        
                  }
            }
             this.panel_principal.setTab(tab);
            
        } else{ //Juste clic droit
            System.out.println("juste clic droit");
            for(int i = 0; i<10; i++){
                  for(int j = 0; j<10; j++){
                      if(p.x+i < this.largeur && p.y+j < this.hauteur){
                          this.tab_mini[i][j] = this.tab[i+x][j+y];
                      }else{
                          this.tab_mini[i][j] = false;
                      }
                        
                  }
            }
            this.panel_tampon.setTab(tab_mini);
            
        }
        
    }
    
    public void nextMove(){
        this.tab = m.nextMove();
        this.panel_principal.setTab(this.tab);
        this.m.setTab(this.tab);
        
    }
     /**
     * <b>clear </b>
     * Reinitialise toute les cases du tableau à false
     */
    public void clear(){
        //Retire toute les cellules du tableau
        for(int i =0; i< largeur ; i++){
            for(int j = 0; j <hauteur; j++){
                this.tab[i][j] = false;
            }
        }
        this.panel_principal.setTab(this.tab);
        this.m.setTab(this.tab);
    }
    /**
     * <b>play pause </b>
     * Met le thread de jeu en pause, ou le remet en route
     */
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
        this.change_etat();
        
    }
    
    private void change_etat(){
        this.actif = ! this.actif;
        this.panel_principal.setActif(this.actif);
    }
    
    
    /**
     * <b> Redefinition taille tableau </b>
     * 
     * Change la taille du tableau, 
     * verifie la validité des parametre
     * 
     * @param larg
     *      Nouvelle largeur
     * @param haut 
     *      Nouvelle hauteur
     */
    
    public void resize(String larg,String haut){
        
        //Convertissage des valeurs (proviennent d'une entrée utilisateur
        int lar = Static.parseWithDefault(larg, -1); 
        int hau = Static.parseWithDefault(haut, -1);
        //Verification de ces valeurs, la taille maximal est pour eviter les ralentissement
        if(lar > 0 && lar <200 && hau>0 && hau <200){
            this.tab = this.m.resize(lar, hau);
            this.largeur = lar;
            this.hauteur = hau;
            this.panel_principal.setTab(this.tab);
        }else{
            System.err.println("mauvais format de tableau");
        }
    }
    
   
    
    public Dimension getSize(){
        return new Dimension(largeur,hauteur);
    }
}

