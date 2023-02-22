/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbd;

import java.sql.*;
import Utils.DataSource;

/**
 *
 * @author Brahim
 */
public class TestBD {

    private static Connection con;
    private static String url = "jdbc:mysql://localhost:3306/tunmix";
    private static String login = "root";
    private static String pwd = "";
    private static Statement ste;

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie");
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            ste = con.createStatement();

            String req = "INSERT INTO `produit` ( `IdProduit`,`NomProduit`, `DescProduit`, `DateProduit`, `PrixProduit`, `Qte`, `PhotoP`,`IdCategorie`) VALUES ( '11','Chapeau','Rouge', 14022023,11,2,'Casq','5');";

            int res = ste.executeUpdate(req);
            System.out.println(res);
            
            ResultSet rest=ste.executeQuery("select * from produit");
            while (rest.next()) {                
                int IdProduit=rest.getInt(11);
                String NomProduit=rest.getString("NomProduit");
                String DescProduit=rest.getString("DescProduit");
                int DateProduit=rest.getInt(10022023);
                Float PrixProduit=rest.getFloat(11);
                int Qte=rest.getInt(2);
                String PhotoP=rest.getString("PhotoP");
                int IdCategorie=rest.getInt(5);
                
                System.out.println("IdProduit :"+IdProduit+" NomProduit :"+NomProduit+ " DescProduit :"+DescProduit+ "DateProduit :"+DateProduit+ "PrixProduit :"+PrixProduit+ "Qte :"+Qte+" PhotoP :"+PhotoP+ "IdCategorie :"+IdCategorie);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
