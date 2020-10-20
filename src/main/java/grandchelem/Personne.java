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
    protected Genre genre;
    protected String nomNaissance;
    public String nomCourant;
    protected String prenom;
    public String surnom;
    private String dateNaissance;   //Date en format YYYYMMDD
    private String lieuNaissance;
    private String dateDeces;       //Date en format YYYYMMDD
    public String nationalite;
    public int taille;
    public double poids;
    
    public Personne(int genre, String nomNaissance, String prenom){  //genre = 1 -> Homme / genre = 2 -> Femme / autre ->Homme
        switch (genre){
            case 1:
                this.genre=Genre.HOMME; 
                break;
            case 2:
                this.genre=Genre.FEMME;
                break;
            default:
                this.genre=Genre.HOMME; 
                System.out.println("WRONG INPUT : Le genre a été configure sur HOMME");
                break;
        }
        this.nomNaissance=nomNaissance;
        this.prenom=prenom;
    }
    //Les accessors
    public Genre getGenre(){
        return this.genre;
    }
    public String getNomNaissance(){
        return this.nomNaissance;
    }
    public String getNomCourant(){
        return this.nomCourant;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public String getSurnom(){
        return this.surnom;
    }
    public String getDateNaissance(){
        return this.dateNaissance;
    }
    public String getDateDeces(){
        return this.dateDeces;
    }
    public String getNationalite(){
        return this.nationalite;
    }
    public int getTaille(){
        return this.taille;
    }
    public double getPoids(){
        return this.poids;
    }
    
    //Les mutators
    public void setNomCourant(String nomCourant){
        this.nomCourant=nomCourant;
    }
    public void setSurnom(String surnom){
        this.surnom=surnom;
    }
    public void setDateDeces(String dateDeces){
        this.dateDeces=dateDeces;
    }
    public void setTaille(int taille){
        this.taille=taille;
    }
    public void setPoids(int poids){
        this.poids=poids;
    }
    public int getAge(String dateNaissance){
        String todayDate = java.time.LocalDate.now().toString();
        int yearToday = Integer.parseInt(todayDate.substring(0, 3));
        int monthToday = Integer.parseInt(todayDate.substring(5, 6));
        int dayToday = Integer.parseInt(todayDate.substring(8, 9));
        int birthYear = Integer.parseInt(dateNaissance.substring(0,3));
        int birthMonth = Integer.parseInt(dateNaissance.substring(4,5));
        int birthDay = Integer.parseInt(dateNaissance.substring(6,7));
        int age = yearToday - birthYear;
        if (monthToday < birthMonth){
            age -= 1;
        }
        if (monthToday == birthMonth){
            if (dayToday < birthDay ){
                age -= 1;
            }
        }
        return age;
    }
    public int getAge(String dateNaissance, String dateDeces){
        int birthYear = Integer.parseInt(dateNaissance.substring(0,3));
        int birthMonth = Integer.parseInt(dateNaissance.substring(4,5));
        int birthDay = Integer.parseInt(dateNaissance.substring(6,7));
        int deathYear = Integer.parseInt(dateNaissance.substring(0,3));
        int deathMonth = Integer.parseInt(dateNaissance.substring(4,5));
        int deathDay = Integer.parseInt(dateNaissance.substring(6,7));
        int age = deathYear - birthYear;
        if (deathMonth < birthMonth){
            age -= 1;
        }
        if (deathMonth == birthMonth){
            if (deathDay < birthDay ){
                age -= 1;
            }
        }
        return age;
    }

    @Override
    public String toString() {
        return prenom + " " + nomNaissance;
    }
    
    
}
