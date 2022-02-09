<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../includeFile.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if('${msg}'!=''){
		alert('${msg}');
	}

	// jusoPopup.jsp에서 실행할 함수
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.frmModify.zipcode.value = zipNo; //우편
		document.frmModify.addr.value = roadAddrPart1; //주소
		document.frmModify.addrdetail.value = addrDetail; //상세주소
	}

	$(function() {
		// 주소창 팝업
		$('#findAddr').on('click',function(){
			// (jsp경로(원래는 /app/WEB-INF/views/member/jusioPopup.jsp),'창의이름(암거나)')
			//   ㄴWEB-INF 하위 폴더는 직접접근불가 하기에 컨트롤러를 이용
			var pop = window.open('/app/member/jusoPopup','popup','width=570,height=420,scrollbars=yes,resizable=yes');
			// ㄴpop 리턴될때 주소가 필요하다
		});
		
		
		// 수정버튼 클릭
		$('#btnModi').on('click',function(e){
			e.preventDefault();
			var result = confirm('이대로 수정하시겠습니까?');
			
			var userid = $('#userid').val();
			var userpw = $('#userpw').val();
			var nicname = $('#nicname').val();
			var username = $('#username').val();
			var tel = $('#tel').val();
			
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
				$('#frmModify').attr('action','${path}/member/modify').submit();
			}
			
	
		});
		
		
		$('#btnRemove').on('click',function(e){
			e.preventDefault();
			var result = confirm('정말 탈퇴하시겠습니까?');
			
			if(result){
				var userid = $('#userid').val();
				var userpw = $('#userpw').val();
				if(userpw==''){
					alert('비밀번호를 입력해주세요');
					return;
				}
				location.href='${path}/member/remove?userid='+userid+'&userpw='+userpw;
			}
		});
		
		
		
		
	});

</script>



</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>수정하기</h2>
	<form name="frmModify" id="frmModify" action="" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td colspan="2"> <input type="text" id="userid" name="userid" value="${member.userid}" readonly> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td colspan="2"> 
					<input type="password" name="userpw" id="userpw" value=""> <br> 
				</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td colspan="2"> <input type="text" id="nicname" name="nicname" value="${member.nicname}"> </td>
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="2"> <input type="text" id="username" name="username" value="${member.username}"> </td>
			</tr>
			<tr>
				<th>이메일</th>
				<td colspan="2"> <input type="email" id="email" name="email" value="${member.email}" > </td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td colspan="2"> <input type="text" id="tel" name="tel" value="${member.tel}" > </td>
			</tr>
			
			
			<tr>
				<th>주소</th>
				<td colspan="2"> 
					<input type="text" name="zipcode" size="6" value="${member.zipcode}"> 
					<button type="button" id="findAddr">찾기</button> <hr>
					<input type="text" name="addr" value="${member.addr}"> <br>
					<input type="text" name="addrdetail" value="${member.addrdetail}">
				 </td> 
			</tr>

			<tr>
				<td colspan="3" align="center"> 
					<button type="button" id="btnModi">수정</button>
					<button type="button" id="btnRemove">회원탈퇴</button>
				</td>
			</tr>						
		</table>
	</form>
</body>
</html>