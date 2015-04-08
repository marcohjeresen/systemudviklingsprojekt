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

    public CustomerButton(final Customer customer, final CustomerPanel panel, final boolean isMassage) {
        this.customer = customer;
        this.panel = panel;
        this.isMassage = isMassage;
        try {
            cc = CustomerControl.getInstance();
        } catch (ClassNotFoundException ex) {
            new ErrorPopup("Der kunne ikke oprettet forbindelse til databasen. "
                    + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
            System.out.println(ex.getLocalizedMessage());
        } catch (SQLException ex) {
            new ErrorPopup("Der kunne ikke oprettet forbindelse til databasen. "
                    + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
            System.out.println(ex.getLocalizedMessage());
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
