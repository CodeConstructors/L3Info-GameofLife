/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

/**
 * Frame est la vue principal sur laquelle tous les affichages sont placer Cette
 * classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>parler des boutons ici</li>
 *
 * </ul>
 *
 * @author Jérémy Rousseau & Benoît Gougeon
 * @version 1.0
 */
public class Frame extends JFrame {

    private Controleur controleur;
    private final JButton butt_quit = new JButton("Quit");
    private final JButton butt_next = new JButton("Next");
    private final JButton butt_clear = new JButton("Clear");
    private final JButton butt_play = new JButton("Play");

    private final Box vbox_westPanel = Box.createVerticalBox();
    private final Box vbox_eastPanel = Box.createVerticalBox();

    private final Box hbox_tablewidth = Box.createHorizontalBox();
    private final Box hbox_tableheight = Box.createHorizontalBox();
    private final JLabel lab_tablewidth = new JLabel("Width : ");
    private final JLabel lab_tableheight = new JLabel("Height : ");
    private final JFormattedTextField tf_tablewidth = new JFormattedTextField("100");
    private final JFormattedTextField tf_tableheight = new JFormattedTextField("100");
    private final JFormattedTextField tf_proportion = new JFormattedTextField("50");

    private final Box hbox_edition_tableau = Box.createVerticalBox();
    private final JLabel lab_edition_tableau = new JLabel("Edition du plateau");
    private final Box hbox_parametre_jeu = Box.createVerticalBox();
    private final Box hbox_proportion = Box.createHorizontalBox();
    private final JLabel lab_proportion = new JLabel("Probabilité : ");

    private final JButton butt_oksize = new JButton("OK");

    private final JButton butt_load = new JButton("Load");
    private final JButton butt_rand = new JButton("Randomize");

    private final Box hbox_asphyxie = Box.createHorizontalBox();
    private final Box hbox_solitude = Box.createHorizontalBox();
    private final Box hbox_vieMin = Box.createHorizontalBox();
    private final Box hbox_vieMax = Box.createHorizontalBox();
    private final JLabel lab_asphyxie = new JLabel("Valeur asphyxie : ");
    private final JLabel lab_solitude = new JLabel("Valeur solitude : ");
    private final JLabel lab_vieMin = new JLabel("Valeur mini vie : ");
    private final JLabel lab_vieMax = new JLabel("Valeur maxi vie : ");
    private final JSpinner asphyxie = new JSpinner();
    private final JSpinner solitude = new JSpinner();
    private final JSpinner vieMin = new JSpinner();
    private final JSpinner vieMax = new JSpinner();

    private final JButton butt_plus = new JButton("+");
    private final JButton butt_moins = new JButton("-");
    private final JScrollPane scrollable = new JScrollPane();
    private Panel panelPrincipal;
    private Panel panelSecondaire;

    private final JComboBox selection_patern = new JComboBox();

    public void Start() {
        this.tf_tablewidth.setValue(this.controleur.getSize().width);
        this.tf_tablewidth.setMinimumSize(new Dimension(80, 25));
        this.tf_tablewidth.setMaximumSize(new Dimension(80, 25));
        this.tf_tableheight.setValue(this.controleur.getSize().height);
        this.tf_tableheight.setMinimumSize(new Dimension(80, 25));
        this.tf_tableheight.setMaximumSize(new Dimension(80, 25));
        this.asphyxie.setMaximumSize(new Dimension(90, 25));
        this.asphyxie.setMinimumSize(new Dimension(90, 25));

        this.solitude.setMaximumSize(new Dimension(90, 25));
        this.solitude.setMinimumSize(new Dimension(90, 25));

        this.vieMin.setMaximumSize(new Dimension(90, 25));
        this.vieMin.setMinimumSize(new Dimension(90, 25));

        this.vieMax.setMaximumSize(new Dimension(80, 25));
        this.vieMax.setMinimumSize(new Dimension(80, 25));

        this.tf_proportion.setMaximumSize(new Dimension(80, 25));
        this.tf_proportion.setMinimumSize(new Dimension(80, 25));

        this.add(vbox_eastPanel, BorderLayout.EAST);
        vbox_eastPanel.setMinimumSize(new Dimension(500, 500));
        vbox_eastPanel.setMaximumSize(new Dimension(500, 500));
        vbox_eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vbox_eastPanel.add(this.panelSecondaire);
        vbox_eastPanel.add(Box.createVerticalGlue());
        vbox_eastPanel.add(butt_quit);
        vbox_eastPanel.add(butt_load);
        vbox_eastPanel.add(selection_patern);
        butt_quit.setMinimumSize(new Dimension(480, 25));
        butt_quit.setMaximumSize(new Dimension(480, 25));
        butt_load.setMinimumSize(new Dimension(480, 25));
        butt_load.setMaximumSize(new Dimension(480, 25));
        selection_patern.setMinimumSize(new Dimension(150, 25));
        selection_patern.setMaximumSize(new Dimension(150, 25));
        selection_patern.setPreferredSize(new Dimension(150, 25));

        this.add(vbox_westPanel, BorderLayout.WEST);
        vbox_westPanel.setMinimumSize(new Dimension(70, 300));
        vbox_westPanel.setPreferredSize(new Dimension(230, 500));
        vbox_westPanel.setMaximumSize(new Dimension(250, 500));
        vbox_westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vbox_westPanel.add(hbox_edition_tableau);
        hbox_edition_tableau.add(lab_edition_tableau);
        hbox_edition_tableau.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        hbox_edition_tableau.add(butt_next);
        hbox_edition_tableau.add(butt_clear);
        butt_next.setMinimumSize(new Dimension(480, 25));
        butt_next.setMaximumSize(new Dimension(480, 25));
        butt_clear.setMinimumSize(new Dimension(480, 25));
        butt_clear.setMaximumSize(new Dimension(480, 25));
        butt_rand.setMinimumSize(new Dimension(480, 25));
        butt_rand.setMaximumSize(new Dimension(480, 25));
        hbox_edition_tableau.add(lab_tablewidth);
        hbox_edition_tableau.add(hbox_tablewidth);

        hbox_edition_tableau.add(hbox_tablewidth);
        hbox_tablewidth.add(lab_tablewidth);
        hbox_tablewidth.add(tf_tablewidth);

        hbox_edition_tableau.add(hbox_tableheight);
        hbox_tableheight.add(lab_tableheight);
        hbox_tableheight.add(tf_tableheight);
        hbox_edition_tableau.add(butt_oksize);

        hbox_edition_tableau.add(hbox_proportion);
        hbox_proportion.add(lab_proportion);
        hbox_proportion.add(tf_proportion);
        hbox_edition_tableau.add(butt_rand);

        vbox_westPanel.add(hbox_parametre_jeu);
        hbox_parametre_jeu.add(new JLabel("parametre du jeu"));
        hbox_parametre_jeu.add(hbox_asphyxie);
        hbox_asphyxie.add(lab_asphyxie);
        hbox_asphyxie.add(asphyxie);

        hbox_parametre_jeu.add(hbox_solitude);
        hbox_solitude.add(lab_solitude);
        hbox_solitude.add(solitude);

        hbox_parametre_jeu.add(hbox_vieMin);
        hbox_vieMin.add(lab_vieMin);
        hbox_vieMin.add(vieMin);

        hbox_parametre_jeu.add(hbox_vieMax);
        hbox_vieMax.add(lab_vieMax);
        hbox_vieMax.add(vieMax);

        vbox_westPanel.add(butt_play);
        butt_oksize.setMinimumSize(new Dimension(480, 25));
        butt_oksize.setMaximumSize(new Dimension(480, 25));
        butt_play.setMinimumSize(new Dimension(480, 25));
        butt_play.setMaximumSize(new Dimension(480, 25));

        this.add(scrollable, BorderLayout.CENTER);
        scrollable.add(panelPrincipal);
        butt_quit.setSize(25, 30);

        butt_quit.addActionListener((ActionEvent evt) -> {
            int reponse = JOptionPane.showConfirmDialog(null,
                    "Quit ?", "", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        });

        butt_next.addActionListener((ActionEvent evt) -> {
            this.controleur.nextMove();
        });

        butt_clear.addActionListener((ActionEvent evt) -> {
            int reponse = JOptionPane.showConfirmDialog(null,
                    "Clear ?", "", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                this.controleur.clear();
            }
        });

        butt_oksize.addActionListener((ActionEvent evt) -> {
            int reponse = JOptionPane.showConfirmDialog(null,
                    "Resize ?", "", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                this.controleur.resize(this.tf_tablewidth.getText(), this.tf_tableheight.getText());
            }
        });

        butt_load.addActionListener((ActionEvent evt) -> {
            this.controleur.charg(this.selection_patern.getSelectedItem());
        });

        butt_rand.addActionListener((ActionEvent evt) -> {
            int reponse = JOptionPane.showConfirmDialog(null,
                    "Random ?", "", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                try {
                    this.controleur.random(Integer.valueOf(this.tf_proportion.getText()));
                } catch (java.lang.NumberFormatException e) {
                    System.err.println("Mauvaise valeur, inserer un entier");
                    this.tf_proportion.setValue("50");
                }
            }
        });

        butt_play.addActionListener((ActionEvent evt) -> {
            this.playPause_boutton();
            this.controleur.playpause();
        });

        butt_plus.addActionListener((ActionEvent evt) -> {
            this.controleur.zoom(1, this.panelPrincipal);
        });

        butt_moins.addActionListener((ActionEvent evt) -> {
            this.controleur.zoom(-1, this.panelPrincipal);
        });

        asphyxie.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            JSpinner s = (JSpinner) evt.getSource();
            if (!this.controleur.setAsphyxie(Integer.valueOf(s.getValue().toString()))) {
                s.setValue(s.getPreviousValue());
            }
        });

        solitude.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            JSpinner s = (JSpinner) evt.getSource();
            if (!this.controleur.setSolitude(Integer.valueOf(s.getValue().toString()))) {
                s.setValue(s.getPreviousValue());
            }
        });

        vieMin.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            JSpinner s = (JSpinner) evt.getSource();
            if (!this.controleur.setVieMin(Integer.valueOf(s.getValue().toString()))) {
                s.setValue(s.getPreviousValue());
            }
        });

        vieMax.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            JSpinner s = (JSpinner) evt.getSource();
            if (!this.controleur.setVieMax(Integer.valueOf(s.getValue().toString()))) {
                s.setValue(s.getPreviousValue());
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setLienControlleur(Controleur c) {
        this.controleur = c;
    }

    public void setPanelPrincipal(Panel p) {
        this.panelPrincipal = p;
    }

    public void setPanelSecondaire(Panel p) {
        this.panelSecondaire = p;
    }

    public void playPause_boutton() {
        if (this.butt_play.getText().equals("Play")) {
            this.butt_play.setText("Stop");
        } else {
            this.butt_play.setText("Play");
        }
    }

    public void redef_taille(int hauteur, int largeur) {
        this.tf_tablewidth.setValue(this.controleur.getSize().width);
        this.tf_tableheight.setValue(this.controleur.getSize().height);
    }

    public void addPatern(Object o) {
        this.selection_patern.addItem(o);
    }

    public void setParametreJeu(int asphyxie, int solitude, int vieMin, int vieMax) {
        this.asphyxie.setValue(asphyxie);
        this.solitude.setValue(solitude);
        this.vieMin.setValue(vieMin);
        this.vieMax.setValue(vieMax);
    }
}
