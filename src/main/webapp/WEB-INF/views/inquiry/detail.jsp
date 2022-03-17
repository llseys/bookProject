<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$('#btnModi').on('click', function() {
			location.href='${path}/inquiry/modify?inquiryno=${inq.inquiryno}'
		});
		$('#btnRemove').on('click', function() {
			location.href='${path}/inquiry/remove?inquiryno=${inq.inquiryno}'
		});
		
	});
	
</script>
</head>
<body>
	<h2>문의글 상세조회</h2>
	<button type="button" id="btnModi">수정</button>
	<button type="button" id="btnRemove">삭제</button>
	<table border="1">
		<tr>
			<td>no</td>
			<td>subject</td>
			<td>content</td>
			<td>name</td>
			<td>hit</td>
		</tr>
		<tr>
			<td>${inq.inquiryno}</td>
			<td>${inq.subject}</td>
			<td>${inq.content}</td>
			<td>${nicname}</td>
			<td>${inq.readhit}</td>						
		</tr>
	</table>
	
</body>
</html>