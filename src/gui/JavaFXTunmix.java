package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Asma Laaribi
 */
public class JavaFXTunmix extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(JavaFXTunmix.class.getResource("GeneralInterface.fxml")); //Afficher l'interface Générale

        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("TunMIX");
        stage.setWidth(650);
        stage.setHeight(500);
    }

}
