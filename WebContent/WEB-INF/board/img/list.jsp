<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jsp" %>
<div class="container board">
	<h3>회원사진</h3>
	<div>
		<p class="text-right"><a href="gowrite.pic" class="btn btn-primary">사진 올리기</a></p>
	</div>
	<div class="panel board">
		<div class="row">
		<c:forEach var="i" items="${list}">
			<div class="col-sm-3">
				<div class="thumbnail well">
					<a data-toggle="modal" data-target="#myModal${i.bno}" style="color:black;text-decoration:none;">
					<img src="${pageContext.request.contextPath}/upload/${i.img}" alt="${i.img}" style="height:200px;">
					<br/>
			        <span><strong>${i.btitle}</strong></span><br/><br/>
			        <span>${i.bname}</span><br/><br/>
			        <span>${i.bdate}</span><br/><br/>
			        </a>
				</div>
			</div>
		</c:forEach>
		<c:forEach var="i" items="${list}" varStatus="st">		
		<div id="myModal${i.bno}" class="modal fade" role="dialog" style="margin-top:100px;">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">${i.btitle}</h4>
		      </div>
		      <div class="modal-body">
		      	<p><img src="${pageContext.request.contextPath}/upload/${i.img}" alt="${i.img }" style="width:70%;height:70%;"/></p>
		        <p>${i.bcontent}</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-info update" value="${i.bno}">수정</button>
		        <button type="button" class="btn btn-danger delete" value="${i.bno}">삭제</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		      </div>
		    </div>		
		  </div>
		</div>
		</c:forEach>				
		</div>	
	</div>
</div>
<script>
$(document).ready(function(){
	$(".update").on("click",function(){
		if(!confirm('수정하시겠습니까?')){return false;}
		location.href="goupdate.pic?bno="+$(this).val();
	});
	$(".delete").on("click",function(){
		if(!confirm("삭제하시겠습니까?")){return false;}
		let pass = prompt("비밀번호를 입력해주세요");
		if(pass==null){return false;}
		$.ajax({
			url:"delete.pic",
			type:"get",
			data:{bno:$(this).val(),bpass:pass},
			success:function(data){
				if(data>0){alert('삭제되었습니다.');location.href='list.pic';}
				else {alert('비밀번호를 확인하세요');}
			}	
		});
	});
});

</script>
<%@include file="/WEB-INF/inc/footer.jsp"%>