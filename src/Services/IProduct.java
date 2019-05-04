/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.CatProd;
import Entites.Products;
import java.util.ArrayList;


/**
 *
 * @author Don Solid
 *
 */
public interface IProduct {
    void addProduct(Products pd);
    void updateProduct(Products pd);
    void deleteProduct(Products pd);
    ArrayList<Products>getAllProducts();
    Products getById(int pd);
    void activerProduit(Products product);
    void refuserProduit(Products product);
    ArrayList<Products> findByName(String name);
    void incrementViews(Products product);
    ArrayList<Products> searchByCategory(CatProd category);
}
