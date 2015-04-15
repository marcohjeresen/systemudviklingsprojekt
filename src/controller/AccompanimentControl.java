/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.AccompanimentHandler;
import java.sql.SQLException;
import model.Accompaniment;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class AccompanimentControl {
    private AccompanimentHandler ah;
    private static AccompanimentControl ac;
    private Accompaniment accompaniment;
    private Listeners listeners;

    private AccompanimentControl() throws ClassNotFoundException, SQLException {
        ah = AccompanimentHandler.getInstance();
        listeners = Listeners.getList();
    }
    
    public static AccompanimentControl getInstance() throws ClassNotFoundException, SQLException{
        if (ac == null) {
            ac = new AccompanimentControl();
        }
        return ac;
    }
    
    
    
}
