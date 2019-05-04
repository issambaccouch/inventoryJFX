/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;

/**
 *
 * @author Don Solid
 */
public class Products {
    
    private int idpr ;
    private String  nomp ;
    private  double prix ;          
    private String  description	;
    private String imagep ;        
    private int etatpr ;
    private int enpromo ;
    private int iduser ;
    private int idcp;
    private int nbreViews;
   
    
    private String date_exp;
    private CatProd catProd;

    public Products(int idpr, String nomp, double prix, String description,CatProd catProd,String imagep,int etatpr, int enpromo) {
        this.idpr = idpr;
        this.nomp = nomp;
        this.prix = prix;
        this.description = description;
        this.catProd=catProd;
        this.etatpr = etatpr;
        this.enpromo = enpromo;
    }

    public Products() {
    }

    public Products(String nomp, double prix, String description, String imagep, int etatpr, int enpromo, int iduser,int idcp,String date_exp) {
        this.nomp = nomp;
        this.prix = prix;
        this.description = description;
        this.imagep = imagep;
        this.etatpr = etatpr;
        this.enpromo = enpromo;
        this.iduser = iduser;
        this.idcp=idcp;
        this.date_exp=date_exp;
        
    }

    public int getIdcp() {
        return idcp;
    }

    public void setIdcp(int idcp) {
        this.idcp = idcp;
    }

    public CatProd getCatProd() {
        return catProd;
    }

    public void setCatProd(CatProd catProd) {
        this.catProd = catProd;
    }

   

    
    public int getIdpr() {
        return idpr;
    }

    public void setIdpr(int idpr) {
        this.idpr = idpr;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagep() {
        return imagep;
    }

    public void setImagep(String imagep) {
        this.imagep = imagep;
    }

    public int getEtatpr() {
        return etatpr;
    }

    public void setEtatpr(int etatpr) {
        this.etatpr = etatpr;
    }

    public int getEnpromo() {
        return enpromo;
    }

    public void setEnpromo(int enpromo) {
        this.enpromo = enpromo;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

   

    public String getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(String date_exp) {
        this.date_exp = date_exp;
    }

    @Override
    public String toString() {
        return "Products{" + "idpr=" + idpr + ", nomp=" + nomp + ", prix=" + prix + ", description=" + description + ", imagep=" + imagep + ", etatpr=" + etatpr + ", enpromo=" + enpromo + ", iduser=" + iduser + ", date_exp=" + date_exp + ", catProd=" + catProd + '}';
    }

    public int getNbreViews() {
        return nbreViews;
    }

    public void setNbreViews(int nbreViews) {
        this.nbreViews = nbreViews;
    }

    

    

}
