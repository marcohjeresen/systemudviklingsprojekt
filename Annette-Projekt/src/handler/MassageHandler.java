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

    private MassageHandler() throws ClassNotFoundException, SQLException {
        listener = Listeners.getList();
        db = DBConnection.getInstance();
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
        String sql = "insert into massage values (" + id + ",'"
                + massage.getComment() + "','" + massage.getStartTime()
                + "'," + massage.getType().getId() + ");";
        int month = cal.getDate().get(Calendar.MONTH) + 1;
        String date = cal.getDate().get(Calendar.YEAR) + "-" + month + "-" + cal.getDate().get(Calendar.DAY_OF_MONTH);
        date = date + " " + massage.getStartTime();
        String sqlCal = "insert into calendar values ('" + date + "','"
                + cal.getCustomer().getPhone() + "'," + id + ");";
            db.execute(sql);
            db.execute(sqlCal);
//        } catch (SQLException ex) {
//            if (ex.getLocalizedMessage().length() == 55) {
//                throw ex;
//            } else {
//                System.out.println("Exception occured in MassageHandler - saveMassage SQL exception\n" + ex.getLocalizedMessage());
//            }
//        }
    }

    public void updateMassage(Event event, Calendar cal) throws SQLException {
        String sqlMas = "update massage set m_comment='" + event.getMassage().getComment()
                + "', m_startTime='" + event.getMassage().getStartTime() + "', m_type_id="
                + event.getMassage().getType().getId() + " where m_id=" + event.getMassage().getId() + ";";
        int month = cal.get(Calendar.MONTH) + 1;
        String date = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DAY_OF_MONTH);
        date = date + " " + event.getMassage().getStartTime();
        String sqlCal = "update calendar set c_date='" + date + "' where c_date='" + cal.getTime() + "';";
            db.execute(sqlMas);
            db.execute(sqlCal);
    }

    public void deleteMassage(Event event) throws SQLException {
        String sqlMas = "delete from massage where m_id=" + event.getMassage().getId() + ";";
            db.execute(sqlMas);
            listener.notifyListeners("New Event Created");
    }
}
