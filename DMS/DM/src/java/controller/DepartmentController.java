/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DepartmentDao;
import model.dao.UserDao;
import model.pojo.Department;
import model.pojo.Users;

/**
 *
 * @author dunglt2603
 */
@WebServlet(name = "DepartmentController", urlPatterns = {"/DepartmentController"})
public class DepartmentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            RequestDispatcher dispatcher;
            request.getSession().setAttribute("ErrorMessage", null);
            //Get action
            if (request.getParameter("action") == null) {
                request.getSession().setAttribute("Title", "TRANG CHỦ");
                dispatcher = request.getRequestDispatcher("index.jsp?view=home&pape=list.jsp");
                dispatcher.forward(request, response);
            } else if ("list".equals(request.getParameter("action"))) {
                List<Department> lstDepartment = DepartmentDao.getList();
                request.getSession().setAttribute("Departments", lstDepartment);
                request.getSession().setAttribute("Title", "DANH SÁCH PHÒNG BAN TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Department&page=list");
            } else if ("create".equals(request.getParameter("action"))) {
                List<Users> lstUser = UserDao.getList();
                request.getSession().setAttribute("Title", "Thêm Phòng Ban");
                request.getSession().setAttribute("ListUser", lstUser);
                response.sendRedirect("index.jsp?view=Department&page=create");
            } else if ("edit".equals(request.getParameter("action"))) {
                int departmentId = Integer.parseInt(request.getParameter("departmentid"));
                Department itemEdit = DepartmentDao.getById(departmentId);
                if (itemEdit != null) {
                    List<Users> lstUser = UserDao.getList();
                    request.getSession().setAttribute("Title", "CHỈNH SỬA PHÒNG BAN");
                    request.getSession().setAttribute("Department", itemEdit);
                    request.getSession().setAttribute("ListUser", lstUser);
                    response.sendRedirect("index.jsp?view=Department&page=edit");
                } else {
                    List<Department> lstDepartment = DepartmentDao.getList();
                    request.getSession().setAttribute("Departments", lstDepartment);
                    request.getSession().setAttribute("Title", "DANH SÁCH PHÒNG BAN TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Department&page=list");
                }
            } else if ("delete".equals(request.getParameter("action"))) {
                int departmentId = Integer.parseInt(request.getParameter("departmentid"));
                Department itemDelete = DepartmentDao.getById(departmentId);
                if (itemDelete != null) {
                    request.getSession().setAttribute("Title", "XÓA PHÒNG BAN");
                    request.getSession().setAttribute("Department", itemDelete);
                    response.sendRedirect("index.jsp?view=Department&page=delete");
                } else {
                    List<Department> lstDepartment = DepartmentDao.getList();
                    request.getSession().setAttribute("Departments", lstDepartment);
                    request.getSession().setAttribute("Title", "DANH SÁCH PHÒNG BAN TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Department&page=list");
                }
            }else if (request.getParameter("action").equals("search")) {
                String departmentNameSearch = request.getParameter("departmentnamesearch");
                List<Department> lstDepartment = DepartmentDao.getListBySearch(departmentNameSearch);
                request.getSession().setAttribute("departmentnamesearch", departmentNameSearch);
                request.getSession().setAttribute("Departments", lstDepartment);
                request.getSession().setAttribute("Title", "DANH SÁCH PHÒNG BAN TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Department&page=list");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (null != action) //action Create
        switch (action) {
            case "create":{
                String departmentName = request.getParameter("departmentname");
                //Check exist
                if (DepartmentDao.CheckExistDepartment(departmentName)) {
                    request.getSession().setAttribute("Title", "Thêm Phòng Ban");
                    request.getSession().setAttribute("ErrorMessage", "Tên phòng Ban đã tồn tại");
                    response.sendRedirect("index.jsp?view=Department&page=create");
                } else {
                    //Add new department
                    Department r = new Department();
                    r.setDepartmentName(departmentName);
                    int userId = Integer.parseInt(request.getParameter("headdepartment"));
                    
                    if (userId > 0) {
                        Users u = UserDao.getById(userId);
                        r.setUser(u);
                    }
                    
                    if (DepartmentDao.addDepartment(r) == true) {
                        List<Department> lstDepartment = DepartmentDao.getList();
                        request.getSession().setAttribute("Departments", lstDepartment);
                        request.getSession().setAttribute("Title", "DANH SÁCH PHÒNG BAN TRÊN CHƯƠNG TRÌNH");
                        response.sendRedirect("index.jsp?view=Department&page=list");
                    } else {
                        request.getSession().setAttribute("Title", "Thêm Phòng Ban");
                        request.getSession().setAttribute("ErrorMessage", "Thêm phòng Ban không thành công!");
                        response.sendRedirect("index.jsp?view=Department&page=create");
                    }
                }       break;
                }
            case "edit":{
                int departmentId = Integer.parseInt(request.getParameter("departmentid"));
                String departmentName = request.getParameter("departmentname");
                Department itemOld = DepartmentDao.getById(departmentId);
                Department itemNew = DepartmentDao.getByName(departmentName);
                if (itemNew != null && itemNew.getDepartmentId() != itemOld.getDepartmentId() && itemNew.getDepartmentName().equals(departmentName)) {
                    request.getSession().setAttribute("Title", "CHỈNH SỬA PHÒNG BAN");
                    request.getSession().setAttribute("ErrorMessage", "Tên phòng Ban đã tồn tại");
                    request.getSession().setAttribute("Department", new Department(departmentId, departmentName));
                    response.sendRedirect("index.jsp?view=Department&page=edit");
                } else {
                    int headdepartment = Integer.parseInt(request.getParameter("headdepartment"));
                    Department d = DepartmentDao.getById(departmentId);
                    if (headdepartment > 0) {
                        Users u = UserDao.getById(headdepartment);
                        d.setUser(u);
                    }else{
                        d.setUser(null);
                    }
                    if (DepartmentDao.Update(d) == true) {
                        List<Department> lstDepartment = DepartmentDao.getList();
                        request.getSession().setAttribute("Departments", lstDepartment);
                        request.getSession().setAttribute("Title", "DANH SÁCH PHÒNG BAN TRÊN CHƯƠNG TRÌNH");
                        response.sendRedirect("index.jsp?view=Department&page=list");
                    } else {
                        request.getSession().setAttribute("Title", "CHỈNH SỬA PHÒNG BAN");
                        request.getSession().setAttribute("ErrorMessage", "Điều chỉnh phòng Ban không thành công!");
                        request.getSession().setAttribute("Department", new Department(departmentId, departmentName));
                        response.sendRedirect("index.jsp?view=Department&page=edit");
                    }
                }       break;
                }
            case "delete":{
                int departmentId = Integer.parseInt(request.getParameter("departmentid"));
                Department itemDelete = DepartmentDao.getById(departmentId);
                if (itemDelete != null) {
                    DepartmentDao.DeleteDepartment(itemDelete);
                    List<Department> lstDepartment = DepartmentDao.getList();
                    request.getSession().setAttribute("Departments", lstDepartment);
                    request.getSession().setAttribute("Title", "DANH SÁCH PHÒNG BAN TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Department&page=list");
                } else {
                    request.getSession().setAttribute("Title", "XÓA PHÒNG BAN");
                    request.getSession().setAttribute("ErrorMessage", "Xóa phòng Ban không thành công!");
                    request.getSession().setAttribute("Department", itemDelete);
                    response.sendRedirect("index.jsp?view=Department&page=delete");
                }       break;
                }
            default:
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
