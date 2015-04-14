/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CustomerControl;
import controller.MassageControl;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import model.Event;
import model.Customer;
import model.CustomerBuilder;
import model.Massage;
import model.MassageBuilder;
import model.MassageType;
import util.DateFormatTools;
import util.Listeners;

/**
 *
 * @author Annette
 */
public class EventPanel extends javax.swing.JPanel implements ActionListener {

    private CardLayout cl;
    private JFrame jFrame;
    private MassageBuilder massage;
    private CustomerControl cc;
    private Customer customer;
    private Event event;
    private MassageControl mc;
    private Calendar cal;
    private DateFormatTools dateFormatTools;
    private ArrayList<MassageType> masTypeList;
    private boolean phoneSearch;
    private Listeners listener;
    private CustomerBuilder cb;
    private boolean editing;
    private Calendar startTime;
    private final Border redLineBorder;
    private boolean isMassage;

    /**
     * Creates new form EventPanel
     */
    public EventPanel(String panel, JFrame jFrame, Calendar calendar) {
        redLineBorder = BorderFactory.createLineBorder(Color.red);
        editing = false;
        startTime = Calendar.getInstance();
        cb = new CustomerBuilder();
        this.jFrame = jFrame;
        this.cal = calendar;
        phoneSearch = true;
        listener = Listeners.getList();
        try {
            cc = CustomerControl.getInstance();
            mc = MassageControl.getInstance();
        } catch (ClassNotFoundException | SQLException ex) {
            new ErrorPopup("Der kunne ikke oprettet forbindelse til databasen. "
                    + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
            System.out.println(ex.getLocalizedMessage());
        }
        dateFormatTools = new DateFormatTools();
        initComponents();
        listener.addListener(this);
        cl = (CardLayout) getLayout();
        cl.addLayoutComponent(choosePanel, "vælg");
        cl.addLayoutComponent(massagePanel, "massage");
        cl.addLayoutComponent(bbqPanel, "bbq");
        showPage(panel);
    }

    public void setJFrame(int xSize, int ySize, String showPanel, String title) {
        int height = 0;
        int widht = 0;
        jFrame.setLocation(550, 150);
        jFrame.setSize(new Dimension(xSize, ySize));
        jFrame.setTitle(title);
        height = Toolkit.getDefaultToolkit().getScreenSize().height - jFrame.getHeight();
        widht = Toolkit.getDefaultToolkit().getScreenSize().width - jFrame.getWidth();
        jFrame.setLocation(widht / 2, height / 2);
        cl.show(this, showPanel);
    }

    //bruges til at navigere i vores CardLayout
    public void showPage(String panel) {
        int height = 0;
        int widht = 0;
        switch (panel) {
            case ("vælg"):
                setJFrame(242, 150, "vælg", dateFormatTools.getDayLetters(cal));
                break;
            case ("massage"):
                massage = new MassageBuilder();
                customer = null;
                try {
                    masTypeList = mc.getMTypeList();
                } catch (SQLException ex) {
                    new ErrorPopup("Der kunne ikke hentes massagetyper fra databasen. "
                            + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                            + "for få dette fixet.<br/>(Husk at have maden klar;)!)!");
                    System.out.println(ex.getLocalizedMessage() + "\n" + mc.getMh().getSql() + "\n" + mc.getMh().getSqlCal());
                }
                setJFrame(300, 370, "massage", dateFormatTools.getDayLetters(cal));
                fillComboStartTime();
                break;
            case ("rediger massage"):
                editing = true;
                startTime = mc.getEvent().getDate();
                try {
                    masTypeList = mc.getMTypeList();
                } catch (SQLException ex) {
                    new ErrorPopup("Der kunne ikke hentes massagetyper fra databasen. "
                            + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                            + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
                    System.out.println(ex.getLocalizedMessage() + "\n" + mc.getMh().getSql() + "\n" + mc.getMh().getSqlCal());
                }
                event = mc.getEvent();
                setJFrame(300, 370, "massage", dateFormatTools.getDayLetters(cal));
                fillComboStartTime();
                fillMassage();
                break;
            case ("bbq"):
                customer = null;
                setJFrame(668, 500, "bbq", dateFormatTools.getDayLetters(cal));
                break;
        }
        jFrame.revalidate();
        jFrame.repaint();
    }

    public void fillMassage() {
        jTName.setText(event.getCustomer().getName());
        jTPhone.setText(event.getCustomer().getPhone());
        jTComment.setText(event.getMassage().getComment());
        if (event.getMassage().getType().getType().equals("Halv krops massage")) {
            jCHalf.setSelected(true);
        } else {
            jCWhole.setSelected(true);
        }
        jCStartTime.setSelectedItem(event.getMassage().getStartTime());
    }

    public void fillComboStartTime() {
        jCStartTime.removeAllItems();
        int time = 11;
        int minut = 0;
        String tid = "";

        for (int i = 0; i < 12; i++) {
            if (minut == 0) {
                tid = time + ":" + minut + "0";
                minut = 30;
            } else {
                tid = time + ":" + minut;
                minut = 0;
            }
            jCStartTime.addItem(tid);
            if (minut == 0) {
                time++;
            }
        }
    }

    public void findCustomer(String search) {
        ArrayList<Customer> cus = new ArrayList<>();
        try {
            if (search.equals("masPhone")) {
                phoneSearch = true;
                cus = cc.getSpecificCustomer(jTPhone.getText(), phoneSearch);
            } else if (search.equals("masName")) {
                phoneSearch = false;
                cus = cc.getSpecificCustomer(jTName.getText(), phoneSearch);
            } else if (search.equals("bbqPhone")) {
                phoneSearch = true;
                cus = cc.getSpecificCustomer(jTBBQPhone.getText(), phoneSearch);
            } else if (search.equals("bbqName")) {
                phoneSearch = false;
                cus = cc.getSpecificCustomer(jTBBQName.getText(), phoneSearch);
            }
        } catch (SQLException ex) {
            new ErrorPopup("Der kunne ikke hentes kunder fra databasen. "
                    + "<br/>Programmet kan godt bruges, men anbefales ikke.<br/> Kontakt Annette, "
                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
            System.out.println(ex.getLocalizedMessage() + cc.getCh().getSql());
        }
        if (search.equals("masPhone") || search.equals("masName")) {
            if (!cus.isEmpty() && cus.size() <= 1) {
                jTPhone.setText(cus.get(0).getPhone());
                jTName.setText(cus.get(0).getName());
                if (editing) {
                    event.setCustomer(cus.get(0));
                } else {
                    customer = cus.get(0);
                }
            } else if (!cus.isEmpty() && cus.size() >= 2) {
                isMassage = true;
                int xSize = 256;
                CustomerPanel cp = new CustomerPanel(cus, isMassage, xSize);
                cp.setSize(260, 20);
                cp.setVisible(true);
                cp.setLocation(10, 80);
                cp.setSize(xSize, cus.size() * cp.getHeight());
                massagePanel.add(cp);
                massagePanel.setComponentZOrder(cp, 0);
            }
        } else {
            if (!cus.isEmpty() && cus.size() <= 1) {
                jTBBQPhone.setText(cus.get(0).getPhone());
                jTBBQName.setText(cus.get(0).getName());
                jTBBQCusAddress.setText(cus.get(0).getHomeAddress());
                if (editing) {
                    event.setCustomer(cus.get(0));
                } else {
                    customer = cus.get(0);
                }
            } else if (!cus.isEmpty() && cus.size() >= 2) {
                isMassage = false;
                int xSize = 180;
                CustomerPanel cp = new CustomerPanel(cus, isMassage, xSize);
                cp.setSize(260, 20);
                cp.setVisible(true);
                cp.setLocation(10, 100);
                cp.setSize(xSize, cus.size() * cp.getHeight());
                bbqPanel.add(cp);
                bbqPanel.setComponentZOrder(cp, 0);
            }
        }
        revalidate();
        repaint();
    }

    public void saveCustomer(boolean isMasssage) {
        customer = null;
        cb = new CustomerBuilder();
        if (isMasssage) {
            if (jTPhone.getText().length() != 0 && jTName.getText().length() != 0) {
                if (customer == null) {
                    cb.setPhone(jTPhone.getText());
                    cb.setName(jTName.getText());
                    customer = cb.createCustomer();
                    try {
                        cc.saveCustomer(customer);
                    } catch (SQLException ex) {
                        new ErrorPopup("Kunden kunne ikke gemmes i databasen. "
                                + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                                + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
                        System.out.println(ex.getLocalizedMessage() + "\n" + cc.getCh().getSql());
                    }
                }
            }
        } else {
            if (!jTBBQName.getText().isEmpty() && !jTBBQPhone.getText().isEmpty() && !jTBBQCusAddress.getText().isEmpty()) {
                if (customer == null) {
                    cb.setPhone(jTBBQPhone.getText());
                    cb.setName(jTBBQName.getText());
                    cb.setHomeAddress(jTBBQCusAddress.getText());
                    customer = cb.createCustomer();
                    try {
                        cc.saveCustomer(customer);
                    } catch (SQLException ex) {
                        if (ex.toString().length() == 120) {
                            new ErrorPopup("Kunden kunne ikke gemmes i databasen. "
                                    + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
                        }
                        System.out.println(ex.toString().length());
                        new ErrorPopup("Kunden kunne ikke gemmes i databasen. "
                                + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                                + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
                        System.out.println(ex.getLocalizedMessage() + "\n" + cc.getCh().getSql());
                    }
                }
            }
        }
    }

    public void alterCustomer(boolean isMassage) {
        String cusPhoneNumber = "";
        if (isMassage) {
            cusPhoneNumber = customer.getPhone();
            customer.setPhone(jTPhone.getText());
            customer.setName(jTName.getText());
        } else {
            cusPhoneNumber = customer.getPhone();
            customer.setPhone(jTBBQPhone.getText());
            customer.setName(jTBBQName.getText());
            customer.setHomeAddress(jTBBQCusAddress.getText());
        }
        try {
            cc.alterCustomer(customer, cusPhoneNumber, isMassage);
        } catch (SQLException exception) {
            new ErrorPopup("Kunden kunne ikke ændres i databasen. "
                    + "<br/>Programmet kan ikke bruges.<br/> Kontakt Annette, "
                    + "for få dette fixet<br/>(Husk at have maden klar;)!)!");
            System.out.println(exception.getLocalizedMessage() + "\n" + cc.getCh().getSql());
        }

    }

    public MassageType chooseMasType(String type) {
        MassageType mt = null;
        for (MassageType massageType : masTypeList) {
            if (massageType.getType().equals(type)) {
                mt = massageType;
            }
        }
        return mt;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel8 = new javax.swing.JLabel();
        choosePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBMassage = new javax.swing.JButton();
        jBGrill = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        massagePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jCHalf = new javax.swing.JCheckBox();
        jCWhole = new javax.swing.JCheckBox();
        jTName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTComment = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jCStartTime = new javax.swing.JComboBox();
        jBCreateMassage = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        bbqPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTBBQPhone = new javax.swing.JTextField();
        jTBBQCusAddress = new javax.swing.JTextField();
        jTBBQName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTBBQKm = new javax.swing.JTextField();
        jTBBQDishes = new javax.swing.JTextField();
        jTBBQEventAddress = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        setLayout(new java.awt.CardLayout());

        choosePanel.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Opret aftale");
        choosePanel.add(jLabel1);
        jLabel1.setBounds(43, 22, 146, 14);

        jBMassage.setText("Massage");
        jBMassage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMassageActionPerformed(evt);
            }
        });
        choosePanel.add(jBMassage);
        jBMassage.setBounds(43, 42, 146, 23);

        jBGrill.setText("Grill");
        jBGrill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGrillActionPerformed(evt);
            }
        });
        choosePanel.add(jBGrill);
        jBGrill.setBounds(43, 71, 146, 23);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/event_bkgr.png"))); // NOI18N
        choosePanel.add(jLabel11);
        jLabel11.setBounds(0, 0, 242, 140);

        add(choosePanel, "card2");

        massagePanel.setLayout(null);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Opret massage");
        massagePanel.add(jLabel2);
        jLabel2.setBounds(10, 0, 266, 14);

        jCHalf.setText("Halvkrops");
        jCHalf.setOpaque(false);
        jCHalf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCHalfActionPerformed(evt);
            }
        });
        massagePanel.add(jCHalf);
        jCHalf.setBounds(10, 82, 140, 23);

        jCWhole.setText("Helkrops");
        jCWhole.setOpaque(false);
        jCWhole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCWholeActionPerformed(evt);
            }
        });
        massagePanel.add(jCWhole);
        jCWhole.setBounds(153, 82, 130, 23);

        jTName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTNameFocusLost(evt);
            }
        });
        massagePanel.add(jTName);
        jTName.setBounds(150, 60, 117, 20);

        jLabel3.setText("Kunde:");
        massagePanel.add(jLabel3);
        jLabel3.setBounds(10, 20, 120, 14);

        jLabel4.setText("Navn:");
        massagePanel.add(jLabel4);
        jLabel4.setBounds(150, 40, 120, 14);

        jLabel5.setText("Telefon nummer:");
        massagePanel.add(jLabel5);
        jLabel5.setBounds(10, 40, 120, 14);

        jTPhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTPhoneFocusLost(evt);
            }
        });
        massagePanel.add(jTPhone);
        jTPhone.setBounds(10, 60, 111, 20);

        jLabel6.setText("Bemærkninger:");
        massagePanel.add(jLabel6);
        jLabel6.setBounds(10, 110, 260, 14);

        jTComment.setColumns(20);
        jTComment.setRows(5);
        jTComment.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTCommentFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTComment);

        massagePanel.add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 266, 96);

        jLabel7.setText("Fra:");
        massagePanel.add(jLabel7);
        jLabel7.setBounds(10, 240, 80, 14);

        jCStartTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        massagePanel.add(jCStartTime);
        jCStartTime.setBounds(73, 240, 130, 20);

        jBCreateMassage.setText("Opret");
        jBCreateMassage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCreateMassageActionPerformed(evt);
            }
        });
        massagePanel.add(jBCreateMassage);
        jBCreateMassage.setBounds(70, 270, 130, 23);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/massage_bkgr.png"))); // NOI18N
        massagePanel.add(jLabel9);
        jLabel9.setBounds(0, 0, 290, 340);

        add(massagePanel, "card3");

        bbqPanel.setLayout(null);

        jButton1.setText("Opret");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        bbqPanel.add(jButton1);
        jButton1.setBounds(480, 420, 90, 23);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kunde"));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        jTBBQPhone.setText("Telefonnummer");
        jTBBQPhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBBQPhoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBBQPhoneFocusLost(evt);
            }
        });
        jPanel1.add(jTBBQPhone);
        jTBBQPhone.setBounds(10, 50, 180, 20);

        jTBBQCusAddress.setText("Adresse");
        jTBBQCusAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBBQCusAddressFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBBQCusAddressFocusGained(evt);
            }
        });
        jPanel1.add(jTBBQCusAddress);
        jTBBQCusAddress.setBounds(10, 80, 180, 20);

        jTBBQName.setText("Navn");
        jTBBQName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBBQNameActionPerformed(evt);
            }
        });
        jTBBQName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBBQNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBBQNameFocusLost(evt);
            }
        });
        jPanel1.add(jTBBQName);
        jTBBQName.setBounds(10, 20, 180, 20);

        bbqPanel.add(jPanel1);
        jPanel1.setBounds(0, 0, 200, 120);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Arrangement"));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        jTBBQKm.setText("Km");
        jTBBQKm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBBQKmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBBQKmFocusLost(evt);
            }
        });
        jPanel2.add(jTBBQKm);
        jTBBQKm.setBounds(10, 80, 180, 20);

        jTBBQDishes.setText("Kuverter");
        jTBBQDishes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBBQDishesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBBQDishesFocusLost(evt);
            }
        });
        jPanel2.add(jTBBQDishes);
        jTBBQDishes.setBounds(10, 50, 180, 20);

        jTBBQEventAddress.setText("Adresse");
        jTBBQEventAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBBQEventAddressFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBBQEventAddressFocusGained(evt);
            }
        });
        jPanel2.add(jTBBQEventAddress);
        jTBBQEventAddress.setBounds(10, 20, 180, 20);

        bbqPanel.add(jPanel2);
        jPanel2.setBounds(200, 0, 200, 120);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Valgmuligheder"));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);
        bbqPanel.add(jPanel3);
        jPanel3.setBounds(0, 120, 200, 340);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Valgte"));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(null);
        bbqPanel.add(jPanel4);
        jPanel4.setBounds(200, 120, 200, 340);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Kurv"));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(null);
        bbqPanel.add(jPanel5);
        jPanel5.setBounds(400, 0, 250, 390);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/bbq_bkgr.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, bbqPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jLabel10, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        bbqPanel.add(jLabel10);
        jLabel10.setBounds(0, -30, 660, 520);

        add(bbqPanel, "card4");

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jBMassageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMassageActionPerformed
        showPage("massage");
    }//GEN-LAST:event_jBMassageActionPerformed

    private void jCHalfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCHalfActionPerformed
        if (!editing) {
            jCWhole.setSelected(false);
            massage.setType(chooseMasType("Halv krops massage"));
        } else {
            jCWhole.setSelected(false);
            event.getMassage().setType(chooseMasType("Halv krops massage"));
        }
    }//GEN-LAST:event_jCHalfActionPerformed

    private void jCWholeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCWholeActionPerformed
        if (!editing) {
            jCHalf.setSelected(false);
            massage.setType(chooseMasType("Hel krops massage"));
        } else {
            jCHalf.setSelected(false);
            event.getMassage().setType(chooseMasType("Hel krops massage"));
        }
    }//GEN-LAST:event_jCWholeActionPerformed

    private void jTCommentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTCommentFocusLost
        if (!editing) {
            massage.setComment(jTComment.getText());
        } else {
            event.getMassage().setComment(jTComment.getText());
        }
    }//GEN-LAST:event_jTCommentFocusLost

    private void jTPhoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTPhoneFocusLost
        findCustomer("masPhone");
    }//GEN-LAST:event_jTPhoneFocusLost

    private void jBCreateMassageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCreateMassageActionPerformed
        boolean goodToGo = true;
        if (jTPhone.getText().equals("")) {
            jTPhone.setBorder(redLineBorder);
            goodToGo = false;
        }
        if (jTName.getText().equals("")) {
            jTName.setBorder(redLineBorder);
            goodToGo = false;
        }
        if (!jCHalf.isSelected() && !jCWhole.isSelected()) {
            jCHalf.setForeground(Color.red);
            jCWhole.setForeground(Color.red);
            goodToGo = false;
        }
        if (goodToGo) {
            if (editing) {
                String startTime = event.getMassage().getStartTime() + ":00";
                event.getMassage().setStartTime(jCStartTime.getSelectedItem().toString());
                try {
                    mc.updateMassage(event, startTime);
                } catch (SQLException ex) {
                    new ErrorPopup("Aftalen kunne ikke redigeres. "
                            + "<br/> Kontakt Annette, for få dette fixet<br/>"
                            + "(Husk at have maden klar;)!)!");
                    System.out.println(ex.getLocalizedMessage() + "\n" + mc.getMh().getSql() + "\n" + mc.getMh().getSqlCal());
                }
                listener.notifyListeners("New Event Created");
                jFrame.dispose();
            } else {
                if (customer == null) {
                    saveCustomer(true);
                } else {
                    if (!jTName.toString().equals(customer.getName()) || !jTPhone.toString().equals(customer.getPhone())) {
                        alterCustomer(true);
                    } else {
                        saveCustomer(true);
                    }
                }
                massage.setStartTime(jCStartTime.getSelectedItem().toString());
                Massage mas = massage.createMassage();
                event = new Event(cal, customer, mas);
                try {
                    mc.saveMassage(mas, event);
                    listener.notifyListeners("New Event Created");
                    jFrame.dispose();
                } catch (SQLException ex) {
                    if (ex.getLocalizedMessage().length() == 55) {
                        jBCreateMassage.setText("Tid Optaget");
                        jBCreateMassage.setBackground(Color.red);
                    } else {
                        new ErrorPopup("Aftalen kunne ikke redigeres. "
                                + "<br/> Kontakt Annette, for få dette fixet<br/>"
                                + "(Husk at have maden klar;)!)!");
                        System.out.println(ex.getLocalizedMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_jBCreateMassageActionPerformed

    private void jTNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTNameFocusLost
        findCustomer("masName");
    }//GEN-LAST:event_jTNameFocusLost

    private void jTBBQNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQNameFocusGained
        if (jTBBQName.getText().equals("Navn")) {
            jTBBQName.setText("");
        }
    }//GEN-LAST:event_jTBBQNameFocusGained

    private void jTBBQPhoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQPhoneFocusGained
        if (jTBBQPhone.getText().equals("Telefonnummer")) {
            jTBBQPhone.setText("");
        }
    }//GEN-LAST:event_jTBBQPhoneFocusGained

    private void jTBBQCusAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQCusAddressFocusGained
        if (jTBBQCusAddress.getText().equals("Adresse")) {
            jTBBQCusAddress.setText("");
        }
    }//GEN-LAST:event_jTBBQCusAddressFocusGained

    private void jTBBQEventAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQEventAddressFocusGained
        if (jTBBQEventAddress.getText().equals("Adresse")) {
            jTBBQEventAddress.setText("");
        }
    }//GEN-LAST:event_jTBBQEventAddressFocusGained

    private void jTBBQDishesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQDishesFocusGained
        if (jTBBQDishes.getText().equals("Kuverter")) {
            jTBBQDishes.setText("");
        }
    }//GEN-LAST:event_jTBBQDishesFocusGained

    private void jTBBQKmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQKmFocusGained
        if (jTBBQKm.getText().equals("Km")) {
            jTBBQKm.setText("");
        }
    }//GEN-LAST:event_jTBBQKmFocusGained

    private void jTBBQNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQNameFocusLost
        if (jTBBQName.getText().isEmpty()) {
            jTBBQName.setText("Navn");
        } else {
            findCustomer("bbqName");
        }
    }//GEN-LAST:event_jTBBQNameFocusLost

    private void jTBBQPhoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQPhoneFocusLost
        if (jTBBQPhone.getText().isEmpty()) {
            jTBBQPhone.setText("Telefonnummer");
        } else {
            findCustomer("bbqPhone");
        }
    }//GEN-LAST:event_jTBBQPhoneFocusLost

    private void jTBBQCusAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQCusAddressFocusLost
        if (jTBBQCusAddress.getText().isEmpty()) {
            jTBBQCusAddress.setText("Adresse");
        }
    }//GEN-LAST:event_jTBBQCusAddressFocusLost

    private void jTBBQEventAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQEventAddressFocusLost
        if (jTBBQEventAddress.getText().isEmpty()) {
            jTBBQEventAddress.setText("Adresse");
        }
    }//GEN-LAST:event_jTBBQEventAddressFocusLost

    private void jTBBQDishesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQDishesFocusLost
        if (jTBBQDishes.getText().isEmpty()) {
            jTBBQDishes.setText("Kuverter");
        }
    }//GEN-LAST:event_jTBBQDishesFocusLost

    private void jTBBQKmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBBQKmFocusLost
        if (jTBBQKm.getText().isEmpty()) {
            jTBBQKm.setText("Km");
        }
    }//GEN-LAST:event_jTBBQKmFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (customer == null) {
            saveCustomer(false);
        } else {
            if (!jTBBQName.toString().equals(customer.getName()) || !jTBBQPhone.toString().equals(customer.getPhone())) {
                alterCustomer(false);
            } else {
                saveCustomer(false);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBGrillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGrillActionPerformed
        showPage("bbq");
    }//GEN-LAST:event_jBGrillActionPerformed

    private void jTBBQNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTBBQNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBBQNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bbqPanel;
    private javax.swing.JPanel choosePanel;
    private javax.swing.JButton jBCreateMassage;
    private javax.swing.JButton jBGrill;
    private javax.swing.JButton jBMassage;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCHalf;
    private javax.swing.JComboBox jCStartTime;
    private javax.swing.JCheckBox jCWhole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTBBQCusAddress;
    private javax.swing.JTextField jTBBQDishes;
    private javax.swing.JTextField jTBBQEventAddress;
    private javax.swing.JTextField jTBBQKm;
    private javax.swing.JTextField jTBBQName;
    private javax.swing.JTextField jTBBQPhone;
    private javax.swing.JTextArea jTComment;
    private javax.swing.JTextField jTName;
    private javax.swing.JTextField jTPhone;
    private javax.swing.JPanel massagePanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Mas Customer Chosen":
                if (editing) {
                    event.setCustomer(cc.getCustomer());
                } else {
                    customer = cc.getCustomer();
                    jTPhone.setText(customer.getPhone());
                    jTName.setText(customer.getName());
                }
                break;
            case "BBQ Customer Chosen":
                if (editing) {
                } else {
                    customer = cc.getCustomer();
                    jTBBQName.setText(customer.getName());
                    jTBBQPhone.setText(customer.getPhone());
                    jTBBQCusAddress.setText(customer.getHomeAddress());
                }
                break;
        }
    }

}
