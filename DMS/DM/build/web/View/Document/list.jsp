<%-- 
    Document   : list
    Created on : Feb 28, 2016, 6:43:53 PM
    Author     : dunglt2603
--%>

<%@page import="model.pojo.Category"%>
<%@page import="model.pojo.Document"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="sp-maincol" class="clearfix">
    <div id="sp-document-top" class="clearfix">
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
                            <form name="searchForm" method="get" action="document?action=search">
                                <input type="hidden" class="inputbox" style="width: 85%" name="action" id="action" value="search">
                                <table>
                                    <tr>
                                        <td width="30%">Tên tài liệu</td>
                                        <%
                                            String documentNameSearch = (String) request.getSession().getAttribute("documentnamesearch");
                                            if (documentNameSearch != null) {
                                        %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="documentnamesearch" id="documentnamesearch" value="<%= documentNameSearch%>"></td>
                                            <%
                                            } else {
                                            %>
                                        <td width="50%"><input type="text" class="inputbox" style="width: 85%" name="documentnamesearch" id="documentnamesearch" value=""></td>
                                            <%
                                                }
                                            %>
                                        <td rowspan="2" style="vertical-align: central;"><input class="btn-search"  style="height: 30px;width: 50px;" type="submit" value="Tìm"></td>
                                    </tr>
                                    <tr>
                                        <td width="30%" style="padding-top: 10px;">Số văn bản</td>
                                        <%
                                            String fullNameSearch = (String) request.getSession().getAttribute("fullnamesearch");
                                            if (fullNameSearch != null) {
                                        %>
                                        <td width="50%" style="padding-top: 10px;"><input type="text" class="inputbox" style="width: 85%" name="fullnamesearch" id="fullnamesearch" value="<%= fullNameSearch%>"></td>
                                            <%
                                            } else {
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
            <a class="btn-backtoshop" href="document?action=create">
                Thêm Document      </a>
        </div>
        <table class="jshop cart cart-table">
            <tbody>
                <tr>
                    <th>STT</th>
                    <th>Loại văn bản</th>
                    <th>Số văn bản</th>
                    <th>Tên văn bản</th>
                    <th>Trạng thái</th>
                    <th width="15%">Sửa</th>
                    <th width="15%">Xóa</th>
                </tr>
                <%
                    List<Document> lstDocument = (List<Document>) request.getSession().getAttribute("Documents");
                    if (lstDocument != null) {
                        for (int i = 0; i < lstDocument.size(); i++) {
                            Document documentItem = lstDocument.get(i);
                            if (i % 2 == 1) {
                %>
                <tr class="jshop_prod_cart odd">
                    <td><%= (i + 1)%></td>
                    <td>
                        <%
                            if (documentItem.getCategory() != null) {
                                Category r = documentItem.getCategory();
                        %>
                        <%= r.getCategoryName()%>     
                        <% }
                        %>
                    </td>
                    <td>
                        <%= documentItem.getDocumentNo()%>
                    </td>
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= documentItem.getDocumentName()%>
                    </td>
                    <td style="text-align:left">
                        <%= "Ngày ban hành: " + documentItem.getDateIssued() + "<br/>" + "Ngày ký: " + documentItem.getSigner()%>                       
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="document?action=edit&documentid=<%= documentItem.getDocumentId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="document?action=delete&documentid=<%= documentItem.getDocumentId()%>">Xóa</a></div>               
                    </td>
                </tr>
                <%
                } else {
                %>
                <tr class="jshop_prod_cart even">
                    <td><%= (i + 1)%></td>
                    <td>
                        <%
                            if (documentItem.getCategory() != null) {
                                Category r = documentItem.getCategory();
                        %>
                        <%= r.getCategoryName()%>     
                        <% }
                        %>
                    </td>
                    <td>
                        <%= documentItem.getDocumentNo()%>
                    </td>
                    <td class="jshop_img_description_center" style="text-align: center;">
                        <%= documentItem.getDocumentName()%>
                    </td>
                    <td style="text-align:left">
                        <%= "Ngày ban hành: " + documentItem.getDateIssued() + "<br/>" + "Ngày ký: " + documentItem.getSigner()%>                       
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="document?action=edit&documentid=<%= documentItem.getDocumentId()%>">Chỉnh sửa</a></div>                    
                    </td>
                    <td style="text-align:left">
                        <div class="jshop-actions clearfix" style="border-top: 0; margin-top: 0px; padding: 0;"><a class="btn-backtoshop" href="document?action=delete&documentid=<%= documentItem.getDocumentId()%>">Xóa</a></div>               
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




