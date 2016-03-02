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
import model.dao.RoleDao;
import model.dao.UserDao;
import model.pojo.Department;
import model.pojo.Role;
import model.pojo.Users;

/**
 *
 * @author dunglt2603
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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
            } else if (request.getParameter("action").equals("list")) {
                List<Users> lstUsers = UserDao.getList();
                request.getSession().setAttribute("Users", lstUsers);
                request.getSession().setAttribute("Title", "DANH SÁCH TÀI KHOẢN TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=User&page=list");
            } else if ("create".equals(request.getParameter("action"))) {
                List<Role> lstRole = RoleDao.getList();
                List<Department> lstUser = DepartmentDao.getList();
                request.getSession().setAttribute("Title", "Thêm Tài Khoản");
                request.getSession().setAttribute("ListRole", lstRole);
                request.getSession().setAttribute("ListUser", lstUser);
                response.sendRedirect("index.jsp?view=User&page=create");
            } else if ("edit".equals(request.getParameter("action"))) {
                int userid = Integer.parseInt(request.getParameter("userid"));
                Users itemEdit = UserDao.getById(userid);
                if (itemEdit != null) {
                    List<Role> lstRole = RoleDao.getList();
                    List<Department> lstDepartment = DepartmentDao.getList();
                    request.getSession().setAttribute("Title", "CHỈNH SỬA TÀI KHOẢN");
                    request.getSession().setAttribute("Users", itemEdit);
                    request.getSession().setAttribute("ListRole", lstRole);
                    request.getSession().setAttribute("ListDepartment", lstDepartment);
                    response.sendRedirect("index.jsp?view=User&page=edit");
                } else {
                    List<Users> lstUsers = UserDao.getList();
                    request.getSession().setAttribute("Userss", lstUsers);
                    request.getSession().setAttribute("Title", "DANH SÁCH TÀI KHOẢN TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=User&page=list");
                }
            } else if ("delete".equals(request.getParameter("action"))) {
                int userId = Integer.parseInt(request.getParameter("userid"));
                Users itemDelete = UserDao.getById(userId);
                if (itemDelete != null) {
                    request.getSession().setAttribute("Title", "XÓA TÀI KHOẢN");
                    request.getSession().setAttribute("Users", itemDelete);
                    response.sendRedirect("index.jsp?view=User&page=delete");
                } else {
                    List<Users> lstUsers = UserDao.getList();
                    request.getSession().setAttribute("Userss", lstUsers);
                    request.getSession().setAttribute("Title", "DANH SÁCH TÀI KHOẢN TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=User&page=list");
                }
            } else if (request.getParameter("action").equals("search")) {
                String userNameSearch = request.getParameter("usernamesearch");
                String fullNameSearch = request.getParameter("fullnamesearch");
                List<Users> lstUsers = UserDao.getListBySearch(userNameSearch, fullNameSearch);
                request.getSession().setAttribute("Users", lstUsers);
                request.getSession().setAttribute("usernamesearch", userNameSearch);
                request.getSession().setAttribute("fullnamesearch", fullNameSearch);
                request.getSession().setAttribute("Title", "DANH SÁCH TÀI KHOẢN TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=User&page=list");
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
                    String userName = request.getParameter("username");
                    //Check exist
                    if (UserDao.CheckExistUser(userName)) {
                        request.getSession().setAttribute("Title", "Thêm Tài Khoản");
                        request.getSession().setAttribute("ErrorMessage", "Tên tài khoản đã tồn tại");
                        response.sendRedirect("index.jsp?view=User&page=create");
                    } else {
                        //Add new user
                        String passWord = request.getParameter("password");
                        String repassWord = request.getParameter("repassword");
                        String firstandlastname = request.getParameter("firstandlastname");
                        int roleId = Integer.parseInt(request.getParameter("roleid"));
                        int departmentId = Integer.parseInt(request.getParameter("departmentid"));
                        Users u = new Users();
                        u.setUserName(userName);
                        u.setPassword(passWord);
                        u.setFirstAndLastName(firstandlastname);

                        if (roleId > 0) {
                            Role r = RoleDao.getById(roleId);
                            u.setRole(r);
                        } else {
                            u.setRole(null);
                        }

                        if (departmentId > 0) {
                            Department d = DepartmentDao.getById(departmentId);
                            u.setDepartment(d);
                        } else {
                            u.setDepartment(null);
                        }

                        if (UserDao.addUser(u) == true) {
                            List<Users> lstUser = UserDao.getList();
                            request.getSession().setAttribute("Users", lstUser);
                            request.getSession().setAttribute("Title", "DANH SÁCH TÀI KHOẢN TRÊN CHƯƠNG TRÌNH");
                            response.sendRedirect("index.jsp?view=User&page=list");
                        } else {
                            request.getSession().setAttribute("Title", "Thêm Tài Khoản");
                            request.getSession().setAttribute("ErrorMessage", "Thêm tài khoản không thành công!");
                            response.sendRedirect("index.jsp?view=User&page=create");
                        }
                    }
                    break;
                }
                case "edit": {
                    int userId = Integer.parseInt(request.getParameter("userid"));
                    String userName = request.getParameter("username");
                    String passWord = request.getParameter("password");
                    String repassWord = request.getParameter("repassword");
                    String firstandlastname = request.getParameter("firstandlastname");
                    int roleId = Integer.parseInt(request.getParameter("roleid"));
                    int departmentId = Integer.parseInt(request.getParameter("departmentid"));
                    Users u = UserDao.getById(userId);
                    u.setUserName(userName);
                    u.setPassword(passWord);
                    u.setFirstAndLastName(firstandlastname);
                    Users itemOld = UserDao.getById(userId);
                    Users itemNew = UserDao.getByName(userName);
                    if (itemNew != null && itemNew.getUserId() != itemOld.getUserId() && itemNew.getUserName().equals(userName)) {
                        request.getSession().setAttribute("Title", "CHỈNH SỬA TÀI KHOẢN");
                        request.getSession().setAttribute("ErrorMessage", "Tên tài khoản đã tồn tại");
                        request.getSession().setAttribute("User", u);
                        response.sendRedirect("index.jsp?view=User&page=edit");
                    } else if (!passWord.equals(repassWord)) {
                        request.getSession().setAttribute("Title", "CHỈNH SỬA TÀI KHOẢN");
                        request.getSession().setAttribute("ErrorMessage", "Mật khẩu không giống nhau");
                        request.getSession().setAttribute("User", u);
                        response.sendRedirect("index.jsp?view=User&page=edit");
                    } else {
                        if (roleId > 0) {
                            Role r = RoleDao.getById(roleId);
                            u.setRole(r);
                        } else {
                            u.setRole(null);
                        }

                        if (departmentId > 0) {
                            Department d = DepartmentDao.getById(departmentId);
                            u.setDepartment(d);
                        } else {
                            u.setDepartment(null);
                        }

                        if (UserDao.Update(u) == true) {
                            List<Users> lstUser = UserDao.getList();
                            request.getSession().setAttribute("Users", lstUser);
                            request.getSession().setAttribute("Title", "DANH SÁCH TÀI KHOẢN TRÊN CHƯƠNG TRÌNH");
                            response.sendRedirect("index.jsp?view=User&page=list");
                        } else {
                            request.getSession().setAttribute("Title", "CHỈNH SỬA TÀI KHOẢN");
                            request.getSession().setAttribute("ErrorMessage", "Điều chỉnh tài khoản không thành công!");
                            request.getSession().setAttribute("User", u);
                            response.sendRedirect("index.jsp?view=User&page=edit");
                        }
                    }
                    break;
                }
                case "delete": {
                    int userId = Integer.parseInt(request.getParameter("userid"));
                    Users itemDelete = UserDao.getById(userId);
                    if (itemDelete != null) {
                        UserDao.DeleteUser(itemDelete);
                        List<Users> lstUser = UserDao.getList();
                        request.getSession().setAttribute("Users", lstUser);
                        request.getSession().setAttribute("Title", "DANH SÁCH TÀI KHOẢN TRÊN CHƯƠNG TRÌNH");
                        response.sendRedirect("index.jsp?view=User&page=list");
                    } else {
                        request.getSession().setAttribute("Title", "XÓA TÀI KHOẢN");
                        request.getSession().setAttribute("ErrorMessage", "Xóa tài khoản không thành công!");
                        request.getSession().setAttribute("User", itemDelete);
                        response.sendRedirect("index.jsp?view=User&page=delete");
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
