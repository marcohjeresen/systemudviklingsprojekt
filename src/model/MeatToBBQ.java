/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Annette
 */
public class MeatToBBQ {
    private Meat meat;
    private Barbercue barbercue;
    private int kilo;

    public MeatToBBQ(Meat meat, Barbercue barbercue, int kilo) {
        this.meat = meat;
        this.barbercue = barbercue;
        this.kilo = kilo;
    }

    public Meat getMeat() {
        return meat;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public Barbercue getBarbercue() {
        return barbercue;
    }

    public void setBarbercue(Barbercue barbercue) {
        this.barbercue = barbercue;
    }

    public int getKilo() {
        return kilo;
    }

    public void setKilo(int kilo) {
        this.kilo = kilo;
    }
    
    
}
