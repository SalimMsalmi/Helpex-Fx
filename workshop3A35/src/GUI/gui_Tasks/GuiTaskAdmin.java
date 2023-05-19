package GUI.gui_Tasks;

import entites.Item;
import entites.Tasks;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.*;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ItemService;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.*;

public class GuiTaskAdmin implements Initializable {
    @FXML
    private Button bilan;
    @FXML
    private Label AccompagnementLabel;

    @FXML
    private Label ExpiredLabel;

    @FXML
    private Label aAcomplirLabel;

    @FXML
    private Label dejaCompleteLabel;

    @FXML
    private ScrollPane scrolling;

    @FXML
    private Label tasksSideNav;



    private int numberoftasks =0, numberCompleteItem=0,numbernonCompleteItem=0;


    VBox vBox = new VBox();

    @FXML
    void AjouterTasks(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchItems();
        dejaCompleteLabel.setText(String.valueOf(numberCompleteItem));
        aAcomplirLabel.setText(String.valueOf(numbernonCompleteItem));

    }

    public void switching() {

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("TasksGui_info.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI_Tasks.fxml"));
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
    public  void fetchItems () {
        vBox.getChildren().clear();
        ItemService service = new ItemService();

        ArrayList<Item> items= service.listerItems();
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

    @FXML
    public void switchingStat() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chart.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) tasksSideNav.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }}

@FXML
    public  void printing (){
    System.out.println("3aaaaaaa");
        // create a node to print
        Text text = new Text("hey");

        // create a button to trigger the print action
        Button printButton = new Button("Print");
        printButton.setOnAction(event -> {
            // create a printer job
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                // create a page layout for the printer job
                PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

                // set the job status message
                //jobStatus.setText("Printing...");

                // print the node
                boolean printed = job.printPage(pageLayout, text);

                if (printed) {
                    // end the job and set the job status message
                    job.endJob();
                    System.out.println("Printing completed.");
                } else {
                    // end the job and set the job status message
                    job.endJob();
                    System.out.println("Printing failed.");
                }
            } else {
                System.out.println("Printer job could not be created.");
            }
        });
    }
}
