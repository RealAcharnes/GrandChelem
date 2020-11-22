package grandchelem;

/**
 *
 * @author charl
 */
public class Spectateur extends Personne {
    private Couleur couleurChemise;
    public int prixBillet;
    private String tribune;
    final Habits habit;
    private Couleur couleur;
    private int seat;
    
    public Spectateur(int genre, String nomNaissance, String prenom){
        super(genre, nomNaissance, prenom);
         if (genre==2){
            habit=Habits.LUNETTES;
            this.couleur = Couleur.BLEU;
        }
        else{
            habit=Habits.CHEMISE;
            this.couleur = Couleur.ROUGE;
        }
    }
    
    public void setCouleur(Couleur couleur){
        this.couleur = couleur;
        String entete = toString();
        if (this.genre == Genre.HOMME){
            System.out.println(entete + " : Change la couleur de son short en " + this.couleur);
        }
        if (this.genre == Genre.FEMME){
            System.out.println(entete + " : Change la couleur de sa jupe en " + this.couleur);
        }
    }
    
    @Override
    public String toString(){
        return this.prenom + " " + this.nomNaissance;
    }
}
