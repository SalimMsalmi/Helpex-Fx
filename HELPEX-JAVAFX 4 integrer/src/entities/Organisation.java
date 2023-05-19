package entities;

import utils.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Organisation {
    private int id;
    private String description;
    private String email;
    private String numero_tel;
    private String document;
    private String payment_info;
    private String nom;
    private String logo;
    private List<CaisseOrganisation> caisseOrganisations=null;

    public List<CaisseOrganisation> getCaisseOrganisations() {
        return caisseOrganisations;
    }

    public void setCaisseOrganisations(List<CaisseOrganisation> caisseOrganisations) {
        this.caisseOrganisations = caisseOrganisations;
    }

    public Organisation() {
    }

    public Organisation( String description, String email, String numero_tel, String document, String payment_info, String nom, String logo) {
        this.description = description;
        this.email = email;
        this.numero_tel = numero_tel;
        this.document = document;
        this.payment_info = payment_info;
        this.nom = nom;
        this.logo = logo;
    }

    public Organisation(int id, String description, String email, String numero_tel, String document, String payment_info, String nom, String logo) {
        this.id = id;
        this.description = description;
        this.email = email;
        this.numero_tel = numero_tel;
        this.document = document;
        this.payment_info = payment_info;
        this.nom = nom;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(String payment_info) {
        this.payment_info = payment_info;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", numero_tel='" + numero_tel + '\'' +
                ", document='" + document + '\'' +
                ", payment_info='" + payment_info + '\'' +
                ", nom='" + nom + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
    public double getsommeCaisses() throws SQLException {
        Statement statement;
        Connection connection= MyConnection.getInstance().getConn();
        statement = connection.createStatement();
       ResultSet rs = statement.executeQuery("SELECT SUM(montant_caisse_org) FROM caisse_organisation WHERE organisation_id= '"+this.id+"'");

        while (rs.next()){
            return rs.getDouble(1);
        }
        return 0;
    }
}
