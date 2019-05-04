/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Don Solid
 */
public class CatProd {
    
   private int idcp ;
   private String nomcp ;

    public CatProd() {
    }

    public CatProd(String nomcp) {
        this.nomcp = nomcp;
    }

    public CatProd(int idcp, String nomcp) {
        this.idcp = idcp;
        this.nomcp = nomcp;
    }

    public int getIdcp() {
        return idcp;
    }

    public void setIdcp(int idcp) {
        this.idcp = idcp;
    }

    public String getNomcp() {
        return nomcp;
    }

    public void setNomcp(String nomcp) {
        this.nomcp = nomcp;
    }

    @Override
    public String toString() {
        return nomcp;
    }

    
}
