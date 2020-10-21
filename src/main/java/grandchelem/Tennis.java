/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

/**
 *
 * @author charl
 */
public class Tennis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Joueur joueur1 = new Joueur(1, "Fenart", "Charles", 2, "Wilson", "Alexis", "Français");
        Joueur joueur2 = new Joueur(1, "Coudon", "Jean Charles", 1, "Babolat", "Alexis", "Français");
        Arbitre arbitre = new Arbitre(1, "Texier", "Eloi");
        Match match = new Match(joueur1, joueur2, arbitre);
        match.jouerMatch();
    }
    
}
