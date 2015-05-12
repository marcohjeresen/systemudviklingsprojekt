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
    private ArrayList<VegetableToSalad> vegetableList;

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
        if(!vegetableList.isEmpty()){
            if (type.equals("Salatbar")) {
                pricePerHead = 8;
            }
            for (VegetableToSalad vegetableToSalad : vegetableList) {
                pricePerHead += vegetableToSalad.getVegetable().getPricePerHead();
            }
        }
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

    public ArrayList<VegetableToSalad> getVegetableList() {
        return vegetableList;
    }

    public void addToVegetableList(VegetableToSalad vegetableToSalad) {
        this.vegetableList.add(vegetableToSalad);
    }
    
    public void addVegetableListToSalad(ArrayList<Vegetable> vegetables){
        for (Vegetable vegetable : vegetables) {
            VegetableToSalad toSalad = new VegetableToSalad(0,vegetable, this);
            vegetableList.add(toSalad);
        }
    }
    
    public void removeFromVegetableList(Vegetable vegetable){
        for (int i = 0; i < vegetableList.size(); i++) {
            if (vegetableList.get(i).getVegetable().getType().equals(vegetable.getType())) {
                vegetableList.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        String toString = "Salad{" + "id=" + id + ", type=" + type + ", pricePerHead=" + pricePerHead + ", vegetableList /n";
        for (VegetableToSalad vegetableList1 : vegetableList) {
            toString = toString + vegetableList1.toString() + "/n";
        }
        return toString;
    }

}
