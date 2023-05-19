/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategorieProduit;
import entities.Produit;
import interfaces.InterfaceProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author FaroukDev
 */
public class CrudProduits implements InterfaceProduit{

    
           Statement ste;
    Connection connection = MyConnection.getInstance().getConn();

    public CrudProduits() {
    }
    
     private MyConnection myConnection; // Instance of MyConnection class

    // Constructor
    public CrudProduits(MyConnection myConnection) {
        this.myConnection = myConnection;
    }

    @Override
public List<Produit> getAllProduit() {
        List<Produit> produits = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = myConnection.getConn(); // Get database connection from MyConnection class
            String sql = "SELECT * FROM produits";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int categoryId = rs.getInt("categorie_produit_id");
                CrudCategorieProduit categorieProduitService = new CrudCategorieProduit();
                CategorieProduit categorieProduit = categorieProduitService.getCategorieById(categoryId);

                //int userId = rs.getInt("user_id");
                String nomProduit = rs.getString("nom_produit");
                String etatProduit = rs.getString("etat_produit");
                String prixProduit = rs.getString("prix_produit");
                boolean authorisation = rs.getBoolean("authorisation");

                Produit produit = new Produit( categorieProduit, nomProduit, etatProduit, prixProduit, authorisation);
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return produits;
    }

    @Override
    public Produit getProduitById(int id) {
         Produit produit = null;
         
          Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         
    try {
          conn = myConnection.getConn(); // Get database connection from MyConnection clas
        String sql = "SELECT * FROM produits WHERE id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
       rs = stmt.executeQuery();
        if (rs.next()) {
            //joiture 
            CrudCategorieProduit categorieProduitService = new CrudCategorieProduit();
            
            CategorieProduit CategorieProduit = categorieProduitService.getCategorieById(rs.getInt("categorie_produit_id"));
            
            produit = new Produit();
            produit.setId(rs.getInt("id"));
            produit.setCategoryProduit(CategorieProduit);
            produit.setNomProduit(rs.getString("nom_produit"));
            produit.setEtatproduit(rs.getString("etat_produit"));
            produit.setPrixProduit(rs.getString("prix_produit"));
            produit.setAuthorization(rs.getBoolean("authorisation"));
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        // Handle SQLException
        e.printStackTrace();
    }
    return produit;
    }

    @Override
    public boolean addProduit(Produit produit , CategorieProduit Cat) {
        
              Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        int rowsAffected = 0 ; 

        try {
            conn = myConnection.getConn(); // Get database connection from MyConnection class
            conn.setAutoCommit(false); // Set auto-commit to false for transactional operation

            // Insert CategorieProduit if not already exists
            CrudCategorieProduit categorieProduitService = new CrudCategorieProduit();
            
            CategorieProduit existingCategorieProduit = categorieProduitService.getCategorieById(Cat.getId());
           
            //I'll use it in controle saisie ! 
            /*
            if (existingCategorieProduit == null) {
                categorieProduitService.createCategorieProduit(Cat);
            }*/

            if (existingCategorieProduit != null)
            {
                // Insert Produit
                String sql = "INSERT INTO produits (categorie_produit_id, nom_produit, etat_produit, prix_produit, authorisation) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Cat.getId());
                stmt.setString(2, produit.getNomProduit());
                stmt.setString(3, produit.getEtatproduit());
                stmt.setString(4, produit.getPrixProduit());
                stmt.setBoolean(5, produit.isAuthorization());
                
                rowsAffected = stmt.executeUpdate();
                
            }
            

            
            if (rowsAffected > 0) {
                result = true;
                conn.commit(); // Commit the transaction if successful
            } else {
                conn.rollback(); // Rollback the transaction if failed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback the transaction in case of exception
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } 

        return result;
    }

    @Override
    public boolean updateProduit(Produit produit) {
        Connection conn = MyConnection.getInstance().getConn() ;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
int rowsAffected = 0;
    if (produit != null){
        try {
            // Get database connection from MyConnection class
            
            String sql = "UPDATE produits SET nom_produit = ?, etat_produit = ?, prix_produit = ?, authorisation = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produit.getNomProduit());
            stmt.setString(2, produit.getEtatproduit());
            stmt.setString(3, produit.getPrixProduit());
            stmt.setBoolean(4, produit.isAuthorization());

            stmt.setInt(5, produit.getId());
            //System.out.println(produit);
             rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                {
                    System.out.println("AfterUpdate="+produit);
                    result = true;
                    System.out.println("produit modifié !"+rowsAffected);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
        

        return result;
    
    }

    @Override
    public boolean deleteProduite(int id) {
        boolean deleted = false;
        Connection conn = null;
    try {
        conn = myConnection.getConn(); // Get database connection from MyConnection class
        String sql = "DELETE FROM produits WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            deleted = true;
        }
        stmt.close();
    } catch (SQLException e) {
        // Handle SQLException
        e.printStackTrace();
    }
    return deleted;
    }
        
    public List<Produit> getProduitByCatProduit(CategorieProduit cat) {
        List<Produit> produits = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Produit produit = null;

    try {
        conn = myConnection.getConn(); // Get database connection from MyConnection class
        String sql = "SELECT * FROM produits WHERE categorie_produit = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, cat.toString()); // Assuming getCategorie() method returns the category value of CategorieProduit object

        rs = stmt.executeQuery();

        if (rs.next()) {
            // Retrieve the data from the result set and create a Produit object
            int id = rs.getInt("id");
            String nomProduit = rs.getString("nom_produit");
            String etatProduit = rs.getString("etat_produit");
            String prixProduit = rs.getString("prix_produit");
            boolean authorization = rs.getBoolean("authorisation");

            produit = new Produit(id, nomProduit, etatProduit, prixProduit, authorization);
            produits.add(produit);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the database resources
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return produits;
}



    
}
