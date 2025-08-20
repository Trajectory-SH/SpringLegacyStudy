<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    admin
</h1>
<div>
    <sec:authentication property="principal"/>
</div>

<div>
    <sec:authentication property="principal.email"/>
</div>

<div>
    <sec:authentication property="principal.uname"/>
</div>
</body>
</html>
