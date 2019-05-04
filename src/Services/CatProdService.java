/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.CatProd;
import Entites.Products;
import Utils.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class CatProdService implements ICatProd {

    Connection cnx;
    Statement stm;

    public CatProdService()  {
       try{
        cnx = ConnexionDB.getInstance().getConnection();
        stm = cnx.createStatement();
       }catch(SQLException e){
           
       }
    }

    @Override
    public void addCategorie(CatProd cp) {
        String request = "INSERT INTO `categorieprod` (`nomcp`)"
                + " VALUES ('" + cp.getNomcp() + "')";
        try {
            stm.executeUpdate(request);

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateCategorie(CatProd cp) {
        System.out.println("Id Event To update : " + cp.getIdcp());

        String req = "UPDATE categorieprod SET nomcp = ?    WHERE idcp=" + cp.getIdcp();
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement(req);
        } catch (SQLException ex) {
            Logger.getLogger(CatProdService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stm.setString(1, cp.getNomcp());
        } catch (SQLException ex) {
            Logger.getLogger(CatProdService.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CatProdService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Event Has Been Updated");
    }

    @Override
    public void deleteCategorie(CatProd cp) {
        String request = "DELETE FROM `categorieprod` WHERE idcp = '" + cp.getIdcp() + "'";
        try {
            stm.executeUpdate(request);

        } catch (SQLException ex) {
            Logger.getLogger(CatProdService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CatProd getAllCatProd(int id) {
        CatProd cp = null;
        try {
            ResultSet res = stm.executeQuery("select * from `categorieprod` where `idcp` = " +id+ "");
            if (res.next()) {
                cp = new CatProd();
                cp.setIdcp(res.getInt("idcp"));
                cp.setNomcp(res.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return cp;
    }

    @Override
    public Products getById(Products pd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CatProd> getAllCatProd() {
     
        ArrayList<CatProd> categorie = new ArrayList<CatProd>();
        CatProd cp = null;
        try {
            ResultSet res = stm.executeQuery("select * from `categorieprod` ");
            while (res.next()) {
                cp = new CatProd();
                cp.setIdcp(res.getInt("idcp"));
                cp.setNomcp(res.getString(2));

                categorie.add(cp);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return categorie;
    }

    @Override
    public CatProd getCategoryByName(String name) {
        CatProd category = new CatProd();
        try{
            ResultSet res = stm.executeQuery("select * from `categorieprod` where nomcp='"+name+"'");
            if (res.next()) {
                category.setIdcp(res.getInt("idcp"));
                category.setNomcp(res.getString("nomcp"));
            }
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return category;
    }

    @Override
    public Boolean uniqueCatgeory(String name) {
        try
        {
            ResultSet res = stm.executeQuery("select * from `categorieprod` where nomcp='"+name+"'");
            if (res.next()) {
                return false;
            }
            return true;
        }catch( SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public CatProd getCategoryById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
