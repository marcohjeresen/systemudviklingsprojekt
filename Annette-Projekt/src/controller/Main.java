

package controller;

import javax.swing.JFrame;
import view.Gui;


public class Main {


    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1366, 730);
        gui.setVisible(true);
    }
}
