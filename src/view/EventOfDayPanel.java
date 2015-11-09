/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.Event;

/**
 *
 * @author Annette
 */
public class EventOfDayPanel extends javax.swing.JPanel {

    private Event event;

    /**
     * Creates new form EventOfDayPanel
     */
    public EventOfDayPanel(Event event) {
        this.event = event;
        setSize(190, 50);
        initComponents();
        if (event.getMassage() != null) {
            showMassage();
        } else {
            showBarbecue();
        }
    }

    public void showMassage() {
        jLEventType.setText(event.getMassage().getClass().getSimpleName());
        String starttime = event.getMassage().getStartTime();
        jLStartTime.setText(starttime);
        int endTimeValue = Integer.parseInt(starttime.replace(":", ""));
        int duration = 0;
        if (event.getMassage().getType().getDuration() == 60) {
            duration = 100;
        } else {
            duration = event.getMassage().getType().getDuration();
        }

        endTimeValue += duration;
        String checkTime = endTimeValue + "";
        checkTime = checkTime.substring(2, 4);
        int intCheckTime = Integer.parseInt(checkTime);
        if (intCheckTime > 59) {
            int addTime = intCheckTime - 60;
            endTimeValue = endTimeValue - intCheckTime + (addTime + 100);
        }

        String endTime = endTimeValue + "";
        endTime = endTime.substring(0, 2) + ":" + endTime.substring(2, endTime.length());
        jLEndTime.setText(endTime);
        ImageIcon icon = new ImageIcon("src/pictures/lotus.png");
        jLabel1.setIcon(icon);
    }

    public void showBarbecue() {
        jLEventType.setText("Grill");
        String starttime = event.getBarbercue().getFoodReady();
        jLabel4.setText(starttime);
        jLStartTime.setText("");
        jLEndTime.setText("");
        ImageIcon icon = new ImageIcon("src/pictures/grill.png");
        jLabel1.setIcon(icon);
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
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(106, 140, 168));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setToolTipText("");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(null);

        jLEventType.setForeground(new java.awt.Color(255, 255, 255));
        jLEventType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEventType.setText("jLabel1");
        add(jLEventType);
        jLEventType.setBounds(30, 0, 141, 14);

        jLStartTime.setForeground(new java.awt.Color(255, 255, 255));
        jLStartTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLStartTime.setText("jLabel2");
        add(jLStartTime);
        jLStartTime.setBounds(20, 20, 58, 14);

        jLEndTime.setForeground(new java.awt.Color(255, 255, 255));
        jLEndTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLEndTime.setText("jLabel3");
        add(jLEndTime);
        jLEndTime.setBounds(110, 20, 67, 14);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");
        add(jLabel4);
        jLabel4.setBounds(80, 20, 40, 14);
        add(jLabel1);
        jLabel1.setBounds(2, 2, 70, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (event.getMassage() != null) {
            JFrame jf = new JFrame();
            ReceiptPanel rp = new ReceiptPanel("kvittering", jf, event);
            jf.add(rp);
            jf.setVisible(true);
            int height = Toolkit.getDefaultToolkit().getScreenSize().height - jf.getHeight();
            int widht = Toolkit.getDefaultToolkit().getScreenSize().width - jf.getWidth();
            jf.setLocation(widht / 2, height / 2);
            rp.setVisible(true);
        } else {
            JFrame jf = new JFrame();
            EventPanel ep = new EventPanel("bbq edit", jf, event.getDate(), event);
            jf.add(ep);
            jf.setVisible(true);
            int height = Toolkit.getDefaultToolkit().getScreenSize().height - jf.getHeight();
            int widht = Toolkit.getDefaultToolkit().getScreenSize().width - jf.getWidth();
            jf.setLocation(widht / 2, height / 2);
            ep.setVisible(true);
        }

    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLEndTime;
    private javax.swing.JLabel jLEventType;
    private javax.swing.JLabel jLStartTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
