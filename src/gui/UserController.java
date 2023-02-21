package gui;

import entity.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import service.UserService;
import utils.DataSource;

public class UserController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField pwd;
    private Connection connection;

    @FXML
    void Gotoacoount(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("creer.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }

    @FXML
    void login(ActionEvent event) throws IOException, SQLException {
    String e = email.getText();
    String mdp = pwd.getText();
    
    if (e == null || e.isEmpty() || mdp == null || mdp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir l'email et le mot de passe.");
        alert.showAndWait();
        return;
    }
    
    UserService userService = new UserService();
    User utilisateur = userService.getUserByEmail(e);

    if (utilisateur == null || !mdp.equals(utilisateur.getPwd())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Email ou mot de passe incorrect.");
        alert.showAndWait();
        return;
    }
    
    if (utilisateur.getTypeUser().equals("Admin")) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Bienvenue Admin");
        alert.setContentText("Vous êtes maintenant connecté!");
        alert.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Vous êtes maintenant connecté!");
        alert.showAndWait();
//        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
    }
    }
          

    }


