/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import model.CustomerBuilder;

/**
 *
 * @author MaleneLykke
 */
public class CustomerHandler {

    private DBConnection db;
    private static CustomerHandler ch;
    private String sql;

    private CustomerHandler() throws ClassNotFoundException, SQLException {
        db = DBConnection.getInstance();
        sql = "";
    }

    public static CustomerHandler getInstance() throws ClassNotFoundException, SQLException {
        if (ch == null) {
            ch = new CustomerHandler();
        }
        return ch;
    }

    public ArrayList<Customer> getSpecificCustomerFromDb(String phone, boolean phoneSearch) throws SQLException {
        ArrayList<Customer> cus = new ArrayList<>();
        if (!"".equals(phone)) {
            if (phoneSearch) {
                sql = "select * from customer where cus_phone like '" + phone + "%'";
            } else {
                sql = "select * from customer where cus_name like '" + phone + "%'";
            }
                ResultSet rs = db.getResult(sql);
                while (rs.next()) {
                    cus.add(new CustomerBuilder().setPhone(rs.getString("cus_phone")).setName(rs.getString("cus_name")).setHomeAddress(rs.getString("cus_homeAddress")).setAddress(rs.getString("cus_address")).createCustomer());
                }
        }
        return cus;
    }
    
    public void saveCustomer(Customer customer) throws SQLException{
        sql = "insert into customer values ('"+customer.getPhone()+"', '"+customer.getName()+"', '"+customer.getHomeAddress()+"', '"+customer.getAddress()+"');";
            db.execute(sql);
    }
    
    public String getSql() {
        return sql;
    }
}