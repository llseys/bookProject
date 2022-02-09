<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if('${msg}'!=''){
		alert('${msg}');
	}
</script>
</head>
<body>
	${mlist}
	<hr>
	<c:forEach var="member" items="${mlist}">
		<a href="${path}/member/modify?userid=${member.userid}"> ${member.userid}수정폼가기</a> <br>
	</c:forEach>

</body>
</html>