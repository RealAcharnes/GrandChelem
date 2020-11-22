/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author jeanc
 */
public class Tournoi {
    ArrayList<Joueur> listeJoueur;
    ArrayList<Arbitre> listeArbitre;
    ArrayList<Spectateur> listeSpectateur;
    ArrayList<Joueur> secondTour = new ArrayList<>();
    ArrayList<Joueur> seizièmeDeFinale = new ArrayList<>();
    ArrayList<Joueur> huitièmeDeFinale = new ArrayList<>();
    ArrayList<Joueur> quartDeFinale = new ArrayList<>();
    ArrayList<Joueur> demiFinale = new ArrayList<>();
    ArrayList<Joueur> finale = new ArrayList<>();
    Joueur gagnantTournoi;
    private static int compteurTournoi;
    int tournoiNumero;
    int etatTournoi;    //Nombre entre 0 et 7 illustrant l'avancé d'un tournoi (ex: 7, le tournoi est terminé.
    int genreTournoi; //1 --> Tournoi masculin  2 --> Tournoi Féminin
    
    public Tournoi(ArrayList<Joueur> listeJoueur, ArrayList<Arbitre> listeArbitre, ArrayList<Spectateur> listeSpectateur, int genreTournoi){
        this.listeJoueur = listeJoueur;
        this.listeArbitre = listeArbitre;
        this.listeSpectateur = listeSpectateur;
        this.genreTournoi = genreTournoi;
        compteurTournoi++;
        this.tournoiNumero = compteurTournoi;
        
    }
    
    //Un tournoi comporte 128 joueurs, disons qu'il comporte 25 arbitres.
    public ArrayList<Joueur> genererJoueursTournoi() throws IOException{
        while (this.listeJoueur.size() != 128) {
            Joueur unjoueur = Menu.creationAutomatiqueJoueur(this.genreTournoi);
            this.listeJoueur.add(unjoueur);
        }
        System.out.println("Joueurs crées avec succès");
        //Après avoir générer les 128 joueurs, on les mélanges dans la liste.
        Collections.shuffle(listeJoueur);
        System.out.println("L'ordre des joueurs a été mélangé aléatoirement");
        return this.listeJoueur;
    }
    
    public ArrayList<Arbitre> genererArbitresTournoi() throws IOException{
        while (this.listeArbitre.size() != 25) {
            Arbitre unarbitre = Menu.creationAutomatiqueArbitre();
            this.listeArbitre.add(unarbitre);
        }
        System.out.println("Arbitres crées avec succès");
        return this.listeArbitre;
    }
    
    public ArrayList<Spectateur> genererSpectateursTournoi() throws IOException{
        System.out.println("Chargement des spectateurs");
        int k = 0;
        while (this.listeSpectateur.size() != 5) {
            Spectateur unspectateur = Menu.creationAutomatiqueSpectateur();
            this.listeSpectateur.add(unspectateur);
            k ++;
            System.out.print("#");
            if (k % 80 == 0) System.out.println("");
        }
        System.out.println(" 100%");
        System.out.println("Spectateurs crées avec succès");
        return this.listeSpectateur;
    }
    
    public void jouerPremierTour() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer le premier tour de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                Scanner saisieUserDetail = new Scanner(System.in);
                int choixDetail = saisieUserDetail.nextInt();
                int i = 0;
                for (int j=0; j< 64; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.listeJoueur.get(i), this.listeJoueur.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.secondTour.add(unmatch.jouerMatch(0,choixDetail));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs du second tour :");
                for (int k = 0; k< this.secondTour.size(); k++){
                    System.out.println(this.secondTour.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers le second tour ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers le second tour");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerSecondTour();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 64; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.listeJoueur.get(i), this.listeJoueur.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.secondTour.add(unmatch.jouerMatch(1,1));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs du second tour :");
                for (int k = 0; k< this.secondTour.size(); k++){
                    System.out.println(this.secondTour.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers le second tour ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers le second tour");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerSecondTour();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 2 ->{
                this.etatTournoi = 0;
                Menu.menuTournoi();
            }
        }
    }
    
    public void jouerSecondTour() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer le second tour de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                Scanner saisieUserDetail = new Scanner(System.in);
                int choixDetail = saisieUserDetail.nextInt();
                int i = 0;
                for (int j=0; j< 32; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.secondTour.get(i), this.secondTour.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.seizièmeDeFinale.add(unmatch.jouerMatch(0,choixDetail));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs des seizièmes de finale :");
                for (int k = 0; k< this.seizièmeDeFinale.size(); k++){
                    System.out.println(this.seizièmeDeFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les seizièmes de finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les seizièmes de finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerSeizièmesDeFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 1->{
                int i = 0;
                for (int j=0; j< 32; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.secondTour.get(i), this.secondTour.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.seizièmeDeFinale.add(unmatch.jouerMatch(1,1));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs des seizièmes de finale :");
                for (int k = 0; k< this.seizièmeDeFinale.size(); k++){
                    System.out.println(this.seizièmeDeFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les seizièmes de finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les seizièmes de finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerSeizièmesDeFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 2 ->{
                this.etatTournoi = 1;
                Menu.menuTournoi();
            }
        }
    }
    
    public void jouerSeizièmesDeFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer les seizièmes de finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                Scanner saisieUserDetail = new Scanner(System.in);
                int choixDetail = saisieUserDetail.nextInt();
                int i = 0;
                for (int j=0; j< 16; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.seizièmeDeFinale.get(i), this.seizièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.huitièmeDeFinale.add(unmatch.jouerMatch(0,choixDetail));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs des huitièmes de finale :");
                for (int k = 0; k< this.huitièmeDeFinale.size(); k++){
                    System.out.println(this.huitièmeDeFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les huitièmes de finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les huitièmes de finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerHuitièmesDeFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 16; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.seizièmeDeFinale.get(i), this.seizièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.huitièmeDeFinale.add(unmatch.jouerMatch(1,1));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs des huitièmes de finale :");
                for (int k = 0; k< this.huitièmeDeFinale.size(); k++){
                    System.out.println(this.huitièmeDeFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les huitièmes de finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les huitièmes de finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerHuitièmesDeFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 2 ->{
                this.etatTournoi = 2;
                Menu.menuTournoi();
            }
        }
    }
    
    public void jouerHuitièmesDeFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer les huitièmes de finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                Scanner saisieUserDetail = new Scanner(System.in);
                int choixDetail = saisieUserDetail.nextInt();
                int i = 0;
                for (int j=0; j< 8; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.huitièmeDeFinale.get(i), this.huitièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.quartDeFinale.add(unmatch.jouerMatch(0,choixDetail));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs des quarts de finale :");
                for (int k = 0; k< this.quartDeFinale.size(); k++){
                    System.out.println(this.quartDeFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les quarts de finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les quarts de finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerQuartDeFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 8; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.huitièmeDeFinale.get(i), this.huitièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.quartDeFinale.add(unmatch.jouerMatch(1,1));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs des quarts de finale :");
                for (int k = 0; k< this.quartDeFinale.size(); k++){
                    System.out.println(this.quartDeFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les quarts de finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les quarts de finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerQuartDeFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 2 ->{
                this.etatTournoi = 3;
                Menu.menuTournoi();
            }
        }
    }
    
    public void jouerQuartDeFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer les quarts de finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                Scanner saisieUserDetail = new Scanner(System.in);
                int choixDetail = saisieUserDetail.nextInt();
                int i = 0;
                for (int j=0; j< 4; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.quartDeFinale.get(i), this.quartDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.demiFinale.add(unmatch.jouerMatch(0,choixDetail));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs de la demi finale :");
                for (int k = 0; k< this.demiFinale.size(); k++){
                    System.out.println(this.demiFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les demi finales ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les demi finales");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerDemiFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 4; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.quartDeFinale.get(i), this.quartDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.demiFinale.add(unmatch.jouerMatch(1,1));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs de la demi finale :");
                for (int k = 0; k< this.demiFinale.size(); k++){
                    System.out.println(this.demiFinale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers les demi finales ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers les demi finales");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerDemiFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 2 ->{
                this.etatTournoi = 4;
                Menu.menuTournoi();
            }
        }
    }
    
    public void jouerDemiFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer la demi finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                Scanner saisieUserDetail = new Scanner(System.in);
                int choixDetail = saisieUserDetail.nextInt();
                int i = 0;
                for (int j=0; j< 2; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.demiFinale.get(i), this.demiFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.finale.add(unmatch.jouerMatch(0,choixDetail));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs de la finale :");
                for (int k = 0; k< this.finale.size(); k++){
                    System.out.println(this.finale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers la finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers la finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 2; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.demiFinale.get(i), this.demiFinale.get(i+1), this.listeArbitre.get(randomArbitre), genreTournoi);
                    this.finale.add(unmatch.jouerMatch(1,1));
                    this.listeJoueur.get(i).listeMatch.add(unmatch);
                    this.listeJoueur.get(i+1).listeMatch.add(unmatch);
                    i += 2;
                }
                System.out.println("");
                System.out.println("Liste des joueurs de la finale :");
                for (int k = 0; k< this.finale.size(); k++){
                    System.out.println(this.finale.get(k));
                }
                System.out.println("");
                System.out.println("Voulez vous continuer vers la finale ou retourner au menu Tournoi ?");
                System.out.println("1) Continuer vers la finale");
                System.out.println("2) Retourner au menu Tournoi");
                Scanner saisieUser2 = new Scanner(System.in);
                int choix2 = saisieUser2.nextInt();
                if (choix2 == 1) this.jouerFinale();
                if (choix2 == 2) Menu.menuTournoi();
            }
            case 2 ->{
                this.etatTournoi = 5;
                Menu.menuTournoi();
            }
        }
    }
    
    public void jouerFinale()throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer la finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                Scanner saisieUserDetail = new Scanner(System.in);
                int choixDetail = saisieUserDetail.nextInt();
                int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                Match unmatch = new Match(this.demiFinale.get(0), this.demiFinale.get(1), this.listeArbitre.get(randomArbitre), genreTournoi);
                this.gagnantTournoi = unmatch.jouerMatch(0,choixDetail);
                this.listeJoueur.get(0).listeMatch.add(unmatch);
                this.listeJoueur.get(1).listeMatch.add(unmatch);
                System.out.println("");
                System.out.println("Le gagnant du tournoi est : " + this.gagnantTournoi + " Bravo !!!");
                System.out.println("");
                System.out.println("Retour au menu Tournoi");
                Menu.menuTournoi();
            }
            case 1 ->{
                int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                Match unmatch = new Match(this.demiFinale.get(0), this.demiFinale.get(1), this.listeArbitre.get(randomArbitre), genreTournoi);
                this.gagnantTournoi = unmatch.jouerMatch(1,1);
                this.listeJoueur.get(0).listeMatch.add(unmatch);
                this.listeJoueur.get(1).listeMatch.add(unmatch);
                System.out.println("");
                System.out.println("Le gagnant du tournoi est : " + this.gagnantTournoi + " Bravo !!!");
                System.out.println("");
                System.out.println("Retour au menu Tournoi");
                this.etatTournoi = 7;
                Menu.menuTournoi();
            }
            case 2 ->{
                this.etatTournoi = 6;
                Menu.menuTournoi();
            }
        }
    }
    
    public void startTournoi() throws IOException{
        genererJoueursTournoi();
        genererArbitresTournoi();
        genererSpectateursTournoi();         
    }
    
    public void reprendreTournoi(int etatTournoi) throws IOException{
        switch (etatTournoi) {
            case 0 -> {jouerPremierTour();}
            case 1 -> {jouerSecondTour();}
            case 2 -> {jouerSeizièmesDeFinale();}
            case 3 -> {jouerHuitièmesDeFinale();}
            case 4 -> {jouerQuartDeFinale();}
            case 5 -> {jouerDemiFinale();}
            case 6 -> {jouerFinale();}
            case 7 -> {
                System.out.println("Ce tournoi est terminé, vous pouvez tout de même aller voir ses informations dans le menu tournoi.");
                Menu.menuTournoi();
            }
        }
    }
    
    //Accessors
    public int getTournoiNumero(){
        return this.tournoiNumero;
    }
    
    public ArrayList<Joueur> getListeJoueur(){
        return this.listeJoueur;
    }
    
    public ArrayList<Arbitre> getListeArbitre(){
        return this.listeArbitre;
    }
    
    public ArrayList<Spectateur> getListeSpectateur(){
        return this.listeSpectateur;
    }
    
}
