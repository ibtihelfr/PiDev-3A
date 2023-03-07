package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entity.Reclamation;
import entity.User;
import services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;


public class AdminInterfaceController implements Initializable {


    @FXML
    public TableColumn<Reclamation, String> columnDesc;
    @FXML
    public TableColumn<Reclamation, String> columnEtat;
    @FXML
    public TableColumn<Reclamation, DateCell> columnDate;
    @FXML
    public TableColumn<Reclamation, String> columnMotif;
    @FXML
    public TableColumn<Reclamation, String> columnReponse;


    @FXML
    public TableView<Reclamation> tableReclamations;
    @FXML
    public TextArea descInput;
    @FXML
    public TextArea reponseInput;
    public Button showReclamation;
    @FXML
    public Button deleteReclamationButton;
    @FXML
    public Button updateReclamationButton;
    @FXML
    public Button downloadPdfButton;
    @FXML
    public Button downloadExcelButton;
    @FXML
    public Button showStat;
    @FXML
    public ComboBox<String> comboBoxEtat;
    
    
    ReclamationService recService = new ReclamationService();
    @FXML
    private Button showEnAttente;
    @FXML
    private TableColumn<Reclamation, User> columnUser;


    @Override
    // Cette méthode sera exécuté lors de lancement de l'interface administrateur ; qui va récupérer la liste des réclamation de la base de données
    // et la mettre dans la table 
    
    public void initialize(URL url, ResourceBundle rb) {
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        columnReponse.setCellValueFactory(new PropertyValueFactory<>("Reponse"));
        columnEtat.setCellValueFactory(new PropertyValueFactory<>("EtatReclamation"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("DateReclamation"));
        columnUser.setCellValueFactory(cellData -> {
        String nomUser = cellData.getValue().getNomUser();
        String prenomUser = cellData.getValue().getPrenomUser();
        return new SimpleObjectProperty<>(new User(nomUser, prenomUser));
});
        columnMotif.setCellValueFactory(new PropertyValueFactory<>("Motif"));
        List<Reclamation> list = new ArrayList<>();
        ReclamationService reclamationService = new ReclamationService();
        list = reclamationService.readAllReclamations();
        tableReclamations.setItems((FXCollections.observableArrayList(list)));
    }

    public void showReclamation(ActionEvent event) {
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        columnReponse.setCellValueFactory(new PropertyValueFactory<>("Reponse"));
        columnEtat.setCellValueFactory(new PropertyValueFactory<>("EtatReclamation"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("DateReclamation"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
        columnMotif.setCellValueFactory(new PropertyValueFactory<>("Motif"));
        List<Reclamation> list = new ArrayList<>();
        ReclamationService reclamationService = new ReclamationService();
        list = reclamationService.readAllReclamations();
        tableReclamations.setItems((FXCollections.observableArrayList(list)));
    }
    
    

    @FXML
    //Cette méthode s'éxecute lorsque on selecte un élement de la table ;; elle va remplir les champs a gauche 
    public void getselectedReclamation() {
        Reclamation reclamation = tableReclamations.getSelectionModel().getSelectedItem();
        comboBoxEtat.getSelectionModel().select(reclamation.getEtatReclamation());
        ObservableList<String> etatList = FXCollections.observableArrayList();
        if (reclamation.getEtatReclamation().equals("En Attente")) {
            etatList.add("Traité");
        }
        comboBoxEtat.setItems(etatList);
        descInput.setText(reclamation.getDescription());
        reponseInput.setText(reclamation.getReponse());
    }

     @FXML
     //Cette méthode va exécuter l'update de réclamation 
    public void updateReclamation(ActionEvent event) {
        int idreclamation = tableReclamations.getSelectionModel().getSelectedItem().getIdreclamation();
        String description = descInput.getText();
        String etatReclamation = comboBoxEtat.getSelectionModel().getSelectedItem();
        String reponseReclamation = reponseInput.getText();
        ReclamationService reclamationService = new ReclamationService();
        Reclamation reclamation = new Reclamation();
        reclamation.setIdreclamation(idreclamation);
        reclamation.setDescription(description);
        reclamation.setEtatReclamation(etatReclamation);
        reclamation.setReponse(reponseReclamation);
        reclamationService.updateReclamation(reclamation);
        
        //Show Alert 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification du réclamation ");
        alert.setHeaderText("Le Réclamation est bien modifié ");
        alert.setContentText("OK!");
        alert.showAndWait();
        // Refresh table --> Re-read from database list of claims
        List<Reclamation> list = new ArrayList<>();
        list = reclamationService.readAllReclamations();
        tableReclamations.getItems().clear();
        tableReclamations.setItems((FXCollections.observableArrayList(list)));
        //Vider les inputs
        descInput.clear();
        reponseInput.clear();
    }

    @FXML
    public void deleteReclamation(ActionEvent event) {
        //Get Selected Reclamation 
        int idreclamation = tableReclamations.getSelectionModel().getSelectedItem().getIdreclamation();
        ReclamationService reclamationService = new ReclamationService();
        reclamationService.deleteReclamation(idreclamation);
        
        // Show Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppresssion du réclamation ");
        alert.setHeaderText("Le Réclamation est bien supprimée ");
        alert.setContentText("OK!");
        alert.showAndWait();
                // Refresh table --> Re-read from database list of claims
        List<Reclamation> list = new ArrayList<>();
        tableReclamations.getItems().clear();
        list = reclamationService.readAllReclamations();
        tableReclamations.setItems((FXCollections.observableArrayList(list)));

    }

    @FXML
    public void showStat(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void downalodPdf() {
        // Appeler la méthode export pdf qui existe dans la classe excelandpdfexport 
        ExcelAndPdfExport excelAndPdfExport = new ExcelAndPdfExport();
        excelAndPdfExport.exportPdf();
        // Show Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("EXPORT PDF ");
        alert.setHeaderText("La Liste des réclamations est bien exporté en PDF ");
        alert.setContentText("OK!");
        alert.showAndWait();
    }

    @FXML
    public void downloadExcel() {
                // Appeler la méthode export excel qui existe dans la classe excelandpdfexport 

        ExcelAndPdfExport excelAndPdfExport = new ExcelAndPdfExport();
        excelAndPdfExport.exportExcel(tableReclamations);
        //Show Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("EXPORT Excel ");
        alert.setHeaderText("La Liste des réclamations est bien exporté en Excel ");
        alert.setContentText("OK!");
        alert.showAndWait();
    }
    
    @FXML
    private void orderByAscDate(ActionEvent event) {
        tableReclamations.getItems().clear();
        tableReclamations.setItems((FXCollections.observableArrayList(recService.displayReclamationOrdredByAscDate())));
    }

    @FXML
    private void orderByDescDate(ActionEvent event) {
        tableReclamations.getItems().clear();
        tableReclamations.setItems((FXCollections.observableArrayList(recService.displayReclamationOrdredByDescDate())));
    }

    @FXML
    private void resetSort(ActionEvent event) {
        tableReclamations.getItems().clear();
        tableReclamations.setItems((FXCollections.observableArrayList(recService.readAllReclamations())));
    }

    @FXML
    private void showTraite(ActionEvent event) {
        tableReclamations.getItems().clear();
        tableReclamations.setItems((FXCollections.observableArrayList(recService.readRecTraites())));
    }

    @FXML
    private void showEnAttente(ActionEvent event) {
        tableReclamations.getItems().clear();
        tableReclamations.setItems((FXCollections.observableArrayList(recService.readRecEnAttente())));
    }


}
