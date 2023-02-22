/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Produit;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brahim
 */
public class ServiceProduit implements IService<Produit>{
 
    Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;

    public ServiceProduit() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    @Override
    public void ajouter(Produit p)  {
        try {
            String req = "INSERT INTO `produit` ( `NomProduit`, `DescProduit`, `DateProduit`, `PrixProduit`, `Qte`, `PhotoP` , `IdCategorie`) VALUES "
                    + "( '"+p.getNomProduit()+"', '"+p.getDescProduit()+"', '"+p.getDateProduit()+"','"+p.getPrixProduit()+"','"+p.getQte()+"','"+p.getPhotoP()+"', '"+p.getIdCategorie()+"');";

            
            ste.executeUpdate(req);
            
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterPST(Produit p)  {
        
        try {
    String req = "INSERT INTO `produit` ( `NomProduit`, `DescProduit`, `DateProduit`, `PrixProduit`, `Qte`, `PhotoP`,`IdCategorie`) VALUES ( ?,?,?,?,?,?,?);";

     PreparedStatement pre=con.prepareStatement(req);
        
     
     pre.setString(1,p.getNomProduit() );
     pre.setString(2, p.getDescProduit());
     pre.setInt(3, p.getDateProduit());
     pre.setFloat(4, p.getPrixProduit());
     pre.setInt(5, p.getQte());
     pre.setString(6, p.getPhotoP());
     pre.setInt(7, p.getIdCategorie());
     pre.executeUpdate();
     
     
     } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Produit p)  {
        try {
            String req = "UPDATE produit SET NomProduit=?,DescProduit=?,DateProduit=?,PrixProduit=?,Qte=?,PhotoP=?  WHERE IdProduit=?";
            PreparedStatement pst = con.prepareStatement(req);
            pst.setString(1, p.getNomProduit());
            pst.setString(2, p.getDescProduit());
            pst.setInt(3, p.getDateProduit());
            pst.setFloat(4, p.getPrixProduit());
            pst.setInt(5, p.getQte());
            pst.setString(6, p.getPhotoP());
            pst.setInt(7, p.getIdProduit());

            pst.executeUpdate();
            System.out.println("Produit_id " + p.getIdProduit()+":" + " modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     *
     * @param p
     */
    @Override
    public void supprime(Produit p) {
        try {
            String req = "DELETE FROM produit WHERE IdProduit=?";
            PreparedStatement pst = con.prepareStatement(req);
            pst.setInt(1, p.getIdProduit());
            pst.executeUpdate();
            System.out.println("Article supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
   

    @Override
    public List<Produit> readAll() {
        
        try {
        ArrayList<Produit> listper=new ArrayList<>();
        
        String req="select * from produit";
        
        ResultSet res=ste.executeQuery(req);
        
        
        while (res.next()) {            
            int IdProduit=res.getInt(1);
            String NomProduit=res.getString(2);
            String DescProduit=res.getString(3);
            int DateProduit=res.getInt(4);
            Float PrixProduit=res.getFloat(5);
            int Qte=res.getInt(6);
            String PhotoP=res.getString(7);
            int IdCategorie=res.getInt(8);
            Produit p=new Produit(NomProduit, DescProduit, DateProduit, PrixProduit, Qte, PhotoP,IdCategorie);
           // System.out.println(p);
            listper.add(p);
        }
        return listper;
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
        
    }

    @Override
    public Produit findById(int IdProduit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


   
    
}
