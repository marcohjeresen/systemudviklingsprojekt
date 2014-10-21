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

    public ArrayList<Customer> getSpecificCustomerFromDb(String phone, boolean phoneSearch) {
        ArrayList<Customer> cus = new ArrayList<>();
        String sql = "";
        if (!"".equals(phone)) {
            if (phoneSearch) {
                sql = "select * from customer where cus_phone like '" + phone + "%'";
            } else {
                sql = "select * from customer where cus_name like '" + phone + "%'";
            }
            try {
                ResultSet rs = db.getResult(sql);
                while (rs.next()) {
                    cus.add(new CustomerBuilder().setPhone(rs.getString("cus_phone")).setName(rs.getString("cus_name")).setHomeAddress(rs.getString("cus_homeAddress")).setAddress(rs.getString("cus_address")).createCustomer());
                }
            } catch (SQLException ex) {
                System.out.println("Exception occured in CustomerHandler - getSpecificCustomerFromDb SQL exception\n" + ex.getLocalizedMessage());
            }
        }
        return cus;
    }
    
    public void saveCustomer(Customer customer){
        String sql = "insert into customer values ('"+customer.getPhone()+"', '"+customer.getName()+"', '"+customer.getHomeAddress()+"', '"+customer.getAddress()+"');";
        try {
            db.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Exception occured in CustomerHandler - saveCustomer SQL exception\n" + ex.getLocalizedMessage());
        }
    }
}
