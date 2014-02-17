package com.ots.restranslator.frames;

import com.ots.restranslator.customtabel.MultiLineTableCellEditor;
import com.ots.restranslator.customtabel.MyTable;
import com.ots.restranslator.models.StringResources;
import com.ots.restranslator.xml.XmlResourceParser;
import com.ots.translator.dialogs.OpenFileDialog;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 * Created by jafar on 2/6/14.
 */
public class MainFrame extends JFrame {

    MyTable table;
    public static final String[] COLUMNS_NAMES = {"Key",
        "default",
        "translation"};

    public MainFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setJMenuBar(createMenuBar());
        initComponents();
    }

    private void initComponents() {
        table = new MyTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

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

        menuItem = new JMenuItem("Save",
                KeyEvent.VK_S);

        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StringResources stringResources = ((StringResourcesModel) table.getModel()).getStringResources();
                System.err.println(stringResources.flatten());

            }
        });

        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
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
//            System.out.print(resources.flatten());

            final StringResourcesModel model = new StringResourcesModel(resources);
            table.setModel(model);

            TableColumn column1 = table.getColumn(table.getColumnName(1));
            column1.setCellEditor(new MultiLineTableCellEditor());
            pack();

        }

        @Override
        public void onCancelFileSelection() {
            System.err.println("File selection canceled");
        }
    };

    class StringResourcesModel extends AbstractTableModel {

        private StringResources mStringResources;
        private static final int COLUMN_KEY = 0;
        private static final int COLUMN_ORIGINAL = 1;
        private static final int COLUMN_TRANSLATED = 2;

        public StringResourcesModel(StringResources stringResources) {
            this.mStringResources = stringResources;
            System.err.println("count: " + getRowCount());

            System.err.println("item at 0, 0: " + getValueAt(0, 0));

        }

        public int getColumnCount() {
            return COLUMNS_NAMES.length;

        }

        public int getRowCount() {
            return mStringResources.getItemsCount();

        }

        public String getColumnName(int col) {
            return COLUMNS_NAMES[col];
        }

        public Object getValueAt(int row, int col) {

            switch (col) {
                case COLUMN_KEY:
                    return mStringResources.getItemKeyAt(row);
                case COLUMN_ORIGINAL:
                    return mStringResources.getItemOriginalTextAt(row);

                case COLUMN_TRANSLATED:
                    return mStringResources.getItemTranslatedTextAt(row);

                default:
                    throw new IllegalStateException("invalid column index");
            }

        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return String.class;
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
//            if (col < 2) {
//                return false;
//            } else {
//                return true;
//            }

            return true;
        }


        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            switch (col) {
                case COLUMN_ORIGINAL:
                    mStringResources.setOriginalText(mStringResources.getItemKeyAt(row), (String) value);

                    break;
                case COLUMN_TRANSLATED:
                    mStringResources.setTranslatedText(mStringResources.getItemKeyAt(row), (String) value);

                    break;
                default:
                    throw new IllegalStateException("invalid column index");
            }

            fireTableCellUpdated(row, col);

        }

        public StringResources getStringResources() {
            return this.mStringResources;

        }

    }
}
