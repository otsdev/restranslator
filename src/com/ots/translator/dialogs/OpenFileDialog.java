package com.ots.translator.dialogs;

import java.awt.MenuBar;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

/**
 * Created by mahdi on 2/11/14.
 */
public class OpenFileDialog extends JFrame {

    private JTextField textFileOne;
    private JTextField textFileTwo;

    public OpenFileDialog() {
        initComponents();

    }

    private void initComponents() {
        textFileOne = new JTextField("File One");
        textFileTwo = new JTextField("File two");

        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        add(textFileOne);
        add(textFileTwo);

    }

}
