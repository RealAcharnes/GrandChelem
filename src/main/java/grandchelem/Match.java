/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.util.Scanner;

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
        this.setJoueur2=0;
        this.setJoueur1=0;
        this.scores=new int[5][2];
        System.out.println(toString());
    }
    
    public Joueur jouerMatch(int mode, int affichage){
        if (mode == 0) {
            int i = 0;
            while (!matchRemporte()){
                int[] tab=jouerSet(mode, affichage);
                this.scores[i][0]=tab[0];
                this.scores[i][1]=tab[1];
                i++;
            }
            printScores();
            System.out.println("Le gagnant est : " + gagnantMatch());
            return gagnantMatch();
        }
        else {
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Veuillez indiquer si vous souhaitez jouer ce match de façon automatique (0) (default) ou bien manuelle (1)");
            int input = saisieUtilisateur.nextInt();
            int i=0;
            while (!matchRemporte()){
                int[] tab=jouerSet(input,1);
                this.scores[i][0]=tab[0];
                this.scores[i][1]=tab[1];
                i++;
            }
            printScores();
            System.out.println("Le gagnant est : " + gagnantMatch());
            return gagnantMatch();
        }
        
    }
    
    public int[] jouerSet(int mode, int affichage){
        if (mode==1){
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Veuillez indiquer si vous souhaitez jouer ce set de façon automatique (0) (default) ou bien manuelle (1)");
            int input = saisieUtilisateur.nextInt();
            mode=input;
        }    
        int serveur;
        if (this.setJoueur1+this.setJoueur2%2==0){
            serveur=1;
        }
        else{
            serveur=2;
        }
        Set set = new Set(this.joueur1, this.joueur2, this.arbitre);
        while (!set.setRemporte()){
            set.jeuRemporte(set.jouerJeu(serveur, mode, affichage));
            if (serveur==1){
                serveur=2;
            }
            else{
                serveur=1;
            }
            if(affichage==1){
                System.out.println("Jeux : " + set.toString());
            }  
        }
        int[] tab = new int[2];
        tab[0]=set.jeuxJoueur1;
        tab[1]=set.jeuxJoueur2;
        setGagne(set.gagnantSet());
        return tab;
    }
    
    public void setGagne(Joueur gagnant){
        if (gagnant==joueur1){
            this.setJoueur1++;
        }
        else{
            this.setJoueur2++;
        }
    }
    
    public boolean matchRemporte(){
        return this.setJoueur1 == 3 || setJoueur2 == 3;
    }
    
    public Joueur gagnantMatch(){
        if (this.setJoueur1>this.setJoueur2){
            return this.joueur1;
        }
        return this.joueur2;
    }
    
    public void printScores(){
        System.out.println("");
        System.out.println(this.joueur1);
        for (int i=0 ; i<(this.setJoueur1+this.setJoueur2)*4+1 ; i++){
            System.out.print("_");
        }
        System.out.println("");
        for (int i=0 ; i<2 ; i++){
            for (int j=0 ; j<this.setJoueur1+this.setJoueur2 ; j++){
                System.out.print("| " + this.scores[j][i] + " ");
            }
            if (i==0){
                System.out.println("| --> " + this.setJoueur1 + " set(s) remportés");
            }
            else{
                System.out.println("| --> " + this.setJoueur2 + " set(s) remportés");
            }
            
        }
        for (int i=0 ; i<(this.setJoueur1+this.setJoueur2)*4+1 ; i++){
            System.out.print("¯");
        }
        System.out.println("");
        System.out.println(this.joueur2);
        System.out.println("");
    }

    @Override
    public String toString() {
        return "Le match opposera " + this.joueur1 + " qui commencera à servir, et " + this.joueur2 + ". Il sera arbitré par " + this.arbitre + '.';
    }
    
    public int[][] getScore(){
        return this.scores;
    }
    
}
