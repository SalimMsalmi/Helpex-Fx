/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.Activite;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import PIClass.Act;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ActController implements Initializable {


    @FXML
    private TableView<Act> tabid;
    @FXML
    private TableColumn<Act, String> colnom;
    @FXML
    private TableColumn<Act, String> coltype;
    @FXML
    private TextField cherch;
    @FXML
    private ImageView imgv5;
    @FXML
    private Button back;
 ObservableList<Act> activs = FXCollections.observableArrayList();
 FilteredList <Act> filter = new FilteredList <> (activs, e -> true);
 SortedList <Act> sort = new SortedList<>(filter);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         colnom.setCellValueFactory(new PropertyValueFactory<Act,String>("Nom_act"));     
        coltype.setCellValueFactory(new PropertyValueFactory<Act,String>("type_act"));
         Activite t = new Activite();
         t.afficher().forEach(e->activs.add(e));
         tabid.setItems(activs);
        // TODO
    }    

    @FXML
    private void cherchetab() {
                 cherch.setOnKeyReleased(e -> { 
               cherch.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(Ev -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                String lowerCaseFilter = newValue.toLowerCase();
                if (Ev.getNom_act().toLowerCase().contains(lowerCaseFilter) ) {
					return true;}
                else  
				    	 return false;
        });
        
        });
       sort.comparatorProperty().bind(tabid.comparatorProperty());
       tabid.setItems(sort);
    });
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserIn.fxml"));
        Parent root = loader.load(); 
        back.getScene().setRoot(root);
    }
    
}
