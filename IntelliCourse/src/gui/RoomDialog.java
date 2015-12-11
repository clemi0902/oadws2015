/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import intellicourse.entity.Room;
import intellicourse.entity.User;
import intellicourse.util.HibernateUtil;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Clemens
 */
public class RoomDialog extends javax.swing.JDialog {

    /**
     * Creates new form RoomDialog
     */
    public RoomDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tfName.getDocument().addDocumentListener(new MyDocumentListener());
        tfSeats.getDocument().addDocumentListener(new MyDocumentListener());
        this.setTitle("Rooms");
        tbRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showFilteredData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRooms = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfSeats = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onRemoveRoom(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onEdit(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setText("Ok");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        tbRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Room Name", "Address", "Number of Seats"
            }
        ));
        jScrollPane1.setViewportView(tbRooms);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(2, 2));

        jLabel1.setText("Name:");
        jPanel3.add(jLabel1);

        jLabel2.setText("Minimum of Seats");
        jPanel3.add(jLabel2);
        jPanel3.add(tfName);
        jPanel3.add(tfSeats);

        jPanel3.setBorder(BorderFactory.createTitledBorder("Filter by:"));

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddRoomDialog ard = new AddRoomDialog(null, rootPaneCheckingEnabled);
        ard.setVisible(true);
        showFilteredData();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void onEdit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onEdit
        int selectedIndex = tbRooms.getSelectedRow();
        if (selectedIndex >= 0) {
            AddRoomDialog erd = new AddRoomDialog(null, rootPaneCheckingEnabled);
            erd.setTitle("Edit Room");
            erd.setEditMode();
            Room room = new Room(Integer.parseInt(tbRooms.getModel().getValueAt(selectedIndex, 0).toString()),
                    tbRooms.getModel().getValueAt(selectedIndex, 1).toString(), Integer.parseInt(tbRooms.getModel().getValueAt(selectedIndex, 2).toString()));
            erd.showRoom(room);
            erd.setVisible(true);
            showFilteredData();
        }


    }//GEN-LAST:event_onEdit

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void onRemoveRoom(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onRemoveRoom
        int i = tbRooms.getSelectedRow();
        if (i >= 0) {
            int sure = JOptionPane.showConfirmDialog(this, "Are you sure you want to do this?", "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (sure == 0) {
                String sql = null;
                SQLQuery query = null;
                try {
                    int rid = Integer.parseInt(tbRooms.getModel().getValueAt(i, 0).toString());
                    Room room = new Room(rid, tbRooms.getModel().getValueAt(i, 1).toString(), Integer.parseInt(tbRooms.getModel().getValueAt(i, 2).toString()));
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    sql = "SELECT lid FROM lecture WHERE rid = " + rid;
                    query = session.createSQLQuery(sql);
                    List results = query.list();
                    for (Object o : results) {
                        int lid = (int) o;

                        String sqlCourse = "DELETE FROM course WHERE lid = " + lid;
                        String sqlEvent = "DELETE FROM event WHERE lid = " + lid;
                        String sqlStudLect = "DELETE FROM student_lecture WHERE lid = " + lid;
                        String sqlLect = "DELETE FROM lecture WHERE lid = " + lid;
                        String sqlCurriculum = "DELETE FROM curriculum_lecture WHERE lid = " + lid;
                        String sqlCourseTimes = "DELETE FROM course_day_time WHERE lid = " + lid;
                        query = session.createSQLQuery(sqlCourseTimes);
                        query.executeUpdate();
                        query = session.createSQLQuery(sqlCurriculum);
                        query.executeUpdate();
                        query = session.createSQLQuery(sqlCourse);
                        query.executeUpdate();
                        query = session.createSQLQuery(sqlEvent);
                        query.executeUpdate();
                        query = session.createSQLQuery(sqlStudLect);
                        query.executeUpdate();
                        query = session.createSQLQuery(sqlLect);
                        query.executeUpdate();
                    }
                    String sqlRoom = "DELETE FROM room WHERE rid = " + rid;
                    query = session.createSQLQuery(sqlRoom);
                    query.executeUpdate();
                    session.getTransaction().commit();
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                showFilteredData();
            }
        }
    }//GEN-LAST:event_onRemoveRoom

    private void showFilteredData() {
        checkInput();
        String QUERY = null;
        if (tfName.getText().trim().equals("") && tfSeats.getText().trim().equals("")) {
            QUERY = "from Room";
        } else if (tfName.getText().trim().equals("")) {
            QUERY = "from Room WHERE anzSitzplatz >= " + tfSeats.getText();
        } else if (tfSeats.getText().trim().equals("")) {
            QUERY = "from Room WHERE UPPER(name) LIKE '" + tfName.getText().toUpperCase() + "%'";
        } else {
            QUERY = "from Room WHERE anzSitzplatz >= " + tfSeats.getText()
                    + " AND UPPER(name) LIKE '" + tfName.getText().toUpperCase() + "%'";
        }
        executeQuery(QUERY);

    }

    private void executeQuery(String query) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(query);
            List resultList = q.list();
            displayResult(resultList);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayResult(List resultList) {
        Vector<String> tableHead = new Vector<String>();
        Vector tableData = new Vector();
        tableHead.add("RoomID");
        tableHead.add("Room Name");
        tableHead.add("Number of Seats");
        for (Object o : resultList) {

            Room r = (Room) o;
            Vector<Object> row = new Vector<>();
            row.add(r.getRid());
            row.add(r.getName());
            row.add(r.getAnzSitzplatz());
            tableData.add(row);
        }
        tbRooms.setModel(new DefaultTableModel(tableData, tableHead));

    }

    private void checkInput() {
        try {
            int input = Integer.parseInt(tfSeats.getText());
        } catch (Exception e) {
            tfSeats.setText("");
        }

    }

    private class MyDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            changeTable();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changeTable();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changeTable();
        }

        private void changeTable() {
            RoomDialog.this.showFilteredData();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoomDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RoomDialog dialog = new RoomDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbRooms;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfSeats;
    // End of variables declaration//GEN-END:variables
}
