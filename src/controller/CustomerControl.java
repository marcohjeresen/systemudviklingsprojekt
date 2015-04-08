/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.CustomerHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import util.Listeners;

/**
 *
 * @author MaleneLykke
 */
public class CustomerControl {

    private CustomerHandler ch;
    private static CustomerControl cc;
    private Customer customer;
    private Listeners listener;

    private CustomerControl() throws ClassNotFoundException, SQLException {
        ch = CustomerHandler.getInstance();
        listener = Listeners.getList();
    }

    public static CustomerControl getInstance() throws ClassNotFoundException, SQLException {
        if (cc == null) {
            cc = new CustomerControl();
        }
        return cc;
    }

    public ArrayList<Customer> getSpecificCustomer(String phone, boolean phoneSearch) throws SQLException {
        return ch.getSpecificCustomerFromDb(phone, phoneSearch);
    }

    public void setCustomer(Customer customer, boolean isMassage) {
        this.customer = customer;
        if (isMassage) {
            listener.notifyListeners("Mas Customer Chosen");
        } else {
            listener.notifyListeners("BBQ Customer Chosen");
        }
        
    }

    public Customer getCustomer() {
        return customer;
    }

    public void saveCustomer(Customer customer) throws SQLException {
        ch.saveCustomer(customer);
    }

    public CustomerHandler getCh() {
        return ch;
    }

}
