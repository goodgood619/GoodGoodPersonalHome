<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-27
  Time: 오후 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<html>
<head>
    <title>findIdInfo_phone</title>
    <script>
        $(document).on('click','#sendPhone',function () {
            $('#form').submit();
        })
    </script>
</head>
<body>
<div class="container col-md-6" role="main">
    <div class="card">
        <div class="card-header">Find ID</div>
        <div class="card-body">
            <form:form name="form" id="form" class="Loginform" role="form" modelAttribute="userVO" method="post" action="${pageContext.request.contextPath}/user/findIdinfo_phone">
                <div class="form-group row"> <label for="cellphone" class="col-md-3 col-form-label text-md-right">Phone</label>
                    <div class="col-md-5">
                        <form:input path="cellphone" id="cellphone" class="form-control" placeholder="등록하신 핸드폰번호를 입력해 주세요" />
                    </div>
                </div>
            </form:form>
            <button type="button" class="btn btn-sm btn-primary" id="sendPhone">전송</button>
            <script>
                <c:if test = "${msg == 'wrongelement'}">
                alert('등록하신 핸드폰번호중에 문자가 존재합니다. 다시 입력해주세요');
                </c:if>
                <c:if test = "${msg == 'wronglength'}">
                    alert('10 자리 혹은 11자리를 입력해주세요, 혹은 -을 포함하여 12자리 혹은 13자리로 입력해주세요');
                </c:if>
                <c:if test = "${msg == 'noelement'}">
                    alert('등록하신 휴대폰 번호는 존재하지 않습니다.');
                </c:if>
                <c:if test = "${msg == 'sendmessageok'}">
                    alert('등록하신 휴대폰 번호로 인증번호를 전송했습니다. 인증번호를 입력해주세요');
                </c:if>
            </script>
        </div>
    </div>
</div>
</body>
</html>
