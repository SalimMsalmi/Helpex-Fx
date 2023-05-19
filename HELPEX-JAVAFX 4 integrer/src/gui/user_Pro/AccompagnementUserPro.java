package gui.user_Pro;

import entities.Accompagnement;
import entities.Item;
import entities.User;
import help.Helpex;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.AccompagnementService;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccompagnementUserPro  implements Initializable {
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<?, ?> accepterId;

    @FXML
    private TableView<Accompagnement> accompagnement_Table;

    @FXML
    private TableColumn<?, ?> clientId;
    @FXML
    private Label number ;
    @FXML
    private Label accomswitch;
    @FXML
    private Label tasklabel;

    @FXML
    private TableColumn<?, ?> taskCid;

    @FXML
    private HBox hboxAccompagnement;
    @FXML
    javafx.scene.control.TextField hhh ;

    private  int current = Helpex.loggedUser.getId() ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mesAccompagnments();

    }

    public void mesAccompagnments (){
        AccompagnementService accompagnementService = new AccompagnementService();
        System.out.println(accompagnementService.lister_accompagnment_for_pro(current));
        number.setText(String.valueOf(accompagnementService.lister_accompagnment_for_pro(current).size()));

        ObservableList<Accompagnement> accompagnementObservableList = FXCollections.observableArrayList(accompagnementService.lister_accompagnment_for_pro(current));
        TableColumn<Accompagnement, String> nomColumn = new TableColumn<Accompagnement, String>("User");
        nomColumn.setCellValueFactory(cellFactory-> new SimpleStringProperty(cellFactory.getValue().getUser().getNom()+ "  " +cellFactory.getValue().getUser().getPrenom() + " vous à envoyer une demande d'accompagnement "));


        ////////////
        TableColumn<Accompagnement, Void> buttonColumn = new TableColumn<Accompagnement,Void>("#");
        TableColumn<Accompagnement, Void> buttonColumn1 = new TableColumn<Accompagnement,Void>("#");
        buttonColumn.setCellFactory(param -> new TableCell<Accompagnement,Void>() {
            private final Button AccepterButton = new Button("accepter");

            private final Pane pane = new Pane(AccepterButton);


            {
                AccepterButton.setOnAction(event -> {
                    Accompagnement accompagnement = getTableView().getItems().get(getIndex());

                    accompagnementService.accepterAccompagnement(accompagnement);
                    mesAccompagnments();
                });


            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });
        buttonColumn1.setCellFactory(param -> new TableCell<Accompagnement,Void>() {

            private final Button RejeterButton = new Button("rejeter");
            private final Pane pane = new Pane(
                     RejeterButton);


            {

                RejeterButton.setOnAction(event -> {
                    Accompagnement accompagnement = getTableView().getItems().get(getIndex());
                    System.out.println("rejeter"+accompagnement.getId());
                    Accompagnement accompagnement1 = getTableView().getItems().get(getIndex());

                    accompagnementService.retirer_accompagnement(accompagnement1);
                    //hboxAccompagnement.getChildren().remove(accompagnement_Table);
                   // pane.getChildren().add(accompagnement_Table);
                    mesAccompagnments();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });


        accompagnement_Table.getColumns().addAll(nomColumn,buttonColumn,buttonColumn1);
        accompagnement_Table.setItems(accompagnementObservableList);
    }


    @FXML
    public void switchingAccompagnemment()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accompagnement_UserPro.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) accomswitch.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    @FXML
    public void switchingTasks()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mes_tasks_pro.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) accomswitch.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }




    @FXML
    private void handleKeyReleased(KeyEvent event) {







        // wrap the TableView's data in a FilteredList
         FilteredList<Accompagnement> filteredList;
        filteredList = new FilteredList<>(accompagnement_Table.getItems(), p -> true);

        // set the filter predicate whenever the search text changes

            String newValue = hhh.getText().toLowerCase();
            filteredList.setPredicate(accompagnement -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return accompagnement.getUser().getNom().toLowerCase().contains(lowerCaseFilter)
                        || accompagnement.getUser().getPrenom().toLowerCase().contains(lowerCaseFilter);
            });


        // wrap the filtered list in a SortedList
        SortedList<Accompagnement> sortedList = new SortedList<>(filteredList);

        // bind the sorted list to the TableView
        sortedList.comparatorProperty().bind(accompagnement_Table.comparatorProperty());
        accompagnement_Table.setItems(sortedList);

    }
}
