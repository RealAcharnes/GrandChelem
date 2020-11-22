/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

/**
 *
 * @author jeanc
 */
public interface InterfaceSpectateur {
    public default void regarderMatch(Joueur joueur1, Joueur joueur2, Arbitre arbitre, int sexe){
        Match lematch = new Match(joueur1,joueur2,arbitre,sexe);
        lematch.jouerMatch(0, 0);
    }
}
