

package controller;

import java.awt.Toolkit;
import javax.swing.JFrame;
import view.Gui;


public class Main {


    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1366, 730);
        int height = Toolkit.getDefaultToolkit().getScreenSize().height - gui.getHeight();
        int widht = Toolkit.getDefaultToolkit().getScreenSize().width - gui.getWidth();
        gui.setLocation(widht / 2, height / 2);
        gui.setVisible(true);
    }
}
