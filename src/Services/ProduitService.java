/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Produit;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Asma Laaribi
 */
public class ProduitService {

    private Connection connection;

    public ProduitService() {
        connection = DataSource.getInstance().getCnx();
    }

    public ObservableList readAllProduitsIds() {
        String requete = "select * from produit";
        ObservableList<String> produitList = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                produitList.add(resultSet.getString("idProduit"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produitList;
    }

    public Produit retournerProduit(int idProduit) throws SQLException {
        Produit produit = new Produit();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM produit where `IdProduit` = " + idProduit);
        while (res.next()) {
            int id = res.getInt("idProduit");
            String nomProduit = res.getString("nomProduit");
            Date dateProduit = res.getDate("dateProduit");
            float prixProduit = res.getFloat("prixProduit");
            int qte = res.getInt("qte");
            String photoP = res.getString("photoP");
            produit.setIdProduit(id);
            produit.setNomProduit(nomProduit);
            produit.setPrixProduit(prixProduit);
            produit.setQte(qte);
            produit.setPhotoP(photoP);
        }

        return produit;
    }


}
