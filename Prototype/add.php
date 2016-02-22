<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title"><i class="fa fa-plus-circle"></i> Add User</h3>
    </div><!--panel-heading-->
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel-body">
                <form>
                    <div class="table-responsive">
                        <table class="table ">
                            <tbody>
                                <tr>
                                    <td><label>Full Name</label></td>
                                    <td><input type="hidden" class="form-control" name="id"/></td>
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
                                            <option>Admin</option>
                                            <option>Manager</option>
                                            <option>User</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Save</button>
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
</div>