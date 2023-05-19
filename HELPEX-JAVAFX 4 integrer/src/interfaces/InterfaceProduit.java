/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author FaroukDev
 */
import entities.CategorieProduit;
import entities.Produit;
import java.util.List;

public interface InterfaceProduit {
    
    List<Produit> getAllProduit();
    Produit getProduitById(int id);
    boolean addProduit(Produit P , CategorieProduit Cat);
    boolean updateProduit(Produit produit);
    boolean deleteProduite(int id);
    
}
