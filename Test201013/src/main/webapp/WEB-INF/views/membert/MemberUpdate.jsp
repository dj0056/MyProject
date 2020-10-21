<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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

</script>
</head>
<body>
	<h2>MemberUpdate.jsp</h2>
	<form action="memberupdateprocess" method="post" enctype="multipart/form-data">
		이메일: <input type="text" name="temail" id="temail"><br />
		주소 : <input type="text" id="postcode" name="postcode" placeholder="우편번호">
             <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
             <input type="text" id="address" name="address" placeholder="주소"><br>
             <input type="text" name="taddress" id="taddress" placeholder="상세주소">
 			 <input type="text" id="extaaddress" name="extaaddress" placeholder="참고항목"><br />
		폰번호: <input type="text" id="tphone" name="tphone" onkeyup="numFn()" onblur="disappear()"><br />
				<span id="numCheck"></span><br />
		프로필사진: <input type="file" name="tfile" id="tfile"><br />
		<input type="submit" value="수정">
	</form>
</body>
</html>