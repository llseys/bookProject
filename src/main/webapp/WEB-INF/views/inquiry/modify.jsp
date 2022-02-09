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
		
		$('#btnList').on('click', function() {
			location.href='${path}/inquiry/list';
		});
		
		$('#btnModi').on('click', function() {
			$('#frmModi').attr('action', '${path}/inquiry/modify')
			.attr('method', 'post')
			.attr('enctype', 'multipart/form-data').submit();
		});
		
	});
</script>
</head>
<body>
	<h2>수정</h2>
		<button type="button" id="btnList">목록</button>
		<button type="button" id="btnModi">등록</button>
		<form id="frmModi">
			<table border="1">
			<tr>
				<td><input type="hidden" name="inquiryno" class="inquiryno" value="${inq.inquiryno}"> </td>
			</tr>
			<tr>
				<th>아이디</th>
				<td> <input type="userid" name="userid" class="userid" value="${sessionScope.userid}"> </td>
			</tr>		
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject" class="subject" value="${inq.subject}"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="23" name="content" class="content"></textarea> </td>
			</tr>
			<tr>
				<th>파일 <i class="far fa-plus-square addfile"></i></th>
				<td>
					<c:forEach var="inqFile" items="${inqFileList}">
						${inqFile.filename} <input type="checkbox" name="removeFile" value="${inqFile.inquiryfileno}">삭제 <br>
					</c:forEach> <hr>
					<ol id="fileList">
						<li> <input type="file" name="files" > <i class="far fa-minus-square delfile"></i> </li>					
					</ol>
				</td>
			</tr>	
			</table>
		</form>	
</body>
</html>