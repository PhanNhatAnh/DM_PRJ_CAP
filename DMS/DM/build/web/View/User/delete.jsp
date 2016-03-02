<%-- 
    Document   : delete
    Created on : Feb 28, 2016, 6:42:47 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Department"%>
<%@page import="model.pojo.Role"%>
<%@page import="model.pojo.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<a href="category?action=list" id="actionredirect" style="display: none"></a>
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
                            <form name="searchForm" method="post" action="user?action=delete">
                                <input type="hidden" name="userid" value="<%= r.getUserId()%>"/>
                                <table>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px;">Tên tài khoản</td>
                                        <td width="60%"><input type="text" class="inputbox" style="width: 85%" name="username" id="username" value="<%= r.getUserName()%>" readonly="readonly"></td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Họ tên</td>
                                        <td width="60%" style="padding-top: 10px;"><input type="text" class="inputbox" style="width: 85%" name="firstandlastname" id="firstandlastname" value="<%= r.getFirstAndLastName()%>" readonly="readonly"></td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Quyền</td>
                                        <td width="60%" style="padding-top: 10px;">
                                            <%
                                                Role role = r.getRole();
                                                if (role != null) {
                                            %>
                                            <input type="text" readonly="readonly" class="inputbox" style="width: 85%" name="roleid" id="roleid" value="<%= role.getRoleName()%>">
                                            <%
                                            } else {
                                            %>
                                            <input type="text" readonly="readonly" class="inputbox" style="width: 85%" name="roleid" id="roleid" value="">
                                            <%
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Phòng ban</td>
                                        <td width="60%" style="padding-top: 10px;">
                                            <%
                                                Department department = r.getDepartment();
                                                if (department != null) {
                                            %>
                                            <input type="text" readonly="readonly" class="inputbox" style="width: 85%" name="departmentid" id="departmentid" value="<%= department.getDepartmentName()%>">
                                            <%
                                            } else {
                                            %>
                                            <input type="text" readonly="readonly" class="inputbox" style="width: 85%" name="departmentid" id="departmentid" value="">
                                            <%
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="float: right; padding-top: 10px;">
                                            <input class="btn-search"  style="height: 30px;width: 100px; margin-left: 5px;" type="submit" value="Xác nhận xóa">
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
