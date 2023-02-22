/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import entity.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ResService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReservationCRUDController implements Initializable {

    @FXML
    private TableColumn<reservation, Integer> res;
    @FXML
    private TableColumn<reservation, Integer> ev;
    @FXML
    private TableColumn<reservation, Integer> us;
    
        @FXML
    private TableView<reservation> table;
       


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ResService RS =new ResService();
        List<reservation> reservations = RS.readAll();
     
        ObservableList<reservation> reservationData = FXCollections.observableArrayList(reservations);
        
        table.setItems(reservationData);
        res.setCellValueFactory(new PropertyValueFactory<>("idRes"));
    ev.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdEvent().getIdEvent()).asObject());
    us.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdUser().getIdUser()).asObject());

          
    }    

    

    @FXML
    private void Modifier_Res(ActionEvent event) {
    }

    @FXML
    private void Supp_Res(ActionEvent event) {
        
         if (table.getSelectionModel().getSelectedItem() != null) {
        // Récupérer les données de l'événement sélectionné
        reservation selectedEvent = table.getSelectionModel().getSelectedItem();
        ResService RS=new ResService();
        RS.delete(selectedEvent);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Reservation Supprimé !");
        alert.showAndWait();
        
          }
         else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Selectionner une réservation pour supprimer!");
        alert.showAndWait();
        
             
         }
         //table.refresh();
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("ReservationCRUD.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(ReservationCRUDController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
    
}
