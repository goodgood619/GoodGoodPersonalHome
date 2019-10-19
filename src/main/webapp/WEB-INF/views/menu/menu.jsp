<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-19
  Time: 오후 4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Menu List</title>
    <script>
    $(function() {
            fn_showList();
    });

    function fn_showList() {
        var paramdata = {};
        var url = "${pageContext.request.contextPath}/restmenu/getMenuList";
        $.ajax({
            url: url
            ,type : 'POST'
            ,dataType : 'json'
            ,data : paramdata
            ,success : function(result) {
                console.log(result);
                if(result.status === "성공"){
                        if(result.menuList.length>0){
                            var htmls = "";
                            result.menuList.forEach(function(e) {
                                    htmls += '<tr>';
                                    htmls += '<td>' + e.mid +'</td>';
                                    htmls += '<td>';
                                    htmls += '<a href="#" onclick="fn_menuInfo(' + e.mid + ',\'' + e.code +'\',\'' + e.codename + '\', ' + e.sort_num + ', \'' + e.comment + '\')" >';
                                    htmls += e.code;
                                    htmls += '</a>';
                                    htmls += '</td>';
                                    htmls += '<td>' + e.codename + '</td>';
                                    htmls += '<td>' + e.sort_num + '</td>';
                                    htmls += '<td>' + e.comment + '</td>';
                                    htmls += '</tr>';
                            })
                    }
                }
                else {
                    console.log("조회 실패");
                }
                $('#menuList').html(htmls);
            }
        });
    }

    function fn_menuInfo(mid,code,codename,sort_num,comment){
        $('#mid').val(mid);
        $('#code').val(code);
        $('#codename').val(codename);
        $('#sort_num').val(sort_num);
        $('#comment').val(comment);

        $('#code').attr("readonly",true);
    }


    $(document).on('click','#btnSave',function(e) {
        e.preventDefault();
        var url = "${pageContext.request.contextPath}/restmenu/saveMenu";
        var paramdata = {
            "code" : $("#code").val(),
            "codename" :$("#codename").val(),
            "sort_num":$("#sort_num").val(),
            "comment":$("#comment").val()
        };
        if($('#mid').val() != 0) {
            url = "${pageContext.request.contextPath}/restmenu/updateMenu";
        }

        $.ajax({
            url: url,
            type:"post",
            dataType:"json",
            data:paramdata,
            success: function(result) {
                if(result.status === "성공") {
                    alert("저장에 성공하였습니다");
                    fn_showList();
                    $("#btnInit").trigger("click");
                }
                else {
                    alert("저장에 실패하였습니다. 다시 입력해주세요");
                    fn_showList();
                    $("#btnInit").trigger("click");
                }
            }

        });
    });

    $(document).on('click','#btnInit',function(e) {
        e.preventDefault();
        $('#mid').val('');
        $('#code').val('');
        $('#codename').val('');
        $('#sort_num').val('');
        $('#comment').val('');
    });

    $(document).on('click','#btnDelete',function(e) {
        e.preventDefault();
        if($('#code').val()==""){
            alert("삭제할 코드를 선택해주세요");
            return;
        }
        var url = "${pageContext.request.contextPath}/restmenu/deleteMenu";
        var paramdata = {"code":$('#code').val()};
        $.ajax({
            url: url,
            type: 'post',
            dataType : 'json',
            data : paramdata,
            success : function(result) {
                    fn_showList();
                    $('#btnInit').trigger("click");
            }
        });
    });
    </script>
    <style>
    #paginationBox {
        padding: 10px 0px;
    }
    </style>
</head>
<body>
<article>
    <div class ="container">
    <!-- Menu form {s} -->
    <h4 class="mb-3">Menu Info</h4>
    <div>
    <form:form name="form" id="form" role="form" modelAttribute="menuVO" method="post" action="${pageContext.request.contextPath}/menu/saveMenu">
        <form:hidden path="mid" id="mid"/>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="code">Code</label>
        <form:input path="code" id="code" class="form-control" placeholder="" value="" required="" />
        <div class="invalid-feedback"> Valid Code is required. </div>
        </div>
        <div class="col-md-5 mb-3">
            <label for="codename">Code name</label>
        <form:input path="codename" class="form-control" id="codename" placeholder="" value="" required="" />
        <div class="invalid-feedback"> Valid Code name is required. </div>
        </div>
        <div class="col-md-3 mb-3">
        <label for="sort_num">Sort</label>
        <form:input path="sort_num" class="form-control" id="sort_num" placeholder="" required="" />
            </div>
        </div>
        <div class="row">
        <div class="col-md-12 mb-3">
        <label for="comment">Comment</label>
        <form:input path="comment" class="form-control" id="comment" placeholder="" value="" required="" />
        </div>
        </div>
    </form:form>
    </div>
    <!-- Menu form {e} -->
    <div>
    <button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
    <button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
    <button type="button" class="btn btn-sm btn-primary" id="btnInit">초기화</button>
    </div>
    <h4 class="mb-3" style="padding-top:15px">Menu List</h4>
    <!-- List{s} -->
    <div class="table-responsive">
    <table class="table table-striped table-sm">
    <colgroup> <col style="width:10%;" />
    <col style="width:15%;" />
    <col style="width:15%;" />
    <col style="width:10%;" />
    <col style="width:auto;" />
    </colgroup>
    <thead>
    <tr>
    <th> menu id</th>
    <th>code</th>
    <th>codename</th>
    <th>sort</th>
    <th>command</th>
    </tr>
    </thead>
    <tbody id="menuList">
    </tbody>
    </table>
    </div>
    <!-- List{e} -->

    </div>
</article>
</body>
</html>
