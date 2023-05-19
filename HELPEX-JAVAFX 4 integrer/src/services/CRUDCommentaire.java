/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Commentaire;
import entities.Poste;
import interfaces.InterfaceCommentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static javafx.scene.input.KeyCode.P;
import utils.MyConnection;


/**
 *
 * @author USER
 */
public class CRUDCommentaire implements InterfaceCommentaire{
    Statement ste;
    Connection conn= MyConnection.getInstance().getConn();
    @Override
    public void ajouterCommentaire(Commentaire c,Poste P) {
         try {
                    String req="INSERT INTO `commentaire`(`description`, `poste_id`) VALUES ('"+c.getDescription()+"','"+P.getId()+"')";

            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println("commentaire ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("commentaire non ajouter");        }
    }

    @Override
    public void modifierCommentaire(Commentaire c, String description) {
     try {
            String requete4 = " UPDATE commentaire SET " + " description = ? WHERE id= " + c.getId();
            PreparedStatement pst = MyConnection.getInstance().getConn().prepareStatement(requete4);
            pst.setString(1, description);
            pst.executeUpdate();
            System.out.println("commentaire modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCommentaire(Commentaire c) {
 try {
        String req = "DELETE FROM Commentaire WHERE id='"+c.getId()+"'";
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println("Commentaire supprimé avec succès");
    } catch (SQLException ex) {
        System.out.println("erreur lors de la suppression du commentaire");
    }
    }

    @Override
    public List<Commentaire> findbyposte(int id) {
            return null;
    }

 
   

    
    
}
