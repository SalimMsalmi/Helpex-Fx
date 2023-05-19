/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author FaroukDev
 */
public class Produit {
    
    private int id;
    private CategorieProduit categoryProduit;
    
    private String NomProduit;
    private String Etatproduit;
    private String PrixProduit;
    /*private LocalDateTime createdAt;
    private LocalDateTime updatedAt;*/
    private boolean authorization;

    public Produit() {
    }

    public Produit(String NomProduit, String Etatproduit, String PrixProduit, boolean authorization) {
        this.NomProduit = NomProduit;
        this.Etatproduit = Etatproduit;
        this.PrixProduit = PrixProduit;
        this.authorization = authorization;
    }

    public Produit(int id, CategorieProduit categoryProduit, String NomProduit, String Etatproduit, String PrixProduit, boolean authorization) {
        this.id = id;
        this.categoryProduit = categoryProduit;
        this.NomProduit = NomProduit;
        this.Etatproduit = Etatproduit;
        this.PrixProduit = PrixProduit;
        this.authorization = authorization;
    }

    public Produit(int id, String NomProduit, String Etatproduit, String PrixProduit, boolean authorization) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.Etatproduit = Etatproduit;
        this.PrixProduit = PrixProduit;
        this.authorization = authorization;
    }

    

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categoryProduit=" + categoryProduit + ", NomProduit=" + NomProduit + ", Etatproduit=" + Etatproduit + ", PrixProduit=" + PrixProduit + ", authorization=" + authorization + '}';
    }

    
    
    
    
    
    public Produit(CategorieProduit categoryProduit, String NomProduit, String Etatproduit, String PrixProduit, boolean authorization) {
        this.categoryProduit = categoryProduit;
        this.NomProduit = NomProduit;
        this.Etatproduit = Etatproduit;
        this.PrixProduit = PrixProduit;
        this.authorization = authorization;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategorieProduit getCategoryProduit() {
        return categoryProduit;
    }

    public void setCategoryProduit(CategorieProduit categoryProduit) {
        this.categoryProduit = categoryProduit;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public String getEtatproduit() {
        return Etatproduit;
    }

    public void setEtatproduit(String Etatproduit) {
        this.Etatproduit = Etatproduit;
    }

    public String getPrixProduit() {
        return PrixProduit;
    }

    public void setPrixProduit(String PrixProduit) {
        this.PrixProduit = PrixProduit;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }
    
    
    
    
    
}
