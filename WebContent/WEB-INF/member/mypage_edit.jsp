<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<body>
	<div class="container panel" id="main_panel">
		<form class="form-group" action="editInfoAction.do" method="post">
			<fieldset>
			<legend>회원정보수정</legend>
			<table class="table table-striped">
				<tbody>
				<c:set var="interest" value="${member.minterest}"/>
					<tr><td>아이디</td><td>${member.mid}</td></tr>
					<tr><td>이름</td><td><input type="text" name="mname" id="name" value="${member.mname}"></td></tr>
					<tr><td>이메일</td><td><input type="text" name="memail" id="email" value="${member.memail}"></td></tr>
					<tr>
						<td>우편번호</td>
						<td>
							<input type="text" id="zonecode" name="zonecode" value="${member.zonecode}">
							<input type="button" id="postcode" class="btn btn-default" value="우편번호찾기"/>
						</td>
					</tr>
					<tr><td>주소</td><td><input type="text" name="address1" id="address1" value="${member.address1}" style="width:450px;"></td></tr>			
					<tr><td>상세주소</td><td><input type="text" name="address2" id="address2" value="${member.address2}"></td></tr>			
					<tr><td>가입날짜</td><td>${member.mdate}</td></tr>
					<tr><td>관심언어</td><td>
						<c:if test="${fn:contains(fn:toUpperCase(interest),'JAVA')}">
						<input type="checkbox" id="java" name="minterest" value="java" checked/>
						</c:if>
						<c:if test="${!fn:contains(fn:toUpperCase(interest),'JAVA')}">
						<input type="checkbox" id="java" name="minterest" value="java"/>
						</c:if>
						<label for="java">자바</label>
						<c:if test="${fn:contains(fn:toUpperCase(interest),'JSP')}">
						<input type="checkbox" id="jsp" name="minterest" value="jsp" checked/>
						</c:if>
						<c:if test="${!fn:contains(fn:toUpperCase(interest),'JSP')}">
						<input type="checkbox" id="jsp" name="minterest" value="jsp" />
						</c:if>
						<label for="jsp">JSP</label>
						<c:if test="${fn:contains(fn:toUpperCase(interest),'SQL')}">
						<input type="checkbox" id="sql" name="minterest" value="sql" checked/>
						</c:if>
						<c:if test="${!fn:contains(fn:toUpperCase(interest),'SQL')}">
						<input type="checkbox" id="sql" name="minterest" value="sql" />
						</c:if>
						<label for="sql">SQL</label>
						<c:if test="${fn:contains(fn:toUpperCase(interest),'PYTHON')}">
						<input type="checkbox" id="python" name="minterest" value="python" checked/>
						</c:if>
						<c:if test="${!fn:contains(fn:toUpperCase(interest),'PYTHON')}">
						<input type="checkbox" id="python" name="minterest" value="python"/>
						</c:if>
						<label for="python">Python</label>
						</td></tr>
					<tr><td>SNS사용여부</td>
						<td><select id="mselect" name="mselect">
						<option value = "-">---선택하세요---</option>
						<option value = "y" ${(member.msns eq 'Y')? "selected":""}>사용</option>		
						<option value = "n" ${(member.msns eq 'N')? "selected":""}>미사용</option>
						</select>
						</td></tr>
				</tbody>
			</table>
			<div class="text-center">
				<input type="submit" id="submit" value="수정" name="edit" class="btn btn-default" style="background-color:#337ab7;color:white;" >
				<a href="mypage.do" id="before" class="btn btn-default" style="background-color:#337ab7;color:white;" >이전</a>
			</div>
			</fieldset>
		</form>
	</div>
</body>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(document).ready(function(){
	$("#submit").click(function(){
		if($("#name").val()==""){alert("이름를 입력해주세요");return false;}
		if($("#email").val()==""){alert("이메일을 입력해주세요");return false;}
	});
	$("#postcode").click(function(){
		new daum.Postcode({
			oncomplete:function(data){
				$("#zonecode").val(data.zonecode);
				$("#address1").val(data.roadAddress);
				$("#address2").val("");					
				$("#address2").focus();					
			}
		}).open();
	});
})
</script>
<%@include file="../inc/footer.jsp"%>