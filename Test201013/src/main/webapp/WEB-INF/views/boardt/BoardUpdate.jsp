<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function boardUpdate(){
		var password = '${boardUpdate.bpassword}';
		var passConfirm = document.getElementById("bpassword").value;
		if(password==passConfirm){
			boardupdateform.submit();
		} else{
			alert('비밀번호 틀립니다.');
		}
	}
</script>
</head>
<body>
	<h2>BoardUpdate.jsp</h2>
	<form action="boardupdateprocess" method="post" name="boardupdateform">
		<input type="hidden" name="bnumber" value="${boardUpdate.bnumber}">
		작성자: <input type="text" name="bwriter" value="${boardUpdate.bwriter}" readonly><br/>
		비밀번호: <input type="text" name="bpassword" id="bpassword"><br />
		제목: <input type="text" name="btitle" value="${boardUpdate.btitle}"> <br/>
		내용: <textarea rows="20" cols="30" name="bcontents"></textarea><br/>
	</form>
	<button onclick="boardUpdate()">글수정</button><br />
</body>
</html>