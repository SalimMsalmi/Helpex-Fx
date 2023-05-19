/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



/**
 * FXML Controller class
 *
 * @author FaroukDev
 */





import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import entities.CategorieProduit;
import services.CrudCategorieProduit;

import java.util.List;

public class CrudTestController {

    @FXML
    private ListView<CategorieProduit> categoryList;

    @FXML
    private TextField categoryName;

    @FXML
    private Label message;

    //private CrudCategorieProduit crudCategorieProduit;

    
        
        CrudCategorieProduit crudCategorieProduit = new CrudCategorieProduit();
    

    @FXML
    public void initialize() {
        List<CategorieProduit> categories = crudCategorieProduit.getAllCategories();
        categoryList.getItems().addAll(categories);
    }

    @FXML
    public void addCategory() {
        String name = categoryName.getText();
        if (name != null && !name.isEmpty()) {
            CategorieProduit category = new CategorieProduit();
            category.setNom_cat_produit(name);
            boolean success = crudCategorieProduit.addCategorie(category);
            if (success) {
                categoryList.getItems().add(category);
                message.setText("Category added successfully");
                categoryName.setText("");
            } else {
                message.setText("Failed to add category");
            }
        }
    }
}



