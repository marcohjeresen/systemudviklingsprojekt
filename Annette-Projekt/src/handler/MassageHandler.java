/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CalendarClass;
import model.Customer;
import model.Massage;
import model.MassageType;
import org.omg.CORBA.MARSHAL;

/**
 *
 * @author Annette
 */
public class MassageHandler {

    private DBConnection db;
    private static MassageHandler mh;

    private MassageHandler() {
        db = DBConnection.getInstance();
    }

    public static MassageHandler getInstance() {
        if (mh == null) {
            mh = new MassageHandler();
        }
        return mh;
    }

    public ArrayList<MassageType> getMTypeList() {
        ArrayList<MassageType> massTypeList = new ArrayList<>();

        ResultSet rs;
        try {
            rs = db.getResult("select * from massagetype");
            while (rs.next()) {
                MassageType mt = new MassageType(rs.getInt("mt_id"), rs.getInt("mt_price"), rs.getString("mt_type"), rs.getInt("mt_duration"));
                massTypeList.add(mt);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return massTypeList;
    }

    public void saveMassage(Massage massage, CalendarClass cal) throws SQLException {
        ResultSet rs;
        int id = 0;
        try {
            rs = db.getResult("select max(m_id) from massage;");
            while (rs.next()) {
                id = rs.getInt("max(m_id)") + 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        String sql = "insert into massage values (" + id + ",'"
                + massage.getComment() + "','" + massage.getStartTime()
                + "'," + massage.getType().getId() + ");";
        int month = cal.getDate().get(Calendar.MONTH)+1;
        String date = cal.getDate().get(Calendar.YEAR)+"-"+ month+"-"+cal.getDate().get(Calendar.DAY_OF_MONTH);
        date = date + " "+ massage.getStartTime();
        String sqlCal = "insert into calendar values ('" + date + "','"
                + cal.getCustomer().getPhone() + "'," + id + ");";
        try {
            db.execute(sql);
            db.execute(sqlCal);
        } catch (SQLException ex) {
            
            if (ex.getLocalizedMessage().length() == 55) {
               throw ex;
            }else{
                System.out.println(ex.getLocalizedMessage());
            }
            
        }
    }

   
}
