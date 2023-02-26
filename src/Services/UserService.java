package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ProduitService;
import entity.User;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserService {


    private Connection connection;

    public UserService() {
        connection = DataSource.getInstance().getCnx();
    }


    public ObservableList readAllUsersIds() {
        String requete = "select * from user";
        ObservableList<String> userList = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                userList.add(String.valueOf(resultSet.getInt("IdUser")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public User retournerUser(int idUser) throws SQLException {
        User user = new User();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM user where `IdUser` = " + idUser);
        while (res.next()) {
            int id = res.getInt("IdUser");
            user.setIdUser(id);
        }
        return user;
    }
}
