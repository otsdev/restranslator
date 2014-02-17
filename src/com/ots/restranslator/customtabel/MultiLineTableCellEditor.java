/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ots.restranslator.customtabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author mahdi
 */
public class MultiLineTableCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    JTextArea textArea = new ResizableTextArea();

    public MultiLineTableCellEditor() {
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textArea.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);

    }

    public Object getCellEditorValue() {
        return textArea.getText();
    }

    /*--------------------------------[ clickCountToStart ]----------------------------------*/
    protected int clickCountToStart = 2;

    public int getClickCountToStart() {
        return clickCountToStart;
    }

    public void setClickCountToStart(int clickCountToStart) {
        this.clickCountToStart = clickCountToStart;
    }

    public boolean isCellEditable(EventObject e) {
        return !(e instanceof MouseEvent)
                || ((MouseEvent) e).getClickCount() >= clickCountToStart;
    }

    /*--------------------------------[ ActionListener ]------------------------*/
    public void actionPerformed(ActionEvent ae) {
        stopCellEditing();
    }

    /*---------------------------[ TableCellEditor ]------------------------*/
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        String text = value != null ? value.toString() : "";
        textArea.setText(text);
        return textArea;
    }
}
