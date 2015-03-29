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
public class Vegetable {
    
    private int id;
    private String type;
    private int pricePerHead;

    public Vegetable(int id, String type, int pricePerHead) {
        this.id = id;
        this.type = type;
        this.pricePerHead = pricePerHead;
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

    public int getPricePerHead() {
        return pricePerHead;
    }

    public void setPricePerHead(int pricePerHead) {
        this.pricePerHead = pricePerHead;
    }

    @Override
    public String toString() {
        return "Vegetable{" + "id=" + id + ", type=" + type + ", pricePerHead=" + pricePerHead + '}';
    }
    
    
    
}
