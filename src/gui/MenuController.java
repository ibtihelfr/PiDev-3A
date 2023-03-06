/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imag;

        @FXML
    void Events(ActionEvent event) {
        try {
            fxml=FXMLLoader.load(getClass().getResource("ReadEvent.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Tickets(ActionEvent event) {

    }

    @FXML
    void Users(ActionEvent event) {

    }

    @FXML
    void paiement(ActionEvent event) {

    }

    @FXML
    void products(ActionEvent event) {

    }

    @FXML
    void reclamations(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
