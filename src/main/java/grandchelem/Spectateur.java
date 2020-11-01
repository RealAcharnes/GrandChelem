package grandchelem;

/**
 *
 * @author charl
 */
public class Spectateur extends Personne {
    private Couleur couleurChemise;
    public int prixBillet;
    private String tribune;
    private int seat;
    
    public Spectateur(int genre, String nomNaissance, String prenom){
        super(genre, nomNaissance, prenom);
    }
    
    @Override
    public String toString(){
        return prenom + " " + nomNaissance;
    }
}
