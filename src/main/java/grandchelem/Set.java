package grandchelem;

/**
 *
 * @author charl
 */
public class Set {
    Jeu jeuxJoueur1;
    Jeu jeuxJoueur2;
    int numeroSet;
    
    public Set(Match match, int numeroSet){
        jeuxJoueur1=new Jeu(1);
        this.numeroSet=numeroSet;
    }
}
