/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author FaroukDev
 */


import entities.CategorieProduit;
import interfaces.InterfaceCatProduit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

public class CrudCategorieProduit implements InterfaceCatProduit {

   Statement ste;
    Connection connection = MyConnection.getInstance().getConn();

    @Override
    public List<CategorieProduit> getAllCategories() {
        List<CategorieProduit> categories = new ArrayList<>();
        String sql = "SELECT * FROM categorie_produit";
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                CategorieProduit categorie = new CategorieProduit(
                        result.getInt("id"),
                        result.getString("nom_cat_produit")
                );
                categories.add(categorie);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving categories: " + e.getMessage());
        }
        return categories;
    }

    @Override
    public CategorieProduit getCategorieById(int id) {
        CategorieProduit categorie = null;
        String sql = "SELECT * FROM categorie_produit WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    categorie = new CategorieProduit(
                            result.getInt("id"),
                            result.getString("nom_cat_produit")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving category: " + e.getMessage());
        }
        return categorie;
    }

    @Override
    public boolean addCategorie(CategorieProduit categorie) {
        boolean success = false;
        String sql = "INSERT INTO categorie_produit (nom_cat_produit) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, categorie.getNom_cat_produit());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        categorie.setId(id);
                        success = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean updateCategorie(CategorieProduit categorie) {
        boolean success = false;
        String sql = "UPDATE categorie_produit SET nom_cat_produit = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categorie.getNom_cat_produit());
            statement.setInt(2, categorie.getId());
            int rowsAffected = statement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating category: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean deleteCategorie(int id) {
        boolean success = false;
        String sql = "DELETE FROM categorie_produit WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting category: " + e.getMessage());
        }
        return success;
    }
    
        public boolean isCategoryTaken(String Cat) {
            
     Statement stm;
        try {
            String req = "select count(*) from `categorie_produit` WHERE `nom_cat_produit` = \"" + Cat + "\"; ";
            stm = connection.createStatement();
            ResultSet result = stm.executeQuery(req);
            result.next();
            return result.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CrudCategorieProduit.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
        public CategorieProduit getByNomCategorie(String nomCat) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CategorieProduit categorieProduit = null;
        Statement stm;

        try {
            conn = connection;// get a connection to your database, for example using DriverManager.getConnection()
            stmt = conn.prepareStatement("SELECT id, nom_cat_produit FROM categorie_produit WHERE nom_cat_produit = ?");
            stmt.setString(1, nomCat);
            rs = stmt.executeQuery();

            if (rs.next()) {
                categorieProduit = new CategorieProduit();
                categorieProduit.setId(rs.getInt("id"));
                categorieProduit.setNom_cat_produit(rs.getString("nom_cat_produit"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(CrudCategorieProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return categorieProduit;
    }

        
        
        
    
}



