/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entity.event;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.EventService;



/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutEventController implements Initializable {

    
   
    @FXML
    private DatePicker DateDebut;

    @FXML
    private DatePicker DateFin;

    @FXML
    private TextField Description;

    @FXML
    private TextField HeureEvent;

    @FXML
    private TextField Localisation;

    @FXML
    private TextField NomEvent;

    @FXML
    private TextField Prix;
    @FXML
    private ImageView PhotoE;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    


    @FXML
    void ajout_image(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        // load the selected image into the image view
        Image image = new Image(selectedFile.toURI().toString());
        PhotoE.setImage(image);
    }

    }
    @FXML
    void AjoutEvent(ActionEvent event) throws IOException{
        Image photo = PhotoE.getImage();
        event e=new event(NomEvent.getText(),
                DateDebut.getValue(),
                DateFin.getValue(),
                Localisation.getText(),
                Description.getText(),
                HeureEvent.getText(), 
                Float.valueOf(Prix.getText()), 
                photo.toString());
        EventService ES=new EventService();
        ES.insert(e);
           
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("ReadEvent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Evenement ajouté avec succès !");
        alert.showAndWait();
        

    }
    
}
