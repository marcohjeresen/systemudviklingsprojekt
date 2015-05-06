/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Category;
import model.Salad;
import model.Vegetable;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class SaladHandler {
    
    private DBConnection db;
    private static SaladHandler sh;
    private Listeners listener;
    private String sql;
    
    private SaladHandler() throws ClassNotFoundException, SQLException{
        db = DBConnection.getInstance();
        listener = Listeners.getList();
        sql = "";
    }
    
    public static SaladHandler getInstance() throws ClassNotFoundException, SQLException{
        if(sh == null){
            sh = new SaladHandler();
        }
        return sh;
    }
    
    public ArrayList<Salad> getSaladList() throws SQLException{
        ArrayList<Salad> saladList = new ArrayList<>();
        ResultSet rs;
        rs = db.getResult("call getSalad();");
        while(rs.next()){
             Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
             Salad salad = new Salad(rs.getInt("s_id"), rs.getString("s_type"), rs.getInt("s_pricePerHead"), category);
             saladList.add(salad);
        }
        return saladList;
    }
    
    public ArrayList<Vegetable> getVegetableList() throws SQLException{
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        ResultSet rs;
        rs = db.getResult("call getVegetables();");
        while(rs.next()){
            Vegetable vegetable = new Vegetable(rs.getInt("v_id"), rs.getString("v_type"), rs.getInt("v_pricePerHead"));
            vegetables.add(vegetable);
        }
        return vegetables;
    }
    
    public ArrayList<String> getStandardVegetableList() throws SQLException{
        ArrayList<String> vegetables = new ArrayList<>();
        ResultSet rs;
        rs = db.getResult("call getStandardVegetables();");
        while(rs.next()){
            String vegetable = rs.getString("v_type");
            vegetables.add(vegetable);
        }
        return vegetables;
    }
}
