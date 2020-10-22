package grandchelem;

/**
 *
 * @author charl
 */
public class Jeu {
    int pointsJoueur1;
    int pointsJoueur2;
    Joueur joueur1;
    Joueur joueur2;
    
    public Jeu(Joueur joueur1, Joueur joueur2){
        pointsJoueur1=0;
        pointsJoueur2=0;
        this.joueur1=joueur1;
        this.joueur2=joueur2;
    }
    
    public void pointRemporte(Joueur gagnant){
        if (gagnant==joueur1){
            switch (pointsJoueur1){
                case 0 -> pointsJoueur1=15;
                case 15 -> pointsJoueur1=30;
                case 30 -> pointsJoueur1=40;
                case 40 -> {
                    switch (pointsJoueur2){
                        case 40 -> pointsJoueur1=45; //45 représente l'avantage
                        case 45 -> pointsJoueur2=40;
                        default -> pointsJoueur1=50; //50 représente la victoire
                    }
                }
                case 45 -> pointsJoueur1=50;

            }
        }
        else{
            switch (pointsJoueur2){
                case 0 -> pointsJoueur2=15;
                case 15 -> pointsJoueur2=30;
                case 30 -> pointsJoueur2=40;
                case 40 -> {
                    switch (pointsJoueur1){
                        case 40 -> pointsJoueur2=45; //45 représente l'avantage
                        case 45 -> pointsJoueur1=40;
                        default -> pointsJoueur2=50; //50 représente la victoire
                    }
                }
                case 45 -> pointsJoueur2=50;
            }
        }
    }
    
    public Joueur gagnantJeu(){
        if (pointsJoueur1>pointsJoueur2){
            return joueur1;
        }
        return joueur2;
    }
    
    public boolean jeuRemporte(){
        return pointsJoueur1 == 50 || pointsJoueur2 == 50;
    }

    @Override
    public String toString() {
        return joueur1 + " " + pointsJoueur1 + " , " + pointsJoueur2 + " " + joueur2 ;
    }
    
    
}
