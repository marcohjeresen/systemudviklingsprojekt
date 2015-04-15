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
    private String dish;
    private int pricePerHead;
    private Category category;

    public Accompaniment(int id, String type, String dish, int pricePerHead, Category category) {
        this.id = id;
        this.type = type;
        this.dish = dish;
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

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
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

    @Override
    public String toString() {
        return "Accompaniment{" + "id=" + id + ", type=" + type + ", dish=" + dish + ", pricePerHead=" + pricePerHead + '}';
    }

}
