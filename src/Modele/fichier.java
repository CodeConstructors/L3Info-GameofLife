/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import static Controleur.Static.parseWithDefault;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerem
 */
public class fichier {
    
    
    
    public static void save(String fileName,boolean [][] tab,String name){
        try {
            FileWriter fw=fw = new FileWriter(fileName, true);
            fw.write(name + "$");
            Integer longueur;
            longueur = tab.length;
             Integer hauteur;
            hauteur = tab[0].length;
            fw.write(longueur.toString());
            fw.write('/');
            fw.write(hauteur.toString());
            fw.write('/');
            int n = 0;
            Integer val = 0;
            for(int i = 0; i< tab.length; i++){
                for(int j = 0; j< tab[0].length; j++){
                    
                    if(tab[i][j]){
                       val += (int)Math.pow(2, n);
                    }
                    n++;
                    if(n == 8){
                        fw.write(val.toString()) ;
                        fw.write(' ');
                        val = 0;
                        n=0;
                    }
                 
                }
                 
            }
            fw.write('.');
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(fichier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static ArrayList<boolean [][]> charger(String fileName){
        try {
            FileReader fr = new FileReader(fileName);
            char [] c = new char[10000];
           
            String s = new String();
             
            fr.read(c);
            s = c.toString();
            //s.
           // System.out.println(s);
            String tableaux[] = s.split(".");
            System.out.println(tableaux.length);
            String name[] = new String[tableaux.length];
            String valeur[][] = new String[tableaux.length][3];
            int longueur = 0;
            int hauteur = 0;
            boolean [][]tab;
            ArrayList<boolean [][]> arr = new ArrayList<>();
            int octet[];
            boolean [] t = new boolean[longueur * hauteur];
            for(int i =0; i< tableaux.length;i++){
                name[i] = tableaux[i].split("$")[0];
                System.out.println( tableaux[i].split("$")[0] + i);
                valeur[i] = tableaux[i].split("$")[1].split("/");
                
                longueur = parseWithDefault(valeur[i][0], -1) ;
                hauteur = parseWithDefault(valeur[i][1], -1) ;
                tab = new boolean[longueur][hauteur];
                octet =new int[ valeur[i][2].split(" ").length];
                for(int j =0;j< octet.length; j++){
                    octet[j] = parseWithDefault(valeur[i][2].split(" ")[j], -1);
                    for(int n =0; n<8; n++){
                        t[j*8+n]= (octet[j] & (int) Math.pow(2, n)) == Math.pow(2, n);
                    }
                }
               for(int x =0; x<tab.length;x++){
                    for(int y=0; y< tab[0].length; y++){
                        tab[x][y] = t[x*y];
                    }
                } 
                arr.add(tab);
            }
            
            return arr;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fichier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(fichier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
    
    
}
