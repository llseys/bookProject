<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>



<!DOCTYPE html>
<html>
<head>
<title>Home</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="favicon.ico">
	<title>Treviso - Clean & Elegant Onepage Multipurpose Bootstrap HTML</title>
	<!-- Bootstrap core CSS -->
	<link href="${path}/resources/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<!-- Custom styles for this template -->
	<link href="${path}/resources/css/style.css" rel="stylesheet">
	
	
	<style type="text/css">
		.bookSerch{
			display: flex;
			justify-content: center;
		}

	</style>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript">

		$(function() {
			$('#bestbook').on('click', function() {
				alert('ㅇㅇ');
			});
			
			
		});
	
	
	
	
	
	
	
	
	</script>
</head>
<body>

  <!-- Navigation -->
		<nav class="navbar navbar-default">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header page-scroll">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand page-scroll" href="#page-top"><span class="icon-bar">MY BOOK</span></a>
	<%-- 				<img src="${path}/resources/images/logo.png" alt="Treviso theme logo"> --%>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li class="hidden">
							<a href="#page-top"></a>
						</li>
						
						

						<li>
							<a  class="page-scroll" href="${path}/member/modify?email=${sessionScope.userid}">${sessionScope.userid}님 반갑습니다.</a>  
						</li>
						
						<li>
							<a class="page-scroll" href="#about">로그인</a>
						</li>
						<li>
							<a class="page-scroll" href="#portfolio">회원가입</a>
						</li>
						<li>
							<a class="page-scroll" href="#team">좋아요</a>
						</li>
						<li>
							<a class="page-scroll" href="#contact">고객센터</a>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		
		
		<div class="bookSerch">
			<div >
				<input type="text" id="bname" size="30">
				<button id="btnSerch">검색</button>
			</div>
		</div>
		
		
		<section id="portfolio">
			<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="section-title">
						<!-- <h2>Our Works</h2>
						<p>Our portfolio is the best way to show our work, you can see here a big range of our work. Check them all and you will find what you are looking for.</p> -->
					</div>
				</div>
			</div>
			<div class="row row-0-gutter">
				<!-- start portfolio item -->
				<div class="col-md-6 col-0-gutter">
					<div class="ot-portfolio-item">
						<figure class="effect-bubba">
							<img src="${path}/resources/images/demo/books-g2df376b8a_640.jpg" alt="img02" class="img-responsive" />
							<figcaption id="bestbook">
								<h2>베스트 셀러</h2>
								<p>Best seller</p>
							</figcaption>
						</figure>
					</div>
				</div>
				<!-- end portfolio item -->
				<!-- start portfolio item -->
				<div class="col-md-6 col-0-gutter">
					<div class="ot-portfolio-item">
						<figure class="effect-bubba">
							<img src="${path}/resources/images/demo/notebook-gc9e023da0_640.jpg" alt="img02" class="img-responsive" />
							<figcaption>
								<h2>신규 도서</h2>
								<p>New book</p>
							</figcaption>
						</figure>
					</div>
				</div>
				<!-- end portfolio item -->
			</div>
			<div class="row row-0-gutter">
				<!-- start portfolio item -->
				<div class="col-md-6 col-0-gutter">
					<div class="ot-portfolio-item">
						<figure class="effect-bubba">
							<img src="${path}/resources/images/demo/gfd675c671_640.jpg" alt="img02" class="img-responsive"id="reviewlist" />
							<figcaption>
								<h2>리뷰 작성</h2>
								<p>Review</p>
								<a href="#" data-toggle="modal" data-target="#Modal-3">View more</a>
							</figcaption>
						</figure>
					</div>
				</div>
				<!-- end portfolio item -->
				<!-- start portfolio item -->
				<div class="col-md-6 col-0-gutter">
					<div class="ot-portfolio-item">
						<figure class="effect-bubba">
							<img src="${path}/resources/images/demo/laptop-gf2c145cf9_640.jpg" alt="img02" class="img-responsive"id="inquiry" />
							<figcaption>
								<h2>문의사항</h2>
								<p>Inquiry</p>
								<a href="#" data-toggle="modal" data-target="#Modal-4">View more</a>
							</figcaption>
						</figure>
					</div>
				</div>
			<!-- end portfolio item -->
			</div>
			</div><!-- container -->
		</section>

		


		<!-- Bootstrap core JavaScript
			================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->

		<script src="${path}/resources/js/bootstrap.min.js"></script>
		<script src="${path}/resources/js/SmoothScroll.js"></script>
		<script src="${path}/resources/js/theme-scripts.js"></script>



		<hr>
		
		<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=n49nsibd8d"></script>
		<div id="map" style="width:300px;height:300px;"></div>
		<script>
		// 맵지도
		var mapOptions = {
		    center: new naver.maps.LatLng(37.484878, 126.930433),
		    zoom: 17
		};
		var map = new naver.maps.Map('map', mapOptions);
		</script>
		
		
		
		
		<div id="under1" style="font-size: 20px;">
		<br>
			Contact Detail
		</div>
		
		<div id="under2" style="font-size: 15px;">
		<br>
			Addr : 서울 특별시 관악구 신림로 340 6층<br>
			Tel : 010-1111-1111 <br>
			Fax : +82-31-000-000 <br>
			Email : llseys@naver.com <br><br>
		</div>

		
		
























	
</body>
</html>