/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIServices.IservicesSalma;
import PIClass.Participation;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASuS
 */

public class ServiceParticipation implements IservicesSalma <Participation>{
    
    Connection cnx = MyConnection.getInstance().getCnx();
    
    @Override
    public void envoyer(Participation p){
        try{
            String ajout = "INSERT INTO Participation (id_user, id_event, username, nbr_par) VALUES (?,?,?,?+1)";
            PreparedStatement st1 = cnx.prepareStatement(ajout);
            st1.setInt(1,p.getId_user());
            st1.setInt(2,p.getId_event());
            st1.setString(3,p.getUsername());
            st1.setInt(4,p.getNbr_par());
            st1.executeUpdate();
            System.out.println("Participant ajouté");
        } catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }  
    
    @Override
    public void supprimer(Participation p) {
        try {
            String supp = "DELETE FROM Participation WHERE id_par = ?";
            PreparedStatement st2 = cnx.prepareStatement(supp);
            st2.setInt(1, p.getId_par());
            st2.executeUpdate();
            System.out.println("Participant supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public List <Participation> afficher() {
        List <Participation> l2 = new ArrayList<>();
        try {
            String aff = "SELECT * FROM Participation";
            PreparedStatement st3 = cnx.prepareStatement(aff);
            ResultSet rs2 = st3.executeQuery();
            while (rs2.next()) {
                l2.add(new Participation(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4), rs2.getString(5),rs2.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l2;
    }
    
    public int calculNbrPar(int p) {
        List <Participation> l3 = new ArrayList<>();
        try {
            String cal = "SELECT * FROM Participation WHERE id_event = "+p;
            PreparedStatement st4 = cnx.prepareStatement(cal);
            ResultSet rs3 = st4.executeQuery();
            while (rs3.next()) {
                l3.add(new Participation(rs3.getInt(1),rs3.getInt(2),rs3.getInt(3),rs3.getInt(4), rs3.getString(5),rs3.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l3.size();
    }

    @Override
    public void modifier(Participation p) {
        
    }
}

    
   
       
