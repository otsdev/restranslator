package com.ots.translator.dialogs;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by mahdi on 2/11/14.
 */
public class OpenFileDialog extends JFrame {

    private JTextField txtOriginalFile, txtTranslatedFile;
    private JButton btnOriginalFile, btnTranslatedFile, btnOk, btnCancel;

    public OpenFileDialog() {
        initComponents();

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
        c.insets = new Insets(0, 4, 0, 0);
        c.ipadx = 10;
        c.weightx = 0.02;
        c.gridx = 0;
        c.gridy = 1;
        add(jLabel, c);

        txtTranslatedFile = new JTextField("");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 0, 4);
        c.weightx = 0.9;
        c.gridx = 1;
        c.gridy = 1;
        add(txtTranslatedFile, c);

        btnTranslatedFile = new JButton("Browse");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 4);
        c.weightx = 0.08;
        c.gridx = 2;
        c.gridy = 1;
        add(btnTranslatedFile, c);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCancel = new JButton("Cancel");
        jPanel.add(btnCancel);

        btnOk = new JButton("Ok");
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

}
