/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Accompaniment;
import model.AccompanimentToBBQ;
import model.BarbercueBuilder;
import model.Grill;
import model.GrillToBBQ;
import model.Meat;
import model.MeatToBBQ;
import model.Salad;
import model.SaladToBBQ;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class BBQControl {

    private static BBQControl bbqc;
    private Listeners listener;
    private BarbercueBuilder builder;

    private BBQControl() {
        listener = Listeners.getList();
        builder = new BarbercueBuilder();
    }

    public static BBQControl getInstance() {
        if (bbqc == null) {
            bbqc = new BBQControl();
        }
        return bbqc;
    }

    public BarbercueBuilder getBuilder() {
        return builder;
    }

    public void clearBBQBuilder() {
        builder = new BarbercueBuilder();
    }

    public void addMeatToList(Meat meat, int kilo) {
        boolean alreadyThere = false;
        for (MeatToBBQ meatToBBQ : builder.getMeatList()) {
            if (meatToBBQ.getMeat().equals(meat)) {
                alreadyThere = true;
            }
        }
        if (!alreadyThere) {
            MeatToBBQ meatToBBQ = new MeatToBBQ(meat, null, kilo);
            builder.addToMeatList(meatToBBQ);
            listener.notifyListeners("added to basket");
        }
    }

    public void removeMeatFromList(MeatToBBQ meatToBBQ) {
        builder.removeFromMeatList(meatToBBQ);
        listener.notifyListeners("added to basket");
    }

    public void addAccompanimentToList(Accompaniment accompaniment) {
        boolean alreadyThere = false;
        for (AccompanimentToBBQ accompanimentToBBQ : builder.getAccompanimentsList()) {
            if (accompanimentToBBQ.getAccompaniment().equals(accompaniment)) {
                alreadyThere = true;
            }
        }
        if (!alreadyThere) {
            AccompanimentToBBQ accompanimentToBBQ = new AccompanimentToBBQ(accompaniment, null);
            builder.addToAccompanimentsList(accompanimentToBBQ);
            listener.notifyListeners("added to basket");
        }
    }

    public void removeAccompanimentFromList(AccompanimentToBBQ accompanimentToBBQ) {
        builder.removeFromAccompanimentList(accompanimentToBBQ);
        listener.notifyListeners("added to basket");
    }

    public void addGrillToList(Grill grill) {
        boolean alreadyThere = false;
        for (GrillToBBQ grillToBBQ : builder.getGrillList()) {
            if (grillToBBQ.getGrill().equals(grill)) {
                alreadyThere = true;
            }
        }
        if (!alreadyThere) {
            GrillToBBQ grillToBBQ = new GrillToBBQ(grill, null);
            builder.addToGrillList(grillToBBQ);
            listener.notifyListeners("added to basket");
        }
    }

    public void removeGrillFromList(GrillToBBQ grillToBBQ) {
        builder.removeFromGrillList(grillToBBQ);
        listener.notifyListeners("added to basket");
    }

    public void addSaladToList(Salad salad) {
        boolean alreadyThere = false;
        for (SaladToBBQ saladToBBQ : builder.getSaladList()) {
            if (saladToBBQ.getSalad().equals(salad)) {
                alreadyThere = true;
            }
        }
        if (!alreadyThere) {
            SaladToBBQ saladToBBQ = new SaladToBBQ(salad, null);
            builder.addToSaladList(saladToBBQ);
            listener.notifyListeners("added to basket");
        }
    }

    public void removeSaladFromList(SaladToBBQ saladToBBQ) {
        builder.removeFromSaladList(saladToBBQ);
        listener.notifyListeners("added to basket");
    }

}
