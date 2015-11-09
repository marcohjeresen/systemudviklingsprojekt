/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import handler.ErrorHandler;
import java.sql.SQLException;
import util.ErrorModel;
import view.ErrorPopup;

/**
 *
 * @author Mark
 */
public class ErrorControl {
    private static ErrorControl ec;
    private ErrorHandler eh;
    private ErrorModel errorModel;


    private ErrorControl() throws ClassNotFoundException, SQLException {
        eh = ErrorHandler.getInstance();
        errorModel = ErrorModel.getInstance();
    }
    
    public static ErrorControl getInstance() throws ClassNotFoundException, SQLException{
        if (ec == null) {
            ec = new ErrorControl();
        }
        return ec;
    }
    
    public void createErrorPopup(String error, String completeMessage) throws SQLException{
        eh.saveErrorMessage( errorModel.getMessage(error) + " " + completeMessage);
        System.out.println(errorModel.getMessage(error) + "      svgegeg    " + completeMessage);
        
        ErrorPopup errorPopup = new ErrorPopup(errorModel.getMessage(error + "   " + completeMessage));
        
    }
    
    
    
    
}
