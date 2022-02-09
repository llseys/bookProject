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
		// 파일추가 아이콘 클릭
		$('.addfile').on('click', function(){
			$('#fileList').append('<li> <input type="file" name="files"> <i class="far fa-minus-square delfile"></i> </li>');
		});
		
		// 파일삭제 아이콘 클릭
		// 동적으로 생성된 엘리먼트에 이벤트를 달기
		$('#fileList').on('click','.delfile', function(){
			$(this).parent().remove(); // 클릭한거 부모 삭제
		});	
		
		//등록버튼 클릭
		$('#btnAdd').on('click', function(e) {
			e.preventDefault(); //기본이벤트 삭제(submit기능)
			var userid = $('.userid').val();
			var subject = $('.subject').val();
			var content = $('.content').val();
			
			if(userid==''){
				alert('아이디를 입력해주세요.');
				$('.userid').focus();
			}else if(subject==''){
				alert('제목을 입력해주세요.');
				$('.subject').focus();
			}else if(content==''){
				alert('내용을 입력해주세요.');
				$('.content').focus();
			}else{
				$('#frmAdd').attr('action', '${path}/inquiry/add')
							.attr('method', 'post')
							.attr('enctype', 'multipart/form-data').submit();
			}		
		});	
	});
</script>
</head>
<body>
	<h2>추가</h2>
	
	<form id="frmAdd">
			<table border="1">
			<tr>
				<th>아이디</th>
				<td> <input type="userid" name="userid" class="userid" value="${sessionScope.userid}"> </td>
			</tr>		
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject" class="subject"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="23" name="content" class="content"></textarea> </td>
			</tr>
			<tr>
				<th>파일 <i class="far fa-plus-square addfile"></i></th>
				<td>
					<ol id="fileList">
						<li> <input type="file" name="files" > <i class="far fa-minus-square delfile"></i> </li>					
					</ol>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnAdd">등록</button>
				</td>
			</tr>		
			</table>
		</form>	
</body>
</html>