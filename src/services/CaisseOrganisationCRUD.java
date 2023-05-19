package services;

import entities.CaisseOrganisation;
import entities.Organisation;
import interfaces.CaisseOrganisationInterface;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaisseOrganisationCRUD implements CaisseOrganisationInterface {
    Statement statement;
    Connection connection= MyConnection.getInstance().getConn();
    @Override
    public void ajouterCaisse(CaisseOrganisation caisseOrganisation) {

        try {
            String req = "INSERT INTO `caisse_organisation`(`organisation_id`, `montant_caisse_org`, `goal`, `description`) VALUES ('"+caisseOrganisation.getOrganisation_id() + "','" + caisseOrganisation.getMontant_caisse_org() + "','" + caisseOrganisation.getGoal() + "','" + caisseOrganisation.getDescription() + "')";
            statement = connection.createStatement();
            statement.executeUpdate(req);

            System.out.println("Caisse ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("Caisse non ajouter");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCaisse(int id, float goal, String description) {
        try {
            String requete = "UPDATE `caisse_organisation` SET " + "goal = ?, description  = ? WHERE id= " + id;
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete);
            pst.setString(1, String.valueOf(goal));
            pst.setString(2, description);
            pst.executeUpdate();
            System.out.println("Caisse modifié avec succés !");
            System.out.println(requete);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }





}
