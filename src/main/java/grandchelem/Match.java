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
        setJoueur2=0;
        setJoueur1=0;
        scores=new int[5][2];
        System.out.println(toString());
    }
    
    public Joueur jouerMatch(){
        Scanner saisieUtilisateur = new Scanner(System.in);
        System.out.println("Veuillez indiquer si vous souhaitez jouer ce match de façon automatique (0) (default) ou bien manuelle (1)");
        int input = saisieUtilisateur.nextInt();
        int i=0;
        while (!matchRemporte()){
            int[] tab=jouerSet(input);
            scores[i][0]=tab[0];
            scores[i][1]=tab[1];
            i++;
        }
        printScores();
        System.out.println("Le gagnant est : " + gagnantMatch());
        return gagnantMatch();
    }
    
    public int[] jouerSet(int mode){
        if (mode==1){
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Veuillez indiquer si vous souhaitez jouer ce set de façon automatique (0) (default) ou bien manuelle (1)");
            int input = saisieUtilisateur.nextInt();
            mode=input;
        }    
        int serveur;
        if (setJoueur1+setJoueur2%2==0){
            serveur=1;
        }
        else{
            serveur=2;
        }
        Set set = new Set(joueur1, joueur2, arbitre);
        while (!set.setRemporte()){
            set.jeuRemporte(set.jouerJeu(serveur, mode));
            if (serveur==1){
                serveur=2;
            }
            else{
                serveur=1;
            }
            System.out.println("Jeux : " + set.toString());
        }
        int[] tab = new int[2];
        tab[0]=set.jeuxJoueur1;
        tab[1]=set.jeuxJoueur2;
        setGagne(set.gagnantSet());
        return tab;
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
        System.out.println("");
        System.out.println(joueur1);
        for (int i=0 ; i<(setJoueur1+setJoueur2)*4+1 ; i++){
            System.out.print("_");
        }
        System.out.println("");
        for (int i=0 ; i<2 ; i++){
            for (int j=0 ; j<setJoueur1+setJoueur2 ; j++){
                System.out.print("| " + scores[j][i] + " ");
            }
            if (i==0){
                System.out.println("| --> " + setJoueur1 + " set(s) remportés");
            }
            else{
                System.out.println("| --> " + setJoueur2 + " set(s) remportés");
            }
            
        }
        for (int i=0 ; i<(setJoueur1+setJoueur2)*4+1 ; i++){
            System.out.print("¯");
        }
        System.out.println("");
        System.out.println(joueur2);
        System.out.println("");
    }

    @Override
    public String toString() {
        return "Le match opposera " + joueur1 + " qui commencera à servir, et " + joueur2 + ". Il sera arbitré par " + arbitre + '.';
    }
    
    
}
