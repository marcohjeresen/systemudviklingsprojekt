/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.GrillHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Grill;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class GrillControl {
    private GrillHandler gh;
    private static GrillControl gc;
    private Grill grill;
    private Listeners listener;
    
    private GrillControl() throws ClassNotFoundException, SQLException{
        gh = GrillHandler.getInstance();
        listener = Listeners.getList();
    }
    
    public static GrillControl getInstance() throws ClassNotFoundException, SQLException{
        if(gc == null){
            gc = new GrillControl();
        }
        return gc;
    }
    
    public ArrayList<Grill> getGrillList() throws SQLException{
        return gh.getGrillList();
    }
}
