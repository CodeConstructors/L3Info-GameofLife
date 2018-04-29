/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

/**
 *
 * @author jeremy
 */
public abstract class Static {
    public static final int INI_LARGEUR = 70;
    public static final int INI_HAUTEUR = 70;
    
    public static final int INI_MINVIE = 3;
    public static final int INI_MAXVIE = 3;
    public static final int INI_SOLITUDE = 1;
    public static final int INI_ASPHYXIE = 4;
    public static final int LARGEUR_MINI = 10;
    public static final int HAUTEUR_MINI = 10;
    public static final int INI_PIXEL = 10;
    
     //Integer.parseInt avec valeur par default en cas d'erreur
    static public int parseWithDefault(String s, int def) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return def;
        }
    }
}
