/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.SaladHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Salad;
import model.Vegetable;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class SaladControl {
    private SaladHandler saladHandler;
    private static SaladControl sc;
    private Listeners listeners;
    
    private SaladControl() throws ClassNotFoundException, SQLException{
        saladHandler = SaladHandler.getInstance();
        listeners = Listeners.getList();
    }
    
    public static SaladControl getInstance() throws ClassNotFoundException, SQLException{
        if(sc == null){
            sc = new SaladControl();
        }
        return sc;
    }
    
    public ArrayList<Salad> getSaladList() throws SQLException{
        return saladHandler.getSaladList();
    }
    
    public ArrayList<Vegetable> getVegetableList() throws SQLException{
        return saladHandler.getVegetableList();
    }
            
    public ArrayList<String> getStandardVegetableList() throws SQLException{
        return saladHandler.getStandardVegetableList();
    }
}
