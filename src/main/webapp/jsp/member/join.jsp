
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>회원가입</title>
<style>
body {
	  margin: 0;
  padding: 0;
  font-size: 1rem;
  font-weight: bold;
}
a{
  color: inherit;
  text-decoration: inherit;
}
.id{
font-size: 3rem;
}

h2{
width: 80vw;
margin: 0 auto;
margin-top: 20px;
display: flex;
flex-direction: column;
align-items: center;
}

form{
width: 20vw;
margin: 0 auto;
margin-top: 20px;
display: flex;
flex-direction: column;
}

input{
  display: block;
  width: 200px;
  height: 30px;
  border: none;
background-color: rgba(168, 172, 173,0.3);
  border-radius: 10px;s
}
div{
padding: 10px 0;
}
button:hover{
border: 2px solid #fa6e8c;
color: #fa6e8c;
}

</style>

</head>
<body>
<h2 class = "title">회원가입</h2>

<form action="../member/join" method="post">

<div>아이디:
<input type="text" name="loginid" required/></div>
<div>비밀번호: 
<input name="loginpw" required/></div>
<div>비밀번호 확인: 
<input name="loginpw2" required/></div>
<div>이름: 
<input name="name" required/></div>
<br>
<div><button type="submit">확인</button></div>

</form>
</body>
</html>