<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
	<%
		//로그아웃되면 기존의 정보를 제거
		session.invalidate();
	 %>
	 <h1>로그아웃 되셨습니다</h1>
</body>
</html>




