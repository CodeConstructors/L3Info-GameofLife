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
    private JButton butt_quit = new JButton("Quit");
    private JButton butt_next = new JButton("Next");
    private Box box_left;
    private JButton butt_clear = new JButton("Clear");
    private JButton butt_play = new JButton("Play");
    public void Start(){ 
        this.box_left = Box.createVerticalBox();
        this.add(butt_quit,BorderLayout.SOUTH);
        this.add(box_left,BorderLayout.WEST);
        box_left.add(butt_next);
        box_left.add(Box.createVerticalGlue());
        box_left.add(butt_clear);
        box_left.add(Box.createVerticalGlue());
        box_left.add(butt_play);
        butt_quit.setSize(25, 30);
        
        butt_quit.addActionListener((ActionEvent evt) -> {    
            int reponse = JOptionPane.showConfirmDialog(null,
                    "Quit ?", "", JOptionPane.YES_NO_OPTION);
            if(reponse == JOptionPane.YES_OPTION){
                this.dispose();
            }
        });
        
        butt_next.addActionListener((ActionEvent evt) -> {
            this.controleur.nextMove();    
        });
        
        butt_clear.addActionListener((ActionEvent evt) -> {
            this.controleur.clear();
        });
        
        butt_play.addActionListener((ActionEvent evt) -> {
            if(this.butt_play.getText().equals("Play")){
                this.butt_play.setText("Stop");
            } else {
                this.butt_play.setText("Play");
            }
            this.controleur.playpause();
        });
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setLienControlleur(Controleur c){
        this.controleur = c;
    }

}
