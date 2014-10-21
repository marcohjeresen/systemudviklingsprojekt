/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Event;
import model.Customer;
import model.CustomerBuilder;
import model.Massage;
import model.MassageType;
import util.DateFormatTools;

/**
 *
 * @author Annette
 */
public class CalendarHandler {

    private DBConnection db;
    private static CalendarHandler ch;

    private CalendarHandler() {
        db = DBConnection.getInstance();
    }

    public static CalendarHandler getInstance() {
        if (ch == null) {
            ch = new CalendarHandler();
        }
        return ch;
    }

    public ArrayList<Event> getEventFromDB(String firstDate, String lastDate) {
        DateFormatTools dt = new DateFormatTools();
        ArrayList<Event> eventList = new ArrayList<>();
        String sql = "select * from calendar, massage, customer, massagetype "
                + "where c_massage_id = m_id and c_customer_number = cus_phone "
                + "and m_type_id = mt_id and (c_date between '" + firstDate + "%' and '" + lastDate + "%') order by c_date;";
        try {
            ResultSet rs = db.getResult(sql);
            while (rs.next()) {
                MassageType mt = new MassageType(rs.getInt("mt_id"), rs.getInt("mt_price"), rs.getString("mt_type"), rs.getInt("mt_duration"));
                Customer c = new CustomerBuilder().setPhone(rs.getString("cus_phone")).setName(rs.getString("cus_name")).setHomeAddress(rs.getString("cus_homeAddress")).setAddress(rs.getString("cus_address")).createCustomer();
                Massage m = new Massage(rs.getInt("m_id"), rs.getString("m_comment"), rs.getString("m_startTime"), mt);
                String date = rs.getString("c_date").substring(0, 10);
                Event calC = new Event(dt.getDateFromString(date), c, m);
                eventList.add(calC);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage() + " I AM BATMAN");
        }
        return eventList;
    }

    public ArrayList<String> getDates() {
        ArrayList<String> dates = new ArrayList<>();
        String sql = "select c_date from calendar;";
        try {
            ResultSet rs = db.getResult(sql);
            while (rs.next()) {
                dates.add(rs.getString("c_date").substring(0, 10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dates;
    }
}
