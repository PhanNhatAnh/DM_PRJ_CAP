<%-- 
    Document   : login
    Created on : Feb 21, 2016, 9:28:19 PM
    Author     : Aking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Document Manager</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

        <!-- Use font-awesome lib-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">



    </head>
    <body>
        <div class="row">
            <div class="container">
                <a href="#">
                    <h3>Document Manager</h3>
                </a>
            </div>
        </div>
        <!-- End .row -->

        <!-- Content -->
        <div class="container" style="min-height: 500px;">
            <div class="row col-sm-12">
                <div class="col-sm-8"><img src="" style="width:100%;height:100%;" />
                </div>
                <div class="col-sm-4">
                    <div class="box box-success box-solid">
                        <div class="box-header">
                            <h3 class="box-title">Login</h3>
                        </div>
                        <form action="login" class="form-horizontal" method="post">
                            <div class="box-body">          
                                <div class="form-group">              
                                    <div class="col-sm-12">
                                        <input type="text" name="username" class="form-control" placeholder="Username">
                                    </div>
                                </div>
                                <div class="form-group">              
                                    <div class="col-sm-12">
                                        <input type="password" name="password" class="form-control" placeholder="Password">
                                    </div>
                                </div>
                                <div class="box-footer">
                                    <input type="reset" class="btn btn-default col-sm-6" value="Cancel" />         
                                    <input type="submit" name="Login" value="Login" class="btn btn-primary col-sm-6" value="Login" />
                                </div>         
                            </div>
                        </form>
                    </div>
                </div>
            </div>   
        </div>
    </body>
</html>