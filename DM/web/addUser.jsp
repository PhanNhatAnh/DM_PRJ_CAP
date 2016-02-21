<%-- 
    Document   : addUser
    Created on : Feb 21, 2016, 10:49:39 PM
    Author     : Aking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-12 frm_search">
    <form action="addUser"  autocomplete="off">      
        <div class="form-control col-md-12">
            <label class="label col-md-2">Mail</label> 
            <input class="form-control col-md-4" name="mail" placeholder="Enter mail"/>
        </div>  
        
        <div class="form-control col-md-12">
            <label class="label col-md-2">Full Name</label> 
            <input class="form-control col-md-4" name="fullname" placeholder="Enter fullname"/>
        </div>
        
        <button type="submit" value="addUser" name="addUser" class="btn btn-default">Add User</button>
    </form>
</div>
