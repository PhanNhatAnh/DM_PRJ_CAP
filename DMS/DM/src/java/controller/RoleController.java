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
import model.dao.RoleDao;
import model.pojo.Role;

/**
 *
 * @author dunglt2603
 */
@WebServlet(name = "RoleController", urlPatterns = {"/RoleController"})
public class RoleController extends HttpServlet {

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
                List<Role> lstRole = RoleDao.getList();
                request.getSession().setAttribute("Roles", lstRole);
                request.getSession().setAttribute("Title", "DANH SÁCH QUYỀN TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Role&page=list");
            } else if ("create".equals(request.getParameter("action"))) {
                request.getSession().setAttribute("Title", "Thêm Quyền");
                response.sendRedirect("index.jsp?view=Role&page=create");
            } else if ("edit".equals(request.getParameter("action"))) {
                int roleId = Integer.parseInt(request.getParameter("roleid"));
                Role itemEdit = RoleDao.getById(roleId);
                if (itemEdit != null) {
                    request.getSession().setAttribute("Title", "CHỈNH SỬA QUYỀN");
                    request.getSession().setAttribute("Role", itemEdit);
                    response.sendRedirect("index.jsp?view=Role&page=edit");
                } else {
                    List<Role> lstRole = RoleDao.getList();
                    request.getSession().setAttribute("Roles", lstRole);
                    request.getSession().setAttribute("Title", "DANH SÁCH QUYỀN TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Role&page=list");
                }
            } else if ("delete".equals(request.getParameter("action"))) {
                int roleId = Integer.parseInt(request.getParameter("roleid"));
                Role itemDelete = RoleDao.getById(roleId);
                if (itemDelete != null) {
                    request.getSession().setAttribute("Title", "XÓA QUYỀN");
                    request.getSession().setAttribute("Role", itemDelete);
                    response.sendRedirect("index.jsp?view=Role&page=delete");
                } else {
                    List<Role> lstRole = RoleDao.getList();
                    request.getSession().setAttribute("Roles", lstRole);
                    request.getSession().setAttribute("Title", "DANH SÁCH QUYỀN TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Role&page=list");
                }
            } else if (request.getParameter("action").equals("search")) {
                String roleNameSearch = request.getParameter("rolenamesearch");
                List<Role> lstRole = RoleDao.getListBySearch(roleNameSearch);
                request.getSession().setAttribute("rolenamesearch", roleNameSearch);
                request.getSession().setAttribute("Roles", lstRole);
                request.getSession().setAttribute("Title", "DANH SÁCH QUYỀN TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Role&page=list");
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
        {
            switch (action) {
                case "create": {
                    String roleName = request.getParameter("rolename");
                    //Check exist
                    if (RoleDao.CheckExistRole(roleName)) {
                        request.getSession().setAttribute("Title", "Thêm Quyền");
                        request.getSession().setAttribute("ErrorMessage", "Tên quyền đã tồn tại");
                        response.sendRedirect("index.jsp?view=Role&page=create");
                    } else {
                        //Add new role
                        Role r = new Role();
                        r.setRoleName(roleName);
                        if (RoleDao.addRole(r) == true) {
                            List<Role> lstRole = RoleDao.getList();
                            request.getSession().setAttribute("Roles", lstRole);
                            request.getSession().setAttribute("Title", "DANH SÁCH QUYỀN TRÊN CHƯƠNG TRÌNH");
                            response.sendRedirect("index.jsp?view=Role&page=list");
                        } else {
                            request.getSession().setAttribute("Title", "Thêm Quyền");
                            request.getSession().setAttribute("ErrorMessage", "Thêm quyền không thành công!");
                            response.sendRedirect("index.jsp?view=Role&page=create");
                        }
                    }
                    break;
                }
                case "edit": {
                    int roleId = Integer.parseInt(request.getParameter("roleid"));
                    String roleName = request.getParameter("rolename");
                    Role itemOld = RoleDao.getById(roleId);
                    Role itemNew = RoleDao.getByName(roleName);
                    if (itemNew != null && itemNew.getRoleId() != itemOld.getRoleId() && itemNew.getRoleName().equals(roleName)) {
                        request.getSession().setAttribute("Title", "CHỈNH SỬA QUYỀN");
                        request.getSession().setAttribute("ErrorMessage", "Tên quyền đã tồn tại");
                        request.getSession().setAttribute("Role", new Role(roleId, roleName));
                        response.sendRedirect("index.jsp?view=Role&page=edit");
                    } else if (RoleDao.Update(new Role(roleId, roleName)) == true) {
                        List<Role> lstRole = RoleDao.getList();
                        request.getSession().setAttribute("Roles", lstRole);
                        request.getSession().setAttribute("Title", "DANH SÁCH QUYỀN TRÊN CHƯƠNG TRÌNH");
                        response.sendRedirect("index.jsp?view=Role&page=list");
                    } else {
                        request.getSession().setAttribute("Title", "CHỈNH SỬA QUYỀN");
                        request.getSession().setAttribute("ErrorMessage", "Điều chỉnh quyền không thành công!");
                        request.getSession().setAttribute("Role", new Role(roleId, roleName));
                        response.sendRedirect("index.jsp?view=Role&page=edit");
                    }
                    break;
                }
                case "delete": {
                    int roleId = Integer.parseInt(request.getParameter("roleid"));
                    Role itemDelete = RoleDao.getById(roleId);
                    if (itemDelete != null) {
                        RoleDao.DeleteRole(itemDelete);
                        List<Role> lstRole = RoleDao.getList();
                        request.getSession().setAttribute("Roles", lstRole);
                        request.getSession().setAttribute("Title", "DANH SÁCH QUYỀN TRÊN CHƯƠNG TRÌNH");
                        response.sendRedirect("index.jsp?view=Role&page=list");
                    } else {
                        request.getSession().setAttribute("Title", "XÓA QUYỀN");
                        request.getSession().setAttribute("ErrorMessage", "Xóa quyền không thành công!");
                        request.getSession().setAttribute("Role", itemDelete);
                        response.sendRedirect("index.jsp?view=Role&page=delete");
                    }
                    break;
                }
                default:
                    break;
            }
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
