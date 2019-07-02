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
			//글쓰기버튼 클릭시
			$("#wBtn").click(function(){
				$(location).attr("href","../fileBoard/writeForm.do");
			});
			
			//검색하기 버튼 클릭시 / id="target" name="target" /id="word"
			$("#sBtn").click(function(){
				alert("#sBtn  ok");
				//무결성검사
			});
		});	
	</script>
</head>
<body>
  <h1>*fileBoard목록(boardList.jsp)*</h1>
  <%-- 검색창 만들기 --%>
	<form method="POST" id="" action="">
		<table border="1" width="800" align="center">
			<tr>
				<td align="center">
					<%-- 검색대상 --%>
					<select id="target" name="target" >
						<option value="title">제목</option>
						<option value="body">내용</option>
						<option value="writer">작성자</option>
						<option value="both">제목+내용</option>
					</select>
					<%-- 검색단어 --%>
					<input type="text" id="word" name="word"/>
					<%-- 검색버튼 --%>
					<input type="button" id="sBtn"value="검색"/>
				</td>
			</tr>
		</table>
	</form>
	
  <%-- 목록 보여주기 --%>
  <table border="1" width="800" align="center">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>작성자</th>
				<th>첨부파일</th>
			</tr>
			<c:forEach items="${LIST}" var="data">
				<tr>
					<td>${data.no }</td>
					 <td><a href="../fileBoard/hitProc.do?oriNo=${data.no}&nowPage=${PINFO.nowPage}">${data.title}</a></td>
					<%-- <td><a href="../fileBoard/boardView.do?oriNo=${data.no}&nowPage=${PINFO.nowPage}">${data.title}</a></td> --%>
					<td>${data.WDate2 }</td>
					<td>${data.hit }</td>
					<td>${data.nick }</td>
					<td>${data.fileCount }</td>
				</tr>
			</c:forEach>
		</table>		 
  <%-- 페이지 이동 --%>
  	<table name="tbl2" id="tbl2" width="800" align="center">
		<tbody>
			<tr>
				<td align="center">
					확인용:PINFO.startPage=${PINFO.startPage}<br/>
					<%-- 1. 이전단추 만들기 --%>
					<%-- PINFO는 컨트롤러에서 페이징관련정보가 담긴 모델 --%>
					<c:if test="${PINFO.startPage eq 1}">[이전]</c:if>
					
					<c:if test="${PINFO.startPage ne 1}">
					<%-- 링크는 목록보기 --%>
					<a href="../fileBoard/boardList.do?nowPage=${PINFO.startPage-1}">[이전]</a>
					</c:if>			 
					
					<%-- 2. 페이지번호 [1][2][3][4][5]만들기 --%>
					<c:forEach  var="page"
								begin="${PINFO.startPage}"
								end="${PINFO.endPage}"   
								step="1">
					  <a href="../fileBoard/boardList.do?nowPage=${page}">[${page}]</a>
					</c:forEach>
					
					
					<%-- 3. 다음단추 만들기 --%>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다음]
					</c:if>
					
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="../fileBoard/boardList.do?nowPage=${PINFO.endPage+1}">[다음]</a></c:if>	
					
				</td>
			</tr>
		</tbody>
	</table>
  
  <%-- 기타 기능 --%>
    <table border="1" width="800" align="center">
			<tr>
				<td align="center">
					<input type="button" id="wBtn" value="글쓰기"/>
				</td>
			</tr>
		</table>		
</body>
</html>





