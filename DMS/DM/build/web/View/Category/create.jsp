<%-- 
    Document   : create
    Created on : Mar 1, 2016, 8:50:35 PM
    Author     : dunglt2603
--%>

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
                    <div class="mod-content clearfix">
                        <div class="mod-inner clearfix">
                            <form name="searchForm" method="post" action="category?action=create">
                                <table>
                                    <tr>
                                        <td width="30%" style="text-align: right; padding-right: 10px;">Tên danh mục</td>
                                        <td width="40%"><input type="text" class="inputbox" style="width: 85%" name="categoryname" id="categoryname" value=""></td>
                                        <td width="20%">
                                            <input class="btn-search"  style="height: 30px;width: 100px;" type="submit" value="Thêm mới">
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
    <% if (request.getSession().getAttribute("ErrorMessage") != null) { %>
        $.notify('<%= request.getSession().getAttribute("ErrorMessage") %>', {
            style: 'bootstrap',
            className: 'error'
        });
    <% }
    %>
</script>
