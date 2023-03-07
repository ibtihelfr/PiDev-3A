/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Brahim
 */
public class Produit {
    private int IdProduit;
    private String NomProduit;
    private String DescProduit;
    private int DateProduit;
    private float PrixProduit ;
    private int Qte;
    private String PhotoP;
    private int IdCategorie;
  public Produit (){
    }
    public Produit (int idProduit){
        this.IdProduit= idProduit ; 
    }
    
      public Produit(int idProduit, String NomProduit){
        this.IdProduit = idProduit;
        this.NomProduit = NomProduit;
    }
    public Produit(String NomProduit, String DescProduit, int DateProduit, float PrixProduit, int Qte, String PhotoP,int IdCategorie) {
        this.NomProduit = NomProduit;
        this.DescProduit = DescProduit;
        this.DateProduit = DateProduit;
        this.PrixProduit = PrixProduit;
        this.Qte = Qte;
        this.PhotoP = PhotoP;
        this.IdCategorie = IdCategorie;
        
        
    }

    public Produit(int IdProduit , String NomProduit, String DescProduit, int DateProduit, float PrixProduit,int Qte, String PhotoP,int IdCategorie) {
        
        this.IdProduit = IdProduit;
        this.NomProduit = NomProduit;
        this.DescProduit = DescProduit;
        this.PrixProduit = PrixProduit;
        this.Qte = Qte;
        this.PhotoP = PhotoP;
        this.IdCategorie = IdCategorie;
    }

    public int getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(int IdProduit) {
        this.IdProduit = IdProduit;
    }

    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public String getDescProduit() {
        return DescProduit;
    }

    public void setDescProduit(String DescProduit) {
        this.DescProduit = DescProduit;
    }

    public int getDateProduit() {
        return DateProduit;
    }

    public void setDateProduit(int DateProduit) {
        this.DateProduit = DateProduit;
    }

    public float getPrixProduit() {
        return PrixProduit;
    }

    public void setPrixProduit(float PrixProduit) {
        this.PrixProduit = PrixProduit;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public String getPhotoP() {
        return PhotoP;
    }

    public void setPhotoP(String PhotoP) {
        this.PhotoP = PhotoP;
    }
    
    public int getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(int IdCategorie) {
        this.IdCategorie = IdCategorie;
    }

    @Override
    public String toString() {
        return NomProduit;
    }
    
    
}
