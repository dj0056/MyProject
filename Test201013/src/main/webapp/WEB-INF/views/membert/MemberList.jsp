<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	function memberViewAjax(tid){
		$.ajax({
			type : "post",
			url : "memberviewajax",
			data : {"tid" : tid},
			dataType : "json",
			success : function(result){
				console.log(result);
				console.log(result.mid);
				var output = "<table border='1'>";
				output += "<tr><th>ID</th> <th>NAME</th> <th>BIRTH</th>";
				output += "<th>ADDRESS</th> <th>ADRESS</th> <th>EMAIL</th> <th>PHONE</th></tr>";
				output += "<tr>";
				output += "<td>"+result.tid+"</td>";
				output += "<td>"+result.tname+"</td>";
				output += "<td>"+result.tbirth+"</td>";
				output += "<td>"+result.address+"</td>";
				output += "<td>"+result.taddress+"</td>";
				output += "<td>"+result.temail+"</td>";
				output += "<td>"+result.tphone+"</td>";
				output += "</tr>";
				output += "</table>";
				
				$("#memberviewdiv").html(output);
			},
			error : function(){
				console.log("실패");
			}
		});
	}
</script>
</head>
<body>
	<h2>MemberList.jsp</h2>
	<h2>관리자 모드</h2>
	<table border="1">
		<tr>
			<th>ID</th> <th>이름</th> <th>출생연도</th>
			<th>주소</th> <th>이메일</th> <th>폰번호</th>
			<th>조회</th> <th>상세조회(ajax)</th> <th>삭제</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.tid}</td>
				<td>${member.tname}</td>
				<td>${member.tbirth}</td>
				<td>${member.address}</td>
				<td>${member.temail}</td>
				<td>${member.tphone}</td>
				<td><a href="memberview?tid=${member.tid}">조회</a></td>
				<td><button onclick="memberViewAjax('${member.tid}')">조회(ajax)</button></td>
				<td><button onclick="location.href='memberdelete?tid=${member.tid}'">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="memberviewdiv">
		
	</div>
	
</body>
</html>