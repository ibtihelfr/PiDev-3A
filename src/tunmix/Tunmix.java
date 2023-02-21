/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tunmix;

import entity.User;
import service.UserService;
import utils.DataSource;

/**
 *
 * @author sassi
 */
public class Tunmix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DataSource ds=DataSource.getInstance();
       // System.out.println(ds);
       //_______________Afficher_________________
        UserService US=new UserService();
        //US.readAll().forEach(System.out::println);
        
        
        //_____________Supprimer_________________
        User U3=new User( "user", "user", "aaa", "user@esprit.tn", 24777978, "client", "aaa.png");
        //User U2=new User(3 ,"ali", "sassi", "aaa", "aaaa", 0123456, "client", "aaa.png");
        //US.delete(U2);
        US.readAll().forEach(System.out::println);
        
        //___________Ajouter_____________________
        //US.insert(U3);
        //US.readAll().forEach(System.out::println);
        
        //_______________AfficherById_________________
       // System.out.println(US.readByID(3));
        
        
        //___________Modifier____________________
        //User U1=new User(2, "ali", "sassi", "aaa", "ibti", 0123456, "client", "aaa.png");
       // US.update(U1);
        //US.readAll().forEach(System.out::println);
          
       // System.out.println(US.getUserByEmail("test"));
    }
    
}
