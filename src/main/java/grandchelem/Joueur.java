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
public class Joueur extends Personne {
    public int main;    //1 = droitier 2 = gaucher
    public String sponsor;
    public int classement;
    public String entraineur;
    public Couleur couleurShort;
    private static int compteurJoueur;
    
    public Joueur(int genre, String nomNaissance, String prenom, int main, String sponsor, int classement, String entraineur){
        super(genre, nomNaissance, prenom);
        this.main = main;
        this.sponsor = sponsor;
        this.entraineur = entraineur;
        this.couleurShort = Couleur.BLEU;   //Couleur par défaut pour un homme -> Bleu
        compteurJoueur++;
        this.classement = compteurJoueur;
    }
    //Accessors
    public int getMain(){
        return this.main;
    }
    public String getSponsor(){
        return this.sponsor;
    }
    public int getClassement(){
        return this.classement;
    }
    public String getEntraineur(){
        return this.entraineur;
    }
    public Couleur getCouleurShort(){
        return this.couleurShort;
    }
}
