/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author markh_000
 */
public class Calendar_Ct {
private static Calendar_Ct cc;

    private Calendar_Ct() {
        
    }
    
    public static Calendar_Ct getInstance(){
        if(cc == null){
            cc = new Calendar_Ct();
        }
        return cc;
    }

}
