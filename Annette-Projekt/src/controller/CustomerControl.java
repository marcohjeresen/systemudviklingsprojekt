/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.CustomerHandler;
import java.util.ArrayList;
import model.Customer;

/**
 *
 * @author MaleneLykke
 */
public class CustomerControl {

    private CustomerHandler ch;
    private static CustomerControl cc;
    private Customer customer;

    private CustomerControl() {
        ch = CustomerHandler.getInstance();
    }

    public static CustomerControl getInstance() {
        if (cc == null) {
            cc = new CustomerControl();
        }
        return cc; 
    }

    public ArrayList<Customer> getSpecificCustomer(String phone, boolean phoneSearch) {
        return ch.getSpecificCustomerFromDb(phone, phoneSearch);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public void saveCustomer(Customer customer){
        ch.saveCustomer(customer);
    }
}
