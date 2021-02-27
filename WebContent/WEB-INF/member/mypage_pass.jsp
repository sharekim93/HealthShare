<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<body>
	<div class="container panel" id="main_panel">
		<form class="form-group" action="editPassAction.do" method="post">
			<fieldset>
			<legend>비밀번호수정</legend>
			<table class="table table-striped">
				<tbody>
					<tr><td>이전 비밀번호</td><td><input type="password" name="mpass" id="pastPass"></td></tr>
					<tr><td>새 비밀번호</td><td><input type="password" name="newpass" id="newPass"></td></tr>
					<tr><td>새 비밀번호 확인</td><td><input type="password" id="newPassChk"></td></tr>
				</tbody>
			</table>
			<div class="text-center">
				<a href="mypage.do" id="before" class="btn btn-danger" >이전</a>
				<input type="submit" id="submit" value="비밀번호 수정하러 가기" class="btn btn-danger" >
			</div>
			</fieldset>
		</form>
	</div>
</body>
<script>
	$(document).ready(function(){
		$("#submit").click(function(){
			if($("#pastPass").val()==""){alert("이전 비밀번호를 입력해주세요");return false;}
			if($("#newPass").val()==""){alert("새 비밀번호를 입력해주세요");return false;}
			if($("#newPassChk").val()==""){alert("새 비밀번호 확인 칸을 입력해주세요");return false;}
			if($("#newPass").val()!=$("#newPassChk").val()){alert("새 비밀번호 확인이 일치하지 않습니다.");return false;}
		})
	})
</script>
<%@include file="../inc/footer.jsp"%>