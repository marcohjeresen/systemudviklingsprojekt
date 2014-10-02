/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import util.Listeners;

/**
 *
 * @author markh_000
 */
public class Calendar_Ct {

    private static Calendar_Ct cc;
    private Listeners listener;
    private Calendar cal;

    private Calendar_Ct() {
        listener = Listeners.getList();
        cal = Calendar.getInstance();
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
        listener.notifyListeners("Chosen Date");
    }

    public Calendar getCal() {
        return cal;
    }

    public ArrayList<String> getWeek() {
        ArrayList<String> days = new ArrayList<>();
        String day = new SimpleDateFormat("EEEE").format(cal.getTime());
        while (!day.equals("mandag")) {
            cal.roll(Calendar.DAY_OF_YEAR, -1);
            day = new SimpleDateFormat("EEEE").format(cal.getTime());
        }

        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.MMM.yyyy");

        int delta = -cal.get(Calendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        cal.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            String dateToUpper = format.format(cal.getTime());
            days.add(dateToUpper.substring(0, 1).toUpperCase() + dateToUpper.substring(1));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    public static Calendar_Ct getInstance() {
        if (cc == null) {
            cc = new Calendar_Ct();
        }
        return cc;
    }

}
