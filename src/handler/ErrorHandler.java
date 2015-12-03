/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.sql.SQLException;
import util.DateFormatTools;

/**
 *
 * @author Mark
 */
public class ErrorHandler {
    private static ErrorHandler eh;
    private DBConnection dbc;
    private DateFormatTools dft;

    private ErrorHandler() throws ClassNotFoundException, SQLException {
        dbc = DBConnection.getInstance();
        dft = new DateFormatTools();
    }
    
    public static ErrorHandler getInstance() throws ClassNotFoundException, SQLException{
        if (eh == null) {
            eh = new ErrorHandler();
        }
        return eh;
    }
    
    public void saveErrorMessage(String message) throws SQLException{
        System.out.println("jeg var her");
        String sql = "insert into errorMsgs values ('"+dft.getDateNowString()+ "','"+message+"')";
        System.out.println("jeg fik afsluttet dety "  +dft.getDateNowString());
        dbc.execute(sql);
        System.out.println("vidre.");
    }
    
    
    
    
    
    
}
