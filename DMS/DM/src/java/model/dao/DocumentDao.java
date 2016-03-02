/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Document;
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
public class DocumentDao {
    //Get All Document
    public static List<Document> getList() {
        List<Document> lst = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql;
            hql = "select r from Document r";
            Query query = session.createQuery(hql);
            //Query list
            lst = (List<Document>) query.list();
        } catch (HibernateException ex) {
            Logger.getLogger(DocumentDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return lst;
    }

    //Add Document
    public static boolean addDocument(Document r) {
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

    public static boolean CheckExistDocument(String documentName) {
        try {
            List<Document> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Document r Where r.documentName = :documentname";
                Query query = session.createQuery(hql);
                query.setParameter("documentname", documentName);

                //query list
                lst = query.list();
            } catch (HibernateException ex) {
                System.err.println(ex);
            } finally {
                //Close sesion
                session.close();
            }
            //Return result
            return (lst != null && lst.size() > 0);
        } catch (Exception e) {
            return false;
        }
    }

    public static Document getById(int documentId) {
        Document r = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            r = (Document) session.get(Document.class,
                    documentId);
        } catch (HibernateException ex) {
            //Log the exception
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return r;
    }

    public static Document getByName(String documentName) {
        try {
            List<Document> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Document r Where r.documentName = :documentname";
                Query query = session.createQuery(hql);
                query.setParameter("documentname", documentName);

                //query list
                lst = query.list();
            } catch (HibernateException ex) {
                System.err.println(ex);
            } finally {
                //Close sesion
                session.close();
            }
            //Return result
            if (lst != null && lst.size() > 0) {
                return lst.get(0);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean Update(Document document) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(document);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return  false;
        }
        return true;
    }

    public static boolean DeleteDocument(Document document) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(document);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return  false;
        }
        return true;
    }

    public static List<Document> getListBySearch(String documentNameSearch) {
        try {
            List<Document> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Document r Where r.documentName like :documentname";
                Query query = session.createQuery(hql);
                query.setParameter("documentname", "%" + documentNameSearch + "%");
                
                //query list
                lst = query.list();
            } catch (HibernateException ex) {
                System.err.println(ex);
            } finally {
                //Close sesion
                session.close();
            }
            //Return result
            return lst;
        } catch (Exception e) {
            return null;
        }
    }
}
