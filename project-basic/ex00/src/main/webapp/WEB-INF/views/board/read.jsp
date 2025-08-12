<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="../includes/header.jsp" %>
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800" Read></h1></h1></h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank"
                                                               href="https://datatables.net">official DataTables
        documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board Read</h6>
    </div>
    <div class="card-body">

        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Bno</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.bno}"/>" readonly>
        </div>

        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Title</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.title}"/>" readonly>
        </div>

        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Content</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.content}"/>" readonly>
        </div>

        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Writer</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.writer}"/>" readonly>
        </div>

        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">RegDate</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.regDate}"/>" readonly>
        </div>

        <div class="input-group input-group-lg">
            <button type="submit" class="btn btn-info btnList">List</button>
            <button type="submit" class="btn btn-warning btnModify">Modify</button>
        </div>
    </div>
</div>
<form id="actionForm" method="get" action="/board/list">
    <input type="hidden" name="pageNum" value="${cri.pageNum}">
    <input type="hidden" name="amount" value="${cri.amount}">
</form>

<%@include file="../includes/footer.jsp" %>

<script>
    const acitonForm = document.querySelector("#actionForm")
    const bno = '${vo.bno}'

    document.querySelector(".btnList").addEventListener("click", (e) => {
        actionForm.setAttribute("action","/board/list")
        actionForm.submit()
    }, false);

    document.querySelector(".btnModify").addEventListener("click",(e)=>{
        actionForm.setAttribute("action",`/board/modify\${bno}`)
        actionForm.submit();
    },false)

</script>

<%@include file="../includes/end.jsp" %>
