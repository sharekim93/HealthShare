<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<c:if test="${kakaoresult lt 0}">
	<script>alert('카카오로 가입되지 않은 아이디입니다. 가입 후 이용하세요');location.href="join_agree.do?id=${kakaoid}";</script>
</c:if>
<c:if test="${login lt 0}">
	<script>alert('아이디 또는 비밀번호를 확인하세요');location.href='main.do';</script>
</c:if>
<c:choose>
	<c:when test="${editinfo gt 0}">
		<script>alert('회원정보 수정에 성공했습니다.');</script>
	</c:when>
	<c:when test="${editinfo <= 0}">
		<script>alert('회원정보 수정에 실패했습니다. 다시 시도하세요');</script>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${editpass gt 0}">
		<script>alert('비밀번호 수정에 성공했습니다.');</script>
	</c:when>
	<c:when test="${editpass <= 0}">
		<script>alert('비밀번호 수정에 실패했습니다. 이전 비밀번호를 확인하세요');</script>
	</c:when>
</c:choose>
<body>
	<div class="container panel" id="main_panel">
		<h3>마이페이지</h3>
		<table class="table table-striped">
		<caption style="margin-left:0px;">회원정보</caption>
		<tbody>
			<tr><th scope="row">아이디</th><td>${member.mid}</td></tr>
			<tr><th scope="row">이름</th><td>${member.mname}</td></tr>
			<tr><th scope="row">이메일</th><td>${member.memail}</td></tr>
			<tr><th scope="row">주소</th><td>${member.address1}&nbsp;${member.address2}</td></tr>
			<tr><th scope="row">가입일자</th><td>${member.mdate}</td></tr>
			<tr><th scope="row">관심언어</th><td>${member.minterest}</td></tr>
			<tr><th scope="row">SNS사용여부</th><td>${member.msns}</td></tr>
		</tbody>
		</table>
		<div class="text-center">
			<input type="button" value="회원정보수정" class="btn btn-danger" onclick="location.href='goEditPage.do'">
			<input type="button" value="비밀번호수정" class="btn btn-danger" onclick="location.href='goPassPage.do'">
			<input type="button" value="탈퇴"		  id="withdrawal"	name="edit" class="btn btn-danger">
		</div>
		<form action="WithdrawAction.do" id="delete" method="post">
			<input type="hidden" name="mpass" id="mpass" value="">
			<input type="hidden" name="edit" id="edit" value="">
		</form>
	</div>
</body>
<script>
	$(document).ready(function(){
		$("#withdrawal").click(function(){
			if(!confirm("정말 탈퇴하시겠습니까?")){return false;}
			let pass = prompt("당신의 비밀번호를 입력해주세요");
			if(pass==null){return false;}
			$("#mpass").attr("value",pass);
			$("#delete").submit();
		})
	});
</script>
<%@include file="../inc/footer.jsp"%>