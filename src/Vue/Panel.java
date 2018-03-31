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
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author jerem
 */
public class Panel extends JPanel implements MouseListener, MouseMotionListener{
    
    private boolean [][] tab;
    private int largeur;
    private int hauteur;
    private Controleur controleur;
    private float longeur_cellule;
    private float hauteur_cellule;
    private boolean actif = false;
    private boolean taille_relative = false;
    private int nbPixel = 10;
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
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public Panel(boolean t [][]){
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.setTab(t);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    
    public void setTab(boolean t [][]){
        this.tab = t;
        this.largeur = t.length;
        this.hauteur = t[0].length;
        this.resize();
        this.repaint();
    }
    
    private void resize(){
        if(this.taille_relative){
            this.longeur_cellule = this.getWidth()/this.largeur;
            this.hauteur_cellule = this.getHeight()/this.hauteur;
        }else{
            this.longeur_cellule = this.nbPixel;
            this.hauteur_cellule = this.nbPixel;
            this.setSize(this.nbPixel*this.largeur, this.nbPixel*this.hauteur);
        }
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
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
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
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
        g.setColor(Color.red);
        if(!actif)g.drawRect((int)this.mousePos.getX()*(int)this.longeur_cellule, (int)this.mousePos.getY()*(int)this.hauteur_cellule, (int)this.longeur_cellule, (int)this.hauteur_cellule);
      
    }
    
    public void setLienControleur(Controleur c ){
        this.controleur = c;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    //non utiliser actuellement
    private Point mousePos = new Point(0,0);
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
        int x = e.getX()/(int)longeur_cellule;
        int y = e.getY()/(int)hauteur_cellule;
        mousePos = new Point(x,y);
        repaint();
       // System.out.println(e.getPoint());
    }
    
    public void setActif(boolean a){
        this.actif = a;
    }
}
