<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jsp" %>
<!-- main -->
<div id="main">
	<h1 class="hidden">메인</h1>
	<!-- Section 1 -->
	<section class="section1">
		<h2 class="hidden">운동방법 검색하기</h2>
		<div class="sectionWrap">
			<div class="title_center">운동방법 검색하기</div>
			<form action="info.board" method="post" class="main_search" style="height: 34px;">
				<select id="select_level" name="level">
					<option value="">난이도</option>
					<option>초급</option>
					<option>중급</option>
					<option>상급</option>
				</select>
				<select id="select_muscle" name="muscle" style="height: 34px;">
					<option value="">운동부위</option>
				</select>
				<input type="text" name="q" style="width:400px;" placeholder="'프레스'를 검색해 보세요">
				<input type="submit" value="검색하기" class="btn btn-default">
			</form>
			<div class="search_suggest text-center">
				<h5>이런 운동은 어떠세요?</h5>
				<form action="info.board" method="post" class="rec_send">
					<input type="hidden" name="muscle" id="rec_val" value="">
					<input type="button" value="어깨 운동" class="recommend">
					<input type="button" value="등 운동" class="recommend">
					<input type="button" value="가슴 운동" class="recommend">
				</form>
			</div>
		</div>
	</section>
		
	<section class="section5">
	<!---- title ---->	
	<div class="title">운동정보 미리보기</div>
		<%@include file="/WEB-INF/board/info_list_index.jsp" %>
	</section>
	
	<!---- title ---->	
	<div class="title">운동자료현황</div>
	
	<!-- Section 2 -->
	<section class="section2">
		<h2 class="hidden">운동자료현황</h2>
		<div class="sectionWrap">
		    <div class="left_p">
		    <div id="chart"></div>
		    </div>
		    <div class="right_p">
			<div id="grid" style="width: 560px;"></div>
			</div>
		</div>
	</section>

	<!-- Section 3 -->
	<section class="section3">
		<h2 class="hidden">회원사진</h2>
		<div class="title" style="padding-top:20px;">
			<div class="text-center">회원사진</div>
		</div>
		<div class="sectionWrap">
			<!-- Left Clearfix -->
			<div class="clearfix">
				<!-- Carousel -->
				<div id="myCarousel2" class="carousel slide picture" data-ride="carousel">
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				  	<c:forEach var="i" begin="0" end="${Math.floor(list.size()/4)}" varStatus="st" items="${list}">
				    <li data-target="#myCarousel2" data-slide-to="st.index" class="${(st.index eq 0)? 'active':'' }"></li>
				    </c:forEach>
				  </ol>
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner " role="listbox">
				  	<c:forEach var="j" begin="0" end="${Math.floor(list.size()/4)}" items="${list}" varStatus="st">			  		
			  		<div class="row item ${(st.first)? 'active':'' }">
						<c:forEach begin="${st.index*4}" end="${st.index*4+3}" var="i" items="${list}">
						    <div class="col-sm-3 ">
						      <img src="${pageContext.request.contextPath}/upload/${i.img}" alt="${i.img}" style="width:300px;height:370px;">
						    </div>
						</c:forEach>
			  		</div>
			  		</c:forEach>
			      </div>
				  <!-- Left and right controls -->
				  <a class="left carousel-control" href="#myCarousel2" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="right carousel-control" href="#myCarousel2" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right"></span>
				    <span class="sr-only">Next</span>
				  </a>
				</div><!-- End Carousel -->
			</div>

		</div><!-- End Wrap -->
	</section><!-- End Section 3 -->
	
	
	<section class="video_section">
		<div class="title">알아두면 도움이 되는 운동영상</div>
		<div class="sectionWrap">

			<div class="row">
				<div class="col-sm-6">
				<iframe width="560" height="315" class="youtub" src="https://www.youtube.com/embed/SKCEpgEucFM?enablejsapi=1" 
				frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
				</iframe>
				</div>
				<div class="col-sm-6">
					<ul class="video_list">
						<li>
							<a data-toggle="modal" data-target="#myModal1" style="color:black;text-decoration:none;">
								<img src="${pageContext.request.contextPath }/resources/video-icon-2x.png">
								<span>김성환의 근육성장을 위한 꿀조언 TIP3</span>
				        	</a>							
						</li>
						<li>
							<a data-toggle="modal" data-target="#myModal2" style="color:black;text-decoration:none;">
								<img src="${pageContext.request.contextPath }/resources/video-icon-2x.png">
								<span>강경원의 벤치프레스 완벽 가이드</span>
				        	</a>							
						</li>
						<li>
							<a data-toggle="modal" data-target="#myModal3" style="color:black;text-decoration:none;">
								<img src="${pageContext.request.contextPath }/resources/video-icon-2x.png">
								<span>'다리 꼬는사람' 필수시청! 뭉친근육 근막이완 하는법!</span>
				        	</a>							
						</li>
						<li>
							<a data-toggle="modal" data-target="#myModal4" style="color:black;text-decoration:none;">
								<img src="${pageContext.request.contextPath }/resources/video-icon-2x.png">
								<span>'라운드숄더' 제가 해결해 드리겠습니다! 운동법+근막이완방법</span>
				        	</a>							
						</li>
					</ul>				
				</div>
				
				<!-- Modal Content -->
				<div id="myModal1" class="modal fade" role="dialog" style="margin-top:100px;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <iframe width="560" height="315" class="youtub" src="https://www.youtube.com/embed/yJX-k8xOgs4?enablejsapi=1" frameborder="0" 
				      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				    </div>		
				  </div>
				</div>
				<div id="myModal2" class="modal fade" role="dialog" style="margin-top:100px;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <iframe width="560" height="315" class="youtub" src="https://www.youtube.com/embed/A2kHURY746E?enablejsapi=1" frameborder="0" 
				      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				    </div>		
				  </div>
				</div>
				<div id="myModal3" class="modal fade" role="dialog" style="margin-top:100px;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <iframe width="560" height="315" class="youtub" src="https://www.youtube.com/embed/pcrkAZc6z68?enablejsapi=1" frameborder="0" 
				      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				    </div>		
				  </div>
				</div>
				<div id="myModal4" class="modal fade" role="dialog" style="margin-top:100px;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <iframe width="560" height="315" class="youtub" src="https://www.youtube.com/embed/s1t1Wfripsc?enablejsapi=1" frameborder="0" 
				      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				    </div>		
				  </div>
				</div>				
				
			</div>
		</div>
	</section>
	
	<!-- Section 4 -->
	<section class="section4">
		<h2 class="hidden">우리동네 헬스장/공지사항/BMI계산기</h2>
		
		<div class="sectionWrap">
		
		<!---- title ---->
		<div class="title">
			<div class="row">
				<div class="col-sm-4">우리동네 헬스장</div>
				<div class="col-sm-4">BMI 계산기</div>
				<div class="col-sm-4">공지사항</div>
			</div>
		</div>
		
		
			<div class="col-sm-4" style="padding-left:0;">
			<h3 class="hidden">우리동네 헬스장</h3>
			<div class="text-center" style="display:flex; margin: 0 auto 10px;">
				<input type="text" id="search_val" class="form-control" placeholder="법정동명 입력 ex)성수동">
				<input type="button" id="search" value="검색" class="btn btn-primary">
			</div>
			<div id="map" style="width:100%;height:300px; margin: 0 auto; border-radius:10%;"></div>
			</div>
			
			<div class="col-sm-4 bmi_cal">
			<h3 class="hidden">BMI계산기</h3>
				<div>
				<input type="text" id="body_height" class="form-control" placeholder="당신의 키를 입력해주세요">
				</div>
				<div>
				<input type="text" id="body_weight" class="form-control" placeholder="당신의 몸무게를 입력해주세요">
				</div>
				<div class="text-right">
				<input type="button" id="body_check" value="확인" class="btn btn-default">
				</div>
				<div>
					<p class="text-center" id="body_result"> </p>
				</div>
			</div>
			
			
			<!-- boardArticle -->
			<div class="col-sm-4">
				<!-- Notice -->
				<h3 class="hidden">공지사항</h3>	
				<div class="notice">
					<ul class="nav nav-tabs">
					  <li class="active"><a data-toggle="tab" href="#notice">공지사항</a></li>
					  <li><a href="notice.board">공지사항 더보기</a></li>
					</ul>
					
					<!-- Tab -->
					<div class="tab-content" style="width:100%;padding-bottom:0;">
					  <div id="notice" class="tab-pane fade in active">
						<ul id="notice_content">		</ul>
					  </div>	
					</div><!-- End Tab -->
					
					<div style="float:right; margin-right:20px;"><a href="notice.board">더보기</a></div>
				</div><!-- End Notice -->
			</div><!-- End boardArticle -->
			
		</div><!-- End Wrap -->
	</section><!-- End Section4 -->
		
	<c:if test="${!empty cookie.subpop}">
	<div class="container panel subpop">
		<h3 class="panel-heading">공지사항</h3>
		<p><img src="${pageContext.request.contextPath}/resources/QRCodeImg.jpg" alt="QRcode"></p>
		<p>본 사이트는 상업적 목적이 아닌 <strong>개인포트폴리오</strong> 용도로 제작되었으며,
			홈페이지의 일부 내용과 기타 이미지 등은 출처가 따로 있음을 밝힙니다.</p>
		<p>
			<input type="checkbox" id="subpop" name="subpop">
			<label for="subpop">오늘 하루동안 이 창을 열지 않음</label>
			<input type="button" class="btn btn-default" id="close" value="닫기">
		</p>	
	</div><!-- End Subpop -->
	</c:if>
</div><!-- End Main -->

<link rel="stylesheet" href="https://uicdn.toast.com/chart/latest/toastui-chart.min.css" />
<script src="https://uicdn.toast.com/chart/latest/toastui-chart.min.js"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/tui-grid/latest/tui-grid.css" />
<script src="https://uicdn.toast.com/tui-grid/latest/tui-grid.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05c5fa9445c4a8378d2e8df0581ace62&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/popupCookie.js"></script>
<script>
$(document).ready(function(){
	getNotice();
	getGrid();
	drawChart();
	setSelect();
    
	
	$(".recommend").click(function(){
		let formdata = this.value.split(" ")[0];
		$("#rec_val").attr("value",formdata);
		$(".rec_send").submit();
	})
	
	
	// 공지사항 팝업 버튼 함수
	$("#close").click(function(){
		if($("#subpop:checked").length == 1) {setCookie("subpop","subpop",1)}
		$(".subpop").hide();
	});
	
	// BMI 계산 함수
	$("#body_check").on("click",function(){
		 $.ajax({
			 url:"${pageContext.request.contextPath}/BMICal",
			 type:"get",
			 data:{"height":$("#body_height").val(),weight:$("#body_weight").val()},
			 success:function(data){
				 $("#body_result").html(data);
			 }
		 })
	 })
	
	 //카카오 지도
	 var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	 var mapContainer = document.getElementById('map'), 
	     mapOption = {
	         center: new kakao.maps.LatLng(37.566826, 126.9786567), 
	         level: 3 
	     };  
	 var map = new kakao.maps.Map(mapContainer, mapOption); 
	 var ps = new kakao.maps.services.Places(); 
	 
	$("#search").on("click",function(){
		 // (입력값 + '헬스장')을 검색어로 입력합니다. 
		 ps.keywordSearch($("#search_val").val()+'헬스장', placesSearchCB);
	});
 
	 function placesSearchCB (data, status, pagination) {
	     if (status === kakao.maps.services.Status.OK) {
	         var bounds = new kakao.maps.LatLngBounds();
	
	         for (var i=0; i<data.length; i++) {
	             displayMarker(data[i]);    
	             bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
	         }       
	         map.setBounds(bounds);
	     } 
	 }
 
	 function displayMarker(place) {
	     var marker = new kakao.maps.Marker({
	         map: map,
	         position: new kakao.maps.LatLng(place.y, place.x) 
	     });
	     kakao.maps.event.addListener(marker, 'click', function() {
	         infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
	         infowindow.open(map, marker);
	     });
	 }
 	// 공지사항 Ajax
	function getNotice(){
		$.ajax({
			url:"getlist.board",
			type:"get",
			data:{c:"2"},
			dataType:"json",
			success:function(data){
				var ul ="";
				$("#notice_content").html("");
				for(var i=0;i<5;i++){
					ul +="<li><a href=detail.board?c=2&bno="+data.list[i].bno+">"+data.list[i].btitle+"</a>";
					ul +="<span class='date'>"+data.list[i].bdate+"</span></li>";
					if(i==data.list.length-1){break;}
				}
				$("#notice_content").html(ul);
			}
		});
	} 
 	
	function drawChart() {
		$.ajax({
			url:"ajax/treemap",
			type:"GET",
			datatype:"json",
			success:function(result){
				const el = document.getElementById('chart');
				const options = {
						chart: { title:{text:'난이도별 운동부위 수(TreeMap Chart)'},width: 560, height: 385 },
						series: { dataLabels: { visible: true },zoomable: true },
						theme: {
						    title: {
						      fontSize: 20,
						      fontWeight: 400
						    },
						    series: {
						        dataLabels: {
						          fontSize: 20,
						          fontWeight: '800',
						          borderWidth:100
								}
							}
						  }
						};
				const data = JSON.parse(result);				
				const chart = toastui.Chart.treemapChart({el, data, options});
			}
		});

    }
 	
	function getGrid(){
		$.ajax({
			url:"ajax/grid",
			type:"GET",
			datatype:"json",
			success:function(result){
				var gridData = JSON.parse(result);
				
				const grid = new tui.Grid({
				      el: document.getElementById('grid'),
				      data: gridData,
				      scrollX: false,
				      bodyHeight: 315,
				      columns: [
				        {
				          header: '난이도',
				          name: 'level',
				          filter: 'select'
				        },
				        {
				          header: '근육부위',
				          name: 'depth1',
				       	  filter: 'select'
				        },
				        {
				          header: '운동자료수',
				          name: 'count',
				          filter: 'select'
				        }
				      ]
			    	});//End grid
				} // End Anonymous function
		});// End Ajax
	}
	
	function setSelect(){
		$.ajax({
			url:"getNavMuscle.board",
			type:"GET",
			async:false,
			success:function(data){
				var list = JSON.parse(data);
				var keys = new Set();
				$.each(list, function(key, value){
				    $.each(value, function(key, value){
				        keys.add(key);
				    });
				});
				$("#select_muscle").html();
				var li = "";
				keys.forEach(function(value){ li += "<option value='"+value+"'>"+value+"</option>"; }
				);
				$("#select_muscle").append(li);
			}
		})
	}
});
</script>
<%@include file="/WEB-INF/inc/footer.jsp"%>