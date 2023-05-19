/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIUtils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import PIClass.Invitation;
/**
 *
 * @author Islem
 */
public class GesInv implements IservicesIslem <Invitation>{
 java.sql.Connection cnx = MyConnection.getInstance().getCnx();
   
 @Override
    public void ajouter(Invitation i) {
//        try {
            
            String requete = "INSERT INTO invitation (id_user,id_event) VALUES (?,?)";
            
//            PreparedStatement pst3 = cnx.prepareStatement(requete);
////            pst3.setInt(1, i.getId());
//            pst3.setInt(2, i.getUser(""));
//            pst3.setString(3, i.getTitre());
//            pst3.executeUpdate();
//            System.out.println("invitation envoyé !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }
     public void ajouterAp(String titre,String username) {
        try {
            
            String requete = "INSERT INTO invitation (id_user,id_ev) VALUES (?,?)";
            
            PreparedStatement pst3 = cnx.prepareStatement(requete);
//  
            pst3.setInt(1, getUser(username));
            pst3.setInt(2, getTitre(titre));
            pst3.executeUpdate();
            System.out.println("invitation envoyé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public void SupprimerAp(String titre,String username) {   try {
            
            String requete = "DELETE FROM invitation WHERE id_user=? and id_ev=?";
            
            PreparedStatement pst = cnx.prepareStatement(requete);
//  
            pst.setInt(1, getUser(username));
            pst.setInt(2, getTitre(titre));
            pst.executeUpdate();
            System.out.println("invitation supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
   public int getTitre(String titre_ev){

 try{
       
        PreparedStatement posted = cnx.prepareStatement("SELECT * FROM evenement where Titre_ev= ?");
        posted.setString(1,titre_ev);
        ResultSet result = posted.executeQuery();
        while(result.next())
        {
                return result.getInt("id_ev");
        }
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return -1;
}
      public int getUser(String username){

 try{
       
        PreparedStatement posted = cnx.prepareStatement("SELECT * FROM simple where username= ?");
        posted.setString(1,username);
        ResultSet result = posted.executeQuery();
        while(result.next())
        {
                return result.getInt("id_user");
        }
           }
           
        catch(SQLException e)
        {
            System.out.println(e);
        }
           return -1;
}

    @Override
    public void supprimer(Invitation i) {

    }

    @Override
    public void modifier(Invitation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invitation> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
