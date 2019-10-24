<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-12
  Time: 오후 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Board</title>
    <script src="https://cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>

    <script>
        $(document).on('click','#btnSave',function (e) {
            e.preventDefault();
            $("#form").submit();
        });

        $(document).on('click','#btnList',function (e) {
           e.preventDefault();
           location.href = "${pageContext.request.contextPath}/board/getBoardList";
        });



        $("#board_img").change(function () {
            if(this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function (data) {
                    $(".select_img img").attr("src",data.target.result).width(500);
                }
                reader.readAsDataURL(this.files[0]);
            }
        })
    </script>
</head>
<body>
<article>
    <div class="container" role="main">
        <h2>board Form</h2>
        <form:form name="form" id="form" role="form" modelAttribute="boardVO" method="post" action="${pageContext.request.contextPath}/board/saveBoard" enctype="multipart/form-data">
            <form:hidden path="bid"/>
            <input type="hidden" name = "mode"/>

            <div class="mb-3">
                <label for="title">제목</label>
                <form:input path="title" class="form-control" id="title" placeholder="제목을 입력해 주세요"/>
            </div>
            <div class="mb-3">
                <label for="reg_id">작성자</label>
                <form:input path="reg_id" class="form-control" id="reg_id" placeholder="이름을 입력해 주세요"/>
            </div>
            <div class="mb-3">
                <label for="content">내용</label>
                <form:textarea path="content" rows="4" id="content" placeholder="내용을 입력해 주세요" htmlEscape="true"/>
                <script>
                    var ckeditor_config = {
                        resize_enable: false,
                        enterMode:CKEDITOR.ENTER_BR,
                        shiftEnterMode: CKEDITOR.ENTER_P,
                        filebrowserUploadUrl : "${pageContext.request.contextPath}/board/modifyCKEditor"
                    };
                    CKEDITOR.replace("content",ckeditor_config);
                </script>
            </div>

            <div class="mb-3">
                <label for="tag">TAG</label>
                <form:input path="tag" class="form-control"  id="tag" placeholder="태그를 입력해 주세요"/>
            </div>
            <div class = "mb-3">
                <label for = "board_img">이미지</label>
                <input type="file" id="board_img" name="file"/>
                <div class = "select_img"><img src="" alt=""/></div>

            </div>

        </form:form>
        <div>
            <button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
            <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
        </div>
    </div>
</article>
</body>
</html>
