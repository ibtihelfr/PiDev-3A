/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sassi
 */
public class DataSource {
     private String url="jdbc:mysql://localhost:3306/tunmix";
    private String login="root";
    private String pwd="";
    public Connection cnx;
    private static DataSource instance;  
    
    
    //pour l'appeler dans le main une seul fois
    private DataSource(){
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("<Connexion etablie> \n _______________Bienvenue_________________");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            
        }
//      finally {
//    //Close the database connection
//    if (cnx != null) {
//      try {
//        cnx.close();
//      } catch (SQLException e) {
//         //Handle any errors that may occur while closing the connection
//      }
//    }
// }
    }
    
    public static DataSource getInstance(){
        if(instance==null)
        instance=new DataSource();
        return instance;
        
    }
    public Connection getCnx(){
        return cnx;   
    }
    

}
