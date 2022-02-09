<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../includeFile.jsp" %>
    <link href="${path}/resources/css/tableStyle.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
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
	.section-title{
		padding-left: 700px;
		padding-right: 700px;
	}
	
	.pagination{
		display: flex;
	}
	
	.pagination a {
	  color: black;
	  float: left;
	  padding: 8px 16px;
	  text-decoration: none;
	}
	
	.pagination a.active {
	  background-color: #4CAF50;
	  color: white;
	}
	
	.pagination a:hover:not(.active) {background-color: #ddd;}
	
	.reviewlist{
		color: black;
		font-size: 16px;
 		font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	}
	
	a:link{
		color: black;
	
	}
</style>


<title>Insert title here</title>
<script type="text/javascript">
	if('${msg}'!=''){
		alert('${msg}');
	}



	$(function() {
		
		$('#btnReviewAdd').on('click', function() {
 			location.href='${path}/book/reviewAdd?isbn=${book.isbn}';
		});
		
		$('.alist').on('click', function(e){
			e.preventDefault(); //href의 기본이벤트(페이지이동) 삭제
			var curPage = $(this).attr('href');//내가 클릭한 엘리멘트'
			location.href='${path}/book/bookInfo?curPage='+curPage+'&isbn=${book.isbn}';
		});
	});

</script>


</head>
<body>
	<h1>도서 상세조회</h1>
	<br><br>



	<table border="1" id="customers">
		<tr>
			<td> <img alt="" src="${book.bigfileurl}" > </td>
			<td>
			 	<table border="1" id="inTable">
					<tr>
						<th>제목</th>
						<td>${book.title} </td>
					</tr>
					<tr>
						<th>저자</th>
						<td>${book.author}</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>${book.category}</td>
					</tr>
					<tr>
						<th>출판사</th>
						<td>${book.publisher}</td>
					</tr>					
					<tr>
						<th>출간일자</th>
						<td>${book.pubdate}</td>
					</tr>					
					<tr>
						<th>별점</th>
						<td>${book.reviewrank}</td>
					</tr>	
					<tr>
						<th>isbn</th>
						<td>${book.isbn}</td>
					</tr>											
				</table>
			 </td>
		</tr>					
	</table>
				<div class="section-title">
					${book.description}
				</div>

	<hr>
	<button type="button" id="btnReviewAdd">리뷰쓰기</button>
	<hr>
	
	<div class="reviewlist">
		<c:forEach var="review" items="${rlist}">
		<a href="${path}/book/reviewInfo?reviewno=${review.reviewno}">${review.content}</a> 
		<hr>
		</c:forEach>
	</div>

	
	
	
	
	 <div class="pagination">
	 	<c:if test="${page.startPage != 1}">
			<a href="${page.startPage-1}" class="alist">&laquo;</a>
		</c:if>
	
		<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		
			<c:if test="${page.curPage==i}">
				<a href="${i}" class="alist active">${i}</a> 
			</c:if>
			<c:if test="${page.curPage!=i}">
				<a href="${i}" class="alist">${i}</a> 
			</c:if>
			
		</c:forEach>
		
		<c:if test="${page.totPage>page.endPage}">
			<a href="${page.endPage+1}" class="alist">&raquo;</a>
		</c:if>
 	
	</div>
	
</body>
</html>