/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class Formation {
    private int id;
    private String nomFormation;
    private String descriptionFormation;
    private float coutFormation;
    private int NombreDePlace;
    private String duree;
    private Centre idCentre;

    public Formation(){
    }
    public Formation(String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree) {
        this.nomFormation = nomFormation;
        this.descriptionFormation = descriptionFormation;
        this.coutFormation = coutFormation;
        this.NombreDePlace = NombreDePlace;
        this.duree = duree;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public void setDescriptionFormation(String descriptionFormation) {
        this.descriptionFormation = descriptionFormation;
    }

    public void setCoutFormation(float coutFormation) {
        this.coutFormation = coutFormation;
    }

    public void setNombreDePlace(int NombreDePlace) {
        this.NombreDePlace = NombreDePlace;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setIdCentre(Centre idCentre) {
        this.idCentre = idCentre;
    }

    public int getId() {
        return id;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public String getDescriptionFormation() {
        return descriptionFormation;
    }

    public float getCoutFormation() {
        return coutFormation;
    }

    public int getNombreDePlace() {
        return NombreDePlace;
    }

    public String getDuree() {
        return duree;
    }

    public Centre getIdCentre() {
        return idCentre;
    }

    public Formation(String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree, Centre idCentre) {
        this.nomFormation = nomFormation;
        this.descriptionFormation = descriptionFormation;
        this.coutFormation = coutFormation;
        this.NombreDePlace = NombreDePlace;
        this.duree = duree;
        this.idCentre = idCentre;
    }

    public Formation(int id, String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree, Centre idCentre) {
        this.id = id;
        this.nomFormation = nomFormation;
        this.descriptionFormation = descriptionFormation;
        this.coutFormation = coutFormation;
        this.NombreDePlace = NombreDePlace;
        this.duree = duree;
        this.idCentre = idCentre;
    }
    
    
    
}
