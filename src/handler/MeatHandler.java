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
import model.Meat;
import util.Listeners;

/**
 *
 * @author Mark
 */
public class MeatHandler {
    
    private DBConnection db;
    private static MeatHandler meatH;
    private Listeners listener;
    private String sql;

    private MeatHandler() throws ClassNotFoundException, SQLException {
        db = DBConnection.getInstance();
        listener = Listeners.getList();
        sql = "";
    }
    
    public static MeatHandler getInstance() throws ClassNotFoundException, SQLException{
        if (meatH == null) {
            meatH = new MeatHandler();
        }
        return meatH;
    }
    
    public ArrayList<Meat> getMeatList() throws SQLException{
        ArrayList<Meat> meatList = new ArrayList<>();
        ResultSet rs;
        rs = db.getResult("call getMeat();");
        while (rs.next()){
            Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            Meat meat = new Meat(rs.getInt("m_id"), rs.getString("m_type"), rs.getInt("m_pricePrKilo"), category);
            meatList.add(meat);
        }
        return meatList;
    }
    
    
    
}
