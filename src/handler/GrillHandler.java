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
import model.Grill;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class GrillHandler {
    private DBConnection db;
    private static GrillHandler gh;
    private Listeners listener;
    private String sql;
    
    private GrillHandler() throws ClassNotFoundException, SQLException{
        db = DBConnection.getInstance();
        listener = Listeners.getList(); 
        sql = "";
    }
    
    public static GrillHandler getInstance() throws ClassNotFoundException, SQLException{
        if(gh == null){
            gh = new GrillHandler();
        }
        return gh;
    }
    
    public ArrayList<Grill> getGrillList() throws SQLException{
        ArrayList<Grill> grills = new ArrayList<>();
        ResultSet rs;
        rs = db.getResult("call getGrills();");
        while(rs.next()){
            Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            Grill grill = new Grill(rs.getInt("g_id"), rs.getString("g_type"), rs.getInt("g_coalPrice"), category);
            grills.add(grill);
        }
        System.out.println(grills.size());
        return grills;
    }
}
