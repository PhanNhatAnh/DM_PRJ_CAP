<%-- 
    Document   : edit
    Created on : Feb 28, 2016, 6:42:33 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Department"%>
<%@page import="model.pojo.Role"%>
<%@page import="java.util.List"%>
<%@page import="model.pojo.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<a href="user?action=list" id="actionredirect" style="display: none"></a>
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
                    <%
                        Users r = (Users) request.getSession().getAttribute("Users");
                    %>
                    <div class="mod-content clearfix">
                        <div class="mod-inner clearfix">
                            <form name="searchForm" method="post" action="user?action=edit">
                                <input type="hidden" name="userid" value="<%= r.getUserId()%>"/>
                                <table>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px;">Tên tài khoản</td>
                                        <td width="60%"><input type="text" class="inputbox" style="width: 85%" name="username" id="username" value="<%= r.getUserName()%>"></td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Mật khẩu</td>
                                        <td width="60%" style="padding-top: 10px;"><input type="password" class="inputbox" style="width: 85%" name="password" id="password" value="<%= r.getPassword()%>"></td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Xác nhận mật khẩu</td>
                                        <td width="60%" style="padding-top: 10px;"><input type="password" class="inputbox" style="width: 85%" name="repassword" id="repassword" value="<%= r.getPassword()%>"></td>
                                    </tr>
                                     <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Họ tên</td>
                                        <td width="60%" style="padding-top: 10px;"><input type="text" class="inputbox" style="width: 85%" name="firstandlastname" id="firstandlastname" value="<%= r.getFirstAndLastName()%>"></td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Quyền</td>
                                        <td width="60%" style="padding-top: 10px;">
                                            <%
                                                List<Role> lstRole = (List<Role>) request.getSession().getAttribute("ListRole");
                                                if (lstRole != null && lstRole.size() > 0) {
                                            %>
                                            <select id="roleid" name="roleid">
                                                <option value="0">-- Chọn --</option>
                                                <%
                                                    for (int i = 0; i < lstRole.size(); i++) {
                                                        Role item = lstRole.get(i);
                                                        String select = "";
                                                        if (r.getRole() != null && r.getRole().getRoleId() == item.getRoleId()) {
                                                            select = "selected";
                                                        }
                                                %>
                                                <option value="<%= item.getRoleId()%>" <%= select%>><%= item.getRoleName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                            <%
                                            } else {
                                            %>
                                            <select id="roleid" name="roleid">
                                                <option value="0">-- Chọn --</option>
                                            </select>
                                            <%
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Phòng ban</td>
                                        <td width="60%" style="padding-top: 10px;">
                                            <%
                                                List<Department> lstDepartment = (List<Department>) request.getSession().getAttribute("ListDepartment");
                                                if (lstRole != null && lstRole.size() > 0) {
                                            %>
                                            <select id="departmentid" name="departmentid">
                                                <option value="0">-- Chọn --</option>
                                                <%
                                                    for (int i = 0; i < lstDepartment.size(); i++) {
                                                        Department item = lstDepartment.get(i);
                                                        String select = "";
                                                        if (r.getDepartment() != null && r.getDepartment().getDepartmentId() == item.getDepartmentId()) {
                                                            select = "selected";
                                                        }
                                                %>
                                                <option value="<%= item.getDepartmentId()%>" <%= select%>><%= item.getDepartmentName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                            <%
                                            } else {
                                            %>
                                            <select id="departmentId" name="departmentId">
                                                <option value="0">-- Chọn --</option>
                                            </select>
                                            <%
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="float: right; padding-top: 10px;">
                                            <input class="btn-search"  style="height: 30px;width: 100px; margin-left: 5px;" type="submit" value="Cập nhật">
                                        </td>
                                        <td style="text-align: left; padding-top: 10px;">
                                            <input class="btn-search"  style="height: 30px;width: 60px; margin-left: 5px;" type="button" value="Hủy" onclick="Cancel();">
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
</div>
<script type="text/javascript">
    function Cancel()
    {
        $('#actionredirect')[0].click();
    }
    <% if (request.getSession().getAttribute("ErrorMessage") != null) {%>
    $.notify('<%= request.getSession().getAttribute("ErrorMessage")%>', {
        style: 'bootstrap',
        className: 'error'
    });
    <% }
    %>
</script>

