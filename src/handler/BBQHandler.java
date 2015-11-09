/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import model.Accompaniment;
import model.AccompanimentToBBQ;
import model.Barbercue;
import model.Category;
import model.Customer;
import model.Event;
import model.Grill;
import model.GrillToBBQ;
import model.MassageType;
import model.Meat;
import model.MeatToBBQ;
import model.Salad;
import model.SaladToBBQ;
import model.Vegetable;
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
    
    public void saveBarbecueToDB(Event event, boolean edit) throws SQLException {
        ResultSet rs;
        int id = 0;
        
        Barbercue bar = event.getBarbercue();
        Customer customer = event.getCustomer();
        if (!edit) {
            rs = dbc.getResult("select max(b_id) from barbecue;");
            while (rs.next()) {
                id = rs.getInt("max(b_id)") + 1;
            }            
        }else{
            id = event.getBarbercue().getId();
            dbc.execute("delete from barbecue where b_id ="+id+";");
        }
        
        sql = "insert into barbecue values (" + id + "," + bar.getSettings() + ",'" + bar.getAddress() + "',"
                + "'" + bar.getFoodReady() + "'," + bar.getSalary() + "," + bar.getTransport() + ","
                + bar.getTotalPrice() + ",'" + bar.getComments() + "');";
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
    
    public void getbbqEvent(Event event) throws SQLException {
        ResultSet rs;
        SaladToBBQ saladToBBQ = null;
        
        rs = dbc.getResult("call getbbqgrill(" + event.getBarbercue().getId() + ")");
        while (rs.next()) {
            Category c = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            Grill g = new Grill(rs.getInt("g_id"), rs.getString("g_type"), rs.getInt("g_coalprice"), c);
            GrillToBBQ gtbbq = new GrillToBBQ(g, event.getBarbercue());
            event.getBarbercue().addGrillToBbqToList(gtbbq);
            
        }
        rs = dbc.getResult("call getmeatToBBQ(" + event.getBarbercue().getId() + ")");
        while (rs.next()) {
            Category c1 = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            Meat m = new Meat(rs.getInt("m_id"), rs.getString("m_type"), rs.getInt("m_pricePrKilo"), c1);
            MeatToBBQ meatToBBQ = new MeatToBBQ(m, event.getBarbercue(), rs.getInt("mtb_kilo"));
            event.getBarbercue().addMeatToBbqToList(meatToBBQ);
            
        }
        rs = dbc.getResult("call getsaladToBBQ(" + event.getBarbercue().getId() + ")");
        while (rs.next()) {
            Category c2 = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            Salad s = new Salad(rs.getInt("s_id"), rs.getString("s_type"), rs.getInt("s_pricePerHead"), c2);
            saladToBBQ = new SaladToBBQ(s, event.getBarbercue());
            event.getBarbercue().addsaladToBbqToList(saladToBBQ);
        }
        if (event.getBarbercue().getSaladList() != null) {
            if (!event.getBarbercue().getSaladList().isEmpty()) {
                for (SaladToBBQ saladList : event.getBarbercue().getSaladList()) {
                    if (saladList.getSalad().getType() == "Salatbar") {
                        rs = dbc.getResult("call getvegeToBBQ(" + saladList.getSalad().getId() + ")");
                        while (rs.next()) {
                            Vegetable v = new Vegetable(rs.getInt("v_id"), rs.getString("v_type"), rs.getInt("v_pricePerHead"));
                            VegetableToSalad vegetableToSalad = new VegetableToSalad(event.getBarbercue().getId(), v, saladList.getSalad());
                            saladToBBQ.getSalad().addToVegetableList(vegetableToSalad);
                        }
                    }
                }
            }
        }
        
        rs = dbc.getResult("call getaccToBBQ(" + event.getBarbercue().getId() + ")");
        while (rs.next()) {
            Category c3 = new Category(rs.getInt("c_id"), rs.getString("c_name"));
            Accompaniment a = new Accompaniment(rs.getInt("a_id"), rs.getString("a_type"), rs.getInt("a_pricePerHead"), c3);
            AccompanimentToBBQ toBBQ = new AccompanimentToBBQ(a, event.getBarbercue());
            event.getBarbercue().addAccToBqqToList(toBBQ);
        }
        
    }
    
}
