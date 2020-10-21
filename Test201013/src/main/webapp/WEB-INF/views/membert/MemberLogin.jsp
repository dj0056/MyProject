<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>MemberLogin.jsp</h2>
	<form action="memberlogin" method="post" name="memberloginform">
		아이디: <input type="text" id="tid" name="tid"><br />
		비밀번호: <input type="password" id="tpassword" name="tpassword"><br />
		<input type="submit" value="로그인">
	</form>
	<button onclick="location.href='home'">Home</button>
	
	<h3>카카오로 로그인</h3>
	<a href="kakaologin">
		<img src="${pageContext.request.contextPath}/resources/joloimg/kakao_login_medium_narrow.png">
	</a>
	
	<h3>네이버로 로그인</h3>
	<a href="naverlogin">
		<img src="${pageContext.request.contextPath}/resources/joloimg/네이버 아이디로 로그인_축약형_Green.PNG">
	</a>
</body>
</html>