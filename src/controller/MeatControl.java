/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.MeatHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Meat;
import util.Listeners;

/**
 *
 * @author Mark
 */
public class MeatControl {
    private MeatHandler meatHandler;
    private static MeatControl mc;
    private Listeners listeners;

    private MeatControl() throws ClassNotFoundException, SQLException {
        meatHandler = MeatHandler.getInstance();
        listeners = Listeners.getList();
    }
    
    public static MeatControl getInstance() throws ClassNotFoundException, SQLException{
        if (mc == null) {
            mc = new MeatControl();
        }
        return mc;
    }
    
    public ArrayList<Meat> getMeatList() throws SQLException{
        return meatHandler.getMeatList();
    }
    
    
}
