<?php
$plugin_path = '/UserManager/';
?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
        <title>
            ABC site
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

        <!-- Use font-awesome lib-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <!-- Custom css-->
        <link rel="stylesheet" href="css/custom.css">

        <!-- Use chosen lib-->
        <link rel="stylesheet" href="chosen/chosen.css">
        <link rel="stylesheet" href="chosen/docsupport/prism.css">

        <!-- Use boostrap datepicker lib-->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/datepicker/css/bootstrap-datepicker.css">

        <!-- User jquery-->
        <script src="js/jquery-1.12.0.min.js"></script>
        <script src="chosen/chosen.jquery.js" type="text/javascript"></script>
        <script src="bootstrap-3.3.6-dist/datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </head>
    <body class="skin-blue">   
        <div class="container ct">
            <div class="banner">
                <img src="img/banner_lvv.png"/>
            </div>
            <nav class="navbar navbar-default">
                <div class="container-fluid  main-nav">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="?page=home">Trang chủ</a></li>
                        <li><a href="?page=404">Hướng dẫn sử dụng</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class=""><a href="?page=home">Đăng nhập</a></li>
                    </ul>
                </div> <!-- End .container-fluid  main-nav -->
            </nav> <!-- End .navbar -->
            <div class="content">
                <div class="span12">
                    <?php
                    /**
                     * Đoạn code này để switch giữa các page, dùng php thuần chứ không phải framework nên phải tự switch
                     * Nếu a dùng framework thì chắc là có template chung, nó giống như file này và chỗ này chính là để load content của các page
                     */
                    require $_GET['page'] . '.php';
                    ?>
                </div> <!-- End .span12 -->
            </div> <!-- End .content -->
            <div class="footer">
                <div class="alert alert-info">
                    © 2016 thuongnc
                </div> <!-- End .alert -->
            </div> <!-- End .footer -->
        </div> <!-- End .container -->
    </body>
</html>
