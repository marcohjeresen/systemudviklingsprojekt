/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.CalendarHandler;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import model.Event;
import util.Listeners;

/**
 *
 * @author markh_000
 */
public class Calendar_Ct {

    private static Calendar_Ct cc;
    private Listeners listener;
    private Calendar cal;
    private ArrayList<Integer> callList;
    private String firstDayOfWeeek; 
    private String lastDayofWeek;
    private CalendarHandler ch;

    private Calendar_Ct() throws ClassNotFoundException, SQLException {
        listener = Listeners.getList();
        cal = Calendar.getInstance();
        ch = CalendarHandler.getInstance();
        callList = new ArrayList<>();
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
        listener.notifyListeners("Chosen Date");
    }

    public Calendar getCal() {
        return cal;
    }

    //den finder den uge som den valgte dato ligger i og retunere et ArrayList med dem
    public ArrayList<String> getWeek() {
        callList.removeAll(callList);
        ArrayList<String> days = new ArrayList<>();
        String day = new SimpleDateFormat("EEEE").format(cal.getTime());
        firstDayOfWeeek = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        while (!day.equals("mandag")) {
            cal.roll(Calendar.DAY_OF_YEAR, -1);
            day = new SimpleDateFormat("EEEE").format(cal.getTime());
            cal.roll(Calendar.DAY_OF_YEAR, -1);
            firstDayOfWeeek = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            cal.roll(Calendar.DAY_OF_YEAR, 1);
        }
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.MMM.yyyy");
        int delta = -cal.get(Calendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        cal.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            int dag = cal.get(Calendar.DAY_OF_YEAR);
            String dateToUpper = format.format(cal.getTime());
            days.add(dateToUpper.substring(0, 1).toUpperCase() + dateToUpper.substring(1));
            callList.add(dag);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            cal.roll(Calendar.DAY_OF_YEAR, 1);
            lastDayofWeek = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            cal.roll(Calendar.DAY_OF_YEAR, -1);
        }
        return days;
    }
    
    public ArrayList<Event> getEventsOfWeek() throws SQLException{
        ArrayList<Event> calList = ch.getEventFromDB(firstDayOfWeeek, lastDayofWeek);
        return calList;
    }

    public ArrayList<Integer> getCallList(){
        return callList;
    }

    public static Calendar_Ct getInstance() throws ClassNotFoundException, SQLException {
        if (cc == null) {
            cc = new Calendar_Ct();
        }
        return cc;
    }
    
    public ArrayList<String> getDates() throws SQLException{
        return ch.getDates();
    }
}
