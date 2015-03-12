/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MassageControl;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.MassageType;

/**
 *
 * @author Mark
 */
public class EditMassage extends javax.swing.JPanel {

    private JFrame jFrame;
    private EditMassagePanel editMP;
    private ArrayList<MassageType> typeList;
    private ArrayList<EditMassagePanel> panelList;

    /**
     * Creates new form EditMassage
     */
    public EditMassage(JFrame jframe, ArrayList<MassageType> typeList) {

        this.jFrame = jframe;
        this.typeList = typeList;
        panelList = new ArrayList<>();
        initComponents();
        int count = 0;
        for (MassageType mT : typeList) {
            editMP = new EditMassagePanel(mT.getId(), mT.getType(), mT.getPrice(), mT.getDuration());
            jPanel1.add(editMP);
            editMP.setLocation(5, (editMP.getHeight() + 5) * count);
            count++;
            panelList.add(editMP);
        }
        jFrame.setSize(editMP.getWidth()+15, ((editMP.getHeight() + 5) * (count+1)) + jButton1.getHeight());
        jPanel1.setSize(editMP.getWidth(), (editMP.getHeight() + 5) * count);
        jButton1.setLocation(5, (editMP.getHeight() + 5) * count);
        jFrame.revalidate();
        jFrame.repaint();
        jFrame.setLocation((1366 - jFrame.getWidth())/2, (730 - jFrame.getHeight())/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setLayout(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 790, 290);

        jButton1.setText("Luk");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(0, 350, 140, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jFrame.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}