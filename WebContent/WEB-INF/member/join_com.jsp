<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<c:set var="m" value="${member}"/>
<body>
	<div class="container panel" id="main_panel">
		<h3>FORM BASIC-JOIN</h3>
		<h5>회원가입을 축하합니다.</h5>
		<table class="table table-striped">
		<caption style="padding-left:0px;">회원가입완료</caption>
		<tbody>
			<tr><th scope="row">아이디</th><td>${m.mid}</td></tr>
			<tr><th scope="row">이름</th><td>${m.mname}</td></tr>
			<tr><th scope="row">이메일</th><td>${m.memail}</td></tr>
			<tr><th scope="row">주소</th><td>${m.address1}</td></tr>
			<tr><th scope="row">가입일자</th><td>${m.mdate}</td></tr>
			<tr><th scope="row">카카오 간편가입여부</th><td>${(empty m.kakao_id)? 'N':'Y'}</td></tr>
			<tr><th scope="row">관심언어</th><td>${m.minterest}</td></tr>
			<tr><th scope="row">SNS사용여부</th><td>${m.msns}</td></tr>
		</tbody>
		</table>
		<div class="text-right">
			<input type="button" onclick="location.href='main.do'" value="LOGIN" id="login" class="btn btn-danger">
		</div>
	</div>
</body>
<%@include file="../inc/footer.jsp"%>