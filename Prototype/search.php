<div class="tab-content">
    <div class="tab-pane active" id="database">
        <form action="index.php" name="form1" onsubmit="">
            <div class="well">
                <table class="table table-responsive">
                    <tbody><tr>
                            <td style="width: 25%">Trích yếu văn bản:</td>
                            <td>
                                <input type="text" class="form-control limit_with" name="query" style="display: inline !important" value="">
                                <select name="mode" class="form-control limit_with" style="display: inline !important">
                                    <option value="0" selected="">chứa ít nhất một từ</option>
                                    <option value="1">chứa toàn bộ các từ</option></select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="hidden" id="page" name="page" value="home" checked="">
                                <input type="hidden" id="keywords" name="searchin[]" value="1" checked="">
                                <input type="hidden" name="searchin[]" id="searchName" value="2" checked="">
                                <input type="hidden" name="searchin[]" id="comment" value="3" checked="">
                                <input type="hidden" name="searchin[]" id="attributes" value="4" checked="">
                            </td>
                        </tr>
                        <tr>
                            <td>Khối soạn thảo:</td>
                            <td>
                                <div>
                                    <select data-placeholder="Nhấn để chọn khối soạn thảo" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value=""></option>
                                        <option value="1">HO
                                        </option><option value="2">FUG
                                        </option><option value="3">FPOLY
                                        </option><option value="4">FSCHOOL
                                        </option><option value="5">FAI
                                        </option><option value="6">FSB
                                        </option><option value="7">FTICO
                                        </option><option value="8">FTRI
                                        </option><option value="9">FGO
                                        </option><option value="10">Bộ/Ban/Ngành
                                        </option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Bộ phận soạn thảo:</td>
                            <td>
                                <div>
                                    <select data-placeholder="Nhấn để chọn bộ phận soạn thảo" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value=""></option>
                                        <option value="1">Đào tạo
                                        </option><option value="2">Công tác sinh viên
                                        </option><option value="3">Tuyển sinh
                                        </option><option value="4">Hành chính
                                        </option><option value="5">Nhân sự
                                        </option><option value="6">Kế toán
                                        </option><option value="7">QA
                                        </option><option value="8">IT
                                        </option><option value="9">PR
                                        </option><option value="10">Dịch vụ đời sống
                                        </option><option value="11">Xây dựng
                                        </option><option value="12">Dự án
                                        </option><option value="13">Bộ/Ban/Ngành
                                        </option><option value="14">Phát triển chương trình
                                        </option><option value="15">Xuất bản
                                        </option><option value="16">Khác</option>
                                    </select>
                                </div>
                        </tr>
                        <tr>
                            <td>Người soạn thảo:</td>
                            <td>
                                <div>
                                    <select data-placeholder="Nhấn để chọn người soạn thảo" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value=""></option>
                                        <option value="1">admin - Administrator
                                        </option><option value="541">anhbn - Bùi Ngọc Anh
                                        </option><option value="905">anhdn - Đào Ngọc Anh
                                        </option><option value="1141">anhdn2008 - Dao Ngoc Anh
                                        </option><option value="783">anhdq - Đoàn Quỳnh Anh 
                                        </option><option value="245">anhdq2 - Đào Quỳnh Anh
                                        </option><option value="357">anhdv - Đào Vân Anh
                                        </option><option value="648">anhdv2 - Đoàn Thị Việt Anh
                                        </option><option value="665">anhhgq - Hoàng Giang Quỳnh Anh
                                        </option><option value="67">anhhn - Hoàng Ngọc Anh
                                        </option><option value="209">anhhtl - Hoàng Thị Lan Anh
                                        </option><option value="169">anhlh - Lại Hồng Anh
                                        </option><option value="418">anhlth - Lương Thị Hồng Anh
                                        </option><option value="844">anhltk - Lê Thị Kim Anh 
                                        </option><option value="585">anhltv - Lê Thị Vân Anh
                                        </option><option value="316">anhlv - Lê Văn Anh
                                        </option><option value="69">anhmt
                                        </option><option value="606">anhnd2 - Nguyễn Đức Anh
                                        </option><option value="109">anhnd4 - Nguyễn Đức Anh
                                        </option><option value="858">anhnh4 - Nguyễn Hoàng Anh 
                                        </option><option value="172">anhnhv - Nguyễn Hoàng Việt Anh
                                        </option><option value="582">anhnl2 - Nguyễn Lan Anh
                                        </option><option value="1094">yennt - Nguyễn Thị Yến (FSchool)
                                        </option><option value="141">yenthh - Trần Hoàng Hải Yến
                                        </option><option value="499">yentth - Trần Thị Hải Yến
                                        </option><option value="955">&nbsp;thapv - Phan Văn Tha
                                        </option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Loại tài liệu</td>
                            <td>
                                <div>
                                    <select data-placeholder="Nhấn để chọn loại tài liệu" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value=""></option><option value="1">Văn bản đến
                                        </option><option value="2">Văn bản đi
                                        </option><option value="3">Quyết định nội bộ
                                        </option><option value="4">Tài liệu ISO
                                        </option><option value="5">Văn bản pháp quy
                                        </option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Dấu ban hành</td>
                            <td>
                                <div>
                                    <select data-placeholder="Nhấn để chọn dấu ban hành" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value=""></option><option value="1">Công ty TNHH Giáo dục FPT
                                        </option><option value="2">Trường Đại học FPT
                                        </option><option value="3">Viện quản trị kinh doanh
                                        </option><option value="4">Trường THPT FPT Hà Nội
                                        </option><option value="5">Trường THPT FPT Đà Nẵng
                                        </option><option value="6">Công ty TNHH Sáng tạo Công nghệ FPT Toàn cầu
                                        </option><option value="8">Trung tâm Đào tạo Quốc tế FPT Đà Nẵng
                                        </option><option value="9">Trung tâm FPT Polytechnic Đà Nẵng
                                        </option><option value="10">Trung tâm công nghệ phần mềm TP.Hồ Chí Minh
                                        </option><option value="11">Viện Đào tạo Quốc tế FPT TP. HCM
                                        </option><option value="12">Viện nghiên cứu công nghệ FPT
                                        </option><option value="13">Bộ/Ban/Ngành
                                        </option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Từ ngày:</td>
                            <td>
                                <div class="row">
                                    <div class="col-sm-5">
                                        <div class="form-group">
                                            <div class="input-group date" data-provide="datepicker">
                                            <input type="text" class="form-control">
                                            <div class="input-group-addon" style="cursor: pointer">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-1">
                                        <div class="form-group" style="line-height: 33px">
                                            Đến
                                        </div>
                                    </div>
                                    <div class="col-sm-5">
                                        <div class="form-group">
                                            <div class="input-group date" data-provide="datepicker">
                                            <input type="text" class="form-control">
                                            <div class="input-group-addon" style="cursor: pointer">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>Người ký:</td>
                            <td>
                                <div>
                                    <select data-placeholder="Nhấn để chọn dấu ban hành" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value=""></option>
                                        <option value="1">admin - Administrator
                                        </option><option value="541">anhbn - Bùi Ngọc Anh
                                        </option><option value="905">anhdn - Đào Ngọc Anh
                                        </option><option value="1141">anhdn2008 - Dao Ngoc Anh
                                        </option><option value="783">anhdq - Đoàn Quỳnh Anh 
                                        </option><option value="245">anhdq2 - Đào Quỳnh Anh
                                        </option><option value="357">anhdv - Đào Vân Anh
                                        </option><option value="648">anhdv2 - Đoàn Thị Việt Anh
                                        </option><option value="665">anhhgq - Hoàng Giang Quỳnh Anh
                                        </option><option value="67">anhhn - Hoàng Ngọc Anh
                                        </option><option value="209">anhhtl - Hoàng Thị Lan Anh
                                        </option><option value="169">anhlh - Lại Hồng Anh
                                        </option><option value="418">anhlth - Lương Thị Hồng Anh
                                        </option><option value="844">anhltk - Lê Thị Kim Anh 
                                        </option><option value="585">anhltv - Lê Thị Vân Anh
                                        </option><option value="316">anhlv - Lê Văn Anh
                                        </option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Nơi nhận bên ngoài:</td>
                            <td><input type="text" name="attributes[16]" class="form-control limit_with" value=""></td>
                        </tr>

                        <tr>
                            <td>Số văn bản:</td>
                            <td><input type="text" name="attributes[8]" class="form-control limit_with" value=""></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button type="submit" class="btn"><i class="fa fa-search"></i> Tìm kiếm</button>
                                <button type="reset" class="btn" onclick=""><i class="fa fa-undo"></i> Nhập lại</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var config = {
        '.chosen-select': {}
    }
    for (var selector in config) {
        $(selector).chosen(config[selector]);
    }
</script>
