<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <h1 style="color: pink;text-align: center;">学员信息列表</h1>
<table width="100%" border="1">
    <a href="/jsp/add.jsp">添加学员</a>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>年龄</td>
        <td>电话</td>
        <td>email</td>
        <td>班级</td>
    </tr>
    <c:forEach items="${students}" var="stu">
    <tr>
        <td>${stu.id}</td>
        <td>${stu.name}</td>
        <td>${stu.gender}</td>
        <td>${stu.age}</td>
        <td>${stu.telephone}</td>
        <td>${stu.email}</td>
        <td>${stu.classes.name}</td>
    </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
