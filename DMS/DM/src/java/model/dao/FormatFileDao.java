/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.FormatFile;
import model.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dunglt2603
 */
public class FormatFileDao {
    //Get All FormatFile
    public static List<FormatFile> getList() {
        List<FormatFile> lst = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql;
            hql = "select r from FormatFile r";
            Query query = session.createQuery(hql);
            //Query list
            lst = (List<FormatFile>) query.list();
        } catch (HibernateException ex) {
            Logger.getLogger(FormatFileDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return lst;
    }

    //Add FormatFile
    public static boolean addFormatFile(FormatFile f) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(f);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return true;
    }
}
