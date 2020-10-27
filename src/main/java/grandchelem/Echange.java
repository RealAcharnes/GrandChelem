package grandchelem;

import java.util.Scanner;

/**
 *
 * @author charl
 */
public class Echange {
    Joueur joueur1;
    Joueur joueur2;
    int serveur;
    int dureeEchange;
    Joueur gagnantEchange;
    
    public Echange(Joueur joueur1, Joueur joueur2, int serveur){
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        this.serveur=serveur; //le joueur qui fait le service
        dureeEchange=0;
    }
    
    public Joueur gagnantEchange(){
        if (serveur==1){
            if (dureeEchange%2==0){
                return joueur2;
            }
            else{
                return joueur1;
            }
        }
        else{
            if (dureeEchange%2==0){
                return joueur1;
            }
            else{
                return joueur2;
            }
        }
    }
    
    public Joueur getGagnantEchange(){
        return gagnantEchange;
    }
    
    public boolean echangeRemporte(){
        return gagnantEchange == joueur1 || gagnantEchange == joueur2;
    }
    
    public int premierService(int mode){
        switch (mode){
            case 1 -> {
                Scanner saisieUtilisateur = new Scanner(System.in);
                System.out.println("Veuillez indiquer si le premier service est réussi (1), let(2) (default) ou faux (0) :");
                int input = saisieUtilisateur.nextInt();
                switch (input){
                    case 1 -> {
                        dureeEchange++;
                        return 1; //service réussi
                    }
                    case 2 -> {
                        return 2;
                    }
                    case 0 -> {
                        return 0;
                    }
                    default -> {
                        System.out.println("Erreur de saisie - Service considéré comme let");
                        return 2;
                    }
                }
            }
            default -> {
                double i = Math.random();
                if (i<0.7){
                    dureeEchange++;
                    return 1; //service réussi
                }
                else if(i<0.8){
                    return 2; //service let
                }
                else{
                    return 0; //service faux
                }
            }
        } 
    }
    
    public int secondService(int mode){
        switch (mode){
            case 1 -> {
                Scanner saisieUtilisateur = new Scanner(System.in);
                System.out.println("Veuillez indiquer si le second service est réussi (1), let(2) (default) ou faux (0) :");
                int input = saisieUtilisateur.nextInt();
                switch (input){
                    case 1 -> {
                        dureeEchange++;
                        return 1; //service réussi
                    }
                    case 2 -> {
                        return 2;
                    }
                    case 0 -> {
                        return 0;
                    }
                    default -> {
                        System.out.println("Erreur de saisie - Service considéré comme let");
                        return 2;
                    }
                }
            }
            default -> {
                double i = Math.random();
                if (i<0.7){
                    dureeEchange++;
                    return 1; //service réussi
                }
                else if(i<0.8){
                    return 2; //service let
                }
                else{
                    return 0; //service faux
                }
            }
        } 
    }
    
    public int retour(int mode){
        switch(mode){
            case 1 -> {
                Scanner saisieUtilisateur = new Scanner(System.in);
                System.out.println("Veuillez indiquer si le retour est réussi (1) (default), ou faux (0) :");
                int input = saisieUtilisateur.nextInt();
                switch (input){
                    case 0 -> {
                        return 0;
                    }
                    default -> {
                        dureeEchange++;
                        return 1;
                    }
                }
            }
            default -> {
                double i = Math.random();
                if (i<0.5+1/dureeEchange){
                    dureeEchange++;
                    return 1; //balle retournée
                }
                else{
                    return 0; //balle faute
                }   
            }     
        }
    }
}
