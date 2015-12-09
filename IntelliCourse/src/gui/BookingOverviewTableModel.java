/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import intellicourse.entity.Course;
import intellicourse.entity.Event;
import intellicourse.entity.Lecture;
import intellicourse.entity.Room;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Clemens
 */
public class BookingOverviewTableModel extends AbstractTableModel{

    private ArrayList<Lecture> lectures;
    private ArrayList<String> tableHeaders;
    private SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    public BookingOverviewTableModel(ArrayList<String> roomNames, ArrayList<Lecture> lectures) {
        this.tableHeaders = roomNames;
        this.lectures = lectures;
        
    }
    
    
    @Override
    public int getRowCount() {
        return tableHeaders.size();
    }

    @Override
    public int getColumnCount() {
        return 15*4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        try {
//            ArrayList<Lecture> lecturesFiltered = new ArrayList<>();
//            int minutes = 15 * (rowIndex % 4);
//            Date timeSlot = parser.parse(8+(rowIndex)+":"+ minutes);
//            for (Lecture l : lectures)
//            {
//                Date lectureBegin = parser.parse(l.getVon().toGMTString());
//                Date lectureEnd = parser.parse(l.getBis().toGMTString());
//                if (timeSlot.after(lectureBegin) && timeSlot.before(timeSlot) || timeSlot.equals(lectureBegin)|| timeSlot.equals(lectureEnd))
//                {
//                    lecturesFiltered.add(l);
//                }
//            }
//            if (lecturesFiltered.contains(tableHeaders.get(columnIndex)))
//            {
//                return "1";
//            }
//            
//        } catch (ParseException ex) {
//            Logger.getLogger(BookingOverviewTableModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return "0";
    }
    
    
    
    
}
