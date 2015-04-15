/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mark
 */
public class Salad {

    private int id;
    private String type;
    private int pricePerHead;
    private Category category;
    private ArrayList<Vegetable> vegetableList;

    public Salad(int id, String type, int pricePerHead, Category category) {
        this.id = id;
        this.type = type;
        this.pricePerHead = pricePerHead;
        this.category = category;
        vegetableList = new ArrayList<>();
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

    public ArrayList<Vegetable> getVegetableList() {
        return vegetableList;
    }

    public void setVegetableList(ArrayList<Vegetable> vegetableList) {
        this.vegetableList = vegetableList;
    }

    @Override
    public String toString() {
        String toString = "Salad{" + "id=" + id + ", type=" + type + ", pricePerHead=" + pricePerHead + ", vegetableList /n";
        for (Vegetable vegetableList1 : vegetableList) {
            toString = toString + vegetableList1.toString() + "/n";
        }
        return toString;
    }

}
