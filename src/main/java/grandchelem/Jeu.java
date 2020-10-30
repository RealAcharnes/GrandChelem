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
    Arbitre arbitre;
    
    public Jeu(Joueur joueur1, Joueur joueur2, Arbitre arbitre){
        pointsJoueur1=0;
        pointsJoueur2=0;
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        this.arbitre=arbitre;
    }
    
    public Joueur jouerEchange(int serveur, int mode){
        Echange echange = new Echange(joueur1, joueur2, serveur);
        int premierService = echange.premierService(mode);
        while(premierService == 2){
            premierService =echange.premierService(mode);
        }
        if (premierService == 1){
            while (!echange.echangeRemporte()){
                if(echange.retour(mode) == 0){
                    echange.gagnantEchange=echange.gagnantEchange();
                } 
            }
        }
        else{
            int secondService = echange.secondService(mode);
            while(secondService == 2){
                secondService = echange.secondService(mode);
            }
            if (secondService == 1){
                while (!echange.echangeRemporte()){
                    if(echange.retour(mode) == 0){
                        echange.gagnantEchange=echange.gagnantEchange();
                    }
                }
            }
            else{
                echange.gagnantEchange=echange.gagnantEchange();
            }
        }
        pointRemporte(echange.gagnantEchange);
        System.out.println("Points : " + toString());
        return echange.gagnantEchange;
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
