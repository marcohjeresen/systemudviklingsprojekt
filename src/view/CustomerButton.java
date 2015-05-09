/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CustomerControl;
import controller.ErrorControl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import model.Customer;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class CustomerButton extends JButton {

    private CustomerControl cc;
    private Listeners listener;
    private Customer customer;
    private CustomerPanel panel;
    private boolean isMassage;
    private ErrorControl errorControl;

    public CustomerButton(final Customer customer, final CustomerPanel panel, final boolean isMassage) {
        this.customer = customer;
        this.panel = panel;
        this.isMassage = isMassage;
        try {
            errorControl = ErrorControl.getInstance();
            cc = CustomerControl.getInstance();
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                errorControl.createErrorPopup("Fejl i forbindelse til databasen.", ex.getLocalizedMessage());
            } catch (SQLException ex1) {
                System.out.println(ex1.getLocalizedMessage());
            }
        }
        listener = Listeners.getList();
        setBackground(Color.white);
        setBorder(null);
        setSize(255, 20);
        setText(customer.getPhone() + "  " + customer.getName());
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                cc.setCustomer(customer, isMassage);
                panel.setVisible(false);
            }
        });
    }

}
