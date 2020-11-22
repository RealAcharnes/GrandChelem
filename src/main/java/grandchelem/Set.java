package grandchelem;

import java.util.Scanner;
/**
 *
 * @author charl
 */
public class Set {
    int jeuxJoueur1;
    int jeuxJoueur2;
    Joueur joueur1;
    Joueur joueur2;
    Arbitre arbitre;
    
    /**
     * Création d'un set
     * @param joueur1 
     * @param joueur2
     * @param arbitre 
     */
    
    public Set(Joueur joueur1, Joueur joueur2, Arbitre arbitre){
        jeuxJoueur1=0;
        jeuxJoueur2=0;
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        this.arbitre=arbitre;
    }
    
    /**
     * Joue un jeu de ce set
     * @param serveur celui qui sert pendant le jeu
     * @param mode manuel (1) ou automatique (0)
     * @param affichage affichage du détail point par point oui (1) ou non (0)
     * @return 
     */
    
    public Joueur jouerJeu(int serveur, int mode, int affichage){
        if (mode==1){
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Veuillez indiquer si vous souhaitez jouer ce jeu de façon automatique (0) (default) ou bien manuelle (1)");
            int input = saisieUtilisateur.nextInt();
            mode=input;
        }
        Jeu jeu = new Jeu(joueur1, joueur2, arbitre);
        while (!jeu.jeuRemporte()){
            jeu.jouerEchange(serveur, mode, affichage);
        }
        return jeu.gagnantJeu();  
    }
    
    /**
     * Permet de mettre à jour le nombre de jeux après un jeu remporté
     * @param gagnant le gagnant du jeu
     */
    public void jeuRemporte(Joueur gagnant){
        if (gagnant==joueur1){
            jeuxJoueur1++;
        }
        else{
            jeuxJoueur2++;
        }
    }
    
    /**
     * Permet de savoir si le set a été remporté
     * @return true si le set est terminé, false sinon
     */
    
    public boolean setRemporte(){
        if(jeuxJoueur1 == 7 || jeuxJoueur2 == 7){
            return true;
        }
        else if(jeuxJoueur1==6 && jeuxJoueur2<5){
            return true;
        }
        else if(jeuxJoueur1<5 && jeuxJoueur2==6){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Permet de connaitre le gagnant du set lorsque le set a déjà été remporté
     * @return le gagnant du set
     */
    public Joueur gagnantSet(){
        if (jeuxJoueur1>jeuxJoueur2){
            return joueur1;
        }
        return joueur2;
    }
    
    @Override
    public String toString() {
        return joueur1 + " " + jeuxJoueur1 + " , " + jeuxJoueur2 + " " + joueur2 ;
    }
}
