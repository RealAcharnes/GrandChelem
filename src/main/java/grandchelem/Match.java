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
    int sexe;//1=homme, 2=femme
    
    /**
     * Création d'un match
     * @param joueur1
     * @param joueur2
     * @param arbitre
     * @param sexe match homme(1) ou femme(2)
     */
    public Match(Joueur joueur1, Joueur joueur2, Arbitre arbitre, int sexe){
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

        this.sexe=sexe;
        this.setJoueur2=0;
        this.setJoueur1=0;
        if (sexe==2){
            scores=new int[3][2];
        }
        else{
            scores=new int[5][2];
        }
        System.out.println(toString());
    }
    
    /**
     * Jouer un match 
     * @param mode manuel (1) ou automatique (0)
     * @param affichage affichage du détail point par point oui (1) ou non (0)
     * @return 
     */
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
    
    /**
     * Jouer un set du match
     * @param mode manuel (1) ou automatique (0)
     * @param affichage affichage du détail point par point oui (1) ou non (0)
     * @return 
     */
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
    
    /**
     * Permet de mettre à jour le nombre de set de chaque joueur
     * @param gagnant le gagnant du set écoulé
     */
    public void setGagne(Joueur gagnant){
        if (gagnant==joueur1){
            this.setJoueur1++;
        }
        else{
            this.setJoueur2++;
        }
    }
    
    /**
     * Permet de savoir si le match a été remporté
     * @return true si le match a été remporté, false sinon
     */
    public boolean matchRemporte(){

        if(sexe==2){
            return setJoueur1 == 2 || setJoueur2 == 2;
        }
        else{
            return setJoueur1 == 3 || setJoueur2 == 3;
        }  
    }
    
    /**
     * Permet de connaitre le gagnant du match lorsque le match a déjà été remporté
     * @return le gagnant du match
     */
    public Joueur gagnantMatch(){
        if (this.setJoueur1>this.setJoueur2){
            return this.joueur1;
        }
        return this.joueur2;
    }
    
    /**
     * Affichage final des scores sous forme de tableau
     */
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
