package grandchelem;

/**
 *
 * @author charl
 */
public class Spectateur extends Personne implements InterfaceSpectateur {
    public int prixBillet;
    private String tribune;
    final Habits habit;
    private Couleur couleur;
    private int seat;
    
    /**
     * Création d'un spectateur
     * @param genre 1 (homme) ou 2 (femme) (défaut : homme)
     * @param nomNaissance
     * @param prenom 
     */
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
            System.out.println(entete + " : Change la couleur de sa chemise en " + this.couleur);
        }
        if (this.genre == Genre.FEMME){
            System.out.println(entete + " : Change la couleur de ses lunettes en " + this.couleur);
        }
    }
    
    public void RegarderMatch(Joueur joueur1, Joueur joueur2, Arbitre arbitre, int sexe){
        InterfaceSpectateur.super.regarderMatch(joueur1, joueur2, arbitre, sexe);
    }
    
    @Override
    public String toString(){
        return this.prenom + " " + this.nomNaissance;
    }
}
