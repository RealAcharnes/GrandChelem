/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.util.Scanner;
import java.util.InputMismatchException;
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
        System.out.println("Bonjour et bienvenu, dans les menus qui vont suivre, lorqu'une proposition"
                + " est précédée ou suivie d'un chiffre, veuillez"
                + " répondre par le chiffre correspondant.");
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
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUserDetail = new Scanner(System.in);
                choix = saisieUserDetail.nextInt();
                if (choix<1 || choix>3){
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
            case 1 -> {
                menuCreation();
                break;
            }
            case 2 -> {
                menuTournoi();
                break;
            }
            case 3 -> {
                System.exit(0);
                break;
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
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUserDetail = new Scanner(System.in);
                choix = saisieUserDetail.nextInt();
                if (choix<1 || choix>4){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e){
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
            }
        }while (erreur);
        
        switch (choix) {
            case 1 -> {
                menuCreationJoueur();
                break;
            }
            case 2 -> {
                menuCreationArbitre();
                break;
            }
            case 3 -> {
                menuCreationPublic();
                break;
            }
            case 4 -> {
                StartMenu();
                break;
            }
        }
    }
    
    public static Joueur creationAutomatiqueJoueur(int genre) throws IOException {
        if (genre == 3){ genre = (int)(Math.random() * (3 - 1)) + 1;} //3 correspond à la création aléatoire d'un homme ou d'une femme
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
        boolean erreur;
        int choix = 1;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
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
        
        switch (choix) {
            case 1 ->{
                Joueur unjoueur = creationAutomatiqueJoueur(3);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                
                if (choix == 1) menuCreationJoueur();
                if (choix == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                }
                break;
            }
            case 2 -> {
                System.out.println("Veuillez entrer le genre de votre joueur, homme(1) ou femme(2) :");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                int genre = choix;
                
                System.out.println("Veuillez entrer le nom de famille de votre joueur :");
                boolean erreurInverse;
                String nom = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        nom = saisieUser.nextLine();
                        double d = Double.parseDouble(nom);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme nom, pas de nombre.");
                    }
                }while (erreurInverse);
                
                System.out.println("Veuillez entrer le prénom de votre joueur :");
                String prenom = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        prenom = saisieUser.nextLine();
                        double d = Double.parseDouble(prenom);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme prénom, pas de nombre.");
                    }
                }while (erreurInverse);
                System.out.println("Veuillez entrer la main de votre joueur, droitier(1) ou gaucher(2) :");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                int main = choix;
                
                System.out.println("Veuillez entrer le sponsor de votre joueur :");
                String sponsor = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        sponsor = saisieUser.nextLine();
                        double d = Double.parseDouble(sponsor);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme sponsor, pas de nombre.");
                    }
                }while (erreurInverse);
                System.out.println("Veuillez entrer le nom de l'entraineur de votre joueur :");
                String entraineur = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        entraineur = saisieUser.nextLine();
                        double d = Double.parseDouble(entraineur);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme entraineur, pas de nombre.");
                    }
                }while (erreurInverse);
                System.out.println("Veuillez entrer la nationalité de votre joueur :");
                String nationalite = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        nationalite = saisieUser.nextLine();
                        double d = Double.parseDouble(nationalite);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme nationalité, pas de nombre.");
                    }
                }while (erreurInverse);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                
                if (choix == 1) menuCreationJoueur();
                if (choix == 2){
                    System.out.println("");
                    System.out.println("Retour au menu principal");
                    System.out.println("");
                    StartMenu();
                }
                break;
            }
        }
    }
    
    public static void menuCreationArbitre() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous créer un arbitre de facon automatique ou manuel ?");
        System.out.println("1) Automatique");
        System.out.println("2) Manuel");
        
        boolean erreur;
        int choix = 1;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                if (choix == 1) menuCreationArbitre();
                if (choix == 2){
                System.out.println("");
                System.out.println("Retour au menu principal");
                System.out.println("");
                StartMenu();
                }
                break;
            }
            case 2 -> {
                System.out.println("Veuillez entrer le genre de votre arbitre, homme(1) ou femme(2) :");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                int genre = choix;
                
                System.out.println("Veuillez entrer le nom de famille de votre arbitre :");
                boolean erreurInverse;
                String nom = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        nom = saisieUser.nextLine();
                        double d = Double.parseDouble(nom);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme nom, pas de nombre.");
                    }
                }while (erreurInverse);
                
                System.out.println("Veuillez entrer le prénom de votre arbitre :");
                String prenom = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        prenom = saisieUser.nextLine();
                        double d = Double.parseDouble(prenom);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme prénom, pas de nombre.");
                    }
                }while (erreurInverse);
                Arbitre arbitreCree = new Arbitre(genre, nom, prenom);
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
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                
                if (choix == 1) menuCreationArbitre();
                if (choix == 2){
                System.out.println("");
                System.out.println("Retour au menu principal");
                System.out.println("");
                StartMenu();
                }
                break;
            }
        }
    }
    
    public static void menuCreationPublic() throws IOException{
        System.out.println("");
        System.out.println("Voulez-vous créer une personne du public de facon automatique ou manuel ?");
        System.out.println("1) Automatique");
        System.out.println("2) Manuel");
        
        boolean erreur;
        int choix = 1;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
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
        
       
        switch (choix) {
            case 1 ->{
                Spectateur unspectateur = creationAutomatiqueSpectateur();
                System.out.println("");
                System.out.println("Spectateur crée : " + unspectateur.toString());
                listeSpectateur.add(unspectateur);
                System.out.println("");
                System.out.println("Liste des spectateurs");
                for (int j = 0; j< listeSpectateur.size(); j++){
                    System.out.println(listeSpectateur.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée un autre spectateur ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                if (choix == 1) menuCreationPublic();
                if (choix == 2){
                System.out.println("");
                System.out.println("Retour au menu principal");
                System.out.println("");
                StartMenu();
                }
                break;
            }
            case 2 -> {
                System.out.println("Veuillez entrer le genre de votre spectateur, homme(1) ou femme(2) :");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                int genre = choix;
                
                System.out.println("Veuillez entrer le nom de famille de votre spectateur :");
                boolean erreurInverse;
                String nom = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        nom = saisieUser.nextLine();
                        double d = Double.parseDouble(nom);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme nom, pas de nombre.");
                    }
                }while (erreurInverse);
                
                System.out.println("Veuillez entrer le prénom de votre spectateur :");
                String prenom = "";
                do {
                    erreurInverse = true;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        prenom = saisieUser.nextLine();
                        double d = Double.parseDouble(prenom);
                    }
                    catch (NumberFormatException f){
                        erreurInverse = false;
                    }
                    if (erreurInverse == true){
                        System.out.println("Autre entrée qu'un texte, veuillez entrer un texte comme prénom, pas de nombre.");
                    }
                }while (erreurInverse);
                Spectateur spectateurCree = new Spectateur(genre, nom, prenom);
                System.out.println("");
                System.out.println("Spectateur crée : " + spectateurCree.toString());
                listeSpectateur.add(spectateurCree);
                System.out.println("");
                System.out.println("Liste des spectateurs");
                for (int j = 0; j< listeSpectateur.size(); j++){
                    System.out.println(listeSpectateur.get(j));
                }
                System.out.println("");
                System.out.println("Voulez vous crée un autre spectateur ?");
                System.out.println("1) Oui");
                System.out.println("2) Non");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choix = saisieUser.nextInt();
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
                
                if (choix == 1) menuCreationPublic();
                if (choix == 2){
                System.out.println("");
                System.out.println("Retour au menu principal");
                System.out.println("");
                StartMenu();
                }
                break;
            }
        }
        
    }
    
    public static void menuTournoi() throws IOException{
        System.out.println("");
        System.out.println("Attention, si vous voulez inscrire des joueurs, des spectateurs ou des arbitres faite le avant de lancer un tournoi dans les menus précédents");
        System.out.println("Que voulez vous faire ?");
        System.out.println("1) Lancer un tournoi");
        System.out.println("2) Reprendre un tournoi");
        System.out.println("3) Obtenir les informations d'un tournoi");
        System.out.println("4) Retour au menu principal");
        
        boolean erreur;
        int choix = 1;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<1 || choix>4){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e) {
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés."); 
            }
        }while (erreur); 
            
        switch (choix){
            case 1 -> {
                System.out.println("");
                System.out.println("Voulez vous créer un tournoi masculin ou féminin ?");
                System.out.println("1) Masculin");
                System.out.println("2) Féminin");
                boolean erreur2;
                int choixGenreTournoi = 1;
                do {
                    erreur2 = false;
                    try {
                        Scanner saisieUser = new Scanner(System.in);
                        choixGenreTournoi = saisieUser.nextInt();
                        if (choixGenreTournoi<1 || choixGenreTournoi>2){
                            erreur2 = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e) {
                        erreur2 = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés."); 
                    }
                }while (erreur2);
                if (choixGenreTournoi == 1){
                    ArrayList<Joueur> listeTempJoueur = new ArrayList<>();
                    for (int i=0; i<listeJoueur.size(); i++){
                        if (listeJoueur.get(i).genre == Genre.HOMME){
                            listeTempJoueur.add(listeJoueur.get(i));
                        }
                    }
                    Tournoi untournoi = new Tournoi(listeTempJoueur, listeArbitre, listeSpectateur, choixGenreTournoi);
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
                    ArrayList<Joueur> listeTempJoueuse = new ArrayList<>();
                    for (int i=0; i<listeJoueur.size(); i++){
                        if (listeJoueur.get(i).genre == Genre.FEMME){
                            listeTempJoueuse.add(listeJoueur.get(i));
                        }
                    }
                    Tournoi untournoi = new Tournoi(listeTempJoueuse, listeArbitre, listeSpectateur, choixGenreTournoi);
                    listeTournoi.add(untournoi);
                    listeJoueur.clear();
                    listeArbitre.clear();
                    listeSpectateur.clear();
                    System.out.println("");
                    System.out.println("Tournoi féminin créer avec succès : Tournoi n°" + untournoi.getTournoiNumero());
                    untournoi.startTournoi();
                    untournoi.jouerPremierTour();
                }
                break;
            }
            case 2 -> {
                System.out.println("");
                if (listeTournoi.isEmpty()){
                    System.out.println("/!\\ /!\\ /!\\ Désolé, aucun tournoi n'a encore débuté. /!\\ /!\\ /!\\");
                    menuTournoi();
                }
                else{
                    System.out.println("------ Quel tournoi voulez vous reprendre ? ------");
                    for (int i = 0; i< listeTournoi.size(); i++){
                        System.out.println(listeTournoi.get(i).getTournoiNumero() + ") Tournoi n°" + listeTournoi.get(i).getTournoiNumero());
                    }
                    boolean erreur2;
                    int choix3 = 1;
                    do {
                        erreur2 = false;
                        try {
                            Scanner saisieUser = new Scanner(System.in);
                            choix3 = saisieUser.nextInt();
                            if (choix3<1 || choix3>listeTournoi.size()){
                                erreur2 = true;
                                System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                            }
                        }
                        catch (InputMismatchException e) {
                            erreur2 = true;
                            System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés."); 
                        }
                    }while (erreur2);
                    Tournoi leTournoi = listeTournoi.get(choix3-1);
                    leTournoi.reprendreTournoi();
                }
                break;
            }
            case 3 -> {
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
                    boolean erreur2;
                    int choix2 = 1;
                    do {
                        erreur2 = false;
                        try {
                            Scanner saisieUser2 = new Scanner(System.in);
                            choix2 = saisieUser2.nextInt();
                            if (choix2<1 || choix2>listeTournoi.size()){
                                erreur2 = true;
                                System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                            }
                        }
                        catch (InputMismatchException e) {
                            erreur2 = true;
                            System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés."); 
                        }
                    }while (erreur2);
                    
                    menuInfo(choix2);
                }
                break;
            }
            case 4 -> {
                StartMenu();
                break;
            }
        }
    }
    
    public static void menuInfo(int numeroTournoi) throws IOException{
        System.out.println("");
        System.out.println("------ Menu informations ------ ");
        System.out.println("1) Informations joueurs");
        System.out.println("2) Informations arbitres");
        System.out.println("3) Informations spectateurs");
        System.out.println("4) Afficher le classement du tournoi (Classement en temps réel)");
        System.out.println("5) Retour");
        
        boolean erreur;
        int choix = 1;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix<1 || choix>5){
                    erreur = true;
                    System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                }
            }
            catch (InputMismatchException e) {
                erreur = true;
                System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés."); 
            }
        }while (erreur);
        
        switch (choix){
            case 1 -> {
                menuChoixJoueur(numeroTournoi);
                break;
            }
            case 2 -> {
                menuChoixArbitre(numeroTournoi);
                break;
            }
            case 3 -> {
                menuChoixSpectateur(numeroTournoi);
                break;
            }
            case 4 -> {
                System.out.println("############### Classement ###############");
                System.out.println("");
                Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
                for (int k = 0; k< leTournoi.getLeClassement().size(); k++){
                    System.out.println(k+1 + ") " + leTournoi.getLeClassement().get(k));
                    System.out.println("       ----> Avec " + leTournoi.getLeClassement().get(k).getNombreVictoire() + " victoire(s)");
                    System.out.println("");
                }
                System.out.println("");
                System.out.println("0) Retour");
                choix = 0;
                do {
                    erreur = false;
                    try {
                        Scanner saisieUserDetail = new Scanner(System.in);
                        choix = saisieUserDetail.nextInt();
                        if (choix != 0){
                            erreur = true;
                            System.out.println("Veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                        }
                    }
                    catch (InputMismatchException e){
                        erreur = true;
                        System.out.println("Autre entrée qu'un chiffre détecté, veuillez entrer un chiffre qui est dans la liste des choix proposés.");
                    }
                }while (erreur);
                if (choix == 0) {menuInfo(numeroTournoi);}
                break;
            }
            case 5 -> {
                menuTournoi();
                break;
            }
        }
    }
    
    public static void menuChoixJoueur(int numeroTournoi) throws IOException{
        System.out.println("");
        System.out.println("Voici la liste des joueurs du tournoi :");
        Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
        for (int i = 0; i< leTournoi.listeJoueur.size(); i++){
            System.out.println(i+1 + ") " + leTournoi.listeJoueur.get(i));
        }
        System.out.println("");
        System.out.println("0) Retour");
        System.out.println("Entrez le numéro du joueur pour obtenir des informations ou 0 pour retourner au menu précédent.");
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix < 0 || choix > leTournoi.listeJoueur.size()){
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
            menuInfo(numeroTournoi);
        }
        else if (choix > 0 && choix <= leTournoi.listeJoueur.size()){
            menuInfoJoueur(numeroTournoi, choix);
        }
    }
    
    public static void menuChoixArbitre(int numeroTournoi) throws IOException{
        System.out.println("");
        System.out.println("Liste des arbitres :");
        System.out.println("");
        Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
        for (int i = 0; i< leTournoi.listeArbitre.size(); i++){
            System.out.println(leTournoi.listeArbitre.get(i));
        }
        System.out.println("");
        System.out.println("0) Retour");
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix != 0){
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
            menuInfo(numeroTournoi);
        }
    }
    
    public static void menuChoixSpectateur(int numeroTournoi) throws IOException{
        System.out.println("");
        System.out.println("Liste des spectateurs :");
        System.out.println("");
        Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
        for (int i = 0; i< leTournoi.listeSpectateur.size(); i++){
            System.out.println(leTournoi.listeSpectateur.get(i));
        }
        System.out.println("");
        System.out.println("0) Retour");
        
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
                if (choix != 0){
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
            menuInfo(numeroTournoi);
        }
    }
    
    public static void menuInfoJoueur(int numeroTournoi, int numeroJoueur) throws IOException{
        Tournoi leTournoi = listeTournoi.get(numeroTournoi-1);
        Joueur leJoueur = leTournoi.getListeJoueur().get(numeroJoueur-1);
        int classementDuJoueur = leJoueur.getClassement();
        String suffix;
        if (classementDuJoueur == 1){
            suffix = "er";
        }
        else {
            suffix = "ème";
        }
        System.out.println("");
        System.out.println("#################### Classement de " + leJoueur + " : " + classementDuJoueur + suffix + " ####################");
        System.out.println("");
        System.out.println("-------- Liste des matchs disputés par ce joueur --------");
        System.out.println("");
        for (int i = 0; i< leJoueur.getListeMatch().size(); i++){
            Match matchJoueur = leJoueur.getListeMatch().get(i);
            matchJoueur.printScores();
        }
        System.out.println("");
        System.out.println("1) Choisir un/une autre joueur(euse)");
        System.out.println("2) Retour au menu Informations");
        boolean erreur;
        int choix = 0;
        do {
            erreur = false;
            try {
                Scanner saisieUser = new Scanner(System.in);
                choix = saisieUser.nextInt();
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

        switch (choix) {
            case 1 -> {
                menuChoixJoueur(numeroTournoi);
                break;
            }
            case 2 -> {
                menuInfo(numeroTournoi);
                break;
            }
        } 
    }
}
