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
    int setJoueur1;
    int setJoueur2;
    int[][] scores;
    
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
        setJoueur2=0;
        setJoueur1=0;
        scores=new int[5][2];
        System.out.println(toString());
    }
    
    public Joueur jouerMatch(){
        while (!matchRemporte()){
            jouerSet();
        }
        printScores();
        System.out.println("Le gagnant est : " + gagnantMatch());
        return gagnantMatch();
    }
    
    public Joueur jouerSet(){
        Set set = new Set(joueur1, joueur2);
        while (!set.setRemporte()){
            set.jeuRemporte(set.jouerJeu());
        }
        setGagne(set.gagnantSet());
        System.out.print("Sets :");
        printScores();
        return set.gagnantSet();
    }
    
    public void setGagne(Joueur gagnant){
        if (gagnant==joueur1){
            setJoueur1++;
        }
        else{
            setJoueur2++;
        }
    }
    
    public boolean matchRemporte(){
        return setJoueur1 == 3 || setJoueur2 == 3;
    }
    
    public Joueur gagnantMatch(){
        if (setJoueur1>setJoueur2){
            return joueur1;
        }
        return joueur2;
    }
    
    public void printScores(){
        System.out.println(joueur1 + " " + setJoueur1 + " ; " + setJoueur2 + " " + joueur2);
    }

    @Override
    public String toString() {
        return "Le match opposera " + joueur1 + " qui commencera à servir, et " + joueur2 + ". Il sera arbitré par " + arbitre + '.';
    }
    
    
}
