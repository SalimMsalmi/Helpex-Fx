/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIServices.Activite;
import static com.itextpdf.text.pdf.BidiOrder.S;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.sort;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.sort;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import PIClass.Act;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ActivitésController implements Initializable {

    @FXML
    private TableView<Act> tabid;
    @FXML
    private TableColumn<Act, String> colnom;
    @FXML
    private TableColumn<Act, String> coltype;
    @FXML
    private TextField cherch;
    @FXML
    private AnchorPane zone_aj;
    @FXML
    private TextField text_nom;
    @FXML
    private Button valider;
     @FXML
    private Button back;
    @FXML
    private Label err_nom;
     @FXML
    private ImageView imgv2;
  
    @FXML
    private Label nom;

    @FXML
    private TextField text_nom1;
    @FXML
    private ImageView imgv;

    @FXML
    private ImageView imgv3;

    @FXML
    private ImageView imageview1;

    @FXML
    private ImageView imgv5;
    
   
  ObservableList<Act> activs = FXCollections.observableArrayList();
  FilteredList <Act> filter = new FilteredList <> (activs, e -> true);
 SortedList <Act> sort = new SortedList<>(filter);
 
    /**
     * Initializes the controller class.
     */
 //Afficher la liste des Activités
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colnom.setCellValueFactory(new PropertyValueFactory<Act,String>("Nom_act"));     
        coltype.setCellValueFactory(new PropertyValueFactory<Act,String>("type_act"));
         Activite t = new Activite();
         t.afficher().forEach(e->activs.add(e));
         tabid.setItems(activs);
         tabid.setEditable(true);
          //Sélection pour la modification 
          colnom.setCellFactory(TextFieldTableCell.forTableColumn());
          coltype.setCellFactory(TextFieldTableCell.forTableColumn());
            Activite e = new Activite();
             e.afficher();
           ContextMenu contextMenuPub = new ContextMenu();
           MenuItem DeleteItem = new MenuItem("Supprimer Activité");
     
       //Suppression
        DeleteItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
          Object item = tabid.getSelectionModel().getSelectedItem();
          Act  p1= (Act) item;
          Activite  spub = new Activite();
          System.out.println(p1.toString());
          spub.supprimer(p1);
          JOptionPane.showMessageDialog(null,"Activité supprimé");
              }});
        contextMenuPub.getItems().add(DeleteItem);
        tabid.setContextMenu(contextMenuPub);
      
    }
    //Fonction du modification du nom de l'activité
    @FXML
    private void modifNom(CellEditEvent edittedCell) {
    if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
         else if (controleTextFieldCellEdit(edittedCell));
        else{      
 Act e = tabid.getSelectionModel().getSelectedItem();
     e.setNom_act (edittedCell.getNewValue().toString());
     JOptionPane.showMessageDialog(null,"Nom modifiée avec succès");
     Activite g = new Activite();
     g.modifier(e);
    }
    }
    //Fonction du modification du type de l'activité
    @FXML
    void modifType(CellEditEvent edittedCellType) {
          if(edittedCellType.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
         else if (controleTextFieldCellEdit(edittedCellType));
        else{      
        Act e = tabid.getSelectionModel().getSelectedItem();
        e.setType_act (edittedCellType.getNewValue().toString());
        JOptionPane.showMessageDialog(null,"Type modifié avec succès");
        Activite g = new Activite();   
        g.modifier(e);
             }
    }
//la Recherche dans le tableau
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
//Bouton mene vers le menu event        
    @FXML
    void back(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccEv.fxml"));
        Parent root = loader.load(); 
//        back.getOnMouseMoved().getClass().getResource("../gui/AccEv.fxml");
        back.getScene().setRoot(root);
//        JOptionPane.showMessageDialog(null, "Welcome HOOOME !!");  
    
   
}
    @FXML
    private void Control(KeyEvent event) {
    }
//Fonction de l'ajout de l'activité
    @FXML
    private void valider(ActionEvent event) {
         Activite ac =new Activite();
          if (text_nom.getText().isEmpty() || text_nom1.getText().isEmpty() )
               
                 {
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText("Veuillez remplir tous les champs");
                     alert.showAndWait();}
                 else if(controleTextFieldNonNumerique(text_nom)||controleTextFieldNonNumerique(text_nom1));
                 else {
        ac.ajouter(new Act (text_nom.getText(),text_nom1.getText()));
        JOptionPane.showMessageDialog(null,"act ajouté");
    }
    }
    
 //Controle de Saisie   
    
    public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
 Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Veuillez saisir des lettres");
    alert.showAndWait();
            return true;
        }
        return false;
    }
      
    public boolean controleTextFieldCellEdit(CellEditEvent edittedCell) {
        if (!edittedCell.getNewValue().toString().matches(".*[a-zA-Z].*")) {
     Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Veuillez saisir des lettres");
    alert.showAndWait();
            return true;
        }
        return false;
    }
    
    }
    

