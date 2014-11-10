/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Calendar_Ct;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mark Hjeresen
 */
public class MonthPanel extends javax.swing.JPanel {

    /**
     * Creates new form CalenderPanel
     */
    private static Calendar cal;
    private static JPanel jPanel;
    private int iconSizeHeight;
    private int iconSizeWidth;
    private ArrayList<DayPanel> listOfDays;
    private Calendar_Ct cc;
    private JFrame jFrame;
    private ArrayList<String> days;
    private int x;
    private int y;
    private int count;

    public MonthPanel(JFrame jFrame) {
        try {
            cc = Calendar_Ct.getInstance();
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

    /**
     * Method, used to draw and identify any busy days in the month.
     *
     * Draws all JPanel with dates.
     */
    private void drawDays() {
        x = 0;
        y = 0;
        count = 0;
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        listOfDays.clear();
        jPanel_calender.removeAll();
        jPanel_calender.repaint();
        try {
            // ArrayList - her skal info ind om optaget dage.
            days = cc.getDates();
            
        } catch (SQLException ex) {
            new ErrorPopup("Der kunne ikke hentes datoer fra databasen. "
                    + "<br/>Programmet kan godt bruges, men anbefales ikke.<br/> Kontakt Annette, "
                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
            System.out.println(ex.getLocalizedMessage()+cc.getCh().getSql());
        }

        //her kører vi alle dagene på selve måneden igennem og tjekker for event og om dagen er i dag
        for (int i = 0; i < daysInMonth; i++) {
            listOfDays.add(new DayPanel(i + 1, cal, jFrame));

            //Hvis i er 0  er det den første dag i måneden og vi skal så finde ud af hvad plads den skal have
            if (i == 0) {
                Calendar dayCheck = Calendar.getInstance();
                dayCheck.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), i + 1);
                String checkday = new SimpleDateFormat("EEEE").format(dayCheck.getTime());
                // her finder vi pladsen den første dag i måneden skal være på
                checkWeekDay(checkday);
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

            int day = i + 1;
            String month = "";
            String daysOnPanel = "";
            if ((cal.get(Calendar.MONTH) + 1) <= 9) {
                month = "0" + (cal.get(Calendar.MONTH) + 1);
            } else {
                month = "" + (cal.get(Calendar.MONTH) + 1);
            }
            if (day <= 9) {
                daysOnPanel = "0" + day;
            } else {
                daysOnPanel = "" + day;
            }
            String dayOnPanel = cal.get(Calendar.YEAR) + "-" + month + "-" + daysOnPanel;
            

            String todayString = toDayStringToCheck();
            boolean erder = false;
            // her tjekker den efter om der er et event på selve dagen inden den tilføjer den til panelet hvis der er for den en mørkere baggrund
            for (int j = 0; j < days.size(); j++) {
                if (days.get(j).equals(dayOnPanel)) {
                    System.out.println(dayOnPanel);
                    System.out.println(days.get(j));
                    listOfDays.get(i).setBackground(new Color(69, 96, 123));
                    erder = true;
                }
            }
            // her laver den dagen om så vis dagen er i dag bliver skriften rød
            if (dayOnPanel.equals(todayString)) {
                JLabel jl = (JLabel) listOfDays.get(i).getLabel();
                if (jl != null) {
                    jl.setForeground(Color.red);
                } else {
                    System.out.println(dayOnPanel + "\t " + listOfDays.get(i) + "\t has no Label!!!");
                }
            }
            //hvis der ikke er et event på dagen så for den en lyser baggrund
            if (!erder) {
                listOfDays.get(i).setBackground(new Color(106, 140, 168));
            }
            jPanel_calender.add(listOfDays.get(i));
            listOfDays.get(i).setVisible(true);
        }
        jPanel_calender.repaint();
    }

    /**
     * Method, Tjekker hvad dag dato'en er. er sætter x og count til den værdi
     * der passer på den dag.
     *
     * @param checkDay
     */
    public void checkWeekDay(String checkDay) {
        String checkday = checkDay;
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
    }

    /**
     * Method, laver en String med dagen i dag så vi kan se om et JPanel passer
     * på den
     *
     * @return en String med dagen i dag.
     */
    public String toDayStringToCheck() {
        Calendar today = Calendar.getInstance();
        int todayMo = today.get(Calendar.MONTH) + 1;
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        String todaySM = "";
        String todaySD = "";

        if (todayMo <= 9) {
            todaySM = "0" + todayMo;
        } else {
            todaySM = todayMo + "";
        }

        if (todayDay <= 9) {
            todaySD = "0" + todayDay;
        } else {
            todaySD = todayDay + "";
        }
        String todayString = today.get(Calendar.YEAR) + "-" + todaySM + "-" + todaySD;
        return todayString;
    }

    // bruges til at gå frem og tilbage i månederne
    /**
     * Method, Bruges til at gå en måned frem eller tilbage.
     * og kalder drawDays() efterfølgene så den kan tegne Panelerne forfra
     *
     * @param days
     */
    public void rollMonth(int days) {
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/month_bkgr.png"))); // NOI18N
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
