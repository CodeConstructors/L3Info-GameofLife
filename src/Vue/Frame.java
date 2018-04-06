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

/**
 * Frame est la vue principal sur laquelle tous les affichages sont placer
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>parler des boutons ici</li>
 * 
 * </ul>
 * 
 * @author benoit gougeon
 * @version 1.0
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
    private JButton butt_save = new JButton("save");
    private JButton butt_charg = new JButton("charge");
    private Panel panelPrincipal;
    private Panel panelSecondaire;
    public void Start(){
         this.tf_tablewidth.setValue(this.controleur.getSize().width);
         this.tf_tableheight.setValue(this.controleur.getSize().height);
         
        this.add(vbox_eastPanel,BorderLayout.EAST);
        vbox_eastPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        vbox_eastPanel.add(this.panelSecondaire);
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
        
        vbox_eastPanel.add(butt_save);
        vbox_eastPanel.add(butt_charg);
        this.add(this.panelPrincipal, BorderLayout.CENTER);
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
         butt_oksize.addActionListener((ActionEvent evt) -> {
            this.controleur.resize(this.tf_tablewidth.getText(), this.tf_tableheight.getText());
        });
          butt_save.addActionListener((ActionEvent evt) -> {
            this.controleur.save();
        });
           butt_charg.addActionListener((ActionEvent evt) -> {
            this.controleur.charg();
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
    
    public void setPanelPrincipal(Panel p){
        this.panelPrincipal = p;
        
    }
     public void setPanelSecondaire(Panel p ){
        this.panelSecondaire = p;
    }

}
