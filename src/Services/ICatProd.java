/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author dell
 */
import Entites.CatProd;
import Entites.Products;
import java.util.ArrayList;

public interface ICatProd {

    void addCategorie(CatProd cp);
    void updateCategorie(CatProd cp);
    void deleteCategorie(CatProd cp);
    ArrayList<CatProd> getAllCatProd();
    Products getById(Products pd);
    CatProd getCategoryByName(String name);
    Boolean uniqueCatgeory(String name);
    CatProd getCategoryById(int id);
}
