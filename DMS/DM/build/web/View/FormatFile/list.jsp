<%-- 
    Document   : list
    Created on : Feb 28, 2016, 6:43:32 PM
    Author     : dunglt2603
--%>

<%@page import="java.util.List"%>
<%@page import="model.pojo.FormatFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="jshop cart cart-table">
    <tbody>
        <tr>
            <th>Mã Phòng Ban</th>
            <th>Tên Phòng Ban</th>
        </tr>
        <%
            List<FormatFile> lstCategory = (List<FormatFile>) request.getSession().getAttribute("FormatFiles");
            for (int i = 0; i < lstCategory.size(); i++) {
                FormatFile formatItem = lstCategory.get(i);
                if (i % 2 == 1) {
        %>
        <tr class="jshop_prod_cart odd">
            <td class="jshop_img_description_center" style="text-align: center;">
                <%= formatItem.getFormartFileId()%>
            </td>
            <td style="text-align:left">
                <%= formatItem.getFormartFileName()%>                       
            </td>
        </tr>
        <%
        } else {
        %>
        <tr class="jshop_prod_cart even">
            <td class="jshop_img_description_center" style="text-align: center;">
                <%= formatItem.getFormartFileId()%>
            </td>
            <td style="text-align:left">
                <%= formatItem.getFormartFileName()%>                       
            </td>
        </tr> 
        <%
                }
            }
        %>
    </tbody>
</table>