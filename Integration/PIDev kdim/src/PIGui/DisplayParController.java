/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.Participation;
import PIServices.ServiceParticipation;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASuS
 */
public class DisplayParController implements Initializable {

    @FXML
    private Button xbtn;
    @FXML
    private Button ret;
    @FXML
    private TableView<Participation> table;
    @FXML
    private TableColumn<Participation, Integer> par;
    @FXML
    private TableColumn<Participation, Integer> user;
    @FXML
    private TableColumn<Participation, Integer> event;
    @FXML
    private TableColumn<Participation, String> name;
    @FXML
    private TableColumn<Participation, Timestamp> date;

    private ObservableList<Participation> dataList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        par.setCellValueFactory(new PropertyValueFactory<Participation, Integer>("id_par"));
        user.setCellValueFactory(new PropertyValueFactory<Participation, Integer>("id_user"));        
        event.setCellValueFactory(new PropertyValueFactory<Participation, Integer>("id_event"));        
        name.setCellValueFactory(new PropertyValueFactory<Participation, String>("username"));
        date.setCellValueFactory(new PropertyValueFactory<Participation, Timestamp>("date_par"));
        ServiceParticipation spar = new ServiceParticipation();
        spar.afficher().forEach(e->{dataList.add(e);});
        table.setItems(dataList);
    }    

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilCoach.fxml"));
        Parent root = loader.load(); 
        ret.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Welcome HOOOME !!");  
    }
    
    public ObservableList<Participation> getPar(List<Participation> l){
        ObservableList<Participation> data = FXCollections.observableArrayList();
        for (int i =0; i<=l.size()-1; i++){
            data.add(l.get(i));
        }
        return data;
    }
        
    private void RefreshRec() {
        ServiceParticipation spar = new ServiceParticipation();
        par.setCellValueFactory(new PropertyValueFactory<Participation, Integer>("id_par"));
        user.setCellValueFactory(new PropertyValueFactory<Participation, Integer>("id_user"));        
        event.setCellValueFactory(new PropertyValueFactory<Participation, Integer>("id_event"));        
        name.setCellValueFactory(new PropertyValueFactory<Participation, String>("username"));
        date.setCellValueFactory(new PropertyValueFactory<Participation, Timestamp>("date_par"));
        table.setItems(getPar(spar.afficher()));
    }
    
//    @FXML
//    void NumbPar(ActionEvent event) {
//      int b = 0;
//        ServiceParticipation spar = new ServiceParticipation();
//        b = spar.calculNbrPar(Integer.parseInt(idev.getText()));
//        String str = String.valueOf(b);
//        calc.setText(str);
//
//    }
    
    @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");  
    }
}
