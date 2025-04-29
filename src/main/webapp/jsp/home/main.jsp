<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String username = (String) request.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
body {
	  margin: 0;
  padding: 0;
  display:flex;
justify-content: flex-start;
align-items: center;
flex-direction: column;
}
a{
  color: inherit;
  text-decoration: inherit;
}
.title{
wigth:100vw;
font-size: 3rem;
}

ul,
li {
  list-style: none;
  margin: 0;
  padding: 0;
}
ul>li:hover>a{
color: #fa6e8c;
}

.active{
display: none;
}
.loginname{
font-size: 1.5rem;
}

</style>
</head>
<body>
	<h1 class="title">메인 페이지</h1>
	<%if(username != null){%>
	<div class = "loginname"><%=username %>님 환영합니다.</div>
<%} %>
	<ul>
		<li><a href="../article/list">리스트로 이동</a></li>
		<li><a class ="<%= username == null ? "":"active"%>" href="http://localhost:8080/AM_JSP_25_04/member/join2">회원가입</a></li>
		<li><a class ="<%= username == null ? "":"active"%>" href="http://localhost:8080/AM_JSP_25_04/member/login">로그인</a></li>
		<li><a class ="<%= username == null ? "active":""%>" href="http://localhost:8080/AM_JSP_25_04/member/logout">로그아웃</a></li>
	</ul>

</body>
</html>