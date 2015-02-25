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

    public CustomerBuilder() {
    }

    public CustomerBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
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
        if (phone.equals("")) {
            throw new IllegalStateException("Cannot create customer without phone");
        } else if (name.equals("")) {
            throw new IllegalStateException("Cannot create customer without name");
        }
        if (homeAddress == null) {
            homeAddress = "";
        }
        if (address == null) {
            address = "";
        }
        return new Customer(phone, name, homeAddress, address);
    }
    
}
