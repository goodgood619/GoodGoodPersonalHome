<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-21
  Time: 오후 5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<html>
<head>
    <title>signupForm</title>
    <script>
        $(document).on('click','#btnSignup',function (e) {
            e.preventDefault();
            $("#form").submit();
        });
        $(document).on('click','#btnCancel',function (e) {
            e.preventDefault();
            $('#id').val('');
            $('#name').val('');
            $('#pwd').val('');
            $('#re_pwd').val('');
            $('#email').val('');
        })
    </script>
</head>
<body>
<article>
    <div class="container col-md-6" role="main">
        <div class="card">
            <div class="card-header">Register</div>
            <div class="card-body">
                <form:form name="form" id="form" class="form-signup" role="form" modelAttribute="userVO" method="post" action="${pageContext.request.contextPath}/user/insertUser">
                    <div class="form-group row"> <label for="uid" class="col-md-3 col-form-label text-md-right">아이디</label>
                        <div class="col-md-5"> <form:input path="id" id="id" class="form-control" placeholder="아이디을 입력해 주세요" /> </div>
                    </div>
                    <div class="form-group row">
                        <label for="name" class="col-md-3 col-form-label text-md-right">이름</label>
                        <div class="col-md-5">
                            <form:input path="name" id="name" class="form-control" placeholder="이름을 입력해 주세요" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="pwd" class="col-md-3 col-form-label text-md-right">비밀번호</label>
                        <div class="col-md-5">
                            <form:password path="pwd" id="pwd" class="form-control" placeholder="비밀번호를 입력해 주세요" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="re_pwd" class="col-md-3 col-form-label text-md-right">비밀번호 확인</label>
                        <div class="col-md-5"> <form:password path="re_pwd" id="re_pwd" class="form-control" placeholder="비밀번호를 입력해 주세요" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="email" class="col-md-3 col-form-label text-md-right">이메일</label>
                        <div class="input-group col-md-7">
                            <div class="input-group-prepend">
                                <span class="input-group-text">@</span>
                            </div>
                            <form:input path="email" id="email" class="form-control" placeholder="이메일을 입력해 주세요" />
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <div style="margin-top:10px">
            <button type="button" class="btn btn-sm btn-primary" id="btnSignup">회원가입</button>
            <button type="button" class="btn btn-sm btn-primary" id="btnCancel">취소</button>
        </div>
    </div>
</article>
</body>
</html>
