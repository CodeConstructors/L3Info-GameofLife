/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;

import Modele.Modele;
import Modele.patern;
import Vue.Frame;
import Vue.Panel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author jeremy
 */
public class Controleur {
 
    
    /**Le panel dans le quel se deroule le jeu */
    private final Panel panel_principal;
    /**Le panel pour les copies */
    private final Panel panel_tampon;
    /**L'afficheur pour les boutons */
    private final Frame f;
    private final Modele m;
    private final auto aut;
    /**Le tableau pour les cellules du panel principal */
    private boolean tab [][];
    /**Le tableau pour les cellules du panel secondaire */
    private final boolean tab_mini [][];
    /**Nombre de cellules du tableau en X */
    private int largeur = Static.INI_LARGEUR;
    /**Nombre de cellules du tableau en Y */
    private int hauteur = Static.INI_HAUTEUR;
    private boolean actif;
    
    /**Liste des paterns pour le panel tampon */
    private final ArrayList<patern> list_patern;
    
    /**Constructeur par default du Controleur, il crée la vue et le modele et les initialise */
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
        //tab[3][4] = true;
        tab_mini = new boolean[Static.LARGEUR_MINI][Static.HAUTEUR_MINI];
        for(int i =0; i< 10 ; i++){
            for(int j = 0; j <10; j++){
                tab_mini[i][j] = false;
            }
        }
        
        
       
        this.m.setTab(this.tab);
        this.m.setAsphyxie(Static.INI_ASPHYXIE);
        this.m.setMaxVie(Static.INI_MAXVIE );
        this.m.setMinVie(Static.INI_MINVIE);
        this.m.setSolitude(Static.INI_SOLITUDE);
        
        this.panel_principal = new Panel(this.tab);
        this.panel_tampon = new Panel(this.tab_mini);
        this.f = new Frame();
        
        //Etablit les liens
        this.f.setLienControlleur(this);
        this.panel_principal.setLienControleur(this);
        this.panel_principal.setNombre_Pixel(Static.INI_PIXEL);
        this.panel_tampon.setLienControleur(this);
        this.panel_tampon.setPreferredSize(new Dimension(100,100));
        f.setPanelPrincipal(panel_principal);
        f.setPanelSecondaire(panel_tampon);
        
       /* Iterator<patern> it = this.list_patern.iterator();
        while(it.hasNext()){
                f.addPatern(it.next());   
        }*/
        
        this.list_patern = this.generation_patern();
        f.Start();
        
        //f.pack();
        f.setSize(900, 700);
        
        f.setVisible(true);
    }
    
    /**<b> Genration des paternes <b/>
     * Genere une liste de patern, version actuel avec paterns dans le code
     * @return La liste des des paterns 
     */
    private ArrayList<patern> generation_patern(){
        ArrayList<patern> a = new ArrayList<patern>();
        boolean[][] tab_local = new boolean[Static.LARGEUR_MINI][Static.HAUTEUR_MINI];
        patern p;
        ArrayList<Point> list_point= new ArrayList<>();
        
        
        reset_tab(tab_local);
        list_point.clear();
        //Definition des positions des cellules
        list_point.add(new Point(0,2));
        list_point.add(new Point(1,2));
        list_point.add(new Point(2,2));
        list_point.add(new Point(2,1));
        list_point.add(new Point(1,0));
        
        this.set_tab(tab_local,list_point);
        p = new patern(tab_local, "slider");
        a.add(p);
        f.addPatern(p);
        //Reinitialisation du tab
        tab_local = new boolean[Static.LARGEUR_MINI][Static.HAUTEUR_MINI];
        reset_tab(tab_local);
        list_point.clear();
        
        list_point.add(new Point(0,0));
        list_point.add(new Point(3,0));
        list_point.add(new Point(4,1));
        list_point.add(new Point(0,2));
        list_point.add(new Point(4,2));
        list_point.add(new Point(1,3));
        list_point.add(new Point(2,3));
        list_point.add(new Point(3,3));
        list_point.add(new Point(4,3));
        this.set_tab(tab_local,list_point);
        p = new patern(tab_local, "vaisseaux");
        a.add(p);
        f.addPatern(p);
        //Reinitialisation du tab
        tab_local = new boolean[Static.LARGEUR_MINI][Static.HAUTEUR_MINI];
        reset_tab(tab_local);
        list_point.clear();
        
        list_point.add(new Point(0,0));
        list_point.add(new Point(1,0));
        list_point.add(new Point(2,0));
        list_point.add(new Point(3,0));
        list_point.add(new Point(0,1));
        list_point.add(new Point(3,2));
        list_point.add(new Point(4,2));
        list_point.add(new Point(1,3));
        list_point.add(new Point(2,3));
        list_point.add(new Point(4,3));
        list_point.add(new Point(0,4));
        list_point.add(new Point(2,4));
        list_point.add(new Point(4,4));
        
        set_tab(tab_local,list_point);
        p = new patern(tab_local, "autre");
        a.add(p);
        f.addPatern(p);
        
        return a;
    }
    
    /** <b> static reset_tab </b>
     * Initialise le tableau avec toutes les cellules à false
     * @param t : tableau à initialiser
     */
    private static void reset_tab(boolean[][] t){
        for(int i=0; i<t.length; i++){
            for(int j =0; j< t[0].length; j++){
                try{
                    t[i][j]=false;
                }catch(ArrayIndexOutOfBoundsException e){
                    System.err.println("ArrayIndexOutOfBoundsException reset_tab ");
                }
            }
        }
    }
    
    /** <b> static set_tab <b/>
     * Definit un tableau de boolean en se basant sur un tableau de point
     * /!\Rajoute des points sans reinitialiser le tableau 
     * @param t : tableau où ajouter les points
     * @param p : ArrayList des points à definir
     */
    private static void set_tab(boolean[][] t,ArrayList<Point> p){
        Point pt;
        Iterator<Point> it = p.iterator();
        while(it.hasNext()){
            pt = it.next();
            try{
                t[pt.x][pt.y] = true;
            }catch(ArrayIndexOutOfBoundsException e){
                System.err.println("ArrayIndexOutOfBoundsException set_tab : x" + pt.x +" y:" + pt.y);
            }
                
            
                    
        }
    }
    /**catchClick 
    * Methode qui traite les click sur un des panel
    * @param p : Le point sur lequel l'utilisateur a cliquer
    * @param panel : Le panel sur lequel l'utilisateur a cliqué
    */
    public void catchClick(Point p, Panel panel){
            //L'action sur le panel principal interagit avec le tab
            if(panel.equals(this.panel_principal)){
                //Le click esst sans effet quand le modele est actif
                if(! this.actif){
                    if(p.x < this.largeur && p.y < this.hauteur){
                   this.tab[p.x][p.y] = !this.tab[p.x][p.y];
                   this.panel_principal.setTab(this.tab);
                   }
                }
                //L'action sur le panel secondaire interagit avec tab_mini
            }else if(panel == this.panel_tampon) {
                   if(p.x < 10 && p.y < 10){
                   this.tab_mini[p.x][p.y] = !this.tab_mini[p.x][p.y];
                   this.panel_tampon.setTab(this.tab_mini);
                   }
            }
            
        }
            
       
    
    /**CatchClickDroit
    * Reçoit l'information lors d'un click droit
    * @param p : Le point sur lequel l'utilisateur a cliquer
    * @param panel : Le panel sur lequel l'utilisateur a cliqué
    * @param mod : Le modifieur renvoyer par l'evenement mouseListener lors d'un click
    */
    public void catchClickDroit(Point p, Panel panel, int mod){
        int onmask = MouseEvent.SHIFT_DOWN_MASK | MouseEvent.BUTTON3_DOWN_MASK;
        int x = p.x;
        int y = p.y;
        if(panel.equals(this.panel_principal)){
            if(! this.actif){
                if( (mod & onmask) == onmask){//Clic droit + shift
                    //Copie dans le tableau principal le contenu du tableau secondaire
                     for(int i = 0; i<10; i++){
                          for(int j = 0; j<10; j++){
                              if(p.x+i < this.largeur && p.y+j < this.hauteur){
                                  this.tab[i+x][j+y] = this.tab_mini[i][j];
                              }                        
                          }
                    }
                     this.panel_principal.setTab(tab);

                } else{ //Juste clic droit
                    for(int i = 0; i<10; i++){
                        //Copie dans le tableau secondaire la zone du tableau principal
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
        }
        
    }
    
    /**Modifie les tableaux à partir de ce que le modele
     * calcul pour le prochain mouvement
     */
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
        if(!this.actif){
            if(lar*this.panel_principal.getNombre_Pixel() > 200 && lar <200 && hau*this.panel_principal.getNombre_Pixel()>200 && hau <200){
                this.tab = this.m.resize(lar, hau);
                this.largeur = lar;
                this.hauteur = hau;
                this.panel_principal.setTab(this.tab);
            }else{
                System.err.println("mauvais format de tableau");
            }
        }else{
            this.f.redef_taille(this.hauteur, this.largeur);
        }
        
    }
    @Deprecated
    /** <b>Non implementer </b> */
   public void save(){
      
   }
   
   
   /** <b> Chargement d'un patern </b>
    * Charge un des patern de la liste dans le mini tableau
    * @param n : id du patern
   */
   public void charg(Object o){
       if(o instanceof patern){
           this.setTabMini(((patern) o).getTab());
       }
       
   }
    
  
    /**<b> Génération des cellules aléatoires </b>
     * Crée un tableau remplit de cellule placé aléatoirement
     * La taille du tableau est basé sur celle du panel actif
     * @param prop : proportion (en pourcent) de cellules vivantes
     * @return : Tableau générer.
     */
    private boolean[][] génération_aleatoire(int prop){
        boolean[][] tab = new boolean[this.largeur][this.hauteur];
        Random r = new java.util.Random();
        
        double v = 0;
        for(int i =0; i< largeur ; i++){
            for(int j = 0; j <hauteur; j++){
                v = Math.random()*100;
                
                tab[i][j] =( (v< prop || prop == 100 ) && prop != 0);
               
            }
        }
        System.out.println(prop);
        return tab;
    }
    
    /** <b>Regenere aleatoirement le tableau </b>
     * Met le jeu en pause si necessaire et remplit le 
     * tableau de cellule
     * @param p : Probabilité qu'une cellule soit vivante (0<p<100)
     */
    public void random(int p){
        if(actif){
            this.playpause();
            this.f.playPause_boutton();
        }
        if(p<=100 && p>= 0){
            this.setTab(this.génération_aleatoire(p));
        }
        
    }
    /**<b> Definition du tableau</b>
     * Redefinit le tableau principal et ses copie dans la vue et le controleur
     * Redefinie egalement la taille du tableau en cas de changement
     * @param tab : tableau à copier
     */
    private void setTab(boolean[][] tab){
       this.tab = tab;
       this.largeur = tab.length;
       this.hauteur = tab[0].length;
       this.m.setTab(tab);
       this.panel_principal.setTab(tab);
       
    }
    
    
     /**<b> Definition du tableau tampon </b>
     * Copie le contenu du tableau donner dans le mini tableau
     * Utilise un clone et pas une copie de reference pour eviter
     * d'alterer le tableau initial (dans le contexte du programme le patern)
     * Redefini egalement le tableau de panel_tampon
     * @param tab : tableau à copier
     */
    private void setTabMini(boolean[][] tab){
        for(int i =0; i< tab.length; i++){
            System.arraycopy(tab[i], 0, this.tab_mini[i], 0, tab[0].length);
            
        }
        this.panel_tampon.setTab(this.tab_mini);
    }
    
    
    /**<b> Zoom </b>
     * Le zoom se contente d'augmenter le nombre de pixel du panel principal
     * @param n : Nombre de pixel à changer
     * @param pan : Le panel sur lequel on zoom
     */
    public void zoom(int n, Panel pan){
        if(n >0 || (pan.getNombre_Pixel()*pan.getHeight() > 200 && pan.getNombre_Pixel()*pan.getWidth()> 200 )){
             pan.setNombre_Pixel(pan.getNombre_Pixel() + n);
        }
      
    }
    
    
    
      public Dimension getSize(){
        return new Dimension(largeur,hauteur);
    }
      
      public boolean setAsphyxie(int n){
          if(n<9 && n>= 0){
              this.m.setAsphyxie(n);
              return true;
          }else{
              return false;
          }
      }
      
      public boolean setSolitude(int n){
          if(n<9 && n>= 0){
              this.m.setSolitude(n);
              return true;
          }else{
              return false;
          }
      }
      
      public boolean setVieMin(int n){
          if(n<9 && n>= 0){
              this.m.setMinVie(n);
              return true;
          }else{
              return false;
          }
      }
      
      public boolean setVieMax(int n){
          if(n<9 && n>= 0){
              this.m.setMaxVie(n);
              return true;
          }else{
              return false;
          }
      }
    
}

