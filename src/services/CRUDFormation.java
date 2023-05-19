/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Formation;
import interfaces.InterfaceFormation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CRUDFormation implements InterfaceFormation{
 Statement ste;
    Connection conn= MyConnection.getInstance().getConn();
    @Override
    public void ajouterFormation(Formation f) {
   try {
                           //String reqq="INSERT INTO `centre`(`nom_centre`, `adresse_centre`, `email_centre`, `telephone_centre`, `site_web_centre`) VALUES ('"+c.getNomCentre()+"','"+c.getAdresseCentre()+"','"+c.getEmailCentre()+"','"+c.getTelephoneCentre()+"','"+c.getSiteWebCentre()+"')";

       
                    String req="INSERT INTO `formation`(`nom_formation`, `description_formation`, `cout_formation`, `nombre_de_place`, `duree`, `id_centre_id`) VALUES ('"+f.getNomFormation()+"','"+f.getDescriptionFormation()+"','"+f.getCoutFormation()+"','"+f.getNombreDePlace()+"','"+f.getDuree()+"','"+f.getIdCentre().getId()+"')";

            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println("formation ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("formation non ajouter");        }    }

    @Override
    public void modifierFormation(Formation f, String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree) {
  try {
   String requete4 = " UPDATE formation SET " + "  nom_formation= ?, description_formation = ?, cout_formation  = ?, Nombre_de_place = ?, duree = ? WHERE id= " + f.getId();
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete4);
            pst.setString(1, nomFormation);
            pst.setString(2, descriptionFormation);
            pst.setFloat(3, coutFormation);
            pst.setInt(4, NombreDePlace);
            pst.setString(5, duree);
            pst.executeUpdate();
            System.out.println("formation modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimerFormation(Formation f) {
  try {
        String req = "DELETE FROM formation WHERE id='"+f.getId()+"'";
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println("formation supprimé avec succès");
    } catch (SQLException ex) {
        System.out.println("erreur lors de la suppression de la formation");
    }    }

    @Override
    public List<Formation> afficherFormation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
