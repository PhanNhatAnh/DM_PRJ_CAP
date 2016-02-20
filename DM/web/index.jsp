<%-- 
    Document   : index
    Created on : Feb 19, 2016, 11:12:51 PM
    Author     : Aking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <jsp:include page="lib.jsp"/>
    <body class="skin-blue">   
        <div class="container ct">
            
            <jsp:include page="header.jsp"/>
            <div class="content">
                <div class="span12">
                    <jsp:include page="home.jsp"/>
                </div> <!-- End .span12 -->
            </div> <!-- End .content -->
            <div class="footer">
                <jsp:include page="footer.jsp"/>
                
            </div> <!-- End .footer -->
        </div> <!-- End .container -->
    </body>
</html>
