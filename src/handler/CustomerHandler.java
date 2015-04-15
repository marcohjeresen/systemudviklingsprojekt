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

    public ArrayList<Customer> getSpecificCustomerFromDb(String search, boolean phoneSearch) throws SQLException {
        ArrayList<Customer> cus = new ArrayList<>();
        if (!"".equals(search)) {
            if (phoneSearch) {
                sql = "select * from customer where cus_phone like '" + search + "%'";
            } else {
                sql = "select * from customer where cus_name like '" + search + "%'";
            }
            ResultSet rs = db.getResult(sql);
            while (rs.next()) {
                cus.add(new CustomerBuilder().setPhone(rs.getString("cus_phone")).setName(rs.getString("cus_name")).setAddress(rs.getString("cus_address")).setEmail(rs.getString("cus_email")).createCustomer());
            }
        }
        return cus;
    }

    public void saveCustomer(Customer customer) throws SQLException {
        sql = "insert into customer values ('" + customer.getPhone() + "', '" + customer.getName() + "', '" + customer.getAddress() + "', '" + customer.getEmail() + "');";
        db.execute(sql);
    }

    public void alterCustomer(Customer customer, String cusNumber, boolean isMassage) throws SQLException {
        if (isMassage) {
            sql = "update customer set Cus_phone = " + customer.getPhone()
                    + ", Cus_name = '" + customer.getName()
                    + "' where cus_phone = " + cusNumber + "";
        } else {
            sql = "update customer set Cus_phone = " + customer.getPhone()
                    + ", cus_name = '" + customer.getName() + "',"
                    + " cus_address = '" + customer.getAddress() + "',"
                    + " cus_email = '" + customer.getEmail() + "'"
                    + " where cus_phone = " + cusNumber + "";
        }
        db.execute(sql);
    }

    public String getSql() {
        return sql;
    }
}
