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
public class AccompanimentToBBQ {
    private Accompaniment accompaniment;
    private Barbercue barbercue;

    public AccompanimentToBBQ(Accompaniment accompaniment, Barbercue barbercue) {
        this.accompaniment = accompaniment;
        this.barbercue = barbercue;
    }

    public Accompaniment getAccompaniment() {
        return accompaniment;
    }

    public void setAccompaniment(Accompaniment accompaniment) {
        this.accompaniment = accompaniment;
    }

    public Barbercue getBarbercue() {
        return barbercue;
    }

    public void setBarbercue(Barbercue barbercue) {
        this.barbercue = barbercue;
    }
    
    
}
