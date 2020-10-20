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
public class Match {
    final Joueur joueur1;
    final Joueur joueur2;
    final Arbitre arbitre;
    
    public Match(Joueur joueur1, Joueur joueur2, Arbitre arbitre){
        double i = Math.random();
        if (i<0.5){
            this.joueur1=joueur1;
            this.joueur2=joueur2;
        }
        else{
            this.joueur1=joueur2;
            this.joueur2=joueur1;
        }
        this.arbitre=arbitre;
        System.out.println(toString());
    }
   

    @Override
    public String toString() {
        return "Le match opposera " + joueur1 + " qui commencera à servir, et " + joueur2 + ". Il sera arbitré par " + arbitre + '.';
    }
    
    
}
