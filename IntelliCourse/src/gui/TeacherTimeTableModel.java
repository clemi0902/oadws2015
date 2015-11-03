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
    public int getRowCount() {
        return 24;
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
