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
    public String sponsor;
    public int classement;
    public String entraineur;
    public Couleur couleurJupe;
    
    public Joueuse(int genre, String nomNaissance, String prenom, int main, String sponsor, int classement, String entraineur){
        super(genre, nomNaissance, prenom);
        this.main = main;
        this.sponsor = sponsor;
        this.classement = classement;
        this.entraineur = entraineur;
        this.couleurJupe = Couleur.ROSE;   //Couleur par défaut pour une femmme -> Rose
    }
    
    public int getMain(){
        return this.main;
    }
}
