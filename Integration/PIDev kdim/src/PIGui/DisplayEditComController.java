/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.Commentaire;
import PIServices.ServiceCommentaire;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASuS
 */
public class DisplayEditComController implements Initializable {

    @FXML
    private Button xbtn;
    @FXML
    private Button home;
    @FXML
    private TableView<Commentaire> table;
    @FXML
    private TableColumn<Commentaire, Integer> user;
    @FXML
    private TableColumn<Commentaire, String> com;
    @FXML
    private TableColumn<Commentaire, Integer> react;
    @FXML
    private TableColumn<Commentaire, Timestamp> date;
    
    private ObservableList<Commentaire> dataList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Tableview Dynamic Display
        user.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("id_user"));      
        com.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("suj_com"));   
        react.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("nb_reaction"));
        date.setCellValueFactory(new PropertyValueFactory<Commentaire, Timestamp>("date_com"));
        ServiceCommentaire scom = new ServiceCommentaire();
        scom.afficher().forEach(e->{dataList.add(e);});
        table.setItems(dataList);  
        
        //Edit Table
        table.setEditable(true);
        com.setCellFactory(TextFieldTableCell.forTableColumn());
        ContextMenu cm = new ContextMenu();
        MenuItem Delete = new MenuItem("Delete");
        MenuItem Like = new MenuItem("Like");
        Delete.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
          Object p = table.getSelectionModel().getSelectedItem();
          Commentaire com = (Commentaire) p;
          ServiceCommentaire  scom = new ServiceCommentaire();
          System.out.println(com.toString());
          scom.supprimer(com);
          Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your comment has been deleted !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       RefreshCom();
            }
        });
        Like.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
          Object p = table.getSelectionModel().getSelectedItem();
          Commentaire com = (Commentaire) p;
          ServiceCommentaire  scom = new ServiceCommentaire();
          System.out.println(com.toString());
          scom.like(com);
          
          Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your comment has been liked !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       RefreshCom();
            }
        });
        cm.getItems().add(Delete);
        cm.getItems().add(Like);
        table.setContextMenu(cm);
        
        //Message Welcome
        JOptionPane.showMessageDialog(null,"Welcome !!");
    }    
    
    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profil.fxml"));
        Parent root = loader.load(); 
        home.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Welcome HOOOME !!");
    }
    
    public ObservableList<Commentaire> getCom(List<Commentaire> l){
        ObservableList<Commentaire> data = FXCollections.observableArrayList();
        for (int i =0; i<=l.size()-1; i++){
            data.add(l.get(i));
        }
        return data;
    }
    
    private void RefreshCom() {
        ServiceCommentaire scom = new ServiceCommentaire();
        user.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("id_user"));      
        com.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("suj_com"));   
        react.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("nb_reaction"));
        date.setCellValueFactory(new PropertyValueFactory<Commentaire, Timestamp>("date_com"));
        scom.afficher().forEach(e->{dataList.add(e);});
        table.setItems(getCom(scom.afficher()));
    }
    
    @FXML
    private void EditSujCom(TableColumn.CellEditEvent edittedCell) {
        if(com.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty field");
            a.showAndWait();
        }
         else{
        Commentaire co = table.getSelectionModel().getSelectedItem();
        co.setSuj_com(edittedCell.getNewValue().toString());
        ServiceCommentaire scom = new ServiceCommentaire();
        scom.modifier(co);
        JOptionPane.showMessageDialog(null, "Success !!");  
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your comment has been updated !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
    } 
    
    @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
    }
}
