/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.BarbercueBuilder;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class BBQControl {
    private static BBQControl bbqc;
    private Listeners listener;
    private BarbercueBuilder builder;
    
    private BBQControl(){
        listener = Listeners.getList();
        builder = new BarbercueBuilder();
    }
    
    public static BBQControl getInstance(){
        if(bbqc == null){
            bbqc = new BBQControl();
        }
        return bbqc;
    }

    public BarbercueBuilder getBuilder() {
        return builder;
    }
    
    public void clearBBQBuilder(){
        builder = new BarbercueBuilder();
    }
}
