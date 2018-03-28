/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author jerem
 */
public class Frame extends JFrame{
    private Controleur controleur;
    private JButton butt_quit = new JButton("Quit");
    private JButton butt_next = new JButton("Next");
    private JButton butt_clear = new JButton("Clear");
    private JButton butt_play = new JButton("Play");
    
    private Box vbox_westPanel = Box.createVerticalBox();
    private Box vbox_eastPanel = Box.createVerticalBox();
    
    private Box hbox_tablewidth = Box.createHorizontalBox();
    private Box hbox_tableheight = Box.createHorizontalBox();
    private JLabel lab_tablewidth = new JLabel("Width : ");
    private JLabel lab_tableheight = new JLabel("Height : ");
    private JFormattedTextField tf_tablewidth = new JFormattedTextField("100");
    private JFormattedTextField tf_tableheight = new JFormattedTextField("100");
    private JButton butt_oksize = new JButton("OK");
    
    public void Start(){
         
        this.add(vbox_eastPanel,BorderLayout.EAST);
        vbox_eastPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        vbox_eastPanel.add(Box.createVerticalGlue());
        vbox_eastPanel.add(butt_quit);
        
        this.add(vbox_westPanel,BorderLayout.WEST);
        vbox_westPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        vbox_westPanel.add(butt_next);
        vbox_westPanel.add(butt_clear);
        vbox_westPanel.add(lab_tablewidth);
        
        vbox_westPanel.add(hbox_tablewidth);
        hbox_tablewidth.add(lab_tablewidth);
        hbox_tablewidth.add(tf_tablewidth);
        
        vbox_westPanel.add(hbox_tableheight);
        hbox_tableheight.add(lab_tableheight);
        hbox_tableheight.add(tf_tableheight);
        
        vbox_westPanel.add(butt_oksize);
        vbox_westPanel.add(butt_play);
        
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
