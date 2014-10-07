/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Henrik J. Nielsen <henrik@hjess.dk>
 */
public class MonthPanel extends javax.swing.JPanel {

    /**
     * Creates new form CalenderPanel
     */
    private int currentDate;
    private int workingDate;
    private static Calendar cal;
    private static JPanel jPanel;
    private int iconSizeHeight;
    private int iconSizeWidth;
    private ArrayList<JPanel> listOfDays;
    private JFrame jFrame;

    public MonthPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        iconSizeHeight = 40;
        iconSizeWidth = 40;
        listOfDays = new ArrayList<>();
        cal = Calendar.getInstance();
        jPanel = new JPanel();
        initComponents();
        jLabel_monht.setText(new SimpleDateFormat("MMMM").format(cal.getTime()).toUpperCase());
        drawDays();
    }
    
    
    //bruges til at tegne og finde evt optaget dage i den måned som vises
     private void drawDays() {
        int x = 0, y = 0;
        listOfDays.clear();
        jPanel_calender.removeAll();
        jPanel_calender.repaint();
        
        // ArrayList - her skal info ind om optaget dage.
        ArrayList<String> days = new ArrayList<>();
        days.add("2014-10-23");
        days.add("2014-10-25");
        days.add("2014-10-30");
        days.add("2014-11-23");

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < daysInMonth; i++) {
            listOfDays.add(new DayPanel(i + 1, cal, jFrame));
        }
        int count = 0;
        for (int i = 0; i < daysInMonth; i++) {
            if (i == 0) {
                Calendar dayCheck = Calendar.getInstance();
                dayCheck.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), i + 1);
                String checkday = new SimpleDateFormat("EEEE").format(dayCheck.getTime());
                switch (checkday) {
                    case "mandag":
                        x = 0;
                        count = 0;
                        break;
                    case "tirsdag":
                        x += iconSizeWidth;
                        count = 1;
                        break;
                    case "onsdag":
                        x = (iconSizeWidth * 2);
                        count = 2;
                        break;
                    case "torsdag":
                        x += (iconSizeWidth * 3);
                        count = 3;
                        break;
                    case "fredag":
                        x += (iconSizeWidth * 4);
                        count = 4;
                        break;
                    case "lørdag":
                        x += (iconSizeWidth * 5);
                        count = 5;
                        break;
                    case "søndag":
                        x += (iconSizeWidth * 6);
                        count = 6;
                        break;
                }
            } else {
                count++;
                x += iconSizeWidth;
            }

            if (count != 0 && (count % 7) == 0) {
                x = 0;
                y += iconSizeHeight;
            }

            listOfDays.get(i).setBounds(x, y, iconSizeHeight, iconSizeWidth);
            listOfDays.get(i).setBorder(BorderFactory.createLineBorder(Color.black));

            boolean erder = false;
            String da = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + i;
            // her tjekker den efter om der er et event på selve dagen inden den tilføjer den til panelet
            for (int j = 0; j < days.size(); j++) {

                if (days.get(j).equals(da)) {
                    listOfDays.get(i).setBackground(new Color(0,66,81));
                    erder = true;
                }
            }
            if (!erder) {
                listOfDays.get(i).setBackground(new Color(0,146,160));
            }
            jPanel_calender.add(listOfDays.get(i));
            listOfDays.get(i).setVisible(true);
        }
        jPanel_calender.repaint();
    }
     
     // bruges til at gå frem og tilbage i månederne
     public void rollMonth(int days){
        cal.roll(cal.MONTH, days);
        jLabel_monht.setText(new SimpleDateFormat("MMMM").format(cal.getTime()).toUpperCase());
        drawDays();
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_back = new javax.swing.JButton();
        jButton_forward = new javax.swing.JButton();
        jPanel_calender = new javax.swing.JPanel();
        jLabel_monht = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jButton_back.setText("Tilbage");
        jButton_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_backActionPerformed(evt);
            }
        });
        add(jButton_back);
        jButton_back.setBounds(10, 10, 80, 23);

        jButton_forward.setText("Frem");
        jButton_forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_forwardActionPerformed(evt);
            }
        });
        add(jButton_forward);
        jButton_forward.setBounds(210, 10, 80, 23);

        jPanel_calender.setBackground(new java.awt.Color(51, 51, 80));
        jPanel_calender.setOpaque(false);
        jPanel_calender.setLayout(null);
        add(jPanel_calender);
        jPanel_calender.setBounds(10, 50, 280, 240);

        jLabel_monht.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_monht.setText("jLabel1");
        add(jLabel_monht);
        jLabel_monht.setBounds(90, 10, 120, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/month background.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 300, 300);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_forwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_forwardActionPerformed
        rollMonth(1);
    }//GEN-LAST:event_jButton_forwardActionPerformed

    private void jButton_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_backActionPerformed
        rollMonth(-1);
    }//GEN-LAST:event_jButton_backActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_back;
    private javax.swing.JButton jButton_forward;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_monht;
    private javax.swing.JPanel jPanel_calender;
    // End of variables declaration//GEN-END:variables
}