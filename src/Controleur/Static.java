/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

/**
 *
 * @author Jérémy Rousseau & Benoît Gougeon
 */
public abstract class Static {

    /**Valeur inital de largeur du panel principal */
    public static final int INI_LARGEUR = 70;
    /**Valeur inital de hauteur du panel principal */
    public static final int INI_HAUTEUR = 70;
    /**Valeur inital de la variable vieMin du modele */
    public static final int INI_MINVIE = 3;
    /**Valeur inital de la variable vieMax du modele */
    public static final int INI_MAXVIE = 3;
    /**Valeur inital de la variable solitude du modele*/
    public static final int INI_SOLITUDE = 1;
    /**Valeur inital de la variable asphyxie du modele */
    public static final int INI_ASPHYXIE = 4;
    /**Valeur inital de largeur du panel secondaire */
    public static final int LARGEUR_MINI = 10;
    /**Valeur inital de hauteur du panel secondaire */
    public static final int HAUTEUR_MINI = 10;
    /**Valeur inital de taille des cases du tableau */
    public static final int INI_PIXEL = 10;
    /**Valeur inital du temps entre deux états du jeu  */
    public static final int TEMPS_PAUSE = 300;

    /**Integer.parseInt avec valeur par default en cas d'erreur
     * Si s n'est pas la representation d'un entier renvoie la 
     * valeur par default
     * @param s : Le string à convertir
     * @param def : La valeur par default à utiliser
     * @return Le string converti ou la valeur par default
     */
    
    static public int parseWithDefault(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
