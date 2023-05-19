/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.gui_Tasks;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Accompagnement;
import entities.Item;
import entities.Tasks;
import help.Helpex;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.xhtmlrenderer.pdf.ITextRenderer;
import services.ItemService;
import services.TasksService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Eya
 */
public class Tasks_Controller implements Initializable {
    @FXML
    private MenuButton taskMenuItem ;
    @FXML
    private ListView<Integer> heure ;
    @FXML
    private  ListView<Integer> min ;
    @FXML
    private  ListView<Integer> seconde ;
    @FXML
    private TextField itemTextField ;
    @FXML
    private Label taskmenuItemLabel ;
    @FXML
    private Label aAcomplirLabel ;
    @FXML
    private Label dejaCompleteLabel ;
    @FXML
    private Label ExpiredLabel ;
    @FXML
    private ScrollPane scrolling ;
    private int numberoftasks =0, numberCompleteItem=0,numbernonCompleteItem=0;
    VBox vBox = new VBox();
    TasksService tasksService = new TasksService();

    private  int currentuser = Helpex.loggedUser.getId() ;
    ArrayList<Accompagnement> listTasks= tasksService.listerTasksofUser(currentuser);
    @FXML
    private  Label tasksSideNav ;
    @FXML
    private VBox vboxUltimit ;
    @FXML
    private Label AccompagnementLabel;
    @FXML
    private Button pdfButton;


    private  Scene scene ;
    private Stage stage;
    @FXML
    public void switching() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TasksGui_info.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) tasksSideNav.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttaccomp() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("accompagnement_button.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) tasksSideNav.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @FXML
        public void switchingAccompagnemment()  {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../admin/accompagnmemnts_admin.fxml"));
                Parent root = loader.load();
                Scene newScene = new Scene(root);
                Stage currentStage = (Stage) AccompagnementLabel.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException e) {
                e.printStackTrace();
            }




        }
    @FXML
    public void switching1(ActionEvent event)  {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("GUI_Tasks.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    public void TEMPScONTROLLERmAKER (){
        ObservableList<Integer> items = FXCollections.observableArrayList (
                0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);
        heure.setItems(items);
        min.setItems(numbers());
        seconde.setItems(numbers());
    }

    private  ObservableList<Integer> numbers() {
        return FXCollections.observableArrayList (
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59
        ) ;
    }

    public  void addingTheitems(){
        TEMPScONTROLLERmAKER();
        TasksService tasksService = new TasksService();
        listTasks.clear();
        taskMenuItem.getItems().clear();
        ArrayList<Accompagnement>listTasks= tasksService.listerTasksofUser(currentuser);


        for (Accompagnement accompagnement
                : listTasks) {
            numberoftasks++;
            MenuItem menuItem = new MenuItem(accompagnement.getId_task().getTitre());
            int idTask = accompagnement.getId_task().getId();
            menuItem.setOnAction(e -> {

                if( itemTextField.getText().isEmpty() ){
                    Alert a = new Alert(Alert.AlertType.ERROR, "item champ non valide!!", ButtonType.OK);
                    a.showAndWait();

                }
                if( heure.getSelectionModel().getSelectedItem() == null || min.getSelectionModel().getSelectedItem() == null || seconde.getSelectionModel().getSelectedItem() == null ){
                    Alert a = new Alert(Alert.AlertType.ERROR, "temps non valide!!", ButtonType.OK);
                    a.showAndWait();

                }
                else{
                    ItemService itemService = new ItemService();
                    //////////////////dialog//////////////
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to proceed?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // Get the selection model
                        MultipleSelectionModel<Integer> selectionModel = heure.getSelectionModel();
                        Integer selectedItem = selectionModel.getSelectedItem();

                        MultipleSelectionModel<Integer> selectionModelmin = min.getSelectionModel();
                        Integer selectedItemmin = selectionModel.getSelectedItem();

                        MultipleSelectionModel<Integer> selectionModelsec = seconde.getSelectionModel();
                        Integer selectedItemseconde = selectionModelsec.getSelectedItem();

                        itemService.AjouterItem(new Item(itemTextField.getText(), Time.valueOf(selectedItem+":"+selectedItemmin+":"+selectedItemseconde),false,new Tasks(idTask),"h"));
                        taskmenuItemLabel.setText(((MenuItem)e.getSource()).getText() + " selected");


                        //fetching again
                        //you need to clear it
                        fetchItems();




                    } else {
                        // Perform another action if the user clicked the cancel button or closed the dialog box
                    }}

                /////////////////dialog//////////////



            });
            taskMenuItem.getItems().add(menuItem);

        }

    }

    public  void fetchItems () {
        vBox.getChildren().clear();
        ItemService service = new ItemService();

        ArrayList<Item> items= service.listerItemsforUser(currentuser);
        for (Item item :items){
            if (item.isId_complete()) {
                numberCompleteItem++;
            }
            if (!item.isId_complete()) {
                numbernonCompleteItem++;
            }
        }
        Map<Integer, ArrayList<Item>> groupedData = new HashMap<>();
        Set<Tasks> setTasks = new HashSet<>();
        for (Item row : items) {
            int name = row.getTasks().getId();
            if (!groupedData.containsKey(name)) {
                groupedData.put(name, new ArrayList<Item>());
                setTasks.add(row.getTasks());
            }
            groupedData.get(name).add(row);
        }

        // Create a new table for each group of data
        for (Map.Entry<Integer, ArrayList<Item>> entry : groupedData.entrySet()) {
            Tasks taskfromSet=setTasks.stream()
                    .filter(task -> task.getId() == entry.getKey())
                    .findFirst().get();


            String tableName = taskfromSet.getTitre() + "_table";
            String datelabel = taskfromSet.getStart_date()+"/"+taskfromSet.getEnd_date();
            Boolean validity =taskfromSet.isIs_valid();
            createTable(tableName, datelabel,validity, entry.getValue());
           /* List<Item> groupData = entry.getValue();
            for (Item row : groupData) {
                insertDataIntoTable(tableName, row);
            }*/
        }
        scrolling.setContent(vBox);
        scrolling.setPrefSize(400,400);
    }


    private void createTable(String tableName,String datelabel, boolean validity ,ArrayList<Item> items) {
        ObservableList<Item> ItemsObservableList = FXCollections.observableArrayList(items);
        TableView<Item> tableView1 = new TableView<>();
        ////////visibility non //////////
        tableView1.setVisible(false);
        tableView1.setMaxSize(0,0);
        tableView1.setMinHeight(0);
        ////////visibility non/////////

        TableColumn<Item, Integer> idColumn = new TableColumn<Item, Integer>("#");
        idColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        TableColumn<Item, String> titreColumn= new TableColumn<Item, String>("titre");
        titreColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("titre"));

        TableColumn<Item, Time> TimeColumn = new TableColumn<Item, Time>("time");
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Item, Time>("time"));

        TableColumn<Item, Boolean> completeColumn = new TableColumn<>("is_complete");
        completeColumn.setCellValueFactory(cellData ->    new SimpleBooleanProperty(cellData.getValue().isId_complete()));
        completeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(completeColumn));




        tableView1.setOnMouseClicked(event -> {


            if (event.getButton() == MouseButton.SECONDARY) {
                TablePosition<?, ?> position = tableView1.getSelectionModel().getSelectedCells().get(0);


                ContextMenu contextMenu = new ContextMenu();

                MenuItem editMenuItem = new MenuItem("Editer Item");
                MenuItem deleteMenuItem = new MenuItem("Supprimer Item");


                ///////////// row position & data /////////////
                int row = position.getRow();
                Item data =tableView1.getItems().get(row) ;
                //////////// row position & data  ////////////
                editMenuItem.setOnAction(event1 -> {

                    //System.out.println("boiy"+data);

                    MakeDialog(data,tableView1);



                });
                deleteMenuItem.setOnAction(event1 -> {

                    ItemService itemService = new ItemService();
                    itemService.SupprimerItem(data.getId());
                    tableView1.getItems().remove(row);

                });

                contextMenu.getItems().addAll(editMenuItem,deleteMenuItem);
                contextMenu.show(tableView1, event.getScreenX(), event.getScreenY());
            }
        });

        /////////////////////action2/////////////


        tableView1.getColumns().addAll(idColumn, titreColumn,TimeColumn,completeColumn);

        tableView1.setItems(ItemsObservableList);

        vBox.prefWidthProperty().bind(scrolling.widthProperty());
        Label label1 = new Label();
        label1.setText("task :"+tableName);
        Image image ;
        if(validity) {
            image  = new Image("images/valid.png");
        }
        else {
            image = new Image("images/nonvalid.png");
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        label1.setGraphic(imageView);

        Label labelDate = new Label();
        labelDate.setText(datelabel);
        labelDate.setTextFill(Color.DARKGRAY);


        ///////////event on click label /////////
        label1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                tableView1.setVisible(false);
                tableView1.setMaxSize(0,0);
                tableView1.setMinHeight(0);

            }
            if (event.getClickCount() == 1) {
                tableView1.setVisible(true);
                tableView1.setMaxSize(500,700);
                tableView1.setMinHeight(500);

            }
        });
        ////////event on click label ////////////






        vBox.getChildren().addAll(label1,labelDate,tableView1);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addingTheitems() ;
        fetchItems ();
        dejaCompleteLabel.setText(String.valueOf(numberCompleteItem));
        aAcomplirLabel.setText(String.valueOf(numbernonCompleteItem));





    }



    @FXML
    public void AjouterTasks(javafx.event.ActionEvent actionEvent)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AjoutTasks_Dialog.fxml"));
        DialogPane TasksDialogPane = null;
        try {
            TasksDialogPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AjoutTasks_Dialog AjoutTasksDialog= fxmlLoader.getController();

        //stage
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(TasksDialogPane);
        dialog.setTitle("nouveau task");
        Optional<ButtonType> clickedButton = dialog.showAndWait();
        if (clickedButton.get() == ButtonType.OK) {
            // Get today's date
            LocalDate today = LocalDate.now();


            if((AjoutTasksDialog.titreTask.getText().isEmpty()) || (AjoutTasksDialog.startDateId.getValue()==null) || (AjoutTasksDialog.endtDateId.getValue()==null)  ){
                Alert a = new Alert(Alert.AlertType.ERROR, "Veillez remplir tous les champs!!", ButtonType.OK);
                a.showAndWait();
            }
            else   if (  (AjoutTasksDialog.startDateId.getValue().compareTo(today)<=0)  )
            {
                Alert a = new Alert(Alert.AlertType.ERROR, "la date de début doit etre supérieur à la date d'aujourd'hui!!", ButtonType.OK);
                a.showAndWait();
            }
            else if (AjoutTasksDialog.startDateId.getValue().compareTo(AjoutTasksDialog.endtDateId.getValue())>=0){
                Alert a = new Alert(Alert.AlertType.ERROR, "la date de début doit etre inférieur à la date de fin!!", ButtonType.OK);
                a.showAndWait();

            }

            else {
                TasksService tasksService = new TasksService();
                Tasks newtasks = new Tasks();
                newtasks.setTitre(AjoutTasksDialog.titreTask.getText());
                newtasks.setStart_date(java.sql.Date.valueOf(AjoutTasksDialog.startDateId.getValue()));
                newtasks.setEnd_date(Date.valueOf(AjoutTasksDialog.endtDateId.getValue()));

                tasksService.AjouterTask(newtasks);

                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Task "+ newtasks.getTitre()+" ajouter avec succée .", ButtonType.OK);
                a.showAndWait();
                // to refresh the tasks
                addingTheitems();




            }}



    }

    @FXML
    public void AjouterItem (ActionEvent actionEvent){
        itemTextField.setOnAction(event -> {
                    String titre = itemTextField.getText();
                    System.out.println("Text entered: " + titre);
                    ItemService service = new ItemService();



                }


        );
    }

    public void MakeDialog(Item row, TableView tableView){
        // Create a new stage
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        // dialog.initOwner(primaryStage);
        dialog.setTitle("Editer Item");

        // Create the form to display the selected row's data
        Label Titrelabel = new Label("Titre Item : ");
        TextField TitreField = new TextField(row.getTitre());

        Label TempsLabel = new Label("Temps  : ");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        TextField TempsField = new TextField(sdf.format(row.getTime()));

        Label Completelabel = new Label("Status Item : ");
        CheckBox checkBox = new CheckBox("Completed");
        checkBox.setSelected(row.isId_complete());



        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            // Update the selected row with the new values from the form
            row.setTitre(TitreField.getText());
            row.setTime(Time.valueOf(TempsField.getText()));
            row.setId_complete(checkBox.isSelected());
            ItemService itemService = new ItemService();
            itemService.EditerItem(row);
            tableView.refresh();



            dialog.close();
        });

        VBox vbox = new VBox(Titrelabel,TitreField,TempsLabel,TempsField,Completelabel,checkBox, saveButton);
        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
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
            ArrayList<Item>items =itemService.listerItemsforUser(currentuser);

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

}


