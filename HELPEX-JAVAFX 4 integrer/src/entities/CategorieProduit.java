/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.MyConnection;

/**
 *
 * @author FaroukDev
 */
public class CategorieProduit {
    private int id ; 
    private String nom_cat_produit ;

    public CategorieProduit() {
    }

    public CategorieProduit(String nom_cat_produit) {
        this.nom_cat_produit = nom_cat_produit;
    }
    
    
    
    
    public CategorieProduit(int id, String nom_cat_produit) {
        this.id = id;
        this.nom_cat_produit = nom_cat_produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat_produit() {
        return nom_cat_produit;
    }

    public void setNom_cat_produit(String nom_cat_produit) {
        this.nom_cat_produit = nom_cat_produit;
    }

    @Override
    public String toString() {
        return "CategorieProduit{" + "id=" + id + ", nom_cat_produit=" + nom_cat_produit + '}';
    }

   public float getsommeProduits () throws SQLException {
       Connection conn = MyConnection.getInstance().getConn();
       PreparedStatement pst;
       float prix = 0 ; 
           pst = conn.prepareStatement("select prix_produit FROM produits WHERE categorie_produit_id='"+ id +"'");
           ResultSet rs = pst.executeQuery();
           while (rs.next())
        {
            
             prix += Float.parseFloat(rs.getString("prix_produit"));
             System.out.println(id);
        }
           return prix ; 
   }
    
    
    
}
