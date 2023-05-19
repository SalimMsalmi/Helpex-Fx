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
import java.awt.event.KeyEvent;
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
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import PIClass.Ev;
import PIUtils.MyConnection;
 

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class EditEventController implements Initializable {
java.sql.Connection cnx = MyConnection.getInstance().getCnx();
 String requete = "SELECT titre_ev ,type_ev,emplacement_ev FROM evenement";

    @FXML
    private TextField cherch;
    
//    @FXML
//    private TextField cool;
//    
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
    private Button pdf;
        @FXML
    private Button back;
    @FXML
    private ImageView imgview3;

    @FXML
    private ImageView imgview;

    @FXML
    private ImageView imageview1;
   
   
     
    ObservableList<Ev> activs = FXCollections.observableArrayList();
    FilteredList <Ev> filter = new FilteredList <> (activs, e -> true);
    SortedList <Ev> sort = new SortedList<>(filter);
//    ContextMenu contextMenuPub = new ContextMenu();
//      MenuItem DeleteItem = new MenuItem("Supprimer Evenement");
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
        colnom.setCellFactory(TextFieldTableCell.forTableColumn());
        coltype.setCellFactory(TextFieldTableCell.forTableColumn());
        colemplacement.setCellFactory(TextFieldTableCell.forTableColumn());
//        colDate_deb.setCellFactory(TextFieldTableCell.forTableColumn());
//        colDate_fin.setCellFactory(TextFieldTableCell.forTableColumn());
//        colTemps_deb.setCellFactory(TextFieldTableCell.forTableColumn());
//        colTemps_fin.setCellFactory(TextFieldTableCell.forTableColumn());
//        colAge_max.setCellFactory(TextFieldTableCell.
//        colAge_min.setCellFactory(TextFieldTableCell.forTableColumn());
//        colAge_max.setCellFactory(TextFieldTableCell.forTableColumn());
     
        GererEv e1 =new GererEv ();
        e1.afficher();
      ContextMenu contextMenuPub = new ContextMenu();
      MenuItem DeleteItem = new MenuItem("Supprimer Evenement");
     
       
        DeleteItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
          Object item = tabid.getSelectionModel().getSelectedItem();
          Ev  p1= (Ev) item;
          GererEv  spub = new GererEv();
          System.out.println(p1.toString());
          spub.supprimer(p1);
          JOptionPane.showMessageDialog(null,"event supprimé");
                }
                });
        contextMenuPub.getItems().add(DeleteItem);
        tabid.setContextMenu(contextMenuPub);
          
                }
      
      
      
    //Les Fonctions de modification dans le tableau          
                 
    @FXML
    void modifNom(CellEditEvent edittedCell) {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
        else if (controleTextFieldCellEdit(edittedCell));
        else{  
         Ev e = tabid.getSelectionModel().getSelectedItem();
         String str = String.valueOf(e);
//        calc.setText(str);
        e.setTitre_ev(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null,"Nom modifiée avec succès");
        GererEv g = new GererEv();
        g.modifier(e);
         }  
    }
        void modifType(CellEditEvent edittedCell) {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
        else if (controleTextFieldCellEdit(edittedCell));
        else{  
         Ev e = tabid.getSelectionModel().getSelectedItem();
        e.setType_ev(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null,"Type modifiée avec succès");
        GererEv g = new GererEv();
        g.modifier(e);
         }  
    }
        void modifEmplacement(CellEditEvent edittedCell) {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
         else if (controleTextFieldCellEdit(edittedCell));
        else{  
         Ev e = tabid.getSelectionModel().getSelectedItem();
        e.setEmplacement_ev(edittedCell.getNewValue().toString());
        JOptionPane.showMessageDialog(null,"emplacement modifiée avec succès");
        GererEv g = new GererEv();
        g.modifier(e);
         }  
    }
        void modifAge_max(CellEditEvent edittedCell) {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
         else if (controleTextFieldCellEdit(edittedCell));
        else{  
         Ev e = tabid.getSelectionModel().getSelectedItem();
        e.setAge_max(edittedCell.getNewValue().hashCode());
        JOptionPane.showMessageDialog(null,"Age_max modifiée avec succès");
        GererEv g = new GererEv();
        g.modifier(e);
         }  
    }
        void modifAge_min(CellEditEvent edittedCell) {
        if(edittedCell.getNewValue().toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
         else if (controleTextFieldCellEdit(edittedCell));
        else{  
         Ev e = tabid.getSelectionModel().getSelectedItem();
        e.setAge_min(edittedCell.getNewValue().hashCode());
        JOptionPane.showMessageDialog(null,"Age_min modifiée avec succès");
        GererEv g = new GererEv();
        g.modifier(e);
         }  
//        void modifDate_deb(CellEditEvent edittedCell) {
//        if(edittedCell.getNewValue().toString().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setHeaderText(null);
//            a.setContentText("Veuillez remplir tous les champs");
//            a.showAndWait();
//        }
//         else if (controleTextFieldCellEdit(edittedCell));
//        else{  
//         Ev e = tabid.getSelectionModel().getSelectedItem();
//        e.setDate_dev(edittedCell.getNewValue().getValue());
//        JOptionPane.showMessageDialog(null,"Date modifiée avec succès");
//        GererEv g = new GererEv();
//        g.modifier(e);
//         }  
//    }
//       void modifDate_fin(CellEditEvent edittedCell) {
//        if(edittedCell.getNewValue().toString().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setHeaderText(null);
//            a.setContentText("Veuillez remplir tous les champs");
//            a.showAndWait();
//        }
//         else if (controleTextFieldCellEdit(edittedCell));
//        else{  
//         Ev e = tabid.getSelectionModel().getSelectedItem();
//        e.setDate_fin();
//        
//        JOptionPane.showMessageDialog(null,"Nom modifiée avec succès");
//        GererEv g = new GererEv();
//        g.modifier(e);
//         }  
//    }
//        void modiftemps_deb(CellEditEvent edittedCell) {
//        if(edittedCell.getNewValue().toString().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setHeaderText(null);
//            a.setContentText("Veuillez remplir tous les champs");
//            a.showAndWait();
//        }
//         else if (controleTextFieldCellEdit(edittedCell));
//        else{  
//         Ev e = tabid.getSelectionModel().getSelectedItem();
//        e.setTemps_deb(edittedCell.getNewValue().toString());
//        
//        JOptionPane.showMessageDialog(null,"Temps de début modifiée avec succès");
//        GererEv g = new GererEv();
//        g.modifier(e);
//         }
//                 }
//         void modiftemps_fin(CellEditEvent edittedCell) {
//        if(edittedCell.getNewValue().toString().isEmpty()){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setHeaderText(null);
//            a.setContentText("Veuillez remplir tous les champs");
//            a.showAndWait();
//        }
//         else if (controleTextFieldCellEdit(edittedCell));
//        else{  
//         Ev e = tabid.getSelectionModel().getSelectedItem();
//        e.setTemps_fin();
//        JOptionPane.showMessageDialog(null,"Temps fin modifiée avec succès");
//        GererEv g = new GererEv();
//        g.modifier(e);
//         }
//                             }
//   
////        @FXML
////    void recherchenom(KeyEvent event) {
////
////    }
//Pour la recherche
                      }
    @FXML
    void cherchetab() {
        cherch.setOnKeyReleased(e -> { 
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
    //Api pdf pour afficher la liste des evenements
    @FXML
 private void CreatePDF(ActionEvent event) throws SQLException, IOException, DocumentException {
    
     try {
       Document doc = new Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\HP\\Desktop\\Esprit\\3eme\\2. PIDev\\PIDev\\src\\Evenement.pdf"));
       doc.open();
       
      Image img = Image.getInstance("C:\\Users\\HP\\Desktop\\Esprit\\3eme\\2. PIDev\\PIDev\\src\\img\\img.jpg");
       img.scaleAbsoluteHeight(60);
       img.scaleAbsoluteWidth(100);
       img.setAlignment(Image.ALIGN_RIGHT);
       doc.open();
       doc.add(img);
    
       doc.add(new Paragraph(" "));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("Nos evenements ", font);
       p.setAlignment(Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));
 

       PdfPTable tabpdf = new PdfPTable(3);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       cell = new PdfPCell(new Phrase("nom", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("type", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("emplacement", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
      
       PreparedStatement pst = cnx.prepareStatement(requete);
       ResultSet rs = pst.executeQuery();
       while (rs.next()) {
           cell = new PdfPCell(new Phrase("titre_ev", FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
     
           cell = new PdfPCell(new Phrase(rs.getString("type_ev"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("emplacement_ev"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
       }
          doc.add(tabpdf);
          JOptionPane.showMessageDialog(null, "Success !!");
          doc.close();
          Desktop.getDesktop().open(new File("C:\\Users\\HP\\Desktop\\Esprit\\3eme\\2. PIDev\\PIDev\\src\\pdf\\Evenement.pdf"));
       }
 
        catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
          }
    
 }
 //controle de saisie
     
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

/*        public boolean validateDatePickerfin(DatePicker d)
    {
          if(d.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("validate Date");
            alert.setHeaderText(null);
            alert.setContentText("veuillez choisir la date de fin ");
            alert.showAndWait();
            return false;
         }
           return true;
        }
    public boolean validateDatePickerexp(DatePicker d)
    {
          if(d.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("validate Date");
            alert.setHeaderText(null);
            alert.setContentText("veuillez choisir une date d'expiration ");
            alert.showAndWait();
            return false;
         }
           return true;
        }
        public  boolean IntegerCheck(String input) {
        try {
            double d = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
     public boolean controleComboBox(ComboBox<String> combo) {
        if (combo.getValue() == null) {
//            errorLabel.setText(msg);
            return true;
        }
        return false;
    }
}*/
    //Bouton mene vers le menu event
        @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccEv.fxml"));
        Parent root = loader.load(); 

        back.getScene().setRoot(root);  
    }
}





       
       
