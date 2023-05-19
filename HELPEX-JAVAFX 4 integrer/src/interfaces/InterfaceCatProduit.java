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


import java.util.List;

public interface InterfaceCatProduit {
    List<CategorieProduit> getAllCategories();
    CategorieProduit getCategorieById(int id);
    boolean addCategorie(CategorieProduit categorie);
    boolean updateCategorie(CategorieProduit categorie);
    boolean deleteCategorie(int id);
    boolean isCategoryTaken(String Cat);
}

