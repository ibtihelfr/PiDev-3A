package gui;

import Entite.Produit;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class InsertController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDesc;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtQte;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAdd3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    ServiceProduit ser = new ServiceProduit();

    @FXML
    @SuppressWarnings("null")
    private void Add(ActionEvent event) throws IOException {
          Produit p = new Produit();
          
           if(txtID.getText().equals(null)&& txtNom.getText().equals(null)&& txtDesc.getText().equals(null)&& txtDate.getText().equals(null) && txtPrix.getText().equals(null)&& txtQte.getText().equals(null)){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("il y a un champs vide !");
                    alert.show();
                    refresh();
           }else if(!(txtID.getText().toString().matches("[0-100000]+"))  && !(txtPrix.toString().matches("[1-100000]+"))&& !(txtQte.getText().toString().matches("[1-100000]+"))){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("l' ID , le Prix et la Qte du produit  doit etre des nombres");
                    alert.show();
                    refresh();
                    }

    else {   
        p.setIdProduit(Integer.parseInt(txtID.getText()));
        p.setNomProduit(txtNom.getText());
        p.setDescProduit(txtDesc.getText());
        p.setDateProduit(Integer.parseInt(txtDate.getText()));
        p.setPrixProduit(Float.parseFloat(txtPrix.getText())); 
        p.setQte(Integer.parseInt(txtQte.getText()));
        
         ser.ajouter(p);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("produit ajouté avec succes!");
                    alert.show();
                    refresh();
    System.out.println(p.toString());
          txtID.setText("");
          txtNom.setText("");
          txtDesc.setText("");
          txtPrix.setText("");
          txtDate.setText("");
          txtQte.setText("");
           }
       FXMLLoader loader = new FXMLLoader (getClass().getResource(""));
        Object root = loader.load();
        ProductController productcontroller = loader.getController();
        productcontroller.displaypro();
            ProductController pc = new ProductController();
    }

    @FXML
    private void Exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();         
    }  

    private static class ProductController {

        public ProductController() {
        }

        private void displaypro() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
}