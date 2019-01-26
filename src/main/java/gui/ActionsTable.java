package gui;


/*
 * TableRenderDemo.java requires no other files.
 */

import aut.AUT;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TableRenderDemo is just like TableDemo, except that it
 * explicitly initializes column sizes and it uses a combo box
 * as an editor for the Sport column.
 */
public class ActionsTable extends JPanel {
    private boolean DEBUG = false;

    public ActionsTable() {
        super(new GridLayout(1,0));

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(1024, 100));
        table.setFillsViewportHeight(true);

        //int rowNumber = 0;


        //Create the scroll pane and add the objectsTable to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);

        //Fiddle with the Sport column's cell editors/renderers.
        setUpActionTypeColumn(table, table.getColumnModel().getColumn(0));
        setUpPageColumn(table, table.getColumnModel().getColumn(1));
        setUpObjectColumn(table, table.getColumnModel().getColumn(2));
        setUpMethodColumn(table, table.getColumnModel().getColumn(3));


        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                    null, column.getHeaderValue(),
                    false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                    getTableCellRendererComponent(
                            table, longValues[i],
                            false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;


            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public void setUpPageColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        AUT aut = new AUT();
        comboBox.addItem(" ");
        for (Field field : aut.getClass().getDeclaredFields()) {
            if (field.getName() != "driver")
                comboBox.addItem(field.getAnnotatedType().getType().getTypeName().replace("pages.", ""));
        }
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Select a page");
        sportColumn.setCellRenderer(renderer);
    }

    public void setUpObjectColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();

        //Set the page to look for
        String page = "pages.Login";

        Class cls = null;
        try {
            cls = Class.forName(page);

            comboBox.addItem(" ");
            for (Field field : cls.getDeclaredFields()) {
                comboBox.addItem(field.getName());
            }
            sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

            //Set up tool tips for the sport cells.
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setToolTipText("Select an object on page");
            sportColumn.setCellRenderer(renderer);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUpMethodColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();

        //Set the page to look for
        String page = "objectTypes.Input";

        Class cls = null;
        try {
            cls = Class.forName(page);

            comboBox.addItem(" ");
            for (Method method : cls.getDeclaredMethods()) {
                comboBox.addItem(method.getName());
            }
            sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

            //Set up tool tips for the sport cells.
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setToolTipText("Select an action");
            sportColumn.setCellRenderer(renderer);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUpActionTypeColumn(JTable table, TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        AUT aut = new AUT();

        comboBox.addItem(" ");
        String[] actionTypes = {"Page action", "Variable store", "Custom code", "Verification"};
        for( String action: actionTypes){
            comboBox.addItem(action);
        }

        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Select an action type");
        sportColumn.setCellRenderer(renderer);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"ActionType",  "Page", "Object", "Method", "Parameters"};

        private Object[][] data = {
                {" ", " ", " ", " ", " "},
        };

        public final Object[] longValues = {
                "       ",
                "                   ",
                "                           ",
                "                               ",
                "                                                   "};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your objectsTable's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
//            if (col < 2) {
//                return false;
//            } else {
                return true;
///           }
        }

        /*
         * Don't need to implement this method unless your objectsTable's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {

            data[row][col] = value;
            fireTableCellUpdated(row, col);

        }

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Actions Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        //Create and set up the content pane.
        ActionsTable newContentPane = new ActionsTable();


        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setSize(600,600);

        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}