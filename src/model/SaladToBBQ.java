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
public class SaladToBBQ {
    private Salad salad;
    private Barbercue barbercue;

    public SaladToBBQ(Salad salad, Barbercue barbercue) {
        this.salad = salad;
        this.barbercue = barbercue;
    }

    public Salad getSalad() {
        return salad;
    }

    public void setSalad(Salad salad) {
        this.salad = salad;
    }

    public Barbercue getBarbercue() {
        return barbercue;
    }

    public void setBarbercue(Barbercue barbercue) {
        this.barbercue = barbercue;
    }
    
    
}
