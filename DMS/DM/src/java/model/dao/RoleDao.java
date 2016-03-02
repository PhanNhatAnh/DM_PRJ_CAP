/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Role;
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
public class RoleDao {

    //Get All Role
    public static List<Role> getList() {
        List<Role> lst = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql;
            hql = "select r from Role r";
            Query query = session.createQuery(hql);
            //Query list
            lst = (List<Role>) query.list();
        } catch (HibernateException ex) {
            Logger.getLogger(RoleDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return lst;
    }

    //Add Role
    public static boolean addRole(Role r) {
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

    public static boolean CheckExistRole(String roleName) {
        try {
            List<Role> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Role r Where r.roleName = :rolename";
                Query query = session.createQuery(hql);
                query.setParameter("rolename", roleName);

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

    public static Role getById(int roleId) {
        Role r = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            r = (Role) session.get(Role.class,
                    roleId);
        } catch (HibernateException ex) {
            //Log the exception
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return r;
    }

    public static Role getByName(String roleName) {
        try {
            List<Role> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Role r Where r.roleName = :rolename";
                Query query = session.createQuery(hql);
                query.setParameter("rolename", roleName);

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

    public static boolean Update(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return false;
        }
        return true;
    }

    public static boolean DeleteRole(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return false;
        }
        return true;
    }

    public static List<Role> getListBySearch(String roleNameSearch) {
        try {
            List<Role> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Role r Where r.roleName like :rolename";
                Query query = session.createQuery(hql);
                query.setParameter("rolename", "%" + roleNameSearch + "%");

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
