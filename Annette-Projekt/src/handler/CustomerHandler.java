/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.CustomerBuilder;

/**
 *
 * @author MaleneLykke
 */
public class CustomerHandler {

    private DBConnection db;
    private static CustomerHandler ch;

    private CustomerHandler() {
        db = DBConnection.getInstance();

    }

    public static CustomerHandler getInstance() {
        if (ch == null) {
            ch = new CustomerHandler();
        }
        return ch;
    }

    public Customer getSpecificCustomerFromDb(String phone) {
        Customer cus = null;
        String sql = "select * from customer where cus_phone = " + phone;
        try {
            ResultSet rs = db.getResult(sql);
            while (rs.next()) {
                cus = new Customer(rs.getString("cus_phone"), rs.getString("cus_name"), rs.getString("cus_homeAddress"), rs.getString("cus_address"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception occured in CustomerHandler - getSpecificCustomerFromDb SQL exception\n" + ex.getLocalizedMessage());
        }
        return cus;
    }
}
