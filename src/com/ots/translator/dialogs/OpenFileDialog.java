package com.ots.translator.dialogs;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * Created by mahdi on 2/11/14.
 */
public class OpenFileDialog extends JFrame {

    private JTextField txtOriginalFile, txtTranslatedFile;
    private JButton btnOriginalFile, btnTranslatedFile, btnOk, btnCancel;
    private JFileChooser fcOpen;

    public static OpenFileDialog newInstance(JFrame frame) {
        OpenFileDialog openFileDialog = new OpenFileDialog();
        openFileDialog.setSize(500, 140);
        openFileDialog.setResizable(false);
        openFileDialog.setLocationRelativeTo(frame);
        return openFileDialog;

    }

    private OpenFileDialog() {
        initComponents();

        test();

    }

    private void initComponents() {
        txtTranslatedFile = new JTextField("File two");

        setLayout(new GridBagLayout());

        // first row
        JLabel jLabel = new JLabel("Original File:");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 4, 0, 0);
        c.ipadx = 10;
        c.weightx = 0.02;
        c.gridx = 0;
        c.gridy = 0;
        add(jLabel, c);

        txtOriginalFile = new JTextField("");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 4);
        c.weightx = 0.9;
        c.gridx = 1;
        c.gridy = 0;
        add(txtOriginalFile, c);

        btnOriginalFile = new JButton("Browse");
        btnOriginalFile.addActionListener(generalButtonsHandler);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 4);
        c.weightx = 0.08;
        c.gridx = 2;
        c.gridy = 0;
        add(btnOriginalFile, c);

        // second row
        jLabel = new JLabel("Translated File:");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(4, 4, 0, 0);
        c.ipadx = 10;
        c.weightx = 0.02;
        c.gridx = 0;
        c.gridy = 1;
        add(jLabel, c);

        txtTranslatedFile = new JTextField("");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(4, 0, 0, 4);
        c.weightx = 0.9;
        c.gridx = 1;
        c.gridy = 1;
        add(txtTranslatedFile, c);

        btnTranslatedFile = new JButton("Browse");
        btnTranslatedFile.addActionListener(generalButtonsHandler);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4, 0, 0, 4);
        c.weightx = 0.08;
        c.gridx = 2;
        c.gridy = 1;
        add(btnTranslatedFile, c);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(generalButtonsHandler);
        jPanel.add(btnCancel);

        btnOk = new JButton("Ok");
        btnOk.addActionListener(generalButtonsHandler);
        jPanel.add(btnOk);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(4, 0, 0, 0);
        c.weightx = 0.2;
        c.gridx = 2;
        c.gridy = 2;
        add(jPanel, c);

    }

    private ActionListener generalButtonsHandler = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src == btnCancel) {
                setVisible(false);
            } else if (src == btnOk) {

            } else if (src == btnOriginalFile || src == btnTranslatedFile) {
                if (fcOpen == null) {
                    fcOpen = new JFileChooser();

                }

                fcOpen.setFileFilter(new XMLFilter());
                int returnVal = fcOpen.showDialog(OpenFileDialog.this, "Done");
            }
        }
    };

    public class XMLFilter extends FileFilter {

        //Accept all directories and all gif, jpg, tiff, or png files.
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String extension = getExtension(f);
            if (extension != null) {
                if (extension.equals("xml")) {
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        }

        public String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
//The description of this filter

        public String getDescription() {
            return "String resource file";
        }
    }

    private void test() {
        String res;
        String res2;
        int finalNum = 0;

        for (int i = 100; i <= 999; i++) {
            for (int j = 100; j < i; j++) {
                int multi = j * i;
                res = String.valueOf(multi);
                res2 = new StringBuilder(res).reverse().toString();
                if (res.equals(res2)) {
                    if (multi > finalNum) {
                        finalNum = multi;
                    }

                }

            }
        }

        System.err.println("res: " + finalNum);

    }

}
