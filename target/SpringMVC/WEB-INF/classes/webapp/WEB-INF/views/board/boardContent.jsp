<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2019-10-14
  Time: 오후 3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/layout/AfterLoginheader.jsp"%>
<html>
<head>
    <title>board</title>
    <script>
        $(document).on('click','#btnList',function () {
            location.href = "${pageContext.request.contextPath}/board/getBoardList";
        });

        //수정클릭 이벤트
        $(document).on('click', '#btnUpdate',function () {
            var url = "${pageContext.request.contextPath}/board/editForm";
            url = url + "?bid=" +  ${boardContent.bid};
            url = url + "&mode=edit";
            location.href = url;
        });
        //삭제클릭 이벤트
        $(document).on('click','#btnDelete',function () {
            var url = "${pageContext.request.contextPath}/board/deleteBoard";
            url = url + "?bid=" + ${boardContent.bid};
            url = url +"&mode=edit";
            location.href = url;
        });
        //댓글저장클릭 이벤트
        $(document).on('click','#btnReplySave',function () {
            var url = "${pageContext.request.contextPath}/restBoard/saveReply";
           var replyContent = $('#content').val();
           var replyReg_id = $('#reg_id').val();

           var paramData = JSON.stringify({"content":replyContent,"reg_id":replyReg_id,"bid":'${boardContent.bid}'});
           var headers = {"Content-Type":"application/json","X-HTTP-Method-Override":"POST"};

           $.ajax({
                url : url,
               headers: headers,
               data: paramData,
               type: 'POST',
               dataType : 'text',
               success:function () {
                   showReplyList();
                   $("#content").val('');
                   $("#reg_id").val('');
               },
               error:function (error) {
                   console.log("댓글저장에 대한 에러: "+ error);
               }
           });

        });
        // 초기에 로딩할때, 댓글보여주기 리스트
        $(document).ready(function () {
            showReplyList();
            $('#btnReClick').trigger('click');
        });
       function fn_btnReReplySave(rid,index){
            var url = "${pageContext.request.contextPath}/restBoard/saveReReply";
            var rereplyContent = r_content[index].value;
            var rereplyReg_id = r_reg_id[index].value;
            var paramdata = JSON.stringify({"rid":rid,"r_content":rereplyContent,"r_reg_id":rereplyReg_id});
            var headers = {"Content-Type":"application/json","X-HTTP-Method-Override":"POST"};
            $.ajax({
                url : url,
                headers : headers,
                data : paramdata,
                type : 'post',
                success:function () {
                    showReplyList();
                    $('#r_content').val('');
                    $('#r_reg_id').val('');
                },
                error:function (error) {
                    console.log("대댓글저장에 대한 에러 : "+ error);
                }
            });
        }
        //댓글수정관련
        function fn_editReply(rid,reg_id,content) {
            var htmls = "";
            htmls += '<div class="media text-muted pt-3" id="rid' + rid + '">';
            htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
            htmls += '<title>Placeholder</title>';
            htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
            htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
            htmls += '</svg>';
            htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
            htmls += '<span class="d-block">';
            htmls += '<strong class="text-gray-dark">' + reg_id + '</strong>';
            htmls += '<span style="padding-left: 7px; font-size: 9pt">';
            htmls += '<a href="javascript:void(0)" onclick="fn_updateReply(' + rid + ', \'' + reg_id + '\')" style="padding-right:5px">저장</a>';
            htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';
            htmls += '</span>';
            htmls += '</span>';
            htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
            htmls += content;
            htmls += '</textarea>';
            htmls += '</p>';
            htmls += '</div>';
            $('#rid' + rid).replaceWith(htmls);
            $('#rid' + rid + ' #editContent').focus();
        }

        // 대댓글 수정
        function fn_editreReply(rrid,r_reg_id,r_content) {
            var htmls = "";
            htmls += '<div class="media text-muted pt-3" id="rrid' + rrid + '">';
            htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
            htmls += '<title>Placeholder</title>';
            htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
            htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
            htmls += '</svg>';
            htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
            htmls += '<span class="d-block">';
            htmls += '<strong class="text-gray-dark">' + r_reg_id + '</strong>';
            htmls += '<span style="padding-left: 7px; font-size: 9pt">';
            htmls += '<a href="javascript:void(0)" onclick="fn_updatereReply(' + rrid + ', \'' + r_reg_id + '\')" style="padding-right:5px">저장</a>';
            htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';
            htmls += '</span>';
            htmls += '</span>';
            htmls += '<textarea name="r_editContent" id="r_editContent" class="form-control" rows="3">';
            htmls += r_content;
            htmls += '</textarea>';
            htmls += '</p>';
            htmls += '</div>';
            $('#rrid' + rrid).replaceWith(htmls);
            $('#rrid' + rrid + ' #r_editContent').focus();
        }

        //대댓글 삭제
        function fn_deletereReply(rrid){
            var url ="${pageContext.request.contextPath}/restBoard/deleteReReply";
            var paramdata = {"rrid":rrid};
            $.ajax({
                url:url,
                data:paramdata,
                type:'post',
                dataType:'json',
                success : function (result) {
                    console.log(result);
                    showReplyList();
                },
                error: function (error) {
                    console.log("에러 : "+ error);
                }
            });
        }
        //
        function fn_deleteReply(rid) {
            var url = "${pageContext.request.contextPath}/restBoard/deleteReply";
            var paramdata = {"rid":rid};
            $.ajax({
                url: url,
                data: paramdata,
                type: 'POST',
                dataType : 'text',
                success : function () {
                    showReplyList();
                },
                error : function (error) {
                    console.log("에러: "+error);
                }
            });
        }
        function fn_updatereReply(rrid) {
            var url = "${pageContext.request.contextPath}/restBoard/updateReReply";
            var r_editContent = $('#r_editContent').val();
            var paramdata = JSON.stringify({"r_content":r_editContent,"rrid":rrid});
            var headers = {"Content-Type":"application/json","X-HTTP-Method-Override":"POST"};
            $.ajax({
                url:url,
                headers:headers,
                data:paramdata,
                type:'post',
                dataType:'json',
                success:function (result) {
                    console.log(result);
                    showReplyList();
                },
                error:function (error) {
                    console.log("에러 "+ error);
                }
            });
        }
        function fn_updateReply(rid) {
            var url = "${pageContext.request.contextPath}/restBoard/updateReply";
            var replyEditContent = $('#editContent').val();
            var paramdata = JSON.stringify({"content":replyEditContent,"rid":rid});
            var headers = {"Content-Type":"application/json","X-HTTP-Method-Override":"POST"};
            $.ajax({
                url : url,
                headers: headers,
                data : paramdata,
                type : 'POST',
                dataType: 'text',
                success: function (result) {
                    console.log(result);
                    showReplyList(); //업데이트 했으니 다시 보여줘야지 ㅇㅇ
                },
                error: function (error) {
                    console.log("에러: "+error);
                }

            });
        }
        function showReplyReplyList(reReplyVOList,rid,index) {
            var rhtmls = [];
            if(reReplyVOList.length > 0) {
                // 읽을수 있지만 수정은 불가능한것
                rhtmls += '<div id = "aRereplyrid' + rid + '" style = "display: none">';
                $.each(reReplyVOList,function (index) {
                    if (reReplyVOList[index].r_readonlyorwrite === 0) {
                        rhtmls += '<div class="media text-muted pt-3" id="rrid' + reReplyVOList[index].rrid + '">';
                        rhtmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
                        rhtmls += '<title>Placeholder</title>';
                        rhtmls += '<rect width="100%" height="100%" fill="#979797"></rect>';
                        rhtmls += '</svg>';
                        rhtmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                        rhtmls += '<span class="d-block">';
                        rhtmls += '<strong class="text-gray-dark">' + reReplyVOList[index].r_reg_id + '</strong>';
                        rhtmls += '<span style="padding-left: 7px; font-size: 9pt">';
                        rhtmls += '</span>';
                        rhtmls += '</span>';
                        rhtmls += reReplyVOList[index].r_content;
                        rhtmls += '<p style="padding-left: 7px; font-size:9pt">';
                        rhtmls += '</p>';
                        rhtmls += '</p>';
                        rhtmls += '</div>';
                    }
                    else { //읽을수 있고, 수정 혹은 삭제도 가능한것
                        rhtmls += '<div class="media text-muted pt-3" id="rrid' + reReplyVOList[index].rrid + '">';
                        rhtmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
                        rhtmls += '<title>Placeholder</title>';
                        rhtmls += '<rect width="100%" height="100%" fill="#979797"></rect>';
                        rhtmls += '</svg>';
                        rhtmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                        rhtmls += '<span class="d-block">';
                        rhtmls += '<strong class="text-gray-dark">' + reReplyVOList[index].r_reg_id + '</strong>';
                        rhtmls += '<span style="padding-left: 7px; font-size: 9pt">';
                        rhtmls += '</span>';
                        rhtmls += '</span>';
                        rhtmls += reReplyVOList[index].r_content;
                        rhtmls += '<p style="padding-left: 7px; font-size:9pt">';
                        rhtmls += '<a href="javascript:void(0)" onclick="fn_editreReply(' + reReplyVOList[index].rrid + ', \'' + reReplyVOList[0].r_reg_id + '\', \'' + reReplyVOList[index].r_content + '\' )" style="padding-right:5px">수정</a>';
                        rhtmls += '<a href="javascript:void(0)" onclick="fn_deletereReply(' + reReplyVOList[index].rrid + ')" >삭제</a>';
                        rhtmls += '</p>';
                        rhtmls += '</p>';
                        rhtmls += '</div>';
                    }
                });
                rhtmls += '</div>';
            }
            rhtmls += '<div class="my-3 p-3 bg-white rounded shadow-sm" style="display: none" id = "bRereplyrid' + rid + '" >';
            rhtmls +=  '<form:form name="form" id="form" role="form" modelAttribute="rereplyVO" method="post">';
            rhtmls += '<div class="row">';
            rhtmls+=  '<div class="col-sm-10">';
            rhtmls += '<textarea id="r_content" class="form-control" placeholder="답글을 입력해 주세요"></textarea>';
            rhtmls += '</div>';
            rhtmls += '<div class="col-sm-2">';
            rhtmls += '<input class="form-control" id="r_reg_id" placeholder="답글 작성자">';
            rhtmls += '<button type="button" class="btn btn-sm btn-primary" onclick="fn_btnReReplySave(' +rid + ', \''+ index + '\')" style="width: 100%; margin-top: 10px"> 저장 </button>';
            rhtmls += '</div>';
            rhtmls += '</div>' ;
            rhtmls += '</form:form>';
            rhtmls +='</div>';
            return rhtmls;
        }

        function btnReClick(rid) {
            $('#aRereplyrid'+rid).toggle();
            $('#bRereplyrid'+rid).toggle();
        }
        function showReplyList() {
            var url = "${pageContext.request.contextPath}/restBoard/getReplyList";
            var paramdata = {"bid":"${boardContent.bid}"};
            $.ajax({
                url:url,
                type:'POST',
                data:paramdata,
                dateType :'text',
                success: function (result) {
                    var htmls = [];
                    if(result.length<1) {
                        htmls.push("등록된 댓글이 없습니다.");
                    }
                    else {
                        $(result).each(function (index) {
                            if(this.readonlyorwrite === 1) {
                                htmls += '<div class="media text-muted pt-3" id="rid' + this.rid + '">';
                                htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
                                htmls += '<title>Placeholder</title>';
                                htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
                                htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
                                htmls += '</svg>';
                                htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                                htmls += '<span class="d-block">';
                                htmls += '<strong class="text-gray-dark">' + this.reg_id + '</strong>';
                                htmls += '<span style="padding-left: 7px; font-size: 9pt">';
                                htmls += '<a href="javascript:void(0)" onclick="fn_editReply(' + this.rid + ', \'' + this.reg_id + '\', \'' + this.content + '\' )" style="padding-right:5px">수정</a>';
                                htmls += '<a href="javascript:void(0)" onclick="fn_deleteReply(' + this.rid + ')" >삭제</a>';
                                htmls += '</span>';
                                htmls += '</span>';
                                htmls += this.content;
                                htmls += '</p>';
                                htmls += '<button type= "button" class="btn btn-sm btn-primary" onclick="btnReClick('+ this.rid + ')">답글</button>';
                                htmls += '</div>';
                                htmls += showReplyReplyList(this.reReplyVOList,this.rid,index);
                            }
                            else {
                                htmls += '<div class="media text-muted pt-3" id="rid' + this.rid + '">';
                                htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
                                htmls += '<title>Placeholder</title>';
                                htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
                                htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
                                htmls += '</svg>';
                                htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                                htmls += '<span class="d-block">';
                                htmls += '<strong class="text-gray-dark">' + this.reg_id + '</strong>';
                                htmls += '<span style="padding-left: 7px; font-size: 9pt">';
                                htmls += '</span>';
                                htmls += '</span>';
                                htmls += this.content;
                                htmls += '<p style="padding-left: 7px; font-size:9pt">';
                                htmls += '</p>';
                                htmls += '<button type= "button" class="btn btn-sm btn-primary" onclick="btnReClick('+ this.rid + ')">답글</button>';
                                htmls += '</p>';
                                htmls += '</div>';
                                htmls += showReplyReplyList(this.reReplyVOList,this.rid,index);
                            }
                        });
                    }
                    $("#replyList").html(htmls);

                } //Ajax success
            }); //Ajax end
        }
    </script>
</head>
<body>
    <article>
        <div class="container" role="main">
            <h2>board Content</h2>
            <div class="bg-white rounded shadow-sm">
                <div class="board_title"><c:out value="${boardContent.title}"/></div>
                <div class="board_info_box">
                    <span class="board_author"><c:out value="${boardContent.reg_id}"/>,</span><span class="board_date"><c:out value="${boardContent.reg_gt}"/></span>
                </div>
                <div class="board_content">${boardContent.content}</div>
                <div class="board_tag">TAG : <c:out value="${boardContent.tag}"/></div>

            </div>
            <div style="margin-top : 20px">
                <c:if test="${msg == 'false'}">
                    <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
                </c:if>
                <c:if test ="${msg == 'true'}">
                    <button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
                    <button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
                    <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
                </c:if>
            </div>
            <!-- Reply Form {s} -->

            <div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
                <form:form name="form" id="form" role="form" modelAttribute="replyVO" method="post">
                    <form:hidden path="bid" id="bid"/>
                    <div class="row">
                        <div class="col-sm-10">
                            <form:textarea path="content" id="content" class="form-control" rows="3" placeholder="댓글을 입력해 주세요"/>
                        </div>
                        <div class="col-sm-2">
                            <form:input path="reg_id" class="form-control" id="reg_id" placeholder="댓글 작성자"/>
                            <button type="button" class="btn btn-sm btn-primary" id="btnReplySave" style="width: 100%; margin-top: 10px"> 저장 </button>
                        </div>
                    </div>
                </form:form>
            </div>
            <!-- Reply Form {e} -->

            <!-- Reply List {s}-->
            <div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
                <h6 class="border-bottom pb-2 mb-0">Reply list</h6>
                <div id="replyList">
                </div>
            </div>
            <!-- Reply List {e}-->
        </div>
    </article>
</body>
</html>
