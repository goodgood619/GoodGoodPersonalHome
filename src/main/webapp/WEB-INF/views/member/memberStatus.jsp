
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-31
  Time: 오후 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/layout/AfterLoginheader.jsp"%>
<html>
<head>
    <title>memberStatus</title>
    <script>
        $(document).on('click','#btnMembermodifyUp',function (e) {
            e.preventDefault();
            var url = "${pageContext.request.contextPath}/member/modifyMemberuser";
            var id = $('#id').val();
            var name = $('#name').val();
            var password = $('#pwd').val();
            var cellphone = $('#cellphone').val();
            var email = $('#email').val();
            var paramdata = JSON.stringify({"id":id,"name":name,"pwd":password,"email":email,"cellphone":cellphone});
            var headers = {"Content-Type":"application/json","X-HTTP-Method-Override":"POST"};
            $.ajax({
                url:url,
                headers:headers,
                data:paramdata,
                type:'POST',
                dataType:'text',
                success:function (result) {
                    console.log(result);
                    var result2 = JSON.parse(result);
                    if(result2.good === '핸드폰문자') {
                        alert('문자는 입력될수 없습니다. 다시 입력해주세요');
                        $('#cellphone').val('');
                        $('#cellphone').focus();
                    }
                    else if(result2.good === '핸드폰길이불가') {
                        alert('핸드폰 번호 10자리 혹은 11자리를 입력해주세요, 혹은 -를 추가하여 12자리 혹은 13자리를 입력해주세요');
                    }
                    else if(result2.good ==='이메일골뱅이') {
                        alert('@를 이용해 이메일을 입력해주세요');
                    }
                    else {
                        alert('회원정보가 수정되었습니다.');
                        $('#btnMembermodifyCancel').trigger("click");
                    }
                }
            })
        });
        $(document).on('click','#btnMembermodifyCancel',function (e) {
            e.preventDefault();
            $('#id').val('');
            $('#name').val('');
            $('#pwd').val('');
            $('#cellphone').val('');
            $('#email').val('');
        });

        $(document).ready(function () {
            $('#id').prop('readonly',true);
            $('#id').val('<c:out value="${UserVO.id}"/>');
            $('#name').prop('readonly', true);
            $('#name').val('<c:out value="${UserVO.name}"/>');
            $("#pwd").val('<c:out value="${UserVO.pwd}"/>');
            $('#cellphone').prop('readonly',true);
            $("#cellphone").val('<c:out value="${UserVO.cellphone}"/>');
            $("#email").val('<c:out value="${UserVO.email}"/>');
        });
        $(document).on('click','#btnIdcheck',function (e) {
            e.preventDefault();
            var url = "${pageContext.request.contextPath}/user/idCheck";
            var idcheck = $('#id').val();
            var paramdata = JSON.stringify({"id":idcheck});
            var headers = {"Content-Type":"application/json","X-HTTP-Method-Override":"POST"};
            $.ajax({
                url:url,
                headers:headers,
                async:false,
                data:paramdata,
                type:'POST',
                dataType:'text',
                success:function (data) {
                    var result = JSON.parse(data);
                    if(result.ret === '실패'){
                        alert('사용할수 있는 아이디입니다.');
                    }
                    else {
                        alert('중복된 아이디입니다. 다시 입력해주세요');
                        $('#id').val('');
                        $('#id').focus();
                    }
                }
            })
        });

        $(document).on('click','#btnEmailcheck',function (e) {
            e.preventDefault();
            var url = "${pageContext.request.contextPath}/user/emailCheck";
            var email = $('#email').val();
            var paramdata = JSON.stringify({"email":email});
            var headers = {"Content-Type":"application/json","X-HTTP-Method-Override":"POST"};
            $.ajax({
                url:url,
                headers:headers,
                data:paramdata,
                type:'POST',
                dataType:'text',
                success:function (data) {
                    var result = JSON.parse(data);
                    if(result.ret === '실패') {
                        alert('사용할 수 있는 이메일입니다. ');
                    }
                    else {
                        alert('중복된 이메일입니다. 다시 입력해주세요');
                        $('#email').val('');
                        $('#email').focus();
                    }
                }
            })
        })

    </script>
</head>
<body>
<article>
    <div class="container col-md-6" role="main">
        <div class="card">
            <div class="card-header">Register</div>
            <div class="card-body">
                <form:form name="form" id="form" class="form-signup" role="form" modelAttribute="userVO" method="post" action="${pageContext.request.contextPath}/member/modifyMemberuser">
                    <div class="form-group row"> <label for="id" class="col-md-3 col-form-label text-md-right">아이디</label>
                        <div class="col-md-5">
                            <form:input path="id" id="id" class="form-control" placeholder="아이디을 입력해 주세요" />
                        </div>
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
                        <label for="cellphone" class="col-md-3 col-form-label text-md-right">핸드폰 번호</label>
                        <div class="col-md-5">
                            <form:textarea path="cellphone" id="cellphone" class="form-control" placeholder="핸드폰 번호를 입력해 주세요" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="email" class="col-md-3 col-form-label text-md-right">이메일</label>
                        <div class="input-group col-md-7">
                            <form:input path="email" id="email" class="form-control" placeholder="@를 포함해 이메일을 입력해 주세요" />
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <div style="margin-top:10px">
            <button type="button" class="btn btn-sm btn-primary" id="btnMembermodifyUp">회원정보수정</button>
            <button type="button" class="btn btn-sm btn-primary" id="btnMembermodifyCancel">취소</button>
        </div>
    </div>
</article>
</body>
</html>
