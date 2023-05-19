/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.GererEv;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;
import static java.util.Collections.sort;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import PIClass.Ev;

import PIUtils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class UserInController implements Initializable {
 java.sql.Connection cnx = MyConnection.getInstance().getCnx();
 String requete = "SELECT titre_ev ,type_ev,emplacement_ev FROM evenement";
 


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
    private TextField cherch;
    @FXML
    private ImageView imageview1;
    @FXML
    private Button back;
    @FXML
    private Button pdf;
    @FXML
    private Button afficher;
 ObservableList<Ev> activs = FXCollections.observableArrayList();
    FilteredList <Ev> filter = new FilteredList <> (activs, e -> true);
    SortedList <Ev> sort = new SortedList<> (filter);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         colnom.setCellValueFactory(new PropertyValueFactory<Ev,String>("titre_ev"));     
        coltype.setCellValueFactory(new PropertyValueFactory<Ev,String>("type_ev"));        
        colemplacement.setCellValueFactory(new PropertyValueFactory<Ev,String>("emplacement_ev"));        
        colDate_deb.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_dev"));   
        colDate_fin.setCellValueFactory(new PropertyValueFactory<Ev,Date>("Date_fev")); 
        colTemps_deb.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_dev"));
        colTemps_fin.setCellValueFactory(new PropertyValueFactory<Ev,Time>("temps_fev")); 
        colAge_min.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_min"));        
        colAge_max.setCellValueFactory(new PropertyValueFactory<Ev,Integer>("Age_max"));        
        GererEv t = new GererEv();
        t.afficher().forEach(e->activs.add(e));
        tabid.setItems(activs);
        tabid.setEditable(true);
        ContextMenu contextMenuPub = new ContextMenu();
    }  

    @FXML
    private void cherchetab() {cherch.setOnKeyReleased(e -> { 
    cherch.textProperty().addListener((observable, oldValue, newValue) -> {
    filter.setPredicate(Ev -> {
    if (newValue == null || newValue.isEmpty()) {
    return true;
	}
    String lowerCaseFilter = newValue.toLowerCase();
    if (Ev.getTitre_ev().toLowerCase().contains(lowerCaseFilter) ) {
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
    void AfficherActivités(ActionEvent event) throws IOException {
FXMLLoader loader = new FXMLLoader(getClass().getResource("Act.fxml"));
        Parent root = loader.load(); 
        back.getScene().setRoot(root); 
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccEv.fxml"));
        Parent root = loader.load(); 
        back.getScene().setRoot(root);  
    
    }

    @FXML
    private void CreatePDF(ActionEvent event) {
    }

}

    

