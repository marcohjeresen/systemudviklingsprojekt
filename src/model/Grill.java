/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mark
 */
public class Grill {

    private int id;
    private String type;
    private int coalPrice;

    public Grill(int id, String type, int coalPrice) {
        this.id = id;
        this.type = type;
        this.coalPrice = coalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCoalPrice() {
        return coalPrice;
    }

    public void setCoalPrice(int coalPrice) {
        this.coalPrice = coalPrice;
    }

    @Override
    public String toString() {
        return "Grill{" + "id=" + id + ", type=" + type + ", coalPrice=" + coalPrice + '}';
    }

}
