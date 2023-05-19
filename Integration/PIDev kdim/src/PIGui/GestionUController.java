/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIGui;

import PIClass.admin;
import PIClass.coach;
import PIClass.nutri;
import PIClass.psycho;
import PIClass.simple;
import PIServices.servicecoach;
import PIServices.servicenutri;
import PIServices.servicepsycho;
import PIServices.servicesimple;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class GestionUController implements Initializable {

    @FXML
    private TableColumn<simple, String> usernameS;
    @FXML
    private TableColumn<simple, String> mailS;
    @FXML
    private TableColumn<simple, Date> dateS;
    @FXML
    private TableColumn<psycho, String> usernameP;
    @FXML
    private TableColumn<psycho, String> mailP;
    @FXML
    private TableColumn<psycho, Date> dateP;
    @FXML
    private TableColumn<nutri, String> usernameN;
    @FXML
    private TableColumn<nutri, String> mailN;
    @FXML
    private TableColumn<nutri, Date> dateN;
    @FXML
    private TableColumn<coach, String> usernameC;
    @FXML
    private TableColumn<coach, String> mailC;
    @FXML
    private TableColumn<coach, Date> dateC;
    @FXML
    private TextField searchS;
    @FXML
    private TextField searchP;
    @FXML
    private TextField searchN;
    @FXML
    private TextField searchC;
    @FXML
    private TableView<simple> Table_User;
    
    private Stage primaryStage;
    private ObservableList<simple> RecData = FXCollections.observableArrayList();
    private ObservableList<psycho> RecData2 = FXCollections.observableArrayList();
    private ObservableList<nutri> RecData3 = FXCollections.observableArrayList();
    private ObservableList<coach> RecData4 = FXCollections.observableArrayList(); 
    
    @FXML
    private TableView<psycho> Table_Psy;
    @FXML
    private TableView<nutri> Table_Nutri;
    @FXML
    private TableView<coach> Table_Coach;
    @FXML
    private Button imprS;
    @FXML
    private Button imprN;
    @FXML
    private Button imprP;
    @FXML
    private Button imprC;
    @FXML
    private Label lbUsername;
    @FXML
    private Button logout;
    @FXML
    private Button btnReclamation;
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Simple
        List<simple> listSimple= new ArrayList<simple>();
        servicesimple ss =  new servicesimple();
        listSimple = ss.getSimpleById();
        RecData.clear();
        RecData.addAll(listSimple);
        Table_User.setItems(RecData);
        
        usernameS.setCellValueFactory(
            new PropertyValueFactory<>("username")
        );
        mailS.setCellValueFactory(
            new PropertyValueFactory<>("mail")
        );
        dateS.setCellValueFactory(
            new PropertyValueFactory<>("date_n")
        );
        
        //Psy
        List<psycho> listPsycho= new ArrayList<psycho>();
        servicepsycho sp =  new servicepsycho();
        listPsycho = sp.getPsyById();
        RecData2.clear();
        RecData2.addAll(listPsycho);
        Table_Psy.setItems(RecData2);
        
        usernameP.setCellValueFactory(
            new PropertyValueFactory<>("username")
        );
        mailP.setCellValueFactory(
            new PropertyValueFactory<>("mail")
        );
        dateP.setCellValueFactory(
            new PropertyValueFactory<>("date_n")
        );
      
        //Nutri
        List<nutri> listNutri= new ArrayList<nutri>();
        servicenutri sn =  new servicenutri();
        listNutri = sn.getNutriById();
        RecData3.clear();
        RecData3.addAll(listNutri);
        Table_Nutri.setItems(RecData3);
        
        usernameN.setCellValueFactory(
            new PropertyValueFactory<>("username")
        );
        mailN.setCellValueFactory(
            new PropertyValueFactory<>("mail")
        );
        dateN.setCellValueFactory(
            new PropertyValueFactory<>("date_n")
        );
          
       //Coach
        List<coach> listCoach= new ArrayList<coach>();
        servicecoach sc =  new servicecoach();
        listCoach = sc.getCoachById();
        RecData4.clear();
        RecData4.addAll(listCoach);
        Table_Coach.setItems(RecData4);
        
        usernameC.setCellValueFactory(
            new PropertyValueFactory<>("username")
        );
        mailC.setCellValueFactory(
            new PropertyValueFactory<>("mail")
        );
        dateC.setCellValueFactory(
            new PropertyValueFactory<>("date_n")
        );
    }    
    admin u = new admin();
    
    public void setAdmin(admin u) {
		this.u = u;
	}
	
	public void refresh() {
		lbUsername.setText( u.getUsername()+": "+u.getMail() );

	}

////    private void btnImpr(ActionEvent event) 
////    {
////        System.out.println("To Printer!");
////         PrinterJob job = PrinterJob.createPrinterJob();
////           if(job != null)
////           {
////    Window primaryStage = null;
////           job.showPrintDialog(this.primaryStage); 
////            
////    Node root = this.Table_Psy;
////           job.printPage(root);
////           job.endJob();
////            }
////    }

//@FXML
//    private void changerMdp(ActionEvent event) throws SQLException, IOException {
//        tfCode.setEditable(false);
//        labelErreur.setText("");
//        if (tfMdp.getText().isEmpty() == false && tfVMdp.getText().isEmpty() == false )
//        {
//            if (tfMdp.getText().equals(tfVMdp.getText())){
//            SU.modifierUtilisateur(InterfaceIAController.getSurnom(), tfMdp.getText());
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceIA.fxml"));
//            Parent root = loader.load();
//            Stage stageInscription= new Stage();
//            stageInscription.initStyle(StageStyle.UNDECORATED);
//            stageInscription.setScene(new Scene(root, 900, 500 ));
//            stageInscription.show();   
//            Stage stage = (Stage) labelX.getScene().getWindow();
//            stage.close();
//            } else
//                labelErreur.setText("Vérifiez que les deux mots de passe correspondent.");
//        } 
//        else
//            labelErreur.setText("Veuillez entrer votre nouveau mot de passe.");
//    }

    @FXML
    private void btnImprS(ActionEvent event) 
    {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null)
           {
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.Table_Psy;
           job.printPage(root);
           job.endJob();
            }
    }

    @FXML
    private void btnImprN(ActionEvent event) 
    {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null)
           {
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.Table_Psy;
           job.printPage(root);
           job.endJob();
            }
    }

    @FXML
    private void btnImprP(ActionEvent event) 
    {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null)
           {
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.Table_Psy;
           job.printPage(root);
           job.endJob();
            }
    }

    @FXML
    private void btnImprC(ActionEvent event) 
    {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null)
           {
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.Table_Psy;
           job.printPage(root);
           job.endJob();
            }
    }

    public void setLbUsername(String username) {
        this.lbUsername.setText(username);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException 
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
        Parent root= loader.load();
        imprS.getScene().setRoot(root);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("DisplayEditRec.fxml"));
        Parent root= loader.load();
        imprS.getScene().setRoot(root);
    }
    
    
}