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
public class GrillToBBQ {
    private Grill grill;
    private Barbercue barbercue;

    public GrillToBBQ(Grill grill, Barbercue barbercue) {
        this.grill = grill;
        this.barbercue = barbercue;
    }

    public Grill getGrill() {
        return grill;
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }

    public Barbercue getBarbercue() {
        return barbercue;
    }

    public void setBarbercue(Barbercue barbercue) {
        this.barbercue = barbercue;
    }
    
}
