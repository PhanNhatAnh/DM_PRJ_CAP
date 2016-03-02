/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Category;
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
public class CategoryDao {
    //Get All Category
    public static List<Category> getList() {
        List<Category> lst = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql;
            hql = "select r from Category r";
            Query query = session.createQuery(hql);
            //Query list
            lst = (List<Category>) query.list();
        } catch (HibernateException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return lst;
    }

    //Add Category
    public static boolean addCategory(Category r) {
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

    public static boolean CheckExistCategory(String categoryName) {
        try {
            List<Category> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Category r Where r.categoryName = :categoryname";
                Query query = session.createQuery(hql);
                query.setParameter("categoryname", categoryName);

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

    public static Category getById(int categoryId) {
        Category r = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            r = (Category) session.get(Category.class,
                    categoryId);
        } catch (HibernateException ex) {
            //Log the exception
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return r;
    }

    public static Category getByName(String categoryName) {
        try {
            List<Category> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Category r Where r.categoryName = :categoryname";
                Query query = session.createQuery(hql);
                query.setParameter("categoryname", categoryName);

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

    public static boolean Update(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return  false;
        }
        return true;
    }

    public static boolean DeleteCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(category);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return  false;
        }
        return true;
    }

    public static List<Category> getListBySearch(String categoryNameSearch) {
        try {
            List<Category> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Category r Where r.categoryName like :categoryname";
                Query query = session.createQuery(hql);
                query.setParameter("categoryname", "%" + categoryNameSearch + "%");
                
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
