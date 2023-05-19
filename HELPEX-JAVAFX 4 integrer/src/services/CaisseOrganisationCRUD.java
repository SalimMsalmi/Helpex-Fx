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
    
     public List<CaisseOrganisation> afficher() {
        List<CaisseOrganisation> list=new ArrayList<>();


        String req="SELECT * FROM `caisse_organisation`";
        Statement ste;
        try {

            ste = connection.createStatement();
            ResultSet RS=ste.executeQuery(req);
            while(RS.next()){
                CaisseOrganisation organisation =new CaisseOrganisation();
                organisation.setId(RS.getInt(1));
                
                organisation.setDescription(RS.getString("description"));
                organisation.setMontant_caisse_org(RS.getFloat("montant_caisse_org"));
                organisation.setGoal(RS.getFloat("goal"));
                organisation.setOrganisation_id(RS.getInt("organisation_id"));
                
                list.add(organisation);

            }
            System.out.println("affichage avec succés");
        } catch (SQLException ex) {
            System.out.println("erreur d'afficahge");
            System.out.println(ex.getMessage());
        }
        return list;
    }

     public CaisseOrganisation CaisseById(int id) {
        CaisseOrganisation caisse=new CaisseOrganisation();


        String req="SELECT * FROM `caisse_organisation` where id='"+id+"';";
        Statement ste;
        try {

            ste = connection.createStatement();
            ResultSet RS=ste.executeQuery(req);
            while(RS.next()){
                caisse.setId(RS.getInt(1));
                
                caisse.setDescription(RS.getString("description"));
                caisse.setMontant_caisse_org(RS.getFloat("montant_caisse_org"));
                caisse.setGoal(RS.getFloat("goal"));
                caisse.setOrganisation_id(RS.getInt("organisation_id"));
                

            }
            System.out.println("affichage avec succés");
        } catch (SQLException ex) {
            System.out.println("erreur d'afficahge");
            System.out.println(ex.getMessage());
        }
        return caisse;
    }

     public void modifierMontantCaisse(int id, float montant) {
        try {
            String requete = "UPDATE `caisse_organisation` SET " + "montant_caisse_org = ?  WHERE id= " + id;
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete);
            pst.setString(1, String.valueOf(montant));
            pst.executeUpdate();
            System.out.println("Caisse modifié avec succés !");
            System.out.println(requete);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
