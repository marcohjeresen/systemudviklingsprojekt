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
    private ArrayList<Grill> grillList;
    private ArrayList<Meat> meatList;
    private ArrayList<Salad> saladList;
    private ArrayList<Accompaniment> accompanimentsList;

    public Barbercue(int id, int settings) {
        this.id = id;
        this.settings = settings;
        this.grillList = new ArrayList<>();
        this.meatList = new ArrayList<>();
        this.saladList = new ArrayList<>();
        this.accompanimentsList = new ArrayList<>();
    }
    
    
    
}
