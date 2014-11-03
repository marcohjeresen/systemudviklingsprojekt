/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import handler.MassageHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import model.Event;
import model.Massage;
import model.MassageType;

/**
 *
 * @author Annette
 */
public class MassageControl {
    private MassageHandler mh;
    private static MassageControl mc;
    private Event event = null;

    private MassageControl() throws ClassNotFoundException, SQLException {
        mh = MassageHandler.getInstance();
    }
    
    public static MassageControl getInstance() throws ClassNotFoundException, SQLException{
        if(mc == null){
            mc = new MassageControl();
        }
        return mc;
    }
    
    public void saveMassage(Massage massage, Event cal) throws SQLException{
        mh.saveMassage(massage, cal);
    }
    
    public ArrayList<MassageType> getMTypeList() throws SQLException{
        return mh.getMTypeList();
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    public void updateMassage(Event event, Calendar cal) throws SQLException{
        mh.updateMassage(event, cal);
    }
    
    public void deleteMassage(Event event) throws SQLException{
        mh.deleteMassage(event);
    }

    public MassageHandler getMh() {
        return mh;
    }
    
    
}
