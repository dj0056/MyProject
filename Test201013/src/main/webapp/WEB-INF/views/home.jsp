<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h2>Home.jsp</h2>
	
	<button onclick="location.href='memberjoinform'">회원가입</button>
	<button onclick="location.href='memberloginform'">로그인화면</button>
	
	<h3>카카오 회원가입</h3>
	<a href="kakaojoin">
		<img src="${pageContext.request.contextPath}/resources/joloimg/kakao_login_medium_narrow.png">
	</a>
	
	<h3>네이버 회원가입</h3>
	<a href="naverjoin">
		<img src="${pageContext.request.contextPath}/resources/joloimg/네이버 아이디로 로그인_축약형_Green.PNG">
	</a>
	
	
	
	
	
	
</body>
</html>
