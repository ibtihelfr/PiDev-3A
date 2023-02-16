/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ASUS
 */
public class reservation {
    private int idRes;
    private Event idEvent;
    private User idUser;

    public reservation(int idRes, Event idEvent, User idUser) {
        this.idRes = idRes;
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public reservation(Event idEvent, User idUser) {
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public int getIdRes() {
        return idRes;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "reservation{" + "idRes=" + idRes + ", idEvent=" + idEvent + ", idUser=" + idUser + '}';
    }
    
    
}
