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
public class Accompaniment {

    private int id;
    private String type;
    private int pricePerHead;
    private Category category;

    public Accompaniment(int id, String type, int pricePerHead, Category category) {
        this.id = id;
        this.type = type;
        this.pricePerHead = pricePerHead;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString(int settings) {
        int total = pricePerHead * settings;
        return type + "\nPris per kuvert: " + pricePerHead + "kr.\nTotalPris: "+total+"kr.";
    }

}
