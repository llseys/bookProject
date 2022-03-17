<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../includeFile.jsp" %>
    <link href="${path}/resources/css/tableStyle.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	if('${msg}'!=''){
		alert('${msg}');
	}
	
	$(function() {
		$('.alist').on('click', function(e){
			e.preventDefault(); //href의 기본이벤트(페이지이동) 삭제
			var curPage = $(this).attr('href');//내가 클릭한 엘리멘트'
			
			location.href='${path}/inquiry/list?curPage='+curPage;
		});
	});
	

	
</script>
<style type="text/css">
	.section-title{
		padding-left: 700px;
		padding-right: 700px;
	}
	
	.pagination{
		display: flex;
		justify-content: center;
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
	
	#inquiryAdd{
		margin-left: 280px;
	
	
	}
	a:link {
	  color : black;
	}
	a:visited {
	  color : black;
	}
	
	
</style>
</head>
<body>
	<h1>문의사항</h1>
	<br><br><br>
	<a id="inquiryAdd" href="${path}/inquiry/add">글쓰기</a>
	
<%-- 	<c:forEach var="inq" items="${inqList}">
		${inq.inquiryno} 
		<a href="${path}/inquiry/detail?inquiryno=${inq.inquiryno}">${inq.subject}</a><br>
	</c:forEach> --%>
	
	
	<table id="customers">
		<tr>
			<td>no</td>
			<td>subject</td>
			<td>name</td>
			<td>date</td>
			<td>hit</td>
		</tr>
		<c:forEach var="inq" items="${inqList}">
			<tr>
				<td>${inq.inquiryno}</td>
				<td><a href="${path}/inquiry/detail?inquiryno=${inq.inquiryno}">${inq.subject}</a></td>
				<td>${inq.userid}</td>
				<td>${inq.regidate}</td>
				<td>${inq.readhit}</td>
			</tr>
		</c:forEach>
	</table>


	
	
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