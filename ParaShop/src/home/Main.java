/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

/**
 *
 * @author FaroukDev
 */


import entities.Produit;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CrudProduits;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*List<Produit> produitList;
        CrudProduits productCrud = new CrudProduits();
        produitList = productCrud.getAllProduit();
        System.out.println(produitList);*/
        Parent root = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
        primaryStage.setScene(new Scene(root));
        //set stage borderless
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        /*
        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });*/
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
