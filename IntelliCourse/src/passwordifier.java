
import beans.Kryptographie;
import intellicourse.util.HibernateUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Clemens
 */
public class passwordifier {

    public static void main(String[] args) {
        String sqlString = "SELECT uid FROM user";
        Kryptographie k = new Kryptographie();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery(sqlString);
        List l = q.list();
        for (Object o : l) {
            int uid = (int) o;
            String getPwQuery = "SELECT password FROM user "
                    + "WHERE uid = " + uid;
            q = session.createSQLQuery(getPwQuery);
            for (Object o2 : q.list()) {
                try {
                    String password = k.encrypt(o2.toString());
                    String setPwQuery = "UPDATE user "
                            + "SET password = '" + password + "' "
                            + "WHERE uid = " + uid;
                    q = session.createSQLQuery(setPwQuery);
                    q.executeUpdate();
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(passwordifier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(passwordifier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(passwordifier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(passwordifier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(passwordifier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(passwordifier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        session.getTransaction().commit();
        session.close();
        session.disconnect();

    }
}
