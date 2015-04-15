/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Accompaniment;
import model.Category;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class AccompanimentHandler {
    private DBConnection db;
    private static AccompanimentHandler ah;
    private Listeners listener;
    private String sql;
    
    private AccompanimentHandler() throws ClassNotFoundException, SQLException{
        db = DBConnection.getInstance();
        listener = Listeners.getList();
        sql = "";
    }
    
    public static AccompanimentHandler getInstance() throws ClassNotFoundException, SQLException{
        if(ah == null){
            ah = new AccompanimentHandler();
        }
        return ah;
    }
    
    public ArrayList<Accompaniment> getAccompanimentList() throws SQLException{
        ArrayList<Accompaniment> accList = new ArrayList<>();
        ResultSet rs;
        rs = db.getResult("call getAccompaniment();");
        while(rs.next()){
            Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            Accompaniment accompaniment = new Accompaniment(rs.getInt("a_id"), rs.getString("a_type"), rs.getInt("a_pricePrHead"), category);
            accList.add(accompaniment);
        }
        return accList;
    }
}
