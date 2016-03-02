<%-- 
    Document   : list
    Created on : Feb 28, 2016, 6:43:10 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Role"%>
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
                            <form name="searchForm" method="get" action="role?action=search">
                                <input type="hidden" class="inputbox" style="width: 85%" name="action" id="action" value="search">
                                <table>
                                    <tr>
                                        <td width="20%">Tên quyền</td>
                                        <%
                                            String roleNameSearch = (String) request.getSession().getAttribute("rolenamesearch");
                                            if (roleNameSearch != null) {
                                        %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="rolenamesearch" id="rolenamesearch" value="<%= roleNameSearch%>"></td>
                                            <%
                                            } else {
                                            %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="rolenamesearch" id="rolenamesearch" value=""></td>
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
            <a class="btn-backtoshop" href="role?action=create">
                Thêm Quyền      </a>
        </div>
        <table class="jshop cart cart-table">
            <tbody>
                <tr>
                    <th width="10%">Mã Quyền</th>
                    <th width="60%">Tên Quyền</th>
                    <th width="15%">Sửa</th>
                    <th width="15%">Xóa</th>
                </tr>
                <%
                    List<Role> lstRole = (List<Role>) request.getSession().getAttribute("Roles");
                    if (lstRole != null) {
                        for (int i = 0; i < lstRole.size(); i++) {
                            Role roleItem = lstRole.get(i);
                            if (i % 2 == 1) {
                %>
                <tr class="jshop_prod_cart odd">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= roleItem.getRoleId()%>
                    </td>
                    <td style="text-align:left">
                        <%= roleItem.getRoleName()%>                       
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="role?action=edit&roleid=<%= roleItem.getRoleId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="role?action=delete&roleid=<%= roleItem.getRoleId()%>">Xóa</a></div>               
                    </td>
                </tr>
                <%
                } else {
                %>
                <tr class="jshop_prod_cart even">
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= roleItem.getRoleId()%>
                    </td>
                    <td style="text-align:left">
                        <%= roleItem.getRoleName()%>                       
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="role?action=edit&roleid=<%= roleItem.getRoleId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="role?action=delete&roleid=<%= roleItem.getRoleId()%>">Xóa</a></div>               
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
