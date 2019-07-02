<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			var count=1; //버튼의 개수제한용
			
			//글쓰기버튼 클릭시 id="sBtn"
			$("#sBtn").click(function(){
					//무결성검사하세요
					
					$("#wfrm").submit();
			});
			
			//첨부파일 추가버튼 클릭시 id="aBtn" 첨부파일 동적추가
			$("#aBtn").click(function(){
				count++;
				if(count==6){
					alert("최대 5개까지 파일을 첨부할 수 있어요");
					count=5;
					return;
				}
				
				//추가할 폼을 만든다
				var tr = "<tr><th>첨부파일</th>";
				tr +="<td><input type='file' name='files' id='files"+count+"'></td></tr>";
				
				//원하는 위치에 배치시킨다
				$("#copy").before(tr);
			});
			
  		//첨부파일 삭제버튼 클릭시 id="dBtn"	첨부파일 동적삭제
			$("#dBtn").click(function(){
				if(count==1){
					alert("최소 1개는 있어야 합니다");
					return;
				}
				
				//해당 줄tr삭제
				//(count는 마지막번호인) id가 copy인 요소의 부모들중에서 tr객체
				var tr = $("#files"+count).parents("tr");
				tr.remove();
				count--;
			});
		});
	</script>
</head>
<body>
  <%-- 글쓰기 폼 보여주자
  		 우리는 파일업로드 기능을 가진 게시판을 보여주기로 했으므로
  		 업로드 기능을 구현하기위해 encType="multipart/form-data"로 지정하여
  		 파라미터 방식이 아닌 스트림방식으로 처리해야 한다	 --%>
  <form id="wfrm" method="post" action="../fileBoard/writeProc.do" 
        encType="multipart/form-data">
  	<table border="1" width="800" align="center">
  		<tr>
  			<th>작성자</th>
  			<td>${sessionScope.UID}</td>
  		</tr>
  		<tr>
  			<th>제목</th>
  			<td><input type="text" name="title" id="title"></td>
  		</tr>
  		<tr>
  			<th>내용</th>
  			<td><textarea name="body" id="body"></textarea></td>
  		</tr>
  		<tr>
  			<th>비밀번호</th>
  			<td><input type="password" name="pw" id="pw"></td>
  		</tr>
  		<tr>
  			<th>첨부파일</th>
  			<td>
  				<input type="button" value="추가" id="aBtn">
  				<input type="button" value="삭제" id="dBtn">
  			</td>
  		</tr>
  		<tr>
  			<th>첨부파일</th>
  			<td><input type="file" name="files" id="files"></td>
  		</tr>		
  		<tr id="copy">
  			<td colspan="2" align="center">
  				<input type="button" id="sBtn" value="글쓰기" />
  			</th>
  		</tr>
  	</table>
  </form>		 
</body>
</html>









