<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-12
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<html>
<head>
    <title>board</title>
</head>
<script>
    $(document).on('click', '#btnWriteForm', function(e){
        e.preventDefault();
        location.href = "${pageContext.request.contextPath}/board/boardForm";
    });
</script>
<body>
<h2>board list</h2>
<table>
    <colgroup>
        <col style ="width:5%;"/>
        <col style = "width:auto;"/>
        <col style = "width:15%;"/>
        <col style = "width:10%;"/>
        <col style = "width:10%;"/>
    </colgroup>

    <thead>
    <tr>
        <th>NO</th>
        <th>글제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>작성일</th>
    </tr>
    </thead>

    <tbody>
    <c:choose>
        <c:when test="${empty boardList}">
            <tr><td colspan="5" align = "center">데이터가 없습니다.</td></tr>
        </c:when>
        <c:when test = "${!empty boardList}">
            <c:forEach var = "list" items ="${boardList}">
                <tr>
                    <td><c:out value = "${list.bid}"/></td>
                    <td><c:out value = "${list.title }"/></td>
                    <td><c:out value = "${list.reg_id}"/></td>
                    <td><c:out value = "${list.view_cnt}"/></td>
                    <td><c:out value = "${list.reg_gt}"/></td>
                </tr>
            </c:forEach>
        </c:when>
    </c:choose>
    </tbody>
</table>
</body>
</html>
