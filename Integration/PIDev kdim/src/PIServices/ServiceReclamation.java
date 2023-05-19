/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import PIClass.Reclamation;
import PIServices.IservicesSalma;
import PIUtils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ASuS
 */
public class ServiceReclamation implements IservicesSalma <Reclamation> {
    
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void envoyer(Reclamation p) {
       try {
            String envoi = "INSERT INTO Reclamation (id_user, username, obj_rec, area_rec, suj_rec, date_rec) VALUES (?,?,?,?,?,CURRENT_TIMESTAMP)";
            PreparedStatement st1 = cnx.prepareStatement(envoi);
            st1.setInt(1, p.getId_user());
            st1.setString(2, p.getUsername());
            st1.setString(3, p.getObj_rec());
            st1.setString(4, p.getArea_rec());
            st1.setString(5, p.getSuj_rec());
            
            st1.executeUpdate();
            System.out.println("Reclamation envoyée avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'envoi " + ex.getMessage());
        }
    }

    public void suppid(int p) {
        try {
            String supp = "DELETE FROM Reclamation WHERE id_rec = ? ";
            PreparedStatement st2 = cnx.prepareStatement(supp);
            st2.setInt(1, p);
            st2.executeUpdate();
            System.out.println("Reclamation supprimée avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    public void suppetat(Reclamation p) {
        try {
            String supp = "DELETE FROM Reclamation WHERE etat_rec LIKE 'Done' ";
            PreparedStatement st2 = cnx.prepareStatement(supp);
            st2.executeUpdate();
            System.out.println("Reclamation supprimée avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> l1 = new ArrayList<>();
        try {
            String aff = "SELECT * FROM Reclamation";
            PreparedStatement st3 = cnx.prepareStatement(aff);
            ResultSet rs1 = st3.executeQuery();
//            System.out.println(rs1.next());
            while (rs1.next()) {
                Reclamation Reclamation = new Reclamation();
                Reclamation.setId_rec(rs1.getInt(1));
                Reclamation.setId_user(rs1.getInt(2));
                Reclamation.setUsername(rs1.getString(3));
                Reclamation.setObj_rec(rs1.getString(4));
                Reclamation.setArea_rec(rs1.getString(5));
                Reclamation.setSuj_rec(rs1.getString(6));
                Reclamation.setEtat_rec(rs1.getString(7));
                Reclamation.setDate_rec(rs1.getTimestamp(8));
                l1.add(Reclamation);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement " + ex.getMessage());
        }
        return l1;
    }
    
    public List<Reclamation> afficherIdUser(int id) {
        List<Reclamation> l5 = new ArrayList<>();
        try {
            String aff = "SELECT * FROM Reclamation WHERE id_user = ?";
            PreparedStatement st3 = cnx.prepareStatement(aff);
            st3.setInt(1, id);
            ResultSet rs1 = st3.executeQuery();
//            System.out.println(rs1.next());
            while (rs1.next()) {
                Reclamation Reclamation = new Reclamation();
                Reclamation.setId_rec(rs1.getInt(1));
                Reclamation.setId_user(rs1.getInt(2));
                Reclamation.setUsername(rs1.getString(3));
                Reclamation.setObj_rec(rs1.getString(4));
                Reclamation.setArea_rec(rs1.getString(5));
                Reclamation.setSuj_rec(rs1.getString(6));
                Reclamation.setEtat_rec(rs1.getString(7));
                Reclamation.setDate_rec(rs1.getTimestamp(8));
                l5.add(Reclamation);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors du chargement " + ex.getMessage());
        }
        return l5;
    }
    
    @Override
    public void modifier(Reclamation p) {
        try {
            String mod = "UPDATE Reclamation SET etat_rec= ? WHERE id_rec = ?";
            PreparedStatement st4 = cnx.prepareStatement(mod);
            st4.setString(1, p.getEtat_rec());
            st4.setInt(2, p.getId_rec());
            st4.executeUpdate();
            System.out.println("Mise à jour de la réclamation effectuée avec succès");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la modification " + ex.getMessage());
        }
    }
    
    public Reclamation searchIdRec (int id) {
        try {
            String rech = "SELECT * FROM reclamation WHERE id_rec = ?";
            PreparedStatement st = cnx.prepareStatement(rech);
            st.setInt(1,id);
            ResultSet rs2 = st.executeQuery();
            if(rs2.next()){
                Reclamation Rec = new Reclamation();
                Rec.setId_rec(rs2.getInt(1));
                Rec.setId_user(rs2.getInt(2));
                Rec.setUsername(rs2.getString(3));
                Rec.setObj_rec(rs2.getString(4));
                Rec.setArea_rec(rs2.getString(5));
                Rec.setSuj_rec(rs2.getString(6));
                Rec.setEtat_rec(rs2.getString(7));
                Rec.setDate_rec(rs2.getTimestamp(8));
                return Rec;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
        }
    
    System.out.println("Not found");
    return null;
    }
    
    public List<Reclamation> searchAreaRec(String lieu) {
        ArrayList<Reclamation> l2 = new ArrayList<>();
        try {
            String rech = "SELECT * FROM reclamation WHERE area_rec = ?";
            PreparedStatement st = cnx.prepareStatement(rech);
            st.setString(1, lieu);
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                Reclamation Rec = new Reclamation();
                Rec.setId_rec(rs.getInt(1));
                Rec.setId_user(rs.getInt(2));
                Rec.setUsername(rs.getString(3));
                Rec.setObj_rec(rs.getString(4));
                Rec.setArea_rec(rs.getString(5));
                Rec.setSuj_rec(rs.getString(6));
                Rec.setEtat_rec(rs.getString(7));
                Rec.setDate_rec(rs.getTimestamp(8));
                if (Rec.getArea_rec().equals(lieu)) {
                    l2.add(Rec);
                } 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (l2.isEmpty()) {
            System.out.println("Not found !");
        }
        return l2;
    }
    
    public List <Reclamation> tri_Date_Desc(){ 
        List <Reclamation> l3 = new ArrayList<>();
        try {
            String tri = "SELECT * FROM Reclamation";
            PreparedStatement st6 = cnx.prepareStatement(tri);
            ResultSet rs2=st6.executeQuery();
            while (rs2.next()) {
                l3.add(new Reclamation(rs2.getInt("id_rec"),rs2.getInt("id_user"),rs2.getString("username"), rs2.getString("obj_rec"),rs2.getString("area_rec"),rs2.getString("suj_rec"),rs2.getString("etat_rec"),rs2.getTimestamp("date_rec")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l3.stream().sorted((a,b)->b.getDate_rec().compareTo(a.getDate_rec())).collect(Collectors.toList());
    }

    @Override
    public void supprimer(Reclamation p) {
        try {
            String supp = "DELETE FROM Reclamation WHERE id_rec = ?";
            PreparedStatement pst = cnx.prepareStatement(supp);
            pst.setInt(1, p.getId_rec());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}