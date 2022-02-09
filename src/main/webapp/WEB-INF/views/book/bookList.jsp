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
	$(function() {
		$('.alist').on('click', function(e){
			
			e.preventDefault(); //href의 기본이벤트(페이지이동) 삭제
			var curPage = $(this).attr('href');//내가 클릭한 엘리멘트'
			
			location.href='${path}/book/serch?bname=${bname}&curPage='+curPage;
		});
	    
	});

</script>
</head>
<body>
	<h1>검색도서 리스트</h1>
	<br><br>
	
	<div>
		<table border="1" id="customers">
			<tr>
				<th>이미지</th>
				<th>이름</th>
				<th>저자</th>
				<th>출판사</th>
				<th>별점</th>				
			</tr>
			<c:forEach var="book" items="${blist}">
				<tr>
					<td> 
						<a href="${path}/book/bookInfo?isbn=${book.isbn}">
							<img alt="" src="${book.smallfileurl}" width="100">
						</a> 
					 </td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.publisher}</td>
					<td>${book.reviewrank}</td>		
				</tr>
			</c:forEach>
		</table>
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