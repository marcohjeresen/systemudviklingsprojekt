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
public class VegetableToSalad {
    private Vegetable vegetable;
    private Salad salad;

    public VegetableToSalad(Vegetable vegetable, Salad salad) {
        this.vegetable = vegetable;
        this.salad = salad;
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }

    public Salad getSalad() {
        return salad;
    }

    public void setSalad(Salad salad) {
        this.salad = salad;
    }
    
    
    
}
