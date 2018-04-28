/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author jerem
 */
public class patern {
    
    boolean[][] tab;
    String name;
    
    public patern(boolean[][] t, String n){
        this.tab = new boolean[t.length][t[0].length];
        this.name=n;
        for(int i =0;i<t.length;i++){
            for(int j=0;j<t[0].length; j++ ){
                this.tab[i][j] = t[i][j];
            }
        } 
    }
    
    public boolean[][] getTab(){
        return this.tab;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
}
