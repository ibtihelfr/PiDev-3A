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
    private int idreclamation;
    private Produit produit;
    private event event;
    private String nomReclamation;
    private String historique;
    private Date dateReclamation;
    private String etatReclamation;
    private String motif;

    public Reclamation() {
    }

    public Reclamation(int idreclamation) {
        this.idreclamation = idreclamation;
    }
    
    
    
 /*   public Reclamation( Produit produit, Event event, String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif) {
 
        this.produit = produit;
        this.event = event;
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
    }*/
           public Reclamation( Produit produit, String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif) {
        this.produit = produit;
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
    }

    public Reclamation( event event, String nomReclamation, String historique, Date dateReclamation, String etatReclamation, String motif) {
   
        this.event = event;
        this.nomReclamation = nomReclamation;
        this.historique = historique;
        this.dateReclamation = dateReclamation;
        this.etatReclamation = etatReclamation;
        this.motif = motif;
    }
 
    public int getIdreclamation() {
        return idreclamation;
    }

    public void setIdreclamation(int idreclamation) {
        this.idreclamation = idreclamation;
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
    
    
    

  //  @Override
   // public String toString() {
  //      return "Reclamation{" + "idreclamation=" + idreclamation + ", idproduit=" + idproduit + ", idevent=" + idevent + ", nomReclamation=" + nomReclamation + ", historique=" + historique + ", dateReclamation=" + dateReclamation + ", etatReclamation=" + etatReclamation + ", motif=" + motif + '}';
   // }



    

 



     
}
