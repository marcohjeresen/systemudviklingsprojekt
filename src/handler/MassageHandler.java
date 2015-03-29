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
import model.Event;
import model.Massage;
import model.MassageType;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class MassageHandler {

    private DBConnection db;
    private static MassageHandler mh;
    private Listeners listener;
    private String sql;
    private String sqlCal;

    private MassageHandler() throws ClassNotFoundException, SQLException {
        listener = Listeners.getList();
        db = DBConnection.getInstance();
        sql = "";
        sqlCal = "";
    }

    public static MassageHandler getInstance() throws ClassNotFoundException, SQLException {
        if (mh == null) {
            mh = new MassageHandler();
        }
        return mh;
    }

    public ArrayList<MassageType> getMTypeList() throws SQLException {
        ArrayList<MassageType> massTypeList = new ArrayList<>();
        ResultSet rs;
            rs = db.getResult("select * from massagetype");
            while (rs.next()) {
                MassageType mt = new MassageType(rs.getInt("mt_id"), rs.getInt("mt_price"), rs.getString("mt_type"), rs.getInt("mt_duration"));
                massTypeList.add(mt);
            }
        return massTypeList;
    }

    public void saveMassage(Massage massage, Event cal) throws SQLException {
        ResultSet rs;
        int id = 0;
            rs = db.getResult("select max(m_id) from massage;");
            while (rs.next()) {
                id = rs.getInt("max(m_id)") + 1;
            }
        sql = "insert into massage values (" + id + ",'"
                + massage.getComment() + "','" + massage.getStartTime()
                + "'," + massage.getType().getId() + ");";
        int month = cal.getDate().get(Calendar.MONTH) + 1;
        String date = cal.getDate().get(Calendar.YEAR) + "-" + month + "-" + cal.getDate().get(Calendar.DAY_OF_MONTH);
        date = date + " " + massage.getStartTime();
        sqlCal = "insert into calendar values ('" + date + "','"
                + cal.getCustomer().getPhone() + "'," + id + ");";
            db.execute(sql);
            db.execute(sqlCal);
    }

    public void updateMassage(Event event, Calendar cal) throws SQLException {
        sql = "update massage set m_comment='" + event.getMassage().getComment()
                + "', m_startTime='" + event.getMassage().getStartTime() + "', m_type_id="
                + event.getMassage().getType().getId() + " where m_id=" + event.getMassage().getId() + ";";
        int month = cal.get(Calendar.MONTH) + 1;
        String date = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DAY_OF_MONTH);
        date = date + " " + event.getMassage().getStartTime();
        sqlCal = "update calendar set c_date='" + date + "' where c_date='" + cal.getTime() + "';";
            db.execute(sql);
            db.execute(sqlCal);
    }

    public void deleteMassage(Event event) throws SQLException {
            sql = "delete from massage where m_id=" + event.getMassage().getId() + ";";
            db.execute(sql);
            listener.notifyListeners("New Event Created");
    }
    
    public void editMassageType(int id, int price, int duration) throws SQLException{
        sql = "update massagetype set mt_price=" + price + ", mt_duration="+duration+" where mt_id=" + id + ";";
        db.execute(sql);
    }
    
    public String getSql() {
        return sql;
    }

    public String getSqlCal() {
        return sqlCal;
    }
    
    
}
