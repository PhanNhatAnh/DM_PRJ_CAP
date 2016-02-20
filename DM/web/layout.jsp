<%-- 
    Document   : default
    Created on : Feb 19, 2016, 10:04:14 PM
    Author     : Aking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
        <title>
            Document Manager
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

        <!-- Use font-awesome lib-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <!-- Custom css-->
        <link rel="stylesheet" href="lib/css/custom.css">

        <!-- Use chosen lib-->
        <link rel="stylesheet" href="lib/chosen/chosen.css">
        <link rel="stylesheet" href="lib/chosen/docsupport/prism.css">

        <!-- Use boostrap datepicker lib-->
        <link rel="stylesheet" href="lib/bootstrap-3.3.6-dist/datepicker/css/bootstrap-datepicker.css">

        <!-- User jquery-->
        <script src="lib/js/jquery-1.12.0.min.js"></script>
        <script src="lib/chosen/chosen.jquery.js" type="text/javascript"></script>
        <script src="lib/bootstrap-3.3.6-dist/datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </head>
    <body class="skin-blue">   
        <div class="container ct">
            
            <jsp:include page="Layout\header.jsp"/>
            <div class="content">
                <div class="span12">
                    <jsp:include page="${requestScope.PAGE}"/>
                </div> <!-- End .span12 -->
            </div> <!-- End .content -->
            <div class="footer">
                <jsp:include page="Layout\footer.jsp"/>
                
            </div> <!-- End .footer -->
        </div> <!-- End .container -->
    </body>
</html>
