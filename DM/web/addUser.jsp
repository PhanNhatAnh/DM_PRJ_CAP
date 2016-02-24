<%-- 
    Document   : addUser
    Created on : Feb 21, 2016, 10:49:39 PM
    Author     : Aking
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="fa fa-plus-circle"></i> Add User</h3>
    </div><!--panel-heading-->
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel-body">
                <c:if test="${requestScope.MESSAGE != null}">
                    <div class="alert alert-success">
                        <i class="glyphicon glyphicon-exclamation-sign"></i>
                        ${requestScope.MESSAGE}
                        <button type="button" class="close" data-dismiss="alert">x</button>
                    </div>
                </c:if>
                <form>
                    <div class="table-responsive">
                        <table class="table ">
                            <tbody>
                                <tr>
                                    <td><label>Full Name</label></td>
                                    <td><input type="text" class="form-control" name="full_name"/></td>
                                </tr>
                                <tr>
                                    <td><label>Email</label></td>
                                    <td><input type="text" class="form-control" name="email"/></td>
                                </tr>
                                <tr>
                                    <td><label>Role</label></td>
                                    <td>
                                        <select name="role" class="form-control">
                                            <c:forEach var="role" items="${requestScope.ROLES}">
                                                <option value="${role.id}">${role.roleName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><button type="submit" name="addUser" value="addUser" class="btn btn-primary"><i class="fa fa-save"></i> Save</button>
                                        <button type="reset" class="btn btn-primary"><i class="fa fa-refresh"></i> Reset</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div><!--table-responsive-->
                </form>
            </div><!--panel-body-->
        </div>
    </div>
</div><!--panel-->