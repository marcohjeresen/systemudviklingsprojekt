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
    private ArrayList<GrillToBBQ> grillList;
    private ArrayList<MeatToBBQ> meatList;
    private ArrayList<SaladToBBQ> saladList;
    private ArrayList<AccompanimentToBBQ> accompanimentsList;

    public Barbercue(int id, int settings, String address, String foodReady, ArrayList<GrillToBBQ> grillList, ArrayList<MeatToBBQ> meatList, ArrayList<SaladToBBQ> saladList, ArrayList<AccompanimentToBBQ> accompanimentsList) {
        this.id = id;
        this.settings = settings;
        this.address = address;
        this.foodReady = foodReady;
        this.grillList = grillList;
        this.meatList = meatList;
        this.saladList = saladList;
        this.accompanimentsList = accompanimentsList;
    }

    public int getId() {
        return id;
    }

    public int getSettings() {
        return settings;
    }

    public String getAddress() {
        return address;
    }

    public String getFoodReady() {
        return foodReady;
    }

    public ArrayList<GrillToBBQ> getGrillList() {
        return grillList;
    }

    public ArrayList<MeatToBBQ> getMeatList() {
        return meatList;
    }

    public ArrayList<SaladToBBQ> getSaladList() {
        return saladList;
    }

    public ArrayList<AccompanimentToBBQ> getAccompanimentsList() {
        return accompanimentsList;
    }

    

}
