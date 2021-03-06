<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-25
  Time: 오후 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/layout/BeforeLoginheader.jsp"%>

<html>
<head>
    <title>find Password</title>
    <script>
        $(document).on('click','#findPwd',function () {
            $('#form').submit();
        })
    </script>
</head>
<body>
<div class="container col-md-6" role="main">
    <div class="card">
        <div class="card-header">Find Password</div>
        <div class="card-body">
            <form:form name="form" id="form" class="Loginform" role="form" modelAttribute="userVO" method="post" action="${pageContext.request.contextPath}/user/findPwd">
                <div class="form-group row"> <label for="id" class="col-md-3 col-form-label text-md-right">ID</label>
                    <div class="col-md-5">
                        <form:input path="id" id="id" class="form-control" placeholder="ID를 입력해 주세요" />
                    </div>
                </div>
                <div class="form-group row"> <label for="email" class="col-md-3 col-form-label text-md-right">Email</label>
                    <div class="col-md-5">
                        <form:input path="email" id="email" class="form-control" placeholder="등록하신 Email을 입력해 주세요" />
                    </div>
                </div>
            </form:form>
            <button type="button" class="btn btn-sm btn-primary" id="findPwd">전송</button>
            <script>
                <c:if test = "${msg == 'false'}">
                    alert('등록하신 이메일이 존재하지 않거나, 아이디가 틀립니다. 다시 입력해주세요');
                </c:if>
                <c:if test="${msg == 'success'}">
                    alert('해당 이메일로 비밀번호를 전송하였습니다.');
                </c:if>
            </script>

        </div>
    </div>
</div>
</body>
</html>
