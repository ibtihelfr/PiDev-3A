package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.ReclamationService;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLStats implements Initializable {


    @FXML
    public PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ReclamationService reclamationService = new ReclamationService();

        int numberReclamationProduit = reclamationService.countProductReclamation();
        int numberReclamationEvent = reclamationService.countEventReclamation();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Product Reclamation", numberReclamationProduit),
                        new PieChart.Data("Event Reclamation", numberReclamationEvent));

        pieChart.setData(pieChartData);
        pieChart.setTitle("Statistique sur les réclamations");
    }


}

