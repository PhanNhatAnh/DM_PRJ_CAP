<%-- 
    Document   : header
    Created on : Feb 19, 2016, 10:15:03 PM
    Author     : Aking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="banner">
    <img src="lib/img/banner_lvv.png"/>
</div>
<nav class="navbar navbar-default">
    <div class="container-fluid  main-nav">
        <ul class="nav navbar-nav">
            <li class="active"><a href="Home">Trang chủ</a></li>
            <li><a href="editAccount">Manager Accounts</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class=""><a href="#">${sessionScope.USER.fullName}</a></li>
            <li class=""><a href="logout">Đăng xuất</a></li>
        </ul>
    </div> <!-- End .container-fluid  main-nav -->
</nav> <!-- End .navbar -->
