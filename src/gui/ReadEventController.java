package gui;

import entity.User;
import entity.event;
import entity.reservation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.EventService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.stage.Stage;
import service.ResService;
import service.UserService;


public class ReadEventController implements Initializable{

    @FXML
    private TableColumn<event, LocalDate> DDebut;

    @FXML
    private TableColumn<event, LocalDate> DFin;

    @FXML
    private TableColumn<event, String> Desc;

    @FXML
    private TableColumn<event, String> heure;

    @FXML
    private TableColumn<event, Integer> id;

    @FXML
    private TableColumn<event,String> loca;

    @FXML
    private TableColumn<event, String> nom;

    @FXML
    private TableColumn<event, String> photo;

    @FXML
    private TableColumn<event, Float> prix;

    @FXML
    private TableView<event> table;
     @FXML
    private ComboBox<User> userComboBox;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService userService = new UserService();
        List<User> users = userService.readIdNom();
           
        ObservableList<User> userData = FXCollections.observableArrayList(users);
        userComboBox.setItems(userData);
        
        
        EventService Es=new EventService();
        // Appeler la méthode "readAll()" de "EventService" pour récupérer la liste des événements
        List<event> events = Es.readAll();
        // Convertir la liste en une "ObservableList" pour pouvoir l'utiliser avec "TableView"
        ObservableList<event> eventData = FXCollections.observableArrayList(events);
        // Ajouter les données à la table
        //eventData.addAll(events);
        table.setItems(eventData);
        
            
            id.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
            loca.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            DDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            DFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            heure.setCellValueFactory(new PropertyValueFactory<>("heureEvent"));
            Desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
            photo.setCellValueFactory(new PropertyValueFactory<>("photoE"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            
    } 
      @FXML
    void ToAjouter(ActionEvent event) {
   try {
                Parent page1 = FXMLLoader.load(getClass().getResource("AjoutEvent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(ReadEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     @FXML
    void update(ActionEvent event) {
       if (table.getSelectionModel().getSelectedItem() != null) {
        // Récupérer les données de l'événement sélectionné
        event selectedEvent = table.getSelectionModel().getSelectedItem();
        // Charger la vue de modification
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvent.fxml"));
            Parent root = (Parent) loader.load();
            ModifierEventController controller = loader.getController();
            controller.setEvent(selectedEvent);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReadEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Modification avec succès !");
        alert.showAndWait();
        
       }
    }
    
    
     @FXML
    void delete(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
        // Récupérer les données de l'événement sélectionné
        event selectedEvent = table.getSelectionModel().getSelectedItem();
        EventService es = new EventService();
        es.delete(selectedEvent);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Suppression avec succès !");
        alert.showAndWait();
        
          }
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("ReadEvent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        

    }
    
      @FXML
    void Reserver(ActionEvent event) {
            if (table.getSelectionModel().getSelectedItem() != null){
                 event selectedEvent = table.getSelectionModel().getSelectedItem();
               User selectedUser = userComboBox.getSelectionModel().getSelectedItem();
    reservation r = new reservation(selectedEvent.getIdEvent(), selectedEvent, selectedUser);
    ResService RS = new ResService();
    RS.insert(r);
                   
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Félicitation");
        alert.setHeaderText(null);
        alert.setContentText("Reservation ajouté avec succès !");
        alert.showAndWait();
         
                         
                         
                 
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réservation");
        alert.setHeaderText(null);
        alert.setContentText("Selectionner Votre ID et l'event que tu va reserver !");
        alert.showAndWait();
                
            }

    }
    @FXML
    void Consulter_reservaion(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("ReservationCRUD.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(ReadEventController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
    
    
}
   


