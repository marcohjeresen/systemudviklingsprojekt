/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Calendar;

/**
 *
 * @author Annette
 */
public class Event {
    private Calendar date;
    private Customer customer;
    private Massage massage;

    public Event(Calendar date, Customer customer, Massage massage) {
        this.date = date;
        this.customer = customer;
        this.massage = massage;
    }

    public Calendar getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Massage getMassage() {
        return massage;
    }
    
    
}
