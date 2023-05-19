/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.InterfacePersonne;
import entites.poste;
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

public class CRUDPoste implements InterfacePersonne {
Statement ste;
Connection conn = MyConnection.getInstance().getConn();
    
    
    @Override
    public void ajouterPoste(poste p) {
     
    try {
       // String req0="INSERT INTO `personne`(`nom`, `prenom`) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"')"  ;
        String req="INSERT INTO `poste`(`titre`, `description`) VALUES ('"+p.getTitre()+"','" + p.getDescription()+"')";
        
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println("poste ajoutée avec succès!!");
    } catch (SQLException ex) {
         System.out.println("poste non ajoutée!!"); 
        System.out.println(ex.getMessage());}
     
    }

    @Override
    public void ajouterPoste2(poste p) {
    
    try {
        String req="INSERT INTO `personne`(`nom`, `prenom`) VALUES (?,?)";   
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, p.getTitre());
        ps.setString(2, p.getDescription());
        
        ps.executeUpdate();
    } catch (SQLException ex) {
         System.out.println(ex.getMessage());    }
        
            }

    @Override
    public void modifierPoste(poste p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerPoste(poste p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<poste> afficherPoste() {
      List<poste> list = new ArrayList<>(); 
      
      String req="SELECT * FROM `poste` ";
      
      Statement ste;
    try {
        ste = conn.createStatement();
        ResultSet RS = ste.executeQuery(req);
        while(RS.next()){
            poste p = new poste();
            p.setId(RS.getInt(1));
            p.setTitre(RS.getString("titre"));
             p.setDescription(RS.getString("description"));
             list.add(p);
            
        }
        System.out.println("ok");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());    }
        
        
    return list;
    }
    
}
