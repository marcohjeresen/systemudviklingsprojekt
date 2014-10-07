/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Annette
 */
public class Massage {

    private int id;
    private String comment;
    private String startTime;
    private MassageType type;
    private Customer customer;

    public Massage(int id, String comment, String startTime, MassageType type, Customer customer) {
        this.id = id;
        this.comment = comment;
        this.startTime = startTime;
        this.type = type;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public MassageType getType() {
        return type;
    }

    public void setType(MassageType type) {
        this.type = type;
    }

}
