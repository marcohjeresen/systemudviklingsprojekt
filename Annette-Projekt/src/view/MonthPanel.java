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
        iconSizeHeight = 35;
        iconSizeWidth = 35;
        listOfDays = new ArrayList<>();
        cal = Calendar.getInstance();
        jPanel = new JPanel();
        initComponents();
        jLabel_maaned.setText(new SimpleDateFormat("MMMM").format(cal.getTime()));
        drawDays();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_tilbage = new javax.swing.JButton();
        jButton_Frem = new javax.swing.JButton();
        jPanel_calender = new javax.swing.JPanel();
        jLabel_maaned = new javax.swing.JLabel();

        setLayout(null);

        jButton_tilbage.setText("Tilbage");
        jButton_tilbage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tilbageActionPerformed(evt);
            }
        });
        add(jButton_tilbage);
        jButton_tilbage.setBounds(10, 10, 67, 23);

        jButton_Frem.setText("Frem");
        jButton_Frem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FremActionPerformed(evt);
            }
        });
        add(jButton_Frem);
        jButton_Frem.setBounds(326, 11, 57, 23);

        jPanel_calender.setBackground(new java.awt.Color(51, 51, 80));
        jPanel_calender.setLayout(null);
        add(jPanel_calender);
        jPanel_calender.setBounds(10, 50, 380, 240);

        jLabel_maaned.setText("jLabel1");
        add(jLabel_maaned);
        jLabel_maaned.setBounds(150, 10, 120, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_FremActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FremActionPerformed
        cal.roll(cal.MONTH, 1);
        jLabel_maaned.setText(new SimpleDateFormat("MMMM").format(cal.getTime()));
        drawDays();
    }//GEN-LAST:event_jButton_FremActionPerformed

    private void jButton_tilbageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tilbageActionPerformed
        cal.roll(cal.MONTH, -1);
        jLabel_maaned.setText(new SimpleDateFormat("MMMM").format(cal.getTime()));
        drawDays();
    }//GEN-LAST:event_jButton_tilbageActionPerformed

    private void drawDays() {
        int x = 0, y = 0;
        listOfDays.clear();
        jPanel_calender.removeAll();
        jPanel_calender.repaint();
        ArrayList<String> days = new ArrayList<>();
        days.add("2014-10-23");
        days.add("2014-10-25");
        days.add("2014-10-30");
        days.add("2014-11-23");
        
        int daysInMonth = cal.getActualMaximum(cal.DAY_OF_MONTH) +1;

        for (int i = 0; i < daysInMonth; i++) {
            listOfDays.add(new DayPanel(i, cal, jFrame));
        }
        
        for (int i = 0; i < daysInMonth; i++) {
            x += iconSizeWidth;
            if ((i % 10) == 0) {
                x = 0;
                y += iconSizeHeight;
            }
            
            listOfDays.get(i).setBounds(x, y, iconSizeHeight, iconSizeWidth);
            listOfDays.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
            
            boolean erder = false;
            String da = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+i;
            for (int j = 0; j < days.size(); j++) {
                
                if (days.get(j).equals(da)) {
                    listOfDays.get(i).setBackground(Color.RED); 
                    erder = true;
                }
            }
            if (!erder) {
                listOfDays.get(i).setBackground(Color.GRAY); 
            }
            jPanel_calender.add(listOfDays.get(i));
            listOfDays.get(i).setVisible(true);
        }
        jPanel_calender.repaint();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Frem;
    private javax.swing.JButton jButton_tilbage;
    private javax.swing.JLabel jLabel_maaned;
    private javax.swing.JPanel jPanel_calender;
    // End of variables declaration//GEN-END:variables
}
