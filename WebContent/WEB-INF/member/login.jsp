<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<c:choose>
	<c:when test="${withdraw > 0}">
	<script>alert("탈퇴되었습니다.");location.href="index.do";</script>
	</c:when>
	<c:when test="${withdraw <= 0}">
	<script>alert('비밀번호를 확인하세요');location.href="mypage.do";</script>
	</c:when>
</c:choose>
<c:if test="${logout eq 1}">
<script>alert('로그아웃되었습니다')</script>
</c:if>
<body>
	<div class="container panel" id="login_panel">
		<h3 class="hidden">로그인 페이지</h3>
		<p class="text-center"><img id="loginImage" src="${pageContext.request.contextPath}/resources/images/new_login_img.png" ></p>
		<form action="login.do" method="post">
			<fieldset>
			<legend>LOGIN</legend>
				<div class="form-group">
					<label for="mid">아이디</label>
					<input type="text" id="mid" name="mid" class="form-control" value="${(empty cookie.login_id)? '':cookie.login_id.value }" placeholder="아이디를 입력하세요">
				</div>
				<div class="form-group">
					<label for="mpass">비밀번호</label>
					<input type="password" id="mpass" name="mpass" class="form-control" placeholder="비밀번호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="checkbox" id="remember" name="chkbox" ${(empty cookie.login_id)? '':'checked' }/>
					<label for="remember">아이디 기억하기</label>
				</div>
				<div class="form-group">
					<div class="login-btns">
					<input type="submit" class="btn btn-login" value="로그인" id="submit">
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=1def83c8f6ac181e4df9db51d15d7f0e&
							redirect_uri=http://sharekim93.cafe24.com/HealthShare/KakaoLogin&response_type=code">
					<img src="resources/images/kakao_login_medium_narrow.png"></a>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
<script>
$(document).ready(function(){
	$("#submit").click(function(){
		if($("#mid").val()==""){alert("아이디를 입력해주세요");return false;}
		if($("#mpass").val()==""){alert("비밀번호를 입력해주세요");return false;}
	});
});
</script>
</html>
<%@include file="../inc/footer.jsp"%>