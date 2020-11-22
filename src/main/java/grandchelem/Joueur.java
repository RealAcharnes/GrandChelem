/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.util.ArrayList;

/**
 *
 * @author jeanc
 */
public class Joueur extends Personne {
    final int main;    //1 = droitier 2 = gaucher
    private String sponsor;
    private int classement;
    private String entraineur;
    final Habits habit;
    private Couleur couleur;
    private static int compteurJoueur;
    private String nationalite;
    private int nombreVictoire;
    ArrayList<Match> listeMatch = new ArrayList<Match>();
        
    public Joueur(int genre, String nomNaissance, String prenom, int main, String sponsor, String entraineur, String nationalite){
        super(genre, nomNaissance, prenom);
        if (genre==2){                      //Habits et couleurs par défaut
            this.habit=Habits.JUPE;
            this.couleur = Couleur.NOIR;
        }
        else{
            this.habit=Habits.SHORT;
            this.couleur = Couleur.BLANC;
        }
        this.main = main;
        this.sponsor = sponsor;
        this.entraineur = entraineur;
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
    public Couleur getCouleur(){
        return this.couleur;
    }
    public ArrayList<Match> getListeMatch(){
        return this.listeMatch;
    }
    public int getNombreVictoire(){
        return this.nombreVictoire;
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
    public void setCouleur(Couleur couleur){
        this.couleur = couleur;
        String entete = toString();
        if (this.genre == Genre.HOMME){
            System.out.println(entete + " : Change la couleur de son short en " + this.couleur);
        }
        if (this.genre == Genre.FEMME){
            System.out.println(entete + " : Change la couleur de sa jupe en " + this.couleur);
        }
    }
    
    public void addVictoire(){
        this.nombreVictoire ++;
    }

    @Override
    public String toString() {
        return this.prenom + " " + this.nomNaissance + " (" + this.nationalite + ')';
    }
    
    
}
