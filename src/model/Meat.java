/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mark
 */
public class Meat {

    private int id;
    private String type;
    private int pricePerKilo;

    public Meat(int id, String type, int pricePerKilo) {
        this.id = id;
        this.type = type;
        this.pricePerKilo = pricePerKilo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(int pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    @Override
    public String toString() {
        return "Meat{" + "id=" + id + ", type=" + type + ", pricePerKilo=" + pricePerKilo + '}';
    }

}
