package services;

import services.ProduitService;
import entity.Reclamation;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Asma Laaribi
 */
public class ReclamationService {

    private Connection connection;

    public ReclamationService() {
        connection = DataSource.getInstance().getCnx();
    }


    public void insertProductReclamation(Reclamation reclamation) {
        String requete = "insert into reclamation(idUser, idproduit,nomreclamation,datereclamation,etatreclamation,motif) values (?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, reclamation.getUser().getIdUser());
            preparedStatement.setInt(2, reclamation.getProduit().getIdProduit());
            preparedStatement.setString(3, reclamation.getNomReclamation());
            preparedStatement.setDate(4, reclamation.getDateReclamation());
            preparedStatement.setString(5, reclamation.getEtatReclamation());
            preparedStatement.setString(6, reclamation.getMotif());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void insertEventReclamation(Reclamation reclamation) {
        String requete = "insert into reclamation(idUser, idevent,nomreclamation,datereclamation,etatreclamation,motif) values (?,?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, reclamation.getUser().getIdUser());
            preparedStatement.setInt(2, reclamation.getEvent().getIdEvent());
            preparedStatement.setString(3, reclamation.getNomReclamation());
            preparedStatement.setDate(4, reclamation.getDateReclamation());
            preparedStatement.setString(5, reclamation.getEtatReclamation());
            preparedStatement.setString(6, reclamation.getMotif());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public List<Reclamation> readAllReclamations() {
        String requete = "select * from reclamation";
        List<Reclamation> reclamationList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setIdreclamation(resultSet.getInt("idReclamation"));
                reclamation.setNomReclamation(resultSet.getString("NomReclamation"));
                reclamation.setHistorique(resultSet.getString("historique"));
                reclamation.setEtatReclamation(resultSet.getString("EtatReclamation"));
                reclamation.setMotif(resultSet.getString("Motif"));
                reclamation.setDateReclamation(resultSet.getDate("DateReclamation"));
                reclamation.setReponse(resultSet.getString("Reponse"));

                reclamationList.add(reclamation);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamationList;
    }

    public int countProductReclamation() {
        try {
            PreparedStatement pt = connection.prepareStatement("SELECT COUNT(*) FROM reclamation WHERE idEvent IS NULL");
            ResultSet rs = pt.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int countEventReclamation() {
        try {
            PreparedStatement pt = connection.prepareStatement("SELECT COUNT(*) FROM reclamation WHERE idProduit IS NULL");
            ResultSet rs = pt.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateReclamation(Reclamation reclamation) {
        try {
            String requete = "update reclamation set nomReclamation=? , etatReclamation=? , reponse=?  where idreclamation='" + reclamation.getIdreclamation() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, reclamation.getNomReclamation());
            preparedStatement.setString(2, reclamation.getEtatReclamation());
            preparedStatement.setString(3, reclamation.getReponse());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteReclamation(int reclamationId) {
        String requete = "delete from reclamation where idreclamation = " + reclamationId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
