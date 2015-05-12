/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class CustomerBuilder {
    private String phone;
    private String name;
    private String address;
    private String email;

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

    public CustomerBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer createCustomer() {
        if (phone.equals("")) {
            throw new IllegalStateException("Cannot create customer without phone");
        } else if (name.equals("")) {
            throw new IllegalStateException("Cannot create customer without name");
        }
        if (address == null) {
            address = "";
        }
        if (email == null) {
            System.out.println("email er null");
            email = "";
        }
        return new Customer(phone, name, address, email);
    }
    
}
