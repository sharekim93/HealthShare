<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<div class="container panel board">
	<h2>고객센터</h2>
	<!-- Section 4 -->
<div>
	
		<h3>자주 묻는 질문</h3>
	
		<div class="wrap">
		
			<!-- accordion -->
			<div class="panel-group" id="accordion">
			  <div class="panel panel-default acc_content">

			  </div><!-- End Panel -->
			</div><!-- End accordion -->
		</div><!-- End Wrap -->
	</div><!-- End Section 4 -->

	<h3 class="panel-heading">메일 문의</h3>
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
		getFaq();
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
		});
	
		function getFaq(){
			$.ajax({
				url:"getlist.board",
				type:"get",
				data:{c:"1"},
				dataType:"json",
				success:function(data){
					var con ="";
					$(".acc_content").html("");
					for(var i=0;i<4;i++){
					    con += "<div class='panel-heading'>";
					    con += "  <h4 class='panel-title'>";
					    con += "    <a data-toggle='collapse' data-parent='#FAQ' href='#FAQ"+i+"'>"+data.list[i].btitle+"</a>";
					    con += "  </h4>";
					    con += "</div>";
					    if(i==0){ con += "<div id='FAQ"+i+"' class='panel-collapse collapse in'>"; }
					    else{ con += "<div id='FAQ"+i+"' class='panel-collapse collapse'>"; }
					    con += "<div class='panel-body'>";
					    con += data.list[i].bcontent;  
					    con += "</div>";
					    con += "</div>";
						if(i==data.list.length-1){break;}
					}
					$(".acc_content").html(con);
				}
			});
		}
	 
	});
</script>
<%@include file="/WEB-INF/inc/footer.jsp" %>