/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.GererEv;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import PIClass.Ev;
import PIUtils.MyConnection;


/**
 * FXML Controller class
 *
 * @author Islem
 */
public class StatisticController implements Initializable {
  @FXML
    private PieChart pieChart;
  @FXML
    private Button back;
//Calcul des statistiques des types des evenements

 java.sql.Connection cnx = MyConnection.getInstance().getCnx();
   private ObservableList data;
   @Override
    public void initialize(URL url, ResourceBundle rb) {
          data = FXCollections.observableArrayList();
          try{
            String SQL = "SELECT DISTINCT type_ev as 'test',((select sum(age_max) FROM evenement WHERE type_ev=test)/(select sum(age_max) from evenement))*100 as 'Statique' from evenement";
            PreparedStatement ste = (PreparedStatement) cnx.prepareStatement(SQL);
            ResultSet rs = ste.executeQuery();
          while(rs.next()){
                System.out.println(rs.getString(1));
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(1),rs.getDouble(2)));
                pieChart.setData(data);
            }
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return ;
          }
    }
//Bouton de retour
            @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccEv.fxml"));
        Parent root = loader.load(); 
        back.getScene().setRoot(root); }
}
//          ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//          System.out.println(pieChartData);
//        GererEv liv = new GererEv();
//        ArrayList<Ev> list = new ArrayList<>();
//        list = (ArrayList<Ev>) liv.afficher();
//
//        for (int i = 0; i < list.size(); i++) {
//            pieChartData.add(new PieChart.Data(list.get(i).getType_ev(), list.get(i).getAge_max()));    
//
//       }
//                pieChart.setData(pieChartData);
//      public void start(Stage stage) throws Exception {
//        //PIE CHART
//        PieChart pieChart = new PieChart();
//        buildData();
//        pieChart.getData().addAll(data);
//
//        //Main Scene
//        Scene scene = new Scene(pieChart);        
//
//        stage.setScene(scene);
//      }
//      
 


        
    
  
  
  
  
  


  

