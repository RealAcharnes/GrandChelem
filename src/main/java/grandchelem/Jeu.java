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
    int serveur;
    
    /**
     * Création d'un jeu
     * @param joueur1
     * @param joueur2
     * @param arbitre 
     */
    public Jeu(Joueur joueur1, Joueur joueur2, Arbitre arbitre){
        pointsJoueur1=0;
        pointsJoueur2=0;
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        this.arbitre=arbitre;
    }
    
    /**
     * Permet de jouer un échange entre 2 joueurs
     * @param serveur le joueur qui servira en premier pour cet échange
     * @param mode manuel (1) ou automatique (0)
     * @param affichage affichage du détail point par point oui (1) ou non (0)
     * @return le gagnant de l'échange
     */
    public Joueur jouerEchange(int serveur, int mode, int affichage){
        Echange echange = new Echange(joueur1, joueur2, serveur);
        this.serveur = serveur;
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
        if (Math.random() < 0.15){
            if(affichage==1){
                System.out.println(echange.gagnantEchange + " a gagné l'échange, mais un litige a été demandé.");
            }
            if (Math.random() < arbitre.getHumeur()) {
                if (echange.gagnantEchange == joueur1){
                    echange.gagnantEchange = joueur2;
                }
                else{
                    echange.gagnantEchange = joueur1;
                }
                if(affichage==1){
                    System.out.println("Le point est accordé à " + echange.gagnantEchange + " qui a demandé le litige.");
                }
            }
            else {
                if(affichage==1){
                    System.out.println("L'arbitre reste sur sa première décision, " + echange.gagnantEchange + " gagne le point");
                }   
            }
        }
        pointRemporte(echange.gagnantEchange);
        if(affichage==1){
            System.out.println("Points : " + toString());
        }      
        return echange.gagnantEchange;
    }
    
    /**
     * Met à jour les points pour le jeu en court après victoire de l'échange
     * @param gagnant le gagnant de l'échange
     */
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
    
    /**
     * Permet de connaître le gagnant du jeu, lorsque l'on sait que le jeu a été remporté
     * @return le gagnant du jeu en cours
     */
    public Joueur gagnantJeu(){
        if (pointsJoueur1>pointsJoueur2){
            return joueur1;
        }
        return joueur2;
    }
    
    /**
     * Permet de savoir si le jeu a été remporté par l'un des deux joueurs
     * @return true si le jeu a été remporté, false sinon
     */
    public boolean jeuRemporte(){
        return pointsJoueur1 == 50 || pointsJoueur2 == 50;
    }

    @Override
    public String toString() {
        if (serveur == 1) {
            if (pointsJoueur1 == 45){
                return joueur1 + " " + "Av" + " , " + pointsJoueur2 + " " + joueur2;
            }
            else if (pointsJoueur2 == 45){
                return joueur1 + " " + pointsJoueur1 + " , " + "Av" + " " + joueur2;
            }
            else if (pointsJoueur1 == 50){
                return joueur1 + " " + "Ga" + " , " + pointsJoueur2 + " " + joueur2;
            }
            else if (pointsJoueur2 == 50){
                return joueur1 + " " + pointsJoueur1 + " , " + "Ga" + " " + joueur2;
            }
            else{
                return joueur1 + " " + pointsJoueur1 + " , " + pointsJoueur2 + " " + joueur2;
            }
        }
        else{
            if (pointsJoueur2 == 45){
                return joueur2 + " " + "Av" + " , " + pointsJoueur1 + " " + joueur1;
            }
            else if (pointsJoueur1 == 45){
                return joueur2 + " " + pointsJoueur2 + " , " + "Av" + " " + joueur1;
            }
            else if (pointsJoueur2 == 50){
                return joueur2 + " " + "Ga" + " , " + pointsJoueur1 + " " + joueur1;
            }
            else if (pointsJoueur1 == 50){
                return joueur2 + " " + pointsJoueur2 + " , " + "Ga" + " " + joueur1;
            }
            else{
                return joueur2 + " " + pointsJoueur2 + " , " + pointsJoueur1 + " " + joueur1;
            }
        }
    }
    
    
}
