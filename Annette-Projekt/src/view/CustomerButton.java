/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CustomerControl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public CustomerButton(final Customer customer, final CustomerPanel panel) {
        this.customer = customer;
        this.panel = panel;
        cc = CustomerControl.getInstance();
        listener = Listeners.getList();
        setBackground(Color.white);
        setBorder(null);
        setSize(255, 20);
        setText(customer.getPhone() + "  " + customer.getName());
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                cc.setCustomer(customer);
                panel.setVisible(false);
            }
        });
    }

}
