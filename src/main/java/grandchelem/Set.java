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
    
    public Set(Joueur joueur1, Joueur joueur2, Arbitre arbitre){
        jeuxJoueur1=0;
        jeuxJoueur2=0;
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        this.arbitre=arbitre;
    }
    
    public Joueur jouerJeu(int serveur, int mode){
        if (mode==1){
            Scanner saisieUtilisateur = new Scanner(System.in);
            System.out.println("Veuillez indiquer si vous souhaitez jouer ce jeu de façon automatique (0) (default) ou bien manuelle (1)");
            int input = saisieUtilisateur.nextInt();
            mode=input;
        }
        Jeu jeu = new Jeu(joueur1, joueur2, arbitre);
        while (!jeu.jeuRemporte()){
            jeu.jouerEchange(serveur, mode);
        }
        return jeu.gagnantJeu();  
    }
    
    public void jeuRemporte(Joueur gagnant){
        if (gagnant==joueur1){
            jeuxJoueur1++;
        }
        else{
            jeuxJoueur2++;
        }
    }
    
    public boolean setRemporte(){
        return jeuxJoueur1 == 6 || jeuxJoueur2 == 6;
    }
    
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
