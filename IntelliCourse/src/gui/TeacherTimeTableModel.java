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
public class TeacherTimeTableModel extends AbstractTableModel{

    
    
    @Override
    public String getColumnName(int column) {
        switch(column)
        {
            case 0: return "Sun";
            case 1: return "Mon";
            case 2: return "Tue";
            case 3: return "Wed";
            case 4: return "Thu";
            case 5: return "Fri";
            case 6: return "Sat";
            default: return "rest";
        }//To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int getRowCount() {
        return 15*4;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    
}
