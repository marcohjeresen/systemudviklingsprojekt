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
public class Customer {
    private String phone;
    private String name;
    private String homeAddress;
    private String address;

    public Customer(String phone, String name, String homeAddress, String address) {
        this.phone = phone;
        this.name = name;
        this.homeAddress = homeAddress;
        this.address = address;
    }
}
