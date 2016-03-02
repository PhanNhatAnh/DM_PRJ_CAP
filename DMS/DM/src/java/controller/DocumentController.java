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
import model.dao.DocumentDao;
import model.pojo.Document;

/**
 *
 * @author dunglt2603
 */
@WebServlet(name = "DocumentController", urlPatterns = {"/DocumentController"})
public class DocumentController extends HttpServlet {

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
                List<Document> lstDocument = DocumentDao.getList();
                request.getSession().setAttribute("Documents", lstDocument);
                request.getSession().setAttribute("Title", "DANH SÁCH TÀI LIỆU TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Document&page=list");
            } else if ("create".equals(request.getParameter("action"))) {
                request.getSession().setAttribute("Title", "Thêm Danh Mục");
                response.sendRedirect("index.jsp?view=Document&page=create");
            } else if ("edit".equals(request.getParameter("action"))) {
                int categoryId = Integer.parseInt(request.getParameter("categoryid"));
                Document itemEdit = DocumentDao.getById(categoryId);
                if (itemEdit != null) {
                    request.getSession().setAttribute("Title", "CHỈNH SỬA TÀI LIỆU");
                    request.getSession().setAttribute("Document", itemEdit);
                    response.sendRedirect("index.jsp?view=Document&page=edit");
                } else {
                    List<Document> lstDocument = DocumentDao.getList();
                    request.getSession().setAttribute("Documents", lstDocument);
                    request.getSession().setAttribute("Title", "DANH SÁCH TÀI LIỆU TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Document&page=list");
                }
            } else if ("delete".equals(request.getParameter("action"))) {
                int categoryId = Integer.parseInt(request.getParameter("categoryid"));
                Document itemDelete = DocumentDao.getById(categoryId);
                if (itemDelete != null) {
                    request.getSession().setAttribute("Title", "XÓA TÀI LIỆU");
                    request.getSession().setAttribute("Document", itemDelete);
                    response.sendRedirect("index.jsp?view=Document&page=delete");
                } else {
                    List<Document> lstDocument = DocumentDao.getList();
                    request.getSession().setAttribute("Documents", lstDocument);
                    request.getSession().setAttribute("Title", "DANH SÁCH TÀI LIỆU TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Document&page=list");
                }
            }else if (request.getParameter("action").equals("search")) {
                String categoryNameSearch = request.getParameter("categorynamesearch");
                List<Document> lstDocument = DocumentDao.getListBySearch(categoryNameSearch);
                request.getSession().setAttribute("categorynamesearch", categoryNameSearch);
                request.getSession().setAttribute("Documents", lstDocument);
                request.getSession().setAttribute("Title", "DANH SÁCH TÀI LIỆU TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Document&page=list");
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
