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
    <title>Title</title>
    <script>
        function fn_btnSignupclick() {
            location.href = "${pageContext.request.contextPath}/login/signupForm";
        }
        function fn_btnFindIdclick(){
            location.href = "${pageContext.request.contextPath}/login/choosefindId_Email_Phone";
        }
        function fn_btnFindPwdclick(){
            location.href = "${pageContext.request.contextPath}/login/findPwdInfo";
        }
        $(document).on('click','#doLogin',function (e) {
            $('#form').submit();
        });
        $(document).ready(function () {
            $("#form").keypress(function (e) {
                keycode = e.keyCode || e.charCode || e.which ;
                if (keycode === 13)    //keyCode for the Enter / Return key
                {
                    var defaultbutton = $(this).attr("DefaultButton");
                    $(defaultbutton).click();
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<article>
    <div class="container col-md-6" role="main">
        <div class="card">
            <div class="card-header">Login</div>
            <div class="card-body">
                <form:form name="form" id="form" class="Loginform" role="form" modelAttribute="userVO" method="post" action="${pageContext.request.contextPath}/login/doLogin" DefaultButton='#doLogin'>
                    <div class="form-group row"> <label for="id" class="col-md-3 col-form-label text-md-right">아이디</label>
                        <div class="col-md-5">
                            <form:input path="id" id="id" class="form-control" placeholder="아이디을 입력해 주세요" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="pwd" class="col-md-3 col-form-label text-md-right">비밀번호</label>
                        <div class="col-md-5">
                            <form:password path="pwd" id="pwd" class="form-control" placeholder="비밀번호를 입력해 주세요" />
                        </div>
                    </div>
                </form:form>
                <button type="button" class="btn btn-sm btn-primary" id="doLogin">로그인</button>
                <a href = "#" onclick="fn_btnSignupclick()">회원가입</a>
                <a href = "#" onclick="fn_btnFindIdclick()">아이디 찾기</a>
                <a href = "#" onclick="fn_btnFindPwdclick()">비밀번호 찾기</a>
            </div>
        </div>
    </div>
    <c:if test="${status}==false">
        <p style="color: #ff000000;">Login Failed</p>

    </c:if>
</article>

</body>
</html>
