<%@include file="../inc/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<div class="container" id="main_panel">
		<h3>회원 약관 동의</h3>
		<h5>(*)은 필수 입력 사항입니다.</h5>
		<form action="join.do" method="post" >
			<fieldset class="form-group">
				<legend>서비스 약관 동의</legend>
			    <textarea rows ="10" class="form-control" id="disabledInput" disabled><%@include file="../inc/agree1.txt" %></textarea>
			    <br>
			    <p class="text-right">서비스 약관에 동의합니다<input type="checkbox" id="chkbox" style="margin-left:10px"/></p>
			</fieldset>
		
			<fieldset class="form-group">
				<legend>개인정보 수집 및 이용 동의</legend>
			    <textarea rows ="10" class="form-control" id="disabledInput" disabled><%@include file="../inc/agree2.txt" %></textarea>
			    <br>
			    <p class="text-right">개인정보 수집 및 이용에 동의합니다<input type="checkbox" id="chkbox2" style="margin-left:10px"></p>
			</fieldset>
			
			<fieldset class="form-group text-center">
				<input type="submit" value="동의하기" class="btn btn-login" name="agree" id="submit">
			</fieldset>
			<fieldset>
				<input type="hidden" name="kakaoid" value="${param.id}">
			</fieldset>
		</form>
	</div>
</body>
<script>
	$(document).ready(function(){
		$("#submit").click(function(){
			if($("#chkbox").is(":checked")==false){alert("약관에 동의해주세요");return false;}
			else if($("#chkbox2").is(":checked")==false){alert("개인정보 수집에 동의해주세요");return false;}
		})
	})
</script>

<%@include file="../inc/footer.jsp"%>