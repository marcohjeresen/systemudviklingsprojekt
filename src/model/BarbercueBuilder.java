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
    private ArrayList<GrillToBBQ> grillList;
    private ArrayList<MeatToBBQ> meatList;
    private ArrayList<SaladToBBQ> saladList;
    private ArrayList<AccompanimentToBBQ> accompanimentsList;

    public BarbercueBuilder() {
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

    public BarbercueBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public BarbercueBuilder setFoodReady(String foodReady) {
        this.foodReady = foodReady;
        return this;
    }

    public BarbercueBuilder addToGrillList(GrillToBBQ grillToBBQ) {
        grillList.add(grillToBBQ);
        return this;
    }
    
    public void removeFromGrillList(GrillToBBQ grillToBBQ){
        for (int i = 0; i < grillList.size(); i++) {
            if(grillList.get(i).equals(grillToBBQ)){
                grillList.remove(i);
            }
        }
    }

    public ArrayList<GrillToBBQ> getGrillList() {
        return grillList;
    }

    public BarbercueBuilder addToMeatList(MeatToBBQ meatToBBQ) {
        meatList.add(meatToBBQ);
        return this;
    }
    
    public void removeFromMeatList(MeatToBBQ meatToBBQ){
        for (int i = 0; i < meatList.size(); i++) {
            if(meatList.get(i).equals(meatToBBQ)){
                meatList.remove(i);
            }
        }
    }

    public ArrayList<MeatToBBQ> getMeatList() {
        return meatList;
    }

    public BarbercueBuilder addToSaladList(SaladToBBQ saladToBBQ) {
        saladList.add(saladToBBQ);
        return this;
    }
    
    public void removeFromSaladList(SaladToBBQ saladToBBQ){
        for (int i = 0; i < saladList.size(); i++) {
            if(saladList.get(i).equals(saladToBBQ)){
                saladList.remove(i);
            }
        }
    }

    public ArrayList<SaladToBBQ> getSaladList() {
        return saladList;
    }

    public BarbercueBuilder addToAccompanimentsList(AccompanimentToBBQ accompanimentToBBQ) {
        accompanimentsList.add(accompanimentToBBQ);
        return this;
    }
    
    public void removeFromAccompanimentList(AccompanimentToBBQ accompanimentToBBQ){
        for (int i = 0; i < accompanimentsList.size(); i++) {
            if(accompanimentsList.get(i).equals(accompanimentToBBQ)){
                accompanimentsList.remove(i);
            }
        }
    }

    public ArrayList<AccompanimentToBBQ> getAccompanimentsList() {
        return accompanimentsList;
    }

    public Barbercue createBarbercue() {
        return new Barbercue(id, settings, address, foodReady, grillList, meatList, saladList, accompanimentsList);
    }
}
