/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.logging.Logger;

/**
 *
 * @author Asma Laaribi
 */
public class Reponse {
    private int idreponse;
    private String description;
    private User user;
    private Reclamation reclamation;

    public Reponse() {
    }

    public Reponse(int idreponse, String description) {
        this.idreponse = idreponse;
        this.description = description;
    }
    
     

    public Reponse(String description, User user, Reclamation reclamation) {
        this.description = description;
        this.user = user;
        this.reclamation = reclamation;
    }
    
    

    public Reponse(int idreponse, String description, User user, Reclamation reclamation) {
        this.idreponse = idreponse;
        this.description = description;
        this.user = user ; 
        this.reclamation= reclamation ; 

    }

   

    public int getIdreponse() {
        return idreponse;
    }

    public void setIdreponse(int idreponse) {
        this.idreponse = idreponse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }


    @Override
    public String toString() {
        return "Reponse{" + "idreponse=" + idreponse + ", description=" + description + ", iduser=" + user + ", idreclamation=" + reclamation + '}';
    }


    
    
    
   

   

    
    
    
}
