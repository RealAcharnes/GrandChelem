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
    final int main;    //1 = droitier 2 = gaucher
    private String sponsor;
    private int classement;
    private String entraineur;
    private Habits habit;
    private Couleur couleurShort;
    private static int compteurJoueur;
    
    public Joueur(int genre, String nomNaissance, String prenom, int main, String sponsor, String entraineur, String nationalite){
        super(genre, nomNaissance, prenom);
        if (genre==2){
            habit=Habits.JUPE;
        }
        else{
            habit=Habits.SHORT;
        }
        this.main = main;
        this.sponsor = sponsor;
        this.entraineur = entraineur;
        this.couleurShort = Couleur.BLEU;   //Couleur par défaut pour un homme -> Bleu
        compteurJoueur++;
        this.classement = compteurJoueur;
        this.nationalite=nationalite;
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
    //Mutator
    public void setSponsor(String sponsor){
        this.sponsor = sponsor;
    }
    public void setClassement(int classement){
        this.classement = classement;
    }
    public void setEntraineur(String entraineur){
        this.entraineur = entraineur;
    }
    public void setCouleurShort(Couleur couleurShort){
        this.couleurShort = couleurShort;
    }

    @Override
    public String toString() {
        return prenom + " " + nomNaissance + " (" + nationalite + ')';
    }
    
    
}
