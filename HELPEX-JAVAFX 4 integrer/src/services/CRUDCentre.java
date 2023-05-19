/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Centre;
import interfaces.InterfaceCentre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CRUDCentre implements InterfaceCentre{
    Statement ste;
    Connection conn= MyConnection.getInstance().getConn();
    

    @Override
    public void ajouterCentre(Centre c) {
        try {
                    String req="INSERT INTO `centre`(`nom_centre`, `adresse_centre`, `email_centre`, `telephone_centre`, `site_web_centre`) VALUES ('"+c.getNomCentre()+"','"+c.getAdresseCentre()+"','"+c.getEmailCentre()+"','"+c.getTelephoneCentre()+"','"+c.getSiteWebCentre()+"')";

            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println("centre ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("centre non ajouter");        }
    }

    @Override
    public void ajouterCentre2(Centre c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCentre(Centre c,String nomCentre,String adresseCentre,String emailCentre,int telephoneCentre,String siteWebCentre) {
        try {
   String requete4 = " UPDATE centre SET " + "  nom_centre= ?, adresse_centre = ?, email_centre  = ?, telephone_centre = ?, site_web_centre = ? WHERE id= " + c.getId();
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete4);
            pst.setString(1, nomCentre);
            pst.setString(2, adresseCentre);
            pst.setString(3, emailCentre);
            pst.setInt(4, telephoneCentre);
            pst.setString(5, siteWebCentre);
            pst.executeUpdate();
            System.out.println("centre modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCentre(Centre c) {
         try {
        String req = "DELETE FROM centre WHERE id='"+c.getId()+"'";
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println("centre supprimé avec succès");
    } catch (SQLException ex) {
        System.out.println("erreur lors de la suppression du centre");
    }
    }

    @Override
  public List<Centre> affichercentre() {
        List<Centre> list=new ArrayList<>();


        String req="SELECT * FROM `centre` ";
        Statement ste;
        try {

            ste = conn.createStatement();
            ResultSet RS=ste.executeQuery(req);
            while(RS.next()){
                Centre c =new Centre();
                c.setId(RS.getInt(1));
                //c.setId(RS.getInt(1));
                    c.setNomCentre(RS.getString("nom_centre"));
                                        c.setAdresseCentre(RS.getString("adresse_centre"));

                                                            c.setEmailCentre(RS.getString("email_centre"));

                                                          c.setTelephoneCentre(RS.getInt("telephone_centre"));
                                        c.setSiteWebCentre(RS.getString("site_web_centre"));
                list.add(c);

            }
            System.out.println("affichage avec succés");
        } catch (SQLException ex) {
            System.out.println("erreur d'afficahge");
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public Centre findbyid(int id) {
    String req = "SELECT * FROM centre WHERE id=" + id; 
    Statement ste;
    Centre c = new Centre();

    try {
        ste = conn.createStatement();
        ResultSet RS = ste.executeQuery(req);
        while (RS.next()) {
            c.setId(RS.getInt(1));
            c.setNomCentre(RS.getString("nom_centre"));
            c.setAdresseCentre(RS.getString("adresse_centre"));
            c.setEmailCentre(RS.getString("email_centre"));
            c.setTelephoneCentre(RS.getInt("telephone_centre"));
            c.setSiteWebCentre(RS.getString("site_web_centre"));
        }
    } catch (SQLException ex) {
        System.out.println("erreur get by id ");        
    }
    
    return c;
}
}
