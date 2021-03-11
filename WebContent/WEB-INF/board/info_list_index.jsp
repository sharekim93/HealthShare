<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="sectionWrap">
	<h5 style="color:gray;">원하시는 항목을 체크해보세요</h5>
		<div class="panel-group">
			<div class="panel panel-default index_filter">
				<div class="panel-heading">
					<h4 class="panel-title">난이도별</h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
					<ul class="list-group" id="index_level">

					</ul>
				</div>
			</div>			
		</div>		
		<div class="panel-group">
			<div class="panel panel-default index_filter">		
				<div class="panel-heading">
					<h4 class="panel-title">운동부위</h4>
				</div>

				<div id="collapse2" class="panel-collapse collapse in">
					<ul class="list-group" id="index_muscle">
						
					</ul>
				</div>
			</div>			
		</div>			
		
		<div class="filter-body">

		</div>
		<!-- hidden value -->
		<form id="hidden">
			<input type="hidden" name="bpass" id="bpass" value="">
			<input type="hidden" name="bno" id="bno" value="">
			<input type="hidden" name="c" id="c" value="5">
			<input type="hidden" id="m" value="${(empty param.muscle)? '':param.muscle}">
		</form>
	</div>
<script>	
	$(document).ready(function(){
		setNav();
		
		$(".filter").on("click",function(){
			setFilterData();
			if(map.size>0){
				setFilter(json,pageNum,field,query);
				setIndex(pageNum,pageAll);
				setPager(pageNum,startNum,lastNum);
			}
			else{$(".filter-body").html("");}
		});
	});
	var pageAll		= 1;
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
	
	function setNav(){
		$.ajax({
			url:"ajax/navlevel",
			type:"GET",
			async:false,
			success:function(data){
				var list = JSON.parse(data);
				var li = "";
				$("#index_level").html();
				for(i in list){
				  li += "<li class='list-group-item'>";
				  li += "<input type='checkbox' id='"+list[i]+"' value='"+list[i]+"' class='filter index_level'>";
				  li += "<label for='"+list[i]+"'>"+list[i]+"</label></li>";
				}
				
				$("#index_level").html(li);
			}
		})
		
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
				$("#index_muscle").html();
				var li = "";
				keys.forEach(function(value){
					li += "<li class='list-group-item'>";
					li += "<input type='checkbox' id='"+value+"' value='"+value+"' class='filter index_muscle'";				
					if(m == value){li+="checked >";} else {li+=">"}
					li += "<label for='"+value+"'>"+value+"</label>";	
					li += "</li>";
				}
				);
				$("#index_muscle").html(li);
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
				if($(this).is(".index_muscle")==true){temp2="depth1";}
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
					
					let body = "<table class='table table-striped'><thead><tr>";
					body +="<th scope='col' class='text-center'>난이도</th>";
					body +="<th scope='col' class='text-center'>운동부위</th>";
					body +="<th scope='col'>운동명</th>";					
					body +="<th scope='col'>작성자</th>";
					body +="<th scope='col'>업로드일자</th>";
					body +="<th scope='col'>조회수</th>";
					body +="</tr></thead><tbody></tbody></table>";
					body +="<div class='margin-top text-center'>";
					body +="<div class='pagination'></div></div>";
					
					$(".filter-body").html(body);
					
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
	
