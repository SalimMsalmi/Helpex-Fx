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

public class Centre {
    private int id;
    private String nomCentre;
    private String adresseCentre;
    private String emailCentre;
    private int telephoneCentre;
    private String siteWebCentre;

    @Override
    public String toString() {
        return "Centre{" + "id=" + id + ", nomCentre=" + nomCentre + ", adresseCentre=" + adresseCentre + ", emailCentre=" + emailCentre + ", telephoneCentre=" + telephoneCentre + ", siteWebCentre=" + siteWebCentre + '}';
    }

    public Centre(int id, String nomCentre, String adresseCentre, String emailCentre, int telephoneCentre, String siteWebCentre) {
        this.id = id;
        this.nomCentre = nomCentre;
        this.adresseCentre = adresseCentre;
        this.emailCentre = emailCentre;
        this.telephoneCentre = telephoneCentre;
        this.siteWebCentre = siteWebCentre;
    }

    public Centre(String nomCentre, String adresseCentre, String emailCentre, int telephoneCentre, String siteWebCentre) {
        this.nomCentre = nomCentre;
        this.adresseCentre = adresseCentre;
        this.emailCentre = emailCentre;
        this.telephoneCentre = telephoneCentre;
        this.siteWebCentre = siteWebCentre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCentre() {
        return nomCentre;
    }

    public void setNomCentre(String nomCentre) {
        this.nomCentre = nomCentre;
    }

    public String getAdresseCentre() {
        return adresseCentre;
    }

    public void setAdresseCentre(String adresseCentre) {
        this.adresseCentre = adresseCentre;
    }

    public String getEmailCentre() {
        return emailCentre;
    }

    public void setEmailCentre(String emailCentre) {
        this.emailCentre = emailCentre;
    }

    public int getTelephoneCentre() {
        return telephoneCentre;
    }

    public void setTelephoneCentre(int telephoneCentre) {
        this.telephoneCentre = telephoneCentre;
    }

    public String getSiteWebCentre() {
        return siteWebCentre;
    }

    public void setSiteWebCentre(String siteWebCentre) {
        this.siteWebCentre = siteWebCentre;
    }

    public Centre() {
    }
    
    
}
