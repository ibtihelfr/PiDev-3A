/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entite.Produit;
import Services.ServiceProduit;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brahim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

        Produit p1 = new Produit(17,"Chapeau","Rouge", 14022023,11,2,"Casq",5);

        ServiceProduit ser = new ServiceProduit();
        
        
        // ser.supprime(p1);
        
         // ser.update(p1);
        

          
         ser.ajouter(p1);
        
        
        
        
        


         /* Produit p2 = new Produit(15,"Jupe","Bleu", 07112000,14,5,"Ju",5);

        
        try {
            ser.ajouterPST(p2);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        List<Produit> l1 = null;

        try {
            l1 = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        l1.forEach(e -> System.out.println(e)); */
    }

}
