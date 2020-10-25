package grandchelem;

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
    
    public int premierService(){
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
    
    public int secondService(){
        double i = Math.random();
        if (i<0.8){
            dureeEchange++;
            return 1; //service réussi
        }
        else if(i<0.9){
            return 2; //service let
        }
        else{
            return 0; //service faux
        }
    }
    
    public int retour(){
        double i = Math.random();
        if (i<0.5){
            dureeEchange++;
            return 1; //balle retournée
        }
        else{
            return 0; //balle faute
        }
    }
}
