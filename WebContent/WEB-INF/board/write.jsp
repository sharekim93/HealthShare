<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
	<div class="container">
		<h3>글쓰기</h3>
		<form action="write.board" method="post" id="form_write">
			<fieldset>
				<div>
					<input type="hidden" name="c" value="${(!empty param.c)? param.c:'1'}"/>
				</div>
				<div class="form-group">
					<label for="bname">이름</label>
					<input type="text" id="bname" name="bname" class="form-control" placeholder="이름을 입력하세요">
				</div>
				<div class="form-group">
					<label for="bpassword">비밀번호</label>
					<input type="password" id="bpassword" name="bpass" class="form-control" placeholder="비밀번호를 입력하세요">
					<p>(*) 수정, 삭제 시 필수</p>
				</div>
				<div class="form-group">
					<label for="btitle">제목</label>
					<input type="text" id="btitle" name="btitle" class="form-control" placeholder="제목을 입력하세요">
				</div>
				<div class="form-group">
					<label for="bcontents">내용</label>
					<textarea rows=10 id="bcontents" name="bcontent" class="form-control" placeholder="내용을 입력하세요"></textarea>
				</div>
				<div class="buttons">
				<c:if test="${param.c eq '4'}">
				<div class="form-group" style="float:left;">
					<input type="checkbox" name="bhidden" id="hidden_check" value="1">
					<label for="hidden_check">제목을 비밀로하기</label>
				</div>
				</c:if>
				<div class="form-group" style="float:right;" >
					<input type="submit" value="입력" id="submit" class="btn btn-default">
					<input type="reset" value="취소" id="cancel" class="btn btn-default">
					<a href="list.board" class="btn btn-default">목록보기</a>
				</div>
				</div>
			</fieldset>
		</form>
	</div>
	<script>
	$(document).ready(function(){
		$("#submit").click(function(){
			if($("#bname").val()==""){alert("이름에 빈 칸이 입력되었습니다.");$("#bname").focus();return false;}
			else if($("#bpassword").val()==""){alert("비밀번호에 빈 칸이 입력되었습니다.");$("#bpassword").focus();return false;}
			else if($("#btitle").val()==""){alert("제목에 빈 칸이 입력되었습니다.");$("#btitle").focus();return false;}
			else if($("#bcontents").val()==""){alert("내용에 빈 칸이 입력되었습니다.");$("#bcontents").focus();return false;}
		})
	})
	</script>
</body>
<%@include file="../inc/footer.jsp"%>