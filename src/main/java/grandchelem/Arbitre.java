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
    
    /**
     * Création d'un arbitre avec une humeur de 0.5 par défaut
     * @param genre 1 (homme) ou 2 (femme) (défaut : homme)
     * @param nomNaissance
     * @param prenom 
     */
    public Arbitre(int genre, String nomNaissance, String prenom){
        super(genre, nomNaissance, prenom);
        this.humeur = 0.5;
    }
    
    /**
     * Création d'un arbitre avec possibilité de choisir son humeur (entre 0 et 1)
     * @param genre
     * @param nomNaissance
     * @param prenom
     * @param humeur 
     */
    public Arbitre(int genre, String nomNaissance, String prenom, double humeur){
        super(genre, nomNaissance, prenom);
        this.humeur = humeur;
    }
    
    public double getHumeur(){
        return this.humeur;
    }
    
    @Override
    public String toString() {
        return prenom + " " + nomNaissance;
    }
}
