/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Poste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;
import interfaces.InterfacePoste;

/**
 *
 * @author ASUS
 */
public class CRUDPoste implements InterfacePoste{
    Statement ste;
    Connection conn= MyConnection.getInstance().getConn();
    

    @Override
    public void ajouterPoste(Poste c) {
        try {
                    String req="INSERT INTO `poste`(`titre`, `description`) VALUES ('"+c.getTitre()+"','"+c.getDescription()+"')";

            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println("poste ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("poste non ajouter");        }
    }

    @Override
    public void ajouterCentre2(Poste c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierPoste(Poste c,String titre,String description) {
       try {
            String requete4 = " UPDATE poste SET " + "  titre= ?, description = ? WHERE id= " + c.getId();
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete4);
            pst.setString(1, titre);
            pst.setString(2, description);
            pst.executeUpdate();
            System.out.println("poste modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerPoste(Poste c) {
         try {
        String req = "DELETE FROM poste WHERE id='"+c.getId()+"'";
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println("poste supprimé avec succès");
    } catch (SQLException ex) {
        System.out.println("erreur lors de la suppression du Poste");
    }
    }

    @Override
    public List<Poste> afficherPoste() {
List<Poste> list=new ArrayList<>();

String req="SELECT * FROM `poste` "; 
Statement ste;
        try {
            
            ste = conn.createStatement();
                    ResultSet RS=ste.executeQuery(req);
                    while(RS.next()){
                    Poste c =new Poste();
                    c.setId(RS.getInt(1));
                   c.setTitre(RS.getString("titre"));
                                        c.setDescription(RS.getString("description"));

                                                      
        list.add(c);

                    }
            System.out.println("affichage");
        } catch (SQLException ex) {
            System.out.println("erreur d afficahge");        }
return list;
    }

    @Override
    public Poste findbyid(int id) {
        String req="SELECT * FROM poste WHERE id='+id'"; 
        Statement ste;
                    Poste c =new Poste();

        try {
            
            ste = conn.createStatement();
                    ResultSet RS=ste.executeQuery(req);
                    while(RS.next()){
                    c.setId(RS.getInt(1));
                   c.setTitre(RS.getString("titre"));
                                        c.setDescription(RS.getString("description"));
                                        

    }
                     } catch (SQLException ex) {
            System.out.println("erreur d afficahge");        }
    
    return c;
}
}