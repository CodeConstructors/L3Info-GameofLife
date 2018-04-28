/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Controleur.Controleur;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Panel est la vue qui affiche l'etat en cour du jeu de la vie
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>tab : Le tableau de cellules à afficher</li>
 *  à continuer
 * </ul>
 * 
 * @author jeremy rousseau
 * @version 1.0
 */
public class Panel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
    
    //Tableau des cellules
    private boolean [][] tab;
    /**Largeur */
    private int largeur;
    private int hauteur;
    
    /**Le controleur qui gere ce panel */
    private Controleur controleur;
    /**Definit si la grille est active ou non */
    private boolean actif = false; 
    
    
    //Variables de taille des cellules
    /**longeur d'une cellule, en pixel */
    private float longeur_cellule;
    /**Hauteur d'une cellule, en pixel */
    private float hauteur_cellule;
    /**Definit si la taille des cellules est fixe, ou si elle depend de la taille du panel*/
    private boolean taille_relative = false; 
    /** Nombre de pixel d'une cellule en cas de taille fixe */
    private int nombre_Pixel = 10;

  
    
    //Variables de gestion de la position de la souris pour le carré rouge
    private Point mousePos = new Point(0,0);
    private boolean mouseTraquer;
    
    public Panel(){
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.largeur = 15;
        this.hauteur = 15;
        tab = new boolean[largeur][hauteur];
        for(int i =0; i< this.largeur ; i++){
            for(int j = 0; j < this.hauteur; j++){
                tab[i][j] = true;
            }
        }
        this.addMouseWheelListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public Panel(boolean t [][]){
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.setTab(t);
        this.addMouseWheelListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    /** 
     *<b>Setter du tableau </b>
     * Definit le tableau de cellule utiliser,
     * appelle la redefinition du panel et le rapaint
     * modifie les attributs de taille
     * On ne verifie pas les valeurs du nouveau tableau, ce sera gerer à l'appelle
     * 
     * @param t
     *      Nouveau tableau
     */
    public void setTab(boolean t [][]){
        this.tab = t;
        this.largeur = t.length;
        this.hauteur = t[0].length;
        this.resize();
        this.repaint();
    }
    
    
    /**
     * <b> Redefinition du tableau </b>
     * 
     * Si les cellules sont en taille fixe change la taille du panel
     * Si les cellules sont en taille relative change la taille des cellules
     *
     */
    private void resize(){
        if(this.taille_relative){
            this.longeur_cellule = this.getWidth()/this.largeur;
            this.hauteur_cellule = this.getHeight()/this.hauteur;
        }else{
            this.longeur_cellule = this.nombre_Pixel;
            this.hauteur_cellule = this.nombre_Pixel;
            this.setSize(this.nombre_Pixel*this.largeur, this.nombre_Pixel*this.hauteur);
        }
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    /**
     * <b> Capture des cliques </b>
     * Detecte un clique et recupere ses coordonnée dans le tableau (convertit la position relative au panel
     * en position relative au tableau de cellule ) 
     * Appelle la methode correspondant au bouton de la souris cliquer au controleur
     */
    @Override
    public void mousePressed(MouseEvent e) {
        resize();
        int x = e.getX()/(int)longeur_cellule;
        int y = e.getY()/(int)hauteur_cellule;
        if( e.getButton() == MouseEvent.BUTTON1) {
           controleur.catchClick(new Point(x,y), this);
        }else if( e.getButton() == MouseEvent.BUTTON3) {
            controleur.catchClickDroit(new Point(x,y), this, e.getModifiersEx());
           
        
        }
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Si la souris entre dans le panel, on affiche sa posiiton
      this.mouseTraquer = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Si la souris sort du panel, on arrete d'afficher sa posiiton
        this.mouseTraquer = false;
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g)
    { 
        super.paintComponent(g);
        resize();
        Graphics2D g2 = (Graphics2D)g;
        Stroke taille_trait = g2.getStroke();
        //Double boucle pour dessinner les cellules, i-> largeur, j-> hauteur 
        for(int i =0; i< this.largeur; i++){
            
            for(int j = 0; j < this.hauteur; j++){
                if(this.tab[i][j]){
                    g.setColor(Color.ORANGE);
                    g.fillOval(i*(int)this.longeur_cellule, j*(int)this.hauteur_cellule, (int)this.longeur_cellule, (int)this.hauteur_cellule);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(i*(int)this.longeur_cellule, j*(int)this.hauteur_cellule, (int)this.longeur_cellule, (int)this.hauteur_cellule);
                }
                
                //Si la simulation est active, on n'affiche pas la grille
                if(!actif){
                    g2.setStroke(new BasicStroke(0.2f));
                    g.setColor(Color.lightGray);
                
                    g.drawLine(0 , j*(int)hauteur_cellule, largeur*(int)this.longeur_cellule, j*(int)this.hauteur_cellule);
                    g2.setStroke(taille_trait);  
                }
                
                
            }
            //Si la simulation est active, on n'affiche pas la grille
            if(!actif){
                g2.setStroke(new BasicStroke(0.2f));
                g.setColor(Color.lightGray);
                g.drawLine(i*(int)this.longeur_cellule, 0, i*(int)this.longeur_cellule, this.hauteur*(int)this.hauteur_cellule);
                g2.setStroke(taille_trait);
            }
            
            
                   
                              
        }
        //Dessin du carré rouge pour le pointeur
        //Uniquement si la grille n'est pas active et si le pointeur est sur le panel
        g.setColor(Color.red);
        if(!actif && this.mouseTraquer)
            g.drawRect((int)this.mousePos.getX()*(int)this.longeur_cellule, (int)this.mousePos.getY()*(int)this.hauteur_cellule, (int)this.longeur_cellule, (int)this.hauteur_cellule);
      
    }
    
    public void setLienControleur(Controleur c ){
        this.controleur = c;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        //Enregistre la position de la souris dans le panel
        if(mouseTraquer){
            mousePos = e.getPoint();
            int x = e.getX()/(int)longeur_cellule;
            int y = e.getY()/(int)hauteur_cellule;
            mousePos = new Point(x,y);
            repaint();
            // System.out.println(e.getPoint());
        }
     
    }
    
    public void setActif(boolean a){
        this.actif = a;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
     
           this.controleur.zoom(-e.getWheelRotation(), this);
       
    }
    
      public int getNombre_Pixel() {
        return nombre_Pixel;
    }

    public void setNombre_Pixel(int nombre_Pixel) {
        if(nombre_Pixel> 1 && nombre_Pixel < 15){
            Graphics2D g2 = (Graphics2D) this.getGraphics();
            System.out.println(g2);
           // g2.scale(2,2);
            
            this.nombre_Pixel = nombre_Pixel;
            this.resize();
            this.repaint();
        }
        
    }
}
