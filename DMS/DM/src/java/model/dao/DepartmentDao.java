/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Department;
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
public class DepartmentDao {
    //Get All Department

    public static List<Department> getList() {
        List<Department> lst = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql;
            hql = "select r from Department r";
            Query query = session.createQuery(hql);
            //Query list
            lst = (List<Department>) query.list();
        } catch (HibernateException ex) {
            Logger.getLogger(DepartmentDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return lst;
    }

    //Add Department
    public static boolean addDepartment(Department r) {
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

    public static boolean CheckExistDepartment(String departmentName) {
        try {
            List<Department> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Department r Where r.departmentName = :departmentname";
                Query query = session.createQuery(hql);
                query.setParameter("departmentname", departmentName);

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

    public static Department getById(int departmentId) {
        Department r = null;
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        try {
            r = (Department) session.get(Department.class,
                    departmentId);
        } catch (HibernateException ex) {
            //Log the exception
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
        } finally {
            session.close();
        }
        return r;
    }

    public static Department getByName(String departmentName) {
        try {
            List<Department> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Department r Where r.departmentName = :departmentname";
                Query query = session.createQuery(hql);
                query.setParameter("departmentname", departmentName);

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

    public static boolean Update(Department department) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return false;
        }
        return true;
    }

    public static boolean DeleteDepartment(Department department) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(department);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            Logger.getLogger(UserDao.class.getName()).log(Priority.ERROR, ex);
            return false;
        }
        return true;
    }

    public static List<Department> getListBySearch(String departmentNameSearch) {
        try {
            List<Department> lst = null;
            // Get Session object
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                // Query table Cart
                String hql = "FROM Department r Where r.departmentName like :departmentname";
                Query query = session.createQuery(hql);
                query.setParameter("departmentname", "%" + departmentNameSearch + "%");

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
