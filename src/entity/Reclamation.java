/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.sql.Date;

/**
 *
 * @author Asma Laaribi
 */
public class Reclamation {
    private int idReclamation;
    private User user;
    private Produit produit;
    private event event;
    private String description;
    private Date dateReclamation;
    private String etatReclamation;
    private String motif;
    private String reponse;
    private int idUser;
    private String nomUser;
    private String prenomUser;

    public Reclamation() {
    }

    public Reclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }
   public Reclamation( int idReclamation , Produit produit, event event, String description, Date dateReclamation, String etatReclamation, String motif,String reponse) {
        this.idReclamation = idReclamation ; 
        this.produit = produit;
        this.event = event;
        this.description = description;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
        this.reponse=reponse;
    }
   
   public Reclamation( int idReclamation  , String description, Date dateReclamation, String etatReclamation, String motif,String reponse) {
        this.idReclamation = idReclamation ; 
        this.description = description;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
        this.reponse=reponse;

    }
   
   
     
   public Reclamation( int idReclamation  , String description, Date dateReclamation, String etatReclamation, String motif,String reponse, int idUser, String nomUser, String prenomUser) {
        this.idReclamation = idReclamation ; 
        this.description = description;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
        this.reponse=reponse;
        this.idUser=idUser;
        this.nomUser=nomUser;
        this.prenomUser=prenomUser;

    }
  
   public Reclamation( int idReclamation , User user , String description, Date dateReclamation, String etatReclamation, String motif,String reponse) {
        this.idReclamation = idReclamation ; 
        this.user=user;
        this.description = description;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
               this.reponse=reponse;

    }
    
    
        public Reclamation( User user, Produit produit, String description, Date dateReclamation, String etatReclamation, String motif, String reponse) {
        this.produit = produit;
        this.user=user;
        this.description = description;
        this.dateReclamation = dateReclamation;
       this.etatReclamation = etatReclamation;
        this.motif = motif;
                this.reponse=reponse;

    }

    public Reclamation( User user,event event, String description, Date dateReclamation, String etatReclamation, String motif, String reponse) {
        this.user=user;
        this.event = event;
        this.description = description;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
                this.reponse=reponse;

}
    
    

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }
    
     public String getReponse() {
     return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 
    
    public int getIdreclamation() {
        return idReclamation;
    }

    public void setIdreclamation(int idreclamation) {
        this.idReclamation = idreclamation;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public String getEtatReclamation() {
        return etatReclamation;
    }

    public void setEtatReclamation(String etatReclamation) {
        this.etatReclamation = etatReclamation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "user=" + user + ", idReclamation=" + idReclamation + ", produit=" + produit + ", event=" + event + ", description=" + description + ", dateReclamation=" + dateReclamation + ", etatReclamation=" + etatReclamation + ", motif=" + motif + '}';
    }
    
    
    

 


    

 



     
}
