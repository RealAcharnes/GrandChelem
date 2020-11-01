/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.io.IOException;

/**
 *
 * @author charl
 */
public class Tennis {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        /*Joueur joueur1 = new Joueur(1, "Fenart", "Charles", 2, "Wilson", "Alexis", "Français");
        Joueur joueur2 = new Joueur(1, "Texier", "Eloi", 1, "Babolat", "Alexis", "Français");
        Arbitre arbitre = new Arbitre(1, "Nollet", "Paul");
        Match match = new Match(joueur1, joueur2, arbitre);
        match.jouerMatch();*/
        
        //TestReadingFile.readCsv();
        Menu menu = new Menu();
        menu.StartMenu();
        
    }
    
}
