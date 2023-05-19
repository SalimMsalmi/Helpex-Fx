package GUI.gui_Tasks;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AjoutTasks_Dialog implements Initializable {
    @FXML
     TextField titreTask ;
    @FXML
     Label km ;
    @FXML
     DatePicker startDateId ;
    @FXML
     DatePicker endtDateId ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
