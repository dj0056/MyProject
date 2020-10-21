<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script>
		function boardWrite(){
			boardwriteform.submit();
		}
		function boardList(){
			location.href='boardlistpaging';
		}
	</script>
</head>
<body>
	<h2>BoardWrite.jsp</h2>
	<form action="boardwrite" method="post" name="boardwriteform">
		작성자 : <input type="text" name="bwriter" id="bwriter"><br />
		비밀번호 : <input type="password" name="bpassword" id="bpassword"><br />
		제목 : <input type="text" name="btitle" id="btitle"><br />
		글내용 : <textarea rows="20" cols="30" name="bcontents" id="bcontents"></textarea><br />
	</form>
	<button onclick="boardWrite()">글등록</button>
	<button onclick="boardList()">글목록</button>
</body>
</html>