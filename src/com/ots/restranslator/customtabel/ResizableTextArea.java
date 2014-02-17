/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ots.restranslator.customtabel;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author mahdi
 */
public class ResizableTextArea extends JTextArea {

    public void addNotify() {
        super.addNotify();
        getDocument().addDocumentListener(listener);
    }

    public void removeNotify() {
        getDocument().removeDocumentListener(listener);
        super.removeNotify();
    }

    DocumentListener listener = new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            updateBounds();
        }

        public void removeUpdate(DocumentEvent e) {
            updateBounds();
        }

        public void changedUpdate(DocumentEvent e) {
            updateBounds();
        }
    };

    private void updateBounds() {
        if (getParent() instanceof JTable) {
            JTable table = (JTable) getParent();
            if (table.isEditing()) {
                Rectangle cellRect = table.getCellRect(table.getEditingRow(), table.getEditingColumn(), false);
                Dimension prefSize = getPreferredSize();
                setBounds(getX(), getY(), Math.max(cellRect.width, prefSize.width), Math.max(cellRect.height, prefSize.height));
                validate();
            }
        }
    }
}
