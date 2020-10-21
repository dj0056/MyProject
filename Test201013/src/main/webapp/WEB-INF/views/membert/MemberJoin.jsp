<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extaaddress").value = extraAddr;
            
            } else {
                document.getElementById("extaaddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("taddress").focus();
        }
    }).open();
}

	function numFn() {
		var exp = /^\d{3}-\d{4}-\d{4}$/;
		var num = document.getElementById("tphone").value;
		var numch = document.getElementById("numCheck");
		if(num.match(exp)) {
			numch.style.color = "green";
			numch.innerHTML = "전화번호 형식 OK";
		} else{
			numch.style.color = "red";
			numch.innerHTML = "전화번호 형식 NO";
		}
	}
	
	
	function idCheck(){
		var inputId = document.getElementById("tid").value;
		var idch = document.getElementById("idch");
		$.ajax({
			type : "post",
			url : "idoverlap",
			data : {"tid" : inputId},
			dataType : "text",
			success : function(result){
				if(result=="OK"){
					idch.style.color = "green";
	                idch.innerHTML = "사용가능한 ID입니다.";
				} else{
					idch.style.color = "red";
	                idch.innerHTML = "이미 사용중인 ID입니다.";
				}
			}
		});
	}
	
	function pwdeqFn(){
        console.log("pwdeqFn 함수 호출");
        var pwd = document.getElementById("tpassword").value;
        var pwdch = document.getElementById("tpasswordch").value;
        var eqMsg = document.getElementById("pwdEq");
        if(pwd==pwdch){
            console.log('비밀번호 일치');
            eqMsg.style.color ="green";
            eqMsg.innerHTML = "비밀번호 일치";
        } else{
            console.log('비밀번호 불일치');
            document.getElementById("pwdEq").style.color ="red";
            document.getElementById("pwdEq").innerHTML = "비밀번호 불일치";
        }
    }
    function pwdCheck(){
        var exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
        // /^ 정규식 시작 소문자a-z, 대문자A-Z, 숫자, 특수문자, 한번더 쓴거, 8~16 자릿수 제한
        var pwd = document.getElementById("tpassword").value;
        var pwdch = document.getElementById("pwdch");
        if(pwd.match(exp)){
            pwdch.style.color = "green";
            pwdch.innerHTML = "비밀번호 형식 맞음"
        } else{
            pwdch.style.color = "red";
            pwdch.innerHTML = "비밀번호 형식 안마즈요"
        }
    }

</script>
</head>
<body>
	<h2>MemberJoin.jsp</h2>
	
	카카오 아이디 : ${kakaoId} <br />
	네이버 아이디 : ${naverId}
	
	<form action="memberjoin" method="post" name="loginform" enctype="multipart/form-data">
		<c:choose>
			<c:when test="${kakaoId ne null}">
				아이디: <input type="text" name="tid" id="tid" onkeyup="idCheck()"> 
				<input type="hidden" name="kakaoId" value="${kakaoId}"><br />
				<span id="idch"></span><br />
			</c:when>
			<c:when test="${naverId ne null}">
				아이디: <input type="text" name="tid" id="tid" onkeyup="idCheck()"> 
				<input type="hidden" name="naverId" value="${naverId}"><br />
				<span id="idch"></span><br />
			</c:when>
			<c:otherwise>
				아이디: <input type="text" name="tid" id="tid" onkeyup="idCheck()"> 
				<span id="idch"></span><br />
			</c:otherwise>
		</c:choose>
		비밀번호: <input type="text" id="tpassword" name="tpassword" onkeyup="pwdCheck()"><br>
            <span id="pwdch"></span><br>
            비밀번호확인: <input type="text" id="tpasswordch" name="tpasswordch"
                    onkeyup="pwdeqFn()"><br>
            <span id="pwdEq"></span><br>
		이름: <input type="text" name="tname" id="tname"><br />
		생년월일: <input type="date" name="tbirth" id="tbirth"><br />
		이메일: <input type="text" name="temail" id="temail"><br />
		주소 : <input type="text" id="postcode" name="postcode" placeholder="우편번호">
             <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
             <input type="text" id="address" name="address" placeholder="주소"><br>
             <input type="text" name="taddress" id="taddress" placeholder="상세주소">
 			 <input type="text" id="extaaddress" name="extaaddress" placeholder="참고항목"><br />
		폰번호: <input type="text" id="tphone" name="tphone" onkeyup="numFn()" onblur="disappear()"><br />
				<span id="numCheck"></span><br />
		프로필사진: <input type="file" name="tfile" id="tfile"><br />
		<input type="submit" value="회원가입">
	</form>
</body>
</html>