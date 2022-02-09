<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../includeFile.jsp" %>
    <link href="${path}/resources/css/tableStyle.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	#disNone{
		display: none;
	}

</style>
<!-- levelSpace : 헬퍼의이름  -->
<!-- handlebars cdn-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
	<!-- 탬플릿소스  -->
<script id="template_source" type="text/x-handlebars-template">

	{{#each .}}
		{{#levelSpace relevel}} 
		{{/levelSpace}}

		<div id="reply{{replyno}}">
			<div id="disNone">
				{{replyno}} 
				<span id="restep{{replyno}}"> {{restep}} </span> 
				<span id="relevel{{replyno}}"> {{relevel}} </span> <br>
			</div>
	
			닉네임 : {{nicname}} <br>
			내용 : <span id="content{{replyno}}">{{content}}</span> <br>
			<button class="btnReReplyAdd" value="{{replyno}}">댓글</button>
	   		<button class="btnReplyModify"value="{{replyno}}">수정</button>
	   		<button class="btnReplyRemove"value="{{replyno}}">삭제</button> 
		</div>
	{{/each}}

</script>

<style type="text/css">
 #divReply{
 	padding-left: 25%;
  	padding-right: 25%;
 }

 #tblImg{
	display: flex;
	justify-content: center;
 }
 
 .fa-reply{
 	transform: rotate(180deg);
 	
 }
 
</style>


<script type="text/javascript">
	// 핸들바의 헬퍼작성
	// relevel에 따라서 반복문 헬퍼(함수)
	Handlebars.registerHelper('levelSpace', function(relevel) {
		var result = '';
		for(var i=0; i<relevel; i++){
			result += '<i class="fas fa-reply"></i>';
		}
		return result;
	});



	$(function() {
		// 조회수 +1 
		$('.fa-heart').on('click', function() {
			var reviewno = ${review.reviewno};
			$.ajax({
				url: '${path}/book/likeUp/'+reviewno,
				type: 'put',
				dataType: 'text',
				contentType: 'application/json; charset=UTF-8',
				success: function(data) {
					console.log(data);
					$('#td_likecnt').html(data);
				},
				error: function(err) {
					console.log(err);
					alert("실패");
				}
			});
		});
		
		//게시물삭제
		$('#btnRemove').on('click', function() {
			//본인작성 게시물확인 아니면 권한없음 띄우기
			if('${review.userid}'!='${sessionScope.userid}'){
				alert('권한없음');
				return;
			}
			//본인작성 게시물일시 
			var result = confirm('삭제하시겠습니까?');
			console.log(result);
			if(result){
				location.href='${path}/book/remove?reviewno=${review.reviewno}';
			}
		});
		
		//게시물수정
		$('#btnModify').on('click', function() {
			
			alert('수정이 완료되었습니다.');
			alert('삭제가 완료되었습니다.');

			//본인작성 게시물확인 아니면 권한없음 띄우기
			if('${review.userid}'!='${sessionScope.userid}'){
				alert('권한없음');
				return;
			}
			//본인작성 게시물일시 
			var result = confirm('수정하시겠습니까?');
			console.log(result);
			if(result){
				location.href='${path}/book/modify?reviewno=${review.reviewno}';
			}
		});
		
		//---------------------------------------------------------------------------------------------------
		//댓글처리
		
		// 댓글리스트 조회
		function replyList() {
			//댓글추가창이 게시물 테이블 아래에 위치 : 댓글리스트 조회시 유지하기위해(백업)
			$('#divReply').insertAfter('#tblReview').hide();
			//댓글수정창 게시물 테이블 아래에 위치 : 댓글리스트 조회시 유지하기위해(백업)
			$('#divReplyModify').insertAfter('#tblReview').hide();
			
			var reviewno = ${review.reviewno};
			
			$.ajax({
				url: '${path}/reply/list/'+reviewno,
				type: 'get',
				dataType: 'json',
				success: function(data) {
					var source = $('#template_source').html();
					var template = Handlebars.compile(source); // template객체생성
					console.log(template(data));
					$('#divReplyList').html(template(data));		
				},
				error: function(err) {
					console.log(err);
					alert('실패');
				}
			});
		}
		
		//원본글의 댓글버튼 클릭했을떄
		$('#btnReply').on('click', function() {
			$('#divReply').insertAfter('#tblReview'); //게시물 테이블 아래에 위치
			$('#divReply').show();
			$('#restep').val('0'); // 원본글0 => 댓글1
			$('#relevel').val('0'); // 원본글0 => 댓글1	
			$('#replyNicname').val(''); //전에 쓴내용 안뜨게 클리어
			$('#replyContent').val('');//전에 쓴내용 안뜨게 클리어
		});
		
		// 댓글창의 추가버튼 클릭
		$('#btnReplyAdd').on('click', function() {
			var reviewno = ${review.reviewno}; //게시물번호 
			var nicname = $('#replyNicname').val();
			var content = $('#replyContent').val();
			var restep = $('#restep').val(); //글순서
			var relevel = $('#relevel').val(); //글레벨
		
			if(nicname==''){
				alert('닉네임 입력해주세요');
				$('#replyNicname').focus();
				return ; //함수의 종료
			}else if(content==''){
				alert('내용을 입력해주세요.');
				$('#replyContent').focus();
				return ; //함수의종료
			}
			
			
			$.ajax({
				url: '${path}/reply/',
				type: 'post', //method방식 : 추가
				data: JSON.stringify({reviewno,nicname,content,restep,relevel}), //보낼데이터
				dataType: 'text', //받을데이터 타입
				contentType: 'application/json; charset=UTF-8', //보낼 데이터 타입
				success: function(data) {
					console.log(data);
					replyList(); //댓글쓰면 바로 조회돼서 볼수있게
				},
				error: function(err) {
					console.log(err);
					alert('실패');
				}
			});
		});
		
		
		//댓글의 댓글 추가
		//댓글의댓글 버튼이 동적으로 생성됐기에 부모 선택자의 자식한테 엘리멘트달기
		$('#divReplyList').on('click', '.btnReReplyAdd', function() {
			var replyno = $(this).val(); // replyno 구하기
			//  $('#divReply')를 ('#reply'+replyno)뒤에 넣기
			$('#divReply').insertAfter('#reply'+replyno); //답글클릭하면 답글창 나타나게
			$('#divReply').show();
			$('#replyNicname').val(''); //전에 쓴내용 안뜨게 클리어
			$('#replyContent').val('');//전에 쓴내용 안뜨게 클리어
			
			//restep 과 relevel 을 읽어서 댓글입력창에 set
			var restep = $('#restep'+replyno).text(); //span 읽기
			var relevel = $('#relevel'+replyno).text(); //span 읽기
			$('#restep').val(restep);
			$('#relevel').val(relevel);
		});
		
		
	//댓글의 수정버튼 클릭
		
		$('#divReplyList').on('click', '.btnReplyModify', function() {
			var replyno = $(this).val(); // replyno 구하기
			$('#divReplyModify').insertAfter('#reply'+replyno); //수정클릭하면 수정입력창 나타나게
			$('#divReplyModify').show();
					
			//replyno 셋팅
			$('#replyno').val(replyno);
			//content셋팅
			$('#replyContentModify').val($('#content'+replyno).text());
			
			
		});
		
		// 수정창의 수정하기버튼(실제수정) 클릭했을때
		$('#btnReplyModify').on('click', function() {
			
			var replyno = $('#replyno').val();
			var content = $('#replyContentModify').val();
			
			
			$.ajax({
				url: '${path}/reply/',
				type: 'put',
				data: JSON.stringify({replyno, content}),
				contentType: 'application/json; charset=URF-8',
				dataType: 'text',
				success: function(data) {
					console.log(data);
					replyList();
				},
				error: function(err) {
					console.log(err);
				}
			});
			
			
			
			
		});
		
		//댓글 삭제버튼 클릭
		
		$('#divReplyList').on('click','.btnReplyRemove', function() {
			var result = confirm('삭제하겠습니까?');
			if(result){
				var replyno = $(this).val();
				$.ajax({
					url: '${path}/reply/'+replyno,
					type: 'delete',
					dataType: 'text',
					success: function(data) {
						console.log(data);
						replyList();
					},
					error: function(err) {
						console.log(err);
						alert('실패');
					}
				});	
			}	
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	//댓글 입력창 숨기기
	$('#divReply').hide();
	//댓글 수정창 숨기기
	$('#divReplyModify').hide();

	// 폼 완료되면 function replyList()함수실행
	replyList(); 	
		
	});
	


</script>
</head>
<body>
	<h1>상세리뷰</h1>
	<br><br>
	
	<div id="tblImg">
		<table border="1">
			<tr>
				<td> <img alt="" src="${book.bigfileurl}" > </td>
				<td>${book.title}</td>
			</tr>
		</table>	
	</div>


	<hr>
	<table border="1" id="customers">
		<tr>
			<td>작성자</td>
			<td>${nicname}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${review.subject}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${review.content}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${review.readhit}</td>
		</tr>
		<tr>
			<td>
				좋아요
				<i class="fas fa-heart"></i>
			</td>
			<td id="td_likecnt">
				${review.likehit}
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button id="btnModify">수정</button>
				<button id="btnRemove">삭제</button>
				<button id="btnReply">댓글</button>
			</td>
		</tr>
	</table>
	<hr>
	
	
	
	
	
	
	
	<!-- 댓글입력창  --------------------------------------------------------------------------------------------------------------------------------------->
	<div class="card mb-2" id="divReply">
		<div class="card-header bg-light" >
		        <i class="fa fa-comment fa"></i> REPLY
		         <input type="hidden" id="restep" size="3"> 
		       	 <input type="hidden" id="relevel" size="3">
		</div>
		<div class="card-body">
			<ul class="list-group list-group-flush">
			    <li class="list-group-item">
				<div class="form-inline mb-2">
					<label for="replyEmail"><i class="far fa-envelope"></i></label>
					<input type="text" class="form-control ml-2" placeholder="Enter yourId" id="replyNicname">
				</div>
				<textarea class="form-control" id="replyContent" rows="3"></textarea>
				<button type="button" class="btn btn-dark mt-3"  id ="btnReplyAdd">추가</button>
			    </li>
			</ul>
		</div>
	</div>
	
	
	<!-- 댓글수정창  -->
	<div class="card mb-2" id="divReplyModify">
		<div class="card-header bg-light" >
		        <i class="fa fa-comment fa"></i> REPLY 수정
		        replyno <input type="text" id="replyno">
		</div>
		<div class="card-body">
			<ul class="list-group list-group-flush">
			    <li class="list-group-item">
				<textarea class="form-control" id="replyContentModify" rows="3"></textarea>
				<button type="button" class="btn btn-dark mt-3"  id ="btnReplyModify">수정하기</button>
			    </li>
			</ul>
		</div>
	</div>

	
	<!-- 댓글조회 ------------------------------------------------------------------------------------------------------------------------------------------>
	<div id="divReplyList">
		
	</div>
	
	
</body>
</html>