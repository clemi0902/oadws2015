/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Clemens
 */



public class CourseEventTableModel extends AbstractTableModel{

    @Override
    public String getColumnName(int column) {
        switch (column)
        {
            case 0: return "Course/Event";
            case 1: return "Name";
            case 2: return "Description";
            default: return "";
        }
    }

    
    
    @Override
    public int getRowCount() {
        return 100;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    
}
