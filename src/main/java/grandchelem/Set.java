package grandchelem;

/**
 *
 * @author charl
 */
public class Set {
    int jeuxJoueur1;
    int jeuxJoueur2;
    Joueur joueur1;
    Joueur joueur2;
    
    public Set(Joueur joueur1, Joueur joueur2){
        jeuxJoueur1=0;
        jeuxJoueur2=0;
        this.joueur1=joueur1;
        this.joueur2=joueur2;
    }
    
    public Joueur jouerJeu(){
        Jeu jeu = new Jeu(joueur1, joueur2);
        while (!jeu.jeuRemporte()){
            double i = Math.random();
            if (i<0.5){
                jeu.pointRemporte(joueur1);
            }
            else{
                jeu.pointRemporte(joueur2);
            }
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
