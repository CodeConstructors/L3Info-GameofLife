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
    
    
     //Integer.parseInt avec valeur par default en cas d'erreur
    static public int parseWithDefault(String s, int def) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            // It's OK to ignore "e" here because returning a default value is the documented behaviour on invalid input.
            return def;
        }
    }
}
