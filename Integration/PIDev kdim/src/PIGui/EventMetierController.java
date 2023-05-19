/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.GererEv;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.size;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.beans.binding.Bindings.size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import PIClass.Ev;


/**
 * FXML Controller class
 *
 * @author Islem
 */
public class EventMetierController implements Initializable {
    @FXML
    private Button nombreInv;

    @FXML
    private TextField idnom;
    @FXML
    private TextField calc;
      @FXML
    private Button Trier;
    @FXML
    private Button back;
    @FXML
    private Button Rechercher;

    @FXML
    private Button afficherParActiv;
  @FXML
    private TableView<Ev> tabid;

    @FXML
    private TableColumn<Ev, String> colnom;

    @FXML
    private TableColumn<Ev, String> coltype;

    @FXML
    private TableColumn<Ev, String> colemplacement;

    @FXML
    private TableColumn<Ev, Date> colDate_deb;

    @FXML
    private TableColumn<Ev, Date> colDate_fin;

    @FXML
    private TableColumn<Ev, Time> colTemps_deb;

    @FXML
    private TableColumn<Ev, Time> colTemps_fin;

    @FXML
    private TableColumn<Ev, Integer> colAge_min;

    @FXML
    private TableColumn<Ev, Integer> colAge_max;

    @FXML
    private TextField nom;
    @FXML
    private TextField nom1;
        @FXML
    private ImageView imgview1;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colnom.setCellFactory(TextFieldTableCell.forTableColumn());
        tabid.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Ev e =tabid.getSelectionModel().getSelectedItem();
                Nombre(e.getTitre_ev());
                tabid.getSelectionModel().clearSelection();
            }
        });
         //TODO
    }    
           
    void Nombre(String nom) {
        GererEv s1 = new GererEv();
        int b = s1.nombreInvite(nom);
        calc.setText(""+b);
                }
                
       @FXML
    void Rechercher(ActionEvent event) {
          GererEv e = new GererEv();

        ObservableList<Ev> event1 = FXCollections.observableArrayList();
         colnom.setCellValueFactory(new PropertyValueFactory<Ev,String>("titre_ev"));     
        coltype.setCellValueFactory(new PropertyValueFactory<Ev,String>("type_ev"));        
        colemplacement.setCellValueFactory(new PropertyValueFactory<Ev,String>("emplacement_ev"));        
        colDate_deb.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_dev"));   
        colDate_fin.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_fev")); 
        colTemps_deb.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_dev"));
        colTemps_fin.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_fev")); 
        colAge_min.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_min"));        
        colAge_max.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_max"));      
        List old = e.RechercheNom(nom.getText());
        event1.addAll(old);
        System.out.println(event1);
        tabid.setItems(event1);
    }

    @FXML
    void Trier(ActionEvent event) {
        ObservableList<Ev> event3 = FXCollections.observableArrayList();
        colnom.setCellValueFactory(new PropertyValueFactory<Ev,String>("titre_ev"));     
        coltype.setCellValueFactory(new PropertyValueFactory<Ev,String>("type_ev"));        
        colemplacement.setCellValueFactory(new PropertyValueFactory<Ev,String>("emplacement_ev"));        
        colDate_deb.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_dev"));   
        colDate_fin.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_fev")); 
        colTemps_deb.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_dev"));
        colTemps_fin.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_fev")); 
        colAge_min.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_min"));        
        colAge_max.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_max"));  

             GererEv e2 = new GererEv();
            e2.triNom().forEach(e3 ->event3.add(e3));;
            tabid.setItems(event3); 
}

    @FXML
    void afficherParActiv(ActionEvent event) {
        GererEv e1 = new GererEv();
        ObservableList<Ev> event2 = FXCollections.observableArrayList();
        colnom.setCellValueFactory(new PropertyValueFactory<Ev,String>("titre_ev"));     
        coltype.setCellValueFactory(new PropertyValueFactory<Ev,String>("type_ev"));        
        colemplacement.setCellValueFactory(new PropertyValueFactory<Ev,String>("emplacement_ev"));        
        colDate_deb.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_dev"));   
        colDate_fin.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_fev")); 
        colTemps_deb.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_dev"));
        colTemps_fin.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_fev")); 
        colAge_min.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_min"));        
        colAge_max.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_max"));      
        List old = e1.afficherEventparActivite(nom1.getText());
        event2.addAll(old);
        System.out.println(event2);
        tabid.setItems(event2);
    }
              @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccEv.fxml"));
        Parent root = loader.load(); 
        back.getScene().setRoot(root); }
    @FXML
    void options(ActionEvent event) {

    }


    }

