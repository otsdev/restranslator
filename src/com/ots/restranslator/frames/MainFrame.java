package com.ots.restranslator.frames;

import com.ots.restranslator.models.StringResources;
import com.ots.restranslator.xml.XmlResourceParser;
import com.ots.translator.dialogs.OpenFileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;

/**
 * Created by jafar on 2/6/14.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setJMenuBar(createMenuBar());

    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("Open String File",
                KeyEvent.VK_O);

        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFileDialog.newInstance(MainFrame.this, onFileSelected).setVisible(true);
            }
        });

        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        return menuBar;
    }

    private OpenFileDialog.OnFilesSelected onFileSelected = new OpenFileDialog.OnFilesSelected() {

        @Override
        public void onDoneFileSelection(File originalFile, File translateFile) {
            XmlResourceParser parser = new XmlResourceParser();
            parser.parse(originalFile, true);
            parser.parse(translateFile, false);

            StringResources resources = parser.getmStringResources();
            System.out.print(resources.flatten());
        }

        @Override
        public void onCancelFileSelection() {
            System.err.println("File selection canceled");
        }
    };
}
