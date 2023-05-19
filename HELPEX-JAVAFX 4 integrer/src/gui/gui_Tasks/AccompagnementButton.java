package gui.gui_Tasks;

import api.pdfGenerator;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entities.Accompagnement;
import entities.Item;
import entities.Tasks;
import entities.User;
import help.Helpex;
import javafx.fxml.FXML;
import com.itextpdf.text.Document;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.xhtmlrenderer.pdf.ITextRenderer;
import services.AccompagnementService;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.twilio.Twilio;

import com.twilio.type.PhoneNumber;
import services.ItemService;

import javax.swing.plaf.ButtonUI;

public class AccompagnementButton implements Initializable {


    @FXML
    private Button pro;
    @FXML
    MenuButton accompagnment_button ;
    @FXML
    Button pdf ;
    @FXML
    Button sms ;

    private User myUser= Helpex.loggedUser;
    private User thisUser =new User(8);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAccompagnment_button();


    }

    public void setAccompagnment_button(){
        AccompagnementService accompagnementService = new AccompagnementService();
        List<Tasks> myTasks = accompagnementService.lister_tasks_for_user(myUser.getId()) ;
        //    accompagnment_button.
        for(Tasks task :myTasks){
            int idTask = task.getId();

            MenuItem menuItem = new MenuItem(task.getTitre());
            menuItem.setOnAction(e->{
               //sendSms();

                accompagnementService.EnvoierAccompagnementPartie3(new Accompagnement(new Tasks(idTask),false,myUser,thisUser)) ;
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "ACCOMPAGNEMENT ENVOYER !!", ButtonType.OK);
                    a.showAndWait();
                    accompagnment_button.getItems().remove(menuItem);

            });
            accompagnment_button.getItems().add(menuItem);
        }

    }



    public void createpdf(javafx.event.ActionEvent actionEvent)  {


            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName("example.pdf");
            File outputFile = fileChooser.showSaveDialog(null);
            if (outputFile == null) {
                return; // User canceled the dialog
            }

            try {
                OutputStream outputStream = new FileOutputStream(outputFile);

                ItemService itemService = new ItemService();
                ArrayList<Item>items =itemService.listerItemsforUser(10);

                Document document = new Document(PageSize.A4.rotate());
                String filename = "output.pdf";


                PdfWriter.getInstance(document, outputStream);

                document.open();
                PdfPTable table = new PdfPTable(2);
                table.setWidths(new int[]{2, 4});
                PdfPCell cell = new PdfPCell(new Paragraph("Header 1"));
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Header 2"));
                table.addCell(cell);
                /*cell = new PdfPCell(new Paragraph("Header 3"));
                table.addCell(cell);*/
        int i = 0 ;
                for(Item item : items){

                    i++ ;
                    cell = new PdfPCell(new Paragraph(item.getTitre()));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph("5 DT"));
                    table.addCell(cell);
                }

                Paragraph totalColumns = new Paragraph("Total : " + i*5, new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD));
                totalColumns.setAlignment(Element.ALIGN_RIGHT);
                Paragraph header = new Paragraph("Totale de facture ", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 24, com.itextpdf.text.Font.BOLD));
                header.setAlignment(Element.ALIGN_CENTER);
                document.add(header);
                document.add(table);
                document.add(totalColumns);



                ///css

                document.close();

                ITextRenderer renderer = new ITextRenderer();


                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static final String ACCOUNT_SID = "AC35f3e6bdc59a86fd5d3763e8d3e093a6";
        public static final String AUTH_TOKEN = "b98f6255b040afaa8779af5c687286dd";

        public void sendSms(){
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(
                            new PhoneNumber("+21623398991"), // destination phone number
                            new PhoneNumber("+19103354023"), // your Twilio phone number
                           "Bonjour,\n" +
                                   "\n" +
                                   "Nous espérons que vous allez bien. Nous tenions à vous informer que vous avez un nouveau demande d'accompagnment.\n" +
                                   "\n" +
                                   "Nous vous remercions de votre confiance et restons à votre disposition pour toute question ou demande.\n" +
                                   "\n" +
                                   "Cordialement,\n" +
                                   "[APEX]") // message body
                    .create();

            System.out.println("Message SID: " + message.getSid());
        }

    @FXML
    public void buttUSERPRO() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../user_Pro/Accompagnement_UserPro.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) pro.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
