
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学员信息管理系统</title>
</head>


<body>
<form method="post" action="${pageContext.request.contextPath}/add">
<h1>学员信息添加</h1>
    <table  border="1" >
        <tr >
            <td >
                姓名
            </td>
            <td>
                <input  type="text" name="name">
            </td>
        </tr>

        <tr >
            <td>
                性别
            </td>
            <td>
                <input  type="text" name="gender">
            </td>
        </tr>
        <tr >
            <td>
                年龄
            </td>
            <td >
                <input  type="text" name="age">
            </td>
        </tr>
        <tr >
            <td width="50%">
                电话
            </td>
            <td width="50%">
                <input  type="text" name="telephone">
            </td>
        </tr>
        <tr >
            <td>
                email
            </td>
            <td >
                <input  type="text" name="email">
            </td>
        </tr>
        <tr>
            <td>
                班级
            </td>
            <td >
               <select name="classId">
                   <option value="0">请选择</option>
                   <option value="1">s2188</option>
                   <option value="2">s2192</option>
                   <option value="3">y2134</option>
               </select>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="保存"> <input type="button" value="返回"> </td>
        </tr>
</table>
</form>
</body>
</html>
