/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Clemens
 */
public class CourseDayTableModel extends AbstractTableModel{
    private String timeString;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
    private String tableHead[] = {"Day", "From", "To", "Course Day"};
    private Object[][] data;

    public Object[][] getData() {
        return data;
    }

    public CourseDayTableModel(Object[][] data) {
        this.data = data;
    }

    
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
       return column >= 1;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex)
        {
            case 0:
                return String.class;
            case 1:
                return Date.class;
            case 2:
                return Date.class;
            case 3:
                return Boolean.class;
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return tableHead.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 1:
            case 2:
        
            try {
                return dateFormat.parse((String) data[rowIndex][columnIndex]);
            } catch (ParseException ex) {
                Logger.getLogger(CourseDayTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return tableHead[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 1 || columnIndex == 2)
        {
            data[rowIndex][columnIndex] = dateFormat.format(aValue);
        }
        else
        {
            data[rowIndex][columnIndex] = aValue;
        }
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    

    
    
    


    
}
