<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-12
  Time: 오후 11:40
  To change this template use File | Settings | File Templates.
--%>

    <%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript"></script>

    <!-- ajax and jquery test-->
    <!--
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <!-- common CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/common.css">
    <script>
        function fn_boardgetBoardList() {
            location.href = "${pageContext.request.contextPath}/board/getBoardList";
        }
        function fn_logout() {
            location.href = "${pageContext.request.contextPath}/login/doLogout";
        }
        function fn_showMemberStatus() {
            location.href = "${pageContext.request.contextPath}/member/showMemberuser";
        }
    </script>

    <!-- 메뉴바 추가부분-->
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a class="navbar-brand" href="#" onclick="fn_boardgetBoardList()">GoodGood</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample03">
    <ul class="navbar-nav mr-auto">
    <li class="nav-item active">
    <a class="nav-link" href="#" onclick="fn_boardgetBoardList()">Board<span class="sr-only">(current)</span></a>
    </li>
    <li class="nav-item">
    <a class="nav-link" href="#">방명록</a>
    </li>
    <li class="nav-item">
    <a class="nav-link" href="#" onclick="fn_showMemberStatus()">회원정보</a>
    </li>
    <li class="nav-item">
    <a class = "nav-link" href="#" onclick="fn_logout()">로그아웃</a>
    </li>
    <li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
    <div class="dropdown-menu" aria-labelledby="dropdown03">
    <a class="dropdown-item" href="#">Action</a>
    <a class="dropdown-item" href="#">Another action</a>
    <a class="dropdown-item" href="#" >Something else here</a>
    </div>
    </li>
    </ul>
    <form class="form-inline my-2 my-md-0">
    <input class="form-control" type="text" placeholder="Search">
    </form>
    </div>
    </nav>