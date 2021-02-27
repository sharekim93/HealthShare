<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="/WEB-INF/inc/header.jsp" %>
<%@page import="java.sql.*"%>

<div class="container"  style="margin-top:5%; min-height:500px"   >
	<h3>답글달기</h3>
		<form action="reply.board" method="post"  id="editForm" >
		   <fieldset>
		   	<div class="hidden">
		   		<input type="text" name="bno" value="${param.bno}">
		   		<input type="text" name="c" value="${param.c}">
		   	</div>
			<div class="form-group">
			  <label for="bname"  >이름</label>
			  <input type="text"   name="bname"   id="bname"   class="form-control" > 
			</div>			
			<div class="form-group">
			  <label for="bpass"  >비밀번호</label>
			  <input type="password"   name="bpass"   id="bpass"   class="form-control" > 
			  <span>(*) 수정, 삭제시 필수</span>
			</div>																
			<div class="form-group">
			  <label for="btitle"  >제목</label>
			  <input type="text"   name="btitle"   id="btitle"   class="form-control"  value="${b.btitle }"> 
			</div>	
			<div class="form-group">
			  <label for="bcontent"  >내용</label>
			  <textarea name="bcontent"  id="bcontent"  cols="60"  rows="10"   class="form-control" >${b.bcontent}</textarea>
			</div>				
			<div class="form-group  text-right">
				<input type="submit"   value="입력"   class="btn btn-danger"  >  
				<input type="reset"    value="초기화"  class="btn btn-default"    >  
				<a href="list.board"   class="btn btn-default"   >목록보기</a>
			</div>
		 </fieldset>		
		</form> 	
</div>

<%@ include  file="/WEB-INF/inc/footer.jsp" %>