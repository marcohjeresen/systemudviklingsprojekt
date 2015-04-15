/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.CategoryHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Category;
import util.Listeners;

/**
 *
 * @author Mark
 */
public class CategoryControl {
    private CategoryHandler categoryHandler;
    private static CategoryControl cc;
    private Listeners listerners;
    private String whichCategory;

    private CategoryControl() throws ClassNotFoundException, SQLException {
        categoryHandler = CategoryHandler.getInstance();
        listerners = Listeners.getList();
    }
    
    public static CategoryControl getInstance() throws ClassNotFoundException, SQLException{
        if (cc == null) {
            cc = new CategoryControl();
        }
        return cc;
    }
    
    public ArrayList<Category> getCategoryList() throws SQLException{
        return categoryHandler.getCategoryList();
    }
    
    public void setCategory(String category){
        switch (category){
            case "Kød":
                listerners.notifyListeners("Category Meat");
                break;
            case "Tilbehør":
                listerners.notifyListeners("Category Accompaniment");
                break;
            case "Salater":
                listerners.notifyListeners("Category Salad");
                break;
            case "Grille":
                listerners.notifyListeners("Category Grill");
                break;
        }
    }
    
    
    
}
