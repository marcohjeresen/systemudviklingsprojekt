/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;


public class BarbercueBuilder {
    private int id;
    private int settings;
    private String address;
    private String foodReady;
    private int salary;
    private int transport;
    private int totalPrice;
    private String comments;
    private ArrayList<GrillToBBQ> grillList;
    private ArrayList<MeatToBBQ> meatList;
    private ArrayList<SaladToBBQ> saladList;
    private ArrayList<AccompanimentToBBQ> accompanimentsList;

    public BarbercueBuilder() {
        /*salary = 0;
        transport = 0;
        totalPrice = 0;
        comments = "";
        foodReady = "";
        address = "";
        settings = 0;
        id = 0;
        */
        grillList = new ArrayList<>();
        meatList = new ArrayList<>();
        saladList = new ArrayList<>();
        accompanimentsList = new ArrayList<>();
    }

    public BarbercueBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public BarbercueBuilder setSettings(int settings) {
        this.settings = settings;
        return this;
    }

    public int getId() {
        return id;
    }


    public String getAddress() {
        return address;
    }

    public BarbercueBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFoodReady() {
        return foodReady;
    }

    public BarbercueBuilder setFoodReady(String foodReady) {
        this.foodReady = foodReady;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public BarbercueBuilder setComments(String comments) {
        this.comments = comments;
        return this;
    }
    
    

    public int getSettings() {
        return settings;
    }


    public int getSalary() {
        return salary;
    }

    public BarbercueBuilder setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public int getTransport() {
        return transport;
    }

    public BarbercueBuilder setTransport(int transport) {
        this.transport = 0;
        if (transport >= 45) {
            this.transport = 500;
        }else{
            this.transport = 0;
        }
        return this;
    }
    
    public BarbercueBuilder addToGrillList(GrillToBBQ grillToBBQ) {
        grillList.add(grillToBBQ);
        return this;
    }
    
    public BarbercueBuilder removeFromGrillList(GrillToBBQ grillToBBQ){
        for (int i = 0; i < grillList.size(); i++) {
            if(grillList.get(i).equals(grillToBBQ)){
                grillList.remove(i);
            }
        }
        return this;
    }

    public ArrayList<GrillToBBQ> getGrillList() {
        return grillList;
    }

    public BarbercueBuilder addToMeatList(MeatToBBQ meatToBBQ) {
        meatList.add(meatToBBQ);
        return this;
    }
    
    public BarbercueBuilder removeFromMeatList(MeatToBBQ meatToBBQ){
        for (int i = 0; i < meatList.size(); i++) {
            if(meatList.get(i).equals(meatToBBQ)){
                meatList.remove(i);
            }
        }
        return this;
    }

    public ArrayList<MeatToBBQ> getMeatList() {
        return meatList;
    }

    public BarbercueBuilder addToSaladList(SaladToBBQ saladToBBQ) {
        saladList.add(saladToBBQ);
        return this;
    }
    
    public BarbercueBuilder removeFromSaladList(SaladToBBQ saladToBBQ){
        for (int i = 0; i < saladList.size(); i++) {
            if(saladList.get(i).equals(saladToBBQ)){
                saladList.remove(i);
            }
        }
        return this;
    }

    public ArrayList<SaladToBBQ> getSaladList() {
        return saladList;
    }

    public BarbercueBuilder addToAccompanimentsList(AccompanimentToBBQ accompanimentToBBQ) {
        accompanimentsList.add(accompanimentToBBQ);
        return this;
    }
    
    public BarbercueBuilder removeFromAccompanimentList(AccompanimentToBBQ accompanimentToBBQ){
        for (int i = 0; i < accompanimentsList.size(); i++) {
            if(accompanimentsList.get(i).equals(accompanimentToBBQ)){
                accompanimentsList.remove(i);
            }
        }
        return this;
    }

    public ArrayList<AccompanimentToBBQ> getAccompanimentsList() {
        return accompanimentsList;
    }

    public int getTotalPrice() {
        totalPrice = 0;
        for (MeatToBBQ meat : meatList) {
            totalPrice += meat.getTotalPrice();
        }
        
        for (AccompanimentToBBQ accompaniment : accompanimentsList) {
            totalPrice += accompaniment.getTotalPrice(settings);
        }
        
        for (SaladToBBQ salad : saladList) {
            totalPrice += salad.getTotalPrice(settings);
        }
        
        for (GrillToBBQ grill : grillList) {
            totalPrice += grill.getGrill().getCoalPrice();
        }
        totalPrice += salary + transport;
        return totalPrice;
    }

    public BarbercueBuilder setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
    
    

    public Barbercue createBarbercue() {
        return new Barbercue(id, settings, address, foodReady, salary, transport, totalPrice, comments, grillList, meatList, saladList, accompanimentsList);
    }
    
    
}
