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
import entity.Produit;
import entity.Reclamation;
import entity.User;
import service.ServiceProduit;
import service.ReclamationService;
import service.UserService;
import javax.mail.MessagingException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.control.TextArea;

public class FXMLAjoutReclamationProduit implements Initializable {

    @FXML
    public TextField motifInput;
    @FXML
    public TextField etatInput;
    @FXML
    public ComboBox<Produit> comboBox;
//    @FXML
//    public ComboBox<User> comboBoxUsers;
    @FXML
    public Button ajoutReclamationButton;
    @FXML
    private TextArea descriptionInput;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProduit produitService = new ServiceProduit();
        ObservableList produitList = produitService.readAllProduitsIds();
        comboBox.setItems(produitList);
        UserService userService = new UserService();
        ObservableList<User> usersList = userService.readAllUsers();
       // comboBoxUsers.setItems(usersList);
        etatInput.setText("En Attente");
        motifInput.setText("Sur Produit");
    }

    @FXML
    public void ajoutReclamationProduit(ActionEvent event) throws SQLException, MessagingException {
        //Controle de saisie
        if (Objects.equals(descriptionInput.getText(), "")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La description est Obligatoire");
            alert.showAndWait();
//        } else if (comboBoxUsers.getSelectionModel().getSelectedItem() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("L'utilisateur est Obligatoire");
//            alert.showAndWait();
        } else if (comboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le Produit est Obligatoire");
            alert.showAndWait();
        } else {
            //Insertion de r??clamation sur Produit
            String description = descriptionInput.getText();
            String motifReclamation = motifInput.getText();
            String etatReclamation = etatInput.getText();
            ReclamationService reclamationService = new ReclamationService();
            Reclamation reclamation = new Reclamation();
            reclamation.setDescription(description);
            reclamation.setMotif(motifReclamation);
            reclamation.setDateReclamation(new Date(System.currentTimeMillis()));
            reclamation.setEtatReclamation(etatReclamation);
            ServiceProduit produitService = new ServiceProduit();
            Produit prod = produitService.retournerProduit(comboBox.getSelectionModel().getSelectedItem().getIdProduit());
            reclamation.setProduit(prod);
            UserService userService = new UserService();
            //User user = userService.retournerUser(comboBoxUsers.getSelectionModel().getSelectedItem().getIdUser());
            System.out.println("userconnected"+userConnected);
            reclamation.setUser(userConnected);
            reclamationService.insertProductReclamation(reclamation);
            
            // Show Alert 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout de reclamation sur Produit ");
            alert.setHeaderText("La reclamation est bien ajout??e ");
            alert.setContentText("OK!");
            alert.showAndWait();
            Stage stage = (Stage) ajoutReclamationButton.getScene().getWindow();
            stage.close();
            
            //Send Email
            SendEmail sendEmail = new SendEmail();
            sendEmail.sendEmailToAdministrator();
        }
    }

    private User userConnected;
    public void setUser(User user) {
        this.userConnected = user;
    }
}
