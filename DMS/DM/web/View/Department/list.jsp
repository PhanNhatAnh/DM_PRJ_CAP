<%-- 
    Document   : list
    Created on : Feb 28, 2016, 6:44:05 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Users"%>
<%@page import="org.apache.catalina.User"%>
<%@page import="model.pojo.Department"%>
<%@page import="java.util.List"%>
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
                            <form name="searchForm" method="get" action="department?action=search">
                                <input type="hidden" class="inputbox" style="width: 85%" name="action" id="action" value="search">
                                <table>
                                    <tr>
                                        <td width="30%">Tên phòng ban</td>
                                        <%
                                            String departmentNameSearch = (String) request.getSession().getAttribute("departmentnamesearch");
                                            if (departmentNameSearch != null) {
                                        %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="departmentnamesearch" id="departmentnamesearch" value="<%= departmentNameSearch%>"></td>
                                            <%
                                            } else {
                                            %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="departmentnamesearch" id="categorynamesearch" value=""></td>
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
            <a class="btn-backtoshop" href="department?action=create">
                Thêm Phòng Ban      </a>
        </div>
        <table class="jshop cart cart-table">
            <tbody>
                <tr>
                    <th>Mã Phòng Ban</th>
                    <th>Tên Phòng Ban</th>
                    <th>Trưởng Phòng</th>
                    <th width="15%">Sửa</th>
                    <th width="15%">Xóa</th>
                </tr>
                <%
                    List<Department> lstDepartment = (List<Department>) request.getSession().getAttribute("Departments");
                    if (lstDepartment != null) {
                        for (int i = 0; i < lstDepartment.size(); i++) {
                            Department departmentItem = lstDepartment.get(i);
                            if (i % 2 == 1) {
                %>
                <tr class="jshop_prod_cart odd">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= departmentItem.getDepartmentId()%>
                    </td>
                    <td style="text-align:left">
                        <%= departmentItem.getDepartmentName()%>                       
                    </td>
                    <td style="text-align:left">
                        <%
                            if (departmentItem.getUser() != null) {
                                Users u = departmentItem.getUser();
                        %>
                        <%= u.getFirstAndLastName()%>     
                        <% }
                        %>
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="department?action=edit&departmentid=<%= departmentItem.getDepartmentId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="department?action=delete&departmentid=<%= departmentItem.getDepartmentId()%>">Xóa</a></div>               
                    </td>
                </tr>
                <%
                } else {
                %>
                <tr class="jshop_prod_cart even">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= departmentItem.getDepartmentId()%>
                    </td>
                    <td style="text-align:left">
                        <%= departmentItem.getDepartmentName()%>                       
                    </td>
                    <td style="text-align:left">
                        <%
                            if (departmentItem.getUser() != null) {
                                Users u = departmentItem.getUser();
                        %>
                        <%= u.getFirstAndLastName()%>     
                        <% }
                        %>
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="department?action=edit&departmentid=<%= departmentItem.getDepartmentId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="department?action=delete&departmentid=<%= departmentItem.getDepartmentId()%>">Xóa</a></div>               
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