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
public class CourseTable extends AbstractTableModel{

    
    
    @Override
    public int getRowCount() {
        return 3; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return 3; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null; //To change body of generated methods, choose Tools | Templates.
    }
    
}
