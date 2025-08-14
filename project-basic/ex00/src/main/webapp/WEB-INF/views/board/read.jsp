<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Read</h1>
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
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Bno</span>
            </div>
            <input type="text" class="form-control" value="<c:out value="${vo.bno}"/>" readonly>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Title</span>
            </div>
            <input type="text" name="title" class="form-control" value="<c:out value="${vo.title}"/>" readonly>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Content</span>
            </div>
            <textarea class="form-control" id="exampleFormControlTextarea1"
                      name="content" rows="3"
                      contenteditable="false"
                      readonly><c:out value="${vo.content}"/></textarea>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Writer</span>
            </div>
            <input type="text" name="writer" class="form-control" value="<c:out value="${vo.writer}"/>" readonly>
        </div>
        <div class="form-group input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">RegDate</span>
            </div>
            <input type="text" name="writer" class="form-control" value="<c:out value="${vo.regDate}"/>" readonly>
        </div>
        <div class="float-right">
            <button type="button" class="btn btn-info btnList">LIST</button>

            <c:if test="${!vo.delFlag}">
                <button type="button" class="btn btn-warning btnModify">MODIFY</button>
            </c:if>
        </div>
    </div>
</div>
<div class="card shadow mb-4">
    <ul class="list-group replyList">
        <li class="list-group-item d-flex justify-content-between align-items-center">
            Cras justo odio
            <span class="badge badge-primary badge-pill">14</span>
        </li>
    </ul>
</div>
<ul class="pagination">
    <li class="page-item ">
        <a class="page-link" href="#" tabindex="-1">Previous</a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item active">
        <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
    </li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
        <a class="page-link" href="#">Next</a>
    </li>
</ul>

<form id="actionForm" method="get" action="/board/list">
    <input type="hidden" name="pageNum" value="${cri.pageNum}">
    <input type="hidden" name="amount" value="${cri.amount}">
    <c:if test="${cri.types != null && cri.keyword != null }">
        <c:forEach var="type" items="${cri.types}">
            <input type="hidden" name="types" value="${type}">
        </c:forEach>
        <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
    </c:if>
</form>

<%@include file="../includes/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>


<script>
    const actionForm = document.querySelector("#actionForm")
    const bno = '${vo.bno}'

    document.querySelector(".btnList").addEventListener("click", (e) => {
        actionForm.setAttribute("action", "/board/list")
        actionForm.submit()
    }, false)

    document.querySelector(".btnModify").addEventListener("click", (e) => {
        actionForm.setAttribute("action", `/board/modify/\${bno}`)
        actionForm.submit()
    }, false)
</script>


<script>
    const boardBno = ${vo.bno}
    const replyUL = document.querySelector(".replyList")
    const pageUL = document.querySelector(".pagination")

    let pageNum = 1
    let amount = 10

    const getList = async (pageParam, amountParam) => {

        const pageNum = pageParam || 1
        const amount = amountParam || 10


        const res = await axios.get(`/reply/list/\${boardBno}`, {

           params: {pageNum, amount}

        })


        const data = res.data
        const pageDto = data.pageDto
        const replyList = data.replyList

        printReplyList(pageDto, replyList)
    }




    const printReplyList = (pageDto, replyList) =>

        // “리스트를 초기화하고, 새 HTML을 만들 준비” 하는 단계
        replyUL.innerHTML = ""

        let str = ''
        for (const reply of replyList) {

            const {rno, replyText, replyer} = reply

            str += `
              <li data-rno="\${rno}" class="list-group-item d-flex justify-content-between align-items-center">


                \${rno} --- \${replyText}




                <span class="badge badge-primary badge-pill">\${replyer}</span>
              </li>`
        }
        replyUL.innerHTML = str

        //-----------------------//


        const {startPage, endPage, prev, next} = pageDto;
        const pageNum =pageDto.cri.pageNum


        let pageStr =``
        //prev
        if (prev) {
            pageStr+=  ` <li class="page-item ">
                                <a class="page-link" href="\${startPage-1}" tabindex="-1">Previous</a>
                         </li>`
        }

        for (let i = startPage; i <= endPage; i++) {
            pageStr += `<li class="page-item \${i==pageNum?'active':''}">
        <a class="page-link" href="\${i}">\${i}</a>
    </li>`
        }
        if (next) {
            pageStr+=  ` <li class="page-item ">
                                <a class="page-link" href="\${endPage+1}" tabindex="-1">Next</a>
                         </li>`
        }
        pageUL.innerHTML = pageStr
    }

    pageUL.addEventListener("click",(e)=>{
        e.preventDefault()
        e.stopPropagation()


        const target = e.target
        const pageNum = target.getAttribute("href")
        console.log(pageNum)

        getList(pageNum)
    },false)



    getList()
</script>

<%@include file="../includes/end.jsp" %>