/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jerem
 */
public class Frame extends JFrame{
    private Controleur controleur;
    private JButton quit = new JButton("quit");
    private JButton next = new JButton("next");
    private Box box_left;
    private JButton clear = new JButton("clear");
    private JButton play = new JButton("play");
    public void Start(){ 
        this.box_left = Box.createVerticalBox();
        this.add(quit,BorderLayout.SOUTH);
        this.add(box_left,BorderLayout.WEST);
        box_left.add(next);
        box_left.add(Box.createVerticalGlue());
        box_left.add(clear);
        box_left.add(Box.createVerticalGlue());
        box_left.add(play);
        quit.setSize(25, 30);
        
        quit.addActionListener((ActionEvent evt) -> {    
            int reponse = JOptionPane.showConfirmDialog(null,
                    "Exit ?", "", JOptionPane.YES_NO_OPTION);
            if(reponse == JOptionPane.YES_OPTION){
                this.dispose();
            }
        });
        
        next.addActionListener((ActionEvent evt) -> {
            this.controleur.nextMove();    
        });
        
        clear.addActionListener((ActionEvent evt) -> {
            this.controleur.clear();
        });
        
        play.addActionListener((ActionEvent evt) -> {
            if(this.play.getText().equals("play")){
                this.play.setText("pause");
            } else {
                this.play.setText("play");
            }
            this.controleur.playpause();
        });
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setLienControlleur(Controleur c){
        this.controleur = c;
    }

}
