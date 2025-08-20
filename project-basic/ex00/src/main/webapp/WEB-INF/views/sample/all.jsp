<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>All </h1>
<div>
    <sec:authorize access="isAuthenticated()">
        로그아웃
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        로그인
    </sec:authorize>
</div>

</body>
</html>
