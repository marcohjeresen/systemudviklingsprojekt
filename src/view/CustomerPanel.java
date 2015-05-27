/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import model.Customer;

/**
 *
 * @author Annette
 */
public class CustomerPanel extends javax.swing.JPanel {

    private ArrayList<Customer> customers;
    private boolean isMassage;

    /**
     * Creates new form CustomerPanel
     */
    public CustomerPanel(ArrayList<Customer> customers, boolean isMassage, int xSize) {
        this.customers = customers;
        this.isMassage = isMassage;
        initComponents();
        int y = 0;
        for (Customer customer : customers) {
            CustomerButton button = new CustomerButton(customer, this, isMassage);
            button.setLocation(0, y);
            button.setSize(xSize, button.getHeight());
            button.setVisible(true);
            add(button);
            y += button.getHeight();
        }
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
