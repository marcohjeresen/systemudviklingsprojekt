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
import util.Listeners;

/**
 *
 * @author Annette
 */
public class CategoryHandler {

    private DBConnection db;
    private static CategoryHandler ch;
    private Listeners listeners;
    private String sql;

    private CategoryHandler() throws ClassNotFoundException, SQLException {
        db = DBConnection.getInstance();
        listeners = Listeners.getList();
        sql = "";
    }

    public static CategoryHandler getInstance() throws ClassNotFoundException, SQLException {
        if (ch == null) {
            ch = new CategoryHandler();
        }
        return ch;
    }

    public ArrayList<Category> getCategoryList() throws SQLException {
        ArrayList<Category> catList = new ArrayList<>();
        ResultSet rs;
        rs = db.getResult("call getCategories();");
        while (rs.next()) {
            Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            catList.add(category);
        }
        return catList;
    }
}
