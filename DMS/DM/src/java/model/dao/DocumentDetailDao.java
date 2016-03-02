/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.DocumentDetail;
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
public class DocumentDetailDao {
    //Get All ContentOfDocument
    public static List<DocumentDetail> getList() {
        List<DocumentDetail> lst = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql;
            hql = "select r from DocumentDetail r";
            Query query = session.createQuery(hql);
            //Query list
            lst = (List<DocumentDetail>) query.list();
        } catch (HibernateException ex) {
            Logger.getLogger(DocumentDetailDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return lst;
    }

    //Add ContentOfDocument
    public static boolean addContentOfDocument(DocumentDetail r) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(r);
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
