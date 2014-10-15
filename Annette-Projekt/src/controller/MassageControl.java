/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import handler.MassageHandler;
import java.util.ArrayList;
import model.CalendarClass;
import model.Massage;
import model.MassageType;

/**
 *
 * @author Annette
 */
public class MassageControl {
    private MassageHandler mh;
    private static MassageControl mc;

    private MassageControl() {
        mh = MassageHandler.getInstance();
    }
    
    public static MassageControl getInstance(){
        if(mc == null){
            mc = new MassageControl();
        }
        return mc;
    }
    
    public void saveMassage(Massage massage, CalendarClass cal){
        mh.saveMassage(massage, cal);
    }
    
    public ArrayList<MassageType> getMTypeList(){
        return mh.getMTypeList();
    }
}
