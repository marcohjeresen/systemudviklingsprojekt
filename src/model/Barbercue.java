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
public class Barbercue {

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

    public Barbercue(int id, int settings, String address, String foodReady, int salary, int transport, int totalPrice, String comments, ArrayList<GrillToBBQ> grillList, ArrayList<MeatToBBQ> meatList, ArrayList<SaladToBBQ> saladList, ArrayList<AccompanimentToBBQ> accompanimentsList) {
        this.id = id;
        this.settings = settings;
        this.address = address;
        this.foodReady = foodReady;
        this.salary = salary;
        this.transport = transport;
        this.totalPrice = totalPrice;
        this.comments = comments;
        this.grillList = grillList;
        this.meatList = meatList;
        this.saladList = saladList;
        this.accompanimentsList = accompanimentsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSettings() {
        return settings;
    }

    public void setSettings(int settings) {
        this.settings = settings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFoodReady() {
        return foodReady;
    }

    public void setFoodReady(String foodReady) {
        this.foodReady = foodReady;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getTransport() {
        return transport;
    }

    public void setTransport(int transport) {
        this.transport = transport;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<GrillToBBQ> getGrillList() {
        return grillList;
    }

    public void setGrillList(ArrayList<GrillToBBQ> grillList) {
        this.grillList = grillList;
    }

    public ArrayList<MeatToBBQ> getMeatList() {
        return meatList;
    }

    public void setMeatList(ArrayList<MeatToBBQ> meatList) {
        this.meatList = meatList;
    }

    public ArrayList<SaladToBBQ> getSaladList() {
        return saladList;
    }

    public void setSaladList(ArrayList<SaladToBBQ> saladList) {
        this.saladList = saladList;
    }

    public ArrayList<AccompanimentToBBQ> getAccompanimentsList() {
        return accompanimentsList;
    }

    public void setAccompanimentsList(ArrayList<AccompanimentToBBQ> accompanimentsList) {
        this.accompanimentsList = accompanimentsList;
    }

    
}
