<%-- 
    Document   : index1
    Created on : Mar 1, 2016, 4:11:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="vi-vn" lang="vi-vn" slick-uniqueid="3">
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="Content/css/mimistore.css" type="text/css">
         <link rel="stylesheet" href="Content/css/template_1.css" type="text/css">
         <link rel="stylesheet" href="Content/css/custom_1.css" type="text/css">
         <link rel="stylesheet" href="Content/css/modules.css" type="text/css">
         <link rel="stylesheet" href="Content/css/joomla.css" type="text/css">
         <link rel="stylesheet" href="Content/css/typography.css" type="text/css">
         <link rel="stylesheet" href="Content/css/css3.css" type="text/css">
         <link rel="stylesheet" href="Content/css/style1.css" type="text/css">
         <link rel="stylesheet" href="Content/css/style.css" type="text/css">
         
         <style type="text/css">
            .sp-wrap {width: 960px;}
            #sp-leftcol {width: 23%}
            #sp-rightcol { width: 23%}
            #sp-maincol {width:73%}
            #inner_content {width: 100%;}
            #inset1 {width: 20%}#inset2 { width: 20%}	
            #sp-inset1 {width: 20%}#sp-inset2 { width: 20%}
         </style>
         <script src="Content/js/jquery-1.6.2.min.js" type="text/javascript"></script>
         <script src="Content/js/jquery-1.11.0.min.js"></script>
         <script src="Content/js/notify.js"></script>
   </head>
   <body class="bg clearfix">
         <div class="bg2 clearfix">
            <div class="sp-wrap clearfix">
               <jsp:include page="View/Share/header.jsp"/>
               <div class="main-bg clearfix">
                  <div class="clr"></div>  
                    <jsp:include page="View/Share/nav.jsp"/>
                    <%
                            if (request.getParameter("page") == null) {
                        %>
                            <jsp:include page="View/Home/list.jsp"/>
                        <%                        } else {
                            String view = request.getParameter("view").toString();
                            String pageredirect = request.getParameter("page").toString();
                            String ulr = "View/" + view + "/" + pageredirect + ".jsp";
                        %>  
                            <jsp:include page="<%= ulr%>"/>
                        <%
                            }
                        %>
                  <div class="clr"></div>
                  <!--Module Position mid1 to mid6-->
                  <div id="sp-mid" class="sp-inner clearfix">
                     <div style="width:100%" class="sp-block "></div>
                  </div>
                  <!--Module Position bottom1 to bottom6-->
                  <div id="sp-bottom" class="sp-inner clearfix">
                     <div style="width:100%" class="sp-block ">
                        <div id="bottom1" class="mod-block sp-bottom single">
                           <div class="module">
                              <div class="mod-wrapper-flat clearfix">
                                 <div class="custom">
                                    <noscript>
                                       &lt;div style="display: inline;"&gt;&lt;img height="1" width="1" style="border-style: none;" alt="" src="//googleads.g.doubleclick.net/pagead/viewthroughconversion/993120848/?value=0&amp;amp;guid=ON&amp;amp;script=0" /&gt;&lt;/div&gt;
                                    </noscript>
                                 </div>
                              </div>
                           </div>
                           <div class="gap"></div>
                        </div>
                     </div>
                  </div>
               </div>
               <!--Footer-->
               <jsp:include page="View/Share/footer.jsp"/>
            </div>
         </div>
      </doctype>
   </body>
</html>