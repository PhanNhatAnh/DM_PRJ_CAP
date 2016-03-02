<%-- 
    Document   : delete
    Created on : Feb 28, 2016, 6:43:17 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Category"%>
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
                        Category r = (Category) request.getSession().getAttribute("Category");
                    %>
                    <div class="mod-content clearfix">
                        <div class="mod-inner clearfix">
                            <form name="searchForm" method="post" action="category?action=delete">
                                <input type="hidden" name="categoryid" value="<%= r.getCategoryId()%>"/>
                                <table>
                                    <tr>
                                        <td width="30%" style="text-align: right; padding-right: 10px;">Tên quyền</td>
                                        <td width="40%"><input type="text" readonly="readonly" class="inputbox" style="width: 85%" name="categoryname" id="categoryname" value="<%= r.getCategoryName()%>"></td>
                                        <td width="20%">
                                            <input class="btn-search"  style="height: 30px;width: 100px;" type="submit" value="Xác nhận xóa">
                                        </td>
                                        <td>
                                            <input class="btn-search"  style="height: 30px;width: 50px; margin-left: 5px;" type="button" value="Hủy" onclick="Cancel();">
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

