/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/**
 *
 * @author jeanc
 */
public class Menu {
    
    static ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
    static ArrayList<Arbitre> listeArbitre = new ArrayList<Arbitre>();
    static ArrayList<Spectateur> listeSpectateur = new ArrayList<Spectateur>();
    static ArrayList<Tournoi> listeTournoi = new ArrayList<Tournoi>();
    
    
    public Menu(){
        System.out.println("Bonjour et bienvenu, dans les menus qui vont suivre, lorqu'une proposition est précédée ou suivie d'un chiffre, veuillez répondre par le chiffre correspondant.");
        System.out.println("");
        System.out.println("Exemple : 1) Oui -> Il faut entrer 1 pour répondre Oui");
        System.out.println("Autre exemple : Oui(1) -> Il faut entrer 1 pour répondre Oui");
        System.out.println("");
    }
    
    public static int StartMenu() throws IOException {
        System.out.println("");
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1) Créer une personne");
        System.out.println("2) Tournoi");
        System.out.println("3) Quitter");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 1 -> {
                menuCreation();
            }
            case 2 -> {
                menuTournoi();
            }
            case 3 -> {
                return 1;
            }
        }
        return 1;
    }
    
    public static void menuCreation() throws IOException {
        System.out.println("");
        System.out.println("Quelle personne voulez-vous créer ?");
        System.out.println("1) Un joueur");
        System.out.println("2) Un arbitre");
        System.out.println("3) Une personne du public");
        System.out.println("4) Retour");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix) {
            case 1 -> menuCreationJoueur();
            case 2 -> menuCreationArbitre();
            case 3 -> menuCreationPublic();
            case 4 -> StartMenu();
        }
    }
    
    public static Joueur creationAutomatiqueJoueur() throws IOException {
        int genre = (int)(Math.random() * (3 - 1)) + 1;
        int main = (int)(Math.random() * (3 - 1)) + 1;
        BufferedReader inNom = null;
        BufferedReader inPrenom = null;
        BufferedReader inSponsor= null;
        BufferedReader inEntraineur = null;
        BufferedReader inNationalite = null;
        int ligneRandomNom = (int)(Math.random() * (878923 - 1)) + 1;
        int ligneRandomPrenom = (int)(Math.random() * (205894 - 1)) + 1;
        int ligneRandomSponsor = (int)(Math.random() * (11 - 1)) + 1;
        int ligneRandomEntraineur = (int)(Math.random() * (27 - 1)) + 1;
        int ligneRandomNationalite = (int)(Math.random() * (17 - 1)) + 1;
        try {
            inNom = new BufferedReader(new FileReader("patronymes.csv", StandardCharsets.UTF_8));
            inPrenom = new BufferedReader(new FileReader("prenom.csv", StandardCharsets.UTF_8));
            inSponsor = new BufferedReader(new FileReader("sponsors.csv", StandardCharsets.UTF_8));
            inEntraineur = new BufferedReader(new FileReader("entraineurs.csv", StandardCharsets.UTF_8));
            inNationalite = new BufferedReader(new FileReader("nationalites.csv", StandardCharsets.UTF_8));
            for (int i = 1; i<ligneRandomNom; i++) {
                inNom.readLine();
            }
            String nom = inNom.readLine().split(",")[0];
            for (int i = 1; i<ligneRandomPrenom; i++) {
                inPrenom.readLine();
            }
            String prenom = inPrenom.readLine().split(",")[0];
            String sponsor;
            if (ligneRandomSponsor == 1){
                sponsor = inSponsor.readLine().substring(1);
            }
            else {
                for (int i = 1; i<ligneRandomSponsor; i++) {
                    inSponsor.readLine();
                }
                sponsor = inNationalite.readLine();
            }
            for (int i = 1; i<ligneRandomEntraineur; i++) {
                inEntraineur.readLine();
            }
            String entraineur = inEntraineur.readLine();
            String nationalite;
            if (ligneRandomNationalite == 1){
                nationalite = inNationalite.readLine().substring(1);
            }
            else {
                for (int i = 1; i<ligneRandomNationalite; i++) {
                    inNationalite.readLine();
                }
                nationalite = inNationalite.readLine();
            }
            Joueur unjoueur = new Joueur(genre,nom,prenom,main,sponsor,entraineur,nationalite);
            return unjoueur;
        }
        finally {
                if (inNom != null)inNom.close();
                if (inPrenom != null)inPrenom.close();
                if (inSponsor != null)inSponsor.close();
                if (inEntraineur != null)inEntraineur.close();
                if (inNationalite != null)inNationalite.close();
        }
    }
    
    public static Arbitre creationAutomatiqueArbitre() throws IOException {
        int genre = (int)(Math.random() * (3 - 1)) + 1;
        BufferedReader inNom = null;
        BufferedReader inPrenom = null;
        int ligneRandomNom = (int)(Math.random() * (878923 - 1)) + 1;
        int ligneRandomPrenom = (int)(Math.random() * (205894 - 1)) + 1;
        try {
            inNom = new BufferedReader(new FileReader("patronymes.csv", StandardCharsets.UTF_8));
            inPrenom = new BufferedReader(new FileReader("prenom.csv", StandardCharsets.UTF_8));
            for (int i = 1; i<ligneRandomNom; i++) {
                inNom.readLine();
            }
            String nom = inNom.readLine().split(",")[0];
            for (int i = 1; i<ligneRandomPrenom; i++) {
                inPrenom.readLine();
            }
            String prenom = inPrenom.readLine().split(",")[0];
            Arbitre unarbitre = new Arbitre(genre,nom,prenom);
            return unarbitre;
        }
        finally {
            if (inNom != null)inNom.close();
            if (inPrenom != null)inPrenom.close();
        } 
    }
    
    public static Spectateur creationAutomatiqueSpectateur() throws IOException {
        int genre = (int)(Math.random() * (3 - 1)) + 1;
        BufferedReader inNom = null;
        BufferedReader inPrenom = null;
        int ligneRandomNom = (int)(Math.random() * (878923 - 1)) + 1;
        int ligneRandomPrenom = (int)(Math.random() * (205894 - 1)) + 1;
        try {
            inNom = new BufferedReader(new FileReader("patronymes.csv", StandardCharsets.UTF_8));
            inPrenom = new BufferedReader(new FileReader("prenom.csv", StandardCharsets.UTF_8));
            for (int i = 1; i<ligneRandomNom; i++) {
                inNom.readLine();
            }
            String nom = inNom.readLine().split(",")[0];
            for (int i = 1; i<ligneRandomPrenom; i++) {
                inPrenom.readLine();
            }
            String prenom = inPrenom.readLine().split(",")[0];
            Spectateur unspectateur = new Spectateur(genre,nom,prenom);
            return unspectateur;
        }
        finally {
            if (inNom != null)inNom.close();
            if (inPrenom != null)inPrenom.close();
        }
    }
    
    public static void menuCreationJoueur() throws IOException {
        System.out.println("");
        System.out.println("Voulez-vous créer un joueur de facon automatique ou manuel ?");
        System.out.println("1) Automatique");
        System.out.println("2) Manuel");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix) {
            case 1 ->{
                Joueur unjoueur = creationAutomatiqueJoueur();
                System.out.println("");
                System.out.println("Joueur crée : " + unjoueur.toString());
                listeJoueur.add(unjoueur);
                System.out.println("");
                System.out.println("Liste des joueurs");
                for (int j = 0; j< listeJoueur.size(); j++){
                    System.out.println(listeJoueur.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée un autre joueur ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                int choix2 = saisieUser.nextInt();
                if (choix2 == 1) menuCreationJoueur();
                if (choix2 == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                }
            }
            case 2 -> {
                System.out.println("Veuillez entrer le genre de votre joueur, homme(1) ou femme(2) :");
                int genre = saisieUser.nextInt();
                String tmp = saisieUser.nextLine();
                System.out.println("Veuillez entrer le nom de famille de votre joueur :");
                String nom = saisieUser.nextLine();
                System.out.println("Veuillez entrer le prénom de votre joueur :");
                String prenom = saisieUser.nextLine();
                System.out.println("Veuillez entrer la main de votre joueur, droitier(1) ou gaucher(2) :");
                int main = saisieUser.nextInt();
                String tmp2 = saisieUser.nextLine();
                System.out.println("Veuillez entrer le sponsor de votre joueur :");
                String sponsor = saisieUser.nextLine();
                System.out.println("Veuillez entrer le nom de l'entraineur de votre joueur :");
                String entraineur = saisieUser.nextLine();
                System.out.println("Veuillez entrer la nationalité de votre joueur :");
                String nationalite = saisieUser.nextLine();
                Joueur joueurCree = new Joueur(genre,nom, prenom, main, sponsor, entraineur, nationalite);
                System.out.println("");
                System.out.println("Joueur crée : " + joueurCree.toString());
                listeJoueur.add(joueurCree);
                System.out.println("");
                System.out.println("Liste des joueurs");
                for (int j = 0; j< listeJoueur.size(); j++){
                    System.out.println(listeJoueur.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée un autre joueur ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                int choix2 = saisieUser.nextInt();
                if (choix2 == 1) menuCreationJoueur();
                if (choix2 == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                }
            }
        }
    }
    
    public static void menuCreationArbitre() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous créer un arbitre de facon automatique ou manuel ?");
        System.out.println("1) Automatique");
        System.out.println("2) Manuel");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix) {
            case 1 ->{
                Arbitre unarbitre = creationAutomatiqueArbitre();
                System.out.println("");
                System.out.println("Arbitre crée : " + unarbitre.toString());
                listeArbitre.add(unarbitre);
                System.out.println("");
                System.out.println("Liste des arbitres");
                for (int j = 0; j< listeArbitre.size(); j++){
                    System.out.println(listeArbitre.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée un autre arbitre ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                int choix2 = saisieUser.nextInt();
                if (choix2 == 1) menuCreationArbitre();
                if (choix2 == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                }
            }
            case 2 -> {
                System.out.println("Veuillez entrer le genre de votre arbitre, homme(1) ou femme(2) :");
                int genre = saisieUser.nextInt();
                String tmp = saisieUser.nextLine();
                System.out.println("Veuillez entrer le nom de famille de votre arbitre :");
                String nom = saisieUser.nextLine();
                System.out.println("Veuillez entrer le prénom de votre arbitre :");
                String prenom = saisieUser.nextLine();
                Arbitre arbitreCree = new Arbitre(genre,nom, prenom);
                System.out.println("");
                System.out.println("Arbitre crée : " + arbitreCree.toString());
                listeArbitre.add(arbitreCree);
                System.out.println("");
                System.out.println("Liste des arbitres");
                for (int j = 0; j< listeArbitre.size(); j++){
                    System.out.println(listeArbitre.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée un autre arbitre ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                int choix2 = saisieUser.nextInt();
                if (choix2 == 1) menuCreationArbitre();
                if (choix2 == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                }
            }
        }
    }
    
    public static void menuCreationPublic() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous créer une personne du public de facon automatique ou manuel ?");
        System.out.println("1) Automatique");
        System.out.println("2) Manuel");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix) {
            case 1 ->{
                Spectateur unspectateur = creationAutomatiqueSpectateur();
                System.out.println("");
                System.out.println("Personne du public crée : " + unspectateur.toString());
                listeSpectateur.add(unspectateur);
                System.out.println("");
                System.out.println("Liste des spectateurs");
                for (int j = 0; j< listeSpectateur.size(); j++){
                    System.out.println(listeSpectateur.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée une autre personne du public ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                int choix2 = saisieUser.nextInt();
                if (choix2 == 1) menuCreationPublic();
                if (choix2 == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                } 
            }
            case 2 -> {
                System.out.println("Veuillez entrer le genre de votre spectateur, homme(1) ou femme(2) :");
                int genre = saisieUser.nextInt();
                String tmp = saisieUser.nextLine();
                System.out.println("Veuillez entrer le nom de famille de votre spectateur :");
                String nom = saisieUser.nextLine();
                System.out.println("Veuillez entrer le prénom de votre spectateur :");
                String prenom = saisieUser.nextLine();
                Spectateur spectateurCree = new Spectateur(genre,nom, prenom);
                System.out.println("");
                System.out.println("Personne du public crée : " + spectateurCree.toString());
                listeSpectateur.add(spectateurCree);
                System.out.println("");
                System.out.println("Liste des spectateurs");
                for (int j = 0; j< listeSpectateur.size(); j++){
                    System.out.println(listeSpectateur.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée une autre personne du public ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                int choix2 = saisieUser.nextInt();
                if (choix2 == 1) menuCreationPublic();
                if (choix2 == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                }
            }
        }
    }
    
    public static void menuTournoi() throws IOException{
        System.out.println("");
        System.out.println("Attention, si vous voulez inscrire des joueurs, des spectateurs ou des arbitres faite le avant de lancer un tournoi dans les menus précédents");
        System.out.println("Que voulez vous faire ?");
        System.out.println("1) Lancer un tournoi");
        System.out.println("2) Obtenir les infos d'un tournoi");
        System.out.println("3) Retour au menu principal");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 1 -> {
                System.out.println("");
                System.out.println("Voulez vous créer un tournoi masculin ou féminin ?");
                System.out.println("1) Masculin");
                System.out.println("2) Féminin");
                Scanner saisieUser3 = new Scanner(System.in);
                int choixGenreTournoi = saisieUser3.nextInt();
                if (choixGenreTournoi == 1){
                    ArrayList<Joueur> listeTempJoueur = new ArrayList<Joueur>();
                    for (int i=0; i<listeJoueur.size(); i++){
                        if (listeJoueur.get(i).genre == Genre.HOMME){
                            listeTempJoueur.add(listeJoueur.get(i));
                        }
                    }
                    Tournoi untournoi = new Tournoi(listeTempJoueur, listeArbitre, listeSpectateur);
                    listeTournoi.add(untournoi);
                    listeJoueur.clear();
                    listeArbitre.clear();
                    listeSpectateur.clear();
                    System.out.println("");
                    System.out.println("Tournoi masculin créer avec succès : Tournoi n°" + untournoi.getTournoiNumero());
                    untournoi.startTournoi();
                    untournoi.jouerPremierTour();
                }
                if (choixGenreTournoi == 2){
                    ArrayList<Joueur> listeTempJoueuse = new ArrayList<Joueur>();
                    for (int i=0; i<listeJoueur.size(); i++){
                        if (listeJoueur.get(i).genre == Genre.FEMME){
                            listeTempJoueuse.add(listeJoueur.get(i));
                        }
                    }
                    Tournoi untournoi = new Tournoi(listeTempJoueuse, listeArbitre, listeSpectateur);
                    listeTournoi.add(untournoi);
                    listeJoueur.clear();
                    listeArbitre.clear();
                    listeSpectateur.clear();
                    System.out.println("");
                    System.out.println("Tournoi féminin créer avec succès : Tournoi n°" + untournoi.getTournoiNumero());
                    untournoi.startTournoi();
                    untournoi.jouerPremierTour();
                }
            }
            case 2 -> {
                System.out.println("");
                if (listeTournoi.isEmpty()){
                    System.out.println("/!\\ /!\\ /!\\ Désolé, aucun tournoi n'a encore débuté. /!\\ /!\\ /!\\");
                    menuTournoi();
                }
                else{
                    System.out.println("------ Veuillez choisir le tournoi qui vous intéresse : ------");
                    for (int i = 0; i< listeTournoi.size(); i++){
                        System.out.println(listeTournoi.get(i).getTournoiNumero() + ") Tournoi n°" + listeTournoi.get(i).getTournoiNumero());
                    }
                    Scanner saisieUser2 = new Scanner(System.in);
                    int choix2 = saisieUser2.nextInt();
                    menuInfo(choix2);
                } 
            }
            case 3 -> {
                StartMenu();
            }
        }
        
    }
    
    public static void menuInfo(int numeroTournoi) throws IOException{
        System.out.println("");
        System.out.println("------ Menu informations ------ ");
        System.out.println("1) Informations joueurs");
        System.out.println("2) Informations arbitres");
        System.out.println("3) Informations spectateurs");
        System.out.println("4) Retour");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 1 -> {
                menuInfoJoueur(numeroTournoi);
            }
            case 2 -> {
                menuInfoArbitre(numeroTournoi);
            }
            case 3 -> {
                menuInfoSpectateur(numeroTournoi);
            }
            case 4 -> {
                menuTournoi();
            }
        }
    }
    
    public static void menuInfoJoueur(int numeroTournoi) throws IOException{
        System.out.println("");
        Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
        for (int i = 0; i< leTournoi.listeJoueur.size(); i++){
            System.out.println(leTournoi.listeJoueur.get(i));
        }
        System.out.println("");
        System.out.println("1) Retour");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 1 -> {
                menuInfo(numeroTournoi);
            }
        }
    }
    
    public static void menuInfoArbitre(int numeroTournoi) throws IOException{
        System.out.println("");
        Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
        for (int i = 0; i< leTournoi.listeArbitre.size(); i++){
            System.out.println(leTournoi.listeArbitre.get(i));
        }
        System.out.println("");
        System.out.println("1) Retour");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 1 -> {
                menuInfo(numeroTournoi);
            }
        }
    }
    
    public static void menuInfoSpectateur(int numeroTournoi) throws IOException{
        System.out.println("");
        Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
        for (int i = 0; i< leTournoi.listeSpectateur.size(); i++){
            System.out.println(leTournoi.listeSpectateur.get(i));
        }
        System.out.println("");
        System.out.println("1) Retour");
        Scanner saisieUser = new Scanner(System.in);
        int choix = saisieUser.nextInt();
        switch (choix){
            case 1 -> {
                menuInfo(numeroTournoi);
            }
        }
    }
    
}
