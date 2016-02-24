<%-- 
    Document   : editAccounts
    Created on : Feb 22, 2016, 9:34:25 PM
    Author     : Aking
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="glyphicon glyphicon-tasks"></i> List User</h3>
    </div><!--panel-heading-->
    <div class="panel-body">
        <div class="well">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="text" name="search" placeholder="Keyword" class="form-control" id="input-search">
                    </div><!--form-group-->
                </div><!--col-md-6-->
                <div class="col-md-4">
                    <div class="button_search">
                        <button type="submit" id="button-print" data-toggle="tooltip" class="btn btn-info" data-original-title="Export to Excel">
                            <i class="glyphicon glyphicon-search"></i>Search
                        </button>
                    </div><!--button_search-->
                </div><!--col-md-4-->
                <div class="col-md-2">
                    <div class="button_search">
                        <a href="addUser" id="button-print" data-toggle="tooltip" class="btn btn-info">
                            <i class="glyphicon glyphicon-plus"></i>Create New
                        </a>
                    </div><!--button_search-->
                </div><!--col-md-2-->
            </div><!--row-->
        </div><!--well-->
        <div class="container">
        </div>
        <form>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <td class="text-left"><a href="">No.</a></td>
                            <td class="text-left"><a href="">Full Name</a></td>
                            <td class="text-left"><a href="">Email</a></td>
                            <td class="text-left"><a href="">Role</a></td>
                            <td class="text-left " style="width: 200px; text-align: center"><a href="">Action</a></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="acc" items="${requestScope.ACCOUNTS}" varStatus="i">
                        <form action="editAccount" method="POST">
                            <tr>
                                <td><input type="hidden" name="id" value="${acc.id}"/> ${i.count}</td>
                                <td><input type="text" name="fullname" value="${acc.fullName}"/></td>
                                <td><input type="text" name="mail" value="${acc.mail}"/></td>
                                <td>${acc.roleID.roleName}</td>
                                <td style="text-align: center">
                                    <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-edit" ></i> Edit</button>
                                    <a href="#delete" class="btn btn-sm btn-warning"><i class="fa fa-times"></i> Delete</a>
                                </td>
                            </tr>
                        </form>
                            
                        </c:forEach>

                    </tbody>
                </table>
            </div><!--table-responsive-->
        </form>
        <div class="pagination pull-right" style="margin: 0px">
            <ul class="pagination">
                <li><a href="#"><i class="glyphicon glyphicon-fast-backward"></i></a></li>
                <li><a href="#"><i class="glyphicon glyphicon-step-backward"></i></a></li>
                <li><span>1/4</span></li>
                <li><a href="#"><i class="glyphicon glyphicon-step-forward"></i></a></li>
                <li><a href="#"><i class="glyphicon glyphicon-fast-forward"></i></a></li>
                <li><span>(10 per page)</span></li>
            </ul>
        </div><!--pagination-->
    </div><!--panel-body-->
</div><!--panel-->