<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/header.jsp" %>
<div class="container panel board">
	<h2>고객센터</h2>
	<!-- Section 4 -->
	<div>
		<h3 style="margin-top:50px;">자주 묻는 질문</h3>
		<div class="wrap">
			<!-- accordion -->
			<div class="panel-group" id="accordion">
			  <div class="panel panel-default acc_content"></div>
			</div><!-- End accordion -->
		</div><!-- End Wrap -->
	</div>
	<div>
	<h3 class="panel-heading" style="margin-top:50px;">메일 문의</h3>
	<table class="table table-striped">
	<caption>문의 내용을 작성해주시면 관리자 이메일로 내용이 전달됩니다. 연락 받아보실 이메일을 반드시 남겨주세요.</caption>
	<tbody>
		<tr><th scope="row">제목</th><td><input type="text" id="title" class="form-control"></td></tr> 
		<tr><th scope="row">이메일</th><td><input type="email" id="from" class="form-control"></td></tr> 
		<tr><th scope="row">내용</th><td><textarea class="form-control" id="content" rows="10" /></textarea></td></tr>
		<tr><td colspan="2" class="text-right"><input type="button" value="보내기" id="submit" class="btn btn-primary"></td></tr>
	</tbody>
	</table>
	</div>
</div>
<script src="//cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
<script>
	$(document).ready(function(){
		getFaq();
		CKEDITOR.replace( 'content' );
		
		$("#submit").on("click",function(){
			let mail = /@/;
			if($("#title").val() == ""){alert("제목에 빈칸이 입력되었습니다.");$("#title").focus();return false;}
			if($("#from").val() == ""){alert("이메일에 빈칸이 입력되었습니다.");$("#from").focus();return false;}
			else if(!mail.test($("#from").val())){alert("이메일 형식이 올바르지 않습니다. @를 포함해서 입력해주세요\n[현재 입력한 이메일 주소 : "+$("#from").val()+"]");$("#from").focus();return false;}
			if(CKEDITOR.instances.content.getData() == ""){alert("내용에 빈칸이 입력되었습니다.");$("#content").focus();return false;}
				$.ajax({
					url:"sendmail.cs",
					type:"get",
					data:{title:$("#title").val(),content:CKEDITOR.instances.content.getData(),from:$("#from").val()},
					success:function(data){
						if(data==1){alert('메일 발송에 성공 했습니다.');location.href="index"}
						else {alert('메일 발송에 실패 했습니다.');console.log(data);}
					}
				});
		});
	
		function getFaq(){
			$.ajax({
				url:"faq.cs",
				type:"get",
				dataType:"json",
				success:function(data){
					var con ="";
					$(".acc_content").html("");
					for(i in data){
					    con += "<div class='panel-heading'>";
					    con += "  <h4 class='panel-title'>";
					    con += "    <a data-toggle='collapse' data-parent='#FAQ' href='#FAQ"+i+"'>"+data[i].btitle+"</a>";
					    con += "  </h4>";
					    con += "</div>";
					    if(i==0){ con += "<div id='FAQ"+i+"' class='panel-collapse collapse in'>"; }
					    else{ con += "<div id='FAQ"+i+"' class='panel-collapse collapse'>"; }
					    con += "<div class='panel-body'>";
					    con += data[i].bcontent;  
					    con += "</div>";
					    con += "</div>";
					}
					$(".acc_content").html(con);
				}
			});
		}
	 
	});
</script>
<%@include file="/WEB-INF/inc/footer.jsp" %>