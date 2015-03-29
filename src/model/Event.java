/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
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

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setMassage(Massage massage) {
        this.massage = massage;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        return "Sund Massage\n\n\n" + customer + "\n\n" + dateFormat.format(date.getTime()) + "\t\t" + massage.getStartTime() + "\n\n" + massage + "\n\nDorthe Sund Petersen\nFysiurgisk massør\n\nVallensved Bygade 11\n4700 Næstved\n\nTlf: 22387172";
    }

}
