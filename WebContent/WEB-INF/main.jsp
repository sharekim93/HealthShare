<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jsp" %>
<%@page import="com.cafe24.sharekim93.dao.*" %>
<%@page import="com.cafe24.sharekim93.entity.*" %>
<!-- main -->
<div id="main">
	<!-- Section 1 -->
	<div class="section1">
		<h2 class="hidden">Sliding Window 1_ 배너</h2>
		
		<!-- Carousel -->
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
		
		  <!-- Indicators -->
		  <ol class="carousel-indicators">
		  	<c:forEach var="i" items="${bannerList}" varStatus="st">
		    <li data-target="#myCarousel" data-slide-to="st.index" class="${(st.index eq 0)? 'active':'' }"></li>
		    </c:forEach>
		  </ol>
		  
		  <!-- Wrapper for slides -->
		  <div class="carousel-inner " role="listbox">
		  	<c:forEach var="i" items="${bannerList}" varStatus="st">  	
		    <div class="item ${(st.index eq 0)? 'active':'' }">
		      <img src="${pageContext.request.contextPath}/upload/${i.img}" alt="${i.img}">
		    </div>
		    </c:forEach>
	      </div>
	      
		  <!-- Left and right controls -->
		  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="right carousel-control" href="#myCarousel" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right"></span>
		    <span class="sr-only">Next</span>
		  </a>
		  
		</div><!-- End Carousel -->
	</div><!-- End section 1 -->
	
	<!---- title ---->	
	<div class="title">운동자료현황</div>
	
	<!---- Toast ---->
	<div class="section4">
		<div class="sectionWrap">
		    <div class="left_p">
		    <div id="chart"></div>
		    </div>
		    <div class="right_p">
			<div id="grid" style="width: 560px;"></div>
			</div>
		</div>
	</div>
	
	<!-- Section 2 -->
	<div class="section2">
		<h2 class="hidden">회원사진/공지사항</h2>
		<div class="title">
			<div class="row">
				<div class="col-sm-6">회원사진</div>
				<div class="col-sm-6">&nbsp;&nbsp;공지사항</div>
			</div>
		</div>
		<div class="sectionWrap">
			<!-- Left Clearfix -->
			<div class="left_p clearfix">
			<h3 class="hidden">회원사진</h3>	
				<!-- Carousel -->
				<div id="myCarousel2" class="carousel slide picture" data-ride="carousel">
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				  	<c:forEach var="i" items="${list}" varStatus="st">
				    <li data-target="#myCarousel2" data-slide-to="st.index" class="${(st.index eq 0)? 'active':'' }"></li>
				    </c:forEach>
				  </ol>
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner " role="listbox">
				  	<c:forEach var="i" items="${list}" varStatus="st">  	
				    <div class="item ${(st.index eq 0)? 'active':'' }">
				      <img src="${pageContext.request.contextPath}/upload/${i.img}" alt="${i.img}" style="height:315px; border-radius:1%;">
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
			<!-- boardArticle -->
			<div class="boardArticle">
				<!-- Notice -->
				<h3 class="hidden">공지사항</h3>	
				<div class="notice">
					<ul class="nav nav-tabs">
					  <li class="active"><a data-toggle="tab" href="#notice">공지사항</a></li>
					  <li><a href="notice.board">공지사항 더보기</a></li>
					</ul>
					
					<div class="tab-content" style="width:560px;padding-bottom:0;">
					  <div id="notice" class="tab-pane fade in active">
						<ul id="notice_content">		</ul>
					  </div>
	
<!-- 					  <div id="menu1" class="tab-pane fade">
					    <h3>Menu 1</h3>
					    <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
					  </div> -->
	
					</div><!-- End Tab -->
					<div style="float:right; margin-right:20px;"><a href="notice.board">더보기</a></div>
				</div><!-- End Notice -->
			</div><!-- End boardArticle -->
		</div><!-- End Wrap -->
	</div>
	
	<!---- title ---->
	<div class="title">
		<div class="row">
			<div class="col-sm-6">BMI 계산기</div>
			<div class="col-sm-6">&nbsp;&nbsp;우리동네 헬스장</div>
		</div>
	</div>
	
	<!-- Section 3 -->
	<div class="section3">
		<h2 class="hidden">BMI계산기/인근 헬스장</h2>
		<div class="sectionWrap">
			<h3 class="hidden">BMI계산기</h3>
			<div class="left_p bmi_cal">
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
			<h3 class="hidden">인근 헬스장</h3>
			<!-- map -->
			<div class="right_p">
				<div class="text-right" style="display:flex;">
				<input type="text" id="search_val" class="form-control" style="width:90%;" placeholder="법정동명 입력 ex)성수동">
				<input type="button" id="search" value="검색" class="btn btn-primary">
				</div>
				<div id="map" style="width:560px;height:280px;"></div>
			</div>
		</div><!-- End Wrap -->
		
	</div><!-- End Section3 -->

</div><!-- End Main -->

<link rel="stylesheet" href="https://uicdn.toast.com/chart/latest/toastui-chart.min.css" />
<script src="https://uicdn.toast.com/chart/latest/toastui-chart.min.js"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/tui-grid/latest/tui-grid.css" />
...
<script src="https://uicdn.toast.com/tui-grid/latest/tui-grid.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05c5fa9445c4a8378d2e8df0581ace62&libraries=services"></script>
<script>
$(document).ready(function(){
	getNotice();
	getGrid();
	drawChart();
	
	
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
});
</script>
<%@include file="/WEB-INF/inc/footer.jsp"%>