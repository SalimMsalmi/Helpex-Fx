/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIUtils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import PIClass.Act;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Islem
 */
public class Activite implements IservicesIslem <Act>{
java.sql.Connection cnx = MyConnection.getInstance().getCnx();
@Override
    public void ajouter(Act t) {
        try {
            String requete = "INSERT INTO act (id_act,nom_act,type_act) VALUES (?,?,?)";
            PreparedStatement pst1 = cnx.prepareStatement(requete);
            pst1.setInt(1,t.getId_act());
            pst1.setString(2,t.getNom_act());
            pst1.setString(3, t.getType_act());
            pst1.executeUpdate();
            System.out.println("activité ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Act t) {
     try {
         String requete = "DELETE FROM act WHERE id_act=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,t.getNom_act());
            pst.executeUpdate();
            System.out.println("l'activité supprimée avec succés  ! \n");
        } 
catch (SQLException ex) {
            System.out.println("erreur lors de la suppression \n" + ex.getMessage());
        }
    } 
   

    @Override
    public void modifier(Act t) {
           
     
try { 
            String requete = "UPDATE `act` SET nom_act=?, type_act=? WHERE id_act= ?";;
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3,t.getId_act());
            pst.setString(1, t.getNom_act());
            pst.setString(2, t.getType_act());
            
            pst.executeUpdate();
            System.out.println("Activité modifié avec succés \n ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modification \n " + ex.getMessage());
        }
    }

    @Override
    public List<Act> afficher() {
        List<Act> activite = new ArrayList<>();
        String requete = "Select * from act";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
             activite.add(new Act( rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return activite;
    } 
      public static Comparator <Act> actComparator = (Act s1, Act s2) -> {
        String Act1 = s1.getNom_act();
        String Act2 = s2.getType_act();
        return Act1.compareTo(Act2);
      };
       public ArrayList<Act> RechercheNom(String x) {
        
        ArrayList<Act> act = new ArrayList<>();
        String requete = "select * from act where titre_ev ='"+x+"'";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

             act.add(new Act( rs.getString(1), rs.getString (2)));}
             Collections.sort(act,actComparator );
            Collections.reverse(act);
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return act;
    }
}

 


    