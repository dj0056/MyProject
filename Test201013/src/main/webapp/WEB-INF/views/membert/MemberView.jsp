<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${memberView.tid}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${memberView.tname}</td>
		</tr>
		<tr>
			<th>출생연도</th>
			<td>${memberView.tbirth}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${memberView.temail}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${memberView.address}</td>
		</tr>
		<tr>
			<th>상세 주소</th>
			<td>${memberView.taddress}</td>
		</tr>
		<tr>
			<th>폰번호</th>
			<td>${memberView.tphone}</td>
		</tr>
		<tr>
			<th>프로필 이미지</th>
			<td><img src="<spring:url value='/resources/profile/${memberView.tprofile}'/>" width="200" height="200"></td>
		</tr>
	</table>
	<button onclick="location.href='memberlist'">돌아가기</button>
</body>
</html>