/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
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
    ArrayList<Joueur> leClassement;
    Joueur gagnantTournoi;
    private static int compteurTournoi;
    int tournoiNumero;
    int etatTournoi;    //Nombre entre 0 et 7 illustrant l'avancé d'un tournoi (ex: 7, le tournoi est terminé.
    int genreTournoi; //1 --> Tournoi masculin  2 --> Tournoi Féminin
    
     /**
     * Constructeur d'un tournoi
     * @param listeJoueur
     * @param listeArbitre
     * @param listeSpectateur
     * @param genreTournoi Tournoi homme(1) ou femme(2)
     */
    public Tournoi(ArrayList<Joueur> listeJoueur, ArrayList<Arbitre> listeArbitre, ArrayList<Spectateur> listeSpectateur, int genreTournoi){
        this.listeJoueur = listeJoueur;
        this.listeArbitre = listeArbitre;
        this.listeSpectateur = listeSpectateur;
        this.genreTournoi = genreTournoi;
        compteurTournoi++;
        this.tournoiNumero = compteurTournoi;
        
    }
    
    /**
     * Un tournoi comporte 128 joueurs, disons qu'il comporte 25 arbitres.
     * Genere le reste des joueurs aléatoirement pour le tournoi s'il n'y a pas
     * 128 joueurs dans la liste déja inscrits.
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     * @return ArrayList<> des joueurs générés
     */
    public ArrayList<Joueur> genererJoueursTournoi() throws IOException{
        while (this.listeJoueur.size() != 128) {
            Joueur unjoueur = Menu.creationAutomatiqueJoueur(this.genreTournoi);
            this.listeJoueur.add(unjoueur);
        }
        System.out.println("Joueurs crées avec succès");
        //Après avoir générer les 128 joueurs, on les mélanges dans la liste.
        Collections.shuffle(listeJoueur);
        System.out.println("L'ordre des joueurs a été mélangé aléatoirement");
        this.leClassement = new ArrayList<>(this.listeJoueur);
        return this.listeJoueur;
    }
    /**
     * Genere le reste des arbitres aléatoirement pour le tournoi s'il n'y a pas
     * 25 arbitres dans la liste déja inscrits.
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     * @return ArrayList<> des arbitres générés
     */
    public ArrayList<Arbitre> genererArbitresTournoi() throws IOException{
        while (this.listeArbitre.size() != 25) {
            Arbitre unarbitre = Menu.creationAutomatiqueArbitre();
            this.listeArbitre.add(unarbitre);
        }
        System.out.println("Arbitres crées avec succès");
        return this.listeArbitre;
    }
    /**
     * Genere des spectateurs pour le tournoi si il n'y en a pas 10
     * la nombre de spectateur est ici fixé à 10 (ligne 98) pour des raison de rapidité d'execution,
     * generer 300 spectateur à partir de fichiers csv est plsu long.
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     * @return ArrayList<> des spectateurs générés
     */
    public ArrayList<Spectateur> genererSpectateursTournoi() throws IOException{
        System.out.println("Chargement des spectateurs");
        int k = 0;
        while (this.listeSpectateur.size() != 10) {
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
    /**
     * Lance le premier tour du tournoi
     * L'utilisateur à le choix de jouer cette poule en automatique
     * ou en manuelle
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     */
    public void jouerPremierTour() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer le premier tour de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<0 || choix>2){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");


                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
            
        if (choix == 0){
            System.out.println("");
            System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
            System.out.println("0) Résultats uniquement");
            System.out.println("1) Détail");
            choix = 0;
            do {
                erreur = false;
                try {
                    Scanner saisieUserDetail = new Scanner(System.in);
                    choix = saisieUserDetail.nextInt();
                    if (choix<0 || choix>1){
                        erreur = true;
                        System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }
                catch (InputMismatchException e){
                    erreur = true;
                    System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }while (erreur);
            int i = 0;
            for (int j=0; j< 64; j++) {
                int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                Match unmatch = new Match(this.listeJoueur.get(i), this.listeJoueur.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                Joueur gagnantMatch = unmatch.jouerMatch(0,choix);
                gagnantMatch.addVictoire();
                this.secondTour.add(gagnantMatch);
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
            choix = 0;
            do {
                erreur = false;
                try {
                    Scanner saisieUserDetail = new Scanner(System.in);
                    choix = saisieUserDetail.nextInt();
                    if (choix<1 || choix>2){
                        erreur = true;
                        System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }
                catch (InputMismatchException e){
                    erreur = true;
                    System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }while (erreur);
            if (choix == 1) this.jouerSecondTour();
            if (choix == 2) {
                this.etatTournoi = 1;
                classer();
                Menu.menuTournoi();
            }
        }
        if (choix == 1){
            int i = 0;
            for (int j=0; j< 64; j++) {
                int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                Match unmatch = new Match(this.listeJoueur.get(i), this.listeJoueur.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                Joueur gagnantMatch = unmatch.jouerMatch(1,1);
                gagnantMatch.addVictoire();
                this.secondTour.add(gagnantMatch);
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
            choix = 0;
            do {
                erreur = false;
                try {
                    Scanner saisieUser2 = new Scanner(System.in);
                    choix = saisieUser2.nextInt();
                    if (choix<1 || choix>2){
                        erreur = true;
                        System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }
                catch (InputMismatchException e){
                    erreur = true;
                    System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }while (erreur);
            if (choix == 1) this.jouerSecondTour();
            if (choix == 2) {
                this.etatTournoi = 1;
                classer();
                Menu.menuTournoi();
            }
        }
        if (choix == 2){
            this.etatTournoi = 0;
            Menu.menuTournoi();
        }
    }
    
    /**
     * Lance le deuxième tour du tournoi
     * L'utilisateur à le choix de jouer cette poule en automatique
     * ou en manuelle
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     */
    public void jouerSecondTour() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer le second tour de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<0 || choix>2){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<0 || choix>1){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                int i = 0;
                for (int j=0; j< 32; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.secondTour.get(i), this.secondTour.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(0,choix);
                    gagnantMatch.addVictoire();
                    this.seizièmeDeFinale.add(gagnantMatch);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerSeizièmesDeFinale();
                if (choix == 2) {
                    this.etatTournoi = 2;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 1->{
                int i = 0;
                for (int j=0; j< 32; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.secondTour.get(i), this.secondTour.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(1,1);
                    gagnantMatch.addVictoire();
                    this.seizièmeDeFinale.add(gagnantMatch);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser2 = new Scanner(System.in);
                        choix = saisieUser2.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerSeizièmesDeFinale();
                if (choix == 2) {
                    this.etatTournoi = 2;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 2 ->{
                this.etatTournoi = 1;
                classer();
                Menu.menuTournoi();
                break;
            }
        }
    }
    
    /**
     * Lance les seizièmes de finales du tournoi
     * L'utilisateur à le choix de jouer cette poule en automatique
     * ou en manuelle
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     */
    public void jouerSeizièmesDeFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer les seizièmes de finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<0 || choix>2){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<0 || choix>1){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                int i = 0;
                for (int j=0; j< 16; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.seizièmeDeFinale.get(i), this.seizièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(0,choix);
                    gagnantMatch.addVictoire();
                    this.huitièmeDeFinale.add(gagnantMatch);

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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerHuitièmesDeFinale();
                if (choix == 2) {
                    this.etatTournoi = 3;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 16; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.seizièmeDeFinale.get(i), this.seizièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(1,1);
                    gagnantMatch.addVictoire();
                    this.huitièmeDeFinale.add(gagnantMatch);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser2 = new Scanner(System.in);
                        choix = saisieUser2.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerHuitièmesDeFinale();
                if (choix == 2) {
                    this.etatTournoi = 3;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 2 ->{
                this.etatTournoi = 2;
                classer();
                Menu.menuTournoi();
                break;
            }
        }
    }
    
    /**
     * Lance les huitièmes de finales du tournoi
     * L'utilisateur à le choix de jouer cette poule en automatique
     * ou en manuelle
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     */
    public void jouerHuitièmesDeFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer les huitièmes de finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<0 || choix>2){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<0 || choix>1){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                int i = 0;
                for (int j=0; j< 8; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.huitièmeDeFinale.get(i), this.huitièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(0,choix);
                    gagnantMatch.addVictoire();
                    this.quartDeFinale.add(gagnantMatch);

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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerQuartDeFinale();
                if (choix == 2) {
                    this.etatTournoi = 4;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 8; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.huitièmeDeFinale.get(i), this.huitièmeDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(1,1);
                    gagnantMatch.addVictoire();
                    this.quartDeFinale.add(gagnantMatch);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser2 = new Scanner(System.in);
                        choix = saisieUser2.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerQuartDeFinale();
                if (choix == 2) {
                    this.etatTournoi = 4;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 2 ->{
                this.etatTournoi = 3;
                classer();
                Menu.menuTournoi();
                break;
            }
        }
    }
    
    /**
     * Lance les quarts de finales du tournoi
     * L'utilisateur à le choix de jouer cette poule en automatique
     * ou en manuelle
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     */
    public void jouerQuartDeFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer les quarts de finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<0 || choix>2){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<0 || choix>1){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                int i = 0;
                for (int j=0; j< 4; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                    Match unmatch = new Match(this.quartDeFinale.get(i), this.quartDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(0,choix);
                    gagnantMatch.addVictoire();
                    this.demiFinale.add(gagnantMatch);

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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerDemiFinale();
                if (choix == 2) {
                    this.etatTournoi = 5;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 4; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.quartDeFinale.get(i), this.quartDeFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(1,1);
                    gagnantMatch.addVictoire();
                    this.demiFinale.add(gagnantMatch);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser2 = new Scanner(System.in);
                        choix = saisieUser2.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerDemiFinale();
                if (choix == 2) {
                    this.etatTournoi = 5;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 2 ->{
                this.etatTournoi = 4;
                classer();
                Menu.menuTournoi();
                break;
            }
        }
    }
    
    /**
     * Lance les demi-finales du tournoi
     * L'utilisateur à le choix de jouer cette poule en automatique
     * ou en manuelle
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     */
    public void jouerDemiFinale() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer la demi finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<0 || choix>2){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<0 || choix>1){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                int i = 0;
                for (int j=0; j< 2; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.demiFinale.get(i), this.demiFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(0,choix);
                    gagnantMatch.addVictoire();
                    this.finale.add(gagnantMatch);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerFinale();
                if (choix == 2) {
                    this.etatTournoi = 6;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 1 ->{
                int i = 0;
                for (int j=0; j< 2; j++) {
                    int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                    Match unmatch = new Match(this.demiFinale.get(i), this.demiFinale.get(i+1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                    Joueur gagnantMatch = unmatch.jouerMatch(1,1);
                    gagnantMatch.addVictoire();
                    this.finale.add(gagnantMatch);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser2 = new Scanner(System.in);
                        choix = saisieUser2.nextInt();
                        if (choix<1 || choix>2){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 1) this.jouerFinale();
                if (choix == 2) {
                    this.etatTournoi = 6;
                    classer();
                    Menu.menuTournoi();
                }
                break;
            }
            case 2 ->{
                this.etatTournoi = 5;
                classer();
                Menu.menuTournoi();
                break;
            }
        }
    }
    /**
     * Lance la finale du tournoi
     * L'utilisateur à le choix de jouer cette poule en automatique
     * ou en manuelle
     * @throws IOException retourne une erreur si il y a un problème avec le traitement
     * des fichiers
     */
    public void jouerFinale()throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous jouer la finale de façon manuel ou automatique ?");
        System.out.println("0) Automatique");
        System.out.println("1) Manuel (vous aurez encore l'option de choisir pour chaque match)");
        System.out.println("2) Retourner au menu Tournoi");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<0 || choix>2){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
        switch (choix){
            case 0 -> {
                System.out.println("");
                System.out.println("Voulez-vous avoir le détail point par point ou uniquement le résultat des matchs ?");
                System.out.println("0) Résultats uniquement");
                System.out.println("1) Détail");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix<0 || choix>1){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;

                Match unmatch = new Match(this.finale.get(0), this.finale.get(1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                Joueur gagnantMatch = unmatch.jouerMatch(0,choix);
                gagnantMatch.addVictoire();
                this.gagnantTournoi = gagnantMatch;
                this.listeJoueur.get(0).listeMatch.add(unmatch);
                this.listeJoueur.get(1).listeMatch.add(unmatch);
                System.out.println("");
                System.out.println("Le gagnant du tournoi est : " + this.gagnantTournoi + " Bravo !!!");
                System.out.println("");
                System.out.println("Retour au menu Tournoi");
                this.etatTournoi = 7;
                classer();
                Menu.menuTournoi();
                break;
            }
            case 1 ->{
                int randomArbitre = (int)(Math.random() * (25 - 0)) + 0;
                Match unmatch = new Match(this.finale.get(0), this.finale.get(1), this.listeArbitre.get(randomArbitre), this.genreTournoi);
                Joueur gagnantMatch = unmatch.jouerMatch(1,1);
                gagnantMatch.addVictoire();
                this.gagnantTournoi = gagnantMatch;
                this.listeJoueur.get(0).listeMatch.add(unmatch);
                this.listeJoueur.get(1).listeMatch.add(unmatch);
                System.out.println("");
                System.out.println("Le gagnant du tournoi est : " + this.gagnantTournoi + " Bravo !!!");
                System.out.println("");
                System.out.println("Retour au menu Tournoi");
                classer();
                this.etatTournoi = 7;
                Menu.menuTournoi();
                break;
            }
            case 2 ->{
                this.etatTournoi = 6;
                classer();
                Menu.menuTournoi();
                break;
            }
        }
    }
    
    /**
     * Lance le tournoi en appellant les méthodes pour générer les personnes nécessaires
     * @throws IOException si il y a une erreur dans la lecture des
     * fichiers .csv
     */
    public void startTournoi() throws IOException{
        genererJoueursTournoi();
        genererArbitresTournoi();
        genererSpectateursTournoi();         
    }
    /**
     * Permet de reprendre le tournoi la où il s'était arrété graca à la variable
     * d'instance etatTournoi qui va de 0 à 7, 7 étant un tournoi terminé
     * @throws IOException IOException si il y a une erreur dans la lecture des
     * fichiers .csv
     */
    public void reprendreTournoi() throws IOException{
        switch (this.etatTournoi) {
            case 0 -> {
                jouerPremierTour();
                break;
            }
            case 1 -> {
                jouerSecondTour();
                break;
            }
            case 2 -> {
                jouerSeizièmesDeFinale();
                break;
            }
            case 3 -> {
                jouerHuitièmesDeFinale();
                break;
            }
            case 4 -> {
                jouerQuartDeFinale();
                break;
            }
            case 5 -> {
                jouerDemiFinale();
                break;
            }
            case 6 -> {
                jouerFinale();
                break;
            }
            case 7 -> {
                System.out.println("");
                System.out.println("/!\\ /!\\ /!\\ Ce tournoi est terminé, vous pouvez tout de même aller voir ses informations dans le menu tournoi. /!\\ /!\\ /!\\");
                Menu.menuTournoi();
                break;
            }
        }
    }
    
    /**
     * Méthode appeller à chaque fin de poules pour trier les classement des joueurs
     * en fonction de leurs nombres de victoires.
     * Création d'un comparateur personalisé et utilisation de la fonction sort()
     * du package Collections
     */
    public void classer() {
        Collections.sort(this.leClassement, new Comparator<Joueur>(){
            @Override
            public int compare(Joueur j1, Joueur j2) {
                if (j1.getNombreVictoire() > j2.getNombreVictoire()){
                    return -1;
                }
                if (j1.getNombreVictoire() < j2.getNombreVictoire()){
                    return +1;
                }
                else {
                    return 0;
                }
            }
        });
        
        for (int k = 0; k< this.leClassement.size(); k++){
            this.leClassement.get(k).setClassement(k+1);
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
    
    public ArrayList<Joueur> getLeClassement(){
        return this.leClassement;
    }
        
}
