<%-- 
    Document   : list
    Created on : Feb 28, 2016, 6:44:23 PM
    Author     : dunglt2603
--%>

<%@page import="java.util.List"%>
<%@page import="model.pojo.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="sp-maincol" class="clearfix">
    <div id="sp-user-top" class="clearfix">
        <div class="sp-inner clearfix">
            <div class="module">
                <div class="mod-wrapper clearfix">
                    <h3 class="header">			
                        <%
                            String title = (String) request.getSession().getAttribute("Title");
                            if (title == null) {
                        %>
                        <span>TRANG CHỦ</span>
                        <%
                        } else {
                        %>
                        <span><%= title%></span>
                        <%
                            }
                        %>
                    </h3>
                    <h1 class="jshop-pagetitle">Tìm kiếm</h1>
                    <div class="mod-content clearfix">
                        <div class="mod-inner clearfix">
                            <form name="searchForm" method="get" action="category?action=search">
                                <input type="hidden" class="inputbox" style="width: 85%" name="action" id="action" value="search">
                                <table>
                                    <tr>
                                        <td width="30%">Tên danh mục</td>
                                        <%
                                            String categoryNameSearch = (String) request.getSession().getAttribute("categorynamesearch");
                                            if (categoryNameSearch != null) {
                                        %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="categorynamesearch" id="categorynamesearch" value="<%= categoryNameSearch%>"></td>
                                            <%
                                            } else {
                                            %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="categorynamesearch" id="categorynamesearch" value=""></td>
                                            <%
                                                }
                                            %>
                                        <td>
                                            <input class="btn-search"  style="height: 30px;width: 50px;" type="submit" value="Tìm">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="gap"></div>
        </div>
    </div>
    <div class="clr"></div>
    <div id="inner_content" class="clearfix">
        <!--Component Area-->
        <div class="jshop-actions clearfix" id="checkout">
            <a class="btn-backtoshop" href="category?action=create">
                Thêm Danh Mục      </a>
        </div>
        <table class="jshop cart cart-table">
            <tbody>
                <tr>
                    <th>Mã Danh Mục</th>
                    <th>Tên Danh Mục</th>
                    <th width="15%">Sửa</th>
                    <th width="15%">Xóa</th>
                </tr>
                <%
                    List<Category> lstCategory = (List<Category>) request.getSession().getAttribute("Categorys");
                    if (lstCategory != null) {
                        for (int i = 0; i < lstCategory.size(); i++) {
                            Category categoryItem = lstCategory.get(i);
                            if (i % 2 == 1) {
                %>
                <tr class="jshop_prod_cart odd">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= categoryItem.getCategoryId()%>
                    </td>
                    <td style="text-align:left">
                        <%= categoryItem.getCategoryName()%>                       
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="category?action=edit&categoryid=<%= categoryItem.getCategoryId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="category?action=delete&categoryid=<%= categoryItem.getCategoryId()%>">Xóa</a></div>               
                    </td>
                </tr>
                <%
                } else {
                %>
                <tr class="jshop_prod_cart even">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= categoryItem.getCategoryId()%>
                    </td>
                    <td style="text-align:left">
                        <%= categoryItem.getCategoryName()%>                       
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="category?action=edit&categoryid=<%= categoryItem.getCategoryId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="category?action=delete&categoryid=<%= categoryItem.getCategoryId()%>">Xóa</a></div>               
                    </td>
                </tr> 
                <%
                            }
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
    <div class="clr"></div>
</div>