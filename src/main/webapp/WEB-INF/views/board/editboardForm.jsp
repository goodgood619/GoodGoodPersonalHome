
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-22
  Time: 오후 8:34
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
        $(document).on('click','#btnModify',function (e) {
            e.preventDefault();
            $("#form2").submit();
        });
        $(document).on('click','#btnList',function (e) {
            e.preventDefault();
            location.href = "${pageContext.request.contextPath}/board/getBoardList";
        });

        $(document).ready(function () {
            var mode = '<c:out value="${mode}"/>';
            if(mode === 'edit'){ // === 3개?? 띠용
                $("#reg_id").prop('readonly', true);
                $("input:hidden[name='bid']").val(<c:out value="${boardContent.bid}"/>);
                $("input:hidden[name='mode']").val('<c:out value="${mode}"/>');
                $("#reg_id").val('<c:out value="${boardContent.reg_id}"/>');
                $("#title").val('<c:out value="${boardContent.title}"/>');
                $("#content").html(ConvertSystemSourcetoHtml('${boardContent.content}'));
                $("#tag").val('<c:out value="${boardContent.tag}"/>');
            }
        });

        function ConvertSystemSourcetoHtml(str){
            str = str.replace(/</g, '&lt;');
            str = str.replace(/>/g,'&gt;');
            var list = $.parseHTML(str);
            return list;
        }

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
        <form:form name="form2" id="form2" role="form2" modelAttribute="boardVO" method="post" action="${pageContext.request.contextPath}/board/modifyBoard" enctype="multipart/form-data">
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
                    CKEDITOR.replace('content');
                </script>
            </div>

            <div class="mb-3">
                <label for="tag">TAG</label>
                <form:input path="tag" class="form-control"  id="tag" placeholder="태그를 입력해 주세요"/>
            </div>
            <div class = "mb-3">
                <label for = "board_img">이미지</label>
                <input type = "file" id="board_img" name="file"/>
                <img src = "<c:url value="/resources/${boardContent.board_img}"/>"/>
                <img src ="<c:url value="/resources/${boardContent.boardthumb_img}"/>"/>
                <input type = "hidden" name = "board_img" value="${boardContent.board_img}"/>
                <input type = "hidden" name="boardthumb_img" value="${boardContent.boardthumb_img}"/>

            </div>

        </form:form>
        <div>
            <button type="button" class="btn btn-sm btn-primary" id="btnModify">수정</button>
            <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
        </div>
    </div>
</article>
</body>
</html>
