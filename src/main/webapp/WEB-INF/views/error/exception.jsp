<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-15
  Time: 오전 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>board</title>
</head>
<body>
    <div class ="container">
        <p>데이터를 처리 하는 과정에서 문제가 발생하였습니다.</p>
        <p>관리자에게 문의하여 주십시오.</p>
        <h3>${exception.getMessage()}</h3>
        <ul>
            <c:forEach items="${exception.getStackTrace()}" var="stack">
                <li>${stack.toString()}</li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
