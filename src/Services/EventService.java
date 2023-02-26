/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.event;
import utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Asma Laaribi
 */
public class EventService {

    private Connection connection;

    public EventService() {
        connection = DataSource.getInstance().getCnx();
    }

    public ObservableList readAllEventsIds() {
        String requete = "select * from event";
        ObservableList<String> eventList = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()) {
                eventList.add(resultSet.getString("idEvent"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventList;
    }

    public event retournerEvent(int idEvent) throws SQLException {
        event event = new event();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM event where `idEvent` = " + idEvent);
        while (res.next()) {
            int id = res.getInt("idEvent");
            String nomEvent = res.getString("nomEvent");
            String description = res.getString("description");
            event.setIdEvent(id);
            event.setNomEvent(nomEvent);
        }
        return event;
    }


}
