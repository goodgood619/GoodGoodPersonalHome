<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-28
  Time: 오전 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<html>
<head>
    <title>Title</title>
    <script>
        function fn_btnFindIdEmail() {
            location.href = "${pageContext.request.contextPath}/login/findIdInfo";
        }
        function fn_btnFindIdCellphone() {
            location.href = "${pageContext.request.contextPath}/login/findIdInfo_phone";
        }
    </script>
</head>
<body>
    <a href = "#" onclick="fn_btnFindIdEmail()">이메일로 아이디 찾기</a>
    <a href = "#" onclick="fn_btnFindIdCellphone()">핸드폰번호로 아이디 찾기</a>
</body>
</html>
