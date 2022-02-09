<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="${path}/resources/css/tableStyle.css" rel="stylesheet">
<title>Insert title here</title>
<!-- 다운받은 제이쿼리 연결 -->
<script src="${path}/resources/js/jquery-3.6.0.js" type="text/javascript"></script>
<script type="text/javascript">

	// jusoPopup.jsp에서 실행할 함수
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.frmJoin.zipcode.value = zipNo; //우편
		document.frmJoin.addr.value = roadAddrPart1; //주소
		document.frmJoin.addrdetail.value = addrDetail; //상세주소
	}
	
	$(function() {
		$('#findAddr').on('click',function(){
			// (jsp경로(원래는 /app/WEB-INF/views/member/jusioPopup.jsp),'창의이름(암거나)')
			//   ㄴWEB-INF 하위 폴더는 직접접근불가 하기에 컨트롤러를 이용
			var pop = window.open('${path}/member/jusoPopup','popup','width=570,height=420,scrollbars=yes,resizable=yes');
			// ㄴpop 리턴될때 주소가 필요하다
		});	
		
		
		
		
		// null 체크
		$('#btnJoin').on('click', function() {
			var userid = $('#userid').val();
			var userpw = $('#userpw').val();
			var nicname = $('#nicname').val();
			var username = $('#username').val();
			var tel = $('#tel').val();
			
			console.log(userid); 
			console.log(userpw);
			console.log(nicname);
			console.log(username);
			
			if(userid==''){
				alert('아이디를 입력해주세요.');
				$('#userid').focus();
			}else if(userpw==''){
				alert('비밀번호를 입력해주세요.');
				$('#userpw').focus();
			}else if(nicname==''){
				alert('닉네임을 입력해주세요.');
				$('#nicname').focus();	
			}else if(username==''){
				alert('이름을 입력해주세요.');
				$('#username').focus();		
			}else if(tel==''){
				alert('전화번호를 입력해주세요.');
				$('#tel').focus();	
			}else{
				$('#frmJoin').attr('action','${path}/member/join').submit();
			}
		});
	});
</script>
</head>
<body>
	<h1>회원가입</h1>
	
	<form name="frmJoin" id="frmJoin" action="" method="post">	
		<table border="1" id="customers">
			<tr>
				<th>아이디</th>
				<td colspan="2"> 
					<input type="text" name="userid" id="userid">
					<span id="useridCheck">아이디확인</span>
				 </td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="userpw" id="userpw"></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>
					 <input type="text" name="nicname" id="nicname"> 
					 <span id="nicnameCheck">닉네임확인</span>
				 </td>
			</tr>
			<tr>
				<th>이름</th>
				<td> <input type="text" name="username" id="username"> </td>
			</tr>	
			<tr>
				<th>이메일</th>
				<td> <input type="email" name="email" id="email"> </td>
			</tr>	
			<tr>
				<th>전화번호</th>
				<td> <input type="text" name="tel" id="tel"> </td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="zipcode" size="6" > 
					<button type="button" id="findAddr">찾기</button>
					<hr>
					<input type="text" name="addr" size="35"> <br>
		
					<input type="text" name="addrdetail"size="35">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnJoin">가입</button>
				</td>
			</tr>
		</table>
	</form>
	

	 ${msg}

	
	
	
</body>
</html>