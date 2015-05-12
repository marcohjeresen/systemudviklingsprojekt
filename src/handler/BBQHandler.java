/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import model.AccompanimentToBBQ;
import model.Barbercue;
import model.Customer;
import model.Event;
import model.GrillToBBQ;
import model.MeatToBBQ;
import model.SaladToBBQ;
import model.VegetableToSalad;
import util.Listeners;

/**
 *
 * @author Mark
 */
public class BBQHandler {

    private static BBQHandler bbqh;
    private DBConnection dbc;
    private Listeners listeners;
    private String sql;

    private BBQHandler() throws ClassNotFoundException, SQLException {
        dbc = DBConnection.getInstance();
        listeners = Listeners.getList();
        sql = "";
    }

    public static BBQHandler getInstance() throws ClassNotFoundException, SQLException {
        if (bbqh == null) {
            bbqh = new BBQHandler();
        }
        return bbqh;
    }

    public void saveBarbecueToDB(Event event) throws SQLException {
        ResultSet rs;
        int id = 0;

        Barbercue bar = event.getBarbercue();
        Customer customer = event.getCustomer();

        rs = dbc.getResult("select max(b_id) from barbecue;");
        while (rs.next()) {
            id = rs.getInt("max(b_id)") + 1;
        }
        sql = "insert into barbecue values (" + id + "," + bar.getSettings() + ",'" + bar.getAddress() + "',"
                + "'" + bar.getFoodReady() + "'," + bar.getSalary() + "," + bar.getTransport() + ","
                + bar.getTotalPrice() + ",'"+bar.getComments()+"');";
        dbc.execute(sql);
        if (!bar.getMeatList().isEmpty()) {
            for (MeatToBBQ meat : bar.getMeatList()) {
                sql = "insert into meatToBBQ values (" + meat.getMeat().getId() + ","
                        + id + "," + meat.getKilo() + ");";
                dbc.execute(sql);
            }
        }

        if (!bar.getAccompanimentsList().isEmpty()) {
            for (AccompanimentToBBQ acc : bar.getAccompanimentsList()) {
                sql = "insert into accompanimentToBBQ values (" + acc.getAccompaniment().getId() + ","
                        + id + ");";
                dbc.execute(sql);
            }
        }

        if (!bar.getSaladList().isEmpty()) {
            for (SaladToBBQ salad : bar.getSaladList()) {
                if (salad.getSalad().getType().equals("Salatbar")) {
                    for (VegetableToSalad vgToSalad : salad.getSalad().getVegetableList()) {
                        sql = "insert into vegetableToSalad values (" + id + "," + vgToSalad.getSalad().getId() + ","
                                + vgToSalad.getVegetable().getId() + ");";
                        dbc.execute(sql);
                    }
                }
                sql = "insert into saladToBBQ values (" + id + "," + salad.getSalad().getId() + ");";
                dbc.execute(sql);
            }
        }

        if (!bar.getGrillList().isEmpty()) {
            for (GrillToBBQ gril : bar.getGrillList()) {
                sql = "insert into grillToBBQ values (" + gril.getGrill().getId() + "," + id + ");";
                dbc.execute(sql);
            }
        }

        Calendar cal = event.getDate();
        int month = cal.get(Calendar.MONTH) + 1;
        String date = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DAY_OF_MONTH);
        date = date + " " + bar.getFoodReady();

        sql = "insert into calendar values ('" + date + "','" + customer.getPhone() + "',null," + id + ");";
        System.out.println(sql);
        dbc.execute(sql);

    }

}
