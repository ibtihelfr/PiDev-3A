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
    private String nomReclamation;
    private String historique;
    private Date dateReclamation;
    private String etatReclamation;
    private String motif;
    private String reponse;

    public Reclamation() {
    }

    public Reclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }
    
    
    
   public Reclamation( int idReclamation , Produit produit, event event, String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif,String reponse) {
        this.idReclamation = idReclamation ; 
        this.produit = produit;
        this.event = event;
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
        this.reponse=reponse;
    }
   
   
     
   public Reclamation( int idReclamation  , String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif,String reponse) {
        this.idReclamation = idReclamation ; 
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
        this.reponse=reponse;

    }
  
   public Reclamation( int idReclamation , User user , String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif,String reponse) {
        this.idReclamation = idReclamation ; 
        this.user=user;
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
               this.reponse=reponse;

    }
    
    
        public Reclamation( User user, Produit produit, String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif, String reponse) {
        this.produit = produit;
        this.user=user;
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
       this.etatReclamation = etatReclamation;
        this.motif = motif;
                this.reponse=reponse;

    }

    public Reclamation( User user,event event, String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif, String reponse) {
        this.user=user;
        this.event = event;
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
                this.reponse=reponse;

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

    public String getNomReclamation() {
        return nomReclamation;
    }

    public void setNomReclamation(String nomReclamation) {
        this.nomReclamation = nomReclamation;
    }

    public String getHistorique() {
        return historique;
    }

    public void setHistorique(String historique) {
        this.historique = historique;
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
        return "Reclamation{" + "user=" + user + ", idReclamation=" + idReclamation + ", produit=" + produit + ", event=" + event + ", nomReclamation=" + nomReclamation + ", historique=" + historique + ", dateReclamation=" + dateReclamation + ", etatReclamation=" + etatReclamation + ", motif=" + motif + '}';
    }
    
    
    

 


    

 



     
}
