<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<div class="secret_pass">
	<h1 class="hidden">비밀번호 확인</h1>
	<p><strong>비밀글 기능으로 보호된 글입니다.</strong> 작성자와 관리자만 열람하실 수 있습니다. 본인이라면 비밀번호를 입력하세요. </p>
	<form action="secret_detail.board" method="post">
		<div class="hidden">
			<input type="hidden" name="bno" value="${param.bno}">
		</div>	
		<fieldset>
	        <label for="pw_wr_password">비밀번호</label>
	        <input type="password" name="bpass" id="password_wr_password" class="frm_input required" size="15" maxlength="20">
	        <input type="submit" value="확인" class="btn_submit">
	    </fieldset>
	</form>
	<div class="btn_back">
        <a href="javascript:history.go(-1)" class="button">돌아가기</a>
    </div>
</div>
<%@include file="../inc/footer.jsp" %>