package modules.gui_interface;

import javax.swing.table.AbstractTableModel;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class TraditionalTableModel extends AbstractTableModel {

    private String[] columnNames;
    private Object[][] data;

    public TraditionalTableModel(Object[][] data, String[] columnNames) {
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0,columnIndex).getClass();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex,columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == columnNames.length - 1;
    }

    public boolean isCellEditable(EventObject event) {
        if (event instanceof MouseEvent) {return ((MouseEvent) event).getClickCount() >= 2;}
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
