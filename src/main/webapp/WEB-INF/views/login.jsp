<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 다운받은 제이쿼리 연결 -->
<script src="/myapp/resources/js/jquery-3.6.0.js" type="text/javascript"></script>
<%@ include file="includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link href="${path}/resources/css/tableStyle.css" rel="stylesheet">

<script type="text/javascript">
	if('${msg}'!=''){
		alert('${msg}');
	}


	$(function() {
		$('.submit-btn').on('click', function() {
			
			var userid = $('#userid').val();
			var userpw = $('#userpw').val();
			
			if(userid==''){
				alert('아이디를 입력해주세요.');
			}else if(userpw==''){
				alert('비밀번호를 입력해주세요.');
			}else{
				$('#frmLogin').submit();
			}
			
/* 			attr('action', '${path}/login?userid='+userid+'&userpw='+userpw) ==> 매개변수 아이디,아이디 비밀번호,비밀번호 ??*/
			
		});
	});
	

</script>
</head>
<body>
	<h1>로그인</h1>
	<form id="frmLogin" action="${path}/login" method="post">
		<table border="1" id="customers">
			<tr>
				<th>아이디</th>
				<td> <input type="text" name="userid" id="userid"> </td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td> <input type="password" name="userpw" id="userpw"> </td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
					<button type="button" id="btnLogin">로그인</button> 
					<a href="${apiURL}"><img height="35" src="${path}/resources/images/btnNaver.png"/></a> <!-- 네이버로그인창 -->
				</td>
			</tr>
		</table>
	</form>

		
		
		
		
		
		
		
		
		
		
</body>
</html>