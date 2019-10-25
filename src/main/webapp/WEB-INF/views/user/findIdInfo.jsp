<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-25
  Time: 오후 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<html>
<head>
    <title>findIdinfo</title>
    <script>
        $(document).on('click','#sendEmail',function () {
            $('#form').submit();
        })
    </script>
</head>

<body>
<div class="container col-md-6" role="main">
    <div class="card">
        <div class="card-header">Find ID</div>
        <div class="card-body">
            <form:form name="form" id="form" class="Loginform" role="form" modelAttribute="userVO" method="post" action="${pageContext.request.contextPath}/user/sendIdcheck">
                <div class="form-group row"> <label for="email" class="col-md-3 col-form-label text-md-right">Email</label>
                    <div class="col-md-5">
                        <form:input path="email" id="email" class="form-control" placeholder="Email을 입력해 주세요" />
                    </div>
                </div>
            </form:form>
            <button type="button" class="btn btn-sm btn-primary" id="sendEmail">전송</button>
        </div>
    </div>
</div>
</body>
</html>
