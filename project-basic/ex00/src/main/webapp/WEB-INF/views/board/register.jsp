<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp"%>


<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Register</h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank"
                                                               href="https://datatables.net">official DataTables documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board Register</h6>
    </div>
    <div class="card-body">
        <form id="registerForm" action="/board/register" method="post" enctype="multipart/form-data">
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Title</span>
                </div>
                <input type="text" name="title" class="form-control" >
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Content</span>
                </div>
                <textarea class="form-control" id="exampleFormControlTextarea1" name="content" rows="3"></textarea>
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Writer</span>
                </div>
                <input type="text" name="writer" class="form-control" >
            </div>
            <div class="form-group input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Files</span>
                </div>
                <input type="file" name="files" class="form-control" >
            </div>
            <div class="input-group input-group-lg">
                <button type="submit" class="btn btn-primary submitBtn" >SUBMIT</button>

            </div>
        </form>

    </div>
</div>

<%@include file="../includes/footer.jsp"%>

<script>
    const formObj = document.querySelector("#registerForm");
    document.querySelector(".submitBtn").addEventListener("click",e=>{
        e.stopPropagation()
        e.preventDefault()

        //파일 확장자, 사이즈 체크
        const fileReg = /(.*?)\.(gif|png|jpg|jpeg|bmp)$/;
        const fileInput = document.querySelector("input[name ='files']");

        const fileArr = fileInput.files;
        if (fileArr && fileArr.length > 0) {
            for (const file of fileArr) {
                if (!file.name.match(fileReg)) {
                    alert("첨부파일 확장자가 잘못되었습니다.")
                    return;
                }
                console.dir(fileInput);
            }
        }
        formObj.submit();
    },false)
</script>

<%@include file="../includes/end.jsp"%>