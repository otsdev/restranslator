package com.ots.restranslator;

import com.ots.restranslator.frames.MainFrame;
import javax.swing.UIManager;

/**
 * Created by jafar on 2/5/14.
 */
public class Main {

    public static void main(String[] args) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                MainFrame mainFrame = new MainFrame();
                mainFrame.setSize(800, 600);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        });
    }
}
