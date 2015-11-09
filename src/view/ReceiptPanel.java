/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ErrorControl;
import controller.MassageControl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import javax.swing.JFrame;
import model.Event;

/**
 *
 * @author Annette
 */
public class ReceiptPanel extends javax.swing.JPanel implements Printable {

    private JFrame frame;
    private CardLayout cl;
    private Event event;
    private MassageControl mc;
    private ErrorControl errorControl;

    /**
     * Creates new form ReceiptPanel
     */
    public ReceiptPanel(String panel, JFrame frame, Event event) {
        this.event = event;
        this.frame = frame;
        try {
            errorControl = ErrorControl.getInstance();
            mc = MassageControl.getInstance();
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                errorControl.createErrorPopup("Fejl i forbindelse til databasen.", ex.getLocalizedMessage());
            } catch (SQLException ex1) {
                System.out.println(ex1.getLocalizedMessage());
            }
        }
        initComponents();
        cl = (CardLayout) getLayout();
        showPage(panel);
    }

    public void showPage(String panel) {
        switch (panel) {
            case ("kvittering"):
                frame.setSize(new Dimension(300, 480));
                jPanel1.setSize(280, 437);
                jLabel2.setSize(300, 480);
                jScrollPane1.setSize(new Dimension(270, 400));
                jButton1.setLocation(jScrollPane1.getX(), (jPanel1.getHeight()- jButton1.getHeight()-3));
                jButton2.setLocation((jScrollPane1.getX()+jScrollPane1.getWidth())-jButton2.getWidth(), (jPanel1.getHeight()-jButton2.getHeight() - 3));
                jButton3.setSize(23, 23);
                jButton3.setLocation((jPanel1.getWidth()/2)-(jButton3.getWidth()/2), jButton1.getY());
                frame.setLocation(533, 125);
                createReceipt();
                cl.show(this, "kvittering");
                break;
        }
    }
    
    public void createReceipt(){
        jTextArea1.setText(event.toString());
    }
    
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        int printResult;
        if (page > 0) {
            printResult = NO_SUCH_PAGE;
        } else {
            // Find øverste venstre hjørne i det printbare område
            // Forskyd g2d, så (0,0) svarer til øverste venstre hjørne
            Graphics2D g2d = (Graphics2D) g;
            double x0 = pf.getImageableX() + 50;
            double y0 = pf.getImageableY() + 50;
            g2d.translate(x0, y0);
            jTextArea1.printAll(g);
            printResult = PAGE_EXISTS;
        }
        return printResult;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(null);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(6, 5, 190, 220);

        jButton1.setText("Rediger");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(0, 230, 90, 23);

        jButton2.setText("Slet");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(113, 230, 90, 23);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/print_printer.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(70, 230, 53, 29);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/receipt_bkgr.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 270, 410);

        add(jPanel1, "card3");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mc.setEvent(event);
        JFrame jf = new JFrame();
        EventPanel ep = new EventPanel("rediger massage", jf, event.getDate(), null);
        jf.add(ep);
        jf.setVisible(true);
        int height = Toolkit.getDefaultToolkit().getScreenSize().height - jf.getHeight();
        int widht = Toolkit.getDefaultToolkit().getScreenSize().width - jf.getWidth();
        jf.setLocation(widht / 2, height / 2);
        ep.setVisible(true);
        frame.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            mc.deleteMassage(event);
        } catch (SQLException ex) {
            try {
                errorControl.createErrorPopup("Fejl i sletning af aftalen.", ex.getLocalizedMessage());
            } catch (SQLException ex1) {
                System.out.println(ex1.getLocalizedMessage());
            }
        }
        frame.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PrinterJob job = PrinterJob.getPrinterJob();
        Printable doc = this;
        job.setPrintable(doc);
        boolean accept = job.printDialog();
        if (accept) {
            try {
                job.print();
            } catch (PrinterException ex) {
                try {
                errorControl.createErrorPopup("Fejl i at printe", ex.getLocalizedMessage());
            } catch (SQLException ex1) {
                System.out.println(ex1.getLocalizedMessage());
            }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
