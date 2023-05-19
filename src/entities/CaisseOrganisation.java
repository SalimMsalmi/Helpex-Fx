package entities;

import utils.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CaisseOrganisation {
    int id;
    String description;
    float montant_caisse_org;
    float goal;
    int organisation_id;

    public CaisseOrganisation(int id, int organisation_id,  float montant_caisse_org, float goal,String description) {
        this.id = id;
        this.description = description;
        this.montant_caisse_org = montant_caisse_org;
        this.goal = goal;
        this.organisation_id = organisation_id;
    }

    public CaisseOrganisation(int organisation_id,float montant_caisse_org, float goal,String description) {
        this.description = description;
        this.montant_caisse_org = montant_caisse_org;
        this.goal = goal;
        this.organisation_id = organisation_id;
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

    public float getMontant_caisse_org() {
        return montant_caisse_org;
    }

    public void setMontant_caisse_org(float montant_caisse_org) {
        this.montant_caisse_org = montant_caisse_org;
    }

    public float getGoal() {
        return goal;
    }

    public void setGoal(float goal) {
        this.goal = goal;
    }

    public int getOrganisation_id() {
        return organisation_id;
    }

    public void setOrganisation_id(int organisation_id) {
        this.organisation_id = organisation_id;
    }
}
