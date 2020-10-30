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
public class Arbitre extends Personne{
    double humeur; //Double entre 0 et 1 qui montre l'humeur de l'arbitre, 1 étant la meilleure humeur
    
    public Arbitre(int genre, String nomNaissance, String prenom){
        super(genre, nomNaissance, prenom);
        this.humeur = 0.5;
    }
    
    public Arbitre(int genre, String nomNaissance, String prenom, double humeur){
        super(genre, nomNaissance, prenom);
        this.humeur = humeur;
    }
    
    @Override
    public String toString() {
        return prenom + " " + nomNaissance;
    }
}
