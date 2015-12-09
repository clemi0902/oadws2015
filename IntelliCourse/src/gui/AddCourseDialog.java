/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import intellicourse.entity.Course;
import intellicourse.entity.Event;
import intellicourse.entity.Lecture;
import intellicourse.entity.Staff;
import intellicourse.entity.User;
import intellicourse.util.HibernateUtil;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Clemens
 */
public class AddCourseDialog extends javax.swing.JDialog {

    /**
     * Creates new form AddCourseDialog
     */
    
        private boolean isAdd;
    private boolean isRb1;
    private String name;
    private String beschreibung;
    private String radioTex;
    private Lecture lecture;
    
    public AddCourseDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Add Course/Event");
    }

    
    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
    public void setIsAdd(boolean isAdd) {
        this.isAdd = isAdd;
    }
    public void setIsRb1(boolean isRb1) {
        this.isRb1 = isRb1;
    }

     public void fillFields()
    {
        if(!isAdd)
        {
            tfName.setText(lecture.getName());
            taBeschreibung.setText(lecture.getBeschreibung());
            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taBeschreibung = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setLayout(new java.awt.GridLayout(2, 2));

        jLabel3.setText("Course/Event:");
        jPanel5.add(jLabel3);

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Course");
        jPanel5.add(jRadioButton1);
        jPanel5.add(jLabel4);

        jRadioButton2.setText("Event");
        ButtonGroup bgChoose = new ButtonGroup();
        bgChoose.add(jRadioButton1);
        bgChoose.add(jRadioButton2);
        jPanel5.add(jRadioButton2);

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setText("Course/Event Name:");
        jPanel3.add(jLabel1);
        jPanel3.add(tfName);

        jPanel4.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setText("Course/Event Description:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2);

        taBeschreibung.setColumns(20);
        taBeschreibung.setRows(5);
        jScrollPane1.setViewportView(taBeschreibung);

        jPanel1.add(jScrollPane1);

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //TODO add Course/Event to DB
        name = tfName.getText();
        beschreibung = taBeschreibung.getText();
        if(name.trim().equals("") ||beschreibung.trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Incorrect Input","Error",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(jRadioButton1.isSelected())
            {
                if(isAdd)
                {
                    addCourse();
                }
                else
                {
                    editCourse();
                    
                }
            }
            if(jRadioButton2.isSelected())
            {
                if(isAdd)
                {
                    addEvent();
                }
                else
                {
                     editCourse();
                }
            } 
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addCourse()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
   
        Lecture lecture = new Lecture();
        lecture.setName(name);
        lecture.setBeschreibung(beschreibung);
        
        session.save(lecture);
        Course course = new Course(lecture);
        session.save(course);
        session.getTransaction().commit();
    } 
    private void addEvent()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
   
        Lecture lecture = new Lecture();
        lecture.setName(name);
        lecture.setBeschreibung(beschreibung);
        
        session.save(lecture);
        Event event = new Event(lecture);
        session.save(event);
        session.getTransaction().commit();
    } 
    
    private void editCourse(){
        String sql;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            
            Lecture lecture2 = new Lecture();
            lecture2.setLid(lecture.getLid());
            lecture2.setName(name);
            lecture2.setBeschreibung(beschreibung);
            
            session.update(lecture2);
            session.getTransaction().commit();
            session.close();
                  
             
        }catch(Exception e){
             
            JOptionPane.showMessageDialog(null, "Error occured !");
            e.printStackTrace();
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AddCourseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCourseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCourseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCourseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddCourseDialog dialog = new AddCourseDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taBeschreibung;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}
