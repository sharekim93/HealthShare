<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/header.jsp" %>
	<div class="wrapper">
	<h2 class="hidden">운동정보</h2>
		<div class="panel-group vertical-nav">
			<h3 class="hidden">운동필터</h3>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">난이도별</h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
					<ul class="list-group">
						<c:forEach var="i" items="${levels}" varStatus="st">
						<li class="list-group-item">
							<label for="${i}">${i}</label>
							<input type="checkbox" id="${i}" value="${i}" class="filter level" ${(empty param.level)? "":((i eq param.level)? "checked":"")}>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="panel-heading">
					<h4 class="panel-title">운동부위</h4>
				</div>

				<div id="collapse2" class="panel-collapse collapse in">
					<ul class="list-group" id="muscle">
						
					</ul>
				</div>
			</div>			
		</div>
		<div class="container panel board">
			<h3>운동정보</h3>
			<div class="search-form margin-top">
				<h4 class="hidden">검색 폼</h4>
				<div class="table-form" >
					<fieldset style="text-align:right;">
					<legend class="hidden">검색 필드</legend>
					<label class="hidden"> 검색 분류</label>
					<input type="hidden" name="c" value="${(!empty param.c)? param.c:'1'}"/>
					<select name="f" id="sch_field">
						<option value="btitle">제목</option>
						<option value="bname">작성자</option>
					</select>
					<label class="hidden">검색어</label>
					<input type="hidden" name="p" value="${(empty param.p)? 1:param.p }"/>
					<input type="text" name="q" id="q" value="${param.q}">
					<input type="button" id="search" value="검색" class="btn btn-search">
					</fieldset>
				</div>		
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col" class="text-center">난이도</th>
						<th scope="col" class="text-center">운동부위</th>
						<th scope="col">운동명</th>						
						<th scope="col">작성자</th>
						<th scope="col">업로드일자</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			<!-- 페이저 -->
		<div class="margin-top text-center">
			<div class="pagination">		</div>
		</div>
		</div>
	</div>
	<!-- hidden value -->
	<form id="hidden">
		<input type="hidden" name="bpass" id="bpass" value="">
		<input type="hidden" name="bno" id="bno" value="">
		<input type="hidden" name="c" id="c" value="5">
		<input type="hidden" id="m" value="${(empty param.muscle)? '':param.muscle}">
	</form>
<script>	
	$(document).ready(function(){
		
		getboard(pageNum,field,query,category);
		setIndex(pageNum,pageAll);
		setPager(pageNum,startNum,lastNum);
		setNav();
		
		$(".btn-search").click(function(){
			field=$("#sch_field").val();
			query=$("#q").val();
			setFilterData();
			setFilter(json,pageNum,field,query);
		});
		
		$(".filter").on("click",function(){
			setFilterData();
			setFilter(json,pageNum,field,query);
		});
	});
	
	var pageNum 	= 1; // 현재 페이지
	var startNum 	= 1;
	var lastNum 	= 1;
	var totalCnt	= 1; // 총 게시글 수
	var listCnt		= 1; // 페이저에 나타날 개수
	var pageLimit 	= 1; // 한 페이지에 보일 레코드 수
	var query 		= $("#q").val();
	var field 		= $("#sch_field").val();
	var category 	= $("#c").val();
	var map			= new Map();
	var json		= "";
	var filter		= false;
	var m			= $("#m").val();
	var temp		="";
	var temp2		="";
	
	function getboard(pageNum,field,query,category){
		$.ajax({
			url:"getlist.board",
			type:"get",
			async:false,
			data:{p:pageNum,f:field,q:query,c:category},
			datatype:"json",
			success:function(data){
				let table ="";
				let obj = JSON.parse(data);

				pageNum = obj.page;
				pageAll = obj.pageAll;
				startNum = obj.startNum;
				lastNum	 = obj.lastNum;
				
				$(".table tbody").html("");
				for(i in obj.list){
					table +="<tr>";
					table +="<td class='text-center'>" + obj.list[i].level + "</td>";
					table +="<td class='text-center'>" + obj.list[i].depth1 + "</td>";
					table +="<td><a href='detail.board?bno="+obj.list[i].bno+"&c="+category+"'>" + obj.list[i].btitle + "</a></td>";
					table +="<td>" + obj.list[i].bname + "</td>";
					table +="<td>" + obj.list[i].bdate + "</td>";
					table +="<td>" + obj.list[i].bhit + "</td>";
					table +="</tr>";
				}
				$(".table tbody").html(table);
				setIndex(pageNum,pageAll);
				setPager(pageNum,startNum,lastNum);
			}
		});
	};
	
	function setNav(){
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
				$("#muscle").html();
				var li = "";
				keys.forEach(function(value){
					li += "<li class='list-group-item'>";
					li += "<label for="+value+">"+value+"</label>";
					li += "<input type='checkbox' id="+value+" value='"+value+"' class='filter muscle'";
					if(m == value){li+="checked >";} else {li+=">"}
					li += "</li>";
				}
				);
				$("#muscle").html(li);
				setFilterData();
				setFilter(json,pageNum,field,query);
			}
		})
	}
	
	function setFilterData(){
		filter=true;
		map.clear();
		pageNum=1;
		$(":checkbox").each(function(){
			if(this.checked){
				temp = this.value;
				if($(this).is(".muscle")==true){temp2="depth1";}
				else{temp2="level";}
				map.set(temp,temp2);
				}
		});
		json="[";
		let first = true;
		map.forEach(function(value,key,map){
			if(first){first=false;}
			else{json+=",";}
			json += "{\""+value+"\":\""+key+"\"}";
		});
		json+="]";
	}
	
	function setFilter(json,pageNum,field,query){
			$.ajax({
				url:"getlist.board",
				type:"post",
				data:{"json":json,"c":category,"p":pageNum,"f":field,"q":query},
				datatype:"json",
				success:function(data){
					let table ="";
					let obj = JSON.parse(data);
					
					pageNum = obj.page;
					pageAll = obj.pageAll;
					startNum = obj.startNum;
					lastNum	 = obj.lastNum;
					
					$(".table tbody").html("");
					for(i in obj.list){
						table +="<tr>";
						table +="<td class='text-center'>" + obj.list[i].level + "</td>";
						table +="<td class='text-center'>" + obj.list[i].depth1 + "</td>";
						table +="<td><a href='detail.board?bno="+obj.list[i].bno+"&c="+category+"'>" + obj.list[i].btitle + "</a></td>";
						table +="<td>" + obj.list[i].bname + "</td>";
						table +="<td>" + obj.list[i].bdate + "</td>";
						table +="<td>" + obj.list[i].bhit + "</td>";
						table +="</tr>";
					}
					$(".table tbody").html(table);
					setIndex(pageNum,pageAll);
					setPager(pageNum,startNum,lastNum);
				}
			});
	}
	
	
	function setIndex(pageNum,pageAll){
		$("#pageIndex").html(pageNum+"/"+pageAll+"&nbsp");		
	}
	
	function setPager(pageNum,startNum,lastNum){
		let pager="";
		if(pageNum>1){ pager += "<a class='btn btn-prev'>이전</a>"; }
		for(let page=startNum;page<=lastNum;page++){ 
			if(pageNum==page){pager+="<a class='active page'>"+  page  +"</a>";}
			else { pager+="<a class='page'>"+  page  +"</a>";} }  /////
		if(pageNum<lastNum){ pager +="<a class='btn btn-next'>다음</a>"; }
		$(".pagination").html(pager);
		
		$(".page").on('click',function(){
			pageNum=$(this).text();  //num
			if(filter){setFilter(json,pageNum,field,query);}
			else getboard(pageNum,field,query,category);
		});
		
		$(".btn-prev").on('click',function(){
			pageNum=pageNum-1;
			if(filter){setFilter(json,pageNum,field,query);}
			else getboard(pageNum,field,query,category);
		});
		
		$(".btn-next").on('click',function(){
			pageNum=Number.parseInt(pageNum)+1;
			if(filter){setFilter(json,pageNum,field,query);}
			else getboard(pageNum,field,query,category);
		});
		
	}
</script>
	
<%@include file="/WEB-INF/inc/footer.jsp" %>