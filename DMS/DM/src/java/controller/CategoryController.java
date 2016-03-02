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
import model.dao.CategoryDao;
import model.pojo.Category;

/**
 *
 * @author dunglt2603
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

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
                List<Category> lstCategory = CategoryDao.getList();
                request.getSession().setAttribute("Categorys", lstCategory);
                request.getSession().setAttribute("Title", "DANH SÁCH DANH MỤC TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Category&page=list");
            } else if ("create".equals(request.getParameter("action"))) {
                request.getSession().setAttribute("Title", "Thêm Danh Mục");
                response.sendRedirect("index.jsp?view=Category&page=create");
            } else if ("edit".equals(request.getParameter("action"))) {
                int categoryId = Integer.parseInt(request.getParameter("categoryid"));
                Category itemEdit = CategoryDao.getById(categoryId);
                if (itemEdit != null) {
                    request.getSession().setAttribute("Title", "CHỈNH SỬA DANH MỤC");
                    request.getSession().setAttribute("Category", itemEdit);
                    response.sendRedirect("index.jsp?view=Category&page=edit");
                } else {
                    List<Category> lstCategory = CategoryDao.getList();
                    request.getSession().setAttribute("Categorys", lstCategory);
                    request.getSession().setAttribute("Title", "DANH SÁCH DANH MỤC TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Category&page=list");
                }
            } else if ("delete".equals(request.getParameter("action"))) {
                int categoryId = Integer.parseInt(request.getParameter("categoryid"));
                Category itemDelete = CategoryDao.getById(categoryId);
                if (itemDelete != null) {
                    request.getSession().setAttribute("Title", "XÓA DANH MỤC");
                    request.getSession().setAttribute("Category", itemDelete);
                    response.sendRedirect("index.jsp?view=Category&page=delete");
                } else {
                    List<Category> lstCategory = CategoryDao.getList();
                    request.getSession().setAttribute("Categorys", lstCategory);
                    request.getSession().setAttribute("Title", "DANH SÁCH DANH MỤC TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Category&page=list");
                }
            }else if (request.getParameter("action").equals("search")) {
                String categoryNameSearch = request.getParameter("categorynamesearch");
                List<Category> lstCategory = CategoryDao.getListBySearch(categoryNameSearch);
                request.getSession().setAttribute("categorynamesearch", categoryNameSearch);
                request.getSession().setAttribute("Categorys", lstCategory);
                request.getSession().setAttribute("Title", "DANH SÁCH DANH MỤC TRÊN CHƯƠNG TRÌNH");
                response.sendRedirect("index.jsp?view=Category&page=list");
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
                String categoryName = request.getParameter("categoryname");
                //Check exist
                if (CategoryDao.CheckExistCategory(categoryName)) {
                    request.getSession().setAttribute("Title", "Thêm Danh Mục");
                    request.getSession().setAttribute("ErrorMessage", "Tên danh mục đã tồn tại");
                    response.sendRedirect("index.jsp?view=Category&page=create");
                } else {
                    //Add new category
                    Category r = new Category();
                    r.setCategoryName(categoryName);
                    if (CategoryDao.addCategory(r) == true) {
                        List<Category> lstCategory = CategoryDao.getList();
                        request.getSession().setAttribute("Categorys", lstCategory);
                        request.getSession().setAttribute("Title", "DANH SÁCH DANH MỤC TRÊN CHƯƠNG TRÌNH");
                        response.sendRedirect("index.jsp?view=Category&page=list");
                    } else {
                        request.getSession().setAttribute("Title", "Thêm Danh Mục");
                        request.getSession().setAttribute("ErrorMessage", "Thêm danh mục không thành công!");
                        response.sendRedirect("index.jsp?view=Category&page=create");
                    }
                }       break;
                }
            case "edit":{
                int categoryId = Integer.parseInt(request.getParameter("categoryid"));
                String categoryName = request.getParameter("categoryname");
                Category itemOld = CategoryDao.getById(categoryId);
                Category itemNew = CategoryDao.getByName(categoryName);
                if (itemNew != null && itemNew.getCategoryId() != itemOld.getCategoryId() && itemNew.getCategoryName().equals(categoryName)) {
                    request.getSession().setAttribute("Title", "CHỈNH SỬA DANH MỤC");
                    request.getSession().setAttribute("ErrorMessage", "Tên danh mục đã tồn tại");
                    request.getSession().setAttribute("Category", new Category(categoryId, categoryName));
                    response.sendRedirect("index.jsp?view=Category&page=edit");
                } else if (CategoryDao.Update(new Category(categoryId, categoryName)) == true) {
                    List<Category> lstCategory = CategoryDao.getList();
                    request.getSession().setAttribute("Categorys", lstCategory);
                    request.getSession().setAttribute("Title", "DANH SÁCH DANH MỤC TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Category&page=list");
                } else {
                    request.getSession().setAttribute("Title", "CHỈNH SỬA DANH MỤC");
                    request.getSession().setAttribute("ErrorMessage", "Điều chỉnh danh mục không thành công!");
                    request.getSession().setAttribute("Category", new Category(categoryId, categoryName));
                    response.sendRedirect("index.jsp?view=Category&page=edit");
                }       break;
                }
            case "delete":{
                int categoryId = Integer.parseInt(request.getParameter("categoryid"));
                Category itemDelete = CategoryDao.getById(categoryId);
                if (itemDelete != null) {
                    CategoryDao.DeleteCategory(itemDelete);
                    List<Category> lstCategory = CategoryDao.getList();
                    request.getSession().setAttribute("Categorys", lstCategory);
                    request.getSession().setAttribute("Title", "DANH SÁCH DANH MỤC TRÊN CHƯƠNG TRÌNH");
                    response.sendRedirect("index.jsp?view=Category&page=list");
                } else {
                    request.getSession().setAttribute("Title", "XÓA DANH MỤC");
                    request.getSession().setAttribute("ErrorMessage", "Xóa danh mục không thành công!");
                    request.getSession().setAttribute("Category", itemDelete);
                    response.sendRedirect("index.jsp?view=Category&page=delete");
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
