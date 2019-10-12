<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-12
  Time: 오후 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<html>
<head>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <!-- common CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/webapp/resources/css/common.css">

    <title>Board</title>
    <script>
        $(document).on('click','#btnSave',function (e) {
            e.preventDefault();
            $("#form").submit();
        });

        $(document).on('click','#btnList',function (e) {
           e.preventDefault();
           location.href = "${pageContext.request.contextPath}/board/getBoardList";
        });
    </script>
</head>
<body>
<article>
    <div class="container" role="main">
        <h2>board Form</h2>
        <form name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
            <div class="mb-3">
                <label for="title">제목</label>
                <input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
            </div>
            <div class="mb-3">
                <label for="reg_id">작성자</label>
                <input type="text" class="form-control" name="reg_id" id="reg_id" placeholder="이름을 입력해 주세요">
            </div>
            <div class="mb-3">
                <label for="content">내용</label>
                <textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" ></textarea>
            </div>
            <div class="mb-3">
                <label for="tag">TAG</label>
                <input type="text" class="form-control" name="tag" id="tag" placeholder="태그를 입력해 주세요">
            </div>
        </form>
        <div >
            <button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
            <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
        </div>
    </div>
</article>
</body>
</html>
