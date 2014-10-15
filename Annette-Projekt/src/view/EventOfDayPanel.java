/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import model.CalendarClass;

/**
 *
 * @author Annette
 */
public class EventOfDayPanel extends javax.swing.JPanel {
    private CalendarClass calC;
    /**
     * Creates new form EventOfDayPanel
     */
    public EventOfDayPanel(CalendarClass calC) {
        this.calC = calC;
        setSize(190, 50);
        initComponents();
        jLEventType.setText(calC.getMassage().getClass().getSimpleName());
        String starttime = calC.getMassage().getStartTime();
        jLStartTime.setText(starttime);
        int endTimeValue = Integer.parseInt(starttime.replace(":", ""));
        int duration = calC.getMassage().getType().getDuration();
        endTimeValue += duration;
        String endTime = endTimeValue+"";
        endTime = endTime.substring(0, 2) + ":" + endTime.substring(2, endTime.length());
        jLEndTime.setText(endTime);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLEventType = new javax.swing.JLabel();
        jLStartTime = new javax.swing.JLabel();
        jLEndTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(92, 143, 154));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLEventType.setForeground(new java.awt.Color(255, 255, 255));
        jLEventType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEventType.setText("jLabel1");

        jLStartTime.setForeground(new java.awt.Color(255, 255, 255));
        jLStartTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLStartTime.setText("jLabel2");

        jLEndTime.setForeground(new java.awt.Color(255, 255, 255));
        jLEndTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEndTime.setText("jLabel3");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLEventType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLEventType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLStartTime)
                    .addComponent(jLEndTime)
                    .addComponent(jLabel4))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println("hej");
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLEndTime;
    private javax.swing.JLabel jLEventType;
    private javax.swing.JLabel jLStartTime;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
