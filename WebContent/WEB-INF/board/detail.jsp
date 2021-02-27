<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../inc/header.jsp" %>
<style>
	#main_panel{width:1000px;}
	#board_title{background-color:lightgrey;padding:12px 10px 10px 15px;border-radius:5px;}
	#writer{margin:20px auto;}
	#content_box{margin-top:40px;padding-left:10px;}
	#content_table{margin-top:30px;}
	#content_table > tbody > tr > th {width:100px;}
	#bcontents{border:0px;background-color:transparent;box-shadow:0 0 0 0;}
</style>
	<div class="container panel" id="main_panel">
		<h2 id="board_title">${b.btitle}</h2>
		<table class="table" id="content_table">
		<tbody>
			<tr><th scope="row" >작성자</th><td>${b.bname}</td></tr>
			<tr><th scope="row" >작성일</th><td>${b.bdate}</td></tr>
			<tr><th scope="row" >조회수</th><td>${b.bhit}</td></tr>
		</tbody>
		</table>
		<div id="content_box">
			${b.bcontent }
		</div>
		<div class="text-right" style="margin-top:20px;">
			<c:if test="${!(param.c eq 5)}">
			<input type="button" class="btn btn-danger" id="d_update" value="수정">
			<input type="button" class="btn btn-danger" id="d_delete" value="삭제">
			<c:if test="${!(param.c eq '2')}"><input type="button" class="btn btn-danger" id="d_reply" value="답글달기"></c:if>
			<a href="list.board?c=${param.c}" class="btn btn-primary">목록보기</a>
			</c:if>
			<c:if test="${param.c eq 5}">
			<a href="info.board" class="btn btn-primary">목록보기</a>
			</c:if>
		</div>
		<form id="delete">
			<input type="hidden" name="bpass" id="bpass" value="">
			<input type="hidden" name="bno" id="bno" value="${b.bno}">
			<input type="hidden" name="c" id="c" value="${(!empty param.c)? param.c:'1'}"/>
		</form>
	</div>
</body>
<script>
	$(document).ready(function(){
		$("#d_update").click(function(){
			if(!confirm("수정 하시겠습니까?")){return false;};
			location.href="goupdate.board?bno="+$("#bno").val()+"&c="+$("#c").val();
		})
		$("#d_delete").click(function(){
			if(!confirm("정말 삭제하시겠습니까?")){return false;}
			let pass = prompt("비밀번호를 입력해주세요");
			if(pass==null){return false;}
			$("#bpass").attr("value",pass);
			$("#delete").attr({action:"delete.board",method:'post'}).submit();
		})
		$("#d_reply").on("click",function(){
			$("#delete").attr({action:"reply_view.board",method:'post'}).submit();
		})
	})
</script>
<%@include file="../inc/footer.jsp"%>