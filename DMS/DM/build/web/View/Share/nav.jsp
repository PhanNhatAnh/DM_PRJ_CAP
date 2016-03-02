<%-- 
    Document   : newjspnav
    Created on : Feb 28, 2016, 8:24:06 PM
    Author     : dunglt2603
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="sp-leftcol" class="clearfix">
    <div id="sp-left" class="clearfix">
        <div class="sp-inner clearfix">
            <div class="module_cart">
                <div class="mod-wrapper clearfix">
                    <h3 class="header">			
                        <span>Thông tin tài khoản</span>			
                    </h3>						
                    <div class="mod-content clearfix">
                    </div>
                </div>
            </div>
            <div class="gap"></div>
            <div class="module_event">
                <div class="mod-wrapper clearfix">
                    <h3 class="header">			
                        <span>Menu</span>				
                    </h3>						
                    <div class="mod-content clearfix">
                        <div class="mod-inner clearfix">
                            <div class="lofmenu_jshopping">
                                <ul class="lofmenu">
                                    <li class="lofitem1">
                                        <a href="index.jsp">&nbsp;&nbsp;<span>Trang chủ</span></a>
                                    </li>
                                    <li class="lofitem1">
                                        <a href="#">&nbsp;&nbsp;<span>Quản trị</span><i></i></a>
                                        <ul style="visibility: hidden;">
                                            <li class="lofitem2 loffirst"><a href="user?action=list">&nbsp;&nbsp;<span>Quản lý Người Dùng</span></a></li>
                                            <li class="lofitem2"><a href="role?action=list">&nbsp;&nbsp;<span>Quản lý Quyền</span></a></li>
                                            <li class="lofitem2"><a href="category?action=list">&nbsp;&nbsp;<span>Quản lý Danh Mục</span></a></li>
                                            <li class="lofitem2"><a href="department?action=list">&nbsp;&nbsp;<span>Quản lý Phòng Ban</span></a></li>
                                        </ul>
                                    </li>
                                    <li class="lofitem1">
                                        <a href="#">&nbsp;&nbsp;<span>Tài liệu</span><i></i></a>
                                        <ul style="visibility: hidden;">
                                            <li class="lofitem2 loffirst"><a href="document?action=upload">&nbsp;&nbsp;<span>Upload</span></a></li>
                                            <li class="lofitem2"><a href="document?action=list">&nbsp;&nbsp;<span>Tìm kiếm</span></a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <script language="javascript">
                                jQuery(document).ready(function () {
                                    jQuery('.lofmenu_jshopping .lofmenu .lofitem1').find('ul').css({'visibility': 'hidden'});
                                    jQuery('.lofmenu_jshopping .lofmenu .lofitem1 ul').each(function () {
                                        jQuery(this).find('li:first').addClass('loffirst');
                                    })
                                    jQuery('.lofmenu_jshopping .lofmenu li').each(function () {
                                        jQuery(this).mouseenter(function () {
                                            jQuery(this).addClass('lofactive');
                                            jQuery(this).find('ul').css({'visibility': 'visible'});
                                            jQuery(this).find('ul li ul').css({'visibility': 'hidden'});
                                        });
                                        jQuery(this).mouseleave(function () {
                                            jQuery(this).removeClass('lofactive');
                                            jQuery(this).find('ul').css({'visibility': 'hidden'});
                                        });
                                    });
                                });
                            </script>				
                        </div>
                    </div>
                </div>
            </div>
            <div class="gap"></div>
            <div class="gap"></div>
        </div>
    </div>
</div>