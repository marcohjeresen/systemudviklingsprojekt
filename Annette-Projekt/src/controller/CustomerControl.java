/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.CustomerHandler;
import model.Customer;

/**
 *
 * @author MaleneLykke
 */
public class CustomerControl {

    private CustomerHandler ch;
    private static CustomerControl cc;

    private CustomerControl() {
        ch = CustomerHandler.getInstance();
    }

    public static CustomerControl getInstance() {
        if (cc == null) {
            cc = new CustomerControl();
        }
        return cc; //that shit
    }

    public Customer getSpecificCustomer(String phone, boolean phoneCearch) {
        return ch.getSpecificCustomerFromDb(phone, phoneCearch);
    }
}
