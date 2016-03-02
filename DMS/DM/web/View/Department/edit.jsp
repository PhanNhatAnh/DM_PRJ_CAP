<%-- 
    Document   : edit
    Created on : Feb 28, 2016, 6:43:03 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Users"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="model.pojo.Department"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<a href="department?action=list" id="actionredirect" style="display: none"></a>
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
                        Department r = (Department) request.getSession().getAttribute("Department");
                    %>
                    <div class="mod-content clearfix">
                        <div class="mod-inner clearfix">
                            <form name="searchForm" method="post" action="department?action=edit">
                                <input type="hidden" name="departmentid" value="<%= r.getDepartmentId()%>"/>
                                <table>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px;">Tên phòng ban</td>
                                        <td width="60%"><input type="text" class="inputbox" style="width: 85%" name="departmentname" id="departmentname" value="<%= r.getDepartmentName() %>"></td>
                                    </tr>
                                    <tr>
                                        <td width="40%" style="text-align: right; padding-right: 10px; padding-top: 10px;">Trưởng phòng</td>
                                        <td width="60%" style="padding-top: 10px;">
                                            <%
                                                List<Users> lstUser = (List<Users>) request.getSession().getAttribute("ListUser");
                                                if (lstUser != null && lstUser.size() > 0) {
                                            %>
                                            <select id="headdepartment" name="headdepartment">
                                                <option value="0">-- Chọn --</option>
                                                <%
                                                    for (int i = 0; i < lstUser.size(); i++) {
                                                        Users item = lstUser.get(i);
                                                        String select = "";
                                                        if(r.getUser() != null && r.getUser().getUserId() == item.getUserId())
                                                            select = "selected";
                                                %>
                                                <option value="<%= item.getUserId()%>" <%= select %>><%= item.getFirstAndLastName() + " (" + item.getUserName() + ")"%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                            <%
                                            } else {
                                            %>
                                            <select id="headdepartment" name="headdepartment">
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
