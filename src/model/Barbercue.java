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

    public Barbercue(int id, int settings, String address, String foodReady) {
        this.id = id;
        this.settings = settings;
        this.address = address;
        this.foodReady = foodReady;
        this.grillList = new ArrayList<>();
        this.meatList = new ArrayList<>();
        this.saladList = new ArrayList<>();
        this.accompanimentsList = new ArrayList<>();
    }

}
