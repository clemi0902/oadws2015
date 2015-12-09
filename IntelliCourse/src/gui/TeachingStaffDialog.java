/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import intellicourse.entity.User;
import intellicourse.util.HibernateUtil;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

/**
 *
 * @author Clemens
 */
public class TeachingStaffDialog extends javax.swing.JDialog {

    /**
     * Creates new form TeachingStaffDialog
     */
    public TeachingStaffDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tfFilterUsername.getDocument().addDocumentListener(new MyDocumentListener());
        tfFilterFirstName.getDocument().addDocumentListener(new MyDocumentListener());
        tfFilterLastName.getDocument().addDocumentListener(new MyDocumentListener());
        tbAnzeige.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setTitle("Teaching Staff");
        displayData();
    }
    
    private void displayData() {
        String sql;
        if (tfFilterUsername.getText().trim().equals("") && tfFilterFirstName.getText().trim().equals("") && tfFilterLastName.getText().trim().equals("")) {
            sql = "select u.uid, u.username, u.password, u.vorname, u.nachname from  User  u inner join staff s USING(uid)";
        } else
            sql = "select u.uid, u.username, u.password, u.vorname, u.nachname from  User  u inner join staff s USING(uid) "
                    + "WHERE UPPER(u.username) LIKE '" + tfFilterUsername.getText().trim().toUpperCase() + "%' "
                    + "AND UPPER(u.vorname) LIKE '" + tfFilterFirstName.getText().trim().toUpperCase() + "%' "
                    + "AND UPPER(u.nachname) LIKE '" + tfFilterLastName.getText().trim().toUpperCase() + "%'";
        
        executeQuery(sql);
    }

    private void executeQuery(String query) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery q = session.createSQLQuery(query);
            q.addScalar("uid",new IntegerType());
            q.addScalar("username",new org.hibernate.type.StringType());
            q.addScalar("vorname",new org.hibernate.type.StringType());
            q.addScalar("nachname",new org.hibernate.type.StringType());
            q.addScalar("password",new org.hibernate.type.StringType());
            q.setResultTransformer(Transformers.aliasToBean(User.class));
            List<User> resultList = q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayResult(List resultList) {
        Vector<String> tableHead = new Vector<String>();
        Vector tableData = new Vector();
        tableHead.add("uid");
        tableHead.add("Username");
        tableHead.add("First Name");
        tableHead.add("Last Name");
        tableHead.add("Password");
        
        for (Object o : resultList) {
            User user = new User();
            
            User u = (User) o;
            Vector<Object> row = new Vector<>();
            row.add(u.getUid());
            row.add(u.getUsername());
            row.add(u.getVorname());
            row.add(u.getNachname());
            row.add(u.getPassword());
            tableData.add(row);
        }
        tbAnzeige.setModel(new DefaultTableModel(tableData, tableHead));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbAnzeige = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tfFilterUsername = new javax.swing.JTextField();
        tfFilterFirstName = new javax.swing.JTextField();
        tfFilterLastName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbAnzeige.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TeacherID", "Name", "", ""
            }
        ));
        jScrollPane1.setViewportView(tbAnzeige);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDeleteStaff(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.GridLayout(1, 4));

        tfFilterUsername.setBorder(BorderFactory.createTitledBorder("Filter Username:"));
        jPanel2.add(tfFilterUsername);

        tfFilterFirstName.setBorder(BorderFactory.createTitledBorder("Filter First Name:"));
        jPanel2.add(tfFilterFirstName);

        tfFilterLastName.setBorder(BorderFactory.createTitledBorder("Filter Last Name:"));
        jPanel2.add(tfFilterLastName);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AddTeachingStaffDialog atsd = new AddTeachingStaffDialog(null, rootPaneCheckingEnabled);
        atsd.addWindowListener(new MyWindowAdapter());
        atsd.setIsAdd(true);
        atsd.setVisible(true);
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AddTeachingStaffDialog etsd = new AddTeachingStaffDialog(null, rootPaneCheckingEnabled);
        etsd.setTitle("Edit Teachinng Staff");
        int rowindex = tbAnzeige.getSelectedRow();
        int id = Integer.parseInt(tbAnzeige.getValueAt(rowindex, 0).toString());
        String username = tbAnzeige.getValueAt(rowindex, 1).toString();
        String  password= tbAnzeige.getValueAt(rowindex, 4).toString();
        String vorname = tbAnzeige.getValueAt(rowindex, 2).toString();
        String nachname = tbAnzeige.getValueAt(rowindex, 3).toString();
        User user = new User(id,username,password,vorname,nachname);
        etsd.setUser(user);
        etsd.setIsAdd(false);
        etsd.fillFields();
        etsd.setVisible(true);
        displayData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void onDeleteStaff(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDeleteStaff
        int selectedIndex = tbAnzeige.getSelectedRow();
        if (selectedIndex >= 0)
        {
            String sql = null;
        SQLQuery query = null;
        try {
            int uid = Integer.parseInt(tbAnzeige.getModel().getValueAt(selectedIndex, 0).toString());
            User user = new User(uid, tbAnzeige.getModel().getValueAt(selectedIndex, 1).toString(), tbAnzeige.getModel().getValueAt(selectedIndex, 2).toString(), tbAnzeige.getModel().getValueAt(selectedIndex, 3).toString(),
            tbAnzeige.getModel().getValueAt(selectedIndex, 4).toString());
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            sql = "SELECT lid FROM lecture WHERE uid = " + uid;
            query = session.createSQLQuery(sql);
            List results = query.list();
            for (Object o : results)
            {
                int lid = (int) o;
                String sqlCourse = "DELETE FROM course WHERE lid = " + lid;
                String sqlEvent =  "DELETE FROM event WHERE lid = " + lid;
                String sqlStudLect = "DELETE FROM student_lecture WHERE lid = " + lid;
                String sqlLect =  "DELETE FROM lecture WHERE lid = " + lid;
                query = session.createSQLQuery(sqlCourse);
                query.executeUpdate();
                query = session.createSQLQuery(sqlEvent);
                query.executeUpdate();
                query = session.createSQLQuery(sqlStudLect);
                query.executeUpdate();
                query = session.createSQLQuery(sqlLect);
                query.executeUpdate(); 
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayData();
        }
    }//GEN-LAST:event_onDeleteStaff

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
            java.util.logging.Logger.getLogger(TeachingStaffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeachingStaffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeachingStaffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeachingStaffDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TeachingStaffDialog dialog = new TeachingStaffDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbAnzeige;
    private javax.swing.JTextField tfFilterFirstName;
    private javax.swing.JTextField tfFilterLastName;
    private javax.swing.JTextField tfFilterUsername;
    // End of variables declaration//GEN-END:variables

    private  class MyWindowAdapter extends WindowAdapter {

        @Override
        public void windowDeactivated(WindowEvent e) {
            TeachingStaffDialog.this.displayData();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            TeachingStaffDialog.this.displayData();
        }

        @Override
        public void windowClosing(WindowEvent e) {
            TeachingStaffDialog.this.displayData();
        }
        
    }

    
    private class MyDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            TeachingStaffDialog.this.displayData();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            TeachingStaffDialog.this.displayData();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            TeachingStaffDialog.this.displayData();
        }

        
    }
}
