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
public class Joueuse extends Personne {
    public int main;    //1 = droitier 2 = gaucher
    private String sponsor;
    private int classement;
    private String entraineur;
    private Couleur couleurJupe;
    private static int compteurJoueuse;
    
    public Joueuse(int genre, String nomNaissance, String prenom, int main, String sponsor, String entraineur){
        super(genre, nomNaissance, prenom);
        this.main = main;
        this.sponsor = sponsor;
        this.entraineur = entraineur;
        this.couleurJupe = Couleur.ROSE;   //Couleur par défaut pour une femmme -> Rose
        compteurJoueuse ++;
        this.classement = compteurJoueuse;
    }
    //Acccessor
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
    public Couleur getCouleurJupe(){
        return this.couleurJupe;
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
    public void setCouleurJupe(Couleur couleurJupe){
        this.couleurJupe = couleurJupe;
    }
}
