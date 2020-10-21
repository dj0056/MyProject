<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function memberUpdate(){
		location.href="memberupdate";
	}
	function memberList(){
		location.href="memberlist";
	}
	function memberLogout(){
		location.href="memberlogout";
	}
	function boardSearch(){
		searchform.submit();
	}
	function boardHit(){
		location.href="boardhit";
	}
</script>
</head>
<body>
	<c:if test="${sessionScope.loginId eq 'admin'}">
		<button onclick="memberList()">회원 관리</button>
	</c:if>
	<button onclick="memberUpdate()">정보 수정</button>
	<button onclick="memberLogout()">로그아웃</button>
	<h2>MemberMain.jsp</h2>
	${sessionScope.loginId}님 환영!!!!
	
	<button onclick="boardHit()">조회순 정렬</button>
	
	
	<form action="boardsearch" method="get" name="searchform">
		<select id="searchtype" name="searchtype">
			<option value="searchtitle">제목</option>
			<option value="searchwriter">작성자</option>
		</select>
		<input type="text" name="keyword" placeholder="검색어입력">
		<input type="button" onclick="boardSearch()" value="검색">
	</form>
	
	
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>작성자</td>
			<td>글제목</td>
			<td>작성일자</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="board" items="${boardList}" >
			<tr>
				<td>${board.bnumber}</td>
				<td><a
					href="memberview?tid=${board.bwriter}">${board.bwriter}</a></td>
				<td><a
					href="boardview?bnumber=${board.bnumber}&page=${paging.page}">${board.btitle}</a></td>
				<td>${board.bdate}</td>
				<td>${board.bhits}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><button onclick="location.href='boardwritefileform'">글쓰기</button><button onclick="location.href='boardwriteform'">글쓰기</button> </td>
		</tr>
	</table>

	<!-- 페이징 처리 -->
	<c:if test="${paging.page<=1}">
	[이전]&nbsp;
	</c:if>
	
	<c:if test="${paging.page>1}">
		<a href="boardlistpaging?page=${paging.page-1}">[이전]</a>&nbsp;
	</c:if>
	
	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
			${i}
			</c:when>
			<c:otherwise>
				<a href="boardlistpaging?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${paging.page>=paging.maxPage}">
		[다음]
	</c:if>
	
	<c:if test="${paging.page<paging.maxPage}">
		<a href="boardlistpaging?page=${paging.page+1}">[다음]</a>
	</c:if>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>