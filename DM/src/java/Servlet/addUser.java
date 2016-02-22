/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entity.Account;
import Entity.Role;
import Service.AccountService;
import Service.IConstant;
import Service.RoleService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aking
 */
public class addUser extends HttpServlet {

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
            IConstant iConstant = new IConstant();
            String addUser = request.getParameter("addUser");

            RoleService roleService = new RoleService();
            List<Role> roles = roleService.listAllRole();

            if (addUser == null) {
                
                request.setAttribute("ROLES", roles);
                request.setAttribute("PAGE", iConstant.addUserPage);

                RequestDispatcher rd = request.getRequestDispatcher(iConstant.LayoutServlet);
                rd.forward(request, response);
            } else {
                String fullname = request.getParameter("full_name");
                String email = request.getParameter("email");
                String role = request.getParameter("role");

                // Random alphabetic string
                final int PASSWORD_LENGTH = 8;
                StringBuffer sb = new StringBuffer();
                for (int x = 0; x < PASSWORD_LENGTH; x++) {
                    sb.append((char) ((int) (Math.random() * 26) + 97));
                }
                String pass = sb.toString();

                Role r = roleService.findByID(Integer.parseInt(role));
                AccountService accountService = new AccountService();
                Account account = new Account(5, email, pass, fullname);
                account.setRoleID(r);
                accountService.createNewAccount(account);
                
                request.setAttribute("MESSAGE", "Sussecc!!!");
                request.setAttribute("ROLES", roles);
                request.setAttribute("PAGE", iConstant.addUserPage);

                RequestDispatcher rd = request.getRequestDispatcher(iConstant.LayoutServlet);
                rd.forward(request, response);
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
        processRequest(request, response);
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
