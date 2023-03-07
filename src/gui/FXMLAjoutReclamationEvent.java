package gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entity.Reclamation;
import entity.User;
import entity.event;
import services.EventService;
import services.ReclamationService;
import services.UserService;

import javax.mail.MessagingException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.control.TextArea;


public class FXMLAjoutReclamationEvent implements Initializable {

    @FXML
    public ComboBox<event> comboBox;

    @FXML
    public ComboBox<User> comboBoxUsers;
    @FXML
    public TextField motifInput;
    @FXML
    public TextField etatInput;
    @FXML
    public Button ajoutReclamationButton;
    @FXML
    private TextArea descriptionInput;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    EventService eventService = new EventService();
        ObservableList eventsList = eventService.readAllEvents();
        comboBox.setItems(eventsList);
        UserService userService = new UserService();
        ObservableList usersList = userService.readAllUsers();
        comboBoxUsers.setItems(usersList);
        etatInput.setText("En Attente");
        motifInput.setText("Sur Evenement");
    }

    @FXML
    public void ajoutReclamationEvent(ActionEvent event) throws SQLException, MessagingException {
        if (Objects.equals(descriptionInput.getText(), "")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La description est Obligatoire");
            alert.showAndWait();
        } else if (comboBoxUsers.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'utilisateur est Obligatoire");
            alert.showAndWait();
        } else if (comboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'Evenement est Obligatoire");
            alert.showAndWait();
        } else {
            String description = descriptionInput.getText();
            String motifReclamation = motifInput.getText();
            String etatReclamation = etatInput.getText();
            ReclamationService reclamationService = new ReclamationService();
            Reclamation reclamation = new Reclamation();
            reclamation.setDescription(description);
            reclamation.setMotif(motifReclamation);
            reclamation.setDateReclamation(new Date(System.currentTimeMillis()));
            reclamation.setEtatReclamation(etatReclamation);
            EventService eventService = new EventService();
            event retournerEvent = eventService.retournerEvent(comboBox.getSelectionModel().getSelectedItem().getIdEvent());
            reclamation.setEvent(retournerEvent);
            UserService userService = new UserService();
            User user = userService.retournerUser(comboBoxUsers.getSelectionModel().getSelectedItem().getIdUser());
            reclamation.setUser(user);
            reclamationService.insertEventReclamation(reclamation);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout de reclamation Sur Evenement ");
            alert.setHeaderText("La reclamation est bien ajoutée ");
            alert.setContentText("OK!");
            alert.showAndWait();
            Stage stage = (Stage) ajoutReclamationButton.getScene().getWindow();
            stage.close();
            SendEmail sendEmail = new SendEmail();
            sendEmail.sendEmailToAdministrator();

        }


    }

}

