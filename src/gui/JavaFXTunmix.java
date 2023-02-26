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


//        DataSource ds1 = DataSource.getInstance();
//        System.out.println("Datasource instance created ! ");
//        
//        
//       
// //CRUD Reclamation        
//         ReclamationService reclamationService=new ReclamationService();
//          Produit produit1 = new Produit(1) ; 
//          Produit produit2=new Produit(2);
//          Produit produit3=new Produit(3);
//        
//          event event1  = new event(1) ;
//          event event2  = new event(2) ;
//          event event3  = new event(3) ;
//          
//          User user1 =new User(1);
//          User user2=new User(2);
//          User user3 =new User(3);
//          
////        //INSERT Reclamation
//          Reclamation reclamation1=new Reclamation(user1,produit1, "Reclamation sur un produit x ", "reclamation faite le x ", new Date(System.currentTimeMillis()),"En attente","reclamation sur le produit 1 ","yyy");
//          Reclamation reclamation2=new Reclamation(user1,produit2, "Reclamation sur un produit y ", "reclamation faite le jour y ", new Date(System.currentTimeMillis()),"En cours","reclamation sur le produit 2 ","vvv");
//          Reclamation reclamation3=new Reclamation(user2,produit3, "Reclamation sur un produit z ", "reclamation faite le jour z ", new Date(System.currentTimeMillis()),"Terminée","reclamation sur le produit 3 ","xxx");
//          Reclamation reclamation4=new Reclamation(user2,event1, "Reclamation sur un event x ", "reclamation faite le jour w ", new Date(System.currentTimeMillis()),"Terminée","reclamation sur event 1 ","www");
//          Reclamation reclamation5=new Reclamation(user3,event2, "Reclamation sur un event y ", "reclamation faite le jour k ", new Date(System.currentTimeMillis()),"Terminée","reclamation sur event 2 ","aaa");
//          Reclamation reclamation6=new Reclamation(user3,event3, "Reclamation sur un event z ", "reclamation faite le jour z ", new Date(System.currentTimeMillis()),"En attente","reclamation sur event 3 ","ccc");
//  
//        /* reclamationService.insertProductReclamation(reclamation1); 
//         reclamationService.insertProductReclamation(reclamation2);
//         reclamationService.insertProductReclamation(reclamation3);
//         reclamationService.insertEventReclamation(reclamation4);
//         reclamationService.insertEventReclamation(reclamation5);
//         reclamationService.insertEventReclamation(reclamation6);
//         System.out.println("*** Reclamations  inserted with success *** ");
//*/
//         // Read Reclamations 
//         reclamationService.readAll().forEach(System.out::println);
//
//         // Update Reclamation
////         Reclamation reclamation1ToBeUpdated =new Reclamation(7, "UPPP TO DAAAAATEEEEE ", "RCCC UPPPP Traité au 19/02", new Date(System.currentTimeMillis()),"Traité c fini","reclamation sur le produit 1 ","BOOOM");
////          reclamationService.updateReclamation(reclamation1ToBeUpdated);
////         System.out.println("*** Reclamation updated with success *** ");
//            
//         
//          //DELETE Reclamation
//         reclamationService.delete(7);
//          System.out.println("*** Reclamation deleted with success *** ");
// 
//          
//
//        
//
//}

}
