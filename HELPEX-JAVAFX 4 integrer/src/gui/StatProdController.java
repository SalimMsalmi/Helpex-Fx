/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategorieProduit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.CrudCategorieProduit;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class StatProdController implements Initializable {

    @FXML
    private PieChart StatProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                );
        CrudCategorieProduit CategorieCRUD=new CrudCategorieProduit();
        for (CategorieProduit Cat: CategorieCRUD.getAllCategories()){
            try {
                pieChartData.add(new PieChart.Data(Cat.getNom_cat_produit(),Cat.getsommeProduits()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        StatProd.setData(pieChartData);


        StatProd.setTitle("");
        StatProd.setLegendVisible(true);
        StatProd.setLabelsVisible(false);
    }    
    
}
