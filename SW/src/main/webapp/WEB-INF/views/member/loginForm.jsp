<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>로그인폼</title>
	<script 
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#lBtn").click(function(){
				//무결성검사
				
				$("#loginForm").submit();
			});
		});
	</script>
</head>
<body>
<%
		//세션에서 데이터 꺼내보자
		//왜? 로그인 했는지 안했는지의 차이는 세션에  (특정)데이터가 있고 없고의 차이니까..
		String id = (String)session.getAttribute("UID");
	  String nick = (String)session.getAttribute("UNICK");
	  if(id==null||id.length()==0){ 
	%>
			<%-- 로그인 안 했다면 	로그인폼  --%>
	<form method="POST" id="loginForm" action="../member/loginProc.do">
		<table width="500" border="1" align="center">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" id="id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" id="pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="lBtn" value="Login">
				</td>
			</tr>
		</table>
	</form>
	<% 
		}else{  
	%>
			<%-- 로그인    했다면 	출력  --%>
			<table width="400" border="1" align="center">
				<tr>
					<td><%= nick %>님 접속중</td>
				</tr>
				<tr>
					<td>
					<!-- <a href="../member/logout.jsp">로그아웃</a>
					&nbsp;-->
					<a href="../index.jsp">메인대문</a>
					</td>
				</tr>
			</table>
	<%	  
	  }
	%>	
</body>
</html>
