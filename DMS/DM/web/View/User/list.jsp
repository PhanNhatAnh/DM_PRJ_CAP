<%-- 
    Document   : list
    Created on : Feb 28, 2016, 6:42:41 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Department"%>
<%@page import="model.pojo.Role"%>
<%@page import="model.pojo.Users"%>
<%@page import="java.util.List"%>
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
                            <form name="searchForm" method="get" action="user?action=search">
                                <input type="hidden" class="inputbox" style="width: 85%" name="action" id="action" value="search">
                                <table>
                                    <tr>
                                        <td width="30%">Tài khoản</td>
                                        <%
                                            String userNameSearch = (String)request.getSession().getAttribute("usernamesearch");
                                            if(userNameSearch != null){
                                        %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="usernamesearch" id="usernamesearch" value="<%= userNameSearch %>"></td>
                                        <%
                                            }else{
                                        %>
                                            <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="usernamesearch" id="usernamesearch" value=""></td>
                                        <% 
                                            }
                                        %>
                                            <td rowspan="2" style="vertical-align: central;"><input class="btn-search"  style="height: 30px;width: 50px;" type="submit" value="Tìm"></td>
                                    </tr>
                                    <tr>
                                        <td width="30%" style="padding-top: 10px;">Họ tên</td>
                                        <%
                                            String fullNameSearch = (String)request.getSession().getAttribute("fullnamesearch");
                                            if(fullNameSearch != null){
                                        %>
                                        <td width="50%" style="padding-top: 10px;"><input type="text" class="inputbox" style="width: 85%" name="fullnamesearch" id="fullnamesearch" value="<%= fullNameSearch %>"></td>
                                        <%
                                            }else{
                                        %>
                                            <td width="50%" style="padding-top: 10px;"><input type="text" class="inputbox" style="width: 85%" name="fullnamesearch" id="fullnamesearch" value=""></td>
                                        <% 
                                            }
                                        %>
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
            <a class="btn-backtoshop" href="user?action=create">
                Thêm User      </a>
        </div>
        <table class="jshop cart cart-table">
            <tbody>
                <tr>
                    <th>Tên đăng nhập</th>
                    <th>Họ và Tên</th>
                    <th>Quyền</th>
                    <th>Phòng Ban</th>
                    <th width="15%">Sửa</th>
                    <th width="15%">Xóa</th>
                </tr>
                <%
                    List<Users> lstUser = (List<Users>) request.getSession().getAttribute("Users");
                    if (lstUser != null) {
                        for (int i = 0; i < lstUser.size(); i++) {
                            Users userItem = lstUser.get(i);
                            if (i % 2 == 1) {
                %>
                <tr class="jshop_prod_cart odd">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= userItem.getUserName()%>
                    </td>
                    <td style="text-align:left">
                        <%= userItem.getFirstAndLastName()%>                       
                    </td>
                    <td>
                        <%
                            if (userItem.getRole() != null) {
                                Role r = userItem.getRole();
                        %>
                        <%= r.getRoleName()%>     
                        <% }
                        %>
                    </td>
                    <td>
                        <%
                            if (userItem.getDepartment() != null) {
                                Department d = userItem.getDepartment();
                        %>
                        <%= d.getDepartmentName()%>     
                        <% }
                        %>
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="user?action=edit&userid=<%= userItem.getUserId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="user?action=delete&userid=<%= userItem.getUserId()%>">Xóa</a></div>               
                    </td>
                </tr>
                <%
                } else {
                %>
                <tr class="jshop_prod_cart even">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= userItem.getUserName()%>
                    </td>
                    <td style="text-align:left">
                        <%= userItem.getFirstAndLastName()%>                       
                    </td>
                    <td>
                        <%
                            if (userItem.getRole() != null) {
                                Role r = userItem.getRole();
                        %>
                        <%= r.getRoleName()%>     
                        <% }
                        %>
                    </td>
                    <td>
                        <%
                            if (userItem.getDepartment() != null) {
                                Department d = userItem.getDepartment();
                        %>
                        <%= d.getDepartmentName()%>     
                        <% }
                        %>
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="user?action=edit&userid=<%= userItem.getUserId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="user?action=delete&userid=<%= userItem.getUserId()%>">Xóa</a></div>               
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
