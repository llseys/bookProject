<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="includeFile.jsp" %>

<script type="text/javascript">
	$(function() {
		const userid = '${sessionScope.userid}';
		if(userid == ''){ //로그인 전
			$('#loginInfo').hide();
			$('#logout').hide();
		}else{ //로그인 후
			$('#login').hide();
			$('#join').hide();
		}
	});
</script>
</head>
<body>
	<header>
		<div id="loginInfo">
			<a href="${path}/member/modify?userid=${sessionScope.userid}">${sessionScope.userid}</a>님 반갑습니다.  
		</div>
		<div id="info">
			<a href="${path}/login" id="login">로그인</a>
			<a href="${path}/logout"id="logout">로그아웃</a>
			<a href="${path}/member/join"id="join">회원가입</a>
			<a href="${path}/" id="home">홈</a>
			<a href="${path}/inquiry/list" id="list">고객센터/문의</a> <hr>
		</div>
	</header>
	<nav> 
		<a href="${path}/book/bestbook">베스트셀러</a>
		<a href="${path}/book/newbook">신간도서</a>
	</nav>
</body>
</html>





