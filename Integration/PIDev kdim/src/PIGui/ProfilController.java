/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.simple;
import PIClass.user;
import PIClass.userclient;
import PIServices.servicesimple;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ProfilController implements Initializable {

    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSignOut;
    @FXML
    private Label lbUsername;
    @FXML
    private Label lbMail;
    private Label lbId;
    @FXML
    private Label lbDate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button article;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    simple u = new simple();
    public void setSimple(simple u) {
		this.u = u;
                userclient.setUsername(u.getUsername());
	}
	
	public void refresh() {
		lbUsername.setText(u.getUsername()+": "+u.getMail()+" "+u.getDate_n());

	}
    
    @FXML
    private void edit(ActionEvent event) throws IOException 
    {
        FXMLLoader loader2= new FXMLLoader(getClass().getResource("Modifprofil.fxml"));
        Parent root0= loader2.load();
        btnSignOut.getScene().setRoot(root0);
    }

    @FXML
    private void search(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("Recherche.fxml"));
        Parent root1= loader1.load();
        btnSignOut.getScene().setRoot(root1);
    }

    @FXML
    private void signout(ActionEvent event) throws IOException 
    {
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
        Parent root1= loader1.load();
        btnSignOut.getScene().setRoot(root1);
    }

    @FXML
    private void delete(ActionEvent event) 
    {
               
    }
    
    
    //Setters
    
    public void setLbUsername(String username) {
        this.lbUsername.setText(username);
    }

    public void setLbMail(String mail) {
        this.lbMail.setText(mail);
    }

    public void setLbId(int id) {
        this.lbId.setText(String.valueOf(id));
    }

    public void setLbDate(Date date) {
        this.lbDate.setText(date.toString());
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException 
    {
        FXMLLoader loader2= new FXMLLoader(getClass().getResource("AddRec.fxml"));
        Parent root0= loader2.load();
        btnSignOut.getScene().setRoot(root0);
    }

    @FXML
    private void article(ActionEvent event) throws IOException 
    {
        FXMLLoader loader2= new FXMLLoader(getClass().getResource("ListeArticlesClient.fxml"));
        Parent root0= loader2.load();
        btnSignOut.getScene().setRoot(root0);
    }

    
    
}
