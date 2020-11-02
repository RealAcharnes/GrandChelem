/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Collections;
/**
 *
 * @author jeanc
 */
public class Tournoi {
    ArrayList<Joueur> listeJoueur;
    ArrayList<Arbitre> listeArbitre;
    ArrayList<Spectateur> listeSpectateur;
    ArrayList<Joueur> secondTour;
    ArrayList<Joueur> troisièmeTour;
    ArrayList<Joueur> seizièmeDeFinale;
    ArrayList<Joueur> huitièmeDeFinale;
    ArrayList<Joueur> quartDeFinale;
    ArrayList<Joueur> DemiFinale;
    ArrayList<Joueur> finale;
    private static int compteurTournoi;
    int tournoiNumero;
    
    public Tournoi(ArrayList<Joueur> listeJoueur, ArrayList<Arbitre> listeArbitre, ArrayList<Spectateur> listeSpectateur){
        this.listeJoueur = listeJoueur;
        this.listeArbitre = listeArbitre;
        this.listeSpectateur = listeSpectateur;
        compteurTournoi++;
        this.tournoiNumero = compteurTournoi;
        
    }
    
    //Un tournoi comporte 128 joueurs, disons qu'il comporte 25 arbitres.
    public ArrayList<Joueur> genererJoueursTournoi() throws IOException{
        while (this.listeJoueur.size() != 128) {
            Joueur unjoueur = Menu.creationAutomatiqueJoueur();
            this.listeJoueur.add(unjoueur);
        }
        //Après avoir générer les 128 joueurs, on les mélanges dans la liste.
        Collections.shuffle(listeJoueur);
        return this.listeJoueur;
    }
    
    public ArrayList<Arbitre> genererArbitresTournoi() throws IOException{
        while (this.listeArbitre.size() != 25) {
            Arbitre unarbitre = Menu.creationAutomatiqueArbitre();
            this.listeArbitre.add(unarbitre);
        }
        return this.listeArbitre;
    }
    
    public ArrayList<Spectateur> genererSpectateursTournoi() throws IOException{
        while (this.listeSpectateur.size() != 3000) {
            Spectateur unspectateur = Menu.creationAutomatiqueSpectateur();
            this.listeSpectateur.add(unspectateur);
        }
        return this.listeSpectateur;
    }
    
    public void startTournoi() throws IOException{
        genererJoueursTournoi();
        genererArbitresTournoi();
        genererSpectateursTournoi();         
    }
    
    public int getTournoiNumero (){
        return this.tournoiNumero;
    }
    
}
