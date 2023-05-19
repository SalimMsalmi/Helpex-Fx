package gui;

import entities.Organisation;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.OrganisationCRUD;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StatOrg implements Initializable {
    public PieChart Orgpiechart;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                );
        OrganisationCRUD organisationCRUD=new OrganisationCRUD();
        for (Organisation organisation: organisationCRUD.afficherOrg()){
            try {
                pieChartData.add(new PieChart.Data(organisation.getNom(),organisation.getsommeCaisses()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Orgpiechart.setData(pieChartData);


        Orgpiechart.setTitle("");
        Orgpiechart.setLegendVisible(true);
        Orgpiechart.setLabelsVisible(false);
    }

    

}
