/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.SaladHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Salad;
import model.Vegetable;
import model.VegetableToSalad;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class SaladControl {

    private SaladHandler saladHandler;
    private static SaladControl sc;
    private Listeners listeners;
    private Salad salad;

    private SaladControl() throws ClassNotFoundException, SQLException {
        salad = null;
        saladHandler = SaladHandler.getInstance();
        listeners = Listeners.getList();
    }

    public static SaladControl getInstance() throws ClassNotFoundException, SQLException {
        if (sc == null) {
            sc = new SaladControl();
        }
        return sc;
    }

    public ArrayList<Salad> getSaladList() throws SQLException {
        return saladHandler.getSaladList();
    }

    public ArrayList<Vegetable> getVegetableList() throws SQLException {
        return saladHandler.getVegetableList();
    }

    public ArrayList<String> getStandardVegetableList() throws SQLException {
        return saladHandler.getStandardVegetableList();
    }

    public void createSaladBar(Salad salad) {
        this.salad = salad;
        listeners.notifyListeners("salad bar updated");
    }

    public Salad getSaladBar() {
        return salad;
    }

    public void addToSaladBar(Vegetable vegetable) {
        VegetableToSalad toSalad = new VegetableToSalad(0, vegetable, salad);
        salad.addToVegetableList(toSalad);
        listeners.notifyListeners("salad bar updated");
    }

    public void removeFromSaladBar(Vegetable vegetable) {
        salad.removeFromVegetableList(vegetable);
        listeners.notifyListeners("salad bar updated");
    }
}
