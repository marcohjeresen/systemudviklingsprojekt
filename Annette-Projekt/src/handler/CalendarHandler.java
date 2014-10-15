/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CalendarClass;
import model.Customer;
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

    public ArrayList<CalendarClass> getCalendarFromDB(String firstDate, String lastDate) {
        DateFormatTools dt = new DateFormatTools();
        ArrayList<CalendarClass> cList = new ArrayList<>();
        String sql = "select * from calendar, massage, customer, massagetype "
                + "where c_massage_id = m_id and c_customer_number = cus_phone "
                + "and m_type_id = mt_id and (c_date between '"+firstDate+"%' and '"+lastDate+"%');";
        try {
            ResultSet rs = db.getResult(sql);
            while (rs.next()) {
                MassageType mt = new MassageType(rs.getInt("mt_id"), rs.getInt("mt_price"), rs.getString("mt_type"), rs.getInt("mt_duration"));
                Customer c = new Customer(rs.getString("cus_phone"), rs.getString("cus_name"), rs.getString("cus_homeAddress"), rs.getString("cus_address"));
                Massage m = new Massage(rs.getInt("m_id"), rs.getString("m_comment"), rs.getString("m_startTime"), mt, c);
               String date = rs.getString("c_date").substring(0, 10);
                CalendarClass calC = new CalendarClass(dt.getDateFromString(date), c, m);
                cList.add(calC);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage() + " I AM BATMAN");
        }
        return cList;

    }
}