/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

/**
 *
 * @author charl
 */
public class Personne {
    private Genre genre;
    private String nomNaissance;
    private String nomCourant;
    private String prenom;
    private String surnom;
    private int dateNaissance;
    private String lieuNaissance;
    private int dateDeces;
    private String nationalite;
    private int taille;
    private double poids;
    
    public Personne(int genre, String nomNaissance, String prenom){
        switch (genre){
            case 1:
                this.genre=this.genre.HOMME; 
                break;
            case 2:
                this.genre=this.genre.FEMME;
                break;
            default:
                this.genre=this.genre.HOMME; 
                System.out.println("WRONG INPUT : Le genre a été configure sur HOMME");
                break;
        }
        this.nomNaissance=nomNaissance;
        this.prenom=prenom;
    }
}
