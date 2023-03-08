package services;

import entity.Reclamation;
import entity.User;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Asma Laaribi
 */
public class ReclamationService {

    private Connection connection;

    public ReclamationService() {
        connection = DataSource.getInstance().getCnx();
    }


    public void insertProductReclamation(Reclamation reclamation) {
        String requete = "insert into reclamation(idUser, idproduit,description,datereclamation,etatreclamation,motif) values (?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, reclamation.getUser().getIdUser());
            preparedStatement.setInt(2, reclamation.getProduit().getIdProduit());
            preparedStatement.setString(3, reclamation.getDescription());
            preparedStatement.setDate(4, reclamation.getDateReclamation());
            preparedStatement.setString(5, reclamation.getEtatReclamation());
            preparedStatement.setString(6, reclamation.getMotif());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void insertEventReclamation(Reclamation reclamation) {
        String requete = "insert into reclamation(idUser, idevent,description,datereclamation,etatreclamation,motif) values (?,?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, reclamation.getUser().getIdUser());
            preparedStatement.setInt(2, reclamation.getEvent().getIdEvent());
            preparedStatement.setString(3, reclamation.getDescription());
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
                reclamation.setDescription(resultSet.getString("Description"));
                reclamation.setEtatReclamation(resultSet.getString("EtatReclamation"));
                reclamation.setMotif(resultSet.getString("Motif"));
                reclamation.setDateReclamation(resultSet.getDate("DateReclamation"));
                reclamation.setReponse(resultSet.getString("Reponse"));
                
                UserService userService = new UserService();
                User user = userService.retournerUser(resultSet.getInt("idUser"));
                int idUser = user.getIdUser();
                String nomUser = user.getNomUser();
                String prenomUser = user.getPrenomUser();
                
                reclamation.setIdUser(idUser);
                reclamation.setNomUser(nomUser);
                reclamation.setPrenomUser(prenomUser);

                reclamationList.add(reclamation);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamationList;
    }
    
    public List<Reclamation> readRecTraites() {
        String requete = "select * from reclamation where EtatReclamation='Traité'";
        List<Reclamation> reclamationList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setIdreclamation(resultSet.getInt("idReclamation"));
                reclamation.setDescription(resultSet.getString("Description"));
                reclamation.setEtatReclamation(resultSet.getString("EtatReclamation"));
                reclamation.setMotif(resultSet.getString("Motif"));
                reclamation.setDateReclamation(resultSet.getDate("DateReclamation"));
                reclamation.setReponse(resultSet.getString("Reponse"));
                
                UserService userService = new UserService();
                User user = userService.retournerUser(resultSet.getInt("idUser"));
                int idUser = user.getIdUser();
                String nomUser = user.getNomUser();
                String prenomUser = user.getPrenomUser();
                
                reclamation.setIdUser(idUser);
                reclamation.setNomUser(nomUser);
                reclamation.setPrenomUser(prenomUser);

                reclamationList.add(reclamation);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamationList;
    }
    
    public List<Reclamation> readRecEnAttente() {
        String requete = "select * from reclamation where EtatReclamation='En Attente'";
        List<Reclamation> reclamationList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setIdreclamation(resultSet.getInt("idReclamation"));
                reclamation.setDescription(resultSet.getString("Description"));
                reclamation.setEtatReclamation(resultSet.getString("EtatReclamation"));
                reclamation.setMotif(resultSet.getString("Motif"));
                reclamation.setDateReclamation(resultSet.getDate("DateReclamation"));
                reclamation.setReponse(resultSet.getString("Reponse"));
                
                UserService userService = new UserService();
                User user = userService.retournerUser(resultSet.getInt("idUser"));
                int idUser = user.getIdUser();
                String nomUser = user.getNomUser();
                String prenomUser = user.getPrenomUser();
                
                reclamation.setIdUser(idUser);
                reclamation.setNomUser(nomUser);
                reclamation.setPrenomUser(prenomUser);

                reclamationList.add(reclamation);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamationList;
    }
    
    public ObservableList<Reclamation> displayReclamationOrdredByDescDate() {
        //instance liste de Reclamation
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les Reclamation
        String req = "SELECT r.* FROM reclamation r ORDER BY r.DateReclamation DESC;";

        try {
            //creation de statement
            Statement st = connection.createStatement();
            // executer la requette et recuperer le resultat 
            ResultSet rs = st.executeQuery(req);
            // tant que on a un resultat
            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setIdreclamation(rs.getInt("idReclamation"));
                reclamation.setDescription(rs.getString("Description"));
                reclamation.setEtatReclamation(rs.getString("EtatReclamation"));
                reclamation.setMotif(rs.getString("Motif"));
                reclamation.setDateReclamation(rs.getDate("DateReclamation"));
                reclamation.setReponse(rs.getString("Reponse"));
                
                UserService userService = new UserService();
                User user = userService.retournerUser(rs.getInt("idUser"));
                int idUser = user.getIdUser();
                String nomUser = user.getNomUser();
                String prenomUser = user.getPrenomUser();
                
                reclamation.setIdUser(idUser);
                reclamation.setNomUser(nomUser);
                reclamation.setPrenomUser(prenomUser);

                //ajouter a la liste
                list.add(reclamation);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    public ObservableList<Reclamation> displayReclamationOrdredByAscDate() {
        //instance liste de Reclamation
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        //ecrire requete sql pour recuperer les Reclamation
        String req = "SELECT r.* FROM reclamation r ORDER BY r.DateReclamation ASC;";

        try {
            //creation de statement
            Statement st = connection.createStatement();
            // executer la requette et recuperer le resultat 
            ResultSet rs = st.executeQuery(req);
            // tant que on a un resultat
            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setIdreclamation(rs.getInt("idReclamation"));
                reclamation.setDescription(rs.getString("Description"));
                reclamation.setEtatReclamation(rs.getString("EtatReclamation"));
                reclamation.setMotif(rs.getString("Motif"));
                reclamation.setDateReclamation(rs.getDate("DateReclamation"));
                reclamation.setReponse(rs.getString("Reponse"));
                
                UserService userService = new UserService();
                User user = userService.retournerUser(rs.getInt("idUser"));
                int idUser = user.getIdUser();
                String nomUser = user.getNomUser();
                String prenomUser = user.getPrenomUser();
                
                reclamation.setIdUser(idUser);
                reclamation.setNomUser(nomUser);
                reclamation.setPrenomUser(prenomUser);

                //ajouter a la liste
                list.add(reclamation);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
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
            String requete = "update reclamation set description=? , etatReclamation=? , reponse=?  where idreclamation='" + reclamation.getIdreclamation() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, reclamation.getDescription());
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
