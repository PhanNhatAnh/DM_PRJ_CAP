/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Users;
import model.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dunglt2603
 */
public class UserDao {

    //Get All User
    public static List<Users> getList() {
        List<Users> lst = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql;
            hql = "select r from Users r";
            Query query = session.createQuery(hql);
            //Query list
            lst = (List<Users>) query.list();
        } catch (HibernateException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return lst;
    }

    //Add User
    public static boolean addUser(Users r) {
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

    public static boolean CheckExistUser(String userName) {
        try {
            List<Users> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Users r Where r.userName = :username";
                Query query = session.createQuery(hql);
                query.setParameter("username", userName);

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

    public static Users getById(int userId) {
        Users r = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            r = (Users) session.get(Users.class,
                    userId);
        } catch (HibernateException ex) {
            //Log the exception
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return r;
    }

    public static Users getByName(String userName) {
        try {
            List<Users> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Users r Where r.userName = :username";
                Query query = session.createQuery(hql);
                query.setParameter("username", userName);

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

    public static boolean Update(Users user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return false;
        }
        return true;
    }

    public static boolean DeleteUser(Users user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return false;
        }
        return true;
    }

    public static List<Users> getListBySearch(String userNameSearch, String fullNameSearch) {
        try {
            List<Users> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                lst = session.createCriteria(Users.class).add(Restrictions.like("userName", "%" + userNameSearch + "%")).add(Restrictions.like("firstAndLastName", "%" + fullNameSearch + "%")).list();
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
