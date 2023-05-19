package services;

import entities.Organisation;
import interfaces.OrganisationInterface;
import utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganisationCRUD implements OrganisationInterface {
    Statement statement;
    Connection connection= MyConnection.getInstance().getConn();
    @Override
    public void ajouterOrg(Organisation organisation) {
        try {
            String req = "INSERT INTO `organisation`(`description_organisation`, `email_organisation`,`num_tel_organisation`,`document_organisation`,`payment_info`,`nom_org`,`logo_org`) VALUES ('"+organisation.getDescription() + "','" + organisation.getEmail() + "','" + organisation.getNumero_tel() + "','" + organisation.getDocument() + "','" + organisation.getPayment_info() + "','" + organisation.getNom() + "','" + organisation.getLogo() + "')";
            statement = connection.createStatement();
            statement.executeUpdate(req);

            System.out.println("organisation ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("organisation non ajouter");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierOrg(int organisationID, String description, String email, String numero_tel, String document, String payment_info, String nom, String logo) {
        try {
            String requete = " UPDATE organisation SET " + "description_organisation= ?, email_organisation = ?, num_tel_organisation  = ?, document_organisation = ?, payment_info = ?, nom_org = ?, logo_org = ? WHERE id= " + organisationID;
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete);
            pst.setString(1, description);
            pst.setString(2, email);
            pst.setString(3, numero_tel);
            pst.setString(4, document);
            pst.setString(5, payment_info);
            pst.setString(6, nom);
            pst.setString(7, logo);
            pst.executeUpdate();
            System.out.println("Organisation modifié avec succés !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerOrg(Organisation organisation) {
        try {
            String req1="DELETE FROM caisse_organisation WHERE organisation_id='"+organisation.getId()+"'";
            statement= connection.createStatement();
            statement.execute(req1);
            String req = "DELETE FROM organisation WHERE id='"+organisation.getId()+"'";
            statement=connection.createStatement();
            statement.executeUpdate(req);
            System.out.println("organisation supprimé avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression du organisation");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Organisation> afficherOrg() {
        List<Organisation> list=new ArrayList<>();


        String req="SELECT * FROM `organisation` ";
        Statement ste;
        try {

            ste = connection.createStatement();
            ResultSet RS=ste.executeQuery(req);
            while(RS.next()){
                Organisation organisation =new Organisation();
                organisation.setId(RS.getInt(1));
                organisation.setNom(RS.getString("nom_org"));
                organisation.setDescription(RS.getString("description_organisation"));

                organisation.setEmail(RS.getString("email_organisation"));

                organisation.setNumero_tel(RS.getString("num_tel_organisation"));
                organisation.setDocument(RS.getString("document_organisation"));
                organisation.setPayment_info(RS.getString("payment_info"));
                organisation.setLogo(RS.getString("logo_org"));
                list.add(organisation);

            }
            System.out.println("affichage avec succés");
        } catch (SQLException ex) {
            System.out.println("erreur d'afficahge");
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    public Organisation OrgByID(int id) {
        Organisation organisation=new Organisation();


        String req="SELECT * FROM `organisation` where id='"+id+"';";
        Statement ste;
        try {

            ste = connection.createStatement();
            ResultSet RS=ste.executeQuery(req);
            while(RS.next()){
                organisation.setId(RS.getInt(1));
                organisation.setNom(RS.getString("nom_org"));
                organisation.setDescription(RS.getString("description_organisation"));

                organisation.setEmail(RS.getString("email_organisation"));

                organisation.setNumero_tel(RS.getString("num_tel_organisation"));
                organisation.setDocument(RS.getString("document_organisation"));
                organisation.setPayment_info(RS.getString("payment_info"));
                organisation.setLogo(RS.getString("logo_org"));

            }
            System.out.println("affichage avec succés");
        } catch (SQLException ex) {
            System.out.println("erreur d'afficahge");
            System.out.println(ex.getMessage());
        }
        return organisation;
    }
}
