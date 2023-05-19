/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import java.sql.Date;
import PIUtils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.scene.control.TableColumn;
import PIClass.Ev;
import PIClass.Invitation;

/**
 *
 * @author Islem
 */
public class GererEv implements IservicesIslem<Ev> {
java.sql.Connection cnx = MyConnection.getInstance().getCnx();

@Override
   public void supprimer(Ev e) {
        try {
         String requete = "DELETE FROM evenement WHERE titre_ev=?";//
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,e.getTitre_ev());
            pst.executeUpdate();
            System.out.println("Evénement supprimée avec succés  ! \n");
        } 
catch (SQLException ex) {
            System.out.println("erreur lors de la suppression \n" + ex.getMessage());
        }
    } 

//    @Override
    public void ajouter(Ev sp) {
            try {
            String requete = "INSERT INTO evenement VALUES (null,?,?,?,?,?,?,?,?,?,null)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, sp.getTitre_ev());
            pst.setString(2, sp.getType_ev());
            pst.setString(3, sp.getEmplacement_ev());
            pst.setDate(4, sp.getDate_dev());
            pst.setDate(5, sp.getDate_fev());
            pst.setTime(6, sp.getTemps_dev());
            pst.setTime(7, sp.getTemps_fev());
            pst.setInt(8, sp.getAge_max());
            pst.setInt(9, sp.getAge_min());
           
            pst.executeUpdate();
            System.out.println("Evenement ajoutée !");

        }  catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Ev e) {
       

try { 
            String requete = "UPDATE `evenement` SET titre_ev=?, type_ev=?,emplacement_ev=?,"
                    + " date_dev=?, date_fev =?,temps_dev =?, temps_fev =?, age_max =?, age_min =? WHERE id_ev= ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(10, e.getId_ev());
            pst.setString(1, e.getTitre_ev());
            pst.setString(2, e.getType_ev());
            pst.setString(3, e.getEmplacement_ev());
            pst.setDate(4,  e.getDate_dev());
            pst.setDate(5,  e.getDate_fev());
            pst.setTime(6, e.getTemps_dev());
            pst.setTime(7, e.getTemps_fev());
            pst.setInt(8, e.getAge_max());
            pst.setInt(9, e.getAge_min());
            
            

            pst.executeUpdate();
            System.out.println("Evenement modifié avec succés \n ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modification \n " + ex.getMessage());
        }
    }
@Override
       public ArrayList<Ev> afficher() {
        ArrayList<Ev> evenements = new ArrayList<>();
        String requete = "Select * from evenement";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                evenements.add(new Ev( rs.getInt(1), rs.getString(2), rs.getString (3),rs.getString (4),rs.getDate (5),rs.getDate (6),rs.getString (7),rs.getString(8),rs.getInt(9),rs.getInt(10)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return evenements;
    }


    public ArrayList<Ev> afficherEventparActivite(String x) {
       ArrayList<Ev> evenements = new ArrayList<>();
    try {
        String requete = "Select * from evenement e INNER join act act where( e.id_act=act.id_act and act.nom_act ='"+x+"'"+")";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
       while(rs.next()){
           evenements.add(new Ev( rs.getInt(1), rs.getString(2), rs.getString (3),rs.getString (4),rs.getDate (5),rs.getDate (6),rs.getString (7),rs.getString(8),rs.getInt(9),rs.getInt(10)));
       }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return evenements;
        
    }

  public static Comparator<Ev> EvComparator = (Ev s1, Ev s2) -> {
        String ev1 = s1.getTitre_ev();
        String ev2 = s2.getTitre_ev();
        return ev1.compareTo(ev2);
    };
    public ArrayList<Ev> triNom() {
        ArrayList<Ev> evenements = new ArrayList<>();
        String requete = "select * from evenement ";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                evenements.add(new Ev( rs.getInt(1), rs.getString(2), rs.getString (3),rs.getString (4),rs.getDate (5),rs.getDate (6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10)));
            }
            Collections.sort(evenements,EvComparator );
            Collections.reverse(evenements);
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return evenements;
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
    public int nombreInvite(String titre) {
        ArrayList<Invitation> inv = new ArrayList<>();
        String requete = "select * from invitation where id_ev= ?" ;
    try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, getTitre(titre));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                inv.add(new Invitation(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
    return inv.size();
    }

    
    public ArrayList<Ev> RechercheNom(String x) {
        
        ArrayList<Ev> evenements = new ArrayList<>();
        String requete = "select * from evenement where titre_ev ='"+x+"'";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                evenements.add(new Ev( rs.getInt(1), rs.getString(2), rs.getString (3),rs.getString (4),rs.getDate (5),rs.getDate(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return evenements;
    }

    public void supprimer(TableColumn<Ev, String> colnom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

   
    

