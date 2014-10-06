/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


public class CustomerBuilder {
    private String phone;
    private String name;
    private String homeAddress;
    private String address;

    public CustomerBuilder(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public CustomerBuilder setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public CustomerBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Customer createCustomer() {
        return new Customer(phone, name, homeAddress, address);
    }
    
}
