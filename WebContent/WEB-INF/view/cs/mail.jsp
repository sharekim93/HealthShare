<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/header.jsp" %>
	<div class="container board">
		<h2 class="panel-heading">메일 문의</h2>
		<table class="table table-striped">
		<tbody>
			<tr><th scope="row">제목</th><td><input type="text" id="title" class="form-control"></td></tr> 
			<!-- <tr><th scope="row">이메일</th><td><input type="text" id="from" class="form-control"></td></tr> --> 
			<tr><th scope="row">내용</th><td><textarea class="form-control" id="content" rows="10" /></textarea></td></tr>
			<tr><td colspan="2" class="text-right"><input type="button" value="보내기" id="submit" class="btn btn-primary"></td></tr>
		</tbody>
		</table>
	</div>
<script src="//cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
<script>
$(document).ready(function(){
	CKEDITOR.replace( 'content' );
	
	$("#submit").on("click",function(){
		$.ajax({
			url:"sendmail.cs",
			type:"get",
			data:{title:$("#title").val(),content:CKEDITOR.instances.content.getData(),from:$("#from").val()},
			success:function(data){
				if(data==1){alert('메일 발송에 성공 했습니다.');location.href="index"}
				else {alert('메일 발송에 실패 했습니다.');console.log(data);}
			}
		})
	})
	});
   
</script>
<%@include file="/WEB-INF/inc/footer.jsp" %>