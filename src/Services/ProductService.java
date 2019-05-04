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
public class ProductService implements IProduct {

    Connection cnx;
    Statement stm;
    CatProdService cs = new CatProdService();

    public ProductService() {
        try {
            cnx = ConnexionDB.getInstance().getConnection();

            stm = cnx.createStatement();
        } catch (SQLException e) {

        }
    }

    @Override
    public void addProduct(Products pd) {
        //String request = "INSERT INTO `produit`(`nomp`, `prix`, `description`, `imagep`, `etatpr`, `enpromo`, `iduser`, `idcp`, `date_exp`) values ('" + pd.getNomp() + "'," + pd.getPrix() + ",'" + pd.getDescription() + "','" + pd.getImagep() + "'," + pd.getEtatpr() + "," + pd.getEnpromo() + "," + pd.getIduser() + "," + pd.getCatProd().getIdcp() + "," + pd.getDate_exp() + ")";
        String request = "INSERT INTO `produit`(`nomp`, `prix`, `description`, `imagep`, `etatpr`, `enpromo`, `iduser`, `idcp`, `date_exp`) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1,pd.getNomp());
            pst.setDouble(2,pd.getPrix());
            pst.setString(3,pd.getDescription());
            pst.setString(4,pd.getImagep());
            pst.setInt(5,pd.getEtatpr());
            pst.setInt(6,pd.getEnpromo());
            pst.setInt(7,pd.getIduser());
            pst.setInt(8,pd.getCatProd().getIdcp());
            pst.setString(9,pd.getDate_exp());
            pst.executeUpdate();
            //System.out.println("adddddddddddddd");
        } catch (SQLException ex) {
            System.out.println("erreur ajout produit ==> "+ ex.getMessage());
        }
        //System.out.println("Adding ");
        //System.out.println(pd.getDate_exp());
    }

    @Override
    public void updateProduct(Products pd) {
        String req = "update produit set nomp=? , prix=?,description=?,imagep=?,idcp=?,date_exp=?,etatpr=0 where idpr=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);

            ste.setString(1, pd.getNomp());
            ste.setDouble(2, pd.getPrix());
            ste.setString(3, pd.getDescription());
            ste.setString(4, pd.getImagep());
            ste.setInt(5, pd.getCatProd().getIdcp());
            ste.setString(6, pd.getDate_exp());

            ste.setInt(7, pd.getIdpr());

            ste.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur update " + ex.getMessage());
        }
    }

    @Override
    public void deleteProduct(Products pd) {
        String request = "DELETE FROM `produit` WHERE idpr = " + pd.getIdpr() + "";
        try {
            stm.executeUpdate(request);
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Products> getAllProducts() {
        ArrayList<Products> prod = new ArrayList<>();
        Products p = null;
        try {
            ResultSet res = stm.executeQuery("select * from `produit` ");
            while (res.next()) {
                p = new Products();
                p.setIdpr(res.getInt("idpr"));
                p.setNomp(res.getString(2));
                p.setPrix(res.getDouble(3));
                p.setDescription(res.getString(4));
                p.setCatProd(cs.getAllCatProd(res.getInt(9)));
                p.setDate_exp(res.getString(10));
                p.setImagep(res.getString("imagep"));
                p.setEtatpr(res.getInt("etatpr"));
                p.setIduser(res.getInt("iduser"));
                p.setNbreViews(res.getInt("views"));

                prod.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;

    }

    @Override
    public Products getById(int id) {
        Products p = null;
        try {
            ResultSet res = stm.executeQuery("select * from `produit` where idpr= " + id + "");
            if (res.next()) {
                p = new Products();
                p.setIdpr(res.getInt("idpr"));
                p.setNomp(res.getString(2));
                p.setPrix(res.getDouble(3));
                p.setDescription(res.getString(4));
                p.setCatProd(cs.getAllCatProd(res.getInt(9)));
                p.setDate_exp(res.getString(10));
                p.setImagep(res.getString(5));
                p.setNbreViews(res.getInt("views"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }
    
    @Override
    public void activerProduit(Products product)
    {
        String req = "update`produit` set `etatpr`=1 WHERE `idpr`=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, product.getIdpr());
            System.out.println("service " + product.getIdpr());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur update Etat" + ex.getMessage());
        }
    }

    @Override
    public ArrayList<Products> findByName(String name) {
         ArrayList<Products> prod = new ArrayList<>();
        Products p = null;
        try {
            String search = "'%"+name+"%'";
            ResultSet res = stm.executeQuery("select * from `produit` where nomp like "+search);
            while (res.next()) {
                p = new Products();
                p.setIdpr(res.getInt("idpr"));
                p.setNomp(res.getString(2));
                p.setPrix(res.getDouble(3));
                p.setDescription(res.getString(4));
                p.setCatProd(cs.getAllCatProd(res.getInt(9)));
                p.setDate_exp(res.getString(10));
                p.setImagep(res.getString("imagep"));
                p.setEtatpr(res.getInt("etatpr"));
                p.setNbreViews(res.getInt("views"));

                prod.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("error finding name");
        }

        return prod;
    }

    @Override
    public void incrementViews(Products product) {
        String req = "update produit set views=views+1 where idpr=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);

           // ste.setString(1, product.getNomp());
            ste.setInt(1, product.getIdpr());
            ste.executeUpdate();

            System.out.println("views ++");
        } catch (SQLException ex) {
            System.out.println("Erreur update " + ex.getMessage());
        }
    }

    @Override
    public void refuserProduit(Products product) {
        String req = "update`produit` set `etatpr`=-1 WHERE `idpr`=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, product.getIdpr());
            System.out.println("service " + product.getIdpr());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur update Etat" + ex.getMessage());
        }
    }

    @Override
    public ArrayList<Products> searchByCategory(CatProd category) {
         ArrayList<Products> prod = new ArrayList<>();
        Products p = null;
        try {
            ResultSet res = stm.executeQuery("select * from `produit` where idcp="+category.getIdcp());
            while (res.next()) {
                p = new Products();
                p.setIdpr(res.getInt("idpr"));
                p.setNomp(res.getString(2));
                p.setPrix(res.getDouble(3));
                p.setDescription(res.getString(4));
                p.setCatProd(cs.getAllCatProd(res.getInt(9)));
                p.setDate_exp(res.getString(10));
                p.setImagep(res.getString("imagep"));
                p.setEtatpr(res.getInt("etatpr"));
                p.setNbreViews(res.getInt("views"));

                prod.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("error finding name");
        }

        return prod;
    }

    

}
