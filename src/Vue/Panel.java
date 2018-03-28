/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Controleur.Controleur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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
        this.longeur_cellule = this.getWidth()/this.largeur;
        this.hauteur_cellule = this.getHeight()/this.hauteur;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        resize();
        int x = e.getX()/(int)longeur_cellule;
        int y = e.getY()/(int)hauteur_cellule;
        controleur.catchClick(new Point(x,y));
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
               // g.setColor(Color.black);
                //g.drawLine(0 , j*(int)hauteur_cellule, largeur*(int)this.longeur_cellule, j*(int)this.hauteur_cellule);
            }
            //g.setColor(Color.black);
            //g.drawLine(i*(int)this.longeur_cellule, 0, i*(int)this.longeur_cellule, this.hauteur*(int)this.hauteur_cellule);
        }
        g.setColor(Color.red);
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
        repaint();
       // System.out.println(e.getPoint());
    }
    
}
