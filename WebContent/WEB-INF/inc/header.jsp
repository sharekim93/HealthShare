<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>열정적인 코딩계의 공룡개발자! 나눔</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
  nav.navbar.gnb   { padding: 10px 0;  margin: 0; }
  nav.navbar.gnb a { font-size: 22px; font-weight: bold; }
  nav.navbar.gnb a.first { color: darkorange; }
  .form-group.myform { border: 1px solid #337ab7; }
  input#header_search { border: 0 none;  box-shadow: none;  }
  button.header_form_go { border: 0 none; background-color: #337ab7; color: white; padding: 8px; }
  nav.navbar.gnb .nav>li>a:focus,   nav.navbar.gnb .nav>li>a:hover {
    text-decoration: underline;
    background-color:white; 
  }
  
  
  nav.navbar.lnb { background-color: #f3f3f3; margin:0;}
  nav.navbar.lnb  a{ color:#333;   font-weight:bold; }
  nav.navbar.lnb   nav.navbar.gnb .nav>li>a:focus, nav.navbar.lnb  .nav>li>a:hover {
    text-decoration: underline;
    background-color:#ccc; 
    }
  
  footer {height:50px;}
  footer > div {margin-top:30px;}  
    
    #main_panel{margin-top:40px;}
	#login_panel{margin-top:40px; width:400px;}
	#loginImage{width:90%;}
	#main {
		width: 100%;
		min-width:1280px;
	}
	#main .section1{
		width: 100%;
		height: 400px;
		margin : 0 auto;
		background-color: royalblue;
	}
	
	#main .section2{
		width: 100%;
		height:400px;
		margin: 0 auto;
	}
	
	#main .section3{
		width: 100%;
		height:530px;
		background-color:#f3f4f4;
		margin: 10px auto;

	}
	#main .section4{
		width: 100%;
		height:315px;
		margin: 10px auto;
	}
	
	#main .section5{
		width: 100%;
		height:600px;
		background-color:#f3f4f4;
		margin: 0 auto;
		padding: 10px auto;
	}
	.sectionWrap{margin:0 auto; width:1200px; padding-top:10px;}
	
	.main_search{
		border:2px; 
		display:flex; 
		margin:40px auto 0; 
		width:40%;}
	.title_center{
		text-align:center;
	    font-size: xx-large;
	    font-weight: 600;
	    margin-top:100px;
	    color:white;
	    font-family:auto;
    }
    
	.board{margin-top:20px;}
	.boardArticle{float:right;margin:20px auto;height:315px;border-radius:1%;}
	.right_p{float:right;}
	.left_p{float:left;}
	.bmi_cal{
		width:560px;
	}
	.bmi_cal > div{
		margin-top:10px;
		width: 300px;
    	height: 30px;	
	}
	.bmi_cal > div > input{width:450px;}
	.bmi_cal > div > p{
		margin:70px 0 0 0;
		width: 450px;
	}
	#body_result{
		font-size:30px;
		font-weight:600;
		}
	.picture{margin:0 auto;}
	.title{
		text-align:left;
	    font-size: xx-large;
	    font-weight: 400;
	    width:1200px;
	    margin :20px auto;
	}
	.table {margin-top:10px;}
	.table>tbody>tr>td{vertical-align:middle;}
	.btn_func{margin:1px;}
	.indexer{text-align:right; margin-right:10px;}
	.pagination {
	  display: inline-block;
	}
	.pagination a {
	  color: black;
	  float: left;
	  padding: 8px 16px;
	  text-decoration: none;
	  transition: background-color .3s;
	}
	.pagination a.active {
	  background-color: #555555;
	  color: white;
	  font-weight:bold;
	}
	.pagination a:hover:not(.active) {background-color: #ddd;}
	#search{background-color: #555555; color:white;}
	.carousel{text-align: -webkit-center;}
	#myCarousel .carousel-inner .item > img {width:100%;}
	.carousel-control{
		opacity:0;background-image:none;
	}
	.boardArticle{
		position: relative;
		margin-top:0;
	    border: 1px solid #e6e6e6;
	    background-color: #fff;}
	.tab-content {padding: 15px 20px; display:block;}
	.tab-content .tab-pane ul{list-style: none;padding:0;}
	.tab-content li {
		overflow: hidden;
	    position: relative;
	    padding-right: 90px;
	    line-height: 34px;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    display: list-item;
	    text-align: -webkit-match-parent;
	    margin:0;
	}
	.tab-content li a{color:#333;text-decoration:none;}
	.tab-content li .date{
		position: absolute;
	    top: 0;
	    right: 0;
	    color: #666;
	}
	
	.secret_pass{
	    margin: 0 auto;
	    padding: 100px 0;
	    width: 500px;
	}
	.secret_pass p{
	    padding: 20px;
	    border: 1px solid #dde4e9;
	    border-bottom: 0;
	    background: #fff;
	    margin:0;
	}
	.secret_pass fieldset{
	    margin: 0 0 30px;
	    padding: 30px 0;
	    border: 1px solid #e9e9e9;
	    background: #f5f6fa;
	    text-align: center;
	}
	.btn_back{float:right;}
	.btn_back a{
		margin-left: 7px;
	    padding: 10px 12px 10px 11px;
	    font-size: 14px;
	    border-color: #ddd;
	    border-radius: 0;
	    position: relative;
	    width: auto;
	    display: inline-block;
	    *display: inline;
	    vertical-align: middle;
	    text-decoration: none !important;
	    color: #000;
	    font-size: 12px;
	    line-height: 100%;
	    font-weight: normal;
	    line-height: 12px;
	    text-decoration: none;
	    overflow: visible;
	    text-align: center;
	    position: relative;
	    display: inline-block;
	    background: #f8f8f8;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    border-radius: 3px;
	    box-sizing: border-box;
	    border: solid 1px #c0c4cb;
	    border-bottom: 1px solid #a8aeb6;
	    white-space: nowrap;
	    vertical-align: top;
	    cursor: pointer;
	    overflow: visible;
	    zoom: 1;
	}
	.vertical-nav{
		margin: 10px 10px 0 10px;
		width:190px;
		min-height:600px;
	}
	.vertical-nav ul {
	  list-style-type: none;
	  margin: 0;
	  padding: 0;
	  width: 190px;
	  background-color: #f1f1f1;
	  border-radius:4%;
	}
	
	.vertical-nav li a {
	  display: block;
	  color: #000;
	  padding: 0;
	  text-decoration: none;
	}
	
	.vertical-nav li a:hover {
	  background-color: #555;
	  color: white;
	} 
	
	.wrapper{display:flex; margin:0 160px 0 0; width:1200px; padding-top:20px;}
	.wrapper h3{margin:0;}
	.wrapper .container {padding:0; margin-left:50px;}

  }  
  </style>
  
</head>
<body>

<nav class="navbar gnb">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index"  >HEALTH SHARE</a>
    </div>
    <form class="navbar-form navbar-left" action="info.board" method="post">
      <div class="form-group  myform">
        <input type="text"  title="검색어를 입력하세요"  id="header_search" name="q"  class="form-control" placeholder="운동방법 검색하기">
        <button type="submit"  class="header_form_go"><span class="	glyphicon glyphicon-search"></span></button>
      </div>
    </form>
    
    <ul class="nav navbar-nav navbar-right">
		<li><a href="info.board" class="first">운동정보</a></li>
		<li><a href="list.pic">회원사진</a></li>
		<li><a href="free.board">자유게시판</a></li>
		<li><a href="secret.board">비밀글게시판</a></li>
    </ul>
  </div>
</nav>
<nav class="navbar lnb">
  <div class="container-fluid">
<!--     <ul class="nav navbar-nav">
      <li><a href="#">전체</a></li>
      <li><a href="#">나신사1</a></li>
      <li><a href="#">나신사2</a></li>
      <li><a href="#">나신사3</a></li>
      <li><a href="#">나신사4</a></li>
    </ul> -->
	<ul class="nav navbar-nav navbar-right">
	<c:choose>
		<c:when test="${!empty sessionScope.id}">
			<li><a href='mypage.do'><strong>${sessionScope.id}님의 마이페이지</strong></a></li>
			<li><a href='logout.do'><strong>로그아웃</strong></a></li>
			<li><a href="index.cs"><strong>고객센터</strong></a></li>
		</c:when>
		<c:otherwise>
			<li><a href='main.do'><strong>로그인</strong></a></li>
			<li><a href='join_agree.do'><strong>회원가입</strong></a></li>
			<li><a href="index.cs"><strong>고객센터</strong></a></li>
		</c:otherwise>
	</c:choose>
	</ul>
  </div>
</nav> 
 
 <!-- https://www.w3schools.com/howto/howto_css_subnav.asp -->